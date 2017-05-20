package com.convoypayments.web;

import com.convoypayments.domain.CpRmtAccount;
import com.convoypayments.domain.CpRmtTxn;
import com.convoypayments.domain.CpUser;
import com.convoypayments.mapper.CpRmtTxnMapper;
import com.convoypayments.mapper.CpUserMapper;
import com.convoypayments.util.Beans;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class UserController {

    @Resource
    private CpUserMapper cpUserMapper;

    @Resource
    private CpRmtTxnMapper cpRmtTxnMapper;

    @Resource
    private HttpSession session;

    @Value("${uploaddir}")
    private String uploaddir;

    @PostConstruct
    public void testInit() {
        CpRmtTxn cpRmtTxn = cpRmtTxnMapper.selectOne();
        cpRmtTxn.rcvNamePinyin = "a";
        cpRmtTxn.rcvBankAcctNo = cpRmtTxn.rcvBankAcctNoF6 = "666666";
        cpRmtTxn.rcvMobileNum = "123456789";
        cpRmtTxn.txnToken = "z";
        cpRmtTxn.cpMasterAcctNum = BigInteger.TEN;
        cpRmtTxn.cpRmtAcctNum = BigInteger.ONE;
        cpRmtTxn.txnId = "123";
        //Beans.fill(cpRmtTxn);bug
        //cpRmtTxnMapper.insert(cpRmtTxn);//这条插入语句出错可能是mybatis或oracle的bug

    }




    private void printJson(Object o) {
        ObjectMapper om = new ObjectMapper();
        om.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        try {
            System.out.println(om.writeValueAsString(o));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @GetMapping
    public String index() {
        return "redirect:cn/index.html";
    }


    @PostMapping("{lang}/index.html")
    public String index(String name, String bankacct, String mobile, String token, CpRmtTxn cpRmtTxn, @PathVariable String lang) {
        cpRmtTxn.rcvNamePinyin = name;
        cpRmtTxn.rcvBankAcctNo = cpRmtTxn.rcvBankAcctNoF6 = bankacct;
        cpRmtTxn.rcvMobileNum = mobile;
        cpRmtTxn.txnToken = token;

        CpRmtAccount cpRmtAccount = cpRmtTxnMapper.keyLogin(cpRmtTxn);

        if (cpRmtAccount == null)
            return "redirect:index_err.html";

        cpRmtAccount.loginToken=token;

        if ("cn".equals(lang) && cpRmtAccount.rcvNameSchi != null)
            cpRmtTxn.rcvNamePinyin = cpRmtAccount.rcvNameSchi;

        session.setAttribute("cpRmtTxn", cpRmtTxn);
        session.setAttribute("cpRmtAccount0", cpRmtAccount);

        return "redirect:createacct.html";
    }

    @PostMapping("{lang}/createacct.html")
    public String createacct(String uname, String pwd, CpUser cpUser, @PathVariable String lang) {

        CpRmtAccount cpRmtAccount = (CpRmtAccount) session.getAttribute("cpRmtAccount0");

        if (cpRmtAccount == null)
            return "redirect:index.html";

        cpUser.username = uname;
        cpUser.password = new Sha256Hash(pwd).toHex();

        cpUser.cpMasterAccount = cpRmtAccount.cpMasterAcctNum;
        cpUser.cpRmtAccount = cpRmtAccount.cpRmtAcctNum;
        cpUser.mobileNum = cpRmtAccount.rcvMobileNum;
        cpUser.bankAcctNumF6 = cpRmtAccount.rcvBankAcctNoF6;
        cpUser.namePinyin = cpRmtAccount.rcvNamePinyin;
        cpUser.loginToken = cpRmtAccount.loginToken;
        cpUser.idProveUploaded="N";
        cpUser.idProveUploadTs=new Date();
        cpUser.idProveUpdateTs=new Date();
        cpUser.idProveUploadPath="";
        cpUser.addrProveUploaded="";
        cpUser.addrProveUpdateTs=new Date();
        cpUser.addrProveUploadPath="";
        cpUser.addrProveUploadTs=new Date();

        cpUserMapper.insert(cpUser);
        cpUserMapper.insertUserRoleMember(cpUser.id);

        return "redirect:userlogin.html";
    }

    @PostMapping("{lang}/userlogin.html")
    public String userlogin(String usrname, String pswd00, CpUser cpUser, @PathVariable String lang) {
        pswd00 = new Sha256Hash(pswd00).toHex();

        CpRmtAccount cpRmtAccount = cpUserMapper.userLogin(usrname, pswd00);

        if (cpRmtAccount == null)
            return "redirect:userlogin.html";

        session.setAttribute("cpRmtAccount", cpRmtAccount);
        List<CpRmtTxn> cpRmtTxns = cpRmtTxnMapper.getByAccount(cpRmtAccount);

        session.setAttribute("cpRmtTxns", cpRmtTxns);
        session.setAttribute("cpRmtTxns4", cpRmtTxns.size() > 4 ? cpRmtTxns.subList(0, 4) : cpRmtTxns);


        return "redirect:acctinfo.html";
    }

    @PostMapping("{lang}/upload")
    public String issueCard(MultipartFile file,@PathVariable String lang) throws IOException {
        String name=file.getName();
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getContentType());
        file.transferTo(new File(uploaddir+"/"+file.getOriginalFilename()));
        return "redirect:acctinfo.html";
    }



    @GetMapping("{lang}/{a}.html")
    public String en(@PathVariable String a, @PathVariable String lang) {
        //应该用过滤器或拦截器


        /*switch (a) {
            case "createacct":
                if (session.getAttribute("cpRmtAccount0") == null)
                    return "redirect:index.html";
                break;
            case "acctinfo":
            case "txhistory":
                if (session.getAttribute("cpRmtAccount") == null)
                    return "redirect:userlogin.html";
        }*/


        return lang + "/" + a;
    }
}
