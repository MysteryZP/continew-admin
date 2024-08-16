package top.continew.admin.merchantManage.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.continew.starter.data.mybatis.plus.base.BaseMapper;
import top.continew.admin.merchantManage.model.entity.MerchantDO;

import java.util.List;
import java.util.Map;

/**
 * 商户管理 Mapper
 *
 * @author Jhon sam
 * @since 2024/07/30 16:14
 */
public interface MerchantMapper extends BaseMapper<MerchantDO> {

    @Select("SELECT mch_no,mch_name" +
            "FROM sys_merchant " +
            "WHERE status = 1 " +
            "ORDER BY create_time ASC")
    List<MerchantDO> getIDAndNameList();

    @Select("SELECT * FROM sys_merchant WHERE mch_no = #{mchNo}")
    MerchantDO getMerchantByMchNo(@Param("mchNo") String mchNo);
}