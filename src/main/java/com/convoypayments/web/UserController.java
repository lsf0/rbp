package com.convoypayments.web;

import com.convoypayments.domain.CpRmtAccount;
import com.convoypayments.domain.CpRmtTxn;
import com.convoypayments.domain.CpUser;
import com.convoypayments.mapper.CpRmtAccountMapper;
import com.convoypayments.mapper.CpRmtTxnMapper;
import com.convoypayments.mapper.CpUserMapper;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class UserController {

    @Resource
    private CpUserMapper cpUserMapper;

    @Resource
    private CpRmtTxnMapper cpRmtTxnMapper;

    @Resource
    private CpRmtAccountMapper cpRmtAccountMapper;

    @Resource
    private HttpSession session;

    @Value("${uploaddir}")
    private String uploaddir;

    @PostConstruct
    public void testInit() {
        CpRmtTxn cpRmtTxn = cpRmtTxnMapper.selectOne();
        cpRmtTxn.rcvBankAcctNo = cpRmtTxn.rcvBankAcctNoF6 = "666666";
        cpRmtTxn.rcvMobileNum = "123456789";

        //Beans.fill(cpRmtTxn);bug
        //cpRmtTxnMapper.insert(cpRmtTxn);//这条插入语句出错可能是mybatis或oracle的bug

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

        cpRmtAccount.loginToken = token;

        if ("cn".equals(lang) && cpRmtAccount.rcvNameSchi != null)
            cpRmtTxn.rcvNamePinyin = cpRmtAccount.rcvNameSchi;

        session.setAttribute("cpRmtTxn", cpRmtTxn);
        session.setAttribute("cpRmtAccount0", cpRmtAccount);

        return "redirect:createacct.html";
    }

    @PostMapping("{lang}/createacct.html")
    public String createacct(String uname, String pwd, CpUser cpUser, @PathVariable String lang) {

        if (cpUserMapper.usernameExisted(uname))
            return "redirect:createacct.html";//账户已存在

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
        cpUser.idProveUploaded = "N";
        cpUser.idProveUploadTs = new Date();
        cpUser.idProveUpdateTs = new Date();
        cpUser.idProveUploadPath = "";
        cpUser.addrProveUploaded = "";
        cpUser.addrProveUpdateTs = new Date();
        cpUser.addrProveUploadPath = "";
        cpUser.addrProveUploadTs = new Date();

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

        session.setAttribute("cpUser", cpUserMapper.getByName(usrname));
        session.setAttribute("cpRmtTxns", cpRmtTxns);
        session.setAttribute("cpRmtTxns4", cpRmtTxns.size() > 4 ? cpRmtTxns.subList(0, 4) : cpRmtTxns);


        return "redirect:acctinfo.html";
    }

    @Value("#{{'image/jpeg':'jpg','image/png':'png','image/gif':'gif'}}")
    private Map<String, String> fmtMap;

    @PostMapping("{lang}/upload")
    public String upload(MultipartFile file, String type, @PathVariable String lang) throws IOException {
        if (file.getSize() > 1 << 20)//文件不得超过1M
            return "redirect:acctinfo.html";

        CpUser cpUser = (CpUser) session.getAttribute("cpUser");

        String fmt = fmtMap.get(file.getContentType());
        Long ts = System.currentTimeMillis();
        String path = uploaddir + "/" + ts + "." + fmt;

        file.transferTo(new File(path));
        System.out.println(file.getOriginalFilename() + "上传完成");

        if ("card".equals(type)) {
            if ("Y".equals(cpUser.idProveUploaded)) {
                cpUser.idProveUpdateTs = new Date();
                File f = new File(cpUser.idProveUploadPath);
                if (f.exists()) f.delete();//把原来的删除
            } else {
                cpUser.idProveUploaded = "Y";
                cpUser.idProveUploadTs = cpUser.idProveUpdateTs = new Date();
                cpUser.idProveUploadPath = path;
            }
            cpUserMapper.updateIdByPrimaryKey(cpUser);
            return "redirect:acctinfo.html";
        }

        if ("Y".equals(cpUser.addrProveUploaded)) {
            cpUser.addrProveUpdateTs = new Date();
            File f = new File(cpUser.addrProveUploadPath);
            if (f.exists()) f.delete();//把原来的删除
        } else {
            cpUser.addrProveUploaded = "Y";
            cpUser.addrProveUploadTs = cpUser.addrProveUpdateTs = new Date();
            cpUser.addrProveUploadPath = path;
        }
        cpUserMapper.updateAddrByPrimaryKey(cpUser);
        return "redirect:acctinfo.html";
    }

    @ResponseBody
    @RequestMapping("{lang}/edit")
    public boolean edit(CpRmtAccount c, @PathVariable String lang) {
        CpRmtAccount cpRmtAccount = (CpRmtAccount) session.getAttribute("cpRmtAccount");
        c.cpMasterAcctNum = cpRmtAccount.cpMasterAcctNum;
        c.cpRmtAcctNum = cpRmtAccount.cpRmtAcctNum;
        c.rcvNamePinyin = cpRmtAccount.rcvNamePinyin;
        c.rcvBankAcctNoF6 = cpRmtAccount.rcvBankAcctNoF6;
        c.rcvMobileNum = cpRmtAccount.rcvMobileNum;
        cpRmtAccountMapper.updateByPrimaryKeySelective(c);
        session.setAttribute("cpRmtAccount",cpRmtAccountMapper.selectByPrimaryKey(c));
        return true;
    }


    @GetMapping("{lang}/{a}.html")
    public String map(@PathVariable String a, @PathVariable String lang) {
        //应该用过滤器或拦截器
        switch (a) {
            case "createacct":
                if (session.getAttribute("cpRmtAccount0") == null)
                    return "redirect:index.html";
                break;
            case "acctinfo":
            case "txhistory":
                if (session.getAttribute("cpRmtAccount") == null)
                    return "redirect:userlogin.html";
        }
        return lang + "/" + a;
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
}
