SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '学生id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生姓名',
  `age` tinyint DEFAULT NULL COMMENT '学生年龄',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学生表';

-- ----------------------------
-- Records of t_student
-- ----------------------------
BEGIN;
INSERT INTO `t_student` VALUES (1, '张三', 16);
INSERT INTO `t_student` VALUES (2, '李四', 17);
INSERT INTO `t_student` VALUES (3, '王五', 18);
COMMIT;


-- ----------------------------
-- Table structure for t_subject
-- ----------------------------
DROP TABLE IF EXISTS `t_subject`;
CREATE TABLE `t_subject` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学科名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学科表';

-- ----------------------------
-- Records of t_subject
-- ----------------------------
BEGIN;
INSERT INTO `t_subject` VALUES (1, '语文');
INSERT INTO `t_subject` VALUES (2, '数学');
COMMIT;

-- ----------------------------
-- Table structure for t_score
-- ----------------------------
DROP TABLE IF EXISTS `t_score`;
CREATE TABLE `t_score` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `subject_id` bigint DEFAULT NULL COMMENT '学科id',
  `student_id` bigint DEFAULT NULL COMMENT '学生id',
  `score` tinyint DEFAULT NULL COMMENT '成绩',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='成绩表';

-- ----------------------------
-- Records of t_score
-- ----------------------------
BEGIN;
INSERT INTO `t_score` VALUES (1, 1, 1, 80);
INSERT INTO `t_score` VALUES (2, 2, 1, 90);
INSERT INTO `t_score` VALUES (3, 1, 2, 70);
INSERT INTO `t_score` VALUES (4, 2, 2, 72);
INSERT INTO `t_score` VALUES (5, 1, 3, 80);
INSERT INTO `t_score` VALUES (6, 2, 3, 100);
COMMIT;


SET FOREIGN_KEY_CHECKS = 1;
