package top.continew.admin.merchantManage.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.continew.admin.merchantManage.model.entity.MerchantDO;
import top.continew.starter.data.mybatis.plus.base.BaseMapper;
import top.continew.admin.merchantManage.model.entity.ApplicationDO;

/**
* 应用管理 Mapper
*
* @author Jhon sam
* @since 2024/08/01 15:36
*/
public interface ApplicationMapper extends BaseMapper<ApplicationDO> {

    @Select("SELECT * FROM sys_application WHERE app_no = #{appNo} AND status = 1")
    ApplicationDO getApplicationByAppNo(@Param("appNo") String appNo);
}