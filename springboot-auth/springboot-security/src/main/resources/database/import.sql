INSERT INTO `user_info` (`uid`,`username`,`name`,`password`,`state`) VALUES ('1', 'admin', '管理员', 'E10ADC3949BA59ABBE56E057F20F883E', 0);
INSERT INTO `user_info` (`uid`,`username`,`name`,`password`,`state`) VALUES ('2', 'user', '用户1', 'E10ADC3949BA59ABBE56E057F20F883E', 0);

INSERT INTO `sys_role` (`id`,`available`,`description`,`role`) VALUES (1,0,'管理员','ROLE_ADMIN');
INSERT INTO `sys_role` (`id`,`available`,`description`,`role`) VALUES (2,0,'用户','ROLE_USER');

INSERT INTO `sys_user_role` (`role_id`,`uid`) VALUES (1,1);
INSERT INTO `sys_user_role` (`role_id`,`uid`) VALUES (2,2);

INSERT INTO `sys_permission` (`id`,`available`,`name`,`parent_id`,`parent_ids`,`resource_type`,`url`) VALUES (1,0,'首页',0,'0/','menu','/');
INSERT INTO `sys_permission` (`id`,`available`,`name`,`parent_id`,`parent_ids`,`resource_type`,`url`) VALUES (2,0,'管理首页',1,'0/1','menu','/admin');

INSERT INTO `sys_role_permission` (`permission_id`,`role_id`) VALUES (1,1);
INSERT INTO `sys_role_permission` (`permission_id`,`role_id`) VALUES (2,1);
INSERT INTO `sys_role_permission` (`permission_id`,`role_id`) VALUES (1,2);
