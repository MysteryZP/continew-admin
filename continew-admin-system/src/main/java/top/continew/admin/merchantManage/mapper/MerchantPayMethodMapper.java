package top.continew.admin.merchantManage.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.continew.admin.merchantManage.model.entity.MerchantDO;
import top.continew.starter.data.mybatis.plus.base.BaseMapper;
import top.continew.admin.merchantManage.model.entity.MerchantPayMethodDO;

import java.util.List;

/**
* 商户支付方式配置 Mapper
*
* @author Jhon sam
* @since 2024/08/06 17:39
*/
public interface MerchantPayMethodMapper extends BaseMapper<MerchantPayMethodDO> {

    @Select("SELECT mpm.*" +
            "FROM sys_merchant_pay_method mpm " +
            "WHERE mpm.status = 1 " +
            "AND mpm.pay_method_code = #{payMethodCode} " +
            "AND mpm.mch_no = #{mchNo} " +
            "AND mpm.app_no = #{appNo} " )
    MerchantPayMethodDO getMerchantPayMethodByPrimaryInfo(@Param("mchNo") String mchNo,@Param("appNo") String appNo,@Param("payCode") String payCode,@Param("payMethodCode") String payMethodCode);
}