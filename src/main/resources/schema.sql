USE `project`;

CREATE TABLE IF NOT EXISTS `material_category` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL,
    `description` VARCHAR(200) DEFAULT '',
    `parent_id` BIGINT DEFAULT NULL,
    `sort_order` INT DEFAULT 0,
    `is_deleted` TINYINT DEFAULT 0,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX `idx_parent_id` (`parent_id`),
    INDEX `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `material` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `code` VARCHAR(50) NOT NULL UNIQUE,
    `name` VARCHAR(100) NOT NULL,
    `category_id` BIGINT NOT NULL,
    `specification` VARCHAR(100) DEFAULT '',
    `unit` VARCHAR(20) NOT NULL,
    `purchase_price` DECIMAL(12,2) DEFAULT 0,
    `sell_price` DECIMAL(12,2) DEFAULT 0,
    `min_stock` INT DEFAULT 0,
    `max_stock` INT DEFAULT 0,
    `description` VARCHAR(500) DEFAULT '',
    `is_deleted` TINYINT DEFAULT 0,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX `idx_category_id` (`category_id`),
    INDEX `idx_code` (`code`),
    INDEX `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `warehouse` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `code` VARCHAR(50) NOT NULL UNIQUE,
    `name` VARCHAR(100) NOT NULL,
    `location` VARCHAR(200) DEFAULT '',
    `capacity` INT DEFAULT 0,
    `description` VARCHAR(500) DEFAULT '',
    `is_deleted` TINYINT DEFAULT 0,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX `idx_code` (`code`),
    INDEX `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `stock` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `material_id` BIGINT NOT NULL,
    `warehouse_id` BIGINT NOT NULL,
    `quantity` INT NOT NULL DEFAULT 0,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE INDEX `uk_material_warehouse` (`material_id`, `warehouse_id`),
    INDEX `idx_material_id` (`material_id`),
    INDEX `idx_warehouse_id` (`warehouse_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `inbound` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `inbound_no` VARCHAR(50) NOT NULL UNIQUE,
    `type` TINYINT NOT NULL,
    `material_id` BIGINT NOT NULL,
    `warehouse_id` BIGINT NOT NULL,
    `quantity` INT NOT NULL,
    `unit_price` DECIMAL(12,2) DEFAULT 0,
    `total_amount` DECIMAL(12,2) DEFAULT 0,
    `supplier` VARCHAR(100) DEFAULT '',
    `operator_id` BIGINT,
    `remark` VARCHAR(500) DEFAULT '',
    `status` TINYINT DEFAULT 1,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX `idx_inbound_no` (`inbound_no`),
    INDEX `idx_type` (`type`),
    INDEX `idx_material_id` (`material_id`),
    INDEX `idx_warehouse_id` (`warehouse_id`),
    INDEX `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `outbound` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `outbound_no` VARCHAR(50) NOT NULL UNIQUE,
    `type` TINYINT NOT NULL,
    `material_id` BIGINT NOT NULL,
    `warehouse_id` BIGINT NOT NULL,
    `quantity` INT NOT NULL,
    `unit_price` DECIMAL(12,2) DEFAULT 0,
    `total_amount` DECIMAL(12,2) DEFAULT 0,
    `recipient` VARCHAR(100) DEFAULT '',
    `department` VARCHAR(100) DEFAULT '',
    `operator_id` BIGINT,
    `remark` VARCHAR(500) DEFAULT '',
    `status` TINYINT DEFAULT 1,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX `idx_outbound_no` (`outbound_no`),
    INDEX `idx_type` (`type`),
    INDEX `idx_material_id` (`material_id`),
    INDEX `idx_warehouse_id` (`warehouse_id`),
    INDEX `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `warehouse_user` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(50) NOT NULL UNIQUE,
    `password` VARCHAR(100) NOT NULL,
    `real_name` VARCHAR(50) DEFAULT '',
    `phone` VARCHAR(20) DEFAULT '',
    `email` VARCHAR(100) DEFAULT '',
    `role` TINYINT DEFAULT 2,
    `status` TINYINT DEFAULT 1,
    `is_deleted` TINYINT DEFAULT 0,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `warehouse_user` (`username`, `password`, `real_name`, `role`, `status`) VALUES ('admin', 'e10adc3949ba59abbe56e057f20f883e', 'Admin', 1, 1);
INSERT INTO `warehouse` (`code`, `name`, `location`) VALUES ('WH001', 'Main Warehouse', 'A Building 1F');
INSERT INTO `material_category` (`name`, `description`) VALUES ('Electronics', 'Electronic components'), ('Office Supplies', 'Office items'), ('Machinery', 'Production machinery'), ('Raw Materials', 'Production materials');