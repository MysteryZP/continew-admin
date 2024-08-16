package top.continew.admin.merchantManage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.common.enums.DisEnableStatusEnum;
import top.continew.admin.common.enums.GenderEnum;
import top.continew.admin.common.util.SecureUtils;
import top.continew.admin.merchantManage.model.entity.MerchantDO;
import top.continew.admin.merchantManage.model.req.MerchantReq;
import top.continew.admin.system.model.entity.RoleDO;
import top.continew.admin.system.model.req.DeptReq;
import top.continew.admin.system.model.req.UserReq;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.continew.admin.merchantManage.mapper.ApplicationMapper;
import top.continew.admin.merchantManage.model.entity.ApplicationDO;
import top.continew.admin.merchantManage.model.query.ApplicationQuery;
import top.continew.admin.merchantManage.model.req.ApplicationReq;
import top.continew.admin.merchantManage.model.resp.ApplicationDetailResp;
import top.continew.admin.merchantManage.model.resp.ApplicationResp;
import top.continew.admin.merchantManage.service.ApplicationService;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用管理业务实现
 *
 * @author Jhon sam
 * @since 2024/08/01 15:36
 */
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl extends BaseServiceImpl<ApplicationMapper, ApplicationDO, ApplicationResp, ApplicationDetailResp, ApplicationQuery, ApplicationReq> implements ApplicationService {

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Long add(ApplicationReq req) {
        ApplicationDO applicationDO = BeanUtil.toBean(req,ApplicationDO.class);
        applicationDO.setAppNo("APP" + DateUtil.currentSeconds());
        this.baseMapper.insert(applicationDO);
        this.afterAdd(req, applicationDO);
        return applicationDO.getId();
    }

    @Override
    public ApplicationDO getApplicationByAppNo(String appNo) {
        return baseMapper.getApplicationByAppNo(appNo);
    }
}