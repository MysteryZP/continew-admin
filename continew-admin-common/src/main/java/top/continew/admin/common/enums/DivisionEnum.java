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

package top.continew.admin.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.admin.common.constant.UiConstants;
import top.continew.starter.data.mybatis.plus.base.IBaseEnum;

/**
 * 订单状态枚举
 *
 * @author Charles7c
 * @since 2023/2/26 21:35
 */
@Getter
@RequiredArgsConstructor
public enum DivisionEnum implements IBaseEnum<Integer> {

    /**
     * 该笔订单不允许分账
     */
    DIVISION_MODE_FORBID(0, "该笔订单不允许分账", UiConstants.COLOR_ERROR),

    /**
     * 支付成功按配置自动完成分账
     */
    DIVISION_MODE_AUTO(1, "支付成功按配置自动完成分账", UiConstants.COLOR_PRIMARY),

    /**
     * 商户手动分账(解冻商户金额)
     */
    DIVISION_MODE_MANUAL(2, "支付成功", UiConstants.COLOR_WARNING),

    /**
     * 未发生分账
     */
    DIVISION_STATE_UNHAPPEN(0, "未发生分账", UiConstants.COLOR_DEFAULT),

    /**
     * 等待分账任务处理
     */
    DIVISION_STATE_WAIT_TASK(1, "等待分账任务处理", UiConstants.COLOR_WARNING),

    /**
     * 分账处理中
     */
    DIVISION_STATE_ING(2, "分账处理中", UiConstants.COLOR_PRIMARY),

    /**
     * 分账任务已结束(不体现状态)
     */
    DIVISION_STATE_FINISH(3, "分账任务已结束(不体现状态)", UiConstants.COLOR_SUCCESS),
    ;

    private final Integer value;
    private final String description;
    private final String color;
}
