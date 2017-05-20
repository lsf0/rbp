package com.convoypayments.domain;

import java.math.BigDecimal;
import java.util.Date;

public class CpRmtTxn extends CpRmtTxnKey {
    public String importBatchNum;

    public String importFileName;

    public Date importTs;

    public String txnUpstreamTxnId;

    public Date txnDate;//日期

    public Date txnTime;

    public Date txnTimestamp;

    public String txnToken;//登入密键

    public String txnStatus;//状况

    public String senderName;//汇款人姓名

    public String senderMobileNum;

    public String senderCountryCode;//国家

    public String destCountryCode;

    public String rcvNameSchi;

    public String rcvBankNameEng;

    public String rcvBankNameSchi;

    public String rcvBranchNamePinyin;

    public String rcvBranchNameSchi;

    public String rcvBankAcctNo;//银行账号

    public BigDecimal rcvBankAcctType1;

    public BigDecimal rcvBankAcctType2;

    public String rcvBankAcctType;

    public String rcvBankAcctCategory;

    public String rcvBankProvince;

    public String rcvBankCity;

    public String rcvEmailAddr;

    public String rcvIdType;

    public String rcvIdNum;

    public Date rcvDob;

    public String rcvOccupation;

    public String rcvAddrPinyin;

    public String rcvAddrSchi;

    public String rcvRelationWithSender;

    public BigDecimal txnAmount;//金额

    public String txnCurrency;//货币

    public BigDecimal txnFxRate;

    public String txnPayoutCurrency;

    public BigDecimal txnPayoutAmount;

    public String txnPurpose;

    public String txnStatusCode;

    public Date txnStatusUpdateDts;

    public BigDecimal txnFee;

    public String txnFeeCurrency;

    public String txnSettled;

    public Date txnSettledTs;

    public String upstreamPartnerCode;

    public String downstreamPartnerCode;

    public BigDecimal alertLevelSent;

    public Date alertLastSentTs;

    public String t0AlertSent;

    public String t2AlertSent;

    public String t4AlertSent;

    public String txnSettleResultCode;

    public String txnSettleResultDesc;

    public String txnRemark;//备注

}