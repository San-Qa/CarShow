DROP TABLE IF EXISTS `user` CASCADE;
CREATE TABLE user (
	id BIGINT AUTO_INCREMENT,
	name VARCHAR(255),
	model VARCHAR(255),
	PRIMARY KEY (id)
);