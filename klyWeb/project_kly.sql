-- CREATE TABLE
CREATE TABLE MEMBER(
	MEMBER_ID NVARCHAR2(50) NOT NULL,
	MEMBER_PW NVARCHAR2(50) NOT NULL,
	MEMBER_EMAIL NVARCHAR2(50) NOT NULL,
	MEMBER_CHECKED NUMBER DEFAULT 0,
	MEMBER_DATE DATE NOT NULL,
	MEMBER_SUSPENDED NUMBER,
	PRIMARY KEY(MEMBER_ID)
);

CREATE TABLE BOARD(
	MEMBER_ID NVARCHAR2(50) NOT NULL,
	BOARD_NUM NUMBER NOT NULL,
	BOARD_SUBJECT NVARCHAR2(100) NOT NULL,
	BOARD_DATE DATE NOT NULL,
	BOARD_FILE NVARCHAR2(200),
	BOARD_URL NVARCHAR2(200),
	BOARD_READCOUNT NUMBER DEFAULT 0,
	BOARD_LIKECOUNT NUMBER DEFAULT 0,
	BOARD_TAG NVARCHAR2(50),
	BOARD_CATEGORY NVARCHAR2(50) NOT NULL,
	PRIMARY KEY(BOARD_NUM),
	CONSTRAINT FK_BOARD FOREIGN KEY(MEMBER_ID)
	REFERENCES MEMBER(MEMBER_ID)
);

CREATE TABLE BOARD_COMMENT ( --COMMENT라는 테이블 생성 불가
	BOARD_NUM NUMBER NOT NULL,
	MEMBER_ID NVARCHAR2(50) NOT NULL,
	COMMENT_NUM NUMBER NOT NULL,
	COMMENT_CON NVARCHAR2(1024) NOT NULL,
	COMMENT_DATE DATE NOT NULL,
	COMMENT_BLIND NUMBER DEFAULT 0,
	PRIMARY KEY(COMMENT_NUM),
	CONSTRAINT FK_COMMENT_MEMBER FOREIGN KEY(MEMBER_ID)
	REFERENCES MEMBER(MEMBER_ID),
	CONSTRAINT FK_COMMENT_ARTICLE FOREIGN KEY(BOARD_NUM)
	REFERENCES BOARD(BOARD_NUM)
);

CREATE TABLE LIKED(
	LIKE_NUM NUMBER NOT NULL,
	MEMBER_ID NVARCHAR2(50) NOT NULL,
	BOARD_NUM NUMBER NOT NULL,
	PRIMARY KEY(LIKE_NUM),
	CONSTRAINT FK_LIKE_MEMBER FOREIGN KEY(MEMBER_ID)
	REFERENCES MEMBER(MEMBER_ID),
	CONSTRAINT FK_LIKE_ARTICLE FOREIGN KEY(BOARD_NUM)
	REFERENCES BOARD(BOARD_NUM)
);

CREATE TABLE REPORT(
	REPORT_NUM NUMBER NOT NULL,
	MEMBER_ID NVARCHAR2(50) NOT NULL, -- 신고한 사람
	BOARD_NUM NUMBER NOT NULL, -- 신고한 게시물
	REPORT_COUNT NUMBER DEFAULT 0, -- 신고 수 , 차단 2개 이상
	REPORT_DATE DATE, -- 신고시간
	PRIMARY KEY(REPORT_NUM),
	CONSTRAINT FK_REPORT_MEMBER FOREIGN KEY(MEMBER_ID)
	REFERENCES MEMBER(MEMBER_ID),
	CONSTRAINT FK_REPORT_ARTICLE FOREIGN KEY(BOARD_NUM)
	REFERENCES BOARD(BOARD_NUM)
);*/

-- QUICK
SELECT * FROM MEMBER;
DROP TABLE BOARD;
DROP TABLE MEMBER;

-- Dummy MEMBER (5)
INSERT INTO MEMBER VALUES('A','1234','koxk@naver.com',0,Sysdate,0);
INSERT INTO MEMBER VALUES('B','1234','dwqwe2@gmail.com',0,sysdate,0);
INSERT INTO MEMBER VALUES('C','1234','Jony123@gmail.com',0,sysdate,0);
INSERT INTO MEMBER VALUES('D','1234','dfdsfw@gmail.com',0,sysdate,0);
INSERT INTO MEMBER VALUES('E','1234','whdwrkdgu@daum.net',0,sysdate,0);
-- Dummy BOARD (5)
INSERT INTO BOARD VALUES('A',1,'aA',Sysdate,null,'<iframe width="420" height="720" src="https://www.youtube.com/embed/zjwqIyoWV8c" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>',0,0,0,'롤','게임');
INSERT INTO BOARD VALUES('B',2,'aB',Sysdate,null,'<iframe width="420" height="720" src="https://www.youtube.com/embed/sB-yuZ2Qncg" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>',0,0,0,'배그','게임');
INSERT INTO BOARD VALUES('C',3,'aC',Sysdate,null,'<iframe width="420" height="720" src="https://www.youtube.com/embed/1yDYGOFc5qY" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>',0,0,0,'일상','유머');
INSERT INTO BOARD VALUES('D',4,'aD',Sysdate,null,'<iframe width="420" height="720" src="https://www.youtube.com/embed/9Xlc_UyjQQY" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>',0,0,0,'축구','스포츠');
INSERT INTO BOARD VALUES('E',5,'aE',Sysdate,null,'<iframe width="420" height="720" src="https://www.youtube.com/embed/f3P_lHInI14" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>',0,0,0,'개','동물');

-- Dummy COMMENT(REPLY) (5)
INSERT INTO REPLY VALUES(1,'tempId',1,'안녕하세요',SYSDATE,0);
INSERT INTO REPLY VALUES(1,'tempId',2,'안녕하세요',SYSDATE,0);
INSERT INTO REPLY VALUES(1,'tempId',3,'안녕하세요',SYSDATE,0);
INSERT INTO REPLY VALUES(1,'tempId',4,'안녕하세요',SYSDATE,0);
INSERT INTO REPLY VALUES(1,'tempId',5,'안녕하세요',SYSDATE,0);
INSERT INTO REPLY VALUES(1,'tempId',6, 'asd',SYSDATE,6);
INSERT INTO REPLY VALUES(1,'tempId',7,'Asdasss',SYSDATE,5);
INSERT INTO REPLY VALUES(1,'tempId',8,'ASdasdsadkj',SYSDATE,4);

-- DAO TEST
UPDATE MEMBER SET MEMBER_PW = '132' WHERE MEMBER_ID = 'asd';

SELECT * FROM MEMBER WHERE MEMBER_ID='123';