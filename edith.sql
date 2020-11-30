SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for edith_file
-- ----------------------------
DROP TABLE IF EXISTS `edith_file`;
CREATE TABLE `edith_file` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `path` varchar(500) NOT NULL,
  `type` varchar(10) NOT NULL DEFAULT 'LOCAL' COMMENT 'LOCAL 本地，REMOTE 远程',
  `name` varchar(100) NOT NULL,
  `extension` varchar(50) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `md5` varchar(40) DEFAULT NULL COMMENT 'md5值，防止上传重复文件',
  `create_time` datetime(3) NOT NULL DEFAULT current_timestamp(3),
  `update_time` datetime(3) NOT NULL DEFAULT current_timestamp(3) ON UPDATE current_timestamp(3),
  `delete_time` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `md5_del` (`md5`,`delete_time`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of edith_file
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for edith_group
-- ----------------------------
DROP TABLE IF EXISTS `edith_group`;
CREATE TABLE `edith_group` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL COMMENT '分组名称，例如：搬砖者',
  `info` varchar(255) DEFAULT NULL COMMENT '分组信息：例如：搬砖的人',
  `level` enum('root','guest','user') DEFAULT 'user' COMMENT '分组级别（root、guest分组只能存在一个）',
  `create_time` datetime(3) NOT NULL DEFAULT current_timestamp(3),
  `update_time` datetime(3) NOT NULL DEFAULT current_timestamp(3) ON UPDATE current_timestamp(3),
  `delete_time` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_del` (`name`,`delete_time`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of edith_group
-- ----------------------------
BEGIN;
INSERT INTO `edith_group` VALUES (1, 'root', '超级用户组', 'root', '2020-08-31 09:56:32.202', '2020-08-31 09:56:32.202', NULL);
INSERT INTO `edith_group` VALUES (2, 'guest', '游客组', 'guest', '2020-08-31 09:56:32.225', '2020-11-22 12:27:40.430', NULL);
COMMIT;

-- ----------------------------
-- Table structure for edith_group_permission
-- ----------------------------
DROP TABLE IF EXISTS `edith_group_permission`;
CREATE TABLE `edith_group_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `group_id` int(10) unsigned NOT NULL COMMENT '分组id',
  `permission_id` int(10) unsigned NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`),
  KEY `group_id_permission_id` (`group_id`,`permission_id`) USING BTREE COMMENT '联合索引'
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of edith_group_permission
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for edith_log
-- ----------------------------
DROP TABLE IF EXISTS `edith_log`;
CREATE TABLE `edith_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `message` varchar(450) DEFAULT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `username` varchar(24) DEFAULT NULL,
  `status_code` int(11) DEFAULT NULL,
  `method` varchar(20) DEFAULT NULL,
  `path` varchar(50) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  `create_time` datetime(3) NOT NULL DEFAULT current_timestamp(3),
  `update_time` datetime(3) NOT NULL DEFAULT current_timestamp(3) ON UPDATE current_timestamp(3),
  `delete_time` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of edith_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for edith_permission
-- ----------------------------
DROP TABLE IF EXISTS `edith_permission`;
CREATE TABLE `edith_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL COMMENT '权限名称，例如：访问首页',
  `module` varchar(50) NOT NULL COMMENT '权限所属模块，例如：人员管理',
  `mount` tinyint(1) NOT NULL DEFAULT 1 COMMENT '0：关闭 1：开启',
  `create_time` datetime(3) NOT NULL DEFAULT current_timestamp(3),
  `update_time` datetime(3) NOT NULL DEFAULT current_timestamp(3) ON UPDATE current_timestamp(3),
  `delete_time` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of edith_permission
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for edith_user
-- ----------------------------
DROP TABLE IF EXISTS `edith_user`;
CREATE TABLE `edith_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(24) NOT NULL COMMENT '用户名，唯一',
  `nickname` varchar(24) DEFAULT NULL COMMENT '用户昵称',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像url',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime(3) NOT NULL DEFAULT current_timestamp(3),
  `update_time` datetime(3) NOT NULL DEFAULT current_timestamp(3) ON UPDATE current_timestamp(3),
  `delete_time` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_del` (`username`,`delete_time`),
  UNIQUE KEY `email_del` (`email`,`delete_time`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of edith_user
-- ----------------------------
BEGIN;
INSERT INTO `edith_user` VALUES (1, 'root', 'root', NULL, NULL, '2020-08-31 09:56:32.154', '2020-08-31 09:56:32.154', NULL);
COMMIT;

-- ----------------------------
-- Table structure for edith_user_group
-- ----------------------------
DROP TABLE IF EXISTS `edith_user_group`;
CREATE TABLE `edith_user_group` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL COMMENT '用户id',
  `group_id` int(10) unsigned NOT NULL COMMENT '分组id',
  PRIMARY KEY (`id`),
  KEY `user_id_group_id` (`user_id`,`group_id`) USING BTREE COMMENT '联合索引'
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of edith_user_group
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for edith_user_identity
-- ----------------------------
DROP TABLE IF EXISTS `edith_user_identity`;
CREATE TABLE `edith_user_identity` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL COMMENT '用户id',
  `identity_type` varchar(100) NOT NULL,
  `identifier` varchar(100) DEFAULT NULL,
  `credential` varchar(100) DEFAULT NULL,
  `create_time` datetime(3) NOT NULL DEFAULT current_timestamp(3),
  `update_time` datetime(3) NOT NULL DEFAULT current_timestamp(3) ON UPDATE current_timestamp(3),
  `delete_time` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of edith_user_identity
-- ----------------------------
BEGIN;
INSERT INTO `edith_user_identity` VALUES (1, 1, 'USERNAME_PASSWORD', 'root', 'pbkdf2sha256:64000:18:24:n:yUnDokcNRbwILZllmUOItIyo9MnI00QW:6ZcPf+sfzyoygOU8h/GSoirF', '2020-08-31 09:56:32.185', '2020-08-31 09:56:32.185', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
