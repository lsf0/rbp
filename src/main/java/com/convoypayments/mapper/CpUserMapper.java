package com.convoypayments.mapper;

import com.convoypayments.domain.CpRmtAccount;
import com.convoypayments.domain.CpUser;
import com.convoypayments.domain.CpUserKey;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface CpUserMapper {

    @Insert({
            "INSERT INTO CP_USER VALUES(#{id},#{username},#{password},#{cpMasterAccount},",
            "#{cpRmtAccount},#{mobileNum},#{bankAcctNumF6},#{namePinyin},#{loginToken},#{idProveUploaded},",
            "#{idProveUploadTs},#{idProveUpdateTs},#{idProveUploadPath},#{addrProveUploaded},",
            "#{addrProveUploadTs},#{addrProveUpdateTs},#{addrProveUploadPath})"
    })
    @SelectKey(statement = "SELECT SEQ_CP_USER_ID.NEXTVAL FROM DUAL",keyProperty = "id",resultType = BigInteger.class,before = true)
    int insert(CpUser cpUser);


    @Insert("INSERT INTO CP_USER_ROLE VALUES(#{userId},'member')")
    int insertUserRoleMember(BigInteger userId);

    @Select("SELECT DISTINCT CRA.* FROM CP_RMT_ACCOUNT CRA JOIN CP_USER CU " +
            "ON USERNAME=#{name} AND PASSWORD=#{pwd}" +
            "AND CRA.CP_MASTER_ACCT_NUM=CU.CP_MASTER_ACCOUNT " +
            "AND CRA.CP_RMT_ACCT_NUM=CU.CP_RMT_ACCOUNT")
    CpRmtAccount userLogin(@Param("name") String name,@Param("pwd") String pwd);

    @Select("SELECT count(1) FROM CP_USER WHERE USERNAME=#{name}")
    boolean usernameExisted(String name);

    @Select("SELECT * FROM CP_USER WHERE USERNAME=#{name}")
    CpUser getByName(String name);


    @Update({
            "UPDATE CP_USER SET ",
            "ID_PROVE_UPLOADED=#{idProveUploaded},",
            "ID_PROVE_UPLOAD_TS=#{idProveUploadTs},",
            "ID_PROVE_UPDATE_TS=#{idProveUpdateTs},",
            "ID_PROVE_UPLOAD_PATH=#{idProveUploadPath}",
            "WHERE ID=#{id} AND USERNAME=#{username}"
    })
    int updateIdByPrimaryKey(CpUser record);

    @Update({
            "UPDATE CP_USER SET ",
            "ADDR_PROVE_UPLOADED=#{addrProveUploaded},",
            "ADDR_PROVE_UPLOAD_TS=#{addrProveUploadTs},",
            "ADDR_PROVE_UPDATE_TS=#{addrProveUpdateTs},",
            "ADDR_PROVE_UPLOAD_PATH=#{addrProveUploadPath}",
            "WHERE ID=#{id} AND USERNAME=#{username}"
    })
    int updateAddrByPrimaryKey(CpUser record);

}