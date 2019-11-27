DROP TABLE IF EXISTS user;
CREATE TABLE `user` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `userName` varchar(32) NOT NULL,
  `passWord` varchar(50) NOT NULL,
  `realName` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT into `user` (id,userName,`passWord`,realName) values (1,'张三丰','123','张三');
INSERT into `user` (id,userName,`passWord`,realName) values (2,'李四光','456','李四');