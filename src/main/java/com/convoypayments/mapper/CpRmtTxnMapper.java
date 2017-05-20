package com.convoypayments.mapper;

import com.convoypayments.domain.CpRmtAccount;
import com.convoypayments.domain.CpRmtAccountKey;
import com.convoypayments.domain.CpRmtTxn;
import com.convoypayments.domain.CpRmtTxnKey;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CpRmtTxnMapper {

    @Select({
            "SELECT * FROM CP_RMT_TXN",
            "WHERE CP_MASTER_ACCT_NUM=#{cpMasterAcctNum}",
            "AND CP_RMT_ACCT_NUM=#{cpRmtAcctNum}",
            "AND RCV_NAME_PINYIN=#{rcvNamePinyin}",
            "AND RCV_BANK_ACCT_NO_F6=#{rcvBankAcctNoF6}",
            "AND RCV_MOBILE_NUM=#{rcvMobileNum}"
    })
    List<CpRmtTxn> getByUser(CpRmtTxnKey cpRmtTxnKey);

    @Select("SELECT * FROM CP_RMT_ACCOUNT CRA JOIN (SELECT * FROM CP_RMT_TXN WHERE RCV_NAME_PINYIN=#{rcvNamePinyin} AND" +
            "(RCV_BANK_ACCT_NO=#{rcvBankAcctNo} OR RCV_BANK_ACCT_NO_F6=#{rcvBankAcctNoF6}) AND " +
            "RCV_MOBILE_NUM=#{rcvMobileNum} AND TXN_TOKEN=#{txnToken}) CRT USING (CP_MASTER_ACCT_NUM,CP_RMT_ACCT_NUM)")
    CpRmtAccount keyLogin(CpRmtTxn cpRmtTxn);


    @Select({
            "SELECT * FROM (SELECT * FROM CP_RMT_ACCOUNT",
            "WHERE CP_MASTER_ACCT_NUM = #{cpMasterAcctNum}",
            "AND CP_RMT_ACCT_NUM = #{cpRmtAcctNum}",
            "AND RCV_NAME_PINYIN = #{rcvNamePinyin}",
            "AND RCV_BANK_ACCT_NO_F6 = #{rcvBankAcctNoF6}",
            "AND RCV_MOBILE_NUM = #{rcvMobileNum}",
            ") CRA JOIN CP_RMT_TXN USING (CP_MASTER_ACCT_NUM,CP_RMT_ACCT_NUM)"
    })
    List<CpRmtTxn> getByAccount(CpRmtAccountKey cpRmtAccountKey);


    @Insert({
            "insert into CP_RMT_TXN ",
            "values (#{cpMasterAcctNum,jdbcType=DECIMAL}, #{cpRmtAcctNum,jdbcType=DECIMAL}, ",
            "#{txnId,jdbcType=VARCHAR}, #{rcvNamePinyin,jdbcType=VARCHAR}, ",
            "#{rcvBankAcctNoF6,jdbcType=CHAR}, #{rcvMobileNum,jdbcType=VARCHAR}, ",
            "#{importBatchNum,jdbcType=VARCHAR}, #{importFileName,jdbcType=VARCHAR}, ",
            "#{importTs,jdbcType=TIMESTAMP}, #{txnUpstreamTxnId,jdbcType=VARCHAR}, ",
            "#{txnDate,jdbcType=DATE}, #{txnTime,jdbcType=DATE}, ",
            "#{txnTimestamp,jdbcType=TIMESTAMP}, #{txnToken,jdbcType=VARCHAR}, ",
            "#{txnStatus,jdbcType=VARCHAR}, #{senderName,jdbcType=VARCHAR}, ",
            "#{senderMobileNum,jdbcType=VARCHAR}, #{senderCountryCode,jdbcType=CHAR}, ",
            "#{destCountryCode,jdbcType=CHAR}, #{rcvNameSchi,jdbcType=NVARCHAR}, ",
            "#{rcvBankNameEng,jdbcType=VARCHAR}, #{rcvBankNameSchi,jdbcType=NVARCHAR}, ",
            "#{rcvBranchNamePinyin,jdbcType=VARCHAR}, #{rcvBranchNameSchi,jdbcType=NVARCHAR}, ",
            "#{rcvBankAcctNo,jdbcType=VARCHAR}, #{rcvBankAcctType1,jdbcType=DECIMAL}, ",
            "#{rcvBankAcctType2,jdbcType=DECIMAL}, #{rcvBankAcctType,jdbcType=VARCHAR}, ",
            "#{rcvBankAcctCategory,jdbcType=VARCHAR}, #{rcvBankProvince,jdbcType=VARCHAR}, ",
            "#{rcvBankCity,jdbcType=VARCHAR}, #{rcvEmailAddr,jdbcType=VARCHAR}, ",
            "#{rcvIdType,jdbcType=VARCHAR}, #{rcvIdNum,jdbcType=VARCHAR}, ",
            "#{rcvDob,jdbcType=DATE}, #{rcvOccupation,jdbcType=VARCHAR}, ",
            "#{rcvAddrPinyin,jdbcType=VARCHAR}, #{rcvAddrSchi,jdbcType=NVARCHAR}, ",
            "#{rcvRelationWithSender,jdbcType=VARCHAR}, #{txnAmount,jdbcType=DECIMAL}, ",
            "#{txnCurrency,jdbcType=VARCHAR}, #{txnFxRate,jdbcType=DECIMAL}, ",
            "#{txnPayoutCurrency,jdbcType=VARCHAR}, #{txnPayoutAmount,jdbcType=DECIMAL}, ",
            "#{txnPurpose,jdbcType=VARCHAR}, #{txnStatusCode,jdbcType=VARCHAR}, ",
            "#{txnStatusUpdateDts,jdbcType=TIMESTAMP}, #{txnFee,jdbcType=DECIMAL}, ",
            "#{txnFeeCurrency,jdbcType=VARCHAR}, #{txnSettled,jdbcType=CHAR}, ",
            "#{txnSettledTs,jdbcType=TIMESTAMP}, #{upstreamPartnerCode,jdbcType=CHAR}, ",
            "#{downstreamPartnerCode,jdbcType=CHAR}, #{alertLevelSent,jdbcType=DECIMAL}, ",
            "#{alertLastSentTs,jdbcType=TIMESTAMP}, #{t0AlertSent,jdbcType=CHAR}, ",
            "#{t2AlertSent,jdbcType=CHAR}, #{t4AlertSent,jdbcType=CHAR}, ",
            "#{txnSettleResultCode,jdbcType=VARCHAR}, #{txnSettleResultDesc,jdbcType=VARCHAR}, ",
            "#{txnRemark,jdbcType=VARCHAR})"
    })
    int insert(CpRmtTxn cpRmtTxn);

    @Select("SELECT * FROM CP_RMT_TXN WHERE rownum=1")
    CpRmtTxn selectOne();


}