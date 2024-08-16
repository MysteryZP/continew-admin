package top.continew.admin.merchantManage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;

import net.dreamlu.mica.core.utils.BeanUtil;
import org.springframework.stereotype.Service;

import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.continew.admin.merchantManage.mapper.MerchantPayMethodMapper;
import top.continew.admin.merchantManage.model.entity.MerchantPayMethodDO;
import top.continew.admin.merchantManage.model.query.MerchantPayMethodQuery;
import top.continew.admin.merchantManage.model.req.MerchantPayMethodReq;
import top.continew.admin.merchantManage.model.resp.MerchantPayMethodDetailResp;
import top.continew.admin.merchantManage.model.resp.MerchantPayMethodResp;
import top.continew.admin.merchantManage.service.MerchantPayMethodService;

/**
 * 商户支付方式配置业务实现
 *
 * @author Jhon sam
 * @since 2024/08/06 17:39
 */
@Service
@RequiredArgsConstructor
public class MerchantPayMethodServiceImpl extends BaseServiceImpl<MerchantPayMethodMapper, MerchantPayMethodDO, MerchantPayMethodResp, MerchantPayMethodDetailResp, MerchantPayMethodQuery, MerchantPayMethodReq> implements MerchantPayMethodService {

    @Override
    public MerchantPayMethodDetailResp getMerchantInfo(MerchantPayMethodQuery query) {
        QueryWrapper<MerchantPayMethodDO> queryWrapper = this.buildQueryWrapper(query);
        MerchantPayMethodDO merchantPayMethodDO = baseMapper.selectOne(queryWrapper);
        return BeanUtil.copyProperties(merchantPayMethodDO, MerchantPayMethodDetailResp.class);
    }

    @Override
    public MerchantPayMethodDO getMerchanPayMethodByPrimaryInfo(String mchNo, String appNo, String payCode, String payMethodCode) {
        return baseMapper.getMerchantPayMethodByPrimaryInfo(mchNo,appNo,payCode,payMethodCode);
    }
}