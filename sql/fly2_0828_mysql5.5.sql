/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : mytest2

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2015-08-28 09:24:17
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `cfg_bean`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_bean`;
CREATE TABLE `cfg_bean` (
  `id` varchar(32) NOT NULL,
  `bean` varchar(20) DEFAULT NULL COMMENT '大写字母开头的BEANNAME',
  `descr` varchar(20) DEFAULT NULL,
  `isFlow` varchar(1) DEFAULT NULL COMMENT '是否属于流程处理',
  `descrProperty` varchar(20) DEFAULT NULL,
  `pkg` varchar(10) DEFAULT NULL,
  `tableName` varchar(20) DEFAULT NULL,
  `usrct` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_bean
-- ----------------------------
INSERT INTO `cfg_bean` VALUES ('1', 'Bean', '配置类', '0', null, 'cfg', 'cfg_bean', 'ggg');
INSERT INTO `cfg_bean` VALUES ('13', 'Org', '组织', '0', 'descr', 'cfg', 'cfg_org', 'ggg');
INSERT INTO `cfg_bean` VALUES ('14', 'Quote', '引用关系', '0', null, 'cfg', 'cfg_quote', 'ggg');
INSERT INTO `cfg_bean` VALUES ('15', 'Permission', '功能权限', '', 'descr', 'cfg', 'cfg_permission', 'ggg');
INSERT INTO `cfg_bean` VALUES ('16', 'Role', '角色', '', 'descr', 'cfg', 'cfg_role', 'ggg');
INSERT INTO `cfg_bean` VALUES ('17', 'OrgRole', '组织中的角色', '', 'id', 'cfg', 'cfg_org_role', 'ggg');
INSERT INTO `cfg_bean` VALUES ('18', 'OrgRoleMember', '组织角色中的人员', '', 'id', 'cfg', 'cfg_orgrole_member', 'ggg');
INSERT INTO `cfg_bean` VALUES ('19', 'RolePermission', '角色功能', '', 'id', 'cfg', 'cfg_role_permission', 'ggg');
INSERT INTO `cfg_bean` VALUES ('2', 'BeanState', '类状态1', '0', null, 'cfg', null, 'ggg');
INSERT INTO `cfg_bean` VALUES ('20', 'RolePerf', '角色表现', '', 'id', 'cfg', 'cfg_role_perf', 'ggg');
INSERT INTO `cfg_bean` VALUES ('21', 'Filter', '过滤', '', 'descr', 'cfg', 'cfg_filter', 'ggg');
INSERT INTO `cfg_bean` VALUES ('22', 'FilterProperty', '过滤属性', '', 'id', 'cfg', 'cfg_filter_property', 'ggg');
INSERT INTO `cfg_bean` VALUES ('23', 'RoleFilter', '角色过滤', '', 'id', 'cfg', 'cfg_role_filter', 'ggg');
INSERT INTO `cfg_bean` VALUES ('26', 'Cons', '常量', null, 'descr', 'cfg', 'cfg_cons', 'ggg');
INSERT INTO `cfg_bean` VALUES ('27', 'Domain', '域', null, 'descr', 'cfg', 'cfg_domain', 'ggg');
INSERT INTO `cfg_bean` VALUES ('3', 'Member', '人员', '0', 'descr', 'cfg', 'cfg_member', 'ggg');
INSERT INTO `cfg_bean` VALUES ('35', 'RoleMember', '角色--人员', '0', '', 'cfg', 'cfg_role_member', 'ggg');
INSERT INTO `cfg_bean` VALUES ('36', 'RoleAtt', '角色--组织属性', '0', '', 'cfg', 'cfg_role_att', 'ggg');
INSERT INTO `cfg_bean` VALUES ('37', 'OrgMember', '组织--人员', '', '', 'cfg', 'cfg_org_member', 'ggg');
INSERT INTO `cfg_bean` VALUES ('38', 'RoleOrg', '角色--组织', '', '', 'cfg', 'cfg_role_org', 'ggg');
INSERT INTO `cfg_bean` VALUES ('4', 'Myinput', 'Input', '0', 'descr', 'cfg', 'cfg_myinput', 'ggg');
INSERT INTO `cfg_bean` VALUES ('402881e64f54de1a014f5893fa0c0009', 'MemberEdit', '人员修改自助', '1', '', 'cfg', 'cfg_member_edit', 'ggg');
INSERT INTO `cfg_bean` VALUES ('5', 'Perf', 'Perf显示', '0', 'descr', 'cfg', 'cfg_perf', 'ggg');
INSERT INTO `cfg_bean` VALUES ('6', 'PerfProperty', '表现属性', '0', 'descr', 'cfg', 'cfg_perf_property', 'ggg');
INSERT INTO `cfg_bean` VALUES ('7', 'PerfState', '显示类对应的状态', '0', null, 'cfg', null, 'ggg');
INSERT INTO `cfg_bean` VALUES ('8', 'Property', '类属性', '0', 'descr', 'cfg', 'cfg_property', 'ggg');

-- ----------------------------
-- Table structure for `cfg_bean_state`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_bean_state`;
CREATE TABLE `cfg_bean_state` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'bean的状态',
  `bean` int(11) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  `descr` varchar(20) DEFAULT NULL,
  `ord` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_bean_state
-- ----------------------------

-- ----------------------------
-- Table structure for `cfg_cons`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_cons`;
CREATE TABLE `cfg_cons` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `enable` varchar(2) DEFAULT NULL,
  `domain` varchar(32) DEFAULT NULL,
  `ord` int(11) DEFAULT NULL,
  `usrct` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_cons
-- ----------------------------
INSERT INTO `cfg_cons` VALUES ('19', '隐藏', '0', '隐藏', '1', '4', '0', 'ggg');
INSERT INTO `cfg_cons` VALUES ('20', '显示', '1', '显示', '1', '4', '0', 'ggg');
INSERT INTO `cfg_cons` VALUES ('21', '编辑', '2', '编辑', '1', '4', '0', 'ggg');
INSERT INTO `cfg_cons` VALUES ('33', '无效', '-1', '无效', '1', '2', '0', 'ggg');
INSERT INTO `cfg_cons` VALUES ('35', '是', '1', '是', '1', '7', '0', 'ggg');
INSERT INTO `cfg_cons` VALUES ('36', '否', '0', '否', '1', '7', '0', 'ggg');
INSERT INTO `cfg_cons` VALUES ('37', '总经理', '1', '总经理', '1', '8', '0', 'ggg');
INSERT INTO `cfg_cons` VALUES ('38', '财务', '2', '财务', '1', '8', '0', 'ggg');
INSERT INTO `cfg_cons` VALUES ('39', '计调', '3', '计调', '1', '8', '0', 'ggg');
INSERT INTO `cfg_cons` VALUES ('40', '本店财务', '4', '本店财务', '1', '8', '0', 'ggg');
INSERT INTO `cfg_cons` VALUES ('402881e64f54de1a014f5881ba6e0007', '人事', '5', '人事', '1', '8', '0', 'ggg');
INSERT INTO `cfg_cons` VALUES ('8', '有效', '1', '有效', '1', '2', '0', 'ggg');
INSERT INTO `cfg_cons` VALUES ('8a8a11de4f3953db014f3957be670001', 't', 't', '1', '-2', '2', '0', 'ggg');
INSERT INTO `cfg_cons` VALUES ('9', '草稿', '0', '草稿', '1', '2', '0', 'ggg');

-- ----------------------------
-- Table structure for `cfg_domain`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_domain`;
CREATE TABLE `cfg_domain` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `domain` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `enable` varchar(2) DEFAULT NULL,
  `pkg` varchar(5) DEFAULT NULL,
  `usrct` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_domain
-- ----------------------------
INSERT INTO `cfg_domain` VALUES ('2', 'enable', '有效位', '1', 'cfg', 'ggg');
INSERT INTO `cfg_domain` VALUES ('4', 'cfg_perfproperty_kind', '显示方式', '1', 'cfg', 'ggg');
INSERT INTO `cfg_domain` VALUES ('7', 'yorn', '是否', '1', 'cfg', 'ggg');
INSERT INTO `cfg_domain` VALUES ('8', 'cfg_org_att', '组织属性', '1', 'cfg', 'ggg');

-- ----------------------------
-- Table structure for `cfg_filter`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_filter`;
CREATE TABLE `cfg_filter` (
  `id` varchar(32) NOT NULL,
  `permission` varchar(32) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `ord` varchar(3) DEFAULT NULL,
  `usrct` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_filter
-- ----------------------------
INSERT INTO `cfg_filter` VALUES ('1', '50', '只能看到本店人员', '10', 'ggg');
INSERT INTO `cfg_filter` VALUES ('2', '51', '只能增加本店人员', '10', 'ggg');
INSERT INTO `cfg_filter` VALUES ('3', '52', '只能编辑本店人员', '10', 'ggg');
INSERT INTO `cfg_filter` VALUES ('4', '52', '只能编辑自己信息', '90', 'ggg');

-- ----------------------------
-- Table structure for `cfg_filter_property`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_filter_property`;
CREATE TABLE `cfg_filter_property` (
  `id` varchar(32) NOT NULL,
  `filter` varchar(32) DEFAULT NULL,
  `property` varchar(32) DEFAULT NULL,
  `op` varchar(255) DEFAULT NULL,
  `val` varchar(255) DEFAULT NULL,
  `clazz` varchar(255) DEFAULT NULL,
  `usrct` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_filter_property
-- ----------------------------
INSERT INTO `cfg_filter_property` VALUES ('1', '1', '83', 'in', ':currentOrgAndUnder', 'List<Org>', 'ggg');
INSERT INTO `cfg_filter_property` VALUES ('2', '4', '82', '==', ':currentMemberId', 'Integer', 'ggg');
INSERT INTO `cfg_filter_property` VALUES ('3', '2', '84', 'in', ':currentOrgAndUnderIds', 'List', 'ggg');
INSERT INTO `cfg_filter_property` VALUES ('4', '3', '84', 'in', ':currentOrgAndUnderIds', 'List<Org>', 'ggg');

-- ----------------------------
-- Table structure for `cfg_member`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_member`;
CREATE TABLE `cfg_member` (
  `id` varchar(32) NOT NULL COMMENT '人员表',
  `org` varchar(32) DEFAULT NULL,
  `loginId` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `psd` varchar(255) DEFAULT NULL,
  `usrct` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_member
-- ----------------------------
INSERT INTO `cfg_member` VALUES ('1', '2', 'admin', '管理员1', '2', 'ggg');
INSERT INTO `cfg_member` VALUES ('17', '3', '770005', '666', '8804', 'ggg');
INSERT INTO `cfg_member` VALUES ('18', '3', '770012', '555', '123321', 'ggg');
INSERT INTO `cfg_member` VALUES ('19', '2', '770001', '444', '200810', 'ggg');
INSERT INTO `cfg_member` VALUES ('20', '2', 'gladmin', '333', '111234', 'ggg');
INSERT INTO `cfg_member` VALUES ('21', '2', '770002', '222', '860731', 'ggg');

-- ----------------------------
-- Table structure for `cfg_member_edit`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_member_edit`;
CREATE TABLE `cfg_member_edit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member` varchar(32) DEFAULT NULL,
  `enable` varchar(2) DEFAULT NULL,
  `createDate` varchar(20) DEFAULT NULL,
  `createMember` int(11) DEFAULT NULL,
  `updateDate` varchar(20) DEFAULT NULL,
  `updateMember` int(11) DEFAULT NULL,
  `ws0` varchar(255) DEFAULT NULL,
  `ws1` varchar(255) DEFAULT NULL,
  `ws2` varchar(255) DEFAULT NULL,
  `ws3` varchar(255) DEFAULT NULL,
  `ws4` varchar(255) DEFAULT NULL,
  `ws5` varchar(255) DEFAULT NULL,
  `ws6` varchar(255) DEFAULT NULL,
  `ws7` varchar(255) DEFAULT NULL,
  `ws8` varchar(255) DEFAULT NULL,
  `ws9` varchar(255) DEFAULT NULL,
  `usrct` varchar(4) DEFAULT NULL,
  `ndescr` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_member_edit
-- ----------------------------

-- ----------------------------
-- Table structure for `cfg_myinput`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_myinput`;
CREATE TABLE `cfg_myinput` (
  `id` varchar(11) NOT NULL,
  `descr` varchar(10) DEFAULT NULL,
  `type` varchar(15) DEFAULT NULL,
  `usrct` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_myinput
-- ----------------------------
INSERT INTO `cfg_myinput` VALUES ('1', '文本', 'text', 'ggg');
INSERT INTO `cfg_myinput` VALUES ('11', '两位小数', 'double2', 'ggg');
INSERT INTO `cfg_myinput` VALUES ('12', '日期范围', 'sdate', 'ggg');
INSERT INTO `cfg_myinput` VALUES ('13', '数值', 'numberbox', 'ggg');
INSERT INTO `cfg_myinput` VALUES ('14', '文本区域', 'textarea', 'ggg');
INSERT INTO `cfg_myinput` VALUES ('15', '是否有效', 'radioEnable', 'ggg');
INSERT INTO `cfg_myinput` VALUES ('2', '日期', 'date', 'ggg');
INSERT INTO `cfg_myinput` VALUES ('3', '选择', 'select', 'ggg');
INSERT INTO `cfg_myinput` VALUES ('4', '是否', 'radio', 'ggg');
INSERT INTO `cfg_myinput` VALUES ('5', '弹出框', 'pop', 'ggg');
INSERT INTO `cfg_myinput` VALUES ('6', '树', 'tree', 'ggg');

-- ----------------------------
-- Table structure for `cfg_org`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_org`;
CREATE TABLE `cfg_org` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `orgCode` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `parent` varchar(32) DEFAULT NULL,
  `pkg` varchar(5) DEFAULT NULL,
  `offi` varchar(2) DEFAULT NULL COMMENT '1 行政组织 2 岗位组织',
  `att1` varchar(2) DEFAULT NULL COMMENT '组织的性质',
  `att2` varchar(2) DEFAULT NULL,
  `att3` varchar(2) DEFAULT NULL,
  `usrct` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_org
-- ----------------------------
INSERT INTO `cfg_org` VALUES ('2', '100001', '总公司', null, 'cfg', '1', null, null, null, 'ggg');
INSERT INTO `cfg_org` VALUES ('3', '10100', '1店', '2', '', '1', null, null, null, 'ggg');
INSERT INTO `cfg_org` VALUES ('4', '10200', '2店', '2', '', '1', null, null, null, 'ggg');
INSERT INTO `cfg_org` VALUES ('402881e64f54de1a014f5882299d0008', '10000105', '人事', '2', null, '0', '5', '', '', 'ggg');
INSERT INTO `cfg_org` VALUES ('5', '10300', '3店', '2', '', '1', null, null, null, 'ggg');
INSERT INTO `cfg_org` VALUES ('6', '10400', '4店', '2', '', '1', null, null, null, 'ggg');
INSERT INTO `cfg_org` VALUES ('7', '10500', '5店', '2', '', '1', null, null, null, 'ggg');
INSERT INTO `cfg_org` VALUES ('8', null, '公司财务', '2', null, '0', '2', null, null, 'ggg');
INSERT INTO `cfg_org` VALUES ('9', null, '调度', '3', null, '0', '3', null, null, 'ggg');

-- ----------------------------
-- Table structure for `cfg_org_member`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_org_member`;
CREATE TABLE `cfg_org_member` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `org` varchar(32) DEFAULT NULL,
  `member` varchar(32) DEFAULT NULL,
  `enable` varchar(2) DEFAULT NULL,
  `createDate` varchar(20) DEFAULT NULL,
  `createMember` varchar(32) DEFAULT NULL,
  `updateDate` varchar(20) DEFAULT NULL,
  `updateMember` varchar(32) DEFAULT NULL,
  `ws0` varchar(255) DEFAULT NULL,
  `ws1` varchar(255) DEFAULT NULL,
  `ws2` varchar(255) DEFAULT NULL,
  `ws3` varchar(255) DEFAULT NULL,
  `ws4` varchar(255) DEFAULT NULL,
  `ws5` varchar(255) DEFAULT NULL,
  `ws6` varchar(255) DEFAULT NULL,
  `ws7` varchar(255) DEFAULT NULL,
  `ws8` varchar(255) DEFAULT NULL,
  `ws9` varchar(255) DEFAULT NULL,
  `usrct` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_org_member
-- ----------------------------
INSERT INTO `cfg_org_member` VALUES ('1', '2', '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_org_member` VALUES ('4', '2', '18', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_org_member` VALUES ('5', '2', '19', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_org_member` VALUES ('8a8a11de4f398391014f3aa5ace70021', '5', '17', '1', '2015-08-17 15:53', '1', '', null, '', '', '', '', '', '', '', '', '', '', 'ggg');

-- ----------------------------
-- Table structure for `cfg_org_role`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_org_role`;
CREATE TABLE `cfg_org_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_org_role
-- ----------------------------
INSERT INTO `cfg_org_role` VALUES ('1', '2', '1');
INSERT INTO `cfg_org_role` VALUES ('2', '3', '2');
INSERT INTO `cfg_org_role` VALUES ('3', '2', '3');
INSERT INTO `cfg_org_role` VALUES ('4', '3', '4');
INSERT INTO `cfg_org_role` VALUES ('5', '2', '5');
INSERT INTO `cfg_org_role` VALUES ('6', '2', '6');
INSERT INTO `cfg_org_role` VALUES ('7', '5', '4');
INSERT INTO `cfg_org_role` VALUES ('8', '5', '2');
INSERT INTO `cfg_org_role` VALUES ('9', '4', '2');
INSERT INTO `cfg_org_role` VALUES ('10', '4', '4');
INSERT INTO `cfg_org_role` VALUES ('11', '5', '7');
INSERT INTO `cfg_org_role` VALUES ('12', '3', '7');
INSERT INTO `cfg_org_role` VALUES ('13', '4', '7');
INSERT INTO `cfg_org_role` VALUES ('14', '6', '4');
INSERT INTO `cfg_org_role` VALUES ('15', '6', '2');
INSERT INTO `cfg_org_role` VALUES ('16', '6', '7');
INSERT INTO `cfg_org_role` VALUES ('17', '7', '4');
INSERT INTO `cfg_org_role` VALUES ('18', '7', '2');
INSERT INTO `cfg_org_role` VALUES ('19', '7', '7');

-- ----------------------------
-- Table structure for `cfg_orgrole_member`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_orgrole_member`;
CREATE TABLE `cfg_orgrole_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orgRole` int(11) DEFAULT NULL,
  `member` int(11) DEFAULT NULL,
  `org` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_orgrole_member
-- ----------------------------
INSERT INTO `cfg_orgrole_member` VALUES ('1', '1', '1', null);
INSERT INTO `cfg_orgrole_member` VALUES ('2', '3', '4', null);
INSERT INTO `cfg_orgrole_member` VALUES ('3', '4', '17', null);
INSERT INTO `cfg_orgrole_member` VALUES ('4', '2', '18', null);
INSERT INTO `cfg_orgrole_member` VALUES ('5', '5', '19', null);
INSERT INTO `cfg_orgrole_member` VALUES ('6', '6', '20', null);
INSERT INTO `cfg_orgrole_member` VALUES ('7', '5', '21', null);
INSERT INTO `cfg_orgrole_member` VALUES ('8', '7', '22', null);
INSERT INTO `cfg_orgrole_member` VALUES ('9', '17', '23', null);
INSERT INTO `cfg_orgrole_member` VALUES ('10', '14', '24', null);
INSERT INTO `cfg_orgrole_member` VALUES ('11', '18', '25', null);
INSERT INTO `cfg_orgrole_member` VALUES ('12', '9', '26', null);
INSERT INTO `cfg_orgrole_member` VALUES ('13', '9', '27', null);
INSERT INTO `cfg_orgrole_member` VALUES ('14', '9', '28', null);
INSERT INTO `cfg_orgrole_member` VALUES ('15', '10', '5', null);
INSERT INTO `cfg_orgrole_member` VALUES ('16', '8', '30', null);
INSERT INTO `cfg_orgrole_member` VALUES ('17', '8', '32', null);
INSERT INTO `cfg_orgrole_member` VALUES ('18', '2', '31', null);
INSERT INTO `cfg_orgrole_member` VALUES ('19', '11', '32', null);
INSERT INTO `cfg_orgrole_member` VALUES ('20', '12', '18', null);
INSERT INTO `cfg_orgrole_member` VALUES ('21', '18', '33', null);
INSERT INTO `cfg_orgrole_member` VALUES ('22', '5', '34', null);
INSERT INTO `cfg_orgrole_member` VALUES ('23', '16', '24', null);
INSERT INTO `cfg_orgrole_member` VALUES ('24', '15', '35', null);
INSERT INTO `cfg_orgrole_member` VALUES ('25', '15', '36', null);
INSERT INTO `cfg_orgrole_member` VALUES ('26', '16', '36', null);
INSERT INTO `cfg_orgrole_member` VALUES ('27', '19', '25', null);
INSERT INTO `cfg_orgrole_member` VALUES ('28', '2', '37', null);

-- ----------------------------
-- Table structure for `cfg_perf`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_perf`;
CREATE TABLE `cfg_perf` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `bean` varchar(32) DEFAULT NULL,
  `permission` varchar(32) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `kind` varchar(10) DEFAULT NULL COMMENT 'LIST、EDIT、ADD、DETAIL、EXPORT',
  `ord` varchar(2) DEFAULT NULL COMMENT '多个同Permission的perf需要显示其中一个Perf的时候',
  `perf` varchar(11) DEFAULT NULL COMMENT '人员的权限组对应多个同kind的不同perf时，弹出页面让用户做选择某种perf显示方式，当处于流程编辑时，默认选择与流程当前状态相匹配的perf（详见cfg_perf_state）',
  `layout` varchar(5000) DEFAULT NULL,
  `usrct` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_perf
-- ----------------------------
INSERT INTO `cfg_perf` VALUES ('1', '1', '1', '列表显示集合', 'List', '10', 'listps', '', 'ggg');
INSERT INTO `cfg_perf` VALUES ('10', '13', '12', '组织列表', null, null, null, '', 'ggg');
INSERT INTO `cfg_perf` VALUES ('11', '8', '13', '属性增加', 'property', null, 'add', null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('12', '15', '15', '功能列表', null, null, null, 'null', 'ggg');
INSERT INTO `cfg_perf` VALUES ('128', '38', '168', '角色--组织', null, '', null, '', 'ggg');
INSERT INTO `cfg_perf` VALUES ('129', '38', '169', '增加角色--组织', null, '', null, '', 'ggg');
INSERT INTO `cfg_perf` VALUES ('13', '15', '16', '增加功能', null, null, null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('130', '38', '170', '修改角色--组织', null, '', null, '', 'ggg');
INSERT INTO `cfg_perf` VALUES ('131', '38', '171', '详细角色--组织', null, '', null, '', 'ggg');
INSERT INTO `cfg_perf` VALUES ('14', '5', '18', '表现列表', null, null, null, '', 'ggg');
INSERT INTO `cfg_perf` VALUES ('15', '5', '19', '增加表现', null, null, null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('16', '6', '21', '表现属性列表', null, null, null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('17', '6', '22', '增加表现属性', null, null, null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('18', '4', '24', '控件列表', null, null, null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('19', '4', '25', '增加控件', null, null, null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('2', '1', null, '过滤条件显示集合', 'Search', null, 'searps', null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('20', '15', '26', '功能详细', null, null, null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('21', '5', '27', '表现详细', null, null, null, '[{\"kind\":1},{\"kind\":2}]', 'ggg');
INSERT INTO `cfg_perf` VALUES ('22', '15', '29', '修改功能', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('23', '8', '32', '修改属性', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('24', '8', '33', '属性详细', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('25', '6', '34', '修改表现属性', null, '', null, '', 'ggg');
INSERT INTO `cfg_perf` VALUES ('26', '6', '35', '详细表现属性', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('27', '13', '36', '增加组织', null, '', null, '', 'ggg');
INSERT INTO `cfg_perf` VALUES ('28', '13', '37', '修改组织', null, '', null, '', 'ggg');
INSERT INTO `cfg_perf` VALUES ('29', '13', '38', '详细组织', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('3', '1', '2', '增加类', 'Add', null, 'add', '[{\"descr\":\"增加类\",\"kind\":1,\"tds\":[[{\"colspan\":12,\"descr\":\"增加类后会相应的在数据库中增加对应table\",\"style\":\"color:red\"}],[{\"pn\":\"id\",\"colspan\":3},{\"pn\":\"bean\",\"colspan\":3},{\"pn\":\"descr\",\"colspan\":6}],[{\"pn\":\"isFlow\",\"colspan\":3},{\"pn\":\"pkg\",\"colspan\":3},{\"pn\":\"descrProperty\",\"colspan\":3},{\"pn\":\"tableName\",\"colspan\":3}]]}]', 'ggg');
INSERT INTO `cfg_perf` VALUES ('30', '16', '40', '列表角色', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('31', '16', '41', '增加角色', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('32', '16', '43', '明细角色', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('33', '17', '45', '列表组织中的角色', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('39', '3', '50', '列表人员', null, '10', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('40', '3', '51', '增加人员', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('402881e64f54de1a014f589fb8d0002b', '402881e64f54de1a014f5893fa0c0009', '402881e64f54de1a014f589fb6ee0024', '人员修改自助', null, '', null, '', '');
INSERT INTO `cfg_perf` VALUES ('402881e64f54de1a014f589fb907002c', '402881e64f54de1a014f5893fa0c0009', '402881e64f54de1a014f589fb72e0025', '增加人员修改自助', null, '', null, '', '');
INSERT INTO `cfg_perf` VALUES ('402881e64f54de1a014f589fb927002d', '402881e64f54de1a014f5893fa0c0009', '402881e64f54de1a014f589fb7450026', '修改人员修改自助', null, '', null, '', '');
INSERT INTO `cfg_perf` VALUES ('402881e64f54de1a014f589fb961002e', '402881e64f54de1a014f5893fa0c0009', '402881e64f54de1a014f589fb7890027', '详细人员修改自助', null, '', null, '', '');
INSERT INTO `cfg_perf` VALUES ('42', '3', '52', '编辑人员', null, '10', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('43', '3', '53', '详细人员', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('45', '17', '46', '增加组织中的角色', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('46', '17', '47', '修改组织中的角色', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('47', '17', '48', '详细组织中的角色', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('48', '18', '55', '列表组织角色人员', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('49', '18', '56', '增加组织角色人员', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('5', null, '1', '列表显示集合2', 'List', '20', 'listps', '', 'ggg');
INSERT INTO `cfg_perf` VALUES ('50', '18', '57', '修改组织角色人员', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('51', '18', '58', '详细组织角色人员', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('52', '19', '60', '角色功能', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('53', '19', '61', '增加角色功能', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('54', '19', '62', '修改角色功能', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('55', '19', '63', '详细角色功能', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('56', '3', '50', '人员(查看)', null, '30', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('57', '5', '30', '修改表现', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('58', '3', '52', '编辑人员（自己）', null, '20', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('59', '20', '65', '角色表现', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('6', '1', '6', '类详细', 'Detail', null, 'detailps', '[{\"descr\":\"类详细\",\"kind\":1,\"tds\":[[{\"colspan\":12,\"descr\":\"类详细\",\"style\":\"color:red\"}],[{\"pn\":\"id\",\"colspan\":3},{\"pn\":\"bean\",\"colspan\":3},{\"pn\":\"descr\",\"colspan\":6}],[{\"pn\":\"isFlow\",\"colspan\":3},{\"pn\":\"pkg\",\"colspan\":3},{\"pn\":\"descrProperty\",\"colspan\":3},{\"pn\":\"tableName\",\"colspan\":3}]]},{\"descr\":\"类属性\",\"kind\":2,\"quote\":1}]', 'ggg');
INSERT INTO `cfg_perf` VALUES ('60', '20', '66', '新增角色表现', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('61', '20', '67', '修改角色表现', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('62', '20', '68', '详细角色表现', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('63', '21', '71', '过滤', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('64', '21', '72', '增加过滤', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('65', '21', '73', '编辑过滤', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('66', '21', '74', '详细过滤', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('68', '22', '75', '过滤属性', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('69', '22', '76', '增加过滤属性', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('7', '1', '7', '类修改', 'Edit', null, 'edit', '[{\"descr\":\"类修改\",\"kind\":1,\"tds\":[[{\"colspan\":12,\"descr\":\"类修改\",\"style\":\"color:red\"}],[{\"pn\":\"bean\",\"colspan\":6},{\"pn\":\"descr\",\"colspan\":6}],[{\"pn\":\"isFlow\",\"colspan\":3},{\"pn\":\"pkg\",\"colspan\":3},{\"pn\":\"descrProperty\",\"colspan\":3},{\"pn\":\"tableName\",\"colspan\":3}]]},{\"descr\":\"类属性\",\"kind\":2}]', 'ggg');
INSERT INTO `cfg_perf` VALUES ('70', '22', '77', '修改过滤属性', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('71', '22', '78', '详细过滤属性', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('72', '23', '80', '角色过滤', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('73', '23', '81', '新增角色过滤', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('74', '23', '82', '修改角色过滤', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('75', '23', '83', '详细角色过滤', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('76', '16', '42', '编辑角色', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('89', '26', '102', '常量', null, '', null, 'null', 'ggg');
INSERT INTO `cfg_perf` VALUES ('8a8a11de4f398391014f3a9e77b10009', '37', '8a8a11de4f398391014f3a9e76af0002', '组织--人员', null, '', null, '', 'ggg');
INSERT INTO `cfg_perf` VALUES ('8a8a11de4f398391014f3a9e77cd000a', '37', '8a8a11de4f398391014f3a9e76eb0003', '增加组织--人员', null, '', null, '', 'ggg');
INSERT INTO `cfg_perf` VALUES ('8a8a11de4f398391014f3a9e77e8000b', '37', '8a8a11de4f398391014f3a9e77070004', '修改组织--人员', null, '', null, '', 'ggg');
INSERT INTO `cfg_perf` VALUES ('8a8a11de4f398391014f3a9e7804000c', '37', '8a8a11de4f398391014f3a9e77220005', '详细组织--人员', null, '', null, '', 'ggg');
INSERT INTO `cfg_perf` VALUES ('9', '8', '10', '属性列表', 'property', null, 'list', '', 'ggg');
INSERT INTO `cfg_perf` VALUES ('90', '26', '103', '增加常量', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('91', '26', '104', '修改常量', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('92', '26', '105', '详细常量', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('93', '27', '107', '域', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('94', '27', '108', '增加域', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('95', '27', '109', '修改域', null, '', null, null, 'ggg');
INSERT INTO `cfg_perf` VALUES ('96', '27', '110', '详细域', null, '', null, null, 'ggg');

-- ----------------------------
-- Table structure for `cfg_perf_property`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_perf_property`;
CREATE TABLE `cfg_perf_property` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `perf` varchar(32) DEFAULT NULL,
  `property` varchar(32) DEFAULT NULL,
  `editshowhide` varchar(1) DEFAULT NULL COMMENT '0-hide，1-show，2-edit',
  `kind` varchar(11) DEFAULT NULL COMMENT '一个perf上对于一个Property有多个用途',
  `myinput` varchar(32) DEFAULT NULL,
  `required` varchar(1) DEFAULT NULL,
  `valueurl` varchar(200) DEFAULT NULL,
  `validType` varchar(20) DEFAULT NULL,
  `dftvalue` varchar(20) DEFAULT NULL,
  `width` varchar(5) DEFAULT NULL COMMENT 'list中使用',
  `min` varchar(5) DEFAULT NULL,
  `max` varchar(5) DEFAULT NULL,
  `showvalue` varchar(200) DEFAULT NULL COMMENT '对于域中的value，需要如何显示成他的descr。如何定义这个指代关系，使得程序能够默认去显示对应的descr',
  `usrct` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='不同示显方式对应不同的字段属性的是否可见可写';

-- ----------------------------
-- Records of cfg_perf_property
-- ----------------------------
INSERT INTO `cfg_perf_property` VALUES ('1', '1', '1', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('100', '20', '35', '1', null, null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1000', '39', '84', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1001', '39', '85', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1002', '39', '86', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1003', '39', '87', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1004', '56', '84', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1005', '56', '85', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1006', '56', '86', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1007', '56', '87', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1008', '10', '17', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1009', '10', '18', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('101', '21', '40', '1', null, null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1010', '10', '20', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1011', '10', '21', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1012', '30', '73', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1013', '30', '74', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1014', '30', '194', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1015', '33', '77', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1016', '33', '78', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1017', '33', '80', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1018', '33', '81', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1019', '63', '113', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('102', '21', '43', '1', null, null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1020', '63', '114', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1021', '63', '115', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1022', '63', '116', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1023', '63', '117', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1024', '63', '118', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1025', '68', '121', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1026', '68', '122', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1027', '68', '124', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1028', '68', '125', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1029', '68', '126', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1030', '68', '127', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1031', '68', '128', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1032', '42', '88', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('105', '22', '31', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('106', '22', '38', '2', '', '6', '', 'cfg_treePermission.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('107', '22', '34', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('109', '23', '7', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('110', '23', '15', '2', '', '6', '', 'cfg_treeBean.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('111', '23', '9', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('112', '23', '10', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('113', '23', '11', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1131', '65', '114', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1132', '65', '115', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1133', '65', '116', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1134', '70', '122', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1135', '70', '125', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1136', '93', '191', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1137', '93', '192', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1138', '93', '193', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1139', '89', '183', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('114', '23', '12', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1140', '89', '184', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1141', '89', '185', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1142', '89', '186', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1143', '89', '187', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1144', '89', '189', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1145', '89', '190', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1146', '11', '206', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('115', '23', '14', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('116', '24', '7', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('117', '24', '13', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('118', '24', '9', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('119', '24', '10', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('12', '6', '1', '1', null, null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('120', '24', '11', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('121', '24', '12', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('122', '24', '14', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('123', '22', '33', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('124', '22', '35', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('125', '22', '36', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('126', '22', '37', '2', '', '4', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('127', '10', '16', '1', 'List', '7', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('13', '6', '2', '1', null, null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('135', '25', '45', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('136', '25', '66', '2', '', '6', '', 'cfg_treePerf.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('138', '25', '68', '2', '', '6', '', 'cfg_treeProperty.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('139', '25', '70', '2', '', '3', '', 'cfg_selectMyinput.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1397', '6', '3', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1398', '6', '4', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1399', '6', '5', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('14', '7', '1', '0', null, null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('140', '25', '48', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1400', '6', '29', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1401', '6', '30', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('141', '25', '49', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('142', '25', '51', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('143', '25', '52', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('144', '25', '53', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('145', '25', '54', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('146', '25', '55', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('147', '25', '56', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('148', '25', '57', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('149', '25', '58', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('15', '7', '2', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('150', '26', '45', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('151', '26', '67', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('152', '26', '69', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('153', '26', '71', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1530', '21', '44', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1531', '21', '62', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1532', '21', '63', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1533', '21', '64', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1534', '21', '89', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1535', '21', '90', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1536', '25', '67', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1537', '25', '69', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1538', '25', '71', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('154', '26', '48', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('155', '26', '49', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('156', '26', '51', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('157', '26', '52', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('158', '26', '53', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('159', '26', '54', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('16', '7', '3', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('160', '26', '55', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('161', '26', '56', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1616', '9', '15', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1618', '9', '10', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1619', '9', '11', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('162', '26', '57', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1620', '9', '9', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1621', '14', '43', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1622', '12', '33', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1623', '12', '34', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1624', '12', '36', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1625', '12', '37', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1626', '12', '38', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('163', '26', '58', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1630', '1', '2', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1631', '10', '466', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1632', '10', '463', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1633', '10', '464', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1634', '10', '465', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1635', '28', '466', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1636', '28', '463', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1637', '28', '464', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('1638', '28', '465', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('164', '27', '17', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('165', '27', '18', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('166', '27', '20', '2', '', '6', '', 'cfg_treeOrg.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('167', '28', '16', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('168', '28', '17', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('169', '28', '18', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('17', '7', '4', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('170', '28', '20', '2', '', '6', '', 'cfg_treeOrg.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('172', '29', '16', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('173', '29', '17', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('174', '29', '18', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('175', '29', '20', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('176', '29', '21', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('177', '30', '72', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('18', '7', '5', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('182', '31', '73', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('183', '31', '74', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('184', '32', '72', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('185', '32', '73', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('186', '32', '74', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('187', '33', '75', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('19', '9', '7', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('200', '39', '82', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('211', '14', '89', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('212', '14', '90', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('215', '40', '82', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('216', '40', '84', '2', '', '6', '', 'cfg_treeOrg.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('218', '40', '86', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('219', '40', '87', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('220', '40', '88', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('225', '42', '82', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('226', '42', '84', '2', '', '6', '', 'cfg_treeOrg.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('227', '42', '86', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('228', '42', '87', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('230', '43', '82', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('231', '43', '84', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('232', '43', '85', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('233', '43', '86', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('234', '43', '87', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('235', '43', '88', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('24', '9', '12', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('246', '45', '75', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('247', '45', '77', '2', '', '6', '', 'cfg_treeOrg.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('248', '45', '80', '2', '', '3', '', 'cfg_selectRole.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('249', '46', '75', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('25', '9', '13', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('250', '46', '77', '2', '', '6', '', 'cfg_treeOrg.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('251', '46', '80', '2', '', '3', '', 'cfg_selectRole.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('252', '47', '75', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('253', '47', '77', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('254', '47', '78', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('255', '47', '80', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('256', '47', '81', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('257', '48', '91', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('26', '9', '14', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('263', '49', '91', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('264', '49', '93', '2', '', '6', '', 'cfg_treeOrgRole.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('265', '49', '95', '2', '', '5', '', 'cfg_findMember.sg?isPage=1&from=url', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('266', '50', '91', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('267', '50', '93', '2', '', '6', '', 'cfg_treeOrgRole.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('268', '50', '95', '2', '', '5', '', 'cfg_findMember.sg?isPage=1&from=url', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('269', '51', '91', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('270', '51', '93', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('271', '51', '95', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('272', '51', '96', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('273', '52', '97', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('274', '52', '99', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('275', '52', '99', '2', 'Search', '3', '', 'cfg_selectRole.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('276', '52', '100', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('277', '52', '101', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('278', '52', '101', '2', 'Search', '6', '', 'cfg_treePermission.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('279', '52', '102', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('280', '53', '97', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('281', '53', '99', '2', '', '3', '', 'cfg_selectRole.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('282', '53', '101', '2', '', '6', '', 'cfg_treePermission.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('283', '54', '97', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('284', '54', '99', '2', '', '3', '', 'cfg_selectRole.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('285', '54', '101', '2', '', '6', '', 'cfg_treePermission.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('286', '55', '97', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('287', '55', '99', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('288', '55', '100', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('289', '55', '101', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('29', '11', '7', '0', null, null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('290', '55', '102', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('291', '56', '82', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('3', '1', '3', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('30', '11', '9', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('301', '57', '40', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('302', '57', '43', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('303', '57', '44', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('304', '57', '62', '2', '', '6', '', 'cfg_treeBean.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('305', '57', '65', '2', '', '6', '', 'cfg_treePermission.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('306', '58', '82', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('307', '58', '84', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('308', '58', '86', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('309', '58', '87', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('31', '11', '10', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('310', '58', '88', '0', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('311', '59', '104', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('312', '59', '106', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('313', '59', '106', '2', 'Search', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('314', '59', '107', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('315', '59', '108', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('316', '59', '108', '2', 'Search', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('317', '59', '109', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('318', '60', '104', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('319', '60', '106', '2', '', '3', '', 'cfg_selectRole.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('32', '11', '11', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('320', '60', '108', '2', '', '6', '', 'cfg_treePerf.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('321', '61', '104', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('322', '61', '106', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('323', '61', '108', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('324', '62', '104', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('325', '62', '106', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('326', '62', '107', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('327', '62', '108', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('328', '62', '109', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('329', '63', '111', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('33', '11', '12', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('339', '64', '111', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('34', '11', '14', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('340', '64', '113', '2', '', '6', '', 'cfg_treePermission.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('341', '64', '117', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('342', '64', '118', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('343', '65', '111', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('344', '65', '113', '2', '', '6', '', 'cfg_treePermission.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('345', '65', '117', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('346', '65', '118', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('347', '66', '111', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('348', '66', '113', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('349', '66', '114', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('35', '11', '15', '2', null, '6', null, 'cfg_treeBean.sg', null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('350', '66', '115', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('351', '66', '116', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('352', '66', '117', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('353', '66', '118', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('36', '1', '29', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('362', '68', '119', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('37', '1', '30', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('375', '69', '119', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('376', '69', '121', '2', '', '6', '', 'cfg_treeFilter.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('377', '69', '124', '2', '', '6', '', 'cfg_treeProperty.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('378', '69', '126', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('379', '69', '127', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('38', '3', '29', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('380', '69', '128', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('381', '70', '119', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('382', '70', '121', '2', '', '6', '', 'cfg_treeFilter.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('383', '70', '124', '2', '', '6', '', 'cfg_treeProperty.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('384', '70', '126', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('385', '70', '127', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('386', '70', '128', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('387', '71', '119', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('388', '71', '121', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('389', '71', '122', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('39', '3', '30', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('390', '71', '124', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('391', '71', '125', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('392', '71', '126', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('393', '71', '127', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('394', '71', '128', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('395', '72', '129', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('396', '72', '131', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('397', '72', '131', '2', 'Search', '3', '', 'cfg_selectRole.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('398', '72', '132', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('399', '72', '133', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('4', '1', '4', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('40', '7', '29', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('400', '72', '133', '2', 'Search', '6', '', 'cfg_treeFilter.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('401', '72', '134', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('402', '73', '129', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('402881e64f54de1a014f581f90420001', '27', '466', '2', '', null, '', '', '', '', '', '', '', '', '');
INSERT INTO `cfg_perf_property` VALUES ('402881e64f54de1a014f581f91730002', '27', '463', '2', '', null, '', '', '', '', '', '', '', '', '');
INSERT INTO `cfg_perf_property` VALUES ('402881e64f54de1a014f581f91920003', '27', '464', '2', '', null, '', '', '', '', '', '', '', '', '');
INSERT INTO `cfg_perf_property` VALUES ('402881e64f54de1a014f581f91d60004', '27', '465', '2', '', null, '', '', '', '', '', '', '', '', '');
INSERT INTO `cfg_perf_property` VALUES ('402881e74f58b3c8014f58c21d160006', '402881e64f54de1a014f589fb8d0002b', '402881e64f54de1a014f589cd54c0020', '0', '', null, '', '', '', '', '', '', '', '', '');
INSERT INTO `cfg_perf_property` VALUES ('402881e74f58b3c8014f58c21d7c0007', '402881e64f54de1a014f589fb8d0002b', '402881e64f54de1a014f589d4e3f0021', '1', '', null, '', '', '', '', '', '', '', '', '');
INSERT INTO `cfg_perf_property` VALUES ('402881e74f58b3c8014f58c21db20008', '402881e64f54de1a014f589fb8d0002b', '402881e64f54de1a014f589e946c0022', '1', '', null, '', '', '', '', '', '', '', '', '');
INSERT INTO `cfg_perf_property` VALUES ('402881e74f58b3c8014f58c21df30009', '402881e64f54de1a014f589fb8d0002b', '402881e64f54de1a014f5893fa7a000c', '1', '', null, '', '', '', '', '', '', '', '', '');
INSERT INTO `cfg_perf_property` VALUES ('402881e74f58b3c8014f58c21e0b000a', '402881e64f54de1a014f589fb8d0002b', '402881e64f54de1a014f5893fb1a000f', '1', '', null, '', '', '', '', '', '', '', '', '');
INSERT INTO `cfg_perf_property` VALUES ('403', '73', '131', '2', '', '3', '', 'cfg_selectRole.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('404', '73', '133', '2', '', '6', '', 'cfg_treeFilter.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('405', '74', '129', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('406', '74', '131', '2', '', '3', '', 'cfg_selectRole.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('407', '74', '133', '2', '', '6', '', 'cfg_treeFilter.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('408', '75', '129', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('409', '75', '131', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('41', '7', '30', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('410', '75', '132', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('411', '75', '133', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('412', '75', '134', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('413', '76', '72', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('414', '76', '73', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('415', '76', '74', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('42', '12', '31', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('47', '12', '35', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('5', '1', '5', '2', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('51', '12', '39', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('52', '13', '38', '2', '', '6', null, 'cfg_treePermission.sg', null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('53', '13', '33', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('54', '13', '34', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('55', '13', '35', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('56', '13', '36', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('57', '13', '37', '2', '', '15', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('58', '14', '40', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('59', '14', '63', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('6', '3', '2', '2', null, '1', '1', null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('60', '14', '64', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('62', '14', '44', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('63', '14', '62', '0', 'Search', '6', null, 'cfg_treeBean.sg', null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('64', '14', '65', '0', 'Search', '6', null, 'cfg_treePermission.sg', null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('66', '15', '62', '2', null, '6', null, 'cfg_treeBean.sg', null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('67', '15', '65', '2', null, '6', null, 'cfg_treePermission.sg', null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('68', '15', '43', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('69', '15', '44', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('7', '3', '3', '2', null, '1', '1', null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('70', '16', '45', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('71', '16', '67', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('715', '90', '181', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('716', '90', '183', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('717', '90', '184', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('718', '90', '185', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('719', '90', '186', '2', '', '15', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('72', '16', '69', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('720', '90', '187', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('721', '90', '189', '2', '', '3', '', 'cfg_selectDomain.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('722', '91', '181', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('723', '91', '183', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('724', '91', '184', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('725', '91', '185', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('726', '91', '186', '2', '', '15', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('727', '91', '187', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('728', '91', '189', '2', '', '3', '', 'cfg_selectDomain.sg', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('729', '92', '181', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('73', '16', '71', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('730', '92', '183', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('731', '92', '184', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('732', '92', '185', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('733', '92', '186', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('734', '92', '187', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('735', '92', '189', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('736', '92', '190', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('737', '93', '182', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('74', '16', '48', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('744', '94', '182', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('745', '94', '191', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('746', '94', '192', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('747', '94', '193', '2', '', '15', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('748', '95', '182', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('749', '95', '191', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('75', '16', '49', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('750', '95', '192', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('751', '95', '193', '2', '', '15', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('752', '96', '182', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('753', '96', '191', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('754', '96', '192', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('755', '96', '193', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('76', '16', '51', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('77', '16', '52', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('775', '16', '54', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('776', '16', '56', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('777', '16', '57', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('778', '16', '58', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('779', '16', '53', '1', 'List', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('78', '16', '55', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('781', '31', '194', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('782', '76', '194', '2', '', '1', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('783', '32', '194', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('784', '11', '196', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('785', '11', '198', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('786', '11', '199', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('787', '11', '200', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('788', '11', '201', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('789', '11', '202', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('79', '16', '66', '2', 'Search', '6', null, 'cfg_treePerf.sg', null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('790', '11', '203', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('791', '11', '204', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('792', '11', '205', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('793', '23', '196', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('794', '23', '198', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('795', '23', '199', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('796', '23', '200', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('797', '23', '201', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('798', '23', '202', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('799', '23', '203', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8', '3', '4', '2', 'null', '4', '0', 'null', 'null', 'null', 'null', 'null', 'null', 'null', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('80', '16', '68', '2', 'Search', '6', null, 'cfg_treeProperty.sg', null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('800', '23', '204', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('801', '23', '205', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('803', '9', '197', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('804', '9', '198', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('805', '9', '199', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('806', '9', '200', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('807', '9', '201', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('808', '9', '202', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('809', '9', '203', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('81', '16', '70', '2', 'Search', '3', null, 'cfg_selectMyinput.sg', null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('810', '9', '204', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('811', '9', '205', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('82', '16', '48', '2', 'Search', '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('824', '9', '206', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('826', '23', '206', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('827', '24', '15', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('828', '24', '196', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('829', '24', '197', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('83', '16', '49', '2', 'Search', '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('830', '24', '198', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('831', '24', '199', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('832', '24', '200', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('833', '24', '201', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('834', '24', '202', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('835', '24', '203', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('836', '24', '204', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('837', '24', '205', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('838', '24', '206', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('839', '48', '93', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('84', '17', '66', '2', null, '6', null, 'cfg_treePerf.sg', null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('840', '48', '95', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('841', '48', '96', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('85', '17', '68', '2', null, '6', null, 'cfg_treeProperty.sg', null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('86', '17', '70', '2', null, '3', null, 'cfg_selectMyinput.sg', null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('87', '17', '48', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('88', '17', '49', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('89', '17', '51', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f398391014f3aa4257c0015', '8a8a11de4f398391014f3a9e77b10009', '539', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f398391014f3aa4259b0016', '8a8a11de4f398391014f3a9e77b10009', '540', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f398391014f3aa425bb0017', '8a8a11de4f398391014f3a9e77b10009', '542', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f398391014f3aa425e90018', '8a8a11de4f398391014f3a9e77b10009', '543', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f398391014f3aa426090019', '8a8a11de4f398391014f3a9e77b10009', '519', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f398391014f3aa42628001a', '8a8a11de4f398391014f3a9e77b10009', '520', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f398391014f3aa42657001b', '8a8a11de4f398391014f3a9e77b10009', '523', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f398391014f3aa42676001c', '8a8a11de4f398391014f3a9e77b10009', '524', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f398391014f3aa426a5001d', '8a8a11de4f398391014f3a9e77b10009', '527', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f398391014f3aa50818001e', '8a8a11de4f398391014f3a9e77cd000a', '539', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f398391014f3aa5083b001f', '8a8a11de4f398391014f3a9e77cd000a', '542', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f398391014f3aa508610020', '8a8a11de4f398391014f3a9e77cd000a', '519', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3ab7b6014f3acbf5ff0001', '8a8a11de4f398391014f3a9e77e8000b', '518', '0', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3ab7b6014f3acbf65d0002', '8a8a11de4f398391014f3a9e77e8000b', '539', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3ab7b6014f3acbf67c0003', '8a8a11de4f398391014f3a9e77e8000b', '542', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3ab7b6014f3acbf69b0004', '8a8a11de4f398391014f3a9e77e8000b', '519', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3ab7b6014f3acbf6bb0005', '8a8a11de4f398391014f3a9e77e8000b', '520', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3ab7b6014f3acbf6ca0006', '8a8a11de4f398391014f3a9e77e8000b', '523', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3ab7b6014f3acbf6e90007', '8a8a11de4f398391014f3a9e77e8000b', '524', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3ab7b6014f3acbf7090008', '8a8a11de4f398391014f3a9e77e8000b', '527', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad289160001', '8a8a11de4f398391014f3a9e7804000c', '518', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad289450002', '8a8a11de4f398391014f3a9e7804000c', '539', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad289540003', '8a8a11de4f398391014f3a9e7804000c', '540', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad289730004', '8a8a11de4f398391014f3a9e7804000c', '542', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad289b20005', '8a8a11de4f398391014f3a9e7804000c', '543', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad289e10006', '8a8a11de4f398391014f3a9e7804000c', '519', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad28a000007', '8a8a11de4f398391014f3a9e7804000c', '520', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad28a1f0008', '8a8a11de4f398391014f3a9e7804000c', '522', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad28a2f0009', '8a8a11de4f398391014f3a9e7804000c', '523', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad28a4e000a', '8a8a11de4f398391014f3a9e7804000c', '524', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad28a6d000b', '8a8a11de4f398391014f3a9e7804000c', '526', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad28a8c000c', '8a8a11de4f398391014f3a9e7804000c', '527', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad28aab000d', '8a8a11de4f398391014f3a9e7804000c', '528', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad28acb000e', '8a8a11de4f398391014f3a9e7804000c', '529', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad28aea000f', '8a8a11de4f398391014f3a9e7804000c', '530', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad28b090010', '8a8a11de4f398391014f3a9e7804000c', '531', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad28b190011', '8a8a11de4f398391014f3a9e7804000c', '532', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad28b470012', '8a8a11de4f398391014f3a9e7804000c', '533', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad28b860013', '8a8a11de4f398391014f3a9e7804000c', '534', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad28ba50014', '8a8a11de4f398391014f3a9e7804000c', '535', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad28bc40015', '8a8a11de4f398391014f3a9e7804000c', '536', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('8a8a11de4f3acc26014f3ad28be30016', '8a8a11de4f398391014f3a9e7804000c', '537', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('9', '3', '5', '2', null, '1', '1', null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('90', '17', '52', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('91', '17', '55', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('92', '18', '59', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('93', '18', '60', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('94', '18', '61', '1', 'List', null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('95', '18', '60', '2', 'Search', '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('96', '18', '61', '2', 'Search', '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('968', '48', '261', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('969', '48', '262', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('97', '19', '60', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('970', '49', '261', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('971', '50', '96', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('972', '50', '261', '2', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('973', '50', '262', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('974', '51', '261', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('975', '51', '262', '1', '', null, '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('98', '19', '61', '2', null, '1', null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_perf_property` VALUES ('99', '20', '31', '1', null, null, null, null, null, null, null, null, null, null, 'ggg');

-- ----------------------------
-- Table structure for `cfg_perf_state`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_perf_state`;
CREATE TABLE `cfg_perf_state` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '单为流程设置，edit显示方式与人员权限、bean自身的状态相关联，人员进入bean的edit界面\r\n1、判断flow中的权限 2判断beanstate 3联合查询得到editperf',
  `perf` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单为流程设置，edit显示方式与bean自身的状态相关联，人员进入bean的edit界面\r\n1、判断flow中的权限 2';

-- ----------------------------
-- Records of cfg_perf_state
-- ----------------------------

-- ----------------------------
-- Table structure for `cfg_permission`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_permission`;
CREATE TABLE `cfg_permission` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `parent` varchar(32) DEFAULT NULL,
  `namespace` varchar(255) DEFAULT NULL,
  `actionName` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `type` varchar(1) DEFAULT NULL COMMENT 'Page\\Element',
  `ord` varchar(1) DEFAULT NULL,
  `enable` varchar(2) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `usrct` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_permission
-- ----------------------------
INSERT INTO `cfg_permission` VALUES ('1', '5', 'cfg', 'findBean', '实体类', '1', null, '1', 'cfg/findBeanPage.sg', 'ggg');
INSERT INTO `cfg_permission` VALUES ('10', '9', 'cfg', 'findProperty', '属性', '1', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('101', '117', 'cfg', '', '常量', '2', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('102', '101', 'cfg', 'findCons', '常量', '1', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('103', '101', 'cfg', 'addCons', '增加常量', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('104', '101', 'cfg', 'editCons', '修改常量', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('105', '101', 'cfg', 'detailCons', '详细常量', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('106', '117', 'cfg', '', '域', '2', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('107', '106', 'cfg', 'findDomain', '域', '1', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('108', '106', 'cfg', 'addDomain', '增加域', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('109', '106', 'cfg', 'editDomain', '修改域', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('11', '117', 'cfg', null, '组织管理', '2', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('110', '106', 'cfg', 'detailDomain', '详细域', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('116', '39', 'cfg', 'c_Role_editUnder', '角色功能配置', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('117', null, 'cfg', null, '系统配置', '3', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('119', '17', 'cfg', 'c_Perf_editUnder', '布局配置', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('12', '11', 'cfg', 'findOrg', '组织', '1', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('13', '9', 'cfg', 'addProperty', '属性增加', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('14', '117', 'cfg', null, '功能管理', '2', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('15', '14', 'cfg', 'findPermission', '功能', '1', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('155', '5', 'cfg', 'delBean', '删除类', '0', '', '1', '', 'ggg');
INSERT INTO `cfg_permission` VALUES ('156', '5', 'cfg', 'c_Bean_createDb', '重建数据表', '0', '', '1', '', 'ggg');
INSERT INTO `cfg_permission` VALUES ('157', '5', 'cfg', 'c_Bean_createVo', '创建VO文件', '0', '', '1', '', 'ggg');
INSERT INTO `cfg_permission` VALUES ('158', '5', 'cfg', 'c_Bean_createPermission', '创建功能', '0', '', '1', '', 'ggg');
INSERT INTO `cfg_permission` VALUES ('159', '9', 'cfg', 'delProperty', '删除属性', '0', '', '1', '', 'ggg');
INSERT INTO `cfg_permission` VALUES ('16', '14', 'cfg', 'addPermission', '增加功能', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('166', '39', 'cfg', 'c_Role_toMemberOrgAtt', '角色对应人员组织', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('167', '117', 'cfg', '', '角色--组织', '2', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('168', '167', 'cfg', 'findRoleOrg', '角色--组织', '1', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('169', '167', 'cfg', 'addRoleOrg', '增加角色--组织', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('17', '117', 'cfg', null, '表现管理', '2', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('170', '167', 'cfg', 'editRoleOrg', '修改角色--组织', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('171', '167', 'cfg', 'detailRoleOrg', '详细角色--组织', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('172', '167', 'cfg', 'delRoleOrg', '删除角色--组织', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('173', '11', 'cfg', 'treeOrg', '树_组织', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('18', '17', 'cfg', 'findPerf', '表现', '1', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('19', '17', 'cfg', 'addPerf', '增加表现', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('2', '5', 'cfg', 'addBean', '增加类', '0', null, '1', 'addBeanPage', 'ggg');
INSERT INTO `cfg_permission` VALUES ('20', '117', 'cfg', null, '表现属性管理', '2', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('21', '20', 'cfg', 'findPerfProperty', '表现属性', '1', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('22', '20', 'cfg', 'addPerfProperty', '增加表现属性', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('23', '117', 'cfg', null, '控件管理', '2', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('24', '23', 'cfg', 'findMyinput', '控件列表', '1', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('25', '23', 'cfg', 'addMyinput', '增加控件', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('26', '14', 'cfg', 'detailPermission', '功能详细', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('27', '17', 'cfg', 'detailPerf', '表现详细', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('28', '31', 'cfg', 'findQuote', '引用列表', '2', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('29', '14', 'cfg', 'editPermission', '功能修改', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('30', '17', 'cfg', 'editPerf', '修改表现', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('31', '117', 'cfg', null, '引用管理', '2', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('32', '9', 'cfg', 'editProperty', '修改属性', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('33', '9', 'cfg', 'detailProperty', '属性详细', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('34', '20', 'cfg', 'editPerfProperty', '修改表现属性', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('35', '20', 'cfg', 'detailPerfProperty', '详细表现属性', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('36', '11', 'cfg', 'addOrg', '增加组织', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('37', '11', 'cfg', 'editOrg', '修改组织', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('38', '11', 'cfg', 'detailOrg', '详细组织', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('39', '117', 'cfg', '', '角色管理', '2', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('40', '39', 'cfg', 'findRole', '角色', '1', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('402881e64f54de1a014f589fb68e0023', '117', 'cfg', '', '人员修改自助', '2', null, '1', null, '');
INSERT INTO `cfg_permission` VALUES ('402881e64f54de1a014f589fb6ee0024', '402881e64f54de1a014f589fb68e0023', 'cfg', 'findMemberEdit', '人员修改自助', '1', null, '1', null, '');
INSERT INTO `cfg_permission` VALUES ('402881e64f54de1a014f589fb72e0025', '402881e64f54de1a014f589fb68e0023', 'cfg', 'addMemberEdit', '增加人员修改自助', '0', null, '1', null, '');
INSERT INTO `cfg_permission` VALUES ('402881e64f54de1a014f589fb7450026', '402881e64f54de1a014f589fb68e0023', 'cfg', 'editMemberEdit', '修改人员修改自助', '0', null, '1', null, '');
INSERT INTO `cfg_permission` VALUES ('402881e64f54de1a014f589fb7890027', '402881e64f54de1a014f589fb68e0023', 'cfg', 'detailMemberEdit', '详细人员修改自助', '0', null, '1', null, '');
INSERT INTO `cfg_permission` VALUES ('402881e64f54de1a014f589fb8360028', '402881e64f54de1a014f589fb68e0023', 'cfg', 'delMemberEdit', '删除人员修改自助', '0', null, '1', null, '');
INSERT INTO `cfg_permission` VALUES ('402881e64f54de1a014f589fb8790029', '402881e64f54de1a014f589fb68e0023', 'cfg', 'treeMemberEdit', '树_人员修改自助', '0', null, '1', null, '');
INSERT INTO `cfg_permission` VALUES ('402881e64f54de1a014f589fb899002a', '402881e64f54de1a014f589fb68e0023', 'cfg', 'selectMemberEdit', '选择_人员修改自助', '0', null, '1', null, '');
INSERT INTO `cfg_permission` VALUES ('41', '39', 'cfg', 'addRole', '增加角色', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('42', '39', 'cfg', 'editRole', '编辑角色', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('43', '39', 'cfg', 'detailRole', '明细角色', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('44', '117', 'cfg', '', '组织中的角色管理', '2', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('45', '44', 'cfg', 'findOrgRole', '组织中的角色', '1', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('46', '44', 'cfg', 'addOrgRole', '增加组织中的角色', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('47', '44', 'cfg', 'editOrgRole', '修改组织中的角色', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('48', '44', 'cfg', 'detailOrgRole', '详细组织中的角色', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('49', '117', 'cfg', '', '人员管理', '2', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('5', '117', 'cfg', null, '类管理', '2', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('50', '49', 'cfg', 'findMember', '人员', '1', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('51', '49', 'cfg', 'addMember', '增加人员', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('52', '49', 'cfg', 'editMember', '编辑人员', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('53', '49', 'cfg', 'detailMember', '详细人员', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('54', '117', 'cfg', '', '组织角色人员管理', '2', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('55', '54', 'cfg', 'findOrgRoleMember', '组织角色人员', '1', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('56', '54', 'cfg', 'addOrgRoleMember', '增加组织角色人员', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('57', '54', 'cfg', 'editOrgRoleMember', '修改组织角色人员', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('58', '54', 'cfg', 'detailOrgRoleMember', '详细组织角色人员', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('59', '117', 'cfg', '', '角色功能', '2', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('6', '5', 'cfg', 'detailBean', '类详细', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('60', '59', 'cfg', 'findRolePermission', '角色功能', '1', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('61', '59', 'cfg', 'addRolePermission', '增加角色功能', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('62', '59', 'cfg', 'editRolePermission', '修改角色功能', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('63', '59', 'cfg', 'detailRolePermission', '详细角色功能', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('64', '117', 'cfg', '', '角色表现', '2', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('65', '64', 'cfg', 'findRolePerf', '角色表现', '1', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('66', '64', 'cfg', 'addRolePerf', '新增角色表现', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('67', '64', 'cfg', 'editRolePerf', '修改角色表现', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('68', '64', 'cfg', 'detailRolePerf', '详细角色表现', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('69', '117', 'cfg', '', '过滤管理', '2', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('7', '5', 'cfg', 'editBean', '类修改', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('70', '117', 'cfg', '', '过滤属性管理', '2', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('71', '69', 'cfg', 'findFilter', '过滤', '1', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('72', '69', 'cfg', 'addFilter', '增加过滤', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('73', '69', 'cfg', 'editFilter', '编辑过滤', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('74', '69', 'cfg', 'detailFilter', '详细过滤', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('75', '70', 'cfg', 'findFilterProperty', '过滤属性', '1', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('76', '70', 'cfg', 'addFilterProperty', '增加过滤属性', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('77', '70', 'cfg', 'editFilterProperty', '修改过滤属性', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('78', '70', 'cfg', 'detailFilterProperty', '详细过滤属性', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('79', '117', 'cfg', '', '角色过滤', '2', '', '1', '', 'ggg');
INSERT INTO `cfg_permission` VALUES ('80', '79', 'cfg', 'findRoleFilter', '角色过滤', '1', '', '1', '', 'ggg');
INSERT INTO `cfg_permission` VALUES ('81', '79', 'cfg', 'addRoleFilter', '新增角色过滤', '0', '', '1', '', 'ggg');
INSERT INTO `cfg_permission` VALUES ('82', '79', 'cfg', 'editRoleFilter', '修改角色过滤', '0', '', '1', '', 'ggg');
INSERT INTO `cfg_permission` VALUES ('83', '79', 'cfg', 'detailRoleFilter', '详细角色过滤', '0', '', '1', '', 'ggg');
INSERT INTO `cfg_permission` VALUES ('8a8a11de4f398391014f3a9e76660001', '117', 'cfg', '', '组织--人员', '2', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('8a8a11de4f398391014f3a9e76af0002', '8a8a11de4f398391014f3a9e76660001', 'cfg', 'findOrgMember', '组织--人员', '1', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('8a8a11de4f398391014f3a9e76eb0003', '8a8a11de4f398391014f3a9e76660001', 'cfg', 'addOrgMember', '增加组织--人员', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('8a8a11de4f398391014f3a9e77070004', '8a8a11de4f398391014f3a9e76660001', 'cfg', 'editOrgMember', '修改组织--人员', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('8a8a11de4f398391014f3a9e77220005', '8a8a11de4f398391014f3a9e76660001', 'cfg', 'detailOrgMember', '详细组织--人员', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('8a8a11de4f398391014f3a9e773e0006', '8a8a11de4f398391014f3a9e76660001', 'cfg', 'delOrgMember', '删除组织--人员', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('8a8a11de4f398391014f3a9e77590007', '8a8a11de4f398391014f3a9e76660001', 'cfg', 'treeOrgMember', '树_组织--人员', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('8a8a11de4f398391014f3a9e77940008', '8a8a11de4f398391014f3a9e76660001', 'cfg', 'selectOrgMember', '选择_组织--人员', '0', null, '1', null, 'ggg');
INSERT INTO `cfg_permission` VALUES ('9', '117', 'cfg', null, '属性管理', '2', null, '1', null, 'ggg');

-- ----------------------------
-- Table structure for `cfg_property`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_property`;
CREATE TABLE `cfg_property` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `bean` varchar(32) DEFAULT NULL,
  `property` varchar(200) DEFAULT NULL COMMENT '有三种Property\r\n1、bean自带的\r\n2、bean关联的外键（比如学生带的班级属性class.name）\r\n3、bean被关联的外键(如班级带的学生student/class/perfx)',
  `type` varchar(200) DEFAULT NULL COMMENT '若pro是外键则是对应的BEANNAME,程序中type为BEANNAME，若pro是FILE，则是File，在程序中其type是string，影响表格中post_enctype',
  `descr` varchar(200) DEFAULT NULL,
  `length` varchar(5) DEFAULT NULL,
  `votype` varchar(200) DEFAULT NULL,
  `myinput` varchar(32) DEFAULT NULL,
  `required` varchar(1) DEFAULT NULL,
  `valueurl` varchar(200) DEFAULT NULL,
  `validType` varchar(20) DEFAULT NULL,
  `dftvalue` varchar(200) DEFAULT NULL,
  `width` varchar(5) DEFAULT NULL,
  `min` varchar(5) DEFAULT NULL,
  `max` varchar(5) DEFAULT NULL,
  `showvalue` varchar(200) DEFAULT NULL,
  `ord` int(3) DEFAULT NULL,
  `usrct` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_property
-- ----------------------------
INSERT INTO `cfg_property` VALUES ('1', '1', 'id', 'varchar', 'ID', '32', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('10', '8', 'type', 'varchar', '类型', '20', null, null, null, null, null, null, null, null, null, null, '30', 'ggg');
INSERT INTO `cfg_property` VALUES ('100', '19', 'role.descr', '', '角色描述', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('101', '19', 'permission.id', '', '功能ID', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('102', '19', 'permission.descr', '', '功能描述', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('103', '19', 'permission', 'varchar', '功能', '32', 'cfg.vo.Permission', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('104', '20', 'id', 'varchar', 'ID', '32', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('105', '20', 'role', 'varchar', '角色', '32', 'cfg.vo.Role', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('106', '20', 'role.id', '', '角色ID', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('107', '20', 'role.descr', '', '角色描述', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('108', '20', 'perf.id', '', '表现ID', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('109', '20', 'perf.descr', '', '表现描述', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('11', '8', 'descr', 'varchar', '属性描述', '255', null, null, null, null, null, null, null, null, null, null, '30', 'ggg');
INSERT INTO `cfg_property` VALUES ('110', '20', 'perf', 'varchar', '表现', '32', 'cfg.vo.Perf', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('111', '21', 'id', 'varchar', 'ID', '32', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('112', '21', 'permission', 'varchar', '功能', '32', 'cfg.vo.Permission', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('113', '21', 'permission.id', '', '功能ID', '', '', '6', '', 'cfg_treePermission.sg', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('114', '21', 'permission.descr', '', '功能描述', '', '', null, '', 'null', '', '', '150', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('115', '21', 'permission.actionName', '', '功能actionName', '', '', null, '', 'null', '', '', '150', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('116', '21', 'permission.namespace', '', '功能namespace', '', '', null, '', 'null', '', '', '100', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('117', '21', 'descr', 'varchar', '描述', '255', '', '1', '', 'null', '', '', '150', '', '100', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('118', '21', 'ord', 'varchar', '顺序', '3', '', '13', '1', 'null', '', '0', '50', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('119', '22', 'id', 'varchar', 'ID', '32', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('12', '8', 'length', 'varchar', '长度', '20', null, null, null, null, null, null, null, null, null, null, '30', 'ggg');
INSERT INTO `cfg_property` VALUES ('120', '22', 'filter', 'varchar', '过滤', '32', 'cfg.vo.Filter', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('121', '22', 'filter.id', '', '过滤ID', '', '', '3', '', 'cfg_selectFilter.sg', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('122', '22', 'filter.descr', '', '过滤描述', '', '', null, '', 'null', '', '', '200', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('123', '22', 'property', 'varchar', '属性', '32', 'cfg.vo.Property', null, null, null, '', null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('124', '22', 'property.id', '', '属性ID', '', '', '6', '', 'cfg_treeProperty.sg', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('125', '22', 'property.descr', '', '属性描述', '', '', null, '0', 'null', '', '', '150', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('126', '22', 'op', 'varchar', '操作', '255', '', '1', '1', 'null', '', '', '50', '', '100', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('127', '22', 'val', 'varchar', '值', '255', '', '1', '1', 'null', '', '', '100', '', '200', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('128', '22', 'clazz', 'varchar', '值类型', '255', '', '1', '0', 'null', '', '', '100', '', '100', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('129', '23', 'id', 'varchar', 'ID', '32', '', null, null, null, null, null, null, null, null, '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('13', '8', 'bean.descr', '', '类描述', null, null, null, null, null, null, null, null, null, null, null, '30', 'ggg');
INSERT INTO `cfg_property` VALUES ('130', '23', 'role', 'varchar', '角色', '32', 'cfg.vo.Role', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('131', '23', 'role.id', '', '角色ID', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('132', '23', 'role.descr', '', '角色描述', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('133', '23', 'filter.id', '', '过滤ID', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('134', '23', 'filter.descr', '', '过滤描述', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('135', '23', 'filter', 'varchar', '过滤', '32', 'cfg.vo.Filter', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('14', '8', 'votype', 'varchar', '外键类', '20', null, null, null, null, null, null, null, null, null, null, '30', 'ggg');
INSERT INTO `cfg_property` VALUES ('15', '8', 'bean.id', null, '类', '', null, '6', null, 'cfg_treeBean.sg', null, null, null, null, null, null, '30', 'ggg');
INSERT INTO `cfg_property` VALUES ('16', '13', 'id', 'varchar', 'ID', '32', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('17', '13', 'orgCode', 'varchar', '组织代码', '20', 'null', '1', '1', '', '', '', '100', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('18', '13', 'descr', 'varchar', '组织名称', '100', '', '1', '1', '', '', '', '150', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('181', '26', 'id', 'varchar', 'ID', '32', '', null, '', 'null', 'null', 'null', '', '', '', 'null', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('182', '27', 'id', 'varchar', 'ID', '32', '', null, '', 'null', 'null', 'null', '', '', '', 'null', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('183', '26', 'name', 'varchar', '名字', '255', null, null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('184', '26', 'value', 'varchar', '值', '255', '', '1', '1', '', '', '', '100', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('185', '26', 'descr', 'varchar', '描述', '255', '', '1', '0', '', '', '', '100', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('186', '26', 'enable', 'varchar', '有效性', '2', '', '15', '0', '', '', '', '100', '', '', 'enable', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('187', '26', 'ord', 'varchar', '顺序', '32', '', '13', '1', '', '', '0', '50', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('188', '26', 'domain', 'varchar', '域', '32', 'cfg.vo.Domain', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('189', '26', 'domain.id', '', '域ID', '', '', '3', '1', 'cfg_selectDomain.sg', '', '', '100', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('19', '13', 'parent', 'int', '父组织', '10', 'cfg.vo.Org', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('190', '26', 'domain.descr', null, '域描述', null, null, null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('191', '27', 'domain', 'varchar', '名字', '255', '', '1', '0', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('192', '27', 'descr', 'varchar', '描述', '255', '', '1', '1', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('193', '27', 'enable', 'varchar', '有效性', '2', '', '15', '1', '', '', '', '', '', '', 'enable', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('194', '16', 'ord', 'int', '顺序', '3', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('195', '8', 'myinput', 'varchar', '控件', '32', 'cfg.vo.Myinput', null, null, null, null, null, null, null, null, null, '30', 'ggg');
INSERT INTO `cfg_property` VALUES ('196', '8', 'myinput.id', null, '控件ID', null, null, '3', null, 'cfg_selectMyinput.sg', null, null, null, null, null, null, '30', 'ggg');
INSERT INTO `cfg_property` VALUES ('197', '8', 'myinput.descr', null, '控件描述', null, null, null, null, null, null, null, null, null, null, null, '30', 'ggg');
INSERT INTO `cfg_property` VALUES ('198', '8', 'required', 'varchar', '必须', '1', null, '4', null, null, '', null, null, null, '1', null, '30', 'ggg');
INSERT INTO `cfg_property` VALUES ('199', '8', 'valueurl', 'varchar', '关联URL', '200', null, '1', null, null, '', null, null, null, '200', null, '30', 'ggg');
INSERT INTO `cfg_property` VALUES ('2', '1', 'bean', 'varchar', '类', '20', '', '1', '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('20', '13', 'parent.id', 'null', '父组织', 'null', 'null', '6', '0', 'cfg_treeOrg.sg', '', '', '100', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('200', '8', 'validType', 'varchar', '检验类型', '20', null, '1', null, null, '', null, null, null, '200', null, '30', 'ggg');
INSERT INTO `cfg_property` VALUES ('201', '8', 'dftvalue', 'varchar', '默认值', '200', null, '1', null, null, '', null, null, null, '200', null, '30', 'ggg');
INSERT INTO `cfg_property` VALUES ('202', '8', 'width', 'varchar', '宽度', '5', '', '13', '0', '', '', '', '100', '', '5', '', '30', 'ggg');
INSERT INTO `cfg_property` VALUES ('203', '8', 'min', 'varchar', '最小', '5', null, '13', null, null, '', null, null, null, '5', null, '30', 'ggg');
INSERT INTO `cfg_property` VALUES ('204', '8', 'max', 'varchar', '最大', '5', null, '13', null, null, '', null, null, null, '5', null, '30', 'ggg');
INSERT INTO `cfg_property` VALUES ('205', '8', 'showvalue', 'varchar', 'showvalue', '200', null, '1', null, null, '', null, null, null, '200', null, '30', 'ggg');
INSERT INTO `cfg_property` VALUES ('206', '8', 'ord', 'int', '顺序', '3', '', '13', '', '', '', '', '', '', '3', '', '30', 'ggg');
INSERT INTO `cfg_property` VALUES ('21', '13', 'parent.descr', '', '父组织名称', '', '', null, '', '', '', '', '200', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('22', '14', 'id', 'varchar', 'ID', '32', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('23', '14', 'oranPerf', 'varchar', null, '32', 'cfg.vo.Perf', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('24', '14', 'oranProperty', 'varchar', null, '32', 'cfg.vo.Property', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('25', '14', 'quotePerf', 'varchar', null, '32', 'cfg.vo.Perf', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('26', '14', 'quoteProperty', 'varchar', null, '32', 'cfg.vo.Property', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('260', '18', 'org', 'varchar', '组织', '32', 'cfg.vo.Org', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('261', '18', 'org.id', '', '组织ID', '', '', '6', '', 'cfg_treeOrg.sg', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('262', '18', 'org.descr', '', '组织描述', '', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('27', '14', 'divTitle', 'varchar', null, '155', null, null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('29', '1', 'descrProperty', 'varchar', '指代类实例描述字段名', '20', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('3', '1', 'descr', 'varchar', '类描述', '20', null, null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('30', '1', 'tableName', 'varchar', '类对应的数据库表名', '20', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('31', '15', 'id', 'varchar', 'ID', '32', '', null, '', 'null', 'null', 'null', '', '', '', 'null', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('32', '15', 'parent', 'varchar', '父功能', '32', 'cfg.vo.Permission', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('33', '15', 'namespace', 'varchar', 'namespace', '255', '', '1', '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('34', '15', 'actionName', 'varchar', 'actionName', '255', '', '1', '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('35', '15', 'descr', 'varchar', '功能描述', '255', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('36', '15', 'type', 'varchar', '功能类型', '1', '', '1', '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('37', '15', 'enable', 'varchar', 'enable', '2', '', '15', '', '', '', '', '', '', '', 'enable', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('38', '15', 'parent.id', '', '父功能', '', '', '6', '', 'cfg_treePermission.sg', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('39', '15', 'parent.descr', '', '父功能描述', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('4', '1', 'isFlow', 'varchar', '流程', '1', '', '4', '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('40', '5', 'id', 'varchar', 'ID', '32', 'null', null, '', '', 'null', 'null', '', '', '', 'null', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f5893fa48000a', '402881e64f54de1a014f5893fa0c0009', 'id', 'varchar', 'ID', '32', '', null, '', '', '', '', '', '', '', '', '0', '');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f5893fa66000b', '402881e64f54de1a014f5893fa0c0009', 'enable', 'varchar', '有效?', '2', '', '15', '', '', '', '1', '50', '', '', 'enable', '90', '');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f5893fa7a000c', '402881e64f54de1a014f5893fa0c0009', 'createDate', 'varchar', '创建日期', '20', '', null, '', '', '', '', '100', '', '', '', '90', '');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f5893faa2000d', '402881e64f54de1a014f5893fa0c0009', 'createMember', 'varchar', '创建人', '32', 'cfg.vo.Member', null, '', '', '', '', '50', '', '', '', '90', '');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f5893fafc000e', '402881e64f54de1a014f5893fa0c0009', 'createMember.id', '', '创建人ID', '', '', null, '', '', '', '', '80', '', '', '', '90', '');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f5893fb1a000f', '402881e64f54de1a014f5893fa0c0009', 'createMember.descr', '', '创建人描述', '', '', null, '', '', '', '', '80', '', '', '', '90', '');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f5893fb4c0010', '402881e64f54de1a014f5893fa0c0009', 'updateDate', 'varchar', '更新日期', '20', '', null, '', '', '', '', '100', '', '', '', '90', '');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f5893fb6a0011', '402881e64f54de1a014f5893fa0c0009', 'updateMember', 'varchar', '更新人', '32', 'cfg.vo.Member', null, '', '', '', '', '50', '', '', '', '90', '');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f5893fb9c0012', '402881e64f54de1a014f5893fa0c0009', 'updateMember.id', '', '更新人ID', '', '', null, '', '', '', '', '80', '', '', '', '90', '');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f5893fbe20013', '402881e64f54de1a014f5893fa0c0009', 'updateMember.descr', '', '更新人描述', '', '', null, '', '', '', '', '80', '', '', '', '90', '');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f5893fc000014', '402881e64f54de1a014f5893fa0c0009', 'ws0', 'varchar', '扩展0', '255', '', null, '', '', '', '', '', '', '', '', '99', '');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f5893fc320015', '402881e64f54de1a014f5893fa0c0009', 'ws1', 'varchar', '扩展1', '255', '', null, '', '', '', '', '', '', '', '', '99', '');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f5893fc6e0016', '402881e64f54de1a014f5893fa0c0009', 'ws2', 'varchar', '扩展2', '255', '', null, '', '', '', '', '', '', '', '', '99', '');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f5893fca00017', '402881e64f54de1a014f5893fa0c0009', 'ws3', 'varchar', '扩展3', '255', '', null, '', '', '', '', '', '', '', '', '99', '');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f5893fcbe0018', '402881e64f54de1a014f5893fa0c0009', 'ws4', 'varchar', '扩展4', '255', '', null, '', '', '', '', '', '', '', '', '99', '');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f5893fcdc0019', '402881e64f54de1a014f5893fa0c0009', 'ws5', 'varchar', '扩展5', '255', '', null, '', '', '', '', '', '', '', '', '99', '');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f5893fd2c001a', '402881e64f54de1a014f5893fa0c0009', 'ws6', 'varchar', '扩展6', '255', '', null, '', '', '', '', '', '', '', '', '99', '');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f5893fd4a001b', '402881e64f54de1a014f5893fa0c0009', 'ws7', 'varchar', '扩展7', '255', '', null, '', '', '', '', '', '', '', '', '99', '');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f5893fd5f001c', '402881e64f54de1a014f5893fa0c0009', 'ws8', 'varchar', '扩展8', '255', '', null, '', '', '', '', '', '', '', '', '99', '');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f5893fd7d001d', '402881e64f54de1a014f5893fa0c0009', 'ws9', 'varchar', '扩展9', '255', '', null, '', '', '', '', '', '', '', '', '99', '');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f5893fdc3001e', '402881e64f54de1a014f5893fa0c0009', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '99', '');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f589711d0001f', '402881e64f54de1a014f5893fa0c0009', 'member', 'varchar', '人员', '32', 'cfg.vo.Member', null, '', '', '', '', '', '', '', '', '10', 'ggg');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f589cd54c0020', '402881e64f54de1a014f5893fa0c0009', 'member.id', '', '人员ID', '', '', '3', '1', 'cfg_selectMember.sg', '', ':currentMemberId', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f589d4e3f0021', '402881e64f54de1a014f5893fa0c0009', 'member.descr', '', '人员描述', '', '', null, '', '', '', '', '80', '', '', '', '10', 'ggg');
INSERT INTO `cfg_property` VALUES ('402881e64f54de1a014f589e946c0022', '402881e64f54de1a014f5893fa0c0009', 'ndescr', 'varchar', '新人员描述', '32', '', '1', '1', '', '', '', '100', '', '', '', '20', 'ggg');
INSERT INTO `cfg_property` VALUES ('41', '5', 'bean', 'varchar', '所属实体', '32', 'cfg.vo.Bean', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('42', '5', 'permission', 'varchar', '对应权限', '32', 'cfg.vo.Permission', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('43', '5', 'descr', 'varchar', '描述', '255', null, null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('44', '5', 'ord', 'varchar', '顺序（小优先）', '2', null, null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('45', '6', 'id', 'varchar', 'ID', '32', 'null', null, '', '', 'null', 'null', '', '', '', 'null', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('46', '6', 'perf', 'varchar', '表现', '32', 'cfg.vo.Perf', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('462', '5', 'layout', 'varchar', '布局', '5000', '', '14', '', '', '', '', '200', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('463', '13', 'att1', 'varchar', '属性1', '2', '', '3', '', 'cfg_selectCons.sg?domain=cfg_org_att', '', '', '', '', '', 'cfg_org_att', '20', 'ggg');
INSERT INTO `cfg_property` VALUES ('464', '13', 'att2', 'varchar', '属性2', '2', '', '3', '', 'cfg_selectCons.sg?domain=cfg_org_att', '', '', '', '', '', 'cfg_org_att', '20', 'ggg');
INSERT INTO `cfg_property` VALUES ('465', '13', 'att3', 'varchar', '属性3', '2', '', '3', '', 'cfg_selectCons.sg?domain=cfg_org_att', '', '', '', '', '', 'cfg_org_att', '20', 'ggg');
INSERT INTO `cfg_property` VALUES ('466', '13', 'offi', 'varchar', '实体机构？', '1', '', '4', '', '', '', '', '', '', '', 'yorn', '10', 'ggg');
INSERT INTO `cfg_property` VALUES ('467', '27', 'pkg', 'varchar', '包', '5', '', '1', '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('468', '35', 'id', 'varchar', 'ID', '32', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('469', '35', 'enable', 'varchar', '有效?', '2', '', '15', '', '', '', '1', '50', '', '', 'enable', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('47', '6', 'property', 'varchar', '属性', '32', 'cfg.vo.Property', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('470', '35', 'createDate', 'varchar', '创建日期', '20', '', null, '', '', '', '', '100', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('471', '35', 'createMember', 'varchar', '创建人', '32', 'cfg.vo.Member', null, '', '', '', '', '50', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('472', '35', 'createMember.id', '', '创建人ID', '', '', null, '', '', '', '', '80', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('473', '35', 'createMember.descr', '', '创建人描述', '', '', null, '', '', '', '', '80', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('474', '35', 'updateDate', 'varchar', '更新日期', '20', '', null, '', '', '', '', '100', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('475', '35', 'updateMember', 'varchar', '更新人', '32', 'cfg.vo.Member', null, '', '', '', '', '50', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('476', '35', 'updateMember.id', '', '更新人ID', '', '', null, '', '', '', '', '80', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('477', '35', 'updateMember.descr', '', '更新人描述', '', '', null, '', '', '', '', '80', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('478', '35', 'ws0', 'varchar', '扩展0', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('479', '35', 'ws1', 'varchar', '扩展1', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('48', '6', 'editshowhide', 'varchar', '显示方式', '1', null, null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('480', '35', 'ws2', 'varchar', '扩展2', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('481', '35', 'ws3', 'varchar', '扩展3', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('482', '35', 'ws4', 'varchar', '扩展4', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('483', '35', 'ws5', 'varchar', '扩展5', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('484', '35', 'ws6', 'varchar', '扩展6', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('485', '35', 'ws7', 'varchar', '扩展7', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('486', '35', 'ws8', 'varchar', '扩展8', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('487', '35', 'ws9', 'varchar', '扩展9', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('488', '35', 'role', 'varchar', '角色', '32', 'cfg.vo.Role', null, '', '', '', '', '', '', '', '', '10', 'ggg');
INSERT INTO `cfg_property` VALUES ('489', '35', 'role.id', '', '角色ID', '', '', '3', '', 'cfg_selectRole.sg', '', '', '', '', '', '', '10', 'ggg');
INSERT INTO `cfg_property` VALUES ('49', '6', 'kind', 'varchar', '类型', '255', null, null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('490', '35', 'role.descr', '', '角色描述', '', '', null, '', '', '', '', '150', '', '', '', '10', 'ggg');
INSERT INTO `cfg_property` VALUES ('491', '35', 'member', 'varchar', '人员', '32', 'cfg.vo.Member', null, '', '', '', '', '', '', '', '', '20', 'ggg');
INSERT INTO `cfg_property` VALUES ('492', '35', 'member.id', '', '人员ID', '', '', '3', '', 'cfg_selectMember.sg', '', '', '', '', '', '', '20', 'ggg');
INSERT INTO `cfg_property` VALUES ('493', '35', 'member.descr', '', '人员描述', '', '', null, '', '', '', '', '', '', '', '', '20', 'ggg');
INSERT INTO `cfg_property` VALUES ('494', '36', 'id', 'varchar', 'ID', '32', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('495', '36', 'enable', 'varchar', '有效?', '2', '', '15', '', '', '', '1', '50', '', '', 'enable', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('496', '36', 'createDate', 'varchar', '创建日期', '20', '', null, '', '', '', '', '100', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('497', '36', 'createMember', 'varchar', '创建人', '32', 'cfg.vo.Member', null, '', '', '', '', '50', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('498', '36', 'createMember.id', '', '创建人ID', '', '', null, '', '', '', '', '80', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('499', '36', 'createMember.descr', '', '创建人描述', '', '', null, '', '', '', '', '80', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('5', '1', 'pkg', 'varchar', '包', '10', '', '1', '1', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('50', '6', 'myinput', 'varchar', '控件', '32', 'cfg.vo.Myinput', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('500', '36', 'updateDate', 'varchar', '更新日期', '20', '', null, '', '', '', '', '100', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('501', '36', 'updateMember', 'varchar', '更新人', '32', 'cfg.vo.Member', null, '', '', '', '', '50', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('502', '36', 'updateMember.id', '', '更新人ID', '', '', null, '', '', '', '', '80', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('503', '36', 'updateMember.descr', '', '更新人描述', '', '', null, '', '', '', '', '80', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('504', '36', 'ws0', 'varchar', '扩展0', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('505', '36', 'ws1', 'varchar', '扩展1', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('506', '36', 'ws2', 'varchar', '扩展2', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('507', '36', 'ws3', 'varchar', '扩展3', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('508', '36', 'ws4', 'varchar', '扩展4', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('509', '36', 'ws5', 'varchar', '扩展5', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('51', '6', 'required', 'varchar', '是否必须', '1', null, null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('510', '36', 'ws6', 'varchar', '扩展6', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('511', '36', 'ws7', 'varchar', '扩展7', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('512', '36', 'ws8', 'varchar', '扩展8', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('513', '36', 'ws9', 'varchar', '扩展9', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('514', '36', 'role', 'varchar', '角色', '32', 'cfg.vo.Role', null, '', '', '', '', '', '', '', '', '10', 'ggg');
INSERT INTO `cfg_property` VALUES ('515', '36', 'role.id', '', '角色ID', '', '', '3', '', 'cfg_selectRole.sg', '', '', '', '', '', '', '10', 'ggg');
INSERT INTO `cfg_property` VALUES ('516', '36', 'role.descr', '', '角色描述', '', '', null, '', '', '', '', '150', '', '', '', '10', 'ggg');
INSERT INTO `cfg_property` VALUES ('517', '36', 'att', 'varchar', '属性', '2', '', '3', '', 'cfg/selectCons.sg?domain=cfg_org_att', '', '', '', '', '', 'cfg_org_att', '20', 'ggg');
INSERT INTO `cfg_property` VALUES ('518', '37', 'id', 'varchar', 'ID', '32', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('519', '37', 'enable', 'varchar', '有效?', '2', '', '15', '', '', '', '1', '50', '', '', 'enable', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('52', '6', 'valueurl', 'varchar', '关联URL', '255', null, null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('520', '37', 'createDate', 'varchar', '创建日期', '20', '', null, '', '', '', '', '100', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('521', '37', 'createMember', 'varchar', '创建人', '32', 'cfg.vo.Member', null, '', '', '', '', '50', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('522', '37', 'createMember.id', '', '创建人ID', '', '', null, '', '', '', '', '80', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('523', '37', 'createMember.descr', '', '创建人描述', '', '', null, '', '', '', '', '80', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('524', '37', 'updateDate', 'varchar', '更新日期', '20', '', null, '', '', '', '', '100', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('525', '37', 'updateMember', 'varchar', '更新人', '32', 'cfg.vo.Member', null, '', '', '', '', '50', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('526', '37', 'updateMember.id', '', '更新人ID', '', '', null, '', '', '', '', '80', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('527', '37', 'updateMember.descr', '', '更新人描述', '', '', null, '', '', '', '', '80', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('528', '37', 'ws0', 'varchar', '扩展0', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('529', '37', 'ws1', 'varchar', '扩展1', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('53', '6', 'validType', 'varchar', '检验类型', '20', null, null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('530', '37', 'ws2', 'varchar', '扩展2', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('531', '37', 'ws3', 'varchar', '扩展3', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('532', '37', 'ws4', 'varchar', '扩展4', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('533', '37', 'ws5', 'varchar', '扩展5', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('534', '37', 'ws6', 'varchar', '扩展6', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('535', '37', 'ws7', 'varchar', '扩展7', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('536', '37', 'ws8', 'varchar', '扩展8', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('537', '37', 'ws9', 'varchar', '扩展9', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('538', '37', 'org', 'varchar', '组织', '32', 'cfg.vo.Org', null, '', '', '', '', '', '', '', '', '10', 'ggg');
INSERT INTO `cfg_property` VALUES ('539', '37', 'org.id', '', '组织ID', '', '', '3', '', 'cfg_selectOrg.sg', '', '', '', '', '', '', '10', 'ggg');
INSERT INTO `cfg_property` VALUES ('54', '6', 'dftvalue', 'varchar', '默认值', '255', null, null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('540', '37', 'org.descr', '', '组织描述', '', '', null, '', '', '', '', '', '', '', '', '10', 'ggg');
INSERT INTO `cfg_property` VALUES ('541', '37', 'member', 'varchar', '人员', '32', 'cfg.vo.Member', null, '', '', '', '', '', '', '', '', '20', 'ggg');
INSERT INTO `cfg_property` VALUES ('542', '37', 'member.id', '', '人员ID', '', '', '3', '', 'cfg_selectMember.sg', '', '', '', '', '', '', '20', 'ggg');
INSERT INTO `cfg_property` VALUES ('543', '37', 'member.descr', '', '人员描述', '', '', null, '', '', '', '', '', '', '', '', '20', 'ggg');
INSERT INTO `cfg_property` VALUES ('545', '38', 'id', 'varchar', 'ID', '32', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('546', '38', 'enable', 'varchar', '有效?', '2', '', '15', '', '', '', '1', '50', '', '', 'enable', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('547', '38', 'createDate', 'varchar', '创建日期', '20', '', null, '', '', '', '', '100', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('548', '38', 'createMember', 'varchar', '创建人', '32', 'cfg.vo.Member', null, '', '', '', '', '50', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('549', '38', 'createMember.id', '', '创建人ID', '', '', null, '', '', '', '', '80', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('55', '6', 'width', 'varchar', '宽度', '255', null, null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('550', '38', 'createMember.descr', '', '创建人描述', '', '', null, '', '', '', '', '80', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('551', '38', 'updateDate', 'varchar', '更新日期', '20', '', null, '', '', '', '', '100', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('552', '38', 'updateMember', 'varchar', '更新人', '32', 'cfg.vo.Member', null, '', '', '', '', '50', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('553', '38', 'updateMember.id', '', '更新人ID', '', '', null, '', '', '', '', '80', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('554', '38', 'updateMember.descr', '', '更新人描述', '', '', null, '', '', '', '', '80', '', '', '', '90', 'ggg');
INSERT INTO `cfg_property` VALUES ('555', '38', 'ws0', 'varchar', '扩展0', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('556', '38', 'ws1', 'varchar', '扩展1', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('557', '38', 'ws2', 'varchar', '扩展2', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('558', '38', 'ws3', 'varchar', '扩展3', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('559', '38', 'ws4', 'varchar', '扩展4', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('56', '6', 'min', 'varchar', '最小', '5', null, '13', null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('560', '38', 'ws5', 'varchar', '扩展5', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('561', '38', 'ws6', 'varchar', '扩展6', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('562', '38', 'ws7', 'varchar', '扩展7', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('563', '38', 'ws8', 'varchar', '扩展8', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('564', '38', 'ws9', 'varchar', '扩展9', '255', '', null, '', '', '', '', '', '', '', '', '99', 'ggg');
INSERT INTO `cfg_property` VALUES ('565', '38', 'role', 'varchar', '角色', '32', 'cfg.vo.Role', null, '', '', '', '', '', '', '', '', '10', 'ggg');
INSERT INTO `cfg_property` VALUES ('566', '38', 'role.id', '', '角色ID', '', '', '3', '', 'cfg_selectRole.sg', '', '', '', '', '', '', '10', 'ggg');
INSERT INTO `cfg_property` VALUES ('567', '38', 'role.descr', '', '角色描述', '', '', null, '', '', '', '', '150', '', '', '', '10', 'ggg');
INSERT INTO `cfg_property` VALUES ('568', '38', 'org', 'varchar', '组织', '32', 'cfg.vo.Org', null, '', '', '', '', '', '', '', '', '10', 'ggg');
INSERT INTO `cfg_property` VALUES ('569', '38', 'org.id', '', '组织ID', '', '', '3', '', 'cfg_selectOrg.sg', '', '', '', '', '', '', '10', 'ggg');
INSERT INTO `cfg_property` VALUES ('57', '6', 'max', 'varchar', '最长', '5', null, '13', null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('570', '38', 'org.descr', '', '组织描述', '', '', null, '', '', '', '', '', '', '', '', '10', 'ggg');
INSERT INTO `cfg_property` VALUES ('58', '6', 'showvalue', 'varchar', '表现值', '255', null, null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('59', '4', 'id', 'varchar', 'ID', '32', null, null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('60', '4', 'descr', 'varchar', '描述', '255', null, null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('61', '4', 'type', 'varchar', '类型', '255', null, null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('62', '5', 'bean.id', '', '所属实体', '', '', '6', null, 'cfg_treeBean.sg', null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('63', '5', 'bean.descr', '', '所属实体描述', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('64', '5', 'permission.descr', '', '对应功能描述', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('65', '5', 'permission.id', '', '对应功能', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('66', '6', 'perf.id', '', '表现', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('67', '6', 'perf.descr', '', '表现描述', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('68', '6', 'property.id', '', '属性', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('69', '6', 'property.descr', '', '属性描述', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('7', '8', 'id', 'varchar', 'ID', '32', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('70', '6', 'myinput.id', '', '控件', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('71', '6', 'myinput.descr', '', '控件', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('72', '16', 'id', 'varchar', 'ID', '32', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('73', '16', 'role', 'varchar', '角色名', '255', '', '1', '1', 'null', '', '', '100', '', '100', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('74', '16', 'descr', 'varchar', '角色描述', '255', '', '1', '0', 'null', '', '', '', '', '100', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('75', '17', 'id', 'varchar', 'ID', '32', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('76', '17', 'org', 'varchar', '组织', '32', 'cfg.vo.Org', null, null, null, null, null, null, '', null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('77', '17', 'org.id', '', '组织ID', '', '', '6', '0', 'cfg_treeOrg.sg', '', '', '111', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('78', '17', 'org.descr', '', '组织描述', '', '', null, '', 'null', '', '', '150', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('79', '17', 'role', 'varchar', '角色', '32', 'cfg.vo.Role', null, null, null, '', null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8', '8', 'bean', 'int', '所属类', '10', 'cfg.vo.Bean', null, null, null, null, null, null, null, null, null, '30', 'ggg');
INSERT INTO `cfg_property` VALUES ('80', '17', 'role.id', '', '角色ID', '', '', '3', '', 'cfg_selectRole.sg', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('81', '17', 'role.descr', '', '角色描述', '', '', null, '', null, '', '', '150', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('82', '3', 'id', 'varchar', 'ID', '32', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('83', '3', 'org', 'varchar', '组织', '32', 'cfg.vo.Org', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('84', '3', 'org.id', '', '组织ID', '', '', '6', '1', 'cfg_treeOrg.sg', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('85', '3', 'org.descr', '', '组织描述', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('86', '3', 'loginId', 'varchar', '名称', '255', '', '1', '1', '', '', '', '100', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('87', '3', 'descr', 'varchar', '描述', '255', '', '1', '1', '', '', '', '100', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('88', '3', 'psd', 'varchar', '密码', '255', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('89', '5', 'permission.namespace', '', '功能namespace', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8a8a11e54f4ebd74014f4ebf7f3a0001', '1', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8a8a11e54f4ebd74014f4ec04e730002', '13', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8a8a11e54f4ebd74014f4ec067070003', '14', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8a8a11e54f4ebd74014f4ec07d030004', '15', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8a8a11e54f4ebd74014f4ec0e2a10005', '16', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8a8a11e54f4ebd74014f4ec148040006', '19', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8a8a11e54f4ebd74014f4ec167df0007', '20', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8a8a11e54f4ebd74014f4ec18ce70008', '21', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8a8a11e54f4ebd74014f4ec1a09f0009', '22', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8a8a11e54f4ebd74014f4ec1b48d000a', '23', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8a8a11e54f4ebd74014f4ec1cd26000b', '26', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8a8a11e54f4ebd74014f4ec1e049000c', '27', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8a8a11e54f4ebd74014f4ec1f58f000d', '3', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8a8a11e54f4ebd74014f4ec20c1c000e', '35', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8a8a11e54f4ebd74014f4ec22631000f', '36', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8a8a11e54f4ebd74014f4ec23e160010', '37', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8a8a11e54f4ebd74014f4ec254d20011', '38', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8a8a11e54f4ebd74014f4ec269aa0012', '4', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8a8a11e54f4ebd74014f4ec2871a0013', '5', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8a8a11e54f4ebd74014f4ec299bb0014', '6', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('8a8a11e54f4ebd74014f4ec2bc0a0015', '8', 'usrct', 'varchar', '用户端', '4', '', null, '', '', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('9', '8', 'property', 'varchar', '属性名', '20', '', '1', '', '', '', '', '', '', '', '', '5', 'ggg');
INSERT INTO `cfg_property` VALUES ('90', '5', 'permission.actionName', '', '功能actionName', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('91', '18', 'id', 'varchar', 'ID', '32', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('92', '18', 'orgRole', 'varchar', '组织角色', '32', 'cfg.vo.OrgRole', '6', '', 'cfg_treeOrgRole.sg', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('93', '18', 'orgRole.id', '', '组织角色ID', '', '', '6', '', 'cfg_treeOrgRole.sg', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('94', '18', 'member', 'varchar', '人员', '32', 'cfg.vo.Member', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('95', '18', 'member.id', '', '人员ID', '', '', '3', '', 'cfg_selectMember.sg', '', '', '', '', '', '', '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('96', '18', 'member.descr', '', '人员描述', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('97', '19', 'id', 'varchar', 'ID', '32', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('98', '19', 'role', 'varchar', '角色', '32', 'cfg.vo.Role', null, null, null, null, null, null, null, null, null, '0', 'ggg');
INSERT INTO `cfg_property` VALUES ('99', '19', 'role.id', '', '角色ID', '', '', null, null, null, null, null, null, null, null, null, '0', 'ggg');

-- ----------------------------
-- Table structure for `cfg_quote`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_quote`;
CREATE TABLE `cfg_quote` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `descr` varchar(255) DEFAULT NULL,
  `oranPerf` varchar(32) DEFAULT NULL,
  `oranProperty` varchar(32) DEFAULT NULL,
  `quotePerf` varchar(32) DEFAULT NULL,
  `quoteProperty` varchar(32) DEFAULT NULL,
  `divTitle` varchar(255) DEFAULT NULL,
  `usrct` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_quote
-- ----------------------------
INSERT INTO `cfg_quote` VALUES ('1', 'bean详细对应Property列表', '6', '1', '9', '15', '对应属性列表', 'ggg');
INSERT INTO `cfg_quote` VALUES ('2', 'Permission详细对应Perf列表', '20', '1', '14', '65', 'Permission详细对应Perf列表', 'ggg');
INSERT INTO `cfg_quote` VALUES ('3', 'Perf详细对应PerfProperty列表', '21', '1', '16', '66', 'Perf详细对应PerfProperty列表', 'ggg');
INSERT INTO `cfg_quote` VALUES ('4', 'Org详细对应的OrgRole列表', '29', '72', '33', '77', '组织中的角色', 'ggg');
INSERT INTO `cfg_quote` VALUES ('5', 'Org详细对应的Member列表', '29', '72', '50', '84', 'Org详细对应的Member列表', 'ggg');
INSERT INTO `cfg_quote` VALUES ('6', '组织角色对应的人员', '47', '75', '48', '93', '组织角色对应的人员', 'ggg');
INSERT INTO `cfg_quote` VALUES ('7', '角色对应的表现', null, null, null, null, '角色对应的表现', 'ggg');
INSERT INTO `cfg_quote` VALUES ('8', null, '66', '111', '68', '121', null, 'ggg');
INSERT INTO `cfg_quote` VALUES ('9', '类修改对应Property列表', '7', '1', '9', '15', '对应属性列表', 'ggg');

-- ----------------------------
-- Table structure for `cfg_role`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_role`;
CREATE TABLE `cfg_role` (
  `id` varchar(32) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `ord` int(2) DEFAULT NULL,
  `usrct` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_role
-- ----------------------------
INSERT INTO `cfg_role` VALUES ('1', 'admin', '系统管理员', '0', 'ggg');
INSERT INTO `cfg_role` VALUES ('402881e74f58a08c014f58a197620001', 'rs', '人事', '0', 'ggg');

-- ----------------------------
-- Table structure for `cfg_role_att`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_role_att`;
CREATE TABLE `cfg_role_att` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `role` varchar(32) DEFAULT NULL,
  `att` varchar(2) DEFAULT NULL,
  `enable` varchar(2) DEFAULT NULL,
  `createDate` varchar(20) DEFAULT NULL,
  `createMember` varchar(32) DEFAULT NULL,
  `updateDate` varchar(20) DEFAULT NULL,
  `updateMember` varchar(32) DEFAULT NULL,
  `ws8` varchar(255) DEFAULT NULL,
  `ws9` varchar(255) DEFAULT NULL,
  `ws0` varchar(255) DEFAULT NULL,
  `ws1` varchar(255) DEFAULT NULL,
  `ws2` varchar(255) DEFAULT NULL,
  `ws3` varchar(255) DEFAULT NULL,
  `ws4` varchar(255) DEFAULT NULL,
  `ws5` varchar(255) DEFAULT NULL,
  `ws6` varchar(255) DEFAULT NULL,
  `ws7` varchar(255) DEFAULT NULL,
  `usrct` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_role_att
-- ----------------------------
INSERT INTO `cfg_role_att` VALUES ('1', '1', '2', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_role_att` VALUES ('2', '402881e74f58a08c014f58a197620001', '5', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `cfg_role_filter`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_role_filter`;
CREATE TABLE `cfg_role_filter` (
  `id` varchar(32) NOT NULL,
  `role` varchar(32) DEFAULT NULL,
  `filter` varchar(32) DEFAULT NULL,
  `usrct` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_role_filter
-- ----------------------------

-- ----------------------------
-- Table structure for `cfg_role_member`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_role_member`;
CREATE TABLE `cfg_role_member` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `role` varchar(32) DEFAULT NULL,
  `member` varchar(32) DEFAULT NULL,
  `enable` varchar(2) DEFAULT NULL,
  `createDate` varchar(20) DEFAULT NULL,
  `createMember` varchar(32) DEFAULT NULL,
  `updateDate` varchar(20) DEFAULT NULL,
  `updateMember` varchar(32) DEFAULT NULL,
  `ws0` varchar(255) DEFAULT NULL,
  `ws1` varchar(255) DEFAULT NULL,
  `ws2` varchar(255) DEFAULT NULL,
  `ws3` varchar(255) DEFAULT NULL,
  `ws4` varchar(255) DEFAULT NULL,
  `ws5` varchar(255) DEFAULT NULL,
  `ws6` varchar(255) DEFAULT NULL,
  `ws7` varchar(255) DEFAULT NULL,
  `ws8` varchar(255) DEFAULT NULL,
  `ws9` varchar(255) DEFAULT NULL,
  `usrct` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_role_member
-- ----------------------------
INSERT INTO `cfg_role_member` VALUES ('1', '1', '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_role_member` VALUES ('2', '1', '18', '-2', '', null, '2015-08-16 16:53', '1', '', '', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_role_member` VALUES ('7', '1', '19', '-2', '2015-08-16 16:58', '1', '2015-08-16 16:58', '1', '', '', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_role_member` VALUES ('8', '1', '18', '-2', '2015-08-16 16:58', '1', '2015-08-16 16:58', '1', '', '', '', '', '', '', '', '', '', '', 'ggg');

-- ----------------------------
-- Table structure for `cfg_role_org`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_role_org`;
CREATE TABLE `cfg_role_org` (
  `id` varchar(32) NOT NULL,
  `role` varchar(32) DEFAULT NULL,
  `org` varchar(32) DEFAULT NULL,
  `enable` varchar(2) DEFAULT NULL,
  `createDate` varchar(20) DEFAULT NULL,
  `createMember` varchar(32) DEFAULT NULL,
  `updateDate` varchar(20) DEFAULT NULL,
  `updateMember` varchar(32) DEFAULT NULL,
  `ws0` varchar(255) DEFAULT NULL,
  `ws1` varchar(255) DEFAULT NULL,
  `ws2` varchar(255) DEFAULT NULL,
  `ws3` varchar(255) DEFAULT NULL,
  `ws4` varchar(255) DEFAULT NULL,
  `ws5` varchar(255) DEFAULT NULL,
  `ws6` varchar(255) DEFAULT NULL,
  `ws7` varchar(255) DEFAULT NULL,
  `ws8` varchar(255) DEFAULT NULL,
  `ws9` varchar(255) DEFAULT NULL,
  `usrct` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_role_org
-- ----------------------------
INSERT INTO `cfg_role_org` VALUES ('1', '1', '2', '-2', null, null, '2015-08-16 17:28', '1', null, null, null, null, null, null, null, null, null, null, 'ggg');
INSERT INTO `cfg_role_org` VALUES ('2', '1', '9', '-2', '2015-08-16 17:31', '1', '2015-08-16 17:33', '1', '', '', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_role_org` VALUES ('3', '1', '4', '-2', '2015-08-16 17:31', '1', '2015-08-16 17:33', '1', '', '', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_role_org` VALUES ('4', '1', '3', '-2', '2015-08-16 17:32', '1', '2015-08-16 17:33', '1', '', '', '', '', '', '', '', '', '', '', 'ggg');
INSERT INTO `cfg_role_org` VALUES ('5', '1', '5', '1', '2015-08-16 17:33', '1', '', null, '', '', '', '', '', '', '', '', '', '', 'ggg');

-- ----------------------------
-- Table structure for `cfg_role_perf`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_role_perf`;
CREATE TABLE `cfg_role_perf` (
  `id` varchar(32) NOT NULL,
  `role` varchar(32) DEFAULT NULL,
  `perf` varchar(32) DEFAULT NULL,
  `usrct` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_role_perf
-- ----------------------------
INSERT INTO `cfg_role_perf` VALUES ('1', '1', '1', 'ggg');
INSERT INTO `cfg_role_perf` VALUES ('10', '1', '42', 'ggg');
INSERT INTO `cfg_role_perf` VALUES ('2', '1', '2', 'ggg');
INSERT INTO `cfg_role_perf` VALUES ('3', '1', '3', 'ggg');
INSERT INTO `cfg_role_perf` VALUES ('5', '1', '5', 'ggg');
INSERT INTO `cfg_role_perf` VALUES ('6', '1', '6', 'ggg');

-- ----------------------------
-- Table structure for `cfg_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_role_permission`;
CREATE TABLE `cfg_role_permission` (
  `id` varchar(32) NOT NULL COMMENT '权限表',
  `role` varchar(32) DEFAULT NULL,
  `permission` varchar(32) DEFAULT NULL,
  `usrct` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_role_permission
-- ----------------------------
INSERT INTO `cfg_role_permission` VALUES ('1', '1', '1', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('10', '1', '16', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('107', '1', '102', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('108', '1', '103', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('109', '1', '104', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('11', '1', '18', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('110', '1', '105', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('111', '1', '107', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('112', '1', '108', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('113', '1', '109', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('114', '1', '110', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('115', '1', '116', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('12', '1', '19', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('13', '1', '21', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('14', '1', '22', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('15', '1', '24', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('16', '1', '25', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('17', '1', '26', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('177', '1', '9', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('178', '1', '11', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('179', '1', '14', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('18', '1', '27', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('181', '1', '20', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('182', '1', '23', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('183', '1', '64', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('184', '1', '69', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('185', '1', '70', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('186', '1', '79', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('187', '1', '101', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('188', '1', '106', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('19', '1', '28', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('2', '1', '2', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('20', '1', '29', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('202', '1', '17', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('203', '1', '119', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('21', '1', '30', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('22', '1', '31', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('23', '1', '32', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('24', '1', '33', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('25', '1', '34', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('26', '1', '35', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('27', '1', '36', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('28', '1', '37', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('29', '1', '38', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('30', '1', '39', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('31', '1', '40', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('32', '1', '41', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('33', '1', '12', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('34', '1', '42', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('35', '1', '43', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('4', '1', '6', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('402881e74f58b3c8014f58c150730001', '1', '402881e64f54de1a014f589fb6ee0024', '');
INSERT INTO `cfg_role_permission` VALUES ('402881e74f58b3c8014f58c150fa0002', '1', '402881e64f54de1a014f589fb72e0025', '');
INSERT INTO `cfg_role_permission` VALUES ('402881e74f58b3c8014f58c1513c0003', '1', '402881e64f54de1a014f589fb7450026', '');
INSERT INTO `cfg_role_permission` VALUES ('402881e74f58b3c8014f58c1515c0004', '1', '402881e64f54de1a014f589fb7890027', '');
INSERT INTO `cfg_role_permission` VALUES ('402881e74f58b3c8014f58c151940005', '1', '402881e64f54de1a014f589fb8360028', '');
INSERT INTO `cfg_role_permission` VALUES ('41', '1', '49', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('42', '1', '50', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('43', '1', '51', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('430', '1', '155', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('431', '1', '156', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('432', '1', '157', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('433', '1', '158', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('434', '1', '159', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('44', '1', '52', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('45', '1', '53', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('479', '1', '166', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('480', '1', '173', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('483', '1', '169', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('484', '1', '170', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('485', '1', '171', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('486', '1', '172', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('5', '1', '5', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('51', '1', '59', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('52', '1', '60', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('53', '1', '61', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('54', '1', '62', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('55', '1', '63', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('59', '1', '65', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('6', '1', '7', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('60', '1', '66', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('61', '1', '67', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('62', '1', '68', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('63', '1', '71', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('64', '1', '72', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('65', '1', '73', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('66', '1', '74', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('67', '1', '75', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('68', '1', '76', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('69', '1', '77', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('7', '1', '10', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('70', '1', '78', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('71', '1', '80', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('72', '1', '81', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('73', '1', '82', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('74', '1', '83', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('8', '1', '13', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('8a8a11de4f398391014f3aa07af8000d', '1', '8a8a11de4f398391014f3a9e76660001', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('8a8a11de4f398391014f3aa07b1e000e', '1', '8a8a11de4f398391014f3a9e76af0002', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('8a8a11de4f398391014f3aa07b3a000f', '1', '8a8a11de4f398391014f3a9e76eb0003', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('8a8a11de4f398391014f3aa07b550010', '1', '8a8a11de4f398391014f3a9e77070004', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('8a8a11de4f398391014f3aa07b7c0011', '1', '8a8a11de4f398391014f3a9e77220005', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('8a8a11de4f398391014f3aa07bad0012', '1', '8a8a11de4f398391014f3a9e773e0006', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('8a8a11de4f398391014f3aa07bc90013', '1', '8a8a11de4f398391014f3a9e77590007', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('8a8a11de4f398391014f3aa07be40014', '1', '8a8a11de4f398391014f3a9e77940008', 'ggg');
INSERT INTO `cfg_role_permission` VALUES ('9', '1', '15', 'ggg');

-- ----------------------------
-- View structure for `view_member_role`
-- ----------------------------
DROP VIEW IF EXISTS `view_member_role`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_member_role` AS select `m1`.`member` AS `member`,`m2`.`role` AS `role` from (`cfg_orgrole_member` `m1` left join `cfg_org_role` `m2` on((`m1`.`orgRole` = `m2`.`id`)));
