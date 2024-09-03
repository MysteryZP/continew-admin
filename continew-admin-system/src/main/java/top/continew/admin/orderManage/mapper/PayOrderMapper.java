package top.continew.admin.orderManage.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.continew.starter.data.mybatis.plus.base.BaseMapper;
import top.continew.admin.orderManage.model.entity.PayOrderDO;

import java.util.List;

/**
* 支付订单 Mapper
*
* @author Jhon sam
* @since 2024/08/15 11:08
*/
public interface PayOrderMapper extends BaseMapper<PayOrderDO> {

    @Select("SELECT o.* FROM sys_pay_order o " +
            "WHERE o.mch_order_no = #{mchOrderNo}" +
            "AND o.mch_no = #{mchNo}")
    List<PayOrderDO> getOrdersByMerchantNoAndMerchantOrderNo(@Param("mchOrderNo") String mchOrderNo, @Param("mchNo") String mchNo);

    @Select("SELECT * FROM sys_pay_order " +
            "WHERE pay_order_no = #{payOrderNo}")
    PayOrderDO getOrdersByOrderNo(@Param("payOrderNo") String payOrderNo);
}