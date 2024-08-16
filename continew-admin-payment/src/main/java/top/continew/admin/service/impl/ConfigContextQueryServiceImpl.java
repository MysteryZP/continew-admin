/*
 * Copyright (c) 2022-present Charles7c Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.continew.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.common.constant.CacheConstants;
import top.continew.admin.common.enums.DisEnableStatusEnum;
import top.continew.admin.common.enums.MenuTypeEnum;
import top.continew.admin.merchantManage.model.entity.ApplicationDO;
import top.continew.admin.merchantManage.model.entity.MerchantDO;
import top.continew.admin.merchantManage.service.ApplicationService;
import top.continew.admin.merchantManage.service.MerchantService;
import top.continew.admin.model.resp.MchAppConfigContextResp;
import top.continew.admin.service.ConfigContextQueryService;
import top.continew.admin.system.mapper.MenuMapper;
import top.continew.admin.system.model.entity.MenuDO;
import top.continew.admin.system.model.query.MenuQuery;
import top.continew.admin.system.model.req.MenuReq;
import top.continew.admin.system.model.resp.MenuResp;
import top.continew.admin.system.service.MenuService;
import top.continew.starter.core.util.validate.CheckUtils;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;

import java.util.List;
import java.util.Set;

/**
 * 菜单业务实现
 *
 * @author Charles7c
 * @since 2023/2/15 20:30
 */
@Service
@RequiredArgsConstructor
public class ConfigContextQueryServiceImpl implements ConfigContextQueryService {
    private final MerchantService merchantService;
    private final ApplicationService applicationService;

    @Override
    public MchAppConfigContextResp queryMchInfoAndAppInfo(String mchNo, String appNo) {

        //todo:先在缓存查询相关信息，如果缓存没有查询到再查询数据库，此处后续再增加

        MerchantDO merchantDO = merchantService.getMerchantByMchNo(mchNo);
        ApplicationDO applicationDO = applicationService.getApplicationByAppNo(appNo);
        if(merchantDO == null || applicationDO == null){
            return null;
        }

        MchAppConfigContextResp result = new MchAppConfigContextResp();
        result.setMchInfo(merchantDO);
        result.setMchNo(mchNo);
        result.setMchType(merchantDO.getType());

        result.setMchApp(applicationDO);
        result.setAppNo(applicationDO.getAppNo());
        return result;
    }
}
