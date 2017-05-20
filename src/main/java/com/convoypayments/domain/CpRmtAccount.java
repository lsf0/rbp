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

    public void setRcvNameSchi(String rcvNameSchi) {
        this.rcvNameSchi = rcvNameSchi;
    }

    public void setRcvBankNamePinyin(String rcvBankNamePinyin) {
        this.rcvBankNamePinyin = rcvBankNamePinyin;
    }

    public void setRcvBankNameSchi(String rcvBankNameSchi) {
        this.rcvBankNameSchi = rcvBankNameSchi;
    }

    public void setRcvBankNameEng(String rcvBankNameEng) {
        this.rcvBankNameEng = rcvBankNameEng;
    }

    public void setRcvBankCity(String rcvBankCity) {
        this.rcvBankCity = rcvBankCity;
    }

    public void setRcvBankProvince(String rcvBankProvince) {
        this.rcvBankProvince = rcvBankProvince;
    }

    public void setRcvBranchNamePinyin(String rcvBranchNamePinyin) {
        this.rcvBranchNamePinyin = rcvBranchNamePinyin;
    }

    public void setRcvBranchNameSchi(String rcvBranchNameSchi) {
        this.rcvBranchNameSchi = rcvBranchNameSchi;
    }

    public void setRcvBranchNameEng(String rcvBranchNameEng) {
        this.rcvBranchNameEng = rcvBranchNameEng;
    }

    public void setRcvBankAcctNo(String rcvBankAcctNo) {
        this.rcvBankAcctNo = rcvBankAcctNo;
    }

    public void setRcvBankAcctType1(BigInteger rcvBankAcctType1) {
        this.rcvBankAcctType1 = rcvBankAcctType1;
    }

    public void setRcvBankAcctType2(BigInteger rcvBankAcctType2) {
        this.rcvBankAcctType2 = rcvBankAcctType2;
    }

    public void setRcvBankAcctType(String rcvBankAcctType) {
        this.rcvBankAcctType = rcvBankAcctType;
    }

    public void setRcvBankAcctCategory(String rcvBankAcctCategory) {
        this.rcvBankAcctCategory = rcvBankAcctCategory;
    }

    public void setRcvEmailAddr(String rcvEmailAddr) {
        this.rcvEmailAddr = rcvEmailAddr;
    }

    public void setRcvIdType(String rcvIdType) {
        this.rcvIdType = rcvIdType;
    }

    public void setRcvIdNum(String rcvIdNum) {
        this.rcvIdNum = rcvIdNum;
    }

    public void setRcvDob(Date rcvDob) {
        this.rcvDob = rcvDob;
    }

    public void setRcvOccupation(String rcvOccupation) {
        this.rcvOccupation = rcvOccupation;
    }

    public void setRcvAddrPinyin(String rcvAddrPinyin) {
        this.rcvAddrPinyin = rcvAddrPinyin;
    }

    public void setRcvAddrSchi(String rcvAddrSchi) {
        this.rcvAddrSchi = rcvAddrSchi;
    }

    public void setRcvRelationWithSender(String rcvRelationWithSender) {
        this.rcvRelationWithSender = rcvRelationWithSender;
    }

    public void setKycUpdateAlertSent(String kycUpdateAlertSent) {
        this.kycUpdateAlertSent = kycUpdateAlertSent;
    }

    public void setKycUpdateToken(String kycUpdateToken) {
        this.kycUpdateToken = kycUpdateToken;
    }

    public void setMandateKycVerified(String mandateKycVerified) {
        this.mandateKycVerified = mandateKycVerified;
    }

    public void setCpRmtAcctStatus(String cpRmtAcctStatus) {
        this.cpRmtAcctStatus = cpRmtAcctStatus;
    }

    public void setLastUpdateTs(Date lastUpdateTs) {
        this.lastUpdateTs = lastUpdateTs;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public void setLoginLasttime(Date loginLasttime) {
        this.loginLasttime = loginLasttime;
    }
}