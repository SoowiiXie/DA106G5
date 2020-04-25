--------------------------------------------------------
--1.名軒 會員Drop
--------------------------------------------------------
DROP SEQUENCE "DA106G5"."ABILITY_NO_SEQ";
DROP SEQUENCE "DA106G5"."MB_RPT_NO_SEQ";
DROP SEQUENCE "DA106G5"."NTF_NO_SEQ";
DROP SEQUENCE "DA106G5"."QUESTION_NO_SEQ";
DROP SEQUENCE "DA106G5"."MSG_NO_SEQ";

DROP TABLE "DA106G5"."ABILITY" cascade constraints;
DROP TABLE "DA106G5"."AUTHORITY" cascade constraints;
DROP TABLE "DA106G5"."MB_RPT" cascade constraints;
DROP TABLE "DA106G5"."MEMBER" cascade constraints;
DROP TABLE "DA106G5"."MESSAGE" cascade constraints;
DROP TABLE "DA106G5"."NOTIFY" cascade constraints;
DROP TABLE "DA106G5"."QUESTION_RPT" cascade constraints;
DROP TABLE "DA106G5"."STAFF" cascade constraints;

CREATE TABLE ability (
 ability_no VARCHAR2(2) NOT NULL,
 ability_name VARCHAR2(30) NOT NULL
);

ALTER TABLE ability ADD CONSTRAINT PK_ability PRIMARY KEY (ability_no);


CREATE TABLE member (
 mb_id VARCHAR2(30) NOT NULL,
 mb_pwd VARCHAR2(30) NOT NULL,
 mb_name VARCHAR2(30) NOT NULL,
 mb_gender NUMBER(1) NOT NULL,
 mb_birthday DATE,
 mb_email VARCHAR2(50) NOT NULL,
 mb_pic BLOB,
 mb_lv NUMBER(2) DEFAULT '1' NOT NULL,
 mb_rpt_times NUMBER(2) DEFAULT '0' NOT NULL,
 mb_status NUMBER(1) DEFAULT '1' NOT NULL,
 mb_line_id VARCHAR2(200),
 mb_line_pic BLOB,
 mb_line_display VARCHAR2(200),
 mb_line_status VARCHAR2(200)
);

ALTER TABLE member ADD CONSTRAINT PK_member PRIMARY KEY (mb_id);


CREATE TABLE message (
 msg_no VARCHAR2(30) NOT NULL,
 mb_id_1 VARCHAR2(30) NOT NULL,
 mb_id_2 VARCHAR2(30) NOT NULL,
 msg_content CLOB NOT NULL,
 msg_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 msg_status NUMBER(1) DEFAULT '1' NOT NULL
);

ALTER TABLE message ADD CONSTRAINT PK_message PRIMARY KEY (msg_no);


CREATE TABLE notify (
 ntf_no VARCHAR2(30) NOT NULL,
 mb_id VARCHAR2(30) NOT NULL,
 ntf_content CLOB NOT NULL,
 ntf_status NUMBER(1) DEFAULT '1' NOT NULL
);

ALTER TABLE notify ADD CONSTRAINT PK_notify PRIMARY KEY (ntf_no);


CREATE TABLE question_rpt (
 question_no VARCHAR2(30) NOT NULL,
 mb_id VARCHAR2(30) NOT NULL,
 question_content CLOB NOT NULL,
 question_status NUMBER(1) DEFAULT '1' NOT NULL
);

ALTER TABLE question_rpt ADD CONSTRAINT PK_question_rpt PRIMARY KEY (question_no);


CREATE TABLE staff (
 staff_id VARCHAR2(30) NOT NULL,
 staff_pwd VARCHAR2(30) NOT NULL,
 staff_name VARCHAR2(30) NOT NULL,
 staff_join TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 staff_status NUMBER(1) DEFAULT '1' NOT NULL
);

ALTER TABLE staff ADD CONSTRAINT PK_staff PRIMARY KEY (staff_id);


CREATE TABLE authority (
 staff_id VARCHAR2(30) NOT NULL,
 ability_no VARCHAR2(2) NOT NULL
);

ALTER TABLE authority ADD CONSTRAINT PK_authority PRIMARY KEY (staff_id,ability_no);


CREATE TABLE mb_rpt (
 mb_rpt_no VARCHAR2(30) NOT NULL,
 mb_id_1 VARCHAR2(30) NOT NULL,
 mb_id_2 VARCHAR2(30) NOT NULL,
 rpt_reason CLOB NOT NULL,
 rpt_status NUMBER(1) DEFAULT '1' NOT NULL
);

ALTER TABLE mb_rpt ADD CONSTRAINT PK_mb_rpt PRIMARY KEY (mb_rpt_no);


ALTER TABLE message ADD CONSTRAINT FK_message_0 FOREIGN KEY (mb_id_1) REFERENCES member (mb_id);
ALTER TABLE message ADD CONSTRAINT FK_message_1 FOREIGN KEY (mb_id_2) REFERENCES member (mb_id);


ALTER TABLE notify ADD CONSTRAINT FK_notify_0 FOREIGN KEY (mb_id) REFERENCES member (mb_id);


ALTER TABLE question_rpt ADD CONSTRAINT FK_question_rpt_0 FOREIGN KEY (mb_id) REFERENCES member (mb_id);


ALTER TABLE authority ADD CONSTRAINT FK_authority_0 FOREIGN KEY (staff_id) REFERENCES staff (staff_id);
ALTER TABLE authority ADD CONSTRAINT FK_authority_1 FOREIGN KEY (ability_no) REFERENCES ability (ability_no);


ALTER TABLE mb_rpt ADD CONSTRAINT FK_mb_rpt_0 FOREIGN KEY (mb_id_1) REFERENCES member (mb_id);
ALTER TABLE mb_rpt ADD CONSTRAINT FK_mb_rpt_1 FOREIGN KEY (mb_id_2) REFERENCES member (mb_id);

CREATE SEQUENCE msg_no_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;


CREATE SEQUENCE ntf_no_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

CREATE SEQUENCE mb_rpt_no_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

CREATE SEQUENCE ability_no_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

CREATE SEQUENCE question_no_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

--會員
Insert into MEMBER( MB_ID, MB_PWD, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL) 
values('soowii123','123456','soowii','1',TO_DATE('1990-02-15','YYYY-MM-DD'),'soowii123@yahoo.com');
Insert into MEMBER( MB_ID, MB_PWD, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL) 
values('xuan123','1234567','xuan','1',TO_DATE('1992-05-01','YYYY-MM-DD'),'xuan123@hotmail.com');
Insert into MEMBER( MB_ID, MB_PWD, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL) 
values('michael123','12345678','Michael','1',TO_DATE('1989-06-25','YYYY-MM-DD'),'Michael123@yahoo.com');
Insert into MEMBER( MB_ID, MB_PWD, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL) 
values('vain123','123456789','vain','1',TO_DATE('1987-07-01','YYYY-MM-DD'),'vain123@hotmail.com');
Insert into MEMBER( MB_ID, MB_PWD, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL) 
values('yiwen123','1234566','yiwen123','1',TO_DATE('1985-09-14','YYYY-MM-DD'),'yiwen123@yahoo.com');
Insert into MEMBER( MB_ID, MB_PWD, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL) 
values('weijhih123','1234577','weijhih123','1',TO_DATE('1989-10-19','YYYY-MM-DD'),'weijhih123@yahoo.com');
Insert into MEMBER( MB_ID, MB_PWD, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL) 
values('androidlababy520','androidlababy520','安卓拉寶貝','2',TO_DATE('1989-10-19','YYYY-MM-DD'),'androidlababy520@yahoo.com');
Insert into MEMBER( MB_ID, MB_PWD, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL) 
values('anjavababy520','anjavababy520','一個爪哇寶貝','2',TO_DATE('1989-10-19','YYYY-MM-DD'),'anjavababy520@yahoo.com');

--訊息
Insert into MESSAGE( MSG_NO, MB_ID_1, MB_ID_2, MSG_CONTENT) 
values('MSN'||LPAD(to_char(msg_no_seq.NEXTVAL), 5, '0'),'soowii123','xuan123','哈囉你好啊');
Insert into MESSAGE( MSG_NO, MB_ID_1, MB_ID_2, MSG_CONTENT) 
values('MSN'||LPAD(to_char(msg_no_seq.NEXTVAL), 5, '0'),'xuan123','soowii123','嗨嗨你好');
Insert into MESSAGE( MSG_NO, MB_ID_1, MB_ID_2, MSG_CONTENT) 
values('MSN'||LPAD(to_char(msg_no_seq.NEXTVAL), 5, '0'),'michael123','vain123','我是麥可');
Insert into MESSAGE( MSG_NO, MB_ID_1, MB_ID_2, MSG_CONTENT, MSG_STATUS) 
values('MSN'||LPAD(to_char(msg_no_seq.NEXTVAL), 5, '0'),'vain123','anjavababy520','我是Vain',2);
Insert into MESSAGE( MSG_NO, MB_ID_1, MB_ID_2, MSG_CONTENT, MSG_STATUS) 
values('MSN'||LPAD(to_char(msg_no_seq.NEXTVAL), 5, '0'),'vain123','anjavababy520','哩賀',2);
Insert into MESSAGE( MSG_NO, MB_ID_1, MB_ID_2, MSG_CONTENT, MSG_STATUS) 
values('MSN'||LPAD(to_char(msg_no_seq.NEXTVAL), 5, '0'),'soowii123','anjavababy520','哈囉你好啊',2);
Insert into MESSAGE( MSG_NO, MB_ID_1, MB_ID_2, MSG_CONTENT, MSG_STATUS) 
values('MSN'||LPAD(to_char(msg_no_seq.NEXTVAL), 5, '0'),'xuan123','anjavababy520','嗨嗨你好',2);
Insert into MESSAGE( MSG_NO, MB_ID_1, MB_ID_2, MSG_CONTENT, MSG_STATUS) 
values('MSN'||LPAD(to_char(msg_no_seq.NEXTVAL), 5, '0'),'michael123','anjavababy520','我是麥可',2);
Insert into MESSAGE( MSG_NO, MB_ID_1, MB_ID_2, MSG_CONTENT, MSG_STATUS) 
values('MSN'||LPAD(to_char(msg_no_seq.NEXTVAL), 5, '0'),'vain123','anjavababy520','我是Vain',2);
Insert into MESSAGE( MSG_NO, MB_ID_1, MB_ID_2, MSG_CONTENT, MSG_STATUS) 
values('MSN'||LPAD(to_char(msg_no_seq.NEXTVAL), 5, '0'),'vain123','anjavababy520','哩賀',2);
Insert into MESSAGE( MSG_NO, MB_ID_1, MB_ID_2, MSG_CONTENT, MSG_STATUS) 
values('MSN'||LPAD(to_char(msg_no_seq.NEXTVAL), 5, '0'),'soowii123','anjavababy520','哈囉你好啊',2);
Insert into MESSAGE( MSG_NO, MB_ID_1, MB_ID_2, MSG_CONTENT, MSG_STATUS) 
values('MSN'||LPAD(to_char(msg_no_seq.NEXTVAL), 5, '0'),'androidlababy520','anjavababy520','嗨嗨你好',2);
Insert into MESSAGE( MSG_NO, MB_ID_1, MB_ID_2, MSG_CONTENT, MSG_STATUS) 
values('MSN'||LPAD(to_char(msg_no_seq.NEXTVAL), 5, '0'),'michael123','anjavababy520','我是麥可',2);
Insert into MESSAGE( MSG_NO, MB_ID_1, MB_ID_2, MSG_CONTENT, MSG_STATUS) 
values('MSN'||LPAD(to_char(msg_no_seq.NEXTVAL), 5, '0'),'vain123','anjavababy520','我是Vain',1);
Insert into MESSAGE( MSG_NO, MB_ID_1, MB_ID_2, MSG_CONTENT, MSG_STATUS) 
values('MSN'||LPAD(to_char(msg_no_seq.NEXTVAL), 5, '0'),'vain123','anjavababy520','哩賀',1);

--通知
Insert into NOTIFY( NTF_NO, MB_ID, NTF_CONTENT) 
values('NTF'||LPAD(to_char(ntf_no_seq.NEXTVAL), 5, '0'),'xuan123','這是給xuan123的通知');
Insert into NOTIFY( NTF_NO, MB_ID, NTF_CONTENT) 
values('NTF'||LPAD(to_char(ntf_no_seq.NEXTVAL), 5, '0'),'xuan123','這是給xuan123的通知2');
Insert into NOTIFY( NTF_NO, MB_ID, NTF_CONTENT) 
values('NTF'||LPAD(to_char(ntf_no_seq.NEXTVAL), 5, '0'),'soowii123','這是給soowii123的通知');
Insert into NOTIFY( NTF_NO, MB_ID, NTF_CONTENT) 
values('NTF'||LPAD(to_char(ntf_no_seq.NEXTVAL), 5, '0'),'michael123','這是給michael123的通知');
Insert into NOTIFY( NTF_NO, MB_ID, NTF_CONTENT) 
values('NTF'||LPAD(to_char(ntf_no_seq.NEXTVAL), 5, '0'),'vain123','這是給vain123的通知');

--會員檢舉
Insert into MB_RPT( MB_RPT_NO, MB_ID_1, MB_ID_2, RPT_REASON) 
values('MBR'||LPAD(to_char(MB_RPT_NO_SEQ.NEXTVAL), 5, '0'),'xuan123','michael123','xuan123檢舉michael123');
Insert into MB_RPT( MB_RPT_NO, MB_ID_1, MB_ID_2, RPT_REASON) 
values('MBR'||LPAD(to_char(MB_RPT_NO_SEQ.NEXTVAL), 5, '0'),'soowii123','vain123','soowii123檢舉vain123');
Insert into MB_RPT( MB_RPT_NO, MB_ID_1, MB_ID_2, RPT_REASON) 
values('MBR'||LPAD(to_char(MB_RPT_NO_SEQ.NEXTVAL), 5, '0'),'yiwen123','weijhih123','yiwen123檢舉weijhih123');
Insert into MB_RPT( MB_RPT_NO, MB_ID_1, MB_ID_2, RPT_REASON) 
values('MBR'||LPAD(to_char(MB_RPT_NO_SEQ.NEXTVAL), 5, '0'),'weijhih123','xuan123','weijhih123檢舉xuan123');
Insert into MB_RPT( MB_RPT_NO, MB_ID_1, MB_ID_2, RPT_REASON) 
values('MBR'||LPAD(to_char(MB_RPT_NO_SEQ.NEXTVAL), 5, '0'),'vain123','yiwen123','vain123檢舉yiwen123');

--回報問題
Insert into QUESTION_RPT( QUESTION_NO, MB_ID, QUESTION_CONTENT) 
values('QUR'||LPAD(to_char(QUESTION_NO_SEQ.NEXTVAL), 5, '0'),'xuan123','xuan123回報問題');
Insert into QUESTION_RPT( QUESTION_NO, MB_ID, QUESTION_CONTENT) 
values('QUR'||LPAD(to_char(QUESTION_NO_SEQ.NEXTVAL), 5, '0'),'michael123','michael123回報問題');
Insert into QUESTION_RPT( QUESTION_NO, MB_ID, QUESTION_CONTENT) 
values('QUR'||LPAD(to_char(QUESTION_NO_SEQ.NEXTVAL), 5, '0'),'vain123','vain123回報問題');
Insert into QUESTION_RPT( QUESTION_NO, MB_ID, QUESTION_CONTENT) 
values('QUR'||LPAD(to_char(QUESTION_NO_SEQ.NEXTVAL), 5, '0'),'weijhih123','weijhih123回報問題');
Insert into QUESTION_RPT( QUESTION_NO, MB_ID, QUESTION_CONTENT) 
values('QUR'||LPAD(to_char(QUESTION_NO_SEQ.NEXTVAL), 5, '0'),'soowii123','soowii123回報問題');

--管理員
Insert into STAFF( STAFF_ID, STAFF_PWD, STAFF_NAME) 
values('staff_xuan','1234','xuan');
Insert into STAFF( STAFF_ID, STAFF_PWD, STAFF_NAME) 
values('staff_michael','12345','michael');
Insert into STAFF( STAFF_ID, STAFF_PWD, STAFF_NAME) 
values('staff_vain','123456','vain');
Insert into STAFF( STAFF_ID, STAFF_PWD, STAFF_NAME) 
values('staff_soowii','1234567','soowii');
Insert into STAFF( STAFF_ID, STAFF_PWD, STAFF_NAME) 
values('staff_weijhih','12345678','weijhih');

--功能
Insert into ABILITY( ABILITY_NO, ABILITY_NAME) 
values(LPAD(to_char(ABILITY_NO_SEQ.NEXTVAL), 2, '0'),'管理員資料管理');
Insert into ABILITY( ABILITY_NO, ABILITY_NAME) 
values(LPAD(to_char(ABILITY_NO_SEQ.NEXTVAL), 2, '0'),'會員管理');
Insert into ABILITY( ABILITY_NO, ABILITY_NAME) 
values(LPAD(to_char(ABILITY_NO_SEQ.NEXTVAL), 2, '0'),'留言管理');
Insert into ABILITY( ABILITY_NO, ABILITY_NAME) 
values(LPAD(to_char(ABILITY_NO_SEQ.NEXTVAL), 2, '0'),'檢舉管理');
Insert into ABILITY( ABILITY_NO, ABILITY_NAME) 
values(LPAD(to_char(ABILITY_NO_SEQ.NEXTVAL), 2, '0'),'商城管理');
Insert into ABILITY( ABILITY_NO, ABILITY_NAME) 
values(LPAD(to_char(ABILITY_NO_SEQ.NEXTVAL), 2, '0'),'問題回報管理');

--功能
Insert into AUTHORITY( STAFF_ID, ABILITY_NO) 
values('staff_xuan','01');
Insert into AUTHORITY( STAFF_ID, ABILITY_NO) 
values('staff_xuan','02');
Insert into AUTHORITY( STAFF_ID, ABILITY_NO) 
values('staff_xuan','04');
Insert into AUTHORITY( STAFF_ID, ABILITY_NO) 
values('staff_xuan','05');
Insert into AUTHORITY( STAFF_ID, ABILITY_NO) 
values('staff_michael','01');
Insert into AUTHORITY( STAFF_ID, ABILITY_NO) 
values('staff_michael','02');
Insert into AUTHORITY( STAFF_ID, ABILITY_NO) 
values('staff_michael','03');
Insert into AUTHORITY( STAFF_ID, ABILITY_NO) 
values('staff_michael','04');
Insert into AUTHORITY( STAFF_ID, ABILITY_NO) 
values('staff_vain','02');
Insert into AUTHORITY( STAFF_ID, ABILITY_NO) 
values('staff_vain','03');
Insert into AUTHORITY( STAFF_ID, ABILITY_NO) 
values('staff_vain','04');
Insert into AUTHORITY( STAFF_ID, ABILITY_NO) 
values('staff_soowii','01');
Insert into AUTHORITY( STAFF_ID, ABILITY_NO) 
values('staff_soowii','03');
Insert into AUTHORITY( STAFF_ID, ABILITY_NO) 
values('staff_soowii','05');
Insert into AUTHORITY( STAFF_ID, ABILITY_NO) 
values('staff_weijhih','02');
Insert into AUTHORITY( STAFF_ID, ABILITY_NO) 
values('staff_weijhih','04');

--------------------------------------------------------
--  2.戍乂 天氣、地標 Drop
--------------------------------------------------------
DROP SEQUENCE "DA106G5"."LOC_RPT_SEQ";
DROP SEQUENCE "DA106G5"."LOC_NO_SEQ";
DROP TABLE "DA106G5"."LOC_RPT" cascade constraints;
DROP TABLE "DA106G5"."LOCATION" cascade constraints;
DROP TABLE "DA106G5"."LOC_TYPE" cascade constraints;
DROP TABLE "DA106G5"."WEATHER" cascade constraints;
DROP TABLE "DA106G5"."WEATHER_DETAIL" cascade constraints;

--地標種類 Create--
CREATE TABLE loc_type (
 loc_typeno VARCHAR2(30) NOT NULL,
 loc_info VARCHAR2(30) NOT NULL
);

ALTER TABLE loc_type ADD CONSTRAINT PK_loc_type PRIMARY KEY (loc_typeno);

--地標種類 應該是真資料--
Insert into loc_type (loc_typeno,loc_info) values (1, '地點');
Insert into loc_type (loc_typeno,loc_info) values (2, '廁所');
Insert into loc_type (loc_typeno,loc_info) values (3, '補水點');

--地標 Create--
CREATE TABLE location (
 loc_no VARCHAR2(30) NOT NULL,
 loc_typeno VARCHAR2(30) NOT NULL,
 latitude VARCHAR2(30) NOT NULL,
 longitude VARCHAR2(30) NOT NULL,
 loc_status NUMBER(1) DEFAULT 2 NOT NULL,
 loc_address VARCHAR2(90),
 loc_pic BLOB
);

ALTER TABLE location ADD CONSTRAINT PK_location PRIMARY KEY (loc_no);

--地標有個外鍵在地標種類--
ALTER TABLE location ADD CONSTRAINT FK_location FOREIGN KEY (loc_typeno) REFERENCES loc_type (loc_typeno);

CREATE SEQUENCE loc_no_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE;

--地標 假資料--
Insert into location (loc_no,loc_typeno,latitude,longitude,loc_status,loc_address) values ('loc'||LPAD(to_char(loc_no_seq.NEXTVAL), 5, '0'),1,24.9695021,121.1899436,1,'中央大學網球場');
Insert into location (loc_no,loc_typeno,latitude,longitude,loc_address) values ('loc'||LPAD(to_char(loc_no_seq.NEXTVAL), 5, '0'),1,24.9700902,121.1896228,'公立游泳池');
Insert into location (loc_no,loc_typeno,latitude,longitude,loc_status,loc_address) values ('loc'||LPAD(to_char(loc_no_seq.NEXTVAL), 5, '0'),1,24.9696086,121.1906534,1,'中央大學籃球場');
Insert into location (loc_no,loc_typeno,latitude,longitude,loc_address) values ('loc'||LPAD(to_char(loc_no_seq.NEXTVAL), 5, '0'),1,24.9691063,121.1908299,'國立中央大學羽球館');
Insert into location (loc_no,loc_typeno,latitude,longitude,loc_status,loc_address) values ('loc'||LPAD(to_char(loc_no_seq.NEXTVAL), 5, '0'),1,24.9684828,121.1908519,1,'中央大學健身房');
Insert into location (loc_no,loc_typeno,latitude,longitude,loc_address) values ('loc'||LPAD(to_char(loc_no_seq.NEXTVAL), 5, '0'),1,24.9689838,121.1894409,'國立中央大學攀岩塔');
Insert into location (loc_no,loc_typeno,latitude,longitude,loc_status,loc_address) values ('loc'||LPAD(to_char(loc_no_seq.NEXTVAL), 5, '0'),1,24.9683377,121.1899126,1,'國立中央大學田徑場');
Insert into location (loc_no,loc_typeno,latitude,longitude,loc_status,loc_address) values ('loc'||LPAD(to_char(loc_no_seq.NEXTVAL), 5, '0'),2,24.9678452,121.1913374,1,'資策會一樓右邊廁所');
Insert into location (loc_no,loc_typeno,latitude,longitude,loc_status,loc_address) values ('loc'||LPAD(to_char(loc_no_seq.NEXTVAL), 5, '0'),2,24.9677461,121.1915513,1,'資策會二樓廁所');
Insert into location (loc_no,loc_typeno,latitude,longitude,loc_status,loc_address) values ('loc'||LPAD(to_char(loc_no_seq.NEXTVAL), 5, '0'),3,24.9677546,121.1918363,1,'資策會二樓飲水機');
Insert into location (loc_no,loc_typeno,latitude,longitude,loc_status,loc_address) values ('loc'||LPAD(to_char(loc_no_seq.NEXTVAL), 5, '0'),3,24.9682972,121.1944231,1,'中大總圖飲水機');
Insert into location (loc_no,loc_typeno,latitude,longitude,loc_status,loc_address) values ('loc'||LPAD(to_char(loc_no_seq.NEXTVAL), 5, '0'),3,24.9694752,121.1951473,1,'中大通識教育中心');

--地標檢舉 Create--
CREATE TABLE loc_rpt (
 loc_rpt_no   VARCHAR2(30)          NOT NULL,
 rpt_reason   CLOB,
 rpt_status   NUMBER(1) DEFAULT'1'  NOT NULL,
 loc_no       VARCHAR2(30)          NOT NULL,
 mb_id        VARCHAR2(30)
);

ALTER TABLE loc_rpt ADD CONSTRAINT PK_loc_rpt PRIMARY KEY (loc_rpt_no);

CREATE SEQUENCE loc_rpt_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

--地標檢舉 假資料--
INSERT INTO "LOC_RPT" (LOC_RPT_NO, RPT_REASON, RPT_STATUS, LOC_NO, MB_ID) VALUES ('locr'||LPAD(to_char(LOC_RPT_SEQ.nextval), 5, '0'), 'LOCATIONreport1', '1', 'loc00001', 'soowii123');
INSERT INTO "LOC_RPT" (LOC_RPT_NO, RPT_REASON, LOC_NO, MB_ID) VALUES ('locr'||LPAD(to_char(LOC_RPT_SEQ.nextval), 5, '0'), 'LOCATIONreport2', 'loc00002', 'xuan123');
INSERT INTO "LOC_RPT" (LOC_RPT_NO, RPT_REASON, RPT_STATUS, LOC_NO, MB_ID) VALUES ('locr'||LPAD(to_char(LOC_RPT_SEQ.nextval), 5, '0'), 'LOCATIONreport3', '1', 'loc00003', 'michael123');
INSERT INTO "LOC_RPT" (LOC_RPT_NO, RPT_REASON, LOC_NO, MB_ID) VALUES ('locr'||LPAD(to_char(LOC_RPT_SEQ.nextval), 5, '0'), 'LOCATIONreport4', 'loc00004', 'vain123');
INSERT INTO "LOC_RPT" (LOC_RPT_NO, RPT_REASON, RPT_STATUS, LOC_NO, MB_ID) VALUES ('locr'||LPAD(to_char(LOC_RPT_SEQ.nextval), 5, '0'), 'LOCATIONreport5', '1', 'loc00005', 'yiwen123');


--天氣 Create--
CREATE TABLE weather (
 wth_status VARCHAR2(100) NOT NULL,
 weather_pic BLOB
);

ALTER TABLE weather ADD CONSTRAINT PK_weather PRIMARY KEY (wth_status);

--天氣 假資料--
Insert into weather (wth_status) values ('晴');
Insert into weather (wth_status) values ('雨');
Insert into weather (wth_status) values ('陰');
Insert into weather (wth_status) values ('晴時多雲偶陣雨');
Insert into weather (wth_status) values ('颱風');
Insert into weather (wth_status) values ('多雲時陰');
Insert into weather (wth_status) values ('多雲時晴');
Insert into weather (wth_status) values ('多雲');
Insert into weather (wth_status) values ('陰時多雲短暫陣雨');

--詳細天氣 Create--
CREATE TABLE weather_detail (
 weather_time TIMESTAMP(6) NOT NULL,
 weather_place VARCHAR2(30) NOT NULL,
 wth_status VARCHAR2(100) NOT NULL,
 wth_high NUMBER(3),
 wth_low NUMBER(3),
 wth_comfort VARCHAR2(30),
 wth_rain_chance NUMBER(3)
);

ALTER TABLE weather_detail ADD CONSTRAINT PK_weather_detail PRIMARY KEY (weather_time,weather_place);

--詳細天氣有個外鍵在天氣--
--ALTER TABLE weather_detail ADD CONSTRAINT FK_weather_detail FOREIGN KEY (wth_status) REFERENCES weather (wth_status);

--詳細天氣 假資料--
Insert into weather_detail (weather_time,weather_place,wth_status) values (TO_TIMESTAMP('2020-03-14 06:00:00', 'YYYY-MM-DD HH24:MI:SS'), '桃園市','晴');
Insert into weather_detail (weather_time,weather_place,wth_status) values (TO_TIMESTAMP('2020-03-14 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), '桃園市','雨');
Insert into weather_detail (weather_time,weather_place,wth_status) values (TO_TIMESTAMP('2020-03-14 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), '桃園市','陰');
Insert into weather_detail (weather_time,weather_place,wth_status) values (TO_TIMESTAMP('2020-03-14 06:00:00', 'YYYY-MM-DD HH24:MI:SS'), '高雄市','晴時多雲偶陣雨');
Insert into weather_detail (weather_time,weather_place,wth_status) values (TO_TIMESTAMP('2020-03-14 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), '高雄市','颱風');

--------------------------------------------------------
--  3.偉誌 揪團Drop
--------------------------------------------------------
DROP TABLE "DA106G5"."GROUP_RPT";
DROP TABLE "DA106G5"."GRP_DETAIL";
DROP TABLE "DA106G5"."GRP_FOLLOW";
DROP TABLE "DA106G5"."GROUPER";
--------------------------------------------------------
--  DDL for Table GROUPER
--------------------------------------------------------

  CREATE TABLE "DA106G5"."GROUPER" 
   (	"GRP_NO" VARCHAR2(30 BYTE), 
	"MB_ID" VARCHAR2(30 BYTE), 
	"LOC_NO" VARCHAR2(30 BYTE), 
	"GRP_APPLYSTART" TIMESTAMP, 
	"GRP_APPLYEND" TIMESTAMP, 
	"GRP_START" TIMESTAMP, 
	"GRP_END" TIMESTAMP, 
	"GRP_NAME" VARCHAR2(30 BYTE), 
	"GRP_CONTENT" VARCHAR2(400 BYTE), 
	"GRP_PERSONMAX" NUMBER(2,0), 
	"GRP_PERSONMIN" NUMBER(2,0), 
	"GRP_PERSONCOUNT" NUMBER(2,0), 
	"GRP_STATUS" NUMBER(1,0), 
	"GRP_FOLLOW" NUMBER(5,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table GROUP_RPT
--------------------------------------------------------

  CREATE TABLE "DA106G5"."GROUP_RPT" 
   (	"GROUP_RPT_NO" VARCHAR2(30 BYTE), 
	"GRP_NO" VARCHAR2(30 BYTE), 
	"MB_ID" VARCHAR2(30 BYTE), 
	"RPT_REASON" CLOB, 
	"RPT_STATUS" NUMBER(1,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" 
 LOB ("RPT_REASON") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;
--------------------------------------------------------
--  DDL for Table GRP_DETAIL
--------------------------------------------------------

  CREATE TABLE "DA106G5"."GRP_DETAIL" 
   (	"MB_ID" VARCHAR2(30 BYTE), 
	"GRP_NO" VARCHAR2(30 BYTE), 
	"GRP_REGISTER" NUMBER(1,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table GRP_FOLLOW
--------------------------------------------------------

  CREATE TABLE "DA106G5"."GRP_FOLLOW" 
   (	"GRP_NO" VARCHAR2(30 BYTE), 
	"MB_ID" VARCHAR2(30 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index PK_GROUP_RPT
--------------------------------------------------------

  CREATE UNIQUE INDEX "DA106G5"."PK_GROUP_RPT" ON "DA106G5"."GROUP_RPT" ("GROUP_RPT_NO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index PK_GROUPER
--------------------------------------------------------

  CREATE UNIQUE INDEX "DA106G5"."PK_GROUPER" ON "DA106G5"."GROUPER" ("GRP_NO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index PK_GRP_DETAIL
--------------------------------------------------------

  CREATE UNIQUE INDEX "DA106G5"."PK_GRP_DETAIL" ON "DA106G5"."GRP_DETAIL" ("MB_ID", "GRP_NO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index PK_GRP_FOLLOW
--------------------------------------------------------

  CREATE UNIQUE INDEX "DA106G5"."PK_GRP_FOLLOW" ON "DA106G5"."GRP_FOLLOW" ("GRP_NO", "MB_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table GROUP_RPT
--------------------------------------------------------

  ALTER TABLE "DA106G5"."GROUP_RPT" ADD CONSTRAINT "PK_GROUP_RPT" PRIMARY KEY ("GROUP_RPT_NO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "DA106G5"."GROUP_RPT" MODIFY ("RPT_STATUS" NOT NULL ENABLE);
  ALTER TABLE "DA106G5"."GROUP_RPT" MODIFY ("GROUP_RPT_NO" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table GROUPER
--------------------------------------------------------

  ALTER TABLE "DA106G5"."GROUPER" ADD CONSTRAINT "PK_GROUPER" PRIMARY KEY ("GRP_NO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "DA106G5"."GROUPER" MODIFY ("GRP_FOLLOW" NOT NULL ENABLE);
  ALTER TABLE "DA106G5"."GROUPER" MODIFY ("GRP_STATUS" NOT NULL ENABLE);
  ALTER TABLE "DA106G5"."GROUPER" MODIFY ("GRP_PERSONCOUNT" NOT NULL ENABLE);
  ALTER TABLE "DA106G5"."GROUPER" MODIFY ("GRP_PERSONMIN" NOT NULL ENABLE);
  ALTER TABLE "DA106G5"."GROUPER" MODIFY ("GRP_NAME" NOT NULL ENABLE);
  ALTER TABLE "DA106G5"."GROUPER" MODIFY ("GRP_END" NOT NULL ENABLE);
  ALTER TABLE "DA106G5"."GROUPER" MODIFY ("GRP_START" NOT NULL ENABLE);
  ALTER TABLE "DA106G5"."GROUPER" MODIFY ("GRP_APPLYEND" NOT NULL ENABLE);
  ALTER TABLE "DA106G5"."GROUPER" MODIFY ("GRP_APPLYSTART" NOT NULL ENABLE);
  ALTER TABLE "DA106G5"."GROUPER" MODIFY ("GRP_NO" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table GRP_FOLLOW
--------------------------------------------------------

  ALTER TABLE "DA106G5"."GRP_FOLLOW" ADD CONSTRAINT "PK_GRP_FOLLOW" PRIMARY KEY ("GRP_NO", "MB_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "DA106G5"."GRP_FOLLOW" MODIFY ("MB_ID" NOT NULL ENABLE);
  ALTER TABLE "DA106G5"."GRP_FOLLOW" MODIFY ("GRP_NO" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table GRP_DETAIL
--------------------------------------------------------

  ALTER TABLE "DA106G5"."GRP_DETAIL" ADD CONSTRAINT "PK_GRP_DETAIL" PRIMARY KEY ("MB_ID", "GRP_NO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "DA106G5"."GRP_DETAIL" MODIFY ("GRP_REGISTER" NOT NULL ENABLE);
  ALTER TABLE "DA106G5"."GRP_DETAIL" MODIFY ("GRP_NO" NOT NULL ENABLE);
  ALTER TABLE "DA106G5"."GRP_DETAIL" MODIFY ("MB_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table GROUPER
--------------------------------------------------------

  ALTER TABLE "DA106G5"."GROUPER" ADD CONSTRAINT "FK_GROUPER_0" FOREIGN KEY ("MB_ID")
	  REFERENCES "DA106G5"."MEMBER" ("MB_ID") ENABLE;
  ALTER TABLE "DA106G5"."GROUPER" ADD CONSTRAINT "FK_GROUPER_1" FOREIGN KEY ("LOC_NO")
	  REFERENCES "DA106G5"."LOCATION" ("LOC_NO") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table GROUP_RPT
--------------------------------------------------------

  ALTER TABLE "DA106G5"."GROUP_RPT" ADD CONSTRAINT "FK_GROUP_RPT_0" FOREIGN KEY ("GRP_NO")
	  REFERENCES "DA106G5"."GROUPER" ("GRP_NO") ENABLE;
  ALTER TABLE "DA106G5"."GROUP_RPT" ADD CONSTRAINT "FK_GROUP_RPT_1" FOREIGN KEY ("MB_ID")
	  REFERENCES "DA106G5"."MEMBER" ("MB_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table GRP_DETAIL
--------------------------------------------------------

  ALTER TABLE "DA106G5"."GRP_DETAIL" ADD CONSTRAINT "FK_GRP_DETAIL_0" FOREIGN KEY ("MB_ID")
	  REFERENCES "DA106G5"."MEMBER" ("MB_ID") ENABLE;
  ALTER TABLE "DA106G5"."GRP_DETAIL" ADD CONSTRAINT "FK_GRP_DETAIL_1" FOREIGN KEY ("GRP_NO")
	  REFERENCES "DA106G5"."GROUPER" ("GRP_NO") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table GRP_FOLLOW
--------------------------------------------------------

  ALTER TABLE "DA106G5"."GRP_FOLLOW" ADD CONSTRAINT "FK_GRP_FOLLOW_0" FOREIGN KEY ("GRP_NO")
	  REFERENCES "DA106G5"."GROUPER" ("GRP_NO") ENABLE;
  ALTER TABLE "DA106G5"."GRP_FOLLOW" ADD CONSTRAINT "FK_GRP_FOLLOW_1" FOREIGN KEY ("MB_ID")
	  REFERENCES "DA106G5"."MEMBER" ("MB_ID") ENABLE;

DROP SEQUENCE grp_no_seq;
DROP SEQUENCE group_rpt_no_seq;


CREATE SEQUENCE grp_no_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;
--揪團

Insert into GROUPER(GRP_NO,MB_ID,LOC_NO,GRP_APPLYSTART,GRP_APPLYEND,GRP_START,GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW)
values('grp'||LPAD(to_char(grp_no_seq.NEXTVAL), 5, '0'),'soowii123','loc00001',TO_TIMESTAMP('2020-05-15 08:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-15 18:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-10 08:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-13 20:30','YYYY-MM-DD HH24:MI'),'luck ball go','gogogo',30,5,15,1,30);

Insert into GROUPER(GRP_NO,MB_ID,LOC_NO,GRP_APPLYSTART,GRP_APPLYEND,GRP_START,GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW)
values('grp'||LPAD(to_char(grp_no_seq.NEXTVAL), 5, '0'),'xuan123','loc00002',TO_TIMESTAMP('2020-05-16 08:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-16 18:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-11 08:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-14 20:30','YYYY-MM-DD HH24:MI'),'happy ball go','gogogo',25,10,30,1,35);

Insert into GROUPER(GRP_NO,MB_ID,LOC_NO,GRP_APPLYSTART,GRP_APPLYEND,GRP_START,GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW)
values('grp'||LPAD(to_char(grp_no_seq.NEXTVAL), 5, '0'),'michael123','loc00003',TO_TIMESTAMP('2020-05-17 08:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-17 18:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-12 08:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-15 20:30','YYYY-MM-DD HH24:MI'),'sad ball go','gogogo',30,15,30,1,37);

Insert into GROUPER(GRP_NO,MB_ID,LOC_NO,GRP_APPLYSTART,GRP_APPLYEND,GRP_START,GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW)
values('grp'||LPAD(to_char(grp_no_seq.NEXTVAL), 5, '0'),'vain123','loc00004',TO_TIMESTAMP('2020-05-18 08:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-18 18:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-13 08:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-16 20:30','YYYY-MM-DD HH24:MI'),'laugh ball go','gogogo',35,30,30,1,38);

Insert into GROUPER(GRP_NO,MB_ID,LOC_NO,GRP_APPLYSTART,GRP_APPLYEND,GRP_START,GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW)
values('grp'||LPAD(to_char(grp_no_seq.NEXTVAL), 5, '0'),'yiwen123','loc00005',TO_TIMESTAMP('2020-05-19 08:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-19 18:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-14 08:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-17 20:30','YYYY-MM-DD HH24:MI'),'yummy ball go','gogogo',40,25,30,1,39);

--揪團明細

Insert into grp_detail (mb_id,grp_no,grp_register)
values('soowii123','grp00001',1);
Insert into grp_detail (mb_id,grp_no,grp_register)
values('xuan123','grp00002',1);
Insert into grp_detail (mb_id,grp_no,grp_register)
values('michael123','grp00003',1);
Insert into grp_detail (mb_id,grp_no,grp_register)
values('vain123','grp00004',1);
Insert into grp_detail (mb_id,grp_no,grp_register)
values('yiwen123','grp00005',1);

--關注揪團

Insert into grp_follow (grp_no,mb_id)
values('grp00001','soowii123');

Insert into grp_follow (grp_no,mb_id)
values('grp00002','xuan123');

Insert into grp_follow (grp_no,mb_id)
values('grp00003','michael123');

Insert into grp_follow (grp_no,mb_id)
values('grp00004','vain123');

Insert into grp_follow (grp_no,mb_id)
values('grp00005','yiwen123');

--揪團檢舉

CREATE SEQUENCE group_rpt_no_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

Insert into group_rpt(group_rpt_no,grp_no,rpt_reason,mb_id,rpt_status)
values('grr'||LPAD(to_char(group_rpt_no_seq.NEXTVAL), 5, '0'),'grp00001',null,'soowii123',1);
Insert into group_rpt(group_rpt_no,grp_no,rpt_reason,mb_id,rpt_status)
values('grr'||LPAD(to_char(group_rpt_no_seq.NEXTVAL), 5, '0'),'grp00002',null,'xuan123',2);
Insert into group_rpt(group_rpt_no,grp_no,rpt_reason,mb_id,rpt_status)
values('grr'||LPAD(to_char(group_rpt_no_seq.NEXTVAL), 5, '0'),'grp00003',null,'michael123',3);
Insert into group_rpt(group_rpt_no,grp_no,rpt_reason,mb_id,rpt_status)
values('grr'||LPAD(to_char(group_rpt_no_seq.NEXTVAL), 5, '0'),'grp00004',null,'vain123',2);
Insert into group_rpt(group_rpt_no,grp_no,rpt_reason,mb_id,rpt_status)
values('grr'||LPAD(to_char(group_rpt_no_seq.NEXTVAL), 5, '0'),'grp00005',null,'yiwen123',1);


--------------------------------------------------------
-- 4.昱盛 商城Drop
--------------------------------------------------------

DROP SEQUENCE "DA106G5"."COUPON_SEQ";
DROP SEQUENCE "DA106G5"."ORDERS_SEQ";
DROP SEQUENCE "DA106G5"."PD_TYPE_SEQ";
DROP SEQUENCE "DA106G5"."PRODUCT_SEQ";

DROP TABLE "DA106G5"."PD_FOLLOW" CASCADE CONSTRAINTS;
DROP TABLE "DA106G5"."OD_DETAIL" CASCADE CONSTRAINTS;
DROP TABLE "DA106G5"."CP_GET" CASCADE CONSTRAINTS;
DROP TABLE "DA106G5"."PRODUCT" CASCADE CONSTRAINTS;
DROP TABLE "DA106G5"."PD_TYPE" CASCADE CONSTRAINTS;
DROP TABLE "DA106G5"."ORDERS" CASCADE CONSTRAINTS;
DROP TABLE "DA106G5"."COUPON" CASCADE CONSTRAINTS;


------優惠卷表格------

CREATE TABLE coupon (
 cp_no VARCHAR2(30) NOT NULL, 
 cp_name VARCHAR2(30) NOT NULL,
 cp_price NUMBER(5) NOT NULL,
 cp_pic BLOB
);

ALTER TABLE coupon ADD CONSTRAINT PK_coupon PRIMARY KEY (cp_no);


-----訂單表格------

CREATE TABLE orders (
 od_no VARCHAR2(30) NOT NULL,
 mb_id VARCHAR2(30) NOT NULL,
 od_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
 od_status NUMBER(1) DEFAULT '1' NOT NULL,
 od_totalprice NUMBER(10) NOT NULL,
 cp_no VARCHAR2(30),
 od_discount NUMBER(10),
 od_add VARCHAR2(100) NOT NULL
);

ALTER TABLE orders ADD CONSTRAINT PK_orders PRIMARY KEY (od_no);


-----產品類型表格----

CREATE TABLE pd_type (
 pd_typeno VARCHAR2(30) NOT NULL,
 pd_typename VARCHAR2(40) NOT NULL
);

ALTER TABLE pd_type ADD CONSTRAINT PK_pd_type PRIMARY KEY (pd_typeno);

-----產品表格------

CREATE TABLE product (
 pd_no VARCHAR2(30) NOT NULL,
 pd_name VARCHAR2(100) NOT NULL,
 pd_price NUMBER(10) NOT NULL,
 pd_pic BLOB,
 pd_detail CLOB,
 pd_status NUMBER(1) DEFAULT '1' NOT NULL,
 pd_typeno VARCHAR2(30)
);

ALTER TABLE product ADD CONSTRAINT PK_product PRIMARY KEY (pd_no);


-----持有優惠卷表格------


CREATE TABLE cp_get (
 mb_id VARCHAR2(30) NOT NULL,
 cp_no VARCHAR2(30) NOT NULL,
 cp_status NUMBER(1) DEFAULT '1' NOT NULL
);

ALTER TABLE cp_get ADD CONSTRAINT PK_cp_get PRIMARY KEY (mb_id,cp_no);


-----訂單明細表格-----


CREATE TABLE od_detail (
 od_no VARCHAR2(30) NOT NULL,
 pd_no VARCHAR2(30) NOT NULL,
 od_amount NUMBER(2) NOT NULL,
 od_price NUMBER(10) NOT NULL,
 pd_size VARCHAR2(10)
);

ALTER TABLE od_detail ADD CONSTRAINT PK_od_detail PRIMARY KEY (pd_no,od_no);


-----商品收藏表格-----


CREATE TABLE pd_follow (
 mb_id VARCHAR2(30) NOT NULL,
 pd_no VARCHAR2(30) NOT NULL
);

ALTER TABLE pd_follow ADD CONSTRAINT PK_pd_follow PRIMARY KEY (mb_id,pd_no);


ALTER TABLE orders ADD CONSTRAINT FK_orders_0 FOREIGN KEY (mb_id) REFERENCES member (mb_id);
ALTER TABLE orders ADD CONSTRAINT FK_orders_1 FOREIGN KEY (cp_no) REFERENCES coupon (cp_no);


ALTER TABLE product ADD CONSTRAINT FK_product_0 FOREIGN KEY (pd_typeno) REFERENCES pd_type (pd_typeno);


ALTER TABLE cp_get ADD CONSTRAINT FK_cp_get_0 FOREIGN KEY (mb_id) REFERENCES member (mb_id);
ALTER TABLE cp_get ADD CONSTRAINT FK_cp_get_1 FOREIGN KEY (cp_no) REFERENCES coupon (cp_no);


ALTER TABLE od_detail ADD CONSTRAINT FK_od_detail_0 FOREIGN KEY (pd_no) REFERENCES product (pd_no);
ALTER TABLE od_detail ADD CONSTRAINT FK_od_detail_1 FOREIGN KEY (od_no) REFERENCES orders (od_no);


ALTER TABLE pd_follow ADD CONSTRAINT FK_pd_follow_0 FOREIGN KEY (mb_id) REFERENCES member (mb_id);
ALTER TABLE pd_follow ADD CONSTRAINT FK_pd_follow_1 FOREIGN KEY (pd_no) REFERENCES product (pd_no);


ALTER SESSION SET NLS_TIMESTAMP_FORMAT = 'yyyy-mm-dd hh24:mi:ss';




--------------------COUPON優惠卷--------------------

CREATE SEQUENCE COUPON_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE;

Insert into COUPON (CP_NO,CP_NAME,CP_PRICE) values ('CPN'||LPAD(to_char(COUPON_SEQ.NEXTVAL), 5, '0'),'優惠卷50元','50');
Insert into COUPON (CP_NO,CP_NAME,CP_PRICE) values ('CPN'||LPAD(to_char(COUPON_SEQ.NEXTVAL), 5, '0'),'優惠卷100元','100');
Insert into COUPON (CP_NO,CP_NAME,CP_PRICE) values ('CPN'||LPAD(to_char(COUPON_SEQ.NEXTVAL), 5, '0'),'優惠卷150元','150');
Insert into COUPON (CP_NO,CP_NAME,CP_PRICE) values ('CPN'||LPAD(to_char(COUPON_SEQ.NEXTVAL), 5, '0'),'優惠卷200元','200');
Insert into COUPON (CP_NO,CP_NAME,CP_PRICE) values ('CPN'||LPAD(to_char(COUPON_SEQ.NEXTVAL), 5, '0'),'優惠卷250元','250');
Insert into COUPON (CP_NO,CP_NAME,CP_PRICE) values ('CPN'||LPAD(to_char(COUPON_SEQ.NEXTVAL), 5, '0'),'優惠卷300元','300');


---------------------ORDERS訂單---------------------
CREATE SEQUENCE ORDERS_seq
INCREMENT BY 1
START WITH 6
NOMAXVALUE
NOCYCLE;

Insert into ORDERS(OD_NO, MB_ID, OD_TOTALPRICE, OD_ADD) 
values('20200324-000001','soowii123','300000','中央大學A');
Insert into ORDERS(OD_NO, MB_ID, OD_TOTALPRICE, OD_ADD) 
values('20200324-000002','xuan123','300000','中央大學C');
Insert into ORDERS(OD_NO, MB_ID, OD_TOTALPRICE, OD_ADD) 
values('20200324-000003','michael123','300000','中央大學B');
Insert into ORDERS(OD_NO, MB_ID, OD_TOTALPRICE, OD_ADD) 
values('20200324-000004','vain123','300000','中央大學D');
Insert into ORDERS(OD_NO, MB_ID, OD_TOTALPRICE, OD_ADD) 
values('20200324-000005', 'yiwen123','300000','中央大學E');
Insert into ORDERS(OD_NO, MB_ID, OD_TOTALPRICE, OD_ADD) 
values('20200324-000006', 'weijhih123','300000','中央大學F');

---------------------PD_TYPE商品類別---------------------

CREATE SEQUENCE PD_TYPE_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE;

Insert into PD_TYPE(PD_TYPENO, PD_TYPENAME) 
values('PTN'||LPAD(to_char(PD_TYPE_SEQ.NEXTVAL), 5, '0'),'服飾-男上身');
Insert into PD_TYPE(PD_TYPENO, PD_TYPENAME) 
values('PTN'||LPAD(to_char(PD_TYPE_SEQ.NEXTVAL), 5, '0'),'服飾-男下身');
Insert into PD_TYPE(PD_TYPENO, PD_TYPENAME) 
values('PTN'||LPAD(to_char(PD_TYPE_SEQ.NEXTVAL), 5, '0'),'鞋類-男');
Insert into PD_TYPE(PD_TYPENO, PD_TYPENAME) 
values('PTN'||LPAD(to_char(PD_TYPE_SEQ.NEXTVAL), 5, '0'),'服飾-女上身');
Insert into PD_TYPE(PD_TYPENO, PD_TYPENAME) 
values('PTN'||LPAD(to_char(PD_TYPE_SEQ.NEXTVAL), 5, '0'),'服飾-女下身'); 
Insert into PD_TYPE(PD_TYPENO, PD_TYPENAME) 
values('PTN'||LPAD(to_char(PD_TYPE_SEQ.NEXTVAL), 5, '0'),'鞋類-女');
Insert into PD_TYPE(PD_TYPENO, PD_TYPENAME) 
values('PTN'||LPAD(to_char(PD_TYPE_SEQ.NEXTVAL), 5, '0'),'服飾-兒童上身');
Insert into PD_TYPE(PD_TYPENO, PD_TYPENAME) 
values('PTN'||LPAD(to_char(PD_TYPE_SEQ.NEXTVAL), 5, '0'),'服飾-兒童下身');
Insert into PD_TYPE(PD_TYPENO, PD_TYPENAME) 
values('PTN'||LPAD(to_char(PD_TYPE_SEQ.NEXTVAL), 5, '0'),'鞋類-兒童');
Insert into PD_TYPE(PD_TYPENO, PD_TYPENAME) 
values('PTN'||LPAD(to_char(PD_TYPE_SEQ.NEXTVAL), 5, '0'),'配件-護具');
Insert into PD_TYPE(PD_TYPENO, PD_TYPENAME) 
values('PTN'||LPAD(to_char(PD_TYPE_SEQ.NEXTVAL), 5, '0'),'配件-包款');
Insert into PD_TYPE(PD_TYPENO, PD_TYPENAME) 
values('PTN'||LPAD(to_char(PD_TYPE_SEQ.NEXTVAL), 5, '0'),'配件-3C');

-----------------------PRODUCT商品-----------------------
CREATE SEQUENCE PRODUCT_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE;

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子速乾排汗路跑背心_螢光綠','650','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子速乾排汗路跑背心_藍綠','650','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子簡單主張銀離子運動短袖上衣_黑','850','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子素面拼接運動短袖上衣_黑色','850','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子素面拼接運動短袖上衣_螢光黃','850','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子迷彩運動短袖上衣_黑','850','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子簡單主張銀離子運動短袖上衣_青石綠','850','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子ZP定番運動短袖上衣_深藍','850','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子軍事主義迷彩拼接鋪棉外套_迷彩藍','1250','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子斜紋印花防風外套_黑色','1250','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子經典側邊字母休閒外套_麻灰','1250','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子五分緊身慢跑褲-黑橘','750','PTN00002','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子長條反光運動短褲_黑螢光綠','750','PTN00002','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子炫彩LOGO排汗運動短褲_深灰','750','PTN00002','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子素面防風運動褲_黑','980','PTN00002','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子素面專業運動短褲_深藍','750','PTN00002','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子撞色剪裁排汗運動短褲_鐵灰_螢光綠','750','PTN00002','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子LOGO素面防風運動褲內刷毛款_黑','980','PTN00002','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'美津濃男子路跑鞋_黑灰','2150','PTN00003','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'美津濃男子路跑鞋_藍紅','2150','PTN00003','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子雲豹系列競速路跑鞋_黑黃色','2150','PTN00003','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子雲豹系列競速路跑鞋_黑黃色','2150','PTN00003','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子雲豹系列競速路跑鞋_螢綠黑','2150','PTN00003','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子雲豹系列競速路跑鞋_藍綠','2150','PTN00003','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子雲豹系列競速路跑鞋II代_閃電橘','2150','PTN00003','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子科技世代運動休閒短袖上衣_水藍','580','PTN00004','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子科技感炫彩運動短袖上衣_湖水綠','580','PTN00004','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子素面拼接運動短袖上衣_玫瑰桃','580','PTN00004','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子素面拼接運動短袖上衣_寶貝橘','580','PTN00004','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子速乾排汗路跑背心_桃紅','580','PTN00004','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子多功能九分薄運動褲_黑','690','PTN00005','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子動能緊身運動訓練褲_黑','690','PTN00005','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子復古滾邊休閒長褲_粉膚','770','PTN00005','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子復古滾邊休閒長褲_寶藍','770','PTN00005','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子經典滾邊休閒長褲_湖水綠','770','PTN00005','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'美津濃女子路跑鞋_粉','1750','PTN00006','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子EASYWALK緩震健走鞋_亮橘黑','1750','PTN00006','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子EASYWALK緩震健走鞋_珊瑚紅','1750','PTN00006','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子KIRIN系列減震耐磨運動跑鞋_艷桃紅','1750','PTN00006','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子MissQ多功能休閒鞋_馬卡龍粉','1750','PTN00006','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'兒童經典百搭休閒外套_珊瑚粉','1100','PTN00007','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'兒童經典百搭休閒外套_經典黑','1100','PTN00007','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'兒童撞色休閒刷毛外套_裸磚紅','1100','PTN00007','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'兒童百搭休閒長褲_珊瑚粉','1100','PTN00008','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'兒童百搭休閒長褲_藍綠','1100','PTN00008','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'兒童雙拉鍊休閒長褲_時尚藍','1100','PTN00008','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'兒童雙拉鍊休閒長褲_經典黑','1100','PTN00008','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'兒童百搭休閒長褲_經典黑','1100','PTN00008','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'動感潮童舒適減震運動鞋_橘紅','1750','PTN00009','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'動感潮童舒適減震運動鞋_紫','1750','PTN00009','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'動感潮童舒適減震運動鞋_黑','1750','PTN00009','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'美津濃-半截式頭套_黑','1680','PTN00010','每位跑者的實力不一、狀態各異，Garmin 最懂你心，發展全系列運動穿戴。從使用方便的 GPS 訓練到高階的鐵人三項強力培訓，Garmin 有如陪訓員般貼心守護。您只需設定目標，便可勇往直前。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男女運動壓力襪_藍黃','1680','PTN00010','每位跑者的實力不一、狀態各異，Garmin 最懂你心，發展全系列運動穿戴。從使用方便的 GPS 訓練到高階的鐵人三項強力培訓，Garmin 有如陪訓員般貼心守護。您只需設定目標，便可勇往直前。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男女運動壓力襪_白黑','1680','PTN00010','每位跑者的實力不一、狀態各異，Garmin 最懂你心，發展全系列運動穿戴。從使用方便的 GPS 訓練到高階的鐵人三項強力培訓，Garmin 有如陪訓員般貼心守護。您只需設定目標，便可勇往直前。');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'率性經典休閒後背包_時尚藍','3310','PTN00011','每位跑者的實力不一、狀態各異，Garmin 最懂你心，發展全系列運動穿戴。從使用方便的 GPS 訓練到高階的鐵人三項強力培訓，Garmin 有如陪訓員般貼心守護。您只需設定目標，便可勇往直前。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'單色跑步運動裝備包_俄羅斯綠','3310','PTN00011','每位跑者的實力不一、狀態各異，Garmin 最懂你心，發展全系列運動穿戴。從使用方便的 GPS 訓練到高階的鐵人三項強力培訓，Garmin 有如陪訓員般貼心守護。您只需設定目標，便可勇往直前。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'經典跑步運動裝備包_玫瑰桃','3310','PTN00011','每位跑者的實力不一、狀態各異，Garmin 最懂你心，發展全系列運動穿戴。從使用方便的 GPS 訓練到高階的鐵人三項強力培訓，Garmin 有如陪訓員般貼心守護。您只需設定目標，便可勇往直前。');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'Obeat1光學心率臂帶','3310','PTN00012','每位跑者的實力不一、狀態各異，Garmin 最懂你心，發展全系列運動穿戴。從使用方便的 GPS 訓練到高階的鐵人三項強力培訓，Garmin 有如陪訓員般貼心守護。您只需設定目標，便可勇往直前。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'GPS全方位運動心率錶','3310','PTN00012','每位跑者的實力不一、狀態各異，Garmin 最懂你心，發展全系列運動穿戴。從使用方便的 GPS 訓練到高階的鐵人三項強力培訓，Garmin 有如陪訓員般貼心守護。您只需設定目標，便可勇往直前。');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'GPS三用光學心率錶','3310','PTN00012','每位跑者的實力不一、狀態各異，Garmin 最懂你心，發展全系列運動穿戴。從使用方便的 GPS 訓練到高階的鐵人三項強力培訓，Garmin 有如陪訓員般貼心守護。您只需設定目標，便可勇往直前。');


-----------------------CP_GET持有優惠卷-----------------------

Insert into CP_GET(MB_ID, CP_NO) 
values('soowii123','CPN00001');
Insert into CP_GET(MB_ID, CP_NO) 
values('xuan123','CPN00003');
Insert into CP_GET(MB_ID, CP_NO) 
values('michael123','CPN00001');
Insert into CP_GET(MB_ID, CP_NO) 
values('vain123','CPN00006');
Insert into CP_GET(MB_ID, CP_NO) 
values('yiwen123','CPN00002');
Insert into CP_GET(MB_ID, CP_NO) 
values('weijhih123','CPN00001');

-----------------------OD_DETAIL-----------------------

Insert into OD_DETAIL(OD_NO, PD_NO, OD_AMOUNT, OD_PRICE, PD_SIZE) 
values('20200324-000001','PDN00001','30','300000','XL');
Insert into OD_DETAIL(OD_NO, PD_NO, OD_AMOUNT, OD_PRICE, PD_SIZE) 
values('20200324-000001','PDN00002','30','300000','S');
Insert into OD_DETAIL(OD_NO, PD_NO, OD_AMOUNT, OD_PRICE, PD_SIZE) 
values('20200324-000002','PDN00003','30','300000','US8.5');
Insert into OD_DETAIL(OD_NO, PD_NO, OD_AMOUNT, OD_PRICE, PD_SIZE) 
values('20200324-000002','PDN00004','30','300000','L');
Insert into OD_DETAIL(OD_NO, PD_NO, OD_AMOUNT, OD_PRICE, PD_SIZE) 
values('20200324-000003','PDN00005','30','300000','XL');
Insert into OD_DETAIL(OD_NO, PD_NO, OD_AMOUNT, OD_PRICE, PD_SIZE) 
values('20200324-000003','PDN00006','30','300000','US10.5');


-----------------------PD_FOLLOW商品收藏-----------------------

Insert into PD_FOLLOW(PD_NO, MB_ID) 
values('PDN00001','soowii123');
Insert into PD_FOLLOW(PD_NO, MB_ID) 
values('PDN00002','xuan123');
Insert into PD_FOLLOW(PD_NO, MB_ID) 
values('PDN00003','michael123');
Insert into PD_FOLLOW(PD_NO, MB_ID) 
values('PDN00001','vain123');
Insert into PD_FOLLOW(PD_NO, MB_ID) 
values('PDN00004','yiwen123');
Insert into PD_FOLLOW(PD_NO, MB_ID) 
values('PDN00005','weijhih123');



--------------------------------------------------------
-- 5.麥口 紀錄Drop
--------------------------------------------------------
DROP TABLE RCD_RPT;
DROP TABLE CMT_RPT;
DROP TABLE METOO;
DROP TABLE PATH_FOLLOW;
DROP TABLE THUMB;
DROP TABLE COMMENTT;
DROP TABLE RECORD;
DROP TABLE PATH;
--DROP TABLE MEMBER;
DROP SEQUENCE path_no_seq;
DROP SEQUENCE rcd_no_seq;
DROP SEQUENCE cmt_no_seq;
DROP SEQUENCE rcd_rpt_seq;
DROP SEQUENCE cmt_rpt_seq;

--CREATE TABLE member (
-- mb_id VARCHAR2(30) NOT NULL,
-- mb_pwd VARCHAR2(30) NOT NULL,
-- mb_line VARCHAR2(30),
-- mb_name VARCHAR2(30) NOT NULL,
-- mb_gender NUMBER(1) NOT NULL,
-- mb_birthday DATE,
-- mb_lv NUMBER(2) DEFAULT '1' NOT NULL,
-- mb_pic BLOB,
-- mb_rpt_times NUMBER(2) DEFAULT '0' NOT NULL,
-- mb_email VARCHAR2(30) NOT NULL,
-- mb_status NUMBER(1) DEFAULT '1' NOT NULL
--);

--ALTER TABLE member ADD CONSTRAINT PK_member PRIMARY KEY (mb_id);


CREATE TABLE path (
 path_no         VARCHAR2(30)               NOT NULL,
 path_name       VARCHAR2(300),
 path_difficulty NUMBER(1)    DEFAULT '1',
 path_popular    NUMBER(1)    DEFAULT '1'   NOT NULL,
 path_start      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 path_end        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 path_pic        BLOB,
 path_distance   NUMBER(10,2),
 path_status     NUMBER(1)    DEFAULT '1'   NOT NULL,
 path_kml        CLOB,
 path_lng        NUMBER(30, 6),
 path_lat        NUMBER(30, 6)
);

ALTER TABLE path ADD CONSTRAINT PK_path PRIMARY KEY (path_no);

CREATE SEQUENCE path_no_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;


CREATE TABLE record (
 rcd_no            VARCHAR2(30)  NOT NULL,
 rcd_uploadtime    DATE,
 rcd_content       CLOB,
 rcd_thumb_amount  NUMBER(5),
 rcd_metoo_amount  NUMBER(5),
 rcd_status        NUMBER(1)  DEFAULT'1',
 path_no           VARCHAR2(30),
 mb_id             VARCHAR2(30)  NOT NULL
);

ALTER TABLE record ADD CONSTRAINT PK_record PRIMARY KEY (rcd_no);


CREATE SEQUENCE rcd_no_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;


CREATE TABLE commentt (
 cmt_no       VARCHAR2(30)          NOT NULL,
 cmt_content  CLOB,
 cmt_time     DATE,
 cmt_status   NUMBER(1) DEFAULT'1'  NOT NULL,
 rcd_no       VARCHAR2(30)          NOT NULL,
 mb_id        VARCHAR2(30)          NOT NULL
);

ALTER TABLE commentt ADD CONSTRAINT PK_comment PRIMARY KEY (cmt_no);

CREATE SEQUENCE cmt_no_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;


CREATE TABLE path_follow (
 path_no   VARCHAR2(30) NOT NULL,
 mb_id     VARCHAR2(30) NOT NULL
);

ALTER TABLE path_follow ADD CONSTRAINT PK_path_follow PRIMARY KEY (path_no,mb_id);


CREATE TABLE thumb (
 rcd_no   VARCHAR2(30) NOT NULL,
 mb_id    VARCHAR2(30) NOT NULL
);

ALTER TABLE thumb ADD CONSTRAINT PK_thumb PRIMARY KEY (rcd_no,mb_id);


CREATE TABLE metoo (
 rcd_no   VARCHAR2(30) NOT NULL,
 mb_id    VARCHAR2(30) NOT NULL
);

ALTER TABLE metoo ADD CONSTRAINT PK_metoo PRIMARY KEY (rcd_no,mb_id);


CREATE TABLE rcd_rpt (
 rcd_rpt_no   VARCHAR2(30)          NOT NULL,
 rpt_reason   CLOB,
 rpt_status   NUMBER(1) DEFAULT'1'  NOT NULL,
 rcd_no       VARCHAR2(30)          NOT NULL,
 mb_id        VARCHAR2(30)
);

ALTER TABLE rcd_rpt ADD CONSTRAINT PK_rcd_rpt PRIMARY KEY (rcd_rpt_no);

CREATE SEQUENCE rcd_rpt_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;



CREATE TABLE cmt_rpt (
 cmt_rpt_no   VARCHAR2(30)          NOT NULL,
 rpt_reason   CLOB,
 rpt_status   NUMBER(1) DEFAULT'1'  NOT NULL,
 cmt_no       VARCHAR2(30)          NOT NULL,
 mb_id        VARCHAR2(30)
);

ALTER TABLE cmt_rpt ADD CONSTRAINT PK_cmt_rpt PRIMARY KEY (cmt_rpt_no);

CREATE SEQUENCE cmt_rpt_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;


ALTER TABLE path_follow ADD CONSTRAINT FK_pathfollow_path FOREIGN KEY (path_no) REFERENCES path (path_no);
ALTER TABLE path_follow ADD CONSTRAINT FK_pathfollow_member FOREIGN KEY (mb_id) REFERENCES member (mb_id);


ALTER TABLE record ADD CONSTRAINT FK_record_path FOREIGN KEY (path_no) REFERENCES path (path_no);
ALTER TABLE record ADD CONSTRAINT FK_record_member FOREIGN KEY (mb_id) REFERENCES member (mb_id);


ALTER TABLE thumb ADD CONSTRAINT FK_thumb_record FOREIGN KEY (rcd_no) REFERENCES record (rcd_no);
ALTER TABLE thumb ADD CONSTRAINT FK_thumb_member FOREIGN KEY (mb_id) REFERENCES member (mb_id);


ALTER TABLE commentt ADD CONSTRAINT FK_comment_record FOREIGN KEY (rcd_no) REFERENCES record (rcd_no);
ALTER TABLE commentt ADD CONSTRAINT FK_comment_member FOREIGN KEY (mb_id) REFERENCES member (mb_id);


ALTER TABLE metoo ADD CONSTRAINT FK_metoo_record FOREIGN KEY (rcd_no) REFERENCES record (rcd_no);
ALTER TABLE metoo ADD CONSTRAINT FK_metoo_member FOREIGN KEY (mb_id) REFERENCES member (mb_id);


ALTER TABLE rcd_rpt ADD CONSTRAINT FK_rcdrpt_record FOREIGN KEY (rcd_no) REFERENCES record (rcd_no);
ALTER TABLE rcd_rpt ADD CONSTRAINT FK_rcdrpt_member FOREIGN KEY (mb_id) REFERENCES member (mb_id);


ALTER TABLE cmt_rpt ADD CONSTRAINT FK_cmtrpt_comment FOREIGN KEY (cmt_no) REFERENCES commentt (cmt_no);
ALTER TABLE cmt_rpt ADD CONSTRAINT FK_cmtrpt_member FOREIGN KEY (mb_id) REFERENCES member (mb_id);



--PATH   																							         							
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(path_NO_SEQ.NEXTVAL), 5, '0'),'path1','1',1,to_timestamp('2020-03-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),to_timestamp('2020-03-01 00:20:00', 'YYYY-MM-DD HH24:MI:SS'),300.22,1,'[{"LNG":"121.4179018","LAT":"25.12907787"},{"LNG":"121.4177913","LAT":"25.12897565"},{"LNG":"121.4177227","LAT":"25.12900364"},{"LNG":"121.4176863","LAT":"25.12891084"},{"LNG":"121.417482","LAT":"25.12892272"},{"LNG":"121.4174582","LAT":"25.12903347"},{"LNG":"121.4174005","LAT":"25.12906141"},{"LNG":"121.4173325","LAT":"25.12906964"},{"LNG":"121.4173052","LAT":"25.12911155"},{"LNG":"121.4173021","LAT":"25.12919337"},{"LNG":"121.4172564","LAT":"25.12923145"},{"LNG":"121.4171765","LAT":"25.12923716"},{"LNG":"121.4171212","LAT":"25.12916482"},{"LNG":"121.4169185","LAT":"25.12901096"},{"LNG":"121.4167501","LAT":"25.12893259"},{"LNG":"121.4167139","LAT":"25.1289402"},{"LNG":"121.4166587","LAT":"25.12900302"},{"LNG":"121.4166054","LAT":"25.12901825"},{"LNG":"121.4165749","LAT":"25.12899921"},{"LNG":"121.4164836","LAT":"25.1287251"},{"LNG":"121.4163712","LAT":"25.12866989"},{"LNG":"121.4163363","LAT":"25.12868583"},{"LNG":"121.4163363","LAT":"25.1287715"},{"LNG":"121.4162977","LAT":"25.12883003"},{"LNG":"121.4162306","LAT":"25.12884145"},{"LNG":"121.416178","LAT":"25.12880369"},{"LNG":"121.4161378","LAT":"25.12880433"},{"LNG":"121.4161264","LAT":"25.12883289"},{"LNG":"121.4161257","LAT":"25.12891736"},{"LNG":"121.4160743","LAT":"25.1289421"},{"LNG":"121.4159997","LAT":"25.12891962"},{"LNG":"121.4159504","LAT":"25.12892655"},{"LNG":"121.4159224","LAT":"25.12894069"},{"LNG":"121.4158855","LAT":"25.1289398"},{"LNG":"121.4158399","LAT":"25.12895563"},{"LNG":"121.4158121","LAT":"25.12899035"},{"LNG":"121.4158263","LAT":"25.12912418"},{"LNG":"121.4158337","LAT":"25.12917458"},{"LNG":"121.4157995","LAT":"25.12922217"},{"LNG":"121.4156103","LAT":"25.12933807"},{"LNG":"121.4155798","LAT":"25.12939327"},{"LNG":"121.4155684","LAT":"25.12972069"},{"LNG":"121.4156023","LAT":"25.12980578"},{"LNG":"121.4156051","LAT":"25.12980744"},{"LNG":"121.4156122","LAT":"25.12985013"},{"LNG":"121.4155951","LAT":"25.12991104"},{"LNG":"121.4151534","LAT":"25.13036409"},{"LNG":"121.4151439","LAT":"25.13039455"},{"LNG":"121.4151668","LAT":"25.13051638"},{"LNG":"121.4151572","LAT":"25.13055064"},{"LNG":"121.4151321","LAT":"25.13057489"},{"LNG":"121.4149936","LAT":"25.13065627"},{"LNG":"121.4147509","LAT":"25.1307148"},{"LNG":"121.4145567","LAT":"25.13073051"},{"LNG":"121.4145157","LAT":"25.13072958"},{"LNG":"121.4144896","LAT":"25.13074193"},{"LNG":"121.4141978","LAT":"25.13103034"},{"LNG":"121.4141369","LAT":"25.13118834"}]',121.4179018,25.12907787);
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(PATH_NO_SEQ.NEXTVAL), 5, '0'),'path2','2',2,to_timestamp('2020-03-05 01:12:12', 'YYYY-MM-DD HH24:MI:SS'),to_timestamp('2020-03-05 01:28:12', 'YYYY-MM-DD HH24:MI:SS'),3001.65,1,'[{"LNG":"121.426705","LAT":"25.13590826"},{"LNG":"121.4266732","LAT":"25.13592384"},{"LNG":"121.4266057","LAT":"25.13593099"},{"LNG":"121.4265606","LAT":"25.13590883"},{"LNG":"121.4265255","LAT":"25.13590571"},{"LNG":"121.4264895","LAT":"25.13592217"},{"LNG":"121.4263113","LAT":"25.13597638"},{"LNG":"121.4260701","LAT":"25.13602407"},{"LNG":"121.4256089","LAT":"25.13603539"},{"LNG":"121.4253291","LAT":"25.13604681"},{"LNG":"121.4252232","LAT":"25.13607757"},{"LNG":"121.4251882","LAT":"25.13608679"},{"LNG":"121.4251521","LAT":"25.13607536"},{"LNG":"121.4250044","LAT":"25.13597879"},{"LNG":"121.4244308","LAT":"25.13578384"},{"LNG":"121.4240193","LAT":"25.13560759"},{"LNG":"121.4236398","LAT":"25.1353461"},{"LNG":"121.4236053","LAT":"25.1352828"},{"LNG":"121.4236196","LAT":"25.13482951"},{"LNG":"121.4237334","LAT":"25.13378602"},{"LNG":"121.4240871","LAT":"25.13323051"},{"LNG":"121.4248188","LAT":"25.13239175"},{"LNG":"121.4250956","LAT":"25.13199404"},{"LNG":"121.4250849","LAT":"25.13166568"},{"LNG":"121.4250116","LAT":"25.1316101"},{"LNG":"121.4249259","LAT":"25.13166007"},{"LNG":"121.4248757","LAT":"25.13180197"},{"LNG":"121.4247152","LAT":"25.13190729"},{"LNG":"121.4244504","LAT":"25.1319525"},{"LNG":"121.4242945","LAT":"25.13196059"},{"LNG":"121.424141","LAT":"25.13192871"},{"LNG":"121.4237615","LAT":"25.13185209"},{"LNG":"121.4237234","LAT":"25.13181973"},{"LNG":"121.4234782","LAT":"25.13127959"},{"LNG":"121.4235013","LAT":"25.13124835"},{"LNG":"121.4235464","LAT":"25.13124485"},{"LNG":"121.4236263","LAT":"25.13127341"},{"LNG":"121.4237082","LAT":"25.13127531"},{"LNG":"121.4238205","LAT":"25.13122011"},{"LNG":"121.4238587","LAT":"25.1311242"},{"LNG":"121.4238357","LAT":"25.13108686"},{"LNG":"121.4237082","LAT":"25.13089079"},{"LNG":"121.4236778","LAT":"25.13074764"},{"LNG":"121.423712","LAT":"25.13071566"},{"LNG":"121.4238026","LAT":"25.13070798"},{"LNG":"121.4238433","LAT":"25.13066426"},{"LNG":"121.423849","LAT":"25.13048723"},{"LNG":"121.4238243","LAT":"25.13035589"},{"LNG":"121.4237824","LAT":"25.13026642"},{"LNG":"121.4237367","LAT":"25.13024929"},{"LNG":"121.4237242","LAT":"25.1302082"},{"LNG":"121.4237478","LAT":"25.13018013"},{"LNG":"121.423796","LAT":"25.13018873"},{"LNG":"121.4239239","LAT":"25.13026904"},{"LNG":"121.424004","LAT":"25.13024202"},{"LNG":"121.4240548","LAT":"25.13018873"},{"LNG":"121.4244801","LAT":"25.12972473"},{"LNG":"121.4246262","LAT":"25.12941276"},{"LNG":"121.4249895","LAT":"25.12888108"},{"LNG":"121.4250036","LAT":"25.1288372"},{"LNG":"121.424971","LAT":"25.12881656"},{"LNG":"121.42468","LAT":"25.12888478"},{"LNG":"121.4246479","LAT":"25.12888003"},{"LNG":"121.4246426","LAT":"25.12885357"},{"LNG":"121.4248192","LAT":"25.12865636"},{"LNG":"121.4248251","LAT":"25.12862542"},{"LNG":"121.4248001","LAT":"25.12861947"},{"LNG":"121.4244869","LAT":"25.12864527"},{"LNG":"121.4244103","LAT":"25.12859284"},{"LNG":"121.4243766","LAT":"25.12855999"},{"LNG":"121.4243778","LAT":"25.12840532"}]',121.426705,25.13590826);
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(PATH_NO_SEQ.NEXTVAL), 5, '0'),'path3','3',1,to_timestamp('2020-03-10 11:11:11', 'YYYY-MM-DD HH24:MI:SS'),to_timestamp('2020-03-10 11:17:58', 'YYYY-MM-DD HH24:MI:SS'),501,1,'[{"LNG":"121.0237348","LAT":"24.65538953"},{"LNG":"121.0236574","LAT":"24.65544188"},{"LNG":"121.0236182","LAT":"24.65546448"},{"LNG":"121.0235907","LAT":"24.65552716"},{"LNG":"121.023585","LAT":"24.65559378"},{"LNG":"121.0236154","LAT":"24.65564327"},{"LNG":"121.0236991","LAT":"24.65568458"},{"LNG":"121.02378","LAT":"24.65569648"},{"LNG":"121.0238972","LAT":"24.65569848"},{"LNG":"121.0240487","LAT":"24.65572289"},{"LNG":"121.0241144","LAT":"24.65578"},{"LNG":"121.0240932","LAT":"24.6558698"},{"LNG":"121.0240951","LAT":"24.65598211"},{"LNG":"121.0241417","LAT":"24.65619141"},{"LNG":"121.0242594","LAT":"24.65637939"},{"LNG":"121.0243519","LAT":"24.65646927"},{"LNG":"121.0244533","LAT":"24.65648212"},{"LNG":"121.0245446","LAT":"24.65644625"},{"LNG":"121.0246645","LAT":"24.65629206"},{"LNG":"121.0247616","LAT":"24.65617784"},{"LNG":"121.0248388","LAT":"24.65606411"}]',121.023734,24.655389);
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(PATH_NO_SEQ.NEXTVAL), 5, '0'),'path4','1',1,to_timestamp('2020-03-15 22:22:22', 'YYYY-MM-DD HH24:MI:SS'),to_timestamp('2020-03-15 23:22:25', 'YYYY-MM-DD HH24:MI:SS'),1500.88,1,'[{"LNG":"121.0229761","LAT":"24.65562083"},{"LNG":"121.0230731","LAT":"24.65547806"},{"LNG":"121.0231729","LAT":"24.655306"},{"LNG":"121.0231931","LAT":"24.65521537"},{"LNG":"121.0232149","LAT":"24.655077"},{"LNG":"121.0232599","LAT":"24.654975"},{"LNG":"121.0233359","LAT":"24.654894"},{"LNG":"121.0234159","LAT":"24.65481746"},{"LNG":"121.0235872","LAT":"24.65476035"},{"LNG":"121.0236519","LAT":"24.65469943"},{"LNG":"121.02369","LAT":"24.65459855"},{"LNG":"121.0236881","LAT":"24.65451669"},{"LNG":"121.0236691","LAT":"24.65442151"},{"LNG":"121.0236557","LAT":"24.65429397"},{"LNG":"121.023709","LAT":"24.65418357"},{"LNG":"121.0237947","LAT":"24.65407316"},{"LNG":"121.0238137","LAT":"24.65394943"},{"LNG":"121.0237852","LAT":"24.65387709"},{"LNG":"121.0236938","LAT":"24.65378953"},{"LNG":"121.0236177","LAT":"24.65363344"},{"LNG":"121.0235073","LAT":"24.65350019"},{"LNG":"121.023296","LAT":"24.65341262"},{"LNG":"121.0232312","LAT":"24.65340501"},{"LNG":"121.0231875","LAT":"24.65336884"},{"LNG":"121.0228981","LAT":"24.65296148"},{"LNG":"121.0228505","LAT":"24.65283774"},{"LNG":"121.0228658","LAT":"24.65272353"},{"LNG":"121.0229019","LAT":"24.65262264"},{"LNG":"121.0229857","LAT":"24.65252175"},{"LNG":"121.0231303","LAT":"24.65247607"},{"LNG":"121.0232255","LAT":"24.65250843"},{"LNG":"121.023275","LAT":"24.65255411"},{"LNG":"121.0234007","LAT":"24.65259218"},{"LNG":"121.0235225","LAT":"24.65255792"},{"LNG":"121.0235986","LAT":"24.6524399"},{"LNG":"121.0235757","LAT":"24.65221725"},{"LNG":"121.0235224","LAT":"24.65192981"},{"LNG":"121.0234691","LAT":"24.65163476"},{"LNG":"121.0233055","LAT":"24.65121154"},{"LNG":"121.0224987","LAT":"24.65049824"},{"LNG":"121.0221355","LAT":"24.65016077"},{"LNG":"121.0219228","LAT":"24.65002681"},{"LNG":"121.0218638","LAT":"24.65001539"},{"LNG":"121.0218009","LAT":"24.65004204"},{"LNG":"121.0217724","LAT":"24.65009343"},{"LNG":"121.0216087","LAT":"24.65054077"},{"LNG":"121.0215478","LAT":"24.65059407"},{"LNG":"121.021464","LAT":"24.6506093"},{"LNG":"121.0213533","LAT":"24.65057726"},{"LNG":"121.0212353","LAT":"24.65050111"}]',121.0229761,24.65562083);
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(PATH_NO_SEQ.NEXTVAL), 5, '0'),'path5','3',2,to_timestamp('2020-03-30 18:18:18', 'YYYY-MM-DD HH24:MI:SS'),to_timestamp('2020-03-30 18:56:12', 'YYYY-MM-DD HH24:MI:SS'),464.21,1,'[{"LNG":"120.9972322","LAT":"24.57346143"},{"LNG":"120.9973021","LAT":"24.57328157"},{"LNG":"120.9973288","LAT":"24.57325967"},{"LNG":"120.9973545","LAT":"24.57326253"},{"LNG":"120.9973669","LAT":"24.57323017"},{"LNG":"120.9973716","LAT":"24.573194"},{"LNG":"120.997383","LAT":"24.57317401"},{"LNG":"120.99742","LAT":"24.57317353"},{"LNG":"120.9975034","LAT":"24.57316162"},{"LNG":"120.997557","LAT":"24.57311046"},{"LNG":"120.9975472","LAT":"24.57305774"},{"LNG":"120.9974523","LAT":"24.57296769"},{"LNG":"120.9973345","LAT":"24.57289869"},{"LNG":"120.9973202","LAT":"24.57286775"},{"LNG":"120.9974035","LAT":"24.57283444"},{"LNG":"120.9974963","LAT":"24.57285348"},{"LNG":"120.9975496","LAT":"24.57284834"},{"LNG":"120.9975544","LAT":"24.57280313"},{"LNG":"120.997433","LAT":"24.57268654"},{"LNG":"120.9973688","LAT":"24.57265799"},{"LNG":"120.997323","LAT":"24.572663"},{"LNG":"120.997248","LAT":"24.572669"},{"LNG":"120.997184","LAT":"24.572619"},{"LNG":"120.9970716","LAT":"24.57258103"},{"LNG":"120.9969847","LAT":"24.57254653"},{"LNG":"120.996955","LAT":"24.57247038"},{"LNG":"120.996974","LAT":"24.57239662"},{"LNG":"120.9970597","LAT":"24.57238353"},{"LNG":"120.9971572","LAT":"24.57245611"},{"LNG":"120.9972809","LAT":"24.57247871"},{"LNG":"120.9973833","LAT":"24.57249537"},{"LNG":"120.997424","LAT":"24.572476"},{"LNG":"120.9974082","LAT":"24.57243469"},{"LNG":"120.9972831","LAT":"24.57232486"},{"LNG":"120.9970799","LAT":"24.57222173"},{"LNG":"120.9969383","LAT":"24.57217652"},{"LNG":"120.9968931","LAT":"24.57210395"},{"LNG":"120.9968824","LAT":"24.57202186"},{"LNG":"120.9969109","LAT":"24.5719469"},{"LNG":"120.9969966","LAT":"24.57192906"},{"LNG":"120.997043","LAT":"24.57193857"},{"LNG":"120.9970537","LAT":"24.57200282"},{"LNG":"120.9970751","LAT":"24.57203375"},{"LNG":"120.9972274","LAT":"24.57208491"},{"LNG":"120.9972964","LAT":"24.57208134"},{"LNG":"120.9973131","LAT":"24.57203732"},{"LNG":"120.9972426","LAT":"24.57188704"},{"LNG":"120.9970475","LAT":"24.57167448"},{"LNG":"120.996898","LAT":"24.571453"},{"LNG":"120.9968182","LAT":"24.57122857"},{"LNG":"120.996817","LAT":"24.57115"},{"LNG":"120.9968695","LAT":"24.57105011"},{"LNG":"120.9968738","LAT":"24.57092733"},{"LNG":"120.996871","LAT":"24.57087307"},{"LNG":"120.996901","LAT":"24.57084595"},{"LNG":"120.997084","LAT":"24.570742"},{"LNG":"120.9972307","LAT":"24.57070604"},{"LNG":"120.9972921","LAT":"24.57068177"},{"LNG":"120.9972993","LAT":"24.57062323"},{"LNG":"120.9973021","LAT":"24.57051473"},{"LNG":"120.9973864","LAT":"24.57043478"},{"LNG":"120.9975206","LAT":"24.5704048"},{"LNG":"120.997669","LAT":"24.57037624"},{"LNG":"120.9978375","LAT":"24.5703948"},{"LNG":"120.997896","LAT":"24.5703791"},{"LNG":"120.9979631","LAT":"24.57030771"},{"LNG":"120.9980145","LAT":"24.57029344"},{"LNG":"120.9980588","LAT":"24.57032342"},{"LNG":"120.9981687","LAT":"24.57049902"},{"LNG":"120.9982801","LAT":"24.57056184"},{"LNG":"120.9983229","LAT":"24.57055327"}]',120.997232,24.573461);
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(PATH_NO_SEQ.NEXTVAL), 5, '0'),'path6','3',2,to_timestamp('2020-03-30 18:18:18', 'YYYY-MM-DD HH24:MI:SS'),to_timestamp('2020-03-30 18:56:12', 'YYYY-MM-DD HH24:MI:SS'),464.21,1,'[{"LNG":"121.19312","LAT":"25.09531"},{"LNG":"121.19311","LAT":"25.09530000000001"},{"LNG":"121.1931","LAT":"25.09529"},{"LNG":"121.19308","LAT":"25.09529"},{"LNG":"121.19305","LAT":"25.09527"},{"LNG":"121.19282","LAT":"25.09515"},{"LNG":"121.19278","LAT":"25.09512"},{"LNG":"121.1926","LAT":"25.09503000000001"},{"LNG":"121.19221","LAT":"25.09483"},{"LNG":"121.19208","LAT":"25.09476"},{"LNG":"121.19193","LAT":"25.09468"},{"LNG":"121.1915","LAT":"25.09446000000001"},{"LNG":"121.19145","LAT":"25.09443"},{"LNG":"121.19103","LAT":"25.0942"},{"LNG":"121.19098","LAT":"25.09418"},{"LNG":"121.19075","LAT":"25.09405"},{"LNG":"121.19051","LAT":"25.09393"},{"LNG":"121.1901","LAT":"25.0937"},{"LNG":"121.1897","LAT":"25.09351"},{"LNG":"121.18944","LAT":"25.0934"},{"LNG":"121.18934","LAT":"25.09337"},{"LNG":"121.18918","LAT":"25.09334"},{"LNG":"121.18893","LAT":"25.09332"},{"LNG":"121.18885","LAT":"25.09331"},{"LNG":"121.1888","LAT":"25.0933"},{"LNG":"121.18879","LAT":"25.0933"},{"LNG":"121.18873","LAT":"25.0933"},{"LNG":"121.18868","LAT":"25.09331"},{"LNG":"121.18864","LAT":"25.09332"},{"LNG":"121.18857","LAT":"25.09333000000001"},{"LNG":"121.18852","LAT":"25.09334"},{"LNG":"121.18842","LAT":"25.09336"},{"LNG":"121.18809","LAT":"25.09345"},{"LNG":"121.1865","LAT":"25.09369"},{"LNG":"121.18636","LAT":"25.09371000000001"},{"LNG":"121.18627","LAT":"25.09371000000001"},{"LNG":"121.1862","LAT":"25.09371000000001"},{"LNG":"121.18612","LAT":"25.0937"},{"LNG":"121.18601","LAT":"25.09367"},{"LNG":"121.18546","LAT":"25.09346"},{"LNG":"121.18492","LAT":"25.09324"},{"LNG":"121.1842","LAT":"25.09295"},{"LNG":"121.18288","LAT":"25.09231"},{"LNG":"121.18061","LAT":"25.09126"},{"LNG":"121.17877","LAT":"25.09035"},{"LNG":"121.17743","LAT":"25.08961"},{"LNG":"121.17664","LAT":"25.08916"},{"LNG":"121.17621","LAT":"25.08889"},{"LNG":"121.17606","LAT":"25.08879"},{"LNG":"121.17598","LAT":"25.08873"},{"LNG":"121.17594","LAT":"25.08868"},{"LNG":"121.17589","LAT":"25.08862"},{"LNG":"121.17572","LAT":"25.08833"},{"LNG":"121.1757","LAT":"25.08824"},{"LNG":"121.17568","LAT":"25.08819"},{"LNG":"121.17568","LAT":"25.08816"},{"LNG":"121.17567","LAT":"25.08814"},{"LNG":"121.17567","LAT":"25.08808"},{"LNG":"121.17567","LAT":"25.08804"},{"LNG":"121.17568","LAT":"25.08801"},{"LNG":"121.17569","LAT":"25.08798"},{"LNG":"121.1757","LAT":"25.08796"},{"LNG":"121.17572","LAT":"25.08794"},{"LNG":"121.17573","LAT":"25.08793"},{"LNG":"121.17575","LAT":"25.08791"},{"LNG":"121.17576","LAT":"25.08789"},{"LNG":"121.17578","LAT":"25.08788"},{"LNG":"121.1758","LAT":"25.08788"},{"LNG":"121.17585","LAT":"25.08786"}]',121.19312,25.09531);
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(PATH_NO_SEQ.NEXTVAL), 5, '0'),'path7','3',2,to_timestamp('2020-03-30 18:18:18', 'YYYY-MM-DD HH24:MI:SS'),to_timestamp('2020-03-30 18:56:12', 'YYYY-MM-DD HH24:MI:SS'),464.21,1,'[{"LNG":"121.3250832650106","LAT":"25.00544417656836"},{"LNG":"121.3251761510035","LAT":"25.00547814404438"},{"LNG":"121.3252505370496","LAT":"25.00552203903235"},{"LNG":"121.3252901031221","LAT":"25.00555904059737"},{"LNG":"121.3253597197128","LAT":"25.00560737746126"},{"LNG":"121.3254283399294","LAT":"25.00566405589403"},{"LNG":"121.3255555912521","LAT":"25.00574750899899"},{"LNG":"121.3256281268092","LAT":"25.00581238114048"},{"LNG":"121.3257225114357","LAT":"25.00588180661664"},{"LNG":"121.3257866745548","LAT":"25.0059376325707"},{"LNG":"121.3258675646701","LAT":"25.00599034019119"},{"LNG":"121.3259444902029","LAT":"25.00604238071971"},{"LNG":"121.3260268120369","LAT":"25.00606743418543"},{"LNG":"121.3260694117051","LAT":"25.00607926303647"},{"LNG":"121.3261209422151","LAT":"25.00605682676028"},{"LNG":"121.3261757141727","LAT":"25.00603340347091"},{"LNG":"121.3262115790797","LAT":"25.00600472719029"},{"LNG":"121.3262371791356","LAT":"25.00596882287927"},{"LNG":"121.3262781154226","LAT":"25.00593725590239"},{"LNG":"121.3262921912166","LAT":"25.00588008381728"},{"LNG":"121.3263024228076","LAT":"25.00584217585303"},{"LNG":"121.3263092488359","LAT":"25.00581688535813"},{"LNG":"121.3263119502897","LAT":"25.00579258183586"},{"LNG":"121.3263118475018","LAT":"25.00576483555847"},{"LNG":"121.3263411536273","LAT":"25.00571632987537"},{"LNG":"121.3263824533374","LAT":"25.00566514278046"},{"LNG":"121.3264149143043","LAT":"25.00563195839024"},{"LNG":"121.3264562573238","LAT":"25.00559293724561"},{"LNG":"121.3264961574071","LAT":"25.0055583609852"},{"LNG":"121.3265570312525","LAT":"25.00549977508937"},{"LNG":"121.3265851540066","LAT":"25.00546042151104"},{"LNG":"121.3266124839757","LAT":"25.00539191154801"},{"LNG":"121.326614951586","LAT":"25.00535648700975"},{"LNG":"121.3266078279414","LAT":"25.00529233587199"},{"LNG":"121.3265935840649","LAT":"25.0052445314235"},{"LNG":"121.3265787609358","LAT":"25.00519422028436"},{"LNG":"121.3265678719676","LAT":"25.00514239094386"},{"LNG":"121.3265822406181","LAT":"25.00506636283203"},{"LNG":"121.3266052253696","LAT":"25.0049600104075"},{"LNG":"121.3266149531497","LAT":"25.00487874716703"},{"LNG":"121.3266342810929","LAT":"25.00480055483117"},{"LNG":"121.3266767052993","LAT":"25.00470476724617"}]',121.325083,25.005444);
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(PATH_NO_SEQ.NEXTVAL), 5, '0'),'path8','3',2,to_timestamp('2020-03-30 18:18:18', 'YYYY-MM-DD HH24:MI:SS'),to_timestamp('2020-03-30 18:56:12', 'YYYY-MM-DD HH24:MI:SS'),464.21,1,'[{"LNG":"121.2585925911483","LAT":"24.94122442898075"},{"LNG":"121.2586181119702","LAT":"24.94132420504848"},{"LNG":"121.25856","LAT":"24.941449"},{"LNG":"121.2585","LAT":"24.941535"},{"LNG":"121.258394","LAT":"24.941609"},{"LNG":"121.258235","LAT":"24.94168"},{"LNG":"121.258087","LAT":"24.94171400000001"},{"LNG":"121.257961","LAT":"24.941711"},{"LNG":"121.257834","LAT":"24.941708"},{"LNG":"121.257708","LAT":"24.9417"},{"LNG":"121.257571","LAT":"24.94171"},{"LNG":"121.257458","LAT":"24.941713"},{"LNG":"121.257347","LAT":"24.941668"},{"LNG":"121.257262","LAT":"24.941587"},{"LNG":"121.257184","LAT":"24.941478"},{"LNG":"121.257109","LAT":"24.941329"},{"LNG":"121.257055","LAT":"24.94121000000001"},{"LNG":"121.257005","LAT":"24.941103"},{"LNG":"121.256968","LAT":"24.941007"},{"LNG":"121.25695","LAT":"24.94089"},{"LNG":"121.256908","LAT":"24.94079"},{"LNG":"121.256883","LAT":"24.940679"},{"LNG":"121.256817","LAT":"24.940557"},{"LNG":"121.256781","LAT":"24.940495"},{"LNG":"121.256793","LAT":"24.940371"},{"LNG":"121.256763","LAT":"24.940323"},{"LNG":"121.256698","LAT":"24.940225"},{"LNG":"121.256651","LAT":"24.940155"},{"LNG":"121.256592","LAT":"24.94009300000001"},{"LNG":"121.2563801","LAT":"24.940019"}]',121.258592,24.941224);
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(PATH_NO_SEQ.NEXTVAL), 5, '0'),'path9','3',2,to_timestamp('2020-03-30 18:18:18', 'YYYY-MM-DD HH24:MI:SS'),to_timestamp('2020-03-30 18:56:12', 'YYYY-MM-DD HH24:MI:SS'),464.21,1,'[{"LNG":"121.2795706","LAT":"24.8860415"},{"LNG":"121.2793937","LAT":"24.8859604"},{"LNG":"121.2792589","LAT":"24.88590200000001"},{"LNG":"121.2791353","LAT":"24.8858379"},{"LNG":"121.2790365","LAT":"24.8857866"},{"LNG":"121.2789148","LAT":"24.8857151"},{"LNG":"121.2788053","LAT":"24.8856423"},{"LNG":"121.2786778","LAT":"24.8855695"},{"LNG":"121.2785603","LAT":"24.8854984"},{"LNG":"121.2784868","LAT":"24.885369"},{"LNG":"121.2783887","LAT":"24.8851789"},{"LNG":"121.2783098","LAT":"24.8850757"},{"LNG":"121.2782533","LAT":"24.88498450000001"},{"LNG":"121.2782123","LAT":"24.8849004"},{"LNG":"121.2782083","LAT":"24.8848331"},{"LNG":"121.278233","LAT":"24.88478"},{"LNG":"121.278186","LAT":"24.884784"},{"LNG":"121.278109","LAT":"24.884818"},{"LNG":"121.278066","LAT":"24.884844"},{"LNG":"121.278002","LAT":"24.884844"},{"LNG":"121.277962","LAT":"24.884843"},{"LNG":"121.277892","LAT":"24.884826"},{"LNG":"121.27783","LAT":"24.884788"},{"LNG":"121.277779","LAT":"24.884755"},{"LNG":"121.277756","LAT":"24.884706"},{"LNG":"121.277714","LAT":"24.884634"},{"LNG":"121.277667","LAT":"24.884595"},{"LNG":"121.277623","LAT":"24.88456400000001"},{"LNG":"121.277573","LAT":"24.884525"},{"LNG":"121.277508","LAT":"24.884481"},{"LNG":"121.277479","LAT":"24.884396"}]',121.2795706,24.8860415);


--RECORD  TO_DATE('2020-03-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS') TO_TIMESTAMP('2020-03-14 06:00:00', 'YYYY-MM-DD HH24:MI:SS')
INSERT INTO RECORD (RCD_NO, RCD_UPLOADTIME, RCD_CONTENT, RCD_THUMB_AMOUNT, RCD_METOO_AMOUNT, RCD_STATUS, PATH_NO, MB_ID)
VALUES ('rcd'||LPAD(to_char(RCD_NO_SEQ.nextval), 5, '0'), TO_DATE('2020-03-01 11:11:11', 'YYYY-MM-DD HH24:MI:SS'), 'record1', '100', '100', '1', 'p00001', 'xuan123');
INSERT INTO RECORD (RCD_NO, RCD_UPLOADTIME, RCD_CONTENT, RCD_THUMB_AMOUNT, RCD_METOO_AMOUNT, RCD_STATUS, PATH_NO, MB_ID)
VALUES ('rcd'||LPAD(to_char(RCD_NO_SEQ.nextval), 5, '0'), TO_DATE('2020-03-01 11:22:33', 'YYYY-MM-DD HH24:MI:SS'), 'record2', '300', '30', '1', 'p00002', 'soowii123');
INSERT INTO RECORD (RCD_NO, RCD_UPLOADTIME, RCD_CONTENT, RCD_THUMB_AMOUNT, RCD_METOO_AMOUNT, RCD_STATUS, PATH_NO, MB_ID)
VALUES ('rcd'||LPAD(to_char(RCD_NO_SEQ.nextval), 5, '0'), TO_DATE('2020-03-12 22:22:22', 'YYYY-MM-DD HH24:MI:SS'), 'record3', '50', '50', '1', 'p00003', 'michael123');
INSERT INTO RECORD (RCD_NO, RCD_UPLOADTIME, RCD_CONTENT, RCD_THUMB_AMOUNT, RCD_METOO_AMOUNT, RCD_STATUS, PATH_NO, MB_ID)
VALUES ('rcd'||LPAD(to_char(RCD_NO_SEQ.nextval), 5, '0'), TO_DATE('2020-03-01 18:22:33', 'YYYY-MM-DD HH24:MI:SS'), 'record4', '65', '300', '1', 'p00004', 'yiwen123');
INSERT INTO RECORD (RCD_NO, RCD_UPLOADTIME, RCD_CONTENT, RCD_THUMB_AMOUNT, RCD_METOO_AMOUNT, RCD_STATUS, PATH_NO, MB_ID)
VALUES ('rcd'||LPAD(to_char(RCD_NO_SEQ.nextval), 5, '0'), TO_DATE('2020-03-01 20:22:33', 'YYYY-MM-DD HH24:MI:SS'), 'record5', '99', '99', '1', 'p00005', 'weijhih123');
INSERT INTO RECORD (RCD_NO, RCD_UPLOADTIME, RCD_CONTENT, RCD_THUMB_AMOUNT, RCD_METOO_AMOUNT, RCD_STATUS, PATH_NO, MB_ID)
VALUES ('rcd'||LPAD(to_char(RCD_NO_SEQ.nextval), 5, '0'), TO_DATE('2020-03-02 20:22:33', 'YYYY-MM-DD HH24:MI:SS'), '安卓拉寶貝的臉太500了，我對她只能405', '99', '99', '1', 'p00006', 'anjavababy520');
INSERT INTO RECORD (RCD_NO, RCD_UPLOADTIME, RCD_CONTENT, RCD_THUMB_AMOUNT, RCD_METOO_AMOUNT, RCD_STATUS, PATH_NO, MB_ID)
VALUES ('rcd'||LPAD(to_char(RCD_NO_SEQ.nextval), 5, '0'), TO_DATE('2020-03-02 20:22:44', 'YYYY-MM-DD HH24:MI:SS'), '爪哇寶貝美翻了，很200', '99', '99', '1', 'p00007', 'anjavababy520');
INSERT INTO RECORD (RCD_NO, RCD_UPLOADTIME, RCD_CONTENT, RCD_THUMB_AMOUNT, RCD_METOO_AMOUNT, RCD_STATUS, PATH_NO, MB_ID)
VALUES ('rcd'||LPAD(to_char(RCD_NO_SEQ.nextval), 5, '0'), TO_DATE('2020-03-03 20:22:33', 'YYYY-MM-DD HH24:MI:SS'), '我跑到差點404', '99', '99', '1', 'p00008', 'anjavababy520');
INSERT INTO RECORD (RCD_NO, RCD_UPLOADTIME, RCD_CONTENT, RCD_THUMB_AMOUNT, RCD_METOO_AMOUNT, RCD_STATUS, PATH_NO, MB_ID)
VALUES ('rcd'||LPAD(to_char(RCD_NO_SEQ.nextval), 5, '0'), TO_DATE('2020-03-04 20:22:33', 'YYYY-MM-DD HH24:MI:SS'), '爪哇寶貝才500，爪哇全家都500', '99', '99', '1', 'p00009', 'androidlababy520');
--留言
INSERT INTO "COMMENTT" (CMT_NO, CMT_CONTENT, CMT_TIME, CMT_STATUS, RCD_NO, MB_ID) VALUES ('cmt'||LPAD(to_char(CMT_NO_SEQ.nextval), 5, '0'), 'commemt1', TO_DATE('2020-03-05', 'YYYY-MM-DD'), '1', 'rcd00001', 'soowii123');
INSERT INTO "COMMENTT" (CMT_NO, CMT_CONTENT, CMT_TIME, CMT_STATUS, RCD_NO, MB_ID) VALUES ('cmt'||LPAD(to_char(CMT_NO_SEQ.nextval), 5, '0'), 'comment2', TO_DATE('2020-03-05', 'YYYY-MM-DD'), '1', 'rcd00002', 'xuan123');
INSERT INTO "COMMENTT" (CMT_NO, CMT_CONTENT, CMT_TIME, CMT_STATUS, RCD_NO, MB_ID) VALUES ('cmt'||LPAD(to_char(CMT_NO_SEQ.nextval), 5, '0'), 'comment3', TO_DATE('2020-03-10', 'YYYY-MM-DD'), '1', 'rcd00003', 'michael123');
INSERT INTO "COMMENTT" (CMT_NO, CMT_CONTENT, CMT_TIME, CMT_STATUS, RCD_NO, MB_ID) VALUES ('cmt'||LPAD(to_char(CMT_NO_SEQ.nextval), 5, '0'), 'comment4', TO_DATE('2020-03-15', 'YYYY-MM-DD'), '1', 'rcd00001', 'vain123');
INSERT INTO "COMMENTT" (CMT_NO, CMT_CONTENT, CMT_TIME, CMT_STATUS, RCD_NO, MB_ID) VALUES ('cmt'||LPAD(to_char(CMT_NO_SEQ.nextval), 5, '0'), 'comment5', TO_DATE('2020-03-30', 'YYYY-MM-DD'), '1', 'rcd00001', 'yiwen123');
INSERT INTO "COMMENTT" (CMT_NO, CMT_CONTENT, CMT_TIME, CMT_STATUS, RCD_NO, MB_ID) VALUES ('cmt'||LPAD(to_char(CMT_NO_SEQ.nextval), 5, '0'), '爪哇', TO_DATE('2020-04-05', 'YYYY-MM-DD'), '1', 'rcd00008', 'soowii123');
INSERT INTO "COMMENTT" (CMT_NO, CMT_CONTENT, CMT_TIME, CMT_STATUS, RCD_NO, MB_ID) VALUES ('cmt'||LPAD(to_char(CMT_NO_SEQ.nextval), 5, '0'), '我愛妳', TO_DATE('2020-04-05', 'YYYY-MM-DD'), '1', 'rcd00008', 'xuan123');
INSERT INTO "COMMENTT" (CMT_NO, CMT_CONTENT, CMT_TIME, CMT_STATUS, RCD_NO, MB_ID) VALUES ('cmt'||LPAD(to_char(CMT_NO_SEQ.nextval), 5, '0'), '安卓拉基醜八怪', TO_DATE('2020-04-10', 'YYYY-MM-DD'), '1', 'rcd00008', 'michael123');
INSERT INTO "COMMENTT" (CMT_NO, CMT_CONTENT, CMT_TIME, CMT_STATUS, RCD_NO, MB_ID) VALUES ('cmt'||LPAD(to_char(CMT_NO_SEQ.nextval), 5, '0'), '讚讚', TO_DATE('2020-04-15', 'YYYY-MM-DD'), '1', 'rcd00007', 'vain123');
INSERT INTO "COMMENTT" (CMT_NO, CMT_CONTENT, CMT_TIME, CMT_STATUS, RCD_NO, MB_ID) VALUES ('cmt'||LPAD(to_char(CMT_NO_SEQ.nextval), 5, '0'), '妳好美', TO_DATE('2020-04-30', 'YYYY-MM-DD'), '1', 'rcd00007', 'yiwen123');

--路徑收藏
INSERT INTO "PATH_FOLLOW" (PATH_NO, MB_ID) VALUES ('p00001', 'soowii123');
INSERT INTO "PATH_FOLLOW" (PATH_NO, MB_ID) VALUES ('p00002', 'xuan123');
INSERT INTO "PATH_FOLLOW" (PATH_NO, MB_ID) VALUES ('p00003', 'michael123');
INSERT INTO "PATH_FOLLOW" (PATH_NO, MB_ID) VALUES ('p00004', 'vain123');
INSERT INTO "PATH_FOLLOW" (PATH_NO, MB_ID) VALUES ('p00005', 'xuan123');


--THUMB
INSERT INTO "DA106G5"."THUMB" (RCD_NO, MB_ID) VALUES ('rcd00001', 'soowii123');
INSERT INTO "DA106G5"."THUMB" (RCD_NO, MB_ID) VALUES ('rcd00002', 'xuan123');
INSERT INTO "DA106G5"."THUMB" (RCD_NO, MB_ID) VALUES ('rcd00003', 'vain123');
INSERT INTO "DA106G5"."THUMB" (RCD_NO, MB_ID) VALUES ('rcd00004', 'vain123');
INSERT INTO "DA106G5"."THUMB" (RCD_NO, MB_ID) VALUES ('rcd00005', 'yiwen123');
INSERT INTO "DA106G5"."THUMB" (RCD_NO, MB_ID) VALUES ('rcd00008', 'soowii123');
INSERT INTO "DA106G5"."THUMB" (RCD_NO, MB_ID) VALUES ('rcd00008', 'xuan123');
INSERT INTO "DA106G5"."THUMB" (RCD_NO, MB_ID) VALUES ('rcd00008', 'vain123');
INSERT INTO "DA106G5"."THUMB" (RCD_NO, MB_ID) VALUES ('rcd00007', 'vain123');
INSERT INTO "DA106G5"."THUMB" (RCD_NO, MB_ID) VALUES ('rcd00007', 'yiwen123');


--METOO
INSERT INTO "METOO" (RCD_NO, MB_ID) VALUES ('rcd00001', 'soowii123');
INSERT INTO "METOO" (RCD_NO, MB_ID) VALUES ('rcd00002', 'xuan123');
INSERT INTO "METOO" (RCD_NO, MB_ID) VALUES ('rcd00003', 'vain123');
INSERT INTO "METOO" (RCD_NO, MB_ID) VALUES ('rcd00004', 'yiwen123');
INSERT INTO "METOO" (RCD_NO, MB_ID) VALUES ('rcd00005', 'weijhih123');
INSERT INTO "METOO" (RCD_NO, MB_ID) VALUES ('rcd00008', 'soowii123');
INSERT INTO "METOO" (RCD_NO, MB_ID) VALUES ('rcd00008', 'xuan123');
INSERT INTO "METOO" (RCD_NO, MB_ID) VALUES ('rcd00007', 'vain123');
INSERT INTO "METOO" (RCD_NO, MB_ID) VALUES ('rcd00007', 'yiwen123');
INSERT INTO "METOO" (RCD_NO, MB_ID) VALUES ('rcd00006', 'weijhih123');


--紀錄檢舉
INSERT INTO "RCD_RPT" (RCD_RPT_NO, RPT_REASON, RPT_STATUS, RCD_NO, MB_ID) VALUES ('rcdr'||LPAD(to_char(RCD_RPT_SEQ.nextval), 5, '0'), 'recordreport1', '1', 'rcd00001', 'soowii123');
INSERT INTO "RCD_RPT" (RCD_RPT_NO, RPT_REASON, RPT_STATUS, RCD_NO, MB_ID) VALUES ('rcdr'||LPAD(to_char(RCD_RPT_SEQ.nextval), 5, '0'), 'recordreport2', '1', 'rcd00002', 'xuan123');
INSERT INTO "RCD_RPT" (RCD_RPT_NO, RPT_REASON, RPT_STATUS, RCD_NO, MB_ID) VALUES ('rcdr'||LPAD(to_char(RCD_RPT_SEQ.nextval), 5, '0'), 'recordreport3', '1', 'rcd00003', 'vain123');
INSERT INTO "RCD_RPT" (RCD_RPT_NO, RPT_REASON, RPT_STATUS, RCD_NO, MB_ID) VALUES ('rcdr'||LPAD(to_char(RCD_RPT_SEQ.nextval), 5, '0'), 'recordreport4', '1', 'rcd00004', 'michael123');
INSERT INTO "RCD_RPT" (RCD_RPT_NO, RPT_REASON, RPT_STATUS, RCD_NO, MB_ID) VALUES ('rcdr'||LPAD(to_char(RCD_RPT_SEQ.nextval), 5, '0'), 'recordreport5', '1', 'rcd00005', 'vain123');


--留言檢舉
INSERT INTO "CMT_RPT" (CMT_RPT_NO, RPT_REASON, RPT_STATUS, CMT_NO, MB_ID) VALUES ('cmtr'||LPAD(to_char(CMT_RPT_SEQ.nextval), 5, '0'), 'commentreport1', '1', 'cmt00001', 'soowii123');
INSERT INTO "CMT_RPT" (CMT_RPT_NO, RPT_REASON, RPT_STATUS, CMT_NO, MB_ID) VALUES ('cmtr'||LPAD(to_char(CMT_RPT_SEQ.nextval), 5, '0'), 'commentreport2', '1', 'cmt00002', 'xuan123');
INSERT INTO "CMT_RPT" (CMT_RPT_NO, RPT_REASON, RPT_STATUS, CMT_NO, MB_ID) VALUES ('cmtr'||LPAD(to_char(CMT_RPT_SEQ.nextval), 5, '0'), 'commentreport3', '1', 'cmt00003', 'michael123');
INSERT INTO "CMT_RPT" (CMT_RPT_NO, RPT_REASON, RPT_STATUS, CMT_NO, MB_ID) VALUES ('cmtr'||LPAD(to_char(CMT_RPT_SEQ.nextval), 5, '0'), 'commentreport4', '1', 'cmt00004', 'vain123');
INSERT INTO "CMT_RPT" (CMT_RPT_NO, RPT_REASON, RPT_STATUS, CMT_NO, MB_ID) VALUES ('cmtr'||LPAD(to_char(CMT_RPT_SEQ.nextval), 5, '0'), 'commentreport5', '1', 'cmt00005', 'yiwen123');


--commit;--oracle 預設沒有autocommit,會找不到資料



--------------------------------------------------------
-- 6.以文 直播Drop
--------------------------------------------------------
DROP TABLE "DA106G5"."MB_FOLLOW";
DROP TABLE "DA106G5"."LIVE_RPT";
DROP TABLE "DA106G5"."LIVE";

DROP SEQUENCE "DA106G5"."LIVE_NO_SEQ";
DROP SEQUENCE "DA106G5"."LIVE_RPT_NO_SEQ";

CREATE TABLE live (
 live_no VARCHAR2(30) NOT NULL,
 live_content VARCHAR2(100),
 live_status NUMBER(1) NOT NULL,
 live_startteaser TIMESTAMP,
 live_start TIMESTAMP,
 live_store BLOB,
 live_pic BLOB,
 mb_id VARCHAR2(30) NOT NULL
);

ALTER TABLE live ADD CONSTRAINT PK_live PRIMARY KEY (live_no);


CREATE TABLE live_rpt (
 live_rpt_no VARCHAR2(30) NOT NULL,
 rpt_status NUMBER(1) DEFAULT'1' NOT NULL,
 rpt_reason CLOB,
 live_no VARCHAR2(30) NOT NULL,
 mb_id VARCHAR2(30) NOT NULL
);

ALTER TABLE live_rpt ADD CONSTRAINT PK_live_rpt PRIMARY KEY (live_rpt_no);


CREATE TABLE mb_follow (
 mb_id VARCHAR2(30) NOT NULL,
 mb_id_followed VARCHAR2(30) NOT NULL
);

ALTER TABLE mb_follow ADD CONSTRAINT PK_mb_follow PRIMARY KEY (mb_id,mb_id_followed);


ALTER TABLE live ADD CONSTRAINT FK_live_0 FOREIGN KEY (mb_id) REFERENCES member (mb_id);


ALTER TABLE live_rpt ADD CONSTRAINT FK_live_rpt_0 FOREIGN KEY (live_no) REFERENCES live (live_no);
ALTER TABLE live_rpt ADD CONSTRAINT FK_live_rpt_1 FOREIGN KEY (mb_id) REFERENCES member (mb_id);


ALTER TABLE mb_follow ADD CONSTRAINT FK_mb_follow_0 FOREIGN KEY (mb_id) REFERENCES member (mb_id);
ALTER TABLE mb_follow ADD CONSTRAINT FK_mb_follow_1 FOREIGN KEY (mb_id_followed) REFERENCES member (mb_id);

CREATE SEQUENCE live_no_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

CREATE SEQUENCE live_rpt_no_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

--live
Insert into LIVE( LIVE_NO, LIVE_CONTENT, LIVE_STATUS, LIVE_STARTTEASER , MB_ID, LIVE_START) 
values('LIV'||LPAD(to_char(LIVE_NO_SEQ.NEXTVAL), 5, '0'),'soowii123_live',1,CURRENT_TIMESTAMP,'soowii123',TO_TIMESTAMP('2020-06-06 06:00:00', 'YYYY-MM-DD HH24:MI:SS')+1/24);

Insert into LIVE( LIVE_NO, LIVE_CONTENT, LIVE_STATUS, LIVE_STARTTEASER , MB_ID, LIVE_START) 
values('LIV'||LPAD(to_char(LIVE_NO_SEQ.NEXTVAL), 5, '0'),'xuan123_live',1,CURRENT_TIMESTAMP,'xuan123',TO_TIMESTAMP('2020-06-06 06:00:00', 'YYYY-MM-DD HH24:MI:SS')+1/24);

Insert into LIVE( LIVE_NO, LIVE_CONTENT, LIVE_STATUS, LIVE_STARTTEASER , MB_ID, LIVE_START) 
values('LIV'||LPAD(to_char(LIVE_NO_SEQ.NEXTVAL), 5, '0'),'michael123_live',1,CURRENT_TIMESTAMP,'michael123',TO_TIMESTAMP('2020-06-06 06:00:00', 'YYYY-MM-DD HH24:MI:SS')+1/24);

Insert into LIVE( LIVE_NO, LIVE_CONTENT, LIVE_STATUS, LIVE_STARTTEASER , MB_ID, LIVE_START) 
values('LIV'||LPAD(to_char(LIVE_NO_SEQ.NEXTVAL), 5, '0'),'vain123_live',2,CURRENT_TIMESTAMP,'vain123',TO_TIMESTAMP('2020-06-06 06:00:00', 'YYYY-MM-DD HH24:MI:SS'));

Insert into LIVE( LIVE_NO, LIVE_CONTENT, LIVE_STATUS, LIVE_STARTTEASER , MB_ID, LIVE_START) 
values('LIV'||LPAD(to_char(LIVE_NO_SEQ.NEXTVAL), 5, '0'),'yiwen123_live',3,TO_TIMESTAMP('2020-03-01 06:00:00', 'YYYY-MM-DD HH24:MI:SS'),'yiwen123',TO_TIMESTAMP('2020-03-01 06:15:00', 'YYYY-MM-DD HH24:MI:SS'));

Insert into LIVE( LIVE_NO, LIVE_CONTENT, LIVE_STATUS, LIVE_STARTTEASER , MB_ID, LIVE_START) 
values('LIV'||LPAD(to_char(LIVE_NO_SEQ.NEXTVAL), 5, '0'),'weijhih123_live',4,(CURRENT_TIMESTAMP)-1/24,'weijhih123',CURRENT_TIMESTAMP);


--live_rpt(LIVE_NO need to be the same as )
Insert into LIVE_RPT( LIVE_RPT_NO, RPT_STATUS, RPT_REASON, LIVE_NO , MB_ID) 
values('LIR'||LPAD(to_char(LIVE_RPT_NO_SEQ.NEXTVAL), 5, '0'),'2','DIRTY','LIV00006','yiwen123');

Insert into LIVE_RPT( LIVE_RPT_NO, RPT_STATUS, RPT_REASON, LIVE_NO , MB_ID) 
values('LIR'||LPAD(to_char(LIVE_RPT_NO_SEQ.NEXTVAL), 5, '0'),'2','DIRTY','LIV00006','soowii123');

Insert into LIVE_RPT( LIVE_RPT_NO, RPT_STATUS, RPT_REASON, LIVE_NO , MB_ID) 
values('LIR'||LPAD(to_char(LIVE_RPT_NO_SEQ.NEXTVAL), 5, '0'),'2','DIRTY','LIV00006','michael123');

Insert into LIVE_RPT( LIVE_RPT_NO, RPT_STATUS, RPT_REASON, LIVE_NO , MB_ID) 
values('LIR'||LPAD(to_char(LIVE_RPT_NO_SEQ.NEXTVAL), 5, '0'),'2','DIRTY','LIV00006','vain123');

Insert into LIVE_RPT( LIVE_RPT_NO, RPT_STATUS, RPT_REASON, LIVE_NO , MB_ID) 
values('LIR'||LPAD(to_char(LIVE_RPT_NO_SEQ.NEXTVAL), 5, '0'),'2','DIRTY','LIV00006','xuan123');

--mb_follow
Insert into MB_FOLLOW(MB_ID, MB_ID_FOLLOWED) 
values('yiwen123','soowii123');

Insert into MB_FOLLOW(MB_ID, MB_ID_FOLLOWED) 
values('yiwen123','xuan123');

Insert into MB_FOLLOW(MB_ID, MB_ID_FOLLOWED) 
values('yiwen123','michael123');

Insert into MB_FOLLOW(MB_ID, MB_ID_FOLLOWED) 
values('yiwen123','vain123');

Insert into MB_FOLLOW(MB_ID, MB_ID_FOLLOWED) 
values('yiwen123','weijhih123');


--commit--
commit;