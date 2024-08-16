-- liquibase formatted sql

-- changeset Zhangsam:1
-- comment 业务表
CREATE TABLE IF NOT EXISTS `sys_merchant` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `mch_no` VARCHAR(50) NOT NULL COMMENT '商户编号',
    `contact_name` VARCHAR(45) NOT NULL COMMENT '联系人姓名',
    `mch_name` VARCHAR(255) NOT NULL COMMENT '商户名称',
    `type` TINYINT NULL DEFAULT 1 COMMENT '类型',
    `contact_tel` VARCHAR(20) NOT NULL COMMENT '联系电话',
    `mch_short_name` VARCHAR(50) NULL COMMENT '商户简称',
    `init_user_id` BIGINT NOT NULL COMMENT '初始用户ID',
    `status` TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态 （1：启用；2：禁用；3：锁定）',
    `create_user` BIGINT NULL,
    `create_time` DATETIME NULL,
    `update_user` BIGINT NULL DEFAULT NULL,
    `update_time` DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COMMENT = '商户表';

-- changeset Zhangsam:2 runOnChange:true
--  comment 应用管理表，
CREATE TABLE IF NOT EXISTS `sys_application` (
   `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
   `app_no` VARCHAR(50) NOT NULL COMMENT '应用编号',
   `app_name` VARCHAR(255) NOT NULL COMMENT '应用名称',
   `app_secret` VARCHAR(45) NULL DEFAULT NULL COMMENT 'APP秘钥',
   `mch_no` VARCHAR(50) NOT NULL COMMENT '商户号',
   `status` TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态（1：启用；2：禁用）',
   `create_user` BIGINT NOT NULL COMMENT '创建用户ID',
   `create_time` DATETIME NOT NULL COMMENT '创建时间',
   `update_user` BIGINT NULL DEFAULT NULL COMMENT '更新用户ID',
   `update_time` DATETIME NULL COMMENT '更新时间',
   PRIMARY KEY (`id`),
   UNIQUE INDEX `uk_app_no` USING BTREE (`app_no`) VISIBLE,
   INDEX `idx_app_no` USING BTREE (`app_no`) VISIBLE,
   INDEX `idx_mch_no` USING BTREE (`mch_no`) VISIBLE,
   INDEX `idx_create_user` USING BTREE (`create_user`) VISIBLE,
   INDEX `idx_update_user` USING BTREE (`update_user`) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COMMENT = '应用管理表';

-- changeset Zhangsam:3 runOnChange:true
--  comment 支付方式配置表，
CREATE TABLE IF NOT EXISTS `sys_pay_method` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` VARCHAR(50) NOT NULL COMMENT '编码',
  `pay_code` VARCHAR(50) NOT NULL DEFAULT 'default' COMMENT '支付代码',
  `name` VARCHAR(100) NOT NULL COMMENT '名称',
  `status` TINYINT ZEROFILL NOT NULL DEFAULT 1 COMMENT '状态（1-启用，2-停用）',
  `create_user` BIGINT NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_user` BIGINT NULL DEFAULT NULL COMMENT '更新人',
  `update_time` DATETIME NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX `idx_code` USING BTREE (`code`) VISIBLE,
  UNIQUE INDEX `uk_code` USING BTREE (`code`) VISIBLE,
  INDEX `idx_name` USING BTREE (`name`) VISIBLE,
  INDEX `idx_pay_code` USING BTREE (`pay_code`) VISIBLE,
  INDEX `idx_create_user` USING BTREE (`create_user`) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COMMENT = '支付方式配置表';

-- changeset Zhangsam:4 runOnChange:true
--  comment 商户支付方式配置表，
CREATE TABLE IF NOT EXISTS `sys_merchant_pay_method` (
   `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
   `mch_no` VARCHAR(50) NOT NULL COMMENT '商户号',
   `app_no` VARCHAR(50) NOT NULL COMMENT '应用号',
   `pay_code` VARCHAR(50) NOT NULL COMMENT '支付代码',
   `pay_method_code` VARCHAR(50) NOT NULL COMMENT '支付方式代码',
   `fee` DECIMAL(5,2) NOT NULL DEFAULT 0.0 COMMENT '费率',
   `status` TINYINT UNSIGNED NOT NULL COMMENT '状态（1-启用，2-停用）',
   `create_user` BIGINT NOT NULL COMMENT '创建用户',
   `create_time` DATETIME NOT NULL COMMENT '创建时间',
   `update_user` BIGINT NULL DEFAULT NULL COMMENT '更新用户',
   `update_time` DATETIME NULL DEFAULT NULL COMMENT '更新时间',
   PRIMARY KEY (`id`),
   INDEX `idx_pay_code` (`pay_code` ASC) VISIBLE,
   INDEX `idx_merchant_no` (`mch_no` ASC) VISIBLE,
   INDEX `idx_application_no` (`app_no` ASC) VISIBLE,
   INDEX `idx_pay_method_code` (`app_no` ASC) VISIBLE,
   UNIQUE INDEX `uk_merchant_code_method` (`mch_no` ASC,`app_no` ASC, `pay_code` ASC, `pay_method_code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COMMENT = '商户支付方式配置表';

-- changeset Zhangsam:6 runOnChange:true
--  comment 支付订单表
CREATE TABLE IF NOT EXISTS `sys_pay_order` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `pay_order_no` VARCHAR(64) NOT NULL COMMENT '支付订单号',
    `mch_no` VARCHAR(64) NOT NULL COMMENT '商户号',
    `isv_no` VARCHAR(64) DEFAULT NULL COMMENT '服务商号',
    `app_no` VARCHAR(64) NOT NULL COMMENT '应用ID',
    `mch_name` VARCHAR(30) NOT NULL COMMENT '商户名称',
    `mch_type` TINYINT NOT NULL COMMENT '类型: 1-普通商户, 2-特约商户(服务商模式)',
    `mch_order_no` VARCHAR(64) NOT NULL COMMENT '商户订单号',
    `trace_no` VARCHAR(64) COMMENT '流水号',
    `pay_code` VARCHAR(20) COMMENT '支付接口代码',
    `pay_method_code` VARCHAR(20) NOT NULL COMMENT '支付方式代码',
    `amount` BIGINT NOT NULL COMMENT '订单金额,单位分',
    `pay_amount` INT COMMENT '支付金额，单位（分）',
    `mch_fee_rate` DECIMAL(20,6) NOT NULL COMMENT '商户手续费费率快照',
    `mch_fee_amount` BIGINT NOT NULL COMMENT '商户手续费,单位分',
    `currency` VARCHAR(3) NOT NULL DEFAULT 'CNY' COMMENT '三位货币代码,人民币:CNY',
    `status` TINYINT NOT NULL DEFAULT 0 COMMENT '支付状态: 0-订单生成, 1-支付中, 2-支付成功, 3-支付失败, 4-已撤销, 5-已退款, 6-订单关闭',
    `notify_status` TINYINT NOT NULL DEFAULT 0 COMMENT '向下游回调状态, 0-未发送,  1-已发送',
    `client_ip` VARCHAR(32) DEFAULT NULL COMMENT '客户端IP',
    `subject` VARCHAR(64) NOT NULL COMMENT '商品标题',
    `body` VARCHAR(256) NOT NULL COMMENT '商品描述信息',
    `channel_extra` VARCHAR(512) DEFAULT NULL COMMENT '特定渠道发起额外参数',
    `channel_user` VARCHAR(64) DEFAULT NULL COMMENT '渠道用户标识,如微信openId,支付宝账号',
    `channel_order_no` VARCHAR(64) DEFAULT NULL COMMENT '渠道订单号',
    `refund_status` TINYINT NOT NULL DEFAULT 0 COMMENT '退款状态: 0-未发生实际退款, 1-部分退款, 2-全额退款',
    `refund_times` INT NOT NULL DEFAULT 0 COMMENT '退款次数',
    `refund_amount` BIGINT NOT NULL DEFAULT 0 COMMENT '退款总金额,单位分',
    `division_mode` TINYINT DEFAULT 0 COMMENT '订单分账模式：0-该笔订单不允许分账, 1-支付成功按配置自动完成分账, 2-商户手动分账(解冻商户金额)',
    `division_status` TINYINT DEFAULT 0 COMMENT '订单分账状态：0-未发生分账, 1-等待分账任务处理, 2-分账处理中, 3-分账任务已结束(不体现状态)',
    `division_last_time` DATETIME COMMENT '最新分账时间',
    `err_code` VARCHAR(128) DEFAULT NULL COMMENT '渠道支付错误码',
    `err_msg` VARCHAR(256) DEFAULT NULL COMMENT '渠道支付错误描述',
    `ext_param` VARCHAR(128) DEFAULT NULL COMMENT '商户扩展参数',
    `notify_url` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '异步通知地址',
    `return_url` VARCHAR(128) DEFAULT '' COMMENT '页面跳转地址',
    `expired_time` DATETIME DEFAULT NULL COMMENT '订单失效时间',
    `success_time` DATETIME DEFAULT NULL COMMENT '订单支付成功时间',
    `create_time` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    `create_user` BIGINT NOT NULL COMMENT '创建用户ID',
    `update_time` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
    `update_user` BIGINT DEFAULT NULL COMMENT '更新用户ID',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `uk_pay_order_no` USING BTREE (`pay_order_no`) VISIBLE,
    UNIQUE INDEX `uk_pay_order_mch_order_trace` USING BTREE (`pay_order_no`, `mch_order_no`, `trace_no`) VISIBLE,
    INDEX `idx_mch_order_no` USING BTREE (`mch_order_no`) VISIBLE,
    INDEX `idx_pay_order_no` USING BTREE (`pay_order_no`) VISIBLE,
    INDEX `idx_mch_id` USING BTREE (`mch_no`) VISIBLE,
    INDEX `idx_app_id` USING BTREE (`app_no`) VISIBLE,
    INDEX `idx_created_at` USING BTREE (`create_time`) VISIBLE,
    INDEX `idx_status` USING BTREE (`status`) VISIBLE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付订单表';

-- changeset Zhangsam:7 runOnChange:true
--  comment 商户通知记录表
CREATE TABLE IF NOT EXISTS `sys_mch_notify_record` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `pay_order_no` VARCHAR(64) NOT NULL COMMENT '订单ID',
    `order_type` TINYINT NOT NULL COMMENT '订单类型:1-支付,2-退款',
    `mch_order_no` VARCHAR(64) NOT NULL COMMENT '商户订单号',
    `mch_no` VARCHAR(64) NOT NULL COMMENT '商户号',
    `isv_no` VARCHAR(64) COMMENT '服务商号',
    `app_no` VARCHAR(64) NOT NULL COMMENT '应用ID',
    `notify_url` TEXT NOT NULL COMMENT '通知地址',
    `res_result` TEXT DEFAULT NULL COMMENT '通知响应结果',
    `notify_count` INT NOT NULL DEFAULT 0 COMMENT '通知次数',
    `notify_count_limit` INT NOT NULL DEFAULT 6 COMMENT '最大通知次数, 默认6次',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '通知状态,1-通知中,2-通知成功,3-通知失败',
    `last_notify_time` DATETIME DEFAULT NULL COMMENT '最后一次通知时间',
    `create_time` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    `create_user` BIGINT NOT NULL COMMENT '创建用户ID',
    `update_time` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
    `update_user` BIGINT DEFAULT NULL COMMENT '更新用户ID',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `uk_orderNo_type` USING BTREE (`pay_order_no`, `order_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商户通知记录表';

