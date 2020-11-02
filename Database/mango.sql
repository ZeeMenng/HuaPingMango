/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50643
 Source Host           : localhost:3306
 Source Schema         : mango

 Target Server Type    : MySQL
 Target Server Version : 50643
 File Encoding         : 65001

 Date: 02/11/2020 20:24:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for da_altitude_info
-- ----------------------------
DROP TABLE IF EXISTS `da_altitude_info`;
CREATE TABLE `da_altitude_info`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `altitude` decimal(20, 6) NULL DEFAULT NULL COMMENT '海拔（米）',
  `average_temperature` decimal(20, 6) NULL DEFAULT NULL COMMENT '平均气温（摄氏度）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '海拔数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_base_detail
-- ----------------------------
DROP TABLE IF EXISTS `da_base_detail`;
CREATE TABLE `da_base_detail`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `detail_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `detail_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `base_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `base_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area_detail` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `output` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `regenerator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(2) NOT NULL DEFAULT 0 COMMENT '删除标志：0正常，1删除',
  `extra` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `planted` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否种植：0未种植，1已种植',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '基地细分表-地块表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_base_info
-- ----------------------------
DROP TABLE IF EXISTS `da_base_info`;
CREATE TABLE `da_base_info`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `base_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `base_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `base_longitude` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `base_latitude` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `base_area` decimal(20, 6) NULL DEFAULT NULL,
  `base_area_unit` tinyint(4) NULL DEFAULT NULL,
  `area_unit` decimal(20, 6) NULL DEFAULT NULL COMMENT '通用面积（用于存放换算为平方米之后的面积数值）',
  `base_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '基地类型 1：大棚，2：其他',
  `base_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `owner` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `identification_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '认证类型 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证',
  `identification_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `peasant_count` decimal(10, 0) NULL DEFAULT NULL COMMENT '种植户数量',
  `enterprise_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '基地信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_check_collection
-- ----------------------------
DROP TABLE IF EXISTS `da_check_collection`;
CREATE TABLE `da_check_collection`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果',
  `crop_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的作物品种',
  `strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sample_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sample_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sample_date` datetime(0) NULL DEFAULT NULL COMMENT '采样时间',
  `sample_place` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sample_personnel` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `check_project` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `check_project_code` tinyint(4) NULL DEFAULT NULL COMMENT '检测项目编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '采样数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_check_testing
-- ----------------------------
DROP TABLE IF EXISTS `da_check_testing`;
CREATE TABLE `da_check_testing`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果',
  `crop_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的作物品种',
  `strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sample_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sample_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `check_date` datetime(0) NULL DEFAULT NULL COMMENT '检测时间',
  `check_result` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `check_personnel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `check_org` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `check_project` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `check_project_code` tinyint(4) NULL DEFAULT NULL COMMENT '检测项目编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '检测数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_common_field
-- ----------------------------
DROP TABLE IF EXISTS `da_common_field`;
CREATE TABLE `da_common_field`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `data_time_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '数据采集维度：对应数据字典表（dictionary）中的编码字段（code）,1：年，2：季度，3：月，4：周，5：日，6：实时',
  `data_time_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `area_latitude_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '区域维度：对应数据字典表（dictionary）中的编码字段（code）1：国家，2：省，3：市，4：县/区，5：乡/镇，6：村/组/社区，7：农户个体，8：企业个体',
  `area_latitude_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `source_channel_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '来源渠道：对应数据字典表（dictionary）中的编码字段（code）1：农户APP填报，2：农户web填报，3：政府人员采集，4：网络采集，5：第三方对接采集，6：其他，7：企业web填报',
  `source_channel_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data_sources` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `audit_status_code` tinyint(4) NULL DEFAULT 0 COMMENT '审核状态：对应数据字典表（dictionary）中的编码字段（code）0：待审核，1:审核通过，2：审核不通过',
  `audit_status_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `auditer_suggestion` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `auditer_user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `auditer_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `add_user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录最后一次修改时间',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_index`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通用字段表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_corp_disaster
-- ----------------------------
DROP TABLE IF EXISTS `da_corp_disaster`;
CREATE TABLE `da_corp_disaster`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果',
  `crop_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的作物品种',
  `strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `disaster_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '受灾类型：对应数据字典表（dictionary）中的编码字段（code）',
  `disaster_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `disaster_area` decimal(20, 6) NULL DEFAULT NULL,
  `disaster_area_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '受灾面积单位:对应数据字典表（dictionary）中的编码字段（code）  1：亩，2：万亩，3：公顷',
  `disaster_area_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `disaster_area_unit` decimal(20, 6) NULL DEFAULT NULL,
  `disaster_lose` decimal(20, 6) NULL DEFAULT NULL,
  `disaster_lose_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '经济损失单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元',
  `disaster_lose_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `disaster_lose_unit` decimal(20, 6) NULL DEFAULT NULL,
  `disaster_level_code` tinyint(4) NULL DEFAULT NULL COMMENT '灾情:对应数据字典表（dictionary）中的编码字段（code）',
  `disaster_level_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `weather` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '作物受灾数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_crawler
-- ----------------------------
DROP TABLE IF EXISTS `da_crawler`;
CREATE TABLE `da_crawler`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `promo_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '促销价格',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `from` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发货地',
  `inventory` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sell_counter` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rate_counter` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parameter` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `goods_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `source` tinyint(1) NULL DEFAULT NULL COMMENT '数据来源：\'平台号：对应数据字典表（dictionary）中的编码字段（code）1：天猫，2：淘宝，3：京东，4：惠农网\'',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `c_gs`(`goods_id`, `source`) USING BTREE,
  INDEX `c_ct`(`created_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '爬虫' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_ecology_norm_info
-- ----------------------------
DROP TABLE IF EXISTS `da_ecology_norm_info`;
CREATE TABLE `da_ecology_norm_info`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `norm_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `optimum_area` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `appropriate_area` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ecumenic_area` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inadequate_area` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '种植生态指标表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_enterprise_certificate_info
-- ----------------------------
DROP TABLE IF EXISTS `da_enterprise_certificate_info`;
CREATE TABLE `da_enterprise_certificate_info`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `cer_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `order_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_date` datetime(0) NULL DEFAULT NULL COMMENT '有效期自',
  `end_date` datetime(0) NULL DEFAULT NULL COMMENT '有效期至',
  `organ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `certificate_status_code` tinyint(4) NULL DEFAULT NULL COMMENT '证书状态。编码，对应数据字典表（dictionary）中的编码字段（code） 目前定义两种状态   0：有效期内 1：证件过期',
  `certificate_status_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `certificate_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '证书类型。编码，对应数据字典表（dictionary）中的编码字段（code）目前定义5种类型   1：通用资质 2：无公害农产品 3：绿色食品 4：有机农产品 5：农产品地理标志',
  `certificate_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `publicity_time` datetime(0) NULL DEFAULT NULL COMMENT '公示时间',
  `detail` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `datasource_code` tinyint(4) NULL DEFAULT NULL COMMENT '数据来源。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：数据中心  2：手工输入',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `file_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_resource_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_resource_path` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业证书' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_enterprise_cognate_info
-- ----------------------------
DROP TABLE IF EXISTS `da_enterprise_cognate_info`;
CREATE TABLE `da_enterprise_cognate_info`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `enterprise_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cognate_enterprise_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cognate_enterprise_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cognate_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '关联关系类型。  编码，对应数据字典表（dictionary）中的编码字段（code）目前定义2种关系  1：主动关联 2：被动关联',
  `cognate_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `datasource_code` tinyint(4) NULL DEFAULT NULL COMMENT '数据来源。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：数据中心  2：手工输入',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业关联企业信息表，企业与企业之间关系。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_enterprise_info
-- ----------------------------
DROP TABLE IF EXISTS `da_enterprise_info`;
CREATE TABLE `da_enterprise_info`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organization_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `registration_mark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `archives_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `society_credit_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `register_status_text` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `register_status_code` tinyint(4) NULL DEFAULT NULL COMMENT '登记状态',
  `register_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `register_type_code` int(4) NULL DEFAULT NULL COMMENT '登记注册类型',
  `statistics_Department_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `found_date` datetime(0) NULL DEFAULT NULL COMMENT '成立时间',
  `corporation_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `corporation_identity_card` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `corporation_mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `linkman` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `linkman_mobile` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `landline_telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '企业类型，编码，对应数据字典表（dictionary）中的编码字段（code）。 目前定义2种类型。5：生产企业,6:加工企业7:销售企业,8:投入品经营企业',
  `enterprise_nature_code` tinyint(4) NULL DEFAULT NULL COMMENT '企业性质。编码，对应数据字典表（dictionary）中的编码字段（code）。 目前定义2种类型。1：国营  2:私人',
  `enterprise_nature_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `industry_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '企业产业类型.编码，对应数据字典表（dictionary）中的编码字段（code）。 目前定义2种类型。1：种植企业  2:畜牧养殖',
  `industry_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `registered_capital` decimal(20, 6) NULL DEFAULT NULL COMMENT '注册资金',
  `paidin_capital` decimal(20, 6) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `administrative_area` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `measure_longitude` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `measure_latitude` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `establish_date` datetime(0) NULL DEFAULT NULL COMMENT '成立日期',
  `approval_date` datetime(0) NULL DEFAULT NULL COMMENT '核准日期',
  `business_duetime` datetime(0) NULL DEFAULT NULL COMMENT '营业期限',
  `longdistance_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `administration_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `business_scope` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `professional_activity` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_control` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organization_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organization_type_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `industry_activity` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `industry_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `industry_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `risk_grade` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `risk_value` decimal(20, 6) NULL DEFAULT NULL,
  `category_code` tinyint(4) NULL DEFAULT NULL COMMENT '评估类型。编码，对应数据字典表（dictionary）中的编码字段（code）。目前11种评估类型：1企业综合风险评估，2成长风险综合评估，3行业地位与竞争力评估，4偿债风险综合评估，5运营风险综合评估，6盈利风险综合评估，7竞争能力综合评估，8诉讼处罚风险评估，9资本运作风险评估，10舆情风险评估，11核心团队风险评估。',
  `category_text` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `risk_tags_code` tinyint(4) NULL DEFAULT NULL COMMENT '风险标签。编码，对应数据字典表（dictionary）中的编码字段（code）目前定义5种类型    1：交易额持续下滑 2：拖欠工资 3：债务拖欠 4：履约不利 5：违法侵权    \r\n            可以有多个用|进行分隔',
  `risk_tags_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `funnel_tags_code` tinyint(4) NULL DEFAULT NULL COMMENT '漏斗标签。编码，对应数据字典表（dictionary）中的编码字段（code）目前定义5种类型   1：资金缺口 2：业务稳定 3：诚信良好 4：舆情正常 5：市场预期良好。可以有多个用|进行分隔。',
  `funnel_tags_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `core_code` tinyint(4) NULL DEFAULT NULL COMMENT '核心企业。编码，对应数据字典表（dictionary）中的编码字段（code） 0：核心企业 1：关联企业',
  `core_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `person_scale` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `money_scale` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `year_they` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_corporate_champion_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '是否是龙头企业，对应数据字典表（dictionary）中的文本字段（name）0：是 1：否',
  `is_corporate_champion_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scale_enterprise_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '规模企业类型，对应数据字典表（dictionary）中的文本字段（name） 1：规模企业',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scale_enterprise_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `summary` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `description` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `zip_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telautogram` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_recommend` tinyint(4) NULL DEFAULT NULL COMMENT '是否推介，对应数据字典表（dictionary）中的文本字段（name）0：是 1：否',
  `organization_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `manage_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trade_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `registry_office` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logo_resource_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logo_resource_path` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_cooperative_code` tinyint(4) NULL DEFAULT NULL COMMENT '是否合作社编码，对应数据字典表（dictionary）中的文本字段（name）0：是 1：否',
  `is_cooperative_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_index`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_enterprise_litigation_info
-- ----------------------------
DROP TABLE IF EXISTS `da_enterprise_litigation_info`;
CREATE TABLE `da_enterprise_litigation_info`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `enterprise_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `execution_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reference_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `filing_time` datetime(0) NULL DEFAULT NULL COMMENT '立案时间',
  `card_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `execution_court` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `execution_object` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `law_state` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `year` year NULL DEFAULT NULL COMMENT '发生年份',
  `litigation_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `litigation_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `accuser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `defendant` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `datasource_code` tinyint(4) NULL DEFAULT NULL COMMENT '数据来源。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：数据中心  2：手工输入',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业诉讼表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_enterprise_loan_info
-- ----------------------------
DROP TABLE IF EXISTS `da_enterprise_loan_info`;
CREATE TABLE `da_enterprise_loan_info`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `enterprise_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loan_date` datetime(0) NULL DEFAULT NULL COMMENT '贷款时间',
  `loan_money` decimal(20, 6) NULL DEFAULT NULL,
  `loan_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '贷款方式。编码，对应数据字典表（dictionary）中的编码字段（code）目前先定义4种方式：1等额本息还款； 2等额本金还款； 3一次性还本付息；4按期付息还本',
  `loan_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loan_status` tinyint(4) NULL DEFAULT NULL COMMENT '当前状态。编码，对应数据字典表（dictionary）中的编码字段（code）目前先定义2种状态   1：还息中 2：其他',
  `loan_status_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `overdue_status_code` tinyint(4) NULL DEFAULT NULL COMMENT '是否逾期标识。编码，对应数据字典表（dictionary）中的编码字段（code）  字段类型  0：是  1：否',
  `overdue_status_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `datasource_code` tinyint(4) NULL DEFAULT NULL COMMENT '数据来源。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：数据中心  2：手工输入',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业贷款记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_enterprise_person_info
-- ----------------------------
DROP TABLE IF EXISTS `da_enterprise_person_info`;
CREATE TABLE `da_enterprise_person_info`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `enterprise_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `position` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `datasource_code` tinyint(4) NULL DEFAULT NULL COMMENT '数据来源。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：数据中心  2：手工输入',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业人员信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_enterprise_property_info
-- ----------------------------
DROP TABLE IF EXISTS `da_enterprise_property_info`;
CREATE TABLE `da_enterprise_property_info`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `enterprise_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `year` year NULL DEFAULT NULL COMMENT '发生年份',
  `total_assets` decimal(20, 6) NULL DEFAULT NULL,
  `total_current_assets` decimal(20, 6) NULL DEFAULT NULL,
  `total_liabilities` decimal(20, 6) NULL DEFAULT NULL,
  `total_owners_equity` decimal(20, 6) NULL DEFAULT NULL,
  `operating_income` decimal(20, 6) NULL DEFAULT NULL,
  `operating_costs` decimal(20, 6) NULL DEFAULT NULL,
  `operating_profit` decimal(20, 6) NULL DEFAULT NULL,
  `total_profit` decimal(20, 6) NULL DEFAULT NULL,
  `net_profit` decimal(20, 6) NULL DEFAULT NULL,
  `assets_interest_rate` decimal(20, 6) NULL DEFAULT NULL,
  `equity_interest_rate` decimal(20, 6) NULL DEFAULT NULL,
  `gross_profit_margin` decimal(20, 6) NULL DEFAULT NULL,
  `before_year_total_assets` decimal(20, 6) NULL DEFAULT NULL,
  `before_year_operating_income` decimal(20, 6) NULL DEFAULT NULL,
  `before_year_total_liabilities` decimal(20, 6) NULL DEFAULT NULL,
  `total_current_quick` decimal(20, 6) NULL DEFAULT NULL,
  `fixed_assets` decimal(20, 6) NULL DEFAULT NULL,
  `invisible_assets_net_alue` decimal(20, 6) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flag_code` tinyint(4) NULL DEFAULT NULL COMMENT '是否有效。  编码。对应数据字典表（dictionary）中的编码字段（code）1.有效，0.无效，2.删除        ',
  `flag_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `source_create_time` datetime(0) NULL DEFAULT NULL COMMENT '源创建时间',
  `currency` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `datasource_code` tinyint(4) NULL DEFAULT NULL COMMENT '数据来源。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：数据中心  2：手工输入',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业财产信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_enterprise_shareholder_info
-- ----------------------------
DROP TABLE IF EXISTS `da_enterprise_shareholder_info`;
CREATE TABLE `da_enterprise_shareholder_info`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `enterprise_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shareholder_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nature_code` tinyint(4) NULL DEFAULT NULL COMMENT '股东类型。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：自然人股东  2：法人股东',
  `nature_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `datasource_code` tinyint(4) NULL DEFAULT NULL COMMENT '数据来源。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：数据中心  2：手工输入',
  `subscribed_amount_sum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paid_amount_sum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `share_rate` decimal(20, 6) NULL DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业股东信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_enterprise_tax_info
-- ----------------------------
DROP TABLE IF EXISTS `da_enterprise_tax_info`;
CREATE TABLE `da_enterprise_tax_info`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `enterprise_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tax_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tax_organ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tax_person_status_code` tinyint(4) NULL DEFAULT NULL COMMENT '纳税人状态。   编码，对应数据字典表（dictionary）中的编码字段（code)  不确定具体类型   0：其他',
  `tax_person_status_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tax_status_code` tinyint(4) NULL DEFAULT NULL COMMENT '纳税状态。  编码，对应数据字典表（dictionary）中的编码字段（code）   不确定具体类型    0：其他',
  `tax_status_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tax_money` decimal(20, 6) NULL DEFAULT NULL,
  `tax_time` datetime(0) NULL DEFAULT NULL COMMENT '纳税时间',
  `datasource_code` tinyint(4) NULL DEFAULT NULL COMMENT '数据来源。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：数据中心  2：手工输入',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业税务信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_enterprise_updownstream_info
-- ----------------------------
DROP TABLE IF EXISTS `da_enterprise_updownstream_info`;
CREATE TABLE `da_enterprise_updownstream_info`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `enterprise_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cognate_enterprise_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cognate_enterprise_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `up_down_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '上下游关系类型。  编码，对应数据字典表（dictionary）中的编码字段（code）目前定义3种关系  1：上游  2：中游  3: 下游',
  `up_down_type_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `datasource_code` tinyint(4) NULL DEFAULT NULL COMMENT '数据来源。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：数据中心  2：手工输入',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业关联企业表，企业上下游关系记录。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_grow_cost
-- ----------------------------
DROP TABLE IF EXISTS `da_grow_cost`;
CREATE TABLE `da_grow_cost`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果',
  `crop_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的作物品种',
  `strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `input_cost` decimal(20, 6) NULL DEFAULT NULL,
  `input_cost_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '投入品总成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩',
  `input_cost_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `input_cost_unit` decimal(20, 6) NULL DEFAULT NULL,
  `artificial_cost` decimal(20, 6) NULL DEFAULT NULL,
  `artificial_cost_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '人工总成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩',
  `artificial_cost_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `artificial_cost_unit` decimal(20, 6) NULL DEFAULT NULL,
  `other_cost` decimal(20, 6) NULL DEFAULT NULL,
  `other_cost_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '其他总成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩',
  `other_cost_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `other_cost_unit` decimal(20, 6) NULL DEFAULT NULL,
  `total_cost` decimal(20, 6) NULL DEFAULT NULL,
  `total_cost_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '总成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩',
  `total_cost_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `total_cost_unit` decimal(20, 6) NULL DEFAULT NULL,
  `acre_cost` decimal(20, 6) NULL DEFAULT NULL,
  `acre_cost_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '亩成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩',
  `acre_cost_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `acre_cost_unit` decimal(20, 6) NULL DEFAULT NULL,
  `catty_cost` decimal(20, 6) NULL DEFAULT NULL,
  `catty_cost_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '市斤成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩',
  `catty_cost_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `catty_cost_unit` decimal(20, 6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '种植成本' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_grow_yield
-- ----------------------------
DROP TABLE IF EXISTS `da_grow_yield`;
CREATE TABLE `da_grow_yield`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `base_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `base_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `block_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `block_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grow_area` decimal(20, 6) NULL DEFAULT NULL,
  `grow_area_code` tinyint(4) NULL DEFAULT NULL COMMENT '种植面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷',
  `grow_area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grow_area_unit` decimal(20, 6) NULL DEFAULT NULL,
  `crop_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果',
  `crop_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的作物品种',
  `strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `improve_area` decimal(20, 6) NULL DEFAULT NULL,
  `improve_area_code` tinyint(4) NULL DEFAULT NULL COMMENT '改良面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷',
  `improve_area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `improve_area_unit` decimal(20, 6) NULL DEFAULT NULL,
  `fruit_area` decimal(20, 6) NULL DEFAULT NULL,
  `fruit_area_code` tinyint(4) NULL DEFAULT NULL COMMENT '挂果面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷',
  `fruit_area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fruit_area_unit` decimal(20, 6) NULL DEFAULT NULL,
  `product_total` decimal(20, 6) NULL DEFAULT NULL COMMENT '产品产量',
  `product_total_code` tinyint(4) NULL DEFAULT NULL COMMENT '产品产量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨',
  `product_total_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_total_unit` decimal(20, 6) NULL DEFAULT NULL COMMENT '标准单位：千克',
  `output_value` decimal(20, 6) NULL DEFAULT NULL COMMENT '产值',
  `output_value_code` tinyint(4) NULL DEFAULT NULL COMMENT '产值单位，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元',
  `output_value_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `output_value_unit` decimal(20, 6) NULL DEFAULT NULL COMMENT '标准单位：元',
  `peasant_count` decimal(20, 6) NULL DEFAULT NULL COMMENT '种植户数',
  `year` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organic_grow_area` decimal(20, 6) NULL DEFAULT NULL COMMENT '有机种植面积',
  `organic_grow_area_code` tinyint(4) NULL DEFAULT NULL COMMENT '有机种植面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷',
  `organic_grow_area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organic_grow_area_unit` decimal(20, 6) NULL DEFAULT NULL,
  `sale_total` decimal(20, 6) NULL DEFAULT NULL COMMENT '销量',
  `sale_total_code` tinyint(4) NULL DEFAULT NULL COMMENT '产品销量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨',
  `sale_total_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_total_unit` decimal(20, 6) NULL DEFAULT NULL,
  `e_commerce_output_value` decimal(20, 6) NULL DEFAULT NULL COMMENT '电商产值',
  `e_commerce_output_value_code` tinyint(4) NULL DEFAULT NULL COMMENT '电商产值单位，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元',
  `e_commerce_output_value_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `e_commerce_output_value_unit` decimal(20, 6) NULL DEFAULT NULL,
  `e_commerce_sale_total` decimal(20, 6) NULL DEFAULT NULL COMMENT '电商销量',
  `e_commerce_sale_total_code` tinyint(4) NULL DEFAULT NULL COMMENT '电商销量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨',
  `e_commerce_sale_total_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `e_commerce_sale_total_unit` decimal(20, 6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '种植数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_identification_info
-- ----------------------------
DROP TABLE IF EXISTS `da_identification_info`;
CREATE TABLE `da_identification_info`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `base_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果',
  `crop_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的作物品种',
  `strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `produce_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `identification_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '认证类型：对应数据字典表（dictionary）中的编码字段（code） 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证',
  `identification_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `identification_area` decimal(20, 6) NULL DEFAULT NULL COMMENT '认证面积',
  `identification_area_code` tinyint(4) NULL DEFAULT NULL COMMENT '认证面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷',
  `identification_area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `identification_area_unit` decimal(20, 6) NULL DEFAULT NULL COMMENT '面积通用单位：平方米',
  `produce_summation` decimal(20, 6) NULL DEFAULT NULL,
  `produce_summation_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '产品产量单位，1：千克，2：吨，3：公斤，4：万吨',
  `produce_summation_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `produce_summation_unit` decimal(20, 6) NULL DEFAULT NULL COMMENT '重量通用单位：千克',
  `process_time` datetime(0) NULL DEFAULT NULL COMMENT '加工时间',
  `longitude` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `latitude` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '三品一标认证表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_import_export
-- ----------------------------
DROP TABLE IF EXISTS `da_import_export`;
CREATE TABLE `da_import_export`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果',
  `crop_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的作物品种',
  `strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `import_amount` decimal(20, 6) NULL DEFAULT NULL,
  `import_amount_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '进口量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨',
  `import_amount_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `import_amount_unit` decimal(20, 6) NULL DEFAULT NULL,
  `import_volume` decimal(20, 6) NULL DEFAULT NULL,
  `import_volume_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '进口额单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：万美元，6：百万美元',
  `import_volume_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `import_volume_unit` decimal(20, 6) NULL DEFAULT NULL,
  `import_price` decimal(20, 6) NULL DEFAULT NULL,
  `import_price_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '进口价格单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：万美元/公斤，6：百万美元/公斤',
  `import_price_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `import_price_unit` decimal(20, 6) NULL DEFAULT NULL,
  `import_time` datetime(0) NULL DEFAULT NULL COMMENT '进口日期',
  `import_country_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `import_country_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `import_enterprise` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `export_amount` decimal(20, 6) NULL DEFAULT NULL,
  `export_amount_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '出口量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨',
  `export_amount_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `export_amount_unit` decimal(20, 6) NULL DEFAULT NULL,
  `export_volume` decimal(20, 6) NULL DEFAULT NULL,
  `export_volume_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '出口额单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：万美元，6：百万美元',
  `export_volume_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `export_volume_unit` decimal(20, 6) NULL DEFAULT NULL,
  `export_price` decimal(20, 6) NULL DEFAULT NULL,
  `export_price_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '出口价格单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：万美元/公斤，5：百万美元/公斤',
  `export_price_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `export_price_unit` decimal(20, 6) NULL DEFAULT NULL,
  `export_time` datetime(0) NULL DEFAULT NULL COMMENT '出口日期',
  `export_country_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `export_country_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `export_enterprise` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '进出口数据表，用于存放直报数据的进出口数据' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_industry_basicinfo
-- ----------------------------
DROP TABLE IF EXISTS `da_industry_basicinfo`;
CREATE TABLE `da_industry_basicinfo`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果',
  `crop_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的作物品种',
  `strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_total_area` decimal(20, 6) NULL DEFAULT NULL,
  `crop_total_area_code` tinyint(4) NULL DEFAULT NULL COMMENT '作物总面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷',
  `crop_total_area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_total_area_unit` decimal(20, 6) NULL DEFAULT NULL,
  `crop_fruit_area` decimal(20, 6) NULL DEFAULT NULL,
  `crop_fruit_area_code` tinyint(4) NULL DEFAULT NULL COMMENT '作物挂果面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷',
  `crop_fruit_area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_fruit_area_unit` decimal(20, 6) NULL DEFAULT NULL,
  `crop_graft_area` decimal(20, 6) NULL DEFAULT NULL,
  `crop_graft_area_code` tinyint(4) NULL DEFAULT NULL COMMENT '作物嫁接面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷',
  `crop_graft_area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_graft_area_unit` decimal(20, 6) NULL DEFAULT NULL,
  `ngrafted_tree_area` decimal(20, 6) NULL DEFAULT NULL,
  `ngrafted_tree_area_code` tinyint(4) NULL DEFAULT NULL COMMENT '未嫁接大树面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷',
  `ngrafted_tree_area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ngrafted_tree_area_unit` decimal(20, 6) NULL DEFAULT NULL,
  `ngrafted_youngtree_area` decimal(20, 6) NULL DEFAULT NULL,
  `ngrafted_youngtree_area_code` tinyint(4) NULL DEFAULT NULL COMMENT '未嫁接幼树面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷',
  `ngrafted_youngtree_area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ngrafted_youngtree_area_unit` decimal(20, 6) NULL DEFAULT NULL,
  `land_area` decimal(20, 6) NULL DEFAULT NULL,
  `land_area_code` tinyint(4) NULL DEFAULT NULL COMMENT '海拔1500米以下土地面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷',
  `land_area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `land_area_unit` decimal(20, 6) NULL DEFAULT NULL,
  `grow_area` decimal(20, 6) NULL DEFAULT NULL,
  `grow_area_code` tinyint(4) NULL DEFAULT NULL COMMENT '海拔1500米以下准备种植品种面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷',
  `grow_area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grow_area_unit` decimal(20, 6) NULL DEFAULT NULL,
  `develop_variety` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产业基本情况表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_industry_fresh
-- ----------------------------
DROP TABLE IF EXISTS `da_industry_fresh`;
CREATE TABLE `da_industry_fresh`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）作物种类  1：芒果',
  `crop_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的作物品种',
  `strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area` decimal(20, 6) NULL DEFAULT NULL,
  `area_code` tinyint(4) NULL DEFAULT NULL COMMENT '面积单位，对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷',
  `area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area_unit` decimal(20, 6) NULL DEFAULT NULL,
  `output_value` decimal(20, 6) NULL DEFAULT NULL,
  `output_value_code` tinyint(4) NULL DEFAULT NULL COMMENT '产值单位，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元',
  `output_value_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `output_value_unit` decimal(20, 6) NULL DEFAULT NULL,
  `yield` decimal(20, 6) NULL DEFAULT NULL,
  `yield_code` tinyint(4) NULL DEFAULT NULL COMMENT '产量单位，对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨',
  `yield_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `yield_unit` decimal(20, 6) NULL DEFAULT NULL,
  `proportion` decimal(20, 6) NULL DEFAULT NULL,
  `proportion_fruit` decimal(20, 6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '鲜果产值数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_industry_process
-- ----------------------------
DROP TABLE IF EXISTS `da_industry_process`;
CREATE TABLE `da_industry_process`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果',
  `crop_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `process_strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）加工品品种  1：芒果汁，2：芒果干，3：芒果醋，4：芒果果酒，5：芒果果粉，6：芒果冻，7：芒果酱',
  `process_strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area` decimal(20, 6) NULL DEFAULT NULL,
  `area_code` tinyint(4) NULL DEFAULT NULL COMMENT '面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷',
  `area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area_unit` decimal(20, 6) NULL DEFAULT NULL,
  `output_value` decimal(20, 6) NULL DEFAULT NULL,
  `output_value_code` tinyint(4) NULL DEFAULT NULL COMMENT '产值单位，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元',
  `output_value_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `output_value_unit` decimal(20, 6) NULL DEFAULT NULL,
  `yield` decimal(20, 6) NULL DEFAULT NULL,
  `yield_code` tinyint(4) NULL DEFAULT NULL COMMENT '产量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨',
  `yield_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `yield_unit` decimal(20, 6) NULL DEFAULT NULL,
  `proportion` decimal(20, 6) NULL DEFAULT NULL,
  `consume_total_code` tinyint(4) NULL DEFAULT NULL COMMENT '原材料消耗总量:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨',
  `consume_total_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `consume_total_unit` decimal(20, 6) NULL DEFAULT NULL COMMENT '原材料消耗总量数值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '加工品产值数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_industry_statistics
-- ----------------------------
DROP TABLE IF EXISTS `da_industry_statistics`;
CREATE TABLE `da_industry_statistics`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果',
  `crop_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的作物品种',
  `strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grow_area` decimal(20, 6) NULL DEFAULT NULL,
  `grow_area_code` tinyint(4) NULL DEFAULT NULL COMMENT '种植单位面积,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷',
  `grow_area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grow_area_unit` decimal(20, 6) NULL DEFAULT NULL,
  `grow_household` decimal(20, 6) NULL DEFAULT NULL,
  `organic_area` decimal(20, 6) NULL DEFAULT NULL,
  `organic_area_code` tinyint(4) NULL DEFAULT NULL COMMENT '有机认证面积,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷',
  `organic_area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organic_area_unit` decimal(20, 6) NULL DEFAULT NULL,
  `output` decimal(20, 6) NULL DEFAULT NULL,
  `output_total_code` tinyint(4) NULL DEFAULT NULL COMMENT '产品产量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨',
  `output_total_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `output_total_unit` decimal(20, 6) NULL DEFAULT NULL,
  `output_per` decimal(20, 6) NULL DEFAULT NULL,
  `output_per_total_code` tinyint(4) NULL DEFAULT NULL COMMENT '产品产量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克/亩，2：克/亩',
  `output_per_total_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `output_per_total_unit` decimal(20, 6) NULL DEFAULT NULL,
  `output_value` decimal(20, 6) NULL DEFAULT NULL,
  `output_value_code` tinyint(4) NULL DEFAULT NULL COMMENT '产值，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元',
  `output_value_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `output_value_unit` decimal(20, 6) NULL DEFAULT NULL,
  `wholesale_sales` decimal(20, 6) NULL DEFAULT NULL,
  `wholesale_sales_code` tinyint(4) NULL DEFAULT NULL COMMENT '批发销售额，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元',
  `wholesale_sales_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wholesale_sales_unit` decimal(20, 6) NULL DEFAULT NULL,
  `online_sales` decimal(20, 6) NULL DEFAULT NULL,
  `online_sales_code` tinyint(4) NULL DEFAULT NULL COMMENT '电商销售额，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元',
  `online_sales_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `online_sales_unit` decimal(20, 6) NULL DEFAULT NULL,
  `online_saleamount` decimal(20, 6) NULL DEFAULT NULL,
  `online_saleamount_code` tinyint(4) NULL DEFAULT NULL COMMENT '电商销售量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨',
  `online_saleamount_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `online_saleamountl_unit` decimal(20, 6) NULL DEFAULT NULL,
  `fruithanging_area` decimal(20, 6) NULL DEFAULT NULL,
  `fruithanging_area_code` tinyint(4) NULL DEFAULT NULL COMMENT '挂果面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷',
  `fruithanging_area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fruithanging_area_unit` decimal(20, 6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产业统计数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_input
-- ----------------------------
DROP TABLE IF EXISTS `da_input`;
CREATE TABLE `da_input`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `object_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `base_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果',
  `crop_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的作物品种',
  `strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `input_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `input_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '投入品类型:对应数据字典表（dictionary）中的编码字段（code）1：种子，2：农药，3：化肥',
  `input_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `brand_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `purchase_point` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `procurement_total` decimal(20, 6) NULL DEFAULT NULL,
  `procurement_total_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '采购总量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨',
  `procurement_total_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `procurement_total_unit` decimal(20, 6) NULL DEFAULT NULL,
  `use_amount` decimal(20, 6) NULL DEFAULT NULL,
  `use_amount_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '使用量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克/亩，2：克/亩',
  `use_amount_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `use_amount_unit` decimal(20, 6) NULL DEFAULT NULL,
  `per_price` decimal(20, 6) NULL DEFAULT NULL,
  `per_price_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：元/吨，3：元/千克，4：美元/吨',
  `per_price_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `per_price_unit` decimal(20, 6) NULL DEFAULT NULL,
  `per_cost` decimal(20, 6) NULL DEFAULT NULL,
  `per_cost_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩',
  `per_cost_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `per_cost_unit` decimal(20, 6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `common_field_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '投入品信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_iot_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `da_iot_dictionary`;
CREATE TABLE `da_iot_dictionary`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `type` int(10) NULL DEFAULT NULL COMMENT '传感器类型',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `display_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unit` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物联网传感器设备字典' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_iot_monitor_basic
-- ----------------------------
DROP TABLE IF EXISTS `da_iot_monitor_basic`;
CREATE TABLE `da_iot_monitor_basic`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `hid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `device_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `basc_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `basc_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `longitude` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `latitude` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mango_type` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `plant_area` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `farmers` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `person` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物联网设备基本信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_iot_monitor_data
-- ----------------------------
DROP TABLE IF EXISTS `da_iot_monitor_data`;
CREATE TABLE `da_iot_monitor_data`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `hid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sensor_type` int(10) NULL DEFAULT 0 COMMENT '传感器类型',
  `sensor_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `display_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `val` double(10, 2) NULL DEFAULT 0.00 COMMENT '传感器的采集的值',
  `unit` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL COMMENT '监控时间',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '添加记录时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx1_hid`(`hid`) USING BTREE,
  INDEX `idx2_time`(`time`) USING BTREE,
  INDEX `idx3_sensor_type`(`sensor_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物联网设备监控数据' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_iot_monitor_disaster_data
-- ----------------------------
DROP TABLE IF EXISTS `da_iot_monitor_disaster_data`;
CREATE TABLE `da_iot_monitor_disaster_data`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `hid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `device_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `basc_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `basc_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `longitude` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `latitude` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mango_type` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `disaster_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）农业灾害类型  0：全部 1：冻害，2：暴雨，3：干旱，4：大风',
  `disaster_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sensor_type` int(10) NULL DEFAULT NULL COMMENT '传感器类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'da_iot_monitor_data表中满足预警条件的数据' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_iot_monitor_query_date
-- ----------------------------
DROP TABLE IF EXISTS `da_iot_monitor_query_date`;
CREATE TABLE `da_iot_monitor_query_date`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `last_query_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物联网数据最新查询时间表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_main_production_area
-- ----------------------------
DROP TABLE IF EXISTS `da_main_production_area`;
CREATE TABLE `da_main_production_area`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `longitude` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `latitude` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '芒果主产区' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_market_price
-- ----------------------------
DROP TABLE IF EXISTS `da_market_price`;
CREATE TABLE `da_market_price`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果',
  `crop_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的作物品种',
  `strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '价格类型:对应数据字典表（dictionary）中的编码字段（code）1：田头价，2：批发价，3:零售价',
  `price_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `per_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '价格',
  `per_price_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '成交单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤',
  `per_price_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `per_price_unit` decimal(20, 2) NULL DEFAULT NULL COMMENT '通用价格，单位：元/千克',
  `average_price` decimal(20, 2) NULL DEFAULT NULL,
  `average_price_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '平均价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤',
  `average_price_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `average_price_unit` decimal(20, 2) NULL DEFAULT NULL,
  `price_range_code` tinyint(4) NULL DEFAULT NULL COMMENT '价格区间，对应数据字典表（dictionary）中的编码字段（code） 1：5元以下/斤，2：5-10元/斤，3：10-15元/斤，4：15-20元/斤，5：20元以上/斤',
  `price_range_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bottom_price` decimal(20, 2) NULL DEFAULT NULL,
  `bottom_price_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '最低价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤',
  `bottom_price_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bottom_price_unit` decimal(20, 2) NULL DEFAULT NULL,
  `top_price` decimal(20, 2) NULL DEFAULT NULL,
  `top_price_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '最高价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤',
  `top_price_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `top_price_unit` decimal(20, 2) NULL DEFAULT NULL,
  `year` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `acre_cost_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '亩成本价',
  `acre_cost_price_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '亩成本价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤',
  `acre_cost_price_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `acre_cost_price_unit` decimal(20, 2) NULL DEFAULT NULL COMMENT '通用价格，单位：元/千克',
  `field_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '田头价格',
  `field_price_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '田头价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤',
  `field_price_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `field_price_unit` decimal(20, 2) NULL DEFAULT NULL COMMENT '通用价格，单位：元/千克',
  `retail_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '零售价格',
  `retail_price_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '零售价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤',
  `retail_price_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `retail_price_unit` decimal(20, 2) NULL DEFAULT NULL COMMENT '通用价格，单位：元/千克',
  `trade_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '批发价格',
  `trade_price_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '批发价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤',
  `trade_price_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trade_price_unit` decimal(20, 2) NULL DEFAULT NULL COMMENT '通用价格，单位：元/千克',
  `e_commerce_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '电商价格',
  `e_commerce_price_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '电商价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤',
  `e_commerce_price_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `e_commerce_price_unit` decimal(20, 2) NULL DEFAULT NULL COMMENT '通用价格，单位：元/千克',
  `average_weight` decimal(20, 2) NULL DEFAULT NULL COMMENT '芒果平均单颗重（g）',
  `mango_grade` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '市场价格数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_online_retailers
-- ----------------------------
DROP TABLE IF EXISTS `da_online_retailers`;
CREATE TABLE `da_online_retailers`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `longitude` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `latitude` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '芒果电商分部表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_peasant_info
-- ----------------------------
DROP TABLE IF EXISTS `da_peasant_info`;
CREATE TABLE `da_peasant_info`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id_number` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mobile_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `peasant_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '农户类型：对应数据字典表（dictionary）中的编码字段（code） 1：示范户，2：重点户，3：其他',
  `peasant_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mobile_operate_code` tinyint(4) NULL DEFAULT NULL COMMENT '智能手机操作熟练程度：对应数据字典表（dictionary）中的编码字段（code） 1代表好，2代表一般，3代表差，4代表完全不会',
  `mobile_operate_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '农户信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_process
-- ----------------------------
DROP TABLE IF EXISTS `da_process`;
CREATE TABLE `da_process`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果',
  `crop_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `process_strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '加工品品种:对应数据字典表（dictionary）中的编码字段（code）  1：芒果汁，2：芒果干，3：芒果醋，4：芒果果酒，5：芒果果粉，6：芒果冻，7：芒果酱',
  `process_strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `process_time` datetime(0) NULL DEFAULT NULL COMMENT '加工时间',
  `output_value` decimal(20, 6) NULL DEFAULT NULL,
  `output_value_code` tinyint(4) NULL DEFAULT NULL COMMENT '产值，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元',
  `output_value_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `output_value_unit` decimal(20, 6) NULL DEFAULT NULL,
  `consume_total` decimal(20, 6) NULL DEFAULT NULL,
  `consume_total_code` tinyint(4) NULL DEFAULT NULL COMMENT '原材料消耗总量:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨',
  `consume_total_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `consume_total_unit` decimal(20, 6) NULL DEFAULT NULL,
  `product_total` decimal(20, 6) NULL DEFAULT NULL,
  `product_total_code` tinyint(4) NULL DEFAULT NULL COMMENT '产品产量，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨',
  `product_total_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_total_unit` decimal(20, 6) NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '加工品数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_sale_channel
-- ----------------------------
DROP TABLE IF EXISTS `da_sale_channel`;
CREATE TABLE `da_sale_channel`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果',
  `crop_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的作物品种',
  `strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_amount` decimal(20, 6) NULL DEFAULT NULL,
  `sale_amount_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '销售量单位，1：千克，2：吨，3：公斤，4：万吨',
  `sale_amount_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_amount_unit` decimal(20, 6) NULL DEFAULT NULL,
  `sale_volume` decimal(20, 6) NULL DEFAULT NULL,
  `sale_volume_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '销售额单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元',
  `sale_volume_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_volume_unit` decimal(20, 6) NULL DEFAULT NULL,
  `sale_region_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_region_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `seller` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_time` datetime(0) NULL DEFAULT NULL COMMENT '销售日期',
  `sale_channel_type_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_channel_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '销售渠道数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_sale_ecommerce_order
-- ----------------------------
DROP TABLE IF EXISTS `da_sale_ecommerce_order`;
CREATE TABLE `da_sale_ecommerce_order`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果',
  `crop_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的作物品种',
  `strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ecommerce_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `order_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ecommerce_code` tinyint(4) NULL DEFAULT NULL COMMENT '平台号：对应数据字典表（dictionary）中的编码字段（code）1：天猫，2：淘宝，3：京东',
  `ecommerce_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_amount` decimal(20, 6) NULL DEFAULT NULL,
  `sale_amount_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '销售数量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨',
  `sale_amount_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_amount_unit` decimal(20, 6) NULL DEFAULT NULL,
  `actual_income` decimal(20, 6) NULL DEFAULT NULL,
  `actual_income_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '实收单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元',
  `actual_income_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `actual_income_unit` decimal(20, 6) NULL DEFAULT NULL,
  `addressee` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delivery_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contact_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `courier` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `courier_fee` decimal(20, 6) NULL DEFAULT NULL,
  `courier_fee_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '快递费用单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元',
  `courier_fee_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `courier_fee_unit` decimal(20, 6) NULL DEFAULT NULL,
  `courier_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pay_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '支付方式:对应数据字典表（dictionary）中的编码字段（code） 1；银行卡，2：支付宝:，3：微信:，4：其他:',
  `pay_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pay_account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '订单日期',
  `area_code` tinyint(4) NULL DEFAULT NULL COMMENT '收件区域code',
  `area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '电商数据表，用于存放直报的电商数据' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_sale_fresh
-- ----------------------------
DROP TABLE IF EXISTS `da_sale_fresh`;
CREATE TABLE `da_sale_fresh`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果',
  `crop_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的作物品种',
  `strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_amount` decimal(20, 6) NULL DEFAULT NULL,
  `sale_amount_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '销售量单位，1：千克，2：吨，3：公斤，4：万吨',
  `sale_amount_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_amount_unit` decimal(20, 6) NULL DEFAULT NULL,
  `sale_volume` decimal(20, 6) NULL DEFAULT NULL,
  `sale_volume_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '交易额单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元',
  `sale_volume_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_volume_unit` decimal(20, 6) NULL DEFAULT NULL,
  `sale_region_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_region_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `seller` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trade_time` datetime(0) NULL DEFAULT NULL COMMENT '交易日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '鲜果销售数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_sale_peasant
-- ----------------------------
DROP TABLE IF EXISTS `da_sale_peasant`;
CREATE TABLE `da_sale_peasant`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果',
  `crop_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的作物品种',
  `strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trade_amount` decimal(20, 6) NULL DEFAULT NULL,
  `trade_amount_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '交易量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨',
  `trade_amount_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trade_amount_unit` decimal(20, 6) NULL DEFAULT NULL,
  `trade_volume` decimal(20, 6) NULL DEFAULT NULL,
  `trade_volume_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '交易额单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元',
  `trade_volume_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trade_volume_unit` decimal(20, 6) NULL DEFAULT NULL,
  `unit_price` decimal(20, 6) NULL DEFAULT NULL,
  `per_price_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '成交单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元',
  `per_price_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `per_price_unit` decimal(20, 6) NULL DEFAULT NULL,
  `sale_region_code` tinyint(4) NULL DEFAULT NULL COMMENT '销区',
  `sale_region_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_area_code` tinyint(4) NULL DEFAULT NULL COMMENT '销地',
  `sale_area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trade_time` datetime(0) NULL DEFAULT NULL COMMENT '交易日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '农户销售数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_sale_process
-- ----------------------------
DROP TABLE IF EXISTS `da_sale_process`;
CREATE TABLE `da_sale_process`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `process_product` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `process_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '加工类型:对应数据字典表（dictionary）中的编码字段（code） 1：初级加工，2：深加工',
  `process_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果',
  `crop_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `process_strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '加工品品种:对应数据字典表（dictionary）中的编码字段（code）  1：芒果汁，2：芒果干，3：芒果醋，4：芒果果酒，5：芒果果粉，6：芒果冻，7：芒果酱',
  `process_strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_region_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_region_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_area_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_amount` decimal(20, 6) NULL DEFAULT NULL,
  `sale_amount_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '销售量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨',
  `sale_amount_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_amount_unit` decimal(20, 6) NULL DEFAULT NULL,
  `sale_volume` decimal(20, 6) NULL DEFAULT NULL,
  `sale_volume_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '销售额单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元',
  `sale_volume_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_volume_unit` decimal(20, 6) NULL DEFAULT NULL,
  `waste_amount` decimal(20, 6) NULL DEFAULT NULL,
  `waste_amount_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '损耗量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨',
  `waste_amount_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `waste_amount_unit` decimal(20, 6) NULL DEFAULT NULL,
  `sale_time` datetime(0) NULL DEFAULT NULL COMMENT '销售日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '加工销售数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_sentiment_article
-- ----------------------------
DROP TABLE IF EXISTS `da_sentiment_article`;
CREATE TABLE `da_sentiment_article`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `theme_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `article_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `publish_time` datetime(0) NULL DEFAULT NULL COMMENT '文章发布时间',
  `article_txt_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `thread_starter` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `media_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '媒体类型。编码，对应数据字典表（dictionary）中的编码字段（code）。和舆情团队提供的接口说明中一至，目前5种类型：1新闻，3论坛，4微博，6博客，10微信公众号。',
  `media_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sentiment_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '情感值（类型）。编码，对应数据字典表（dictionary）中的编码字段（code）。和舆情团队提供的接口说明中一至，目前3种类型：1负面，2中性，3正面。',
  `sentiment_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `view_count` int(11) NULL DEFAULT 0 COMMENT '浏览数。',
  `datasource_code` tinyint(4) NULL DEFAULT NULL COMMENT '来源渠道：对应数据字典表（dictionary）中的编码字段（code）1：农户APP填报，2：农户web填报，3：政府人员采集，4：网络采集，5：第三方对接采集，6：其他，7：企业web填报',
  `datasource_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `serial_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '增加时间',
  `update_status_code` tinyint(4) NULL DEFAULT 0 COMMENT '审核状态：0：原文，1：审核文章',
  `update_user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni3_serial_number`(`serial_number`) USING BTREE,
  INDEX `idx1_thread_starter`(`thread_starter`) USING BTREE,
  INDEX `idx2_media_type_code`(`media_type_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '舆情文章表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_sentiment_content
-- ----------------------------
DROP TABLE IF EXISTS `da_sentiment_content`;
CREATE TABLE `da_sentiment_content`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `article_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `txt` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `txt1` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `txt2` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `txt3` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '舆情文章内容表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_sentiment_rule
-- ----------------------------
DROP TABLE IF EXISTS `da_sentiment_rule`;
CREATE TABLE `da_sentiment_rule`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `rule_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `media_type_code_set` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `media_type_text_set` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `frequency` int(11) NOT NULL COMMENT '采集频率（N条/小时）',
  `start_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `end_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `add_user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status_code` tinyint(4) NULL DEFAULT NULL COMMENT '状态code，对应数据字典表,状态类型字典',
  `status_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '舆情采集规则表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_sentiment_source
-- ----------------------------
DROP TABLE IF EXISTS `da_sentiment_source`;
CREATE TABLE `da_sentiment_source`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `thread_starter` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `media_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '媒体类型code，对应数据字典表,媒体类型字典',
  `media_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `website` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `add_user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status_code` tinyint(4) NULL DEFAULT NULL COMMENT '状态code，对应数据字典表，0：关闭，1：开启',
  `status_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '舆情数据源表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_sentiment_theme
-- ----------------------------
DROP TABLE IF EXISTS `da_sentiment_theme`;
CREATE TABLE `da_sentiment_theme`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `theme_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `key_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `exclude_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rule_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `add_user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status_code` tinyint(4) NULL DEFAULT NULL COMMENT '状态code，对应数据字典表，0：关闭，1：开启',
  `status_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '舆情主题名称' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_target
-- ----------------------------
DROP TABLE IF EXISTS `da_target`;
CREATE TABLE `da_target`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `serial_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `target_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `target_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '指标单位,对应数据字典表（dictionary）中的编码字段（code）1：元，2：千克，3：平方米',
  `target_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `target_html_code` tinyint(4) NULL DEFAULT NULL COMMENT 'html类型，对应数据字典表（dictionary）中的编码字段（code）1：文本框，2、单选下拉列表，3、复选下拉列表',
  `target_html_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `html_value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `add_user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status_code` tinyint(4) NULL DEFAULT NULL COMMENT '状态，对应数据字典表（dictionary）中的编码字段（code）1：新建（新建状态可编辑，使用后无法编辑），2：启用，3：禁用',
  `status_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '指标数据' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_target_type
-- ----------------------------
DROP TABLE IF EXISTS `da_target_type`;
CREATE TABLE `da_target_type`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `target_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `constant_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `priority` int(11) NULL DEFAULT NULL COMMENT '排序',
  `add_user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '指标类型' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_template
-- ----------------------------
DROP TABLE IF EXISTS `da_template`;
CREATE TABLE `da_template`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `serial_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `service_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '业务类型,对应数据字典表（dictionary）中的编码字段（code）1：产业，2：统计',
  `service_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `template_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '指标模板表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_template_data
-- ----------------------------
DROP TABLE IF EXISTS `da_template_data`;
CREATE TABLE `da_template_data`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `template_target_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `add_user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `audit_status_code` tinyint(4) NULL DEFAULT NULL COMMENT '审核状态：对应数据字典表（dictionary）中的编码字段（code）0：待审核，1:审核通过，2：审核不通过',
  `audit_status_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `auditer_suggestion` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `auditer_user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '模板数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_user_contribution
-- ----------------------------
DROP TABLE IF EXISTS `da_user_contribution`;
CREATE TABLE `da_user_contribution`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `price_times` int(11) NULL DEFAULT NULL COMMENT '价格上传次数',
  `price_num` int(11) NULL DEFAULT NULL COMMENT '价格上传数量',
  `yield_times` int(11) NULL DEFAULT NULL COMMENT '产量上传次数',
  `yield_num` int(11) NULL DEFAULT NULL COMMENT '产量上传数量',
  `farm_oper_times` int(11) NULL DEFAULT NULL COMMENT '农事操作上传次数',
  `farm_oper_num` int(11) NULL DEFAULT NULL COMMENT '农事操作上传数量',
  `user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户贡献' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for da_wholesale_market
-- ----------------------------
DROP TABLE IF EXISTS `da_wholesale_market`;
CREATE TABLE `da_wholesale_market`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `longitude` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `latitude` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cover_area` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `merchants_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stalls_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `days_volume` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `years_amount` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '批发市场' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for dar_enterprise_resource
-- ----------------------------
DROP TABLE IF EXISTS `dar_enterprise_resource`;
CREATE TABLE `dar_enterprise_resource`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `enterprise_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type_code` tinyint(4) NULL DEFAULT NULL COMMENT '资源用途：对应数据字典表（dictionary）中的编码字段（code） 1：推介轮播，2，企业logo及资质证书，3、视频介绍',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业和资源中间表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for dar_template_target
-- ----------------------------
DROP TABLE IF EXISTS `dar_template_target`;
CREATE TABLE `dar_template_target`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `template_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `target_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `priority` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '模板指标中间表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ds_gp_menu
-- ----------------------------
DROP TABLE IF EXISTS `ds_gp_menu`;
CREATE TABLE `ds_gp_menu`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `domain_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用领域。外键，引用应用领域表（domain）的主键。',
  `module_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '功能模块。外键，引用应用功能模块表（module）的主键。',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称。',
  `page_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接页面。外键，引用系统页面表（page）的主键。',
  `page_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接页面。路径，和系统页面表（page）的路径（url）对应。',
  `priority` int(11) NULL DEFAULT NULL COMMENT '排序字段。',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注字段。',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间。',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录最后一次修改时间。',
  `icon_class` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标样式。',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '链接菜单。已废弃，功能集成到gp_module。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ds_gpr_user_icon
-- ----------------------------
DROP TABLE IF EXISTS `ds_gpr_user_icon`;
CREATE TABLE `ds_gpr_user_icon`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统用户。外键，引用系统用户（user）表的主键',
  `resource_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像。外键，引用资源（resource）表的主键',
  `is_default_code` tinyint(4) NULL DEFAULT NULL COMMENT '是否为默认头像。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户头像。已废弃，改用gpr_resource' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_catalog_interface
-- ----------------------------
DROP TABLE IF EXISTS `gp_catalog_interface`;
CREATE TABLE `gp_catalog_interface`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别名称。',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别编码。非空唯一，十位字符串，0000000000，可继续扩展，每两位代表一级编码，目前最多支持到五级分类。',
  `level` tinyint(4) NULL DEFAULT NULL COMMENT '级别。类别在层级关系中所属等级。',
  `farther_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级编码。父级类别编码，顶级类别的父级编码为0000000000。',
  `category_code` tinyint(4) NULL DEFAULT NULL COMMENT '分类方式。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义两种分类方式：1按业务分类，2按请求方式分类。',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注。',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '接口分类字典。存放接口分类信息，支持树形分级分类，主要但不限于业务上的分类方式，支持同时对接口进行多种分类。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_control
-- ----------------------------
DROP TABLE IF EXISTS `gp_control`;
CREATE TABLE `gp_control`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `domain_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用领域。外键，引用应用领域表（domain）的主键。',
  `page_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属页面。外键，引用系统页面表（page）的主键（id）。',
  `page_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属页面。路径，和系统页面表（page）的路径（url）对应。',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '控件名称。',
  `serial_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '控件编号。',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注字段。',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间。',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录最后一次修改时间。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gp_control_gp_domain`(`domain_id`) USING BTREE,
  INDEX `fk_gp_control_gp_page`(`page_id`) USING BTREE,
  CONSTRAINT `fk_gp_control_gp_domain` FOREIGN KEY (`domain_id`) REFERENCES `gp_domain` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_gp_control_gp_page` FOREIGN KEY (`page_id`) REFERENCES `gp_page` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统控件。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `gp_dictionary`;
CREATE TABLE `gp_dictionary`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `type_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '外键，对应字典类型表(dictionary)主键',
  `code` tinyint(4) NULL DEFAULT NULL COMMENT '字典编码',
  `text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `priority` int(11) NULL DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gp_dictionary_gp_dictionary_type`(`type_id`) USING BTREE,
  CONSTRAINT `fk_gp_dictionary_gp_dictionary_type` FOREIGN KEY (`type_id`) REFERENCES `gp_dictionary_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典信息。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_dictionary_type
-- ----------------------------
DROP TABLE IF EXISTS `gp_dictionary_type`;
CREATE TABLE `gp_dictionary_type`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `constant_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '常量名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_domain
-- ----------------------------
DROP TABLE IF EXISTS `gp_domain`;
CREATE TABLE `gp_domain`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `serial_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '领域编号。',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '领域名称。',
  `com` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '领域域名。',
  `icon_resource` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '领域图标。',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注字段。',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间。',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录最后一次修改时间。',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '应用领域。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_early_warning
-- ----------------------------
DROP TABLE IF EXISTS `gp_early_warning`;
CREATE TABLE `gp_early_warning`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `growth_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生育期id',
  `growth_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生育期名称',
  `begin_date` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生育期开始时间，如3-1',
  `end_date` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生育期结束时间，如3-30',
  `warning_type` int(10) NULL DEFAULT NULL COMMENT '预警指标类型',
  `warning_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预警指标名称',
  `max_val` double(10, 2) NULL DEFAULT NULL COMMENT '最大阀值,默认阀值',
  `min_val` double(10, 2) NULL DEFAULT NULL COMMENT '最小阀值',
  `tips_mess` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提醒信息',
  `stutas_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应字典表状态code',
  `stutas_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应字典表状态text',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '预警阀值表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_interface
-- ----------------------------
DROP TABLE IF EXISTS `gp_interface`;
CREATE TABLE `gp_interface`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `serial_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接口编号。',
  `domain_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用领域。外键，引用应用领域表（domain）的主键。',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口名称。',
  `table_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作主表。',
  `is_public_code` tinyint(4) NULL DEFAULT NULL COMMENT '是否为公共接口。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值1。',
  `type_code` tinyint(4) NULL DEFAULT NULL COMMENT '接口调用方式。对应数据字典表（dictionary）中的编码字段（code）。目前两种类型：1GET，2POST。',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '访问路径。',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注字段。',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间。',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录最后一次修改时间。',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ak_uq_url`(`url`) USING BTREE,
  INDEX `fk_gp_interface_gp_domain`(`domain_id`) USING BTREE,
  CONSTRAINT `fk_gp_interface_gp_domain` FOREIGN KEY (`domain_id`) REFERENCES `gp_domain` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统接口。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_login_log
-- ----------------------------
DROP TABLE IF EXISTS `gp_login_log`;
CREATE TABLE `gp_login_log`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `domain_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用领域。外键，引用应用领域表（domain）的主键。',
  `user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统用户。外键，引用系统用户表（user）的主键。',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录用户名。',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '登录时间。',
  `logout_time` datetime(0) NULL DEFAULT NULL COMMENT '退出时间。',
  `logout_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '退出方式。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，四种类型：0其它，1Token过期，2主动退出，3被动退出。',
  `duration` int(11) NULL DEFAULT NULL COMMENT '登录时长。单位秒。',
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址。',
  `ip_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP归属地。',
  `browser` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器版本。',
  `resolution` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '屏幕分辨率。',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统。',
  `is_success_code` tinyint(4) NULL DEFAULT NULL COMMENT '登录是否成功。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注字段。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gp_login_log_gp_domain`(`domain_id`) USING BTREE,
  INDEX `fk_gp_login_log_gp_user`(`user_id`) USING BTREE,
  CONSTRAINT `fk_gp_login_log_gp_domain` FOREIGN KEY (`domain_id`) REFERENCES `gp_domain` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_gp_login_log_gp_user` FOREIGN KEY (`user_id`) REFERENCES `gp_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录日志。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_message
-- ----------------------------
DROP TABLE IF EXISTS `gp_message`;
CREATE TABLE `gp_message`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息创建者。外键，引用系统用户表（user）的主键。',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息创建者。登录名称，和系统用户表（user）的登录名称（user_name）对应。',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '消息内容。',
  `type_code` tinyint(4) NULL DEFAULT NULL COMMENT '消息类型。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义4种类型：0其它，1公告，2提醒，3私信。如果消息类型是公告，在用户读取消息的时候向消息队列表（gpr_message_user）插入数据；如果消息类型是私信和提醒，则新建消息后立即向消息列表（gpr_message_use）中插入数据。',
  `type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息类型。文本，对应数据字典表（dictionary）中的文本字段（text）。目前先定义4种类型：0其它，1公告，2提醒，3私信。如果消息类型是公告，在用户读取消息的时候向消息队列表（gpr_message_user）插入数据；如果消息类型是私信和提醒，则新建消息后立即向消息列表（gpr_message_use）中插入数据。',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注字段。',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gp_message_gp_user`(`user_id`) USING BTREE,
  CONSTRAINT `fk_gp_message_gp_user` FOREIGN KEY (`user_id`) REFERENCES `gp_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统消息。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_module
-- ----------------------------
DROP TABLE IF EXISTS `gp_module`;
CREATE TABLE `gp_module`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `domain_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用领域。外键，引用应用领域表（domain）的主键。',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块名称。',
  `serial_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块编号。',
  `level_code` tinyint(4) NULL DEFAULT NULL COMMENT '模块级别。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义两种级别：1第一级，2第二级。',
  `level_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块级别。文本，对应数据字典表（dictionary）中的文本字段（text）。目前先定义两种级别：1第一级，2第二级。',
  `farther_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级模块。外键，引用自身功能模块表（module）的主键。',
  `priority` int(11) NULL DEFAULT NULL COMMENT '排序字段。',
  `page_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接页面。外键，引用系统页面表（page）的主键。',
  `page_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接页面。路径，和系统页面表（page）的路径（url）对应。',
  `style` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单样式。',
  `icon_resource` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标。',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注字段。',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间。',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录最后一次修改时间。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gp_module_gp_domain`(`domain_id`) USING BTREE,
  INDEX `fk_gp_module_gp_module`(`farther_id`) USING BTREE,
  CONSTRAINT `fk_gp_module_gp_domain` FOREIGN KEY (`domain_id`) REFERENCES `gp_domain` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_gp_module_gp_module` FOREIGN KEY (`farther_id`) REFERENCES `gp_module` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '功能模块。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `gp_oper_log`;
CREATE TABLE `gp_oper_log`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `domain_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用领域。外键，引用应用领域表（domain）的主键。',
  `object_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作记录主键。只记录单条记录操作时的主键，暂不考虑记录针对多条记录操作的主键列表。',
  `oper_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '操作类型。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义9种类型：0其它，1添加记录，2批量添加，3删除记录，4批量删除，5修改记录，6单条查询，7模糊查询，8自定义查询。',
  `oper_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作类型。文本，对应数据字典表（dictionary）中的文本字段（text）。目前先定义9种类型：0其它，1添加记录，2批量添加，3删除记录，4批量删除，5修改记录，6单条查询，7模糊查询，8自定义查询。',
  `table_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作表名。只记录核心表名，暂不考虑记录操作动作涉及的所有表名列表。',
  `total_count` bigint(20) NULL DEFAULT NULL COMMENT '记录总数。',
  `income_value` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '传入参数。记录调用BLL层方法时传入的参数值，对象的话序列化成JSON字符串保存。',
  `return_value` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '返回值。记录调用BLL层方法时返回的参数值，对象的话序列化成JSON字符串保存。',
  `is_success_code` tinyint(4) NULL DEFAULT NULL COMMENT '操作是否成功。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。 ',
  `result_code` int(11) NULL DEFAULT NULL COMMENT '操作结果编码。操作结果和一套编码表对应，暂不进行具体设计，只是在程序根据现有代码设计给出了一部分编码规则。',
  `result_message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '提示信息。BLL层方法执行后返回给调用者的提示信息。',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注字段。',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gp_oper_log_gp_domain`(`domain_id`) USING BTREE,
  CONSTRAINT `fk_gp_oper_log_gp_domain` FOREIGN KEY (`domain_id`) REFERENCES `gp_domain` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_oper_log_login
-- ----------------------------
DROP TABLE IF EXISTS `gp_oper_log_login`;
CREATE TABLE `gp_oper_log_login`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `oper_log_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作日志。外键，引用操作日志表（oper_log）表的主键。',
  `login_log_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属登录。外键，引用登录日志表（login_log)的主键。',
  `user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属用户。外键，引用系统用户表（user）的主键。',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属用户 。登录名称，和系统用户表（user）的登录名称（user_name）对应。',
  `token_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属Token。外键，引用Token信息表（token）的主键。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gp_oper_log_login_gp_login_log`(`login_log_id`) USING BTREE,
  INDEX `fk_gp_oper_log_login_gp_oper_log`(`oper_log_id`) USING BTREE,
  INDEX `fk_gp_oper_log_login_gp_user`(`user_id`) USING BTREE,
  INDEX `fk_gp_oper_log_login_gp_token`(`token_id`) USING BTREE,
  CONSTRAINT `fk_gp_oper_log_login_gp_login_log` FOREIGN KEY (`login_log_id`) REFERENCES `gp_login_log` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_gp_oper_log_login_gp_oper_log` FOREIGN KEY (`oper_log_id`) REFERENCES `gp_oper_log` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_gp_oper_log_login_gp_token` FOREIGN KEY (`token_id`) REFERENCES `gp_token` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_gp_oper_log_login_gp_user` FOREIGN KEY (`user_id`) REFERENCES `gp_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录用户操作日志。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_organization
-- ----------------------------
DROP TABLE IF EXISTS `gp_organization`;
CREATE TABLE `gp_organization`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `serial_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号。',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称。',
  `short_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简称。',
  `type_code` tinyint(4) NULL DEFAULT NULL COMMENT '类型。对应数据字典表（dictionary）中的编码字段（code）。目前七种类型：1县政府、2省市双管单位、3镇政府、4合作社、5生产企业、6加工企业、7销售企业,8物流企业。',
  `type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型。对应数据字典表（dictionary）中的文本字段（text）。目前七种类型：1县政府、2省市双管单位、3镇政府、4合作社、5生产企业、6加工企业、7销售企业。',
  `level_code` tinyint(4) NULL DEFAULT NULL COMMENT '模块级别。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义两种级别：1第一级，2第二级。',
  `level_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块级别。文本，对应数据字典表（dictionary）中的文本字段（text）。目前先定义两种级别：1第一级，2第二级。',
  `farther_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级组织机构。外键，引用自身组织机构表（module）的主键。',
  `priority` int(11) NULL DEFAULT NULL COMMENT '排序字段。',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码。',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱。',
  `fax` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '传真。',
  `postcode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮编。',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址。',
  `responsibility` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构职能。',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注字段。',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间。',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录最后一次修改时间。',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ak_uq__id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织机构。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_page
-- ----------------------------
DROP TABLE IF EXISTS `gp_page`;
CREATE TABLE `gp_page`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `domain_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用领域。外键，引用应用领域表（domain）的主键。',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '页面名称。',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存放路径。',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注字段。',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间。',
  `is_public_code` tinyint(4) NULL DEFAULT NULL COMMENT '是否为公共页面。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值1。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gp_page_gp_domain`(`domain_id`) USING BTREE,
  CONSTRAINT `fk_gp_page_gp_domain` FOREIGN KEY (`domain_id`) REFERENCES `gp_domain` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统页面。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_region
-- ----------------------------
DROP TABLE IF EXISTS `gp_region`;
CREATE TABLE `gp_region`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区编码。',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区名称。',
  `category` tinyint(4) NULL DEFAULT NULL COMMENT '地区类别字典，用于区分Region表中记录的类型。0是国家，1是直辖市，2是省，3是自治区，4是特别行政区，5是地级市，6是直辖市下的区，7是县级市（县级市不好区分，可以都暂时默认为7），8是县。',
  `farther_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级地区，引用父级行政机构的主键。',
  `is_display_code` tinyint(4) NULL DEFAULT NULL COMMENT '是否显示。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。',
  `region_level` tinyint(4) NULL DEFAULT NULL COMMENT '区域等级。国家为第一级，直辖市、省、自治区、特别行政区为第二级，地级市、直辖市下的区为第三级，县级市、县为第四级、乡镇为第五级',
  `longitude` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区经度。',
  `latitude` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区纬度。',
  `area` int(11) NULL DEFAULT NULL COMMENT '区域面积大小，单位平方公里（ SquareKilometer sq . km ）。',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注字段。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ak_uq_code`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '地区信息。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_region_country
-- ----------------------------
DROP TABLE IF EXISTS `gp_region_country`;
CREATE TABLE `gp_region_country`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `iso_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国家编码。使用ISO-3166-1标准中的Alpha3编码。',
  `chinese_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文名称。',
  `english_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名称。',
  `abbreviation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简称',
  `is_display_code` tinyint(4) NULL DEFAULT NULL COMMENT '是否显示。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。',
  `longitude` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区经度。',
  `latitude` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区纬度。',
  `area` int(11) NULL DEFAULT NULL COMMENT '国家面积。单位平方公里（ SquareKilometer sq . km ）。',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注字段。',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ak_uq_iso_code`(`iso_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '地区信息。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_resource
-- ----------------------------
DROP TABLE IF EXISTS `gp_resource`;
CREATE TABLE `gp_resource`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `domain_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用领域。外键，引用应用领域表（domain）的主键。',
  `module_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属功能模块。外键，引用应用功能模块表（module）的主键。',
  `page_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属页面。外键，引用应用页面表（page）的主键。',
  `user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件创建者。外键，引用应用系统用户表（user）的主键。',
  `original_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源原始名称',
  `new_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名称',
  `extension` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源类型，后缀名，统一小写。',
  `path` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源路径',
  `priority` int(11) NULL DEFAULT NULL COMMENT '排列顺序',
  `size` bigint(20) NOT NULL DEFAULT 0 COMMENT '资源大小，单位字节。',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gp_resource_gp_module`(`module_id`) USING BTREE,
  INDEX `fk_gp_resource_gp_page`(`page_id`) USING BTREE,
  INDEX `fk_gp_resource_gp_domain`(`domain_id`) USING BTREE,
  CONSTRAINT `fk_gp_resource_gp_domain` FOREIGN KEY (`domain_id`) REFERENCES `gp_domain` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_gp_resource_gp_module` FOREIGN KEY (`module_id`) REFERENCES `gp_module` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_gp_resource_gp_page` FOREIGN KEY (`page_id`) REFERENCES `gp_page` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件信息。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_role
-- ----------------------------
DROP TABLE IF EXISTS `gp_role`;
CREATE TABLE `gp_role`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称。',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注字段。',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间。',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录最后一次修改时间。',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ak_uq_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_station
-- ----------------------------
DROP TABLE IF EXISTS `gp_station`;
CREATE TABLE `gp_station`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `serial_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号。',
  `organization_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属组织机构。外键，引用系统组织机构表（organization）的主键。',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称。',
  `responsibility` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构职能。',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注字段。',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间。',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录最后一次修改时间。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gp_station_gp_organization`(`organization_id`) USING BTREE,
  CONSTRAINT `fk_gp_station_gp_organization` FOREIGN KEY (`organization_id`) REFERENCES `gp_organization` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_token
-- ----------------------------
DROP TABLE IF EXISTS `gp_token`;
CREATE TABLE `gp_token`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `domain_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用领域。外键，引用应用领域表（domain）的主键。',
  `user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属用户。外键，引用系统用户表（user）的主键。',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属用户 。登录名称，和系统用户表（user）的登录名称（user_name）对应。',
  `access_token` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'access_token。唯一字段。',
  `a_dead_time` datetime(0) NULL DEFAULT NULL COMMENT 'access_token过期时间。',
  `refresh_token` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'refresh_token。唯一字段。',
  `r_dead_time` datetime(0) NULL DEFAULT NULL COMMENT 'refresh_token过期时间。',
  `secret` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '动态密钥。',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注字段。',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间。',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录最后一次修改时间。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gp_token_gp_domain`(`domain_id`) USING BTREE,
  INDEX `fk_gp_token_gp_user`(`user_id`) USING BTREE,
  CONSTRAINT `fk_gp_token_gp_domain` FOREIGN KEY (`domain_id`) REFERENCES `gp_domain` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_gp_token_gp_login_log` FOREIGN KEY (`id`) REFERENCES `gp_login_log` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_gp_token_gp_user` FOREIGN KEY (`user_id`) REFERENCES `gp_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'token信息。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_user
-- ----------------------------
DROP TABLE IF EXISTS `gp_user`;
CREATE TABLE `gp_user`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录账号。',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录密码。',
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名。',
  `gender_code` tinyint(4) NULL DEFAULT NULL COMMENT '性别。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0男，1女。',
  `birth_time` datetime(0) NULL DEFAULT NULL COMMENT '出生日期。',
  `age` tinyint(4) NULL DEFAULT NULL COMMENT '年龄。',
  `is_marriage_code` tinyint(4) NULL DEFAULT NULL COMMENT '是否已婚。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码。',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱。',
  `qq` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ号',
  `icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像图片存放路径。',
  `register_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册IP。',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间。',
  `last_login_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后登录IP。',
  `login_count` int(11) NULL DEFAULT 0 COMMENT '登录次数。',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注字段。',
  `is_admin_code` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否管理员。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。',
  `is_disabled_code` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否禁用。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间。',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录最后一次修改时间。',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ak_uq_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_value_location
-- ----------------------------
DROP TABLE IF EXISTS `gp_value_location`;
CREATE TABLE `gp_value_location`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `column_value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值。',
  `origin_table_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原始表名',
  `target_column_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值所在列名。',
  `target_table_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值所在表名。',
  `target_record_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值所属记录主键。',
  `remarks` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注。',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ak_uq_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '调用存储过程查询某个值在本数据库中的位置，记录相关信息到本表中。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gp_variety
-- ----------------------------
DROP TABLE IF EXISTS `gp_variety`;
CREATE TABLE `gp_variety`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品种名称',
  `parent_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级id',
  `relation_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关系id/唯一code',
  `describes` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `resource_id` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应附件表id',
  `resource_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应附件表url',
  `stutas_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应字典表状态code',
  `stutas_text` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应字典表状态text',
  `cycle_time` int(11) NULL DEFAULT NULL COMMENT '时间周期,单位为天',
  `priority` int(11) NULL DEFAULT NULL COMMENT '排序字段',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注字段',
  `add_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '品种表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gpr_catalog_interface
-- ----------------------------
DROP TABLE IF EXISTS `gpr_catalog_interface`;
CREATE TABLE `gpr_catalog_interface`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `catalog_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口分类。外键，引用接口分类字典表（catalog_interface）的主键。',
  `interface_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统接口。外键，引用系统接口表（interface）的主键。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gpr_domain_message_gp_message`(`interface_id`) USING BTREE,
  INDEX `fk_gpr_domain_message_gp_domain`(`catalog_id`) USING BTREE,
  CONSTRAINT `gpr_catalog_interface_ibfk_1` FOREIGN KEY (`catalog_id`) REFERENCES `gp_domain` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `gpr_catalog_interface_ibfk_2` FOREIGN KEY (`interface_id`) REFERENCES `gp_message` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台接口所属分类。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gpr_domain_message
-- ----------------------------
DROP TABLE IF EXISTS `gpr_domain_message`;
CREATE TABLE `gpr_domain_message`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `domain_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用领域。外键，引用应用领域表（domain）的主键。',
  `message_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '领域消息。外键，引用站内信（message）的主键。',
  `enterprise_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业id。外键，引用企业信息表（da_enterprise_info）的主键。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gpr_domain_message_gp_message`(`message_id`) USING BTREE,
  INDEX `fk_gpr_domain_message_gp_domain`(`domain_id`) USING BTREE,
  CONSTRAINT `fk_gpr_domain_message_gp_domain` FOREIGN KEY (`domain_id`) REFERENCES `gp_domain` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_gpr_domain_message_gp_message` FOREIGN KEY (`message_id`) REFERENCES `gp_message` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '应用领域的站内信。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gpr_domain_user
-- ----------------------------
DROP TABLE IF EXISTS `gpr_domain_user`;
CREATE TABLE `gpr_domain_user`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `domain_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用领域。外键，引用应用领域表（domain）的主键。',
  `user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统用户。外键，引用系统用户表（user）的主键。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gpr_domain_user_gp_user`(`user_id`) USING BTREE,
  INDEX `fk_gpr_domain_user_gp_domain`(`domain_id`) USING BTREE,
  CONSTRAINT `fk_gpr_domain_user_gp_domain` FOREIGN KEY (`domain_id`) REFERENCES `gp_domain` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_gpr_domain_user_gp_user` FOREIGN KEY (`user_id`) REFERENCES `gp_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '应用领域拥有的用户。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gpr_message_user
-- ----------------------------
DROP TABLE IF EXISTS `gpr_message_user`;
CREATE TABLE `gpr_message_user`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `message_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息内容。外键，引用站内信（message）的主键。',
  `user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息接收者。外键，引用系统用户表（user）的主键。',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息接收者。登录名称，和系统用户表（user）的登录名称（user_name）对应。',
  `is_read_code` tinyint(4) NULL DEFAULT NULL COMMENT '是否已读。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。 ',
  `read_time` datetime(0) NULL DEFAULT NULL COMMENT '消息接收方读取时间。',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gpr_message_user_gp_message`(`message_id`) USING BTREE,
  INDEX `fk_gpr_message_user_gp_user`(`user_id`) USING BTREE,
  CONSTRAINT `fk_gpr_message_user_gp_message` FOREIGN KEY (`message_id`) REFERENCES `gp_message` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_gpr_message_user_gp_user` FOREIGN KEY (`user_id`) REFERENCES `gp_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '消息队列。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gpr_module_page
-- ----------------------------
DROP TABLE IF EXISTS `gpr_module_page`;
CREATE TABLE `gpr_module_page`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `module_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '功能模块。外键，引用功能模块表（module）的主键。',
  `page_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统页面。外键，引用应用系统页面表（page）的主键。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gpr_module_page_gp_page`(`page_id`) USING BTREE,
  INDEX `fk_gpr_module_page_gp_module`(`module_id`) USING BTREE,
  CONSTRAINT `fk_gpr_module_page_gp_module` FOREIGN KEY (`module_id`) REFERENCES `gp_module` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_gpr_module_page_gp_page` FOREIGN KEY (`page_id`) REFERENCES `gp_page` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '功能模块所包含的页面。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gpr_resource
-- ----------------------------
DROP TABLE IF EXISTS `gpr_resource`;
CREATE TABLE `gpr_resource`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `business_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务表。外键，引用业务表的主键',
  `resource_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件。外键，引用资源（resource）表的主键',
  `is_default` tinyint(4) NULL DEFAULT NULL COMMENT '是否为默认。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gpr_resource_gp_resource`(`resource_id`) USING BTREE,
  CONSTRAINT `fk_gpr_resource_gp_resource` FOREIGN KEY (`resource_id`) REFERENCES `gp_resource` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '附件关联表。只要存有附件字段的表，都会通过此表于gp_resource表关联。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gpr_role_control
-- ----------------------------
DROP TABLE IF EXISTS `gpr_role_control`;
CREATE TABLE `gpr_role_control`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `role_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统角色。外键，引用应用角色表（role）的主键。',
  `control_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统控件。外键，引用系统控件表（control）的主键。',
  `is_enable_code` tinyint(4) NULL DEFAULT 0 COMMENT '是否有权。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gpr_role_control_gp_control`(`control_id`) USING BTREE,
  INDEX `fk_gpr_role_control_gp_role`(`role_id`) USING BTREE,
  CONSTRAINT `fk_gpr_role_control_gp_control` FOREIGN KEY (`control_id`) REFERENCES `gp_control` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_gpr_role_control_gp_role` FOREIGN KEY (`role_id`) REFERENCES `gp_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色拥有的控件权限。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gpr_role_domain
-- ----------------------------
DROP TABLE IF EXISTS `gpr_role_domain`;
CREATE TABLE `gpr_role_domain`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `role_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统角色。外键，引用应用角色表（role）的主键。',
  `domain_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用领域。外键，引用应用领域表（domain）的主键。',
  `is_enable_code` tinyint(4) NULL DEFAULT 0 COMMENT '是否有权。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gpr_role_domain_gp_domain`(`domain_id`) USING BTREE,
  INDEX `fk_gpr_role_domain_gp_role`(`role_id`) USING BTREE,
  CONSTRAINT `fk_gpr_role_domain_gp_domain` FOREIGN KEY (`domain_id`) REFERENCES `gp_domain` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_gpr_role_domain_gp_role` FOREIGN KEY (`role_id`) REFERENCES `gp_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色拥有的功能模块权限。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gpr_role_interface
-- ----------------------------
DROP TABLE IF EXISTS `gpr_role_interface`;
CREATE TABLE `gpr_role_interface`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `role_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统角色。外键，引用系统角色表（role）的主键。',
  `interface_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统接口。外键，引用系统接口表（interface）的主键。',
  `is_enable_code` tinyint(4) NULL DEFAULT 0 COMMENT '是否有权。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gpr_role_interface_gp_interface`(`interface_id`) USING BTREE,
  INDEX `fk_gpr_role_interface_gp_role`(`role_id`) USING BTREE,
  CONSTRAINT `fk_gpr_role_interface_gp_interface` FOREIGN KEY (`interface_id`) REFERENCES `gp_interface` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_gpr_role_interface_gp_role` FOREIGN KEY (`role_id`) REFERENCES `gp_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色拥有的接口权限。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gpr_role_module
-- ----------------------------
DROP TABLE IF EXISTS `gpr_role_module`;
CREATE TABLE `gpr_role_module`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `role_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统角色。外键，引用应用角色表（role）的主键。',
  `module_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '功能模块。外键，引用功能模块表（module）的主键。',
  `is_enable_code` tinyint(4) NULL DEFAULT 0 COMMENT '是否有权。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gpr_role_module_gp_module`(`module_id`) USING BTREE,
  INDEX `fk_gpr_role_module_gp_role`(`role_id`) USING BTREE,
  CONSTRAINT `fk_gpr_role_module_gp_module` FOREIGN KEY (`module_id`) REFERENCES `gp_module` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_gpr_role_module_gp_role` FOREIGN KEY (`role_id`) REFERENCES `gp_role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色拥有的功能模块权限。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gpr_role_page
-- ----------------------------
DROP TABLE IF EXISTS `gpr_role_page`;
CREATE TABLE `gpr_role_page`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `role_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统角色。外键，引用应用角色表（role）的主键。',
  `page_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统页面。外键，引用应用系统页面表（page）的主键。',
  `is_enable_code` tinyint(4) NULL DEFAULT 0 COMMENT '是否有权。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gpr_role_page_gp_page`(`page_id`) USING BTREE,
  INDEX `fk_gpr_role_page_gp_role`(`role_id`) USING BTREE,
  CONSTRAINT `fk_gpr_role_page_gp_page` FOREIGN KEY (`page_id`) REFERENCES `gp_page` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_gpr_role_page_gp_role` FOREIGN KEY (`role_id`) REFERENCES `gp_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色拥有的页面权限。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gpr_user_base
-- ----------------------------
DROP TABLE IF EXISTS `gpr_user_base`;
CREATE TABLE `gpr_user_base`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统用户。外键，引用系统用户表（gp_user）的主键。',
  `base_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '基地。外键，引用应用基地信息表（da_base_info）的主键。',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户归属的基地。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gpr_user_organization
-- ----------------------------
DROP TABLE IF EXISTS `gpr_user_organization`;
CREATE TABLE `gpr_user_organization`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统用户。外键，引用系统用户表（user）的主键。',
  `organization_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户组织机构。外键，引用组织机构表（organization）的主键。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gpr_user_organization_gp_user`(`user_id`) USING BTREE,
  INDEX `fk_gpr_user_organization_gp_organization`(`organization_id`) USING BTREE,
  CONSTRAINT `fk_gpr_user_organization_gp_organization` FOREIGN KEY (`organization_id`) REFERENCES `gp_organization` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_gpr_user_organization_gp_user` FOREIGN KEY (`user_id`) REFERENCES `gp_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户所属组织机构。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gpr_user_role
-- ----------------------------
DROP TABLE IF EXISTS `gpr_user_role`;
CREATE TABLE `gpr_user_role`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统用户。外键，引用系统用户表（user）的主键。',
  `role_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户角色。外键，引用应用角色表（role）的主键。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gpr_user_role_gp_user`(`user_id`) USING BTREE,
  INDEX `fk_gpr_user_role_gp_role`(`role_id`) USING BTREE,
  CONSTRAINT `fk_gpr_user_role_gp_role` FOREIGN KEY (`role_id`) REFERENCES `gp_role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_gpr_user_role_gp_user` FOREIGN KEY (`user_id`) REFERENCES `gp_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户拥有的角色。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gpr_user_station
-- ----------------------------
DROP TABLE IF EXISTS `gpr_user_station`;
CREATE TABLE `gpr_user_station`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键。',
  `user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统用户。外键，引用系统用户表（user）的主键。',
  `station_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户岗位。外键，引用岗位表（station）的主键。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_gpr_user_station_gp_station`(`station_id`) USING BTREE,
  INDEX `fk_gpr_user_station_gp_user`(`user_id`) USING BTREE,
  CONSTRAINT `fk_gpr_user_station_gp_station` FOREIGN KEY (`station_id`) REFERENCES `gp_station` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_gpr_user_station_gp_user` FOREIGN KEY (`user_id`) REFERENCES `gp_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户所属岗位。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mf_formatrix
-- ----------------------------
DROP TABLE IF EXISTS `mf_formatrix`;
CREATE TABLE `mf_formatrix`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `region_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price_change` decimal(20, 6) NULL DEFAULT NULL COMMENT '价格涨跌,单位：元/千克',
  `return_change` decimal(20, 6) NULL DEFAULT NULL COMMENT '价格变化率',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '波士顿矩阵' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mf_forprice
-- ----------------------------
DROP TABLE IF EXISTS `mf_forprice`;
CREATE TABLE `mf_forprice`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `date_time` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `actual_amount` decimal(20, 6) NULL DEFAULT NULL COMMENT '实际价格,单位：元/千克',
  `forecast_amount` decimal(20, 6) NULL DEFAULT NULL COMMENT '预测价格,单位：元/千克',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '价格预测' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mf_forreturn
-- ----------------------------
DROP TABLE IF EXISTS `mf_forreturn`;
CREATE TABLE `mf_forreturn`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `date_time` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `actual_amount` decimal(20, 6) NULL DEFAULT NULL COMMENT '价格波动性，实际',
  `forecast_amount` decimal(20, 6) NULL DEFAULT NULL COMMENT '价格波动性，预测',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_date_time_1`(`date_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '批发价格波动性预测' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mf_fresh_volume
-- ----------------------------
DROP TABLE IF EXISTS `mf_fresh_volume`;
CREATE TABLE `mf_fresh_volume`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `date_time` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `actual_amount` decimal(20, 6) NULL DEFAULT NULL COMMENT '实际销售金额，单位：元',
  `forecast_amount` decimal(20, 6) NULL DEFAULT NULL COMMENT '预测销售金额，单位：元',
  `actual_volume` decimal(20, 6) NULL DEFAULT NULL COMMENT '实际销售量，单位：千克',
  `forecast_volume` decimal(20, 6) NULL DEFAULT NULL COMMENT '预测销售量，单位：千克',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_date_time_1`(`date_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mf_hp_fluct
-- ----------------------------
DROP TABLE IF EXISTS `mf_hp_fluct`;
CREATE TABLE `mf_hp_fluct`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `date_time` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dately_price` decimal(20, 6) NULL DEFAULT NULL COMMENT '价格，单位：元/千克',
  `trend` decimal(20, 6) NULL DEFAULT NULL COMMENT '长期趋势',
  `undulation` decimal(20, 6) NULL DEFAULT NULL COMMENT '短周期波动',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_date_time_1`(`date_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '批发价格周期性波动规律分析' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mf_hp_season
-- ----------------------------
DROP TABLE IF EXISTS `mf_hp_season`;
CREATE TABLE `mf_hp_season`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `date_time` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `undulation` decimal(20, 6) NULL DEFAULT NULL COMMENT '规律',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_date_time_1`(`date_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '批发价格季节性波动规律分析' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mf_input_subject
-- ----------------------------
DROP TABLE IF EXISTS `mf_input_subject`;
CREATE TABLE `mf_input_subject`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type_input` tinyint(4) NULL DEFAULT NULL COMMENT '投入品主体种类',
  `punish` tinyint(4) NULL DEFAULT NULL COMMENT '是否被处罚，0没处罚，1处罚',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '投入品主体监管表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mf_input_type
-- ----------------------------
DROP TABLE IF EXISTS `mf_input_type`;
CREATE TABLE `mf_input_type`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `issue` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `quality_threshold` int(11) NULL DEFAULT NULL COMMENT '质量检测阀值',
  `type_input` tinyint(4) NULL DEFAULT NULL COMMENT '投入品种类',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '投入品种类监管' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mf_per_unit_yield
-- ----------------------------
DROP TABLE IF EXISTS `mf_per_unit_yield`;
CREATE TABLE `mf_per_unit_yield`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `actual_volume` decimal(20, 6) NULL DEFAULT NULL COMMENT '实际值，单位：千克/亩',
  `forecast_volume` decimal(20, 6) NULL DEFAULT NULL COMMENT '预测值，单位：千克/亩',
  `date_time` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_date_time_1`(`date_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '单产预测建模表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mf_prewarning_value
-- ----------------------------
DROP TABLE IF EXISTS `mf_prewarning_value`;
CREATE TABLE `mf_prewarning_value`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `entry_violation` int(11) NULL DEFAULT NULL COMMENT '违法案件月预警值',
  `quality_feedback` int(11) NULL DEFAULT NULL COMMENT '投诉反馈月预警值',
  `agricultural_capital_punishment` int(11) NULL DEFAULT NULL COMMENT '农资处罚月预警值',
  `quality_spot_check_disqualification` int(11) NULL DEFAULT NULL COMMENT '质量抽检不合格月预警值',
  `creator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '质量安全预警值设置' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mf_process_material_consume
-- ----------------------------
DROP TABLE IF EXISTS `mf_process_material_consume`;
CREATE TABLE `mf_process_material_consume`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `actual_amount` decimal(20, 6) NULL DEFAULT NULL COMMENT '鲜果消耗量实际值，单位：万吨',
  `forecast_amount` decimal(20, 6) NULL DEFAULT NULL COMMENT '鲜果消耗量预测值，单位：万吨',
  `scale_actual` decimal(20, 6) NULL DEFAULT NULL COMMENT '加工品消耗鲜果量占鲜果产量比例实际值',
  `scale_forecast` decimal(20, 6) NULL DEFAULT NULL COMMENT '加工品消耗鲜果量占鲜果产量比例预测值',
  `date_time` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '加工品原料消耗情况预测表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mf_process_volume
-- ----------------------------
DROP TABLE IF EXISTS `mf_process_volume`;
CREATE TABLE `mf_process_volume`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `date_time` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `actual_amount` decimal(20, 6) NULL DEFAULT NULL COMMENT '实际销售金额，单位：元',
  `forecast_amount` decimal(20, 6) NULL DEFAULT NULL COMMENT '预测销售金额，单位：元',
  `actual_volume` decimal(20, 6) NULL DEFAULT NULL COMMENT '实际销售量，单位：千克',
  `forecast_volume` decimal(20, 6) NULL DEFAULT NULL COMMENT '预测销售量，单位：千克',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_date_time_1`(`date_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mf_process_yield
-- ----------------------------
DROP TABLE IF EXISTS `mf_process_yield`;
CREATE TABLE `mf_process_yield`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `process_strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）加工品品种  1：芒果汁，2：芒果干，3：芒果醋，4：芒果果酒，5：芒果果粉，6：芒果冻，7：芒果酱',
  `process_strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `output_value_forecast` decimal(20, 6) NULL DEFAULT NULL COMMENT '产值预测值，单位，元',
  `output_value_actual` decimal(20, 6) NULL DEFAULT NULL COMMENT '产值实际值，单位，元',
  `yield_forecast` decimal(20, 6) NULL DEFAULT NULL COMMENT '产量预测值，单位，千克',
  `yield_actual` decimal(20, 6) NULL DEFAULT NULL COMMENT '产量实际值，单位 千克',
  `date_time` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_code_time_1`(`process_strains_code`, `date_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '加工品产量建模表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mf_product_sale
-- ----------------------------
DROP TABLE IF EXISTS `mf_product_sale`;
CREATE TABLE `mf_product_sale`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `yield_actual` decimal(20, 6) NULL DEFAULT NULL COMMENT '产量实际值， 单位：千克',
  `yield_forecast` decimal(20, 6) NULL DEFAULT NULL COMMENT '产量预测值， 单位：千克',
  `sale_actual` decimal(20, 6) NULL DEFAULT NULL COMMENT '销量实际值， 单位：千克',
  `sale_forecast` decimal(20, 6) NULL DEFAULT NULL COMMENT '销量预测值， 单位：千克',
  `date_time` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_date_time_1`(`date_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产销预测表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mf_quality
-- ----------------------------
DROP TABLE IF EXISTS `mf_quality`;
CREATE TABLE `mf_quality`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `index_num` int(11) NULL DEFAULT NULL COMMENT '指数,数字',
  `level_code` tinyint(4) NULL DEFAULT NULL COMMENT '指数,1:低，2：中，3：高',
  `level_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date_time` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_date_time_1`(`date_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '质量安全综合指数表,建模' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mf_quality_inspection
-- ----------------------------
DROP TABLE IF EXISTS `mf_quality_inspection`;
CREATE TABLE `mf_quality_inspection`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organic_area` decimal(20, 6) NULL DEFAULT NULL,
  `organic_area_code` tinyint(4) NULL DEFAULT NULL COMMENT '有机种植面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷',
  `organic_area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organic_area_unit` decimal(20, 6) NULL DEFAULT NULL,
  `inspection` int(11) NULL DEFAULT NULL COMMENT '抽检次数',
  `inspection_qualified` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `issue_times` int(11) NULL DEFAULT NULL COMMENT '质量问题反馈次数',
  `area_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lng` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lat` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '质量抽检表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mf_quality_inspection_rate
-- ----------------------------
DROP TABLE IF EXISTS `mf_quality_inspection_rate`;
CREATE TABLE `mf_quality_inspection_rate`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inspection` int(11) NULL DEFAULT NULL COMMENT '抽检次数',
  `inspection_qualified` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `issue_times` int(11) NULL DEFAULT NULL COMMENT '质量问题反馈次数',
  `area_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creat_time` datetime(0) NULL DEFAULT NULL COMMENT '质量检测时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '质量检测表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mf_sale_ecommerce_craw
-- ----------------------------
DROP TABLE IF EXISTS `mf_sale_ecommerce_craw`;
CREATE TABLE `mf_sale_ecommerce_craw`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crop_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果',
  `crop_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的作物品种',
  `strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ecommerce_code` tinyint(4) NULL DEFAULT NULL COMMENT '平台号：对应数据字典表（dictionary）中的编码字段（code）1：天猫，2：淘宝，3：京东，4：惠农网',
  `ecommerce_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_price` decimal(20, 6) NULL DEFAULT NULL,
  `sale_price_code` tinyint(4) NULL DEFAULT NULL COMMENT '售价单位，对应数据字典表（dictionary）中的编码字段（code），1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤',
  `sale_price_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_price_unit` decimal(20, 6) NULL DEFAULT NULL,
  `price_range_code` tinyint(4) NULL DEFAULT NULL COMMENT '价格区间，对应数据字典表（dictionary）中的编码字段（code） 1：5元以下/公斤，2：5-10元/公斤，3：10-15元/公斤，4：15-20元/公斤，5：20元以上/公斤',
  `price_range_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_amount` decimal(20, 6) NULL DEFAULT NULL,
  `sale_amount_code` tinyint(4) NULL DEFAULT NULL COMMENT '销量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨',
  `sale_amount_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_amount_unit` decimal(20, 6) NULL DEFAULT NULL,
  `weight_specification_code` tinyint(4) NULL DEFAULT NULL COMMENT '重量规格，对应数据字典表（dictionary）中的编码字段（code） 1：1kg以下，2：1kg-2kg，3：2kg-3kg，4:3kg以上',
  `weight_specification_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_volume` decimal(20, 6) NULL DEFAULT NULL,
  `sale_volume_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '销售额单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元',
  `sale_volume_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_volume_unit` decimal(20, 6) NULL DEFAULT NULL,
  `product_area_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_area_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_area_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '电商数据表,采集' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mf_scavenging
-- ----------------------------
DROP TABLE IF EXISTS `mf_scavenging`;
CREATE TABLE `mf_scavenging`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `common_field_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '扫码反馈表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mf_weather
-- ----------------------------
DROP TABLE IF EXISTS `mf_weather`;
CREATE TABLE `mf_weather`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `warning_num` int(11) NULL DEFAULT NULL COMMENT '预警次数',
  `weather_threshold` int(11) NULL DEFAULT NULL COMMENT '气象阈值',
  `date_time` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_date_time_1`(`date_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '气象建模,气象阈值表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pe_aerial_broadcast
-- ----------------------------
DROP TABLE IF EXISTS `pe_aerial_broadcast`;
CREATE TABLE `pe_aerial_broadcast`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `resource_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contet` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `page_view` int(11) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '访问量',
  `activity` int(10) UNSIGNED ZEROFILL NOT NULL COMMENT '活跃度',
  `small_resource_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '鸟瞰华坪轮播图' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pe_aerial_view
-- ----------------------------
DROP TABLE IF EXISTS `pe_aerial_view`;
CREATE TABLE `pe_aerial_view`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource_path` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contet` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `page_view` int(11) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '访问量',
  `activity` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '活跃度',
  `is_slideshow_code` tinyint(4) NULL DEFAULT NULL COMMENT '是否轮播。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。',
  `is_recommend_code` tinyint(4) NULL DEFAULT NULL COMMENT '是否推荐。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录添加时间。',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录修改时间。',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '鸟瞰图' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pe_query_menu
-- ----------------------------
DROP TABLE IF EXISTS `pe_query_menu`;
CREATE TABLE `pe_query_menu`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `farther_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data_time_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应字典表的code字段,1年2季度3月4日5实时',
  `data_time_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `strains_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应数据字典表（dictionary）中的作物品种',
  `strains_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price_type_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应字典表的code字段,1，田头价2，批发价3，零售价4，电商价5，进口价6，出口价',
  `price_type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `query_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price_unit_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应字典表的code字段,1，元2，万元3，亿元4，美元',
  `price_unit_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_level_code` tinyint(4) NULL DEFAULT NULL COMMENT '对应字典表的code字段,0导航菜单 1，一级菜单2，二级菜单3，三级菜单',
  `menu_level_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relation_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据版门户-数据资源-查询菜单' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_acquisition
-- ----------------------------
DROP TABLE IF EXISTS `pi_acquisition`;
CREATE TABLE `pi_acquisition`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `site_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `channel_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `acq_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '停止时间',
  `status` int(11) NULL DEFAULT 0 COMMENT '当前状态(0:静止;1:采集;2:暂停)',
  `curr_num` int(11) NULL DEFAULT 0 COMMENT '当前号码',
  `curr_item` int(11) NULL DEFAULT 0 COMMENT '当前条数',
  `total_item` int(11) NULL DEFAULT 0 COMMENT '每页总条数',
  `pause_time` int(11) NULL DEFAULT 0 COMMENT '暂停时间(毫秒)',
  `page_encoding` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `plan_list` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `dynamic_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dynamic_start` int(11) NULL DEFAULT NULL COMMENT '页码开始',
  `dynamic_end` int(11) NULL DEFAULT NULL COMMENT '页码结束',
  `linkset_start` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `linkset_end` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `link_start` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `link_end` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title_start` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title_end` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `keywords_start` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `keywords_end` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description_start` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description_end` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content_start` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content_end` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pagination_start` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pagination_end` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `queue` int(11) NULL DEFAULT 0 COMMENT '队列',
  `repeat_check_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img_acqu` tinyint(1) NULL DEFAULT 0 COMMENT '是否采集图片',
  `content_prefix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img_prefix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `view_start` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `view_end` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `view_id_start` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `view_id_end` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `view_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `releaseTime_start` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `releaseTime_end` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `author_start` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `author_end` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `origin_start` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `origin_end` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `releaseTime_format` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_jc_acquisition_channel`(`channel_id`) USING BTREE,
  INDEX `fk_jc_acquisition_contenttype`(`type_id`) USING BTREE,
  INDEX `fk_jc_acquisition_site`(`site_id`) USING BTREE,
  INDEX `fk_jc_acquisition_user`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS采集任务表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_acquisition_history
-- ----------------------------
DROP TABLE IF EXISTS `pi_acquisition_history`;
CREATE TABLE `pi_acquisition_history`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `acquisition_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `channel_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS采集的文章历史记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_advertising
-- ----------------------------
DROP TABLE IF EXISTS `pi_advertising`;
CREATE TABLE `pi_advertising`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `serial_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `domain_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resouce_id` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource_path` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `width` int(11) NULL DEFAULT NULL COMMENT '展示宽度。',
  `height` int(11) NULL DEFAULT NULL COMMENT '展示高度。',
  `target_code` tinyint(4) NULL DEFAULT NULL COMMENT '打开方式。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义两种方式：1_self相同的框架或者窗口中打开，2_blank在新窗口中打开。',
  `target_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `space_code` tinyint(4) NOT NULL COMMENT '广告版位。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义两种版位：1资讯版热点中的通栏广告，2数据版首页中的导航栏。',
  `category_code` tinyint(4) NOT NULL COMMENT '广告类型。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义三种类型：1图片，2Flash，3文字。',
  `weight` int(11) NOT NULL DEFAULT 1 COMMENT '广告权重。',
  `display_count` bigint(20) NOT NULL DEFAULT 0 COMMENT '展现次数。',
  `click_count` bigint(20) NULL DEFAULT 0 COMMENT '点击次数。',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间。',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间。',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_enabled_code` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否启用。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录添加时间。',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录修改时间。',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_jc_space_advertising`(`space_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'CMS广告表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_channel
-- ----------------------------
DROP TABLE IF EXISTS `pi_channel`;
CREATE TABLE `pi_channel`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `model_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `site_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `channel_path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lft` int(11) NULL DEFAULT 1 COMMENT '树左边',
  `rgt` int(11) NULL DEFAULT 2 COMMENT '树右边',
  `priority` int(11) NOT NULL DEFAULT 10 COMMENT '排列顺序',
  `has_content` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否有内容',
  `is_display` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否显示',
  `relation_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_jc_channel_model`(`model_id`) USING BTREE,
  INDEX `fk_jc_channel_parent`(`parent_id`) USING BTREE,
  INDEX `fk_jc_channel_site`(`site_id`) USING BTREE,
  INDEX `fk_jc_channel_relation`(`relation_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS栏目表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_channel_attr
-- ----------------------------
DROP TABLE IF EXISTS `pi_channel_attr`;
CREATE TABLE `pi_channel_attr`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `channel_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_jc_attr_channel`(`channel_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS栏目扩展属性表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_channel_count
-- ----------------------------
DROP TABLE IF EXISTS `pi_channel_count`;
CREATE TABLE `pi_channel_count`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `channel_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `views` int(11) NOT NULL DEFAULT 0 COMMENT '总访问数',
  `views_month` int(11) NOT NULL DEFAULT 0 COMMENT '月访问数',
  `views_week` int(11) NOT NULL DEFAULT 0 COMMENT '周访问数',
  `views_day` int(11) NOT NULL DEFAULT 0 COMMENT '日访问数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS栏目访问量计数表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_channel_ext
-- ----------------------------
DROP TABLE IF EXISTS `pi_channel_ext`;
CREATE TABLE `pi_channel_ext`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `channel_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `channel_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `final_step` tinyint(4) NULL DEFAULT 2 COMMENT '终审级别',
  `after_check` tinyint(4) NULL DEFAULT NULL COMMENT '审核后(1:不能修改删除;2:修改后退回;3:修改后不变)',
  `is_static_channel` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_static_content` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_access_by_dir` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_list_child` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `page_size` int(11) NOT NULL DEFAULT 20 COMMENT '每页多少条记录',
  `channel_rule` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content_rule` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tpl_channel` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tpl_content` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title_img` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content_img` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `has_title_img` tinyint(1) NOT NULL DEFAULT 0 COMMENT '内容是否有缩略图',
  `has_content_img` tinyint(1) NOT NULL DEFAULT 0 COMMENT '内容是否有内容图',
  `title_img_width` int(11) NOT NULL DEFAULT 139 COMMENT '内容标题图宽度',
  `title_img_height` int(11) NOT NULL DEFAULT 139 COMMENT '内容标题图高度',
  `content_img_width` int(11) NOT NULL DEFAULT 310 COMMENT '内容内容图宽度',
  `content_img_height` int(11) NOT NULL DEFAULT 310 COMMENT '内容内容图高度',
  `comment_control` int(11) NOT NULL DEFAULT 0 COMMENT '评论(0:匿名;1:会员;2:关闭)',
  `allow_updown` tinyint(1) NOT NULL DEFAULT 1 COMMENT '顶踩(true:开放;false:关闭)',
  `is_blank` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否新窗口打开',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `keywords` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `allow_share` tinyint(1) NOT NULL DEFAULT 0 COMMENT '分享(true:开放;false:关闭)',
  `allow_score` tinyint(1) NOT NULL DEFAULT 0 COMMENT '评分(true:开放;false:关闭)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS栏目内容表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_channel_model
-- ----------------------------
DROP TABLE IF EXISTS `pi_channel_model`;
CREATE TABLE `pi_channel_model`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `channel_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `model_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tpl_content` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `priority` int(11) NOT NULL DEFAULT 10 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_jc_model_channel_m`(`model_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS栏目可选内容模型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_channel_txt
-- ----------------------------
DROP TABLE IF EXISTS `pi_channel_txt`;
CREATE TABLE `pi_channel_txt`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `channel_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `txt` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `txt1` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `txt2` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `txt3` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS栏目文本表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_content
-- ----------------------------
DROP TABLE IF EXISTS `pi_content`;
CREATE TABLE `pi_content`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `model_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `site_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort_date` datetime(0) NULL DEFAULT NULL COMMENT '排序日期',
  `top_level` tinyint(4) NOT NULL DEFAULT 0 COMMENT '固顶级别',
  `has_title_img` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否有标题图',
  `is_recommend` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否推荐,0:否，1：是',
  `status` tinyint(4) NOT NULL DEFAULT 2 COMMENT '状态(0:草稿;1:审核中;2:审核通过;3:回收站；4:投稿)',
  `views_day` int(11) NOT NULL DEFAULT 0 COMMENT '日访问数',
  `comments_day` smallint(6) NOT NULL DEFAULT 0 COMMENT '日评论数',
  `downloads_day` smallint(6) NOT NULL DEFAULT 0 COMMENT '日下载数',
  `ups_day` smallint(6) NOT NULL DEFAULT 0 COMMENT '日顶数',
  `score` int(11) NOT NULL DEFAULT 0 COMMENT '得分',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_jc_content_model`(`model_id`) USING BTREE,
  INDEX `fk_jc_content_site`(`site_id`) USING BTREE,
  INDEX `fk_jc_content_type`(`type_id`) USING BTREE,
  INDEX `fk_jc_content_user`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS内容表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_content_attachment
-- ----------------------------
DROP TABLE IF EXISTS `pi_content_attachment`;
CREATE TABLE `pi_content_attachment`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `content_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `priority` int(11) NOT NULL COMMENT '排列顺序',
  `attachment_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attachment_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `filename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `download_count` int(11) NOT NULL DEFAULT 0 COMMENT '下载次数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_jc_attachment_content`(`content_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS内容附件表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_content_attr
-- ----------------------------
DROP TABLE IF EXISTS `pi_content_attr`;
CREATE TABLE `pi_content_attr`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `content_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_jc_attr_content`(`content_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS内容扩展属性表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_content_check
-- ----------------------------
DROP TABLE IF EXISTS `pi_content_check`;
CREATE TABLE `pi_content_check`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `content_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `check_step` tinyint(4) NULL DEFAULT 0 COMMENT '审核步数',
  `check_opinion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_rejected` tinyint(1) NULL DEFAULT 0 COMMENT '是否退回',
  `reviewer` int(11) NULL DEFAULT NULL COMMENT '终审者',
  `check_date` datetime(0) NULL DEFAULT NULL COMMENT '终审时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_jc_content_check_user`(`reviewer`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS内容审核信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_content_count
-- ----------------------------
DROP TABLE IF EXISTS `pi_content_count`;
CREATE TABLE `pi_content_count`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `content_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `views` int(11) NOT NULL DEFAULT 0 COMMENT '总访问数',
  `views_month` int(11) NOT NULL DEFAULT 0 COMMENT '月访问数',
  `views_week` int(11) NOT NULL DEFAULT 0 COMMENT '周访问数',
  `views_day` int(11) NOT NULL DEFAULT 0 COMMENT '日访问数',
  `comments` int(11) NOT NULL DEFAULT 0 COMMENT '总评论数',
  `comments_month` int(11) NOT NULL DEFAULT 0 COMMENT '月评论数',
  `comments_week` smallint(6) NOT NULL DEFAULT 0 COMMENT '周评论数',
  `comments_day` smallint(6) NOT NULL DEFAULT 0 COMMENT '日评论数',
  `downloads` int(11) NOT NULL DEFAULT 0 COMMENT '总下载数',
  `downloads_month` int(11) NOT NULL DEFAULT 0 COMMENT '月下载数',
  `downloads_week` smallint(6) NOT NULL DEFAULT 0 COMMENT '周下载数',
  `downloads_day` smallint(6) NOT NULL DEFAULT 0 COMMENT '日下载数',
  `ups` int(11) NOT NULL DEFAULT 0 COMMENT '总顶数',
  `ups_month` int(11) NOT NULL DEFAULT 0 COMMENT '月顶数',
  `ups_week` smallint(6) NOT NULL DEFAULT 0 COMMENT '周顶数',
  `ups_day` smallint(6) NOT NULL DEFAULT 0 COMMENT '日顶数',
  `downs` int(11) NOT NULL DEFAULT 0 COMMENT '总踩数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS内容计数表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_content_ext
-- ----------------------------
DROP TABLE IF EXISTS `pi_content_ext`;
CREATE TABLE `pi_content_ext`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `content_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `short_title` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `author` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `origin` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `origin_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `release_date` datetime(0) NOT NULL COMMENT '发布日期',
  `media_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `media_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title_color` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_bold` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否加粗',
  `title_img` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content_img` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type_img` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tpl_content` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `need_regenerate` tinyint(1) NOT NULL DEFAULT 1 COMMENT '需要重新生成静态页',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间。',
  `add_user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_jc_content_release_date`(`release_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS内容扩展表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_content_picture
-- ----------------------------
DROP TABLE IF EXISTS `pi_content_picture`;
CREATE TABLE `pi_content_picture`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `content_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `priority` int(11) NULL DEFAULT NULL COMMENT '排列顺序',
  `img_path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS内容图片表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_content_tag
-- ----------------------------
DROP TABLE IF EXISTS `pi_content_tag`;
CREATE TABLE `pi_content_tag`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `tag_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ref_counter` int(11) NULL DEFAULT 1 COMMENT '被引用的次数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ak_tag_name`(`tag_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS内容TAG表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_content_txt
-- ----------------------------
DROP TABLE IF EXISTS `pi_content_txt`;
CREATE TABLE `pi_content_txt`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `content_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `txt` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `txt1` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `txt2` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `txt3` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS内容文本表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_content_type
-- ----------------------------
DROP TABLE IF EXISTS `pi_content_type`;
CREATE TABLE `pi_content_type`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img_width` int(11) NULL DEFAULT NULL COMMENT '图片宽',
  `img_height` int(11) NULL DEFAULT NULL COMMENT '图片高',
  `has_image` tinyint(1) NULL DEFAULT 0 COMMENT '是否有图片',
  `is_disabled` tinyint(1) NULL DEFAULT 0 COMMENT '是否禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS内容类型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_expert
-- ----------------------------
DROP TABLE IF EXISTS `pi_expert`;
CREATE TABLE `pi_expert`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `is_recommend` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否推荐。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值1。',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = '专家表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_file
-- ----------------------------
DROP TABLE IF EXISTS `pi_file`;
CREATE TABLE `pi_file`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_isvalid` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否有效',
  `content_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_jc_file_content`(`content_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS文章相关文件表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_friendlink
-- ----------------------------
DROP TABLE IF EXISTS `pi_friendlink`;
CREATE TABLE `pi_friendlink`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `domain_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logo_resource_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logo_resource_path` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `views` int(11) NOT NULL DEFAULT 0 COMMENT '点击次数。',
  `is_enabled_code` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否启用。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。',
  `priority` int(11) NOT NULL DEFAULT 10 COMMENT '排列顺序',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录填加时间。',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录修改时间。',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'CMS友情链接' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_interaction
-- ----------------------------
DROP TABLE IF EXISTS `pi_interaction`;
CREATE TABLE `pi_interaction`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `interaction_type` tinyint(4) NULL DEFAULT NULL COMMENT '类型：1：问专家，2：留建言',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qq` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `req_content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `res_user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `res_content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `audit_status_code` tinyint(4) NULL DEFAULT 0 COMMENT '审核状态：对应数据字典表（dictionary）中的编码字段（code）0：待审核，1:审核通过，2：审核不通过',
  `audit_status_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `auditer_suggestion` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `auditer_user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `auditer_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录最后一次修改时间',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_product_recommend
-- ----------------------------
DROP TABLE IF EXISTS `pi_product_recommend`;
CREATE TABLE `pi_product_recommend`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `type_code` tinyint(4) NULL DEFAULT NULL COMMENT '产品类型：对应数据字典表（dictionary）中的编码字段（code） 1：鲜果，2，加工品',
  `type_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `brand` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `weight` decimal(20, 6) NULL DEFAULT NULL COMMENT '产品重量，标准单位：千克',
  `selling_price` decimal(20, 6) NULL DEFAULT NULL COMMENT '售价，标准单位：元',
  `per_price_unit` decimal(20, 6) NULL DEFAULT NULL COMMENT '单价，标准单位：元/千克',
  `mall_interlinkage` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `release_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '数据入库时间',
  `is_recommend` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否推荐,0:否，1：是',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业推介产品表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_topic
-- ----------------------------
DROP TABLE IF EXISTS `pi_topic`;
CREATE TABLE `pi_topic`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `channel_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `topic_name` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `short_name` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `keywords` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title_img` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content_img` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tpl_content` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `priority` int(11) NOT NULL DEFAULT 10 COMMENT '排列顺序',
  `is_recommend` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否推??',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_jc_topic_channel`(`channel_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS专题表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pi_user_favorite
-- ----------------------------
DROP TABLE IF EXISTS `pi_user_favorite`;
CREATE TABLE `pi_user_favorite`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `channel_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `channel_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx1_add_time`(`add_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pir_channel_user
-- ----------------------------
DROP TABLE IF EXISTS `pir_channel_user`;
CREATE TABLE `pir_channel_user`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `channel_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_jc_channel_user`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS栏目用户关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pir_chnl_group_contri
-- ----------------------------
DROP TABLE IF EXISTS `pir_chnl_group_contri`;
CREATE TABLE `pir_chnl_group_contri`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `channel_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_jc_channel_group_c`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS栏目投稿会员组关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pir_chnl_group_view
-- ----------------------------
DROP TABLE IF EXISTS `pir_chnl_group_view`;
CREATE TABLE `pir_chnl_group_view`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `channel_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_jc_channel_group_v`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS栏目浏览会员组关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pir_content_attachment
-- ----------------------------
DROP TABLE IF EXISTS `pir_content_attachment`;
CREATE TABLE `pir_content_attachment`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `content_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `download_count` int(11) NULL DEFAULT NULL COMMENT '下载次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'CMS文章内容包含附件信息表。' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pir_content_channel
-- ----------------------------
DROP TABLE IF EXISTS `pir_content_channel`;
CREATE TABLE `pir_content_channel`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `channel_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_jc_channel_content`(`content_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS内容栏目关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pir_content_group_view
-- ----------------------------
DROP TABLE IF EXISTS `pir_content_group_view`;
CREATE TABLE `pir_content_group_view`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `content_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_jc_content_group_v`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS内容浏览会员组关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pir_content_image
-- ----------------------------
DROP TABLE IF EXISTS `pir_content_image`;
CREATE TABLE `pir_content_image`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `content_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_title_img_code` tinyint(4) NULL DEFAULT NULL COMMENT '是否为标题图。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'CMS文章内容所包含图片信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pir_content_tag
-- ----------------------------
DROP TABLE IF EXISTS `pir_content_tag`;
CREATE TABLE `pir_content_tag`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `content_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tag_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `priority` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_jc_content_tag`(`tag_id`) USING BTREE,
  INDEX `fk_jc_tag_content`(`content_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS内容标签关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pir_content_topic
-- ----------------------------
DROP TABLE IF EXISTS `pir_content_topic`;
CREATE TABLE `pir_content_topic`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `content_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `topic_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_jc_content_topic`(`topic_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = 'CMS专题内容关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pir_content_video
-- ----------------------------
DROP TABLE IF EXISTS `pir_content_video`;
CREATE TABLE `pir_content_video`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `content_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'CMS文章内容所包含视频信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pir_enterprise_product
-- ----------------------------
DROP TABLE IF EXISTS `pir_enterprise_product`;
CREATE TABLE `pir_enterprise_product`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `enterprise_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `priority` int(11) NULL DEFAULT NULL COMMENT '排序依据',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业和产品中间表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pir_interaction_image
-- ----------------------------
DROP TABLE IF EXISTS `pir_interaction_image`;
CREATE TABLE `pir_interaction_image`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `interaction_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_title_img_code` tinyint(4) NULL DEFAULT NULL COMMENT '是否为标题图。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pir_product_resource
-- ----------------------------
DROP TABLE IF EXISTS `pir_product_resource`;
CREATE TABLE `pir_product_resource`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `product_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` tinyint(4) NULL DEFAULT 0 COMMENT '0：产品图片，1：证书图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品和资源中间表' ROW_FORMAT = Compact;

-- ----------------------------
-- View structure for after_3_year_view
-- ----------------------------
DROP VIEW IF EXISTS `after_3_year_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `after_3_year_view` AS select date_format((curdate() - interval 4 year),'%Y') AS `year` union select date_format((curdate() - interval 3 year),'%Y') AS `year` union select date_format((curdate() - interval 2 year),'%Y') AS `year` union select date_format((curdate() - interval 1 year),'%Y') AS `year` union select date_format(curdate(),'%Y') AS `year` union select date_format((curdate() + interval 1 year),'%Y') AS `year` union select date_format((curdate() + interval 2 year),'%Y') AS `year` union select date_format((curdate() + interval 3 year),'%Y') AS `year`;

-- ----------------------------
-- View structure for cur_12_month_view
-- ----------------------------
DROP VIEW IF EXISTS `cur_12_month_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `cur_12_month_view` AS select concat(convert(date_format(curdate(),'%Y') using utf8mb4),'-01') AS `month` union select concat(convert(date_format(curdate(),'%Y') using utf8mb4),'-02') AS `month` union select concat(convert(date_format(curdate(),'%Y') using utf8mb4),'-03') AS `month` union select concat(convert(date_format(curdate(),'%Y') using utf8mb4),'-04') AS `month` union select concat(convert(date_format(curdate(),'%Y') using utf8mb4),'-05') AS `month` union select concat(convert(date_format(curdate(),'%Y') using utf8mb4),'-06') AS `month` union select concat(convert(date_format(curdate(),'%Y') using utf8mb4),'-07') AS `month` union select concat(convert(date_format(curdate(),'%Y') using utf8mb4),'-08') AS `month` union select concat(convert(date_format(curdate(),'%Y') using utf8mb4),'-09') AS `month` union select concat(convert(date_format(curdate(),'%Y') using utf8mb4),'-10') AS `month` union select concat(convert(date_format(curdate(),'%Y') using utf8mb4),'-11') AS `month` union select concat(convert(date_format(curdate(),'%Y') using utf8mb4),'-12') AS `month`;

-- ----------------------------
-- View structure for cur_month_view
-- ----------------------------
DROP VIEW IF EXISTS `cur_month_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `cur_month_view` AS select concat('01') AS `month` union select concat('02') AS `month` union select concat('03') AS `month` union select concat('04') AS `month` union select concat('05') AS `month` union select concat('06') AS `month` union select concat('07') AS `month` union select concat('08') AS `month` union select concat('09') AS `month` union select concat('10') AS `month` union select concat('11') AS `month` union select concat('12') AS `month`;

-- ----------------------------
-- View structure for lately_5_year_view
-- ----------------------------
DROP VIEW IF EXISTS `lately_5_year_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `lately_5_year_view` AS select date_format((curdate() - interval 4 year),'%Y') AS `year` union select date_format((curdate() - interval 3 year),'%Y') AS `year` union select date_format((curdate() - interval 2 year),'%Y') AS `year` union select date_format((curdate() - interval 1 year),'%Y') AS `year` union select date_format(curdate(),'%Y') AS `year`;

-- ----------------------------
-- View structure for past_1_month_view
-- ----------------------------
DROP VIEW IF EXISTS `past_1_month_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `past_1_month_view` AS select date_format(now(),'%Y-%m-%d') AS `times` union select date_format((now() - interval 1 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 2 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 3 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 4 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 5 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 6 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 7 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 8 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 9 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 10 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 11 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 12 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 13 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 14 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 15 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 16 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 17 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 18 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 19 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 20 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 21 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 22 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 23 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 24 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 25 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 26 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 27 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 28 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 29 day),'%Y-%m-%d') AS `times` union select date_format((now() - interval 30 day),'%Y-%m-%d') AS `times`;

-- ----------------------------
-- View structure for past_1_week_view
-- ----------------------------
DROP VIEW IF EXISTS `past_1_week_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `past_1_week_view` AS select date_format(curdate(),'%Y-%m-%d') AS `times` union select date_format((curdate() - interval 1 day),'%Y-%m-%d') AS `times` union select date_format((curdate() - interval 2 day),'%Y-%m-%d') AS `times` union select date_format((curdate() - interval 3 day),'%Y-%m-%d') AS `times` union select date_format((curdate() - interval 4 day),'%Y-%m-%d') AS `times` union select date_format((curdate() - interval 5 day),'%Y-%m-%d') AS `times` union select date_format((curdate() - interval 6 day),'%Y-%m-%d') AS `times`;

-- ----------------------------
-- View structure for past_24_hour_view
-- ----------------------------
DROP VIEW IF EXISTS `past_24_hour_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `past_24_hour_view` AS select date_format(now(),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 1 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 2 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 3 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 4 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 5 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 6 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 7 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 8 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 9 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 10 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 11 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 12 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 13 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 14 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 15 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 16 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 17 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 18 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 19 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 20 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 21 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 22 hour),'%Y-%m-%d %H') AS `times` union select date_format((now() - interval 23 hour),'%Y-%m-%d %H') AS `times`;

-- ----------------------------
-- View structure for past_5_year_view
-- ----------------------------
DROP VIEW IF EXISTS `past_5_year_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `past_5_year_view` AS select date_format((curdate() - interval 4 year),'%Y') AS `year` union select date_format((curdate() - interval 3 year),'%Y') AS `year` union select date_format((curdate() - interval 2 year),'%Y') AS `year` union select date_format((curdate() - interval 1 year),'%Y') AS `year` union select date_format(curdate(),'%Y') AS `year` union select date_format((curdate() + interval 1 year),'%Y') AS `year`;

-- ----------------------------
-- Procedure structure for clean_parent_table
-- ----------------------------
DROP PROCEDURE IF EXISTS `clean_parent_table`;
delimiter ;;
CREATE PROCEDURE `clean_parent_table`(origin_table varchar(1024),id_value VARCHAR ( 1024 ))
BEGIN
	DECLARE
		done INT DEFAULT FALSE;
	DECLARE
		m_table VARCHAR ( 64 );
	DECLARE
		m_column VARCHAR ( 64 );
	DECLARE
		skip_tables VARCHAR ( 1024 ) DEFAULT ( 'da_iot_monitor_data,da_crawler,gp_oper_log' );
	DECLARE
		all_table_list_cursor CURSOR FOR SELECT
		table_name,
		column_name 
	FROM
		information_schema.COLUMNS 
	WHERE
		data_type = 'CHAR' 
		AND FIND_IN_SET( table_name, skip_tables ) = 0 
		AND column_name != 'id' 
		AND table_schema = 'mango';
	DECLARE
		CONTINUE HANDLER FOR NOT FOUND 
		SET done = TRUE;
	OPEN all_table_list_cursor;
	all_table_list_loop :
	LOOP
			FETCH all_table_list_cursor INTO m_table,
			m_column;
		
		SET @is_use_sql = concat( " select count(1) INTO @record_count from ", m_table, " where  ", m_column, " = '", id_value, "';" );
		PREPARE is_use_stmt 
		FROM
			@is_use_sql;
		EXECUTE is_use_stmt;
		DEALLOCATE PREPARE is_use_stmt;
	
			
		#把@record_count > 0这个条件拿掉，再做些改造，则向gp_value_location表中就能记录所有引入位置
		#现在只能记录第一次发现的位置，不过对现在的应用足够了
		#因为da_common_field和gp_resource两张表中的单条记录在数据库中最多只能有一个引用位置。
		
		IF
			@record_count > 0 || done THEN
				LEAVE all_table_list_loop;
		END IF;
		
	END LOOP all_table_list_loop;
	CLOSE all_table_list_cursor;
		SELECT
	IF
		( @record_count = 0, '垃圾数据', '正在使用' );
#无用的记录删除
	    IF @record_count = 0 THEN
            select '无用记录';
				#有用的记录将对应关系存入gp_value_location表中
				ELSE
					SET @update_value_location_sql = concat( "insert into gp_value_location(id,origin_table_name,target_table_name,target_record_id,target_column_name,column_value,remarks,add_time) select md5(uuid()),'", origin_table,"','",m_table, "' as tablename,id,'", m_column, "' as columnname,", m_column, " as columnvalue,'自动生成，单个值做为非主键在MySQL中所处位置。',now() from ", m_table, " where ", m_column, " = '", id_value, "';" );
			
			PREPARE update_value_location_stmt 
			FROM
				@update_value_location_sql;
			EXECUTE update_value_location_stmt;
			DEALLOCATE PREPARE update_value_location_stmt;

    END IF;


END
;;
delimiter ;

-- ----------------------------
-- Function structure for rand_str
-- ----------------------------
DROP FUNCTION IF EXISTS `rand_str`;
delimiter ;;
CREATE FUNCTION `rand_str`(strlen SMALLINT)
 RETURNS varchar(255) CHARSET utf8
BEGIN
DECLARE randStr VARCHAR(255) DEFAULT 'abcdefghijklmnopqrstuvwxyz1234567890';
DECLARE i SMALLINT DEFAULT 0;
DECLARE resultStr VARCHAR(255) DEFAULT '';
WHILE i<strlen DO
SET resultStr=CONCAT(SUBSTR(randStr,FLOOR(RAND()*LENGTH(randStr))+1,1),resultStr);
SET i=i+1;
END WHILE;
RETURN resultStr;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for search_child_tables
-- ----------------------------
DROP PROCEDURE IF EXISTS `search_child_tables`;
delimiter ;;
CREATE PROCEDURE `search_child_tables`(parasite_table VARCHAR ( 1024 ))
BEGIN
	DECLARE
		all_table VARCHAR ( 64 );
	DECLARE
		all_column VARCHAR ( 64 );
	DECLARE
		record_count INT DEFAULT 0;
	DECLARE
		id_value CHAR ( 32 );
	DECLARE
		skip_tables VARCHAR ( 1024 ) DEFAULT ( 'da_iot_monitor_data,da_crawler' );
		
	DECLARE
		all_table_list_cursor CURSOR FOR SELECT
		table_name,
		column_name 
	FROM
		information_schema.COLUMNS 
	WHERE
		data_type = 'CHAR' 
		AND FIND_IN_SET( table_name, skip_tables ) = 0 
		AND table_schema = 'mango';
	DECLARE
		EXIT HANDLER FOR NOT FOUND CLOSE all_table_list_cursor;
	
	
	
	
	SET @_tableCount = 0;
	OPEN all_table_list_cursor;
	REPEAT
			FETCH all_table_list_cursor INTO all_table,all_column;
		SET @select_junck_sql = concat( "select count(1),A.ID INTO @record_count,@id_value from ", parasite_table, " A LEFT JOIN ", all_table, " B on A.id=B.", all_column, " where length(A.ID)=32" );
		PREPARE select_junck_stmt 
		FROM
			@select_junck_sql;
		EXECUTE select_junck_stmt;
		DEALLOCATE PREPARE select_junck_stmt;
		IF
			@record_count > 0 THEN
			SELECT
				@select_junck_sql;
			
		END IF;
		UNTIL @record_count > 0 
	END REPEAT;
	IF
		@record_count = 0 THEN
			
			SET @delete_junck_sql = "delete from " + parasite_table + " where id='" + @id_value + "'";
		SELECT
			@delete_junck_sql;
		
	END IF;
	CLOSE all_table_list_cursor;

END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for update_value_location
-- ----------------------------
DROP PROCEDURE IF EXISTS `update_value_location`;
delimiter ;;
CREATE PROCEDURE `update_value_location`(origin_table VARCHAR(1024),id_value VARCHAR(1024))
BEGIN
	DECLARE
		done INT DEFAULT FALSE;
	DECLARE
		m_table VARCHAR ( 64 );
	DECLARE
		m_column VARCHAR ( 64 );
	DECLARE
		skip_tables VARCHAR ( 1024 ) DEFAULT ( 'da_iot_monitor_data,da_crawler,gp_oper_log' );
	DECLARE
		all_table_list_cursor CURSOR FOR SELECT
		table_name,
		column_name 
	FROM
		information_schema.COLUMNS 
	WHERE
		data_type = 'CHAR' 
		AND FIND_IN_SET( table_name, skip_tables ) = 0 
		AND column_name != 'id' 
		AND table_schema = 'mango';
	DECLARE
		CONTINUE HANDLER FOR NOT FOUND 
		SET done = TRUE;
		
		
		delete from gp_value_location where column_value=id_value;
		
		
	OPEN all_table_list_cursor;
	REPEAT
			FETCH all_table_list_cursor INTO m_table,
			m_column;
		IF
			m_table != 'da_iot_monitor_data'&&m_table!='da_crawler' THEN
							
				SET @m_sql = concat( "insert into gp_value_location(id,origin_table_name,target_table_name,target_record_id,target_column_name,column_value,remarks,add_time) select md5(uuid()),'", origin_table,"','",m_table, "' as tablename,id,'", m_column, "' as columnname,", m_column, " as columnvalue,'自动生成，单个值做为非主键在MySQL中所处位置。',now() from ", m_table, " where ", m_column, " = '", id_value, "';" );
			
			PREPARE stmt 
			FROM
				@m_sql;
			EXECUTE stmt;
			DEALLOCATE PREPARE stmt;
							
		END IF;
		UNTIL done
	END REPEAT;
	

	CLOSE all_table_list_cursor;

END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
