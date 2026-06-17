USE `project`;

CREATE TABLE IF NOT EXISTS `material_category` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '分类ID',
    `name` VARCHAR(50) NOT NULL COMMENT '分类名称',
    `description` VARCHAR(200) DEFAULT '' COMMENT '分类描述',
    `parent_id` BIGINT DEFAULT NULL COMMENT '父分类ID',
    `sort_order` INT DEFAULT 0 COMMENT '排序号',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX `idx_parent_id` (`parent_id`),
    INDEX `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='物资分类表';

CREATE TABLE IF NOT EXISTS `material` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '物资ID',
    `code` VARCHAR(50) NOT NULL UNIQUE COMMENT '物资编码',
    `name` VARCHAR(100) NOT NULL COMMENT '物资名称',
    `category_id` BIGINT NOT NULL COMMENT '分类ID',
    `specification` VARCHAR(100) DEFAULT '' COMMENT '规格型号',
    `unit` VARCHAR(20) NOT NULL COMMENT '计量单位',
    `purchase_price` DECIMAL(12,2) DEFAULT 0 COMMENT '采购单价',
    `sell_price` DECIMAL(12,2) DEFAULT 0 COMMENT '销售单价',
    `min_stock` INT DEFAULT 0 COMMENT '最低库存阈值',
    `max_stock` INT DEFAULT 0 COMMENT '最高库存阈值',
    `description` VARCHAR(500) DEFAULT '' COMMENT '备注说明',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX `idx_category_id` (`category_id`),
    INDEX `idx_code` (`code`),
    INDEX `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='物资表';

CREATE TABLE IF NOT EXISTS `warehouse` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '仓库ID',
    `code` VARCHAR(50) NOT NULL UNIQUE COMMENT '仓库编码',
    `name` VARCHAR(100) NOT NULL COMMENT '仓库名称',
    `location` VARCHAR(200) DEFAULT '' COMMENT '仓库位置',
    `capacity` INT DEFAULT 0 COMMENT '仓库容量',
    `description` VARCHAR(500) DEFAULT '' COMMENT '备注说明',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX `idx_code` (`code`),
    INDEX `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='仓库表';

CREATE TABLE IF NOT EXISTS `stock` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '库存ID',
    `material_id` BIGINT NOT NULL COMMENT '物资ID',
    `warehouse_id` BIGINT NOT NULL COMMENT '仓库ID',
    `quantity` INT NOT NULL DEFAULT 0 COMMENT '库存数量',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE INDEX `uk_material_warehouse` (`material_id`, `warehouse_id`),
    INDEX `idx_material_id` (`material_id`),
    INDEX `idx_warehouse_id` (`warehouse_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存表';

CREATE TABLE IF NOT EXISTS `inbound` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '入库ID',
    `inbound_no` VARCHAR(50) NOT NULL UNIQUE COMMENT '入库单号',
    `type` TINYINT NOT NULL COMMENT '入库类型：1-采购入库，2-退货入库',
    `material_id` BIGINT NOT NULL COMMENT '物资ID',
    `warehouse_id` BIGINT NOT NULL COMMENT '仓库ID',
    `quantity` INT NOT NULL COMMENT '入库数量',
    `unit_price` DECIMAL(12,2) DEFAULT 0 COMMENT '单价',
    `total_amount` DECIMAL(12,2) DEFAULT 0 COMMENT '总金额',
    `supplier` VARCHAR(100) DEFAULT '' COMMENT '供应商',
    `operator_id` BIGINT COMMENT '操作人ID',
    `remark` VARCHAR(500) DEFAULT '' COMMENT '备注说明',
    `status` TINYINT DEFAULT 1 COMMENT '状态：1-已完成，0-已取消',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '入库时间',
    INDEX `idx_inbound_no` (`inbound_no`),
    INDEX `idx_type` (`type`),
    INDEX `idx_material_id` (`material_id`),
    INDEX `idx_warehouse_id` (`warehouse_id`),
    INDEX `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='入库记录表';

CREATE TABLE IF NOT EXISTS `outbound` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '出库ID',
    `outbound_no` VARCHAR(50) NOT NULL UNIQUE COMMENT '出库单号',
    `type` TINYINT NOT NULL COMMENT '出库类型：1-领用出库，2-报废出库',
    `material_id` BIGINT NOT NULL COMMENT '物资ID',
    `warehouse_id` BIGINT NOT NULL COMMENT '仓库ID',
    `quantity` INT NOT NULL COMMENT '出库数量',
    `unit_price` DECIMAL(12,2) DEFAULT 0 COMMENT '单价',
    `total_amount` DECIMAL(12,2) DEFAULT 0 COMMENT '总金额',
    `recipient` VARCHAR(100) DEFAULT '' COMMENT '领用人',
    `department` VARCHAR(100) DEFAULT '' COMMENT '领用部门',
    `operator_id` BIGINT COMMENT '操作人ID',
    `remark` VARCHAR(500) DEFAULT '' COMMENT '备注说明',
    `status` TINYINT DEFAULT 1 COMMENT '状态：1-已完成，0-已取消',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '出库时间',
    INDEX `idx_outbound_no` (`outbound_no`),
    INDEX `idx_type` (`type`),
    INDEX `idx_material_id` (`material_id`),
    INDEX `idx_warehouse_id` (`warehouse_id`),
    INDEX `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='出库记录表';

CREATE TABLE IF NOT EXISTS `warehouse_user` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
    `username` VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    `password` VARCHAR(100) NOT NULL COMMENT '密码',
    `real_name` VARCHAR(50) DEFAULT '' COMMENT '真实姓名',
    `phone` VARCHAR(20) DEFAULT '' COMMENT '手机号码',
    `email` VARCHAR(100) DEFAULT '' COMMENT '邮箱地址',
    `role` TINYINT DEFAULT 2 COMMENT '角色：1-管理员，2-普通用户',
    `status` TINYINT DEFAULT 1 COMMENT '状态：1-启用，0-禁用',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

INSERT INTO `warehouse_user` (`username`, `password`, `real_name`, `role`, `status`) VALUES ('admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', 1, 1);
INSERT INTO `warehouse` (`code`, `name`, `location`) VALUES ('WH001', '主仓库', 'A栋1楼');
INSERT INTO `material_category` (`name`, `description`) VALUES ('电子设备', '电子元器件'), ('办公用品', '办公物品'), ('机械设备', '生产设备'), ('原材料', '生产原材料');