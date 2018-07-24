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
	BOARD_BLIND NUMBER DEFAULT 0,
	BOARD_TAG NVARCHAR2(50),
	BOARD_CATEGORY NVARCHAR2(50) NOT NULL,
	PRIMARY KEY(BOARD_NUM),
	CONSTRAINT FK_BOARD FOREIGN KEY(MEMBER_ID)
	REFERENCES MEMBER(MEMBER_ID)
);

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

-- DAO TEST
UPDATE MEMBER SET MEMBER_PW = '132' WHERE MEMBER_ID = 'asd';