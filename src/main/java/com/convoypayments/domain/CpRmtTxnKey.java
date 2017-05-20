package com.convoypayments.domain;

import java.math.BigInteger;

public class CpRmtTxnKey {
    public BigInteger cpMasterAcctNum;

    public BigInteger cpRmtAcctNum;

    public String txnId;//交易号码

    public String rcvNamePinyin;//姓名（拼音）

    public String rcvBankAcctNoF6;//银行卡的首6位数字

    public String rcvMobileNum;//手机号码

  
}