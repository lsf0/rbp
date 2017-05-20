package com.convoypayments.domain;

import java.math.BigInteger;
import java.util.Date;

public class CpRmtAccount extends CpRmtAccountKey {

    public String rcvNameSchi;//姓名(中文)

    public String rcvBankNamePinyin;

    public String rcvBankNameSchi;//银行名称(中文)

    public String rcvBankNameEng;//银行名称(英文)

    public String rcvBankCity;

    public String rcvBankProvince;

    public String rcvBranchNamePinyin;

    public String rcvBranchNameSchi;

    public String rcvBranchNameEng;//

    public String rcvBankAcctNo;//银行账户号码

    public BigInteger rcvBankAcctType1;

    public BigInteger rcvBankAcctType2;

    public String rcvBankAcctType;

    public String rcvBankAcctCategory;

    public String rcvEmailAddr;//电邮地址

    public String rcvIdType;//证件类型

    public String rcvIdNum;//证件号码

    public Date rcvDob;//出生日期

    public String rcvOccupation;//职业

    public String rcvAddrPinyin;

    public String rcvAddrSchi;//住址

    public String rcvRelationWithSender;

    public String kycUpdateAlertSent;

    public String kycUpdateToken;

    public String mandateKycVerified;

    public String cpRmtAcctStatus;

    public Date lastUpdateTs;

    public String loginToken;

    public String loginUsername;

    public String loginPassword;

    public Date loginLasttime;

   
}