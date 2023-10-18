DROP TABLE IF EXISTS video;

CREATE TABLE video(
  `title` VARCHAR(300) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `fitPartName` VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL,
  `youtubeId` VARCHAR(500) CHARACTER SET utf8mb4 NOT NULL,
  `channelName` VARCHAR(300) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `viewCnt` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`youtubeId`)
);

SELECT * FROM video;

INSERT INTO video (youtubeId, title, fitPartName, channelName, viewCnt)
VALUES
	("gMaB-fG4u4g", "전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]", "전신", "ThankyouBUBU", 27),
	("swRNeYw1JkY", "하루 15분! 전신 칼로리 불태우는 다이어트 운동", "전신", "ThankyouBUBU", 15),
	("54tTYO-vU2E", "상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]", "상체", "ThankyouBUBU", 36),
	("QqqZH3j_vH0", "상체비만 다이어트 최고의 운동 [상체 핵매운맛]", "상체", "ThankyouBUBU", 11),
	("tzN6ypk6Sps", "하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]", "하체", "김강민", 12),
	("u5OgcZdNbMo", "저는 하체 식주의자 입니다", "하체", "GYM종국", 3),
	("PjGcOP-TQPE", "11자복근 복부 최고의 운동 [복근 핵매운맛]", "복부", "ThankyouBUBU", 1),
	("7TLk7pscICk", "(Sub)누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)", "복부", "SomiFit", 2);
