/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50635
Source Host           : 127.0.0.1:3306
Source Database       : standard_cloud

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2018-07-04 19:40:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_dict_info
-- ----------------------------
DROP TABLE IF EXISTS `t_dict_info`;
CREATE TABLE `t_dict_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `dict_code` varchar(28) DEFAULT NULL,
  `dict_type` tinyint(1) DEFAULT '0' COMMENT '0 后台字典  1前台字典',
  `dict_name` varchar(64) DEFAULT NULL COMMENT '字典名称',
  `dict_desc` varchar(256) DEFAULT NULL COMMENT '字典描述',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `operator_id` varchar(64) DEFAULT NULL COMMENT '操作员ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dict_code` (`dict_code`) USING BTREE,
  UNIQUE KEY `dict_name` (`dict_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dict_info
-- ----------------------------
INSERT INTO `t_dict_info` VALUES ('1', 'standNo', '0', '标准编号', '文档的标准编号', '2018-05-24 10:50:38', '2018-05-24 10:50:41', '0');
INSERT INTO `t_dict_info` VALUES ('2', 'standName', '0', '标准名称', '文档的标准名称', '2018-05-24 11:30:45', '2018-05-24 11:30:45', null);
INSERT INTO `t_dict_info` VALUES ('3', 'qualification', '0', '资质', '资质', '2018-05-24 11:33:38', '2018-05-24 11:33:38', null);
INSERT INTO `t_dict_info` VALUES ('4', 'standClass', '0', '标准类别', '标准类别', '2018-05-24 11:34:15', '2018-05-24 11:34:15', null);
INSERT INTO `t_dict_info` VALUES ('5', 'topCategory', '0', '一级分类', '一级分类', '2018-05-24 11:35:45', '2018-05-24 11:35:45', null);
INSERT INTO `t_dict_info` VALUES ('6', 'subCategory', '0', '二级分类', '二级分类', '2018-05-24 11:36:04', '2018-05-24 11:36:04', null);
INSERT INTO `t_dict_info` VALUES ('7', 'effectiveDate', '0', '生效日期', '生效日期', '2018-05-24 11:36:55', '2018-05-24 11:36:55', null);
INSERT INTO `t_dict_info` VALUES ('8', 'expirationDate', '0', '失效日期', '失效日期', '2018-05-24 11:37:12', '2018-05-24 11:37:12', null);
INSERT INTO `t_dict_info` VALUES ('9', 'alterStand', '0', '替代标准', '替代标准', '2018-05-24 11:37:28', '2018-05-24 11:37:28', null);
INSERT INTO `t_dict_info` VALUES ('10', 'probationScope', '0', '适用范围', '适用范围', '2018-05-24 11:37:45', '2018-05-24 11:37:45', null);
INSERT INTO `t_dict_info` VALUES ('11', 'sampleGross', '0', '样品总量', '样品总量', '2018-05-24 11:37:57', '2018-05-24 11:37:57', null);
INSERT INTO `t_dict_info` VALUES ('12', 'publisher', '0', '发布机构', '发布机构', '2018-05-24 11:38:15', '2018-05-24 11:38:15', null);
INSERT INTO `t_dict_info` VALUES ('13', 'draftingOrg', '0', '起草单位', '起草单位', '2018-05-24 11:38:29', '2018-05-24 11:38:29', null);
INSERT INTO `t_dict_info` VALUES ('14', 'engName', '0', '英文名', '英文名', '2018-05-24 11:38:42', '2018-05-24 11:38:42', null);
INSERT INTO `t_dict_info` VALUES ('15', 'winningBidCategory', '0', '中标分类', '中标分类', '2018-05-24 11:38:55', '2018-05-24 11:38:55', null);
INSERT INTO `t_dict_info` VALUES ('16', 'icsCategory', '0', 'ICS分类', 'ICS分类', '2018-05-24 11:39:07', '2018-05-24 11:39:07', null);
INSERT INTO `t_dict_info` VALUES ('17', 'refStandard', '0', '引用标准', '引用标准', '2018-05-24 11:39:18', '2018-05-24 11:39:18', null);
INSERT INTO `t_dict_info` VALUES ('18', 'commonData', '0', '常规数据', '常规数据', '2018-06-11 18:52:55', '2018-06-11 18:52:55', null);
INSERT INTO `t_dict_info` VALUES ('19', 'termDefine', '0', '术语和定义', '术语和定义', '2018-06-11 18:57:49', '2018-06-11 18:57:49', null);
INSERT INTO `t_dict_info` VALUES ('20', 'classify', '0', '分类', '分类', '2018-06-11 18:58:28', '2018-06-11 18:58:28', null);
INSERT INTO `t_dict_info` VALUES ('21', 'requirement', '0', '要求', '要求', '2018-06-11 18:58:52', '2018-06-11 18:58:52', null);
INSERT INTO `t_dict_info` VALUES ('22', 'paramList', '0', '参数列表', '参数列表', '2018-06-11 18:59:33', '2018-06-11 18:59:33', null);
INSERT INTO `t_dict_info` VALUES ('23', 'deteMth', '0', '检测方法', '检测方法', '2018-06-11 19:00:15', '2018-06-11 19:00:15', null);
INSERT INTO `t_dict_info` VALUES ('24', 'thirdCategory', '0', '三级分类', '三级分类', '2018-06-11 19:02:45', '2018-06-11 19:02:45', null);
INSERT INTO `t_dict_info` VALUES ('25', 'deteItem', '0', '检测项目', '检测项目', '2018-06-11 19:04:30', '2018-06-11 19:04:30', null);
INSERT INTO `t_dict_info` VALUES ('26', 'deteBounds', '0', '检出限量值', '检出限量值', '2018-06-11 19:05:38', '2018-06-11 19:05:38', null);
INSERT INTO `t_dict_info` VALUES ('27', 'remark', '0', '备注', '备注', '2018-06-11 19:10:33', '2018-06-11 19:10:33', null);
INSERT INTO `t_dict_info` VALUES ('28', 'price', '0', '价格', '价格', '2018-06-11 19:13:08', '2018-06-11 19:13:08', null);
INSERT INTO `t_dict_info` VALUES ('29', 'deteItemType', '0', '检测项目类型', '检测项目类型', '2018-06-11 19:13:46', '2018-06-11 19:13:46', null);
INSERT INTO `t_dict_info` VALUES ('30', 'deteFactorBounds', '0', '检出限条件限量值', '检出限条件限量值', '2018-06-11 19:14:31', '2018-06-11 19:14:31', null);
INSERT INTO `t_dict_info` VALUES ('31', 'mthTheory', '0', '方法原理', '方法原理', '2018-06-11 19:14:59', '2018-06-11 19:14:59', null);
INSERT INTO `t_dict_info` VALUES ('32', 'sampGross', '0', '样品量', '样品量', '2018-06-11 19:15:17', '2018-06-11 19:15:17', null);
INSERT INTO `t_dict_info` VALUES ('33', 'reagent', '0', '试剂', '试剂', '2018-06-11 19:15:32', '2018-06-11 19:15:32', null);
INSERT INTO `t_dict_info` VALUES ('34', 'standMaterial', '0', '标准物质', '标准物质', '2018-06-11 19:15:49', '2018-06-11 19:15:49', null);
INSERT INTO `t_dict_info` VALUES ('35', 'equipment', '0', '仪器设备', '仪器设备', '2018-06-11 19:16:10', '2018-06-11 19:16:10', null);
INSERT INTO `t_dict_info` VALUES ('36', 'sampPreStep', '0', '制样步骤', '制样步骤', '2018-06-11 19:16:33', '2018-06-11 19:16:33', null);
INSERT INTO `t_dict_info` VALUES ('37', 'testStep', '0', '测定步骤', '测定步骤', '2018-06-11 19:16:51', '2018-06-11 19:16:51', null);
INSERT INTO `t_dict_info` VALUES ('38', 'calculation', '0', '计算', '计算', '2018-06-11 19:17:03', '2018-06-11 19:17:03', null);
INSERT INTO `t_dict_info` VALUES ('39', 'allowDeviationFactorBounds', '0', '允许差条件限量值', '允许差条件限量值', '2018-06-11 19:17:46', '2018-06-11 19:17:46', null);
INSERT INTO `t_dict_info` VALUES ('40', 'allowDeviation', '0', '允许差', '允许差', '2018-06-11 19:18:08', '2018-06-11 19:18:08', null);
INSERT INTO `t_dict_info` VALUES ('41', 'standSpecialRequire', '0', '标准特殊要求', '标准特殊要求', '2018-06-11 19:18:24', '2018-06-11 19:18:24', null);
INSERT INTO `t_dict_info` VALUES ('42', 'deteRule', '0', '检测规则', '检测规则', '2018-06-11 19:19:00', '2018-06-11 19:19:00', null);
INSERT INTO `t_dict_info` VALUES ('43', 'symbolPackage', '0', '标志和包装', '标志和包装', '2018-06-11 19:19:36', '2018-06-11 19:19:36', null);
INSERT INTO `t_dict_info` VALUES ('44', 'transStorage', '0', '运输和贮存', '运输和贮存', '2018-06-11 19:19:59', '2018-06-11 19:19:59', null);
INSERT INTO `t_dict_info` VALUES ('45', 'attach', '0', '附录', '附录', '2018-06-11 19:20:31', '2018-06-11 19:20:31', null);
INSERT INTO `t_dict_info` VALUES ('46', 'thirtyThreeClass', '0', '三十三大类', '三十三大类', '2018-06-25 14:49:09', '2018-06-25 14:49:09', null);
INSERT INTO `t_dict_info` VALUES ('47', 'middleClass', '0', '中类', '中类', '2018-06-25 14:49:42', '2018-06-25 14:49:42', null);
INSERT INTO `t_dict_info` VALUES ('48', 'lettleClass', '0', '小类', '小类', '2018-06-25 14:50:02', '2018-06-25 14:50:02', null);
INSERT INTO `t_dict_info` VALUES ('49', 'productName', '0', '品名', '品名', '2018-06-25 14:50:23', '2018-06-25 14:50:23', null);
INSERT INTO `t_dict_info` VALUES ('50', 'smpName', '0', '抽样样品名称', '抽样样品名称', '2018-06-25 14:50:55', '2018-06-25 14:50:55', null);
INSERT INTO `t_dict_info` VALUES ('51', 'smpSpecification', '0', '抽样型号或规格', '抽样型号或规格', '2018-06-25 14:51:24', '2018-06-25 14:51:24', null);
INSERT INTO `t_dict_info` VALUES ('52', 'procucingSmp', '0', '生产环节抽样时', '生产环节抽样时', '2018-06-25 14:52:05', '2018-06-25 14:52:05', null);
INSERT INTO `t_dict_info` VALUES ('53', 'businessSmp', '0', '经营环节抽样时', '经营环节抽样时', '2018-06-25 14:52:25', '2018-06-25 14:52:25', null);
INSERT INTO `t_dict_info` VALUES ('54', 'smpOrderInfo', '0', '抽样单信息', '抽样单信息', '2018-06-25 14:52:44', '2018-06-25 14:52:44', null);
INSERT INTO `t_dict_info` VALUES ('55', 'sealedSmp', '0', '封样', '封样', '2018-06-25 14:53:15', '2018-06-25 14:53:15', null);
INSERT INTO `t_dict_info` VALUES ('56', 'sealedSmpPrinciple', '0', '封样原则', '封样原则', '2018-06-25 14:53:34', '2018-06-25 14:53:34', null);
INSERT INTO `t_dict_info` VALUES ('57', 'smpTransStorRequire', '0', '样品运输、贮存要求', '样品运输、贮存要求', '2018-06-25 14:54:42', '2018-06-25 14:54:42', null);
INSERT INTO `t_dict_info` VALUES ('58', 'netFoodSmpTransStorRequire', '0', '网络食品样品运输、贮存要求', '网络食品样品运输、贮存要求', '2018-06-25 14:55:01', '2018-06-25 14:55:01', null);
INSERT INTO `t_dict_info` VALUES ('59', 'category', '0', '类别', '类别', '2018-06-25 14:58:59', '2018-06-25 14:58:59', null);
INSERT INTO `t_dict_info` VALUES ('60', 'smpDetectItem', '0', '抽样样品检验项目', '抽样样品检验项目', '2018-06-25 15:01:33', '2018-06-25 15:01:33', null);
INSERT INTO `t_dict_info` VALUES ('61', 'smpBasicStand', '0', '依据法律法规或标准', '依据法律法规或标准', '2018-06-25 15:02:07', '2018-06-25 15:02:07', null);
INSERT INTO `t_dict_info` VALUES ('62', 'smpDeteMth', '0', '抽样检测方法', '抽样检测方法', '2018-06-25 15:02:53', '2018-06-25 15:02:53', null);
INSERT INTO `t_dict_info` VALUES ('63', 'smpItemSpeRequire', '0', '抽样检验项目特殊要求', '抽样检验项目特殊要求', '2018-06-25 15:03:28', '2018-06-25 15:03:28', null);
INSERT INTO `t_dict_info` VALUES ('64', 'determinationConclusion', '0', '判定原则与结论', '判定原则与结论', '2018-06-25 15:03:54', '2018-06-25 15:03:54', null);
INSERT INTO `t_dict_info` VALUES ('65', 'classifyGeneralPrinciples', '0', '分类总则', '分类总则', '2018-06-25 15:08:39', '2018-06-25 15:08:39', null);
INSERT INTO `t_dict_info` VALUES ('66', 'smpMth', '0', '抽样方法', '抽样方法', '2018-06-25 15:10:27', '2018-06-25 15:10:27', null);
INSERT INTO `t_dict_info` VALUES ('67', 'foodName', '0', '食品名称', '食品名称', '2018-06-25 18:41:35', '2018-06-25 18:41:35', null);
INSERT INTO `t_dict_info` VALUES ('68', 'additiveName', '0', '添加剂名称', '添加剂名称', '2018-06-25 18:42:23', '2018-06-25 18:42:23', null);
INSERT INTO `t_dict_info` VALUES ('69', 'CNS', '0', 'CNS号', 'CNS号', '2018-06-25 18:43:59', '2018-06-25 18:43:59', null);
INSERT INTO `t_dict_info` VALUES ('70', 'INS', '0', 'INS号', 'INS号', '2018-06-25 18:44:15', '2018-06-25 18:44:15', null);
INSERT INTO `t_dict_info` VALUES ('71', 'function', '0', '功能', '功能', '2018-06-25 18:47:20', '2018-06-25 18:47:20', null);
INSERT INTO `t_dict_info` VALUES ('72', 'foodClassNum', '0', '食品分类号', '食品分类号', '2018-06-25 18:48:16', '2018-06-25 18:48:16', null);
INSERT INTO `t_dict_info` VALUES ('73', 'maximum', '0', '最大使用量', '最大使用量', '2018-06-25 18:49:52', '2018-06-25 18:49:52', null);
INSERT INTO `t_dict_info` VALUES ('74', 'deterStand', '0', '判定标准', '判定标准', '2018-06-25 18:54:54', '2018-06-25 18:54:54', null);
INSERT INTO `t_dict_info` VALUES ('75', 'mthStandNo', '0', '方法标准编号', '方法标准编号', '2018-06-26 14:59:32', '2018-06-26 14:59:32', null);
INSERT INTO `t_dict_info` VALUES ('76', 'bounds', '0', '限量值', '限量值', '2018-06-28 11:18:08', '2018-06-28 11:18:08', null);
INSERT INTO `t_dict_info` VALUES ('77', 'deteMthName', '0', '检测方法名称', '检测方法名称', '2018-06-28 11:18:54', '2018-06-28 11:18:54', null);
INSERT INTO `t_dict_info` VALUES ('78', 'deteBasis', '0', '检测依据', '检测依据', '2018-06-28 11:19:35', '2018-06-28 11:19:35', null);
INSERT INTO `t_dict_info` VALUES ('79', 'additive', '0', '添加剂', '添加剂', '2018-07-04 10:23:31', '2018-07-04 10:23:31', null);
INSERT INTO `t_dict_info` VALUES ('80', 'topFoodCategory', '0', '食品类别一级', null, '2018-07-04 15:38:27', '2018-07-04 15:38:27', null);
INSERT INTO `t_dict_info` VALUES ('81', 'secondFoodCategory', '0', '食品类别二级', null, '2018-07-04 15:44:24', '2018-07-04 15:44:24', null);
INSERT INTO `t_dict_info` VALUES ('82', 'threeFoodCategory', '0', '食品类别三级', null, '2018-07-04 15:52:02', '2018-07-04 15:52:02', null);
INSERT INTO `t_dict_info` VALUES ('83', 'mycotoxin', '0', '真菌毒素', null, '2018-07-04 15:56:37', '2018-07-04 15:56:37', null);
INSERT INTO `t_dict_info` VALUES ('84', 'contaminants', '0', '污染物', null, '2018-07-04 16:05:06', '2018-07-04 16:05:06', null);
INSERT INTO `t_dict_info` VALUES ('85', 'pesticideResidues', '0', '农药残留物', null, '2018-07-04 16:24:35', '2018-07-04 16:24:35', null);
INSERT INTO `t_dict_info` VALUES ('86', 'mainPurpose', '0', '主要用途', null, '2018-07-04 16:26:25', '2018-07-04 16:26:25', null);
INSERT INTO `t_dict_info` VALUES ('87', 'ADI', '0', 'ADI值', null, '2018-07-04 16:29:44', '2018-07-04 16:29:44', null);
INSERT INTO `t_dict_info` VALUES ('88', 'maxResidueLimit', '0', '最大残留限量', null, '2018-07-04 16:31:31', '2018-07-04 16:31:31', null);
INSERT INTO `t_dict_info` VALUES ('89', 'topFoodName', '0', '食品名称一级', null, '2018-07-04 16:33:52', '2018-07-04 16:33:52', null);
INSERT INTO `t_dict_info` VALUES ('90', 'secondFoodName', '0', '食品名称二级', null, '2018-07-04 16:34:24', '2018-07-04 16:34:24', null);
