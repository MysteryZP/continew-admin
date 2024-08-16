package top.continew.admin.payConfig.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.continew.admin.payConfig.mapper.PayMethodMapper;
import top.continew.admin.payConfig.model.entity.PayMethodDO;
import top.continew.admin.payConfig.model.query.PayMethodQuery;
import top.continew.admin.payConfig.model.req.PayMethodReq;
import top.continew.admin.payConfig.model.resp.PayMethodDetailResp;
import top.continew.admin.payConfig.model.resp.PayMethodResp;
import top.continew.admin.payConfig.service.PayMethodService;

/**
 * 支付方式配置业务实现
 *
 * @author Jhon sam
 * @since 2024/08/02 10:46
 */
@Service
@RequiredArgsConstructor
public class PayMethodServiceImpl extends BaseServiceImpl<PayMethodMapper, PayMethodDO, PayMethodResp, PayMethodDetailResp, PayMethodQuery, PayMethodReq> implements PayMethodService {}