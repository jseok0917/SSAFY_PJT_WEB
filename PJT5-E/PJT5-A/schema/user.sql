DROP TABLE IF EXISTS user;

CREATE TABLE user(
  `Id` VARCHAR(300) NOT NULL,
  `password` VARCHAR(300) NOT NULL,
  `nickname` VARCHAR(300) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`Id`)
);

CREATE INDEX idx_nickname ON user (nickname);

SELECT * FROM user;

INSERT INTO `user` (Id, password, nickname)
VALUES 
	('lainlnya', 'ssafy', 'ssafy');
