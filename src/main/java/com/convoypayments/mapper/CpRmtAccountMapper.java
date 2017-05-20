package com.convoypayments.mapper;


import com.convoypayments.domain.CpRmtAccount;
import com.convoypayments.domain.CpRmtAccountKey;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

public interface CpRmtAccountMapper {

    class SqlProvider{
        public String updateByPrimaryKeySelective(CpRmtAccount record) {
            SQL sql = new SQL();
            sql.UPDATE("CP_RMT_ACCOUNT");

            if (record.rcvNameSchi != null)
                sql.SET("RCV_NAME_SCHI = #{rcvNameSchi}");

            if (record.rcvBankNamePinyin != null)
                sql.SET("RCV_BANK_NAME_PINYIN = #{rcvBankNamePinyin}");

            if (record.rcvBankNameSchi != null)
                sql.SET("RCV_BANK_NAME_SCHI = #{rcvBankNameSchi}");

            if (record.rcvBankNameEng != null)
                sql.SET("RCV_BANK_NAME_ENG = #{rcvBankNameEng}");

            if (record.rcvBankCity != null)
                sql.SET("RCV_BANK_CITY = #{rcvBankCity}");

            if (record.rcvBankProvince != null)
                sql.SET("RCV_BANK_PROVINCE = #{rcvBankProvince}");

            if (record.rcvBranchNamePinyin != null)
                sql.SET("RCV_BRANCH_NAME_PINYIN = #{rcvBranchNamePinyin}");

            if (record.rcvBranchNameSchi != null)
                sql.SET("RCV_BRANCH_NAME_SCHI = #{rcvBranchNameSchi}");

            if (record.rcvBranchNameEng != null)
                sql.SET("RCV_BRANCH_NAME_ENG = #{rcvBranchNameEng}");

            if (record.rcvBankAcctNo != null)
                sql.SET("RCV_BANK_ACCT_NO = #{rcvBankAcctNo}");

            if (record.rcvBankAcctType1 != null)
                sql.SET("RCV_BANK_ACCT_TYPE1 = #{rcvBankAcctType1}");

            if (record.rcvBankAcctType2 != null)
                sql.SET("RCV_BANK_ACCT_TYPE2 = #{rcvBankAcctType2}");

            if (record.rcvBankAcctType != null)
                sql.SET("RCV_BANK_ACCT_TYPE = #{rcvBankAcctType}");

            if (record.rcvBankAcctCategory != null)
                sql.SET("RCV_BANK_ACCT_CATEGORY = #{rcvBankAcctCategory}");

            if (record.rcvEmailAddr != null)
                sql.SET("RCV_EMAIL_ADDR = #{rcvEmailAddr}");

            if (record.rcvIdType != null)
                sql.SET("RCV_ID_TYPE = #{rcvIdType}");

            if (record.rcvIdNum != null)
                sql.SET("RCV_ID_NUM = #{rcvIdNum}");

            if (record.rcvDob != null)
                sql.SET("RCV_DOB = #{rcvDob}");

            if (record.rcvOccupation != null)
                sql.SET("RCV_OCCUPATION = #{rcvOccupation}");

            if (record.rcvAddrPinyin != null)
                sql.SET("RCV_ADDR_PINYIN = #{rcvAddrPinyin}");

            if (record.rcvAddrSchi != null)
                sql.SET("RCV_ADDR_SCHI = #{rcvAddrSchi}");

            if (record.rcvRelationWithSender != null)
                sql.SET("RCV_RELATION_WITH_SENDER = #{rcvRelationWithSender}");

            if (record.kycUpdateAlertSent != null)
                sql.SET("KYC_UPDATE_ALERT_SENT = #{kycUpdateAlertSent}");

            if (record.kycUpdateToken != null)
                sql.SET("KYC_UPDATE_TOKEN = #{kycUpdateToken}");

            if (record.mandateKycVerified != null)
                sql.SET("MANDATE_KYC_VERIFIED = #{mandateKycVerified}");

            if (record.cpRmtAcctStatus != null)
                sql.SET("CP_RMT_ACCT_STATUS = #{cpRmtAcctStatus}");

            if (record.lastUpdateTs != null)
                sql.SET("LAST_UPDATE_TS = #{lastUpdateTs}");

            if (record.loginToken != null)
                sql.SET("LOGIN_TOKEN = #{loginToken}");

            if (record.loginUsername != null)
                sql.SET("LOGIN_USERNAME = #{loginUsername}");

            if (record.loginPassword != null)
                sql.SET("LOGIN_PASSWORD = #{loginPassword}");

            if (record.loginLasttime != null)
                sql.SET("LOGIN_LASTTIME = #{loginLasttime}");

            sql.WHERE("CP_MASTER_ACCT_NUM = #{cpMasterAcctNum}");
            sql.WHERE("CP_RMT_ACCT_NUM = #{cpRmtAcctNum}");
            sql.WHERE("RCV_NAME_PINYIN = #{rcvNamePinyin}");
            sql.WHERE("RCV_BANK_ACCT_NO_F6 = #{rcvBankAcctNoF6}");
            sql.WHERE("RCV_MOBILE_NUM = #{rcvMobileNum}");

            return sql.toString();
        }
    }

    @UpdateProvider(type=SqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CpRmtAccount record);

    @Select({
            "SELECT * FROM CP_RMT_ACCOUNT",
            "where CP_MASTER_ACCT_NUM = #{cpMasterAcctNum}",
            "and CP_RMT_ACCT_NUM = #{cpRmtAcctNum}",
            "and RCV_NAME_PINYIN = #{rcvNamePinyin}",
            "and RCV_BANK_ACCT_NO_F6 = #{rcvBankAcctNoF6}",
            "and RCV_MOBILE_NUM = #{rcvMobileNum}"
    })
    CpRmtAccount selectByPrimaryKey(CpRmtAccountKey key);

}