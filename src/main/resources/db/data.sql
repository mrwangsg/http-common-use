 DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user(
   id varchar(32) unsigned not null primary key,
	name varchar(32),
	sex varchar(32),
	age int(8)
);

INSERT INTO `user` VALUES ('1', 'test', '2', 2);