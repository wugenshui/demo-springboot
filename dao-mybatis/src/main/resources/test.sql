DROP TABLE IF EXISTS user;
CREATE TABLE `user` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `userName` varchar(32) NOT NULL,
  `passWord` varchar(50) NOT NULL,
  `realName` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

ALTER TABLE user COMMENT '用户';
ALTER TABLE user MODIFY COLUMN id VARCHAR(100) COMMENT '主键id';
ALTER TABLE user MODIFY COLUMN userName VARCHAR(100) COMMENT '用户名';
ALTER TABLE user MODIFY COLUMN passWord VARCHAR(100) COMMENT '密码';
ALTER TABLE user MODIFY COLUMN realName VARCHAR(100) COMMENT '真实姓名';

INSERT into `user` (id,userName,`passWord`,realName) values (1,'张三丰','123','张三');
INSERT into `user` (id,userName,`passWord`,realName) values (2,'李四光','456','李四');
