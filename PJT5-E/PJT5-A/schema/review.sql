DROP TABLE IF EXISTS review;

CREATE TABLE review(
  `id` INT NOT NULL AUTO_INCREMENT,
  `writer` VARCHAR(300) CHARACTER SET utf8mb4 NOT NULL,
  `content` TEXT,
  `regDate` TIMESTAMP DEFAULT now(),
  `youtubeId` VARCHAR(500) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT FK_youtubeId FOREIGN KEY (youtubeId) REFERENCES video(youtubeId)
);

SELECT * FROM review;


