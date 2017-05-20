package com.convoypayments.domain;

import java.math.BigInteger;
import java.util.Date;

public class CpUser extends CpUserKey {
    public String password;

    public BigInteger cpMasterAccount;

    public BigInteger cpRmtAccount;

    public String mobileNum;

    public String bankAcctNumF6;

    public String namePinyin;

    public String loginToken;

    public String idProveUploaded;

    public Date idProveUploadTs;

    public Date idProveUpdateTs;

    public String idProveUploadPath;

    public String addrProveUploaded;

    public Date addrProveUploadTs;

    public Date addrProveUpdateTs;

    public String addrProveUploadPath;

}