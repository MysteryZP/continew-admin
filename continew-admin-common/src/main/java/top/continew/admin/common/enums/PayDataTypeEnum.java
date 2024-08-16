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

/**
 * 支付数据包 类型
 *
 * @author Zhang Sam
 * @since 2023/2/26 21:35
 */
@Getter
@RequiredArgsConstructor
public enum PayDataTypeEnum {

    PAY_URL("payurl", "跳转链接的方式"),
    FORM("form", "表单提交"),
    WX_APP("wxapp", "微信app参数"),
    ALI_APP("aliapp", "支付宝app参数"),
    YSF_APP("ysfapp", "云闪付app参数"),
    CODE_URL("codeUrl", "二维码URL"),
    CODE_IMG_URL("codeImgUrl", "二维码图片显示URL"),
    NONE("none", "无参数"),
    ;

    private final String value;
    private final String description;
}
