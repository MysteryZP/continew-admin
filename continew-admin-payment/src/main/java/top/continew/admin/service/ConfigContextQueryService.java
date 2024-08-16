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

package top.continew.admin.service;

import top.continew.admin.model.resp.MchAppConfigContextResp;
import top.continew.admin.system.model.query.MessageQuery;
import top.continew.admin.system.model.req.MessageReq;
import top.continew.admin.system.model.resp.MessageResp;
import top.continew.starter.extension.crud.model.query.PageQuery;
import top.continew.starter.extension.crud.model.resp.PageResp;

import java.util.List;

/**
 * 基本配置信息查询服务
 *
 * @author Bull-BCLS
 * @since 2023/10/15 19:05
 */
public interface ConfigContextQueryService {

    /**
     * 分页查询列表
     *
     * @param mchNo     查询条件
     * @param mchAppId 分页查询条件
     * @return 分页列表信息
     */
    MchAppConfigContextResp queryMchInfoAndAppInfo(String mchNo, String appNo);

}