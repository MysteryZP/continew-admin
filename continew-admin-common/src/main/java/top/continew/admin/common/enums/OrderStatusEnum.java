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
public enum OrderStatusEnum implements IBaseEnum<Integer> {

    /**
     * 订单生成
     */
    INIT(0, "订单生成", UiConstants.COLOR_DEFAULT),

    /**
     * 支付中
     */
    ING(1, "支付中", UiConstants.COLOR_WARNING),

    /**
     * 支付成功
     */
    SUCCESS(2, "支付成功", UiConstants.COLOR_SUCCESS),

    /**
     * 支付失败
     */
    FAIL(3, "支付失败", UiConstants.COLOR_ERROR),

    /**
     * 已撤销
     */
    CANCEL(4, "已撤销", UiConstants.COLOR_DEFAULT),

    /**
     * 已退款
     */
    REFUND(5, "已退款", UiConstants.COLOR_PRIMARY),

    /**
     * 订单关闭
     */
    CLOSED(6, "已退款", UiConstants.COLOR_PRIMARY),

    /**
     * 订单关闭
     */
    REFUND_NONE(0, "未发生实际退款", UiConstants.COLOR_ERROR),

    /**
     * 订单关闭
     */
    REFUND_SUB(1, "部分退款", UiConstants.COLOR_WARNING),

    /**
     * 订单关闭
     */
    REFUND_ALL(2, "全额退款", UiConstants.COLOR_PRIMARY),
    ;

    private final Integer value;
    private final String description;
    private final String color;
}
