-- add by dzm at 2016-07-14
ALTER TABLE `oim_supplier`
ADD INDEX `ucs_id_index` (`ucs_id`) USING BTREE ;
-- add by dzm at 2016-07-21
ALTER TABLE `sms_employee_schedule`
MODIFY COLUMN `six`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '6点' AFTER `status`,
MODIFY COLUMN `seven`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '7点' AFTER `six`,
MODIFY COLUMN `eight`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '8点' AFTER `seven`,
MODIFY COLUMN `nine`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '9点' AFTER `eight`,
MODIFY COLUMN `ten`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '10点' AFTER `nine`,
MODIFY COLUMN `eleven`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '11点' AFTER `ten`,
MODIFY COLUMN `twelve`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '12点' AFTER `eleven`,
MODIFY COLUMN `thirteen`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '13点' AFTER `twelve`,
MODIFY COLUMN `fourteen`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '14点' AFTER `thirteen`,
MODIFY COLUMN `fifteen`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '15点' AFTER `fourteen`,
MODIFY COLUMN `sixteen`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '16点' AFTER `fifteen`,
MODIFY COLUMN `seventeen`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '17点' AFTER `sixteen`,
MODIFY COLUMN `eighteen`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '18点' AFTER `seventeen`,
MODIFY COLUMN `nineteen`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '19点' AFTER `eighteen`,
MODIFY COLUMN `twenty`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '20点' AFTER `nineteen`,
MODIFY COLUMN `twenty_one`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '21点' AFTER `twenty`,
MODIFY COLUMN `twenty_two`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '22点' AFTER `twenty_one`,
MODIFY COLUMN `twenty_three`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '23点' AFTER `twenty_two`;
ALTER TABLE `sms_employee_schedule`
DROP COLUMN `month`;
-- add by dzm at 2016-7-23
ALTER TABLE `sms_member`
ADD COLUMN `status`  char(2) NULL COMMENT '状态' AFTER `header_pic`;
ALTER TABLE `sms_order_log`
MODIFY COLUMN `director`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人' AFTER `opt_content`;
-- add by dzm at 2016-7-31
ALTER TABLE `sms_order`
MODIFY COLUMN `director`  char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人' AFTER `appointment_time`;
-- add bt dzn at 2016-08-13
ALTER TABLE `sms_member_address`
MODIFY COLUMN `id`  char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID' FIRST ;
ALTER TABLE `sms_member_address`
MODIFY COLUMN `short_address`  varchar(49) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '短地址' AFTER `division`,
MODIFY COLUMN `long_address`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '长地址' AFTER `short_address`;
ALTER TABLE `sms_order`
MODIFY COLUMN `appointment_time`  datetime NULL COMMENT '预约服务时间' AFTER `order_time`;
ALTER TABLE `sms_order`
ADD COLUMN `employees`  varchar(500) NULL COMMENT '员工' AFTER `city`;
ALTER TABLE `sms_member_address`
ADD COLUMN `adname`  varchar(40) NULL COMMENT '区县' AFTER `latitude`;
ALTER TABLE `sms_housing`
ADD COLUMN `division`  char(6) NULL COMMENT '区县' AFTER `province`;
ALTER TABLE `sms_housing`
MODIFY COLUMN `division`  varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区县' AFTER `province`,
ADD COLUMN `location`  varchar(100) NULL COMMENT '经纬度' AFTER `division`;
ALTER TABLE `sms_house_building`
MODIFY COLUMN `id`  char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '楼栋编号' FIRST ;

