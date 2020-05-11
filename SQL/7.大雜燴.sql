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
Insert into MEMBER( MB_ID, MB_PWD, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL, MB_STATUS) 
values('soowii123','123456','soowii','1',TO_DATE('1990-02-15','YYYY-MM-DD'),'soowii123@yahoo.com',2);
Insert into MEMBER( MB_ID, MB_PWD, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL, MB_STATUS) 
values('xuan123','1234567','xuan','1',TO_DATE('1992-05-01','YYYY-MM-DD'),'xuan123@hotmail.com',2);
Insert into MEMBER( MB_ID, MB_PWD, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL, MB_STATUS) 
values('michael123','12345678','Michael','1',TO_DATE('1989-06-25','YYYY-MM-DD'),'Michael123@yahoo.com',2);
Insert into MEMBER( MB_ID, MB_PWD, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL, MB_STATUS) 
values('vain123','123456789','vain','1',TO_DATE('1987-07-01','YYYY-MM-DD'),'vain123@hotmail.com',2);
Insert into MEMBER( MB_ID, MB_PWD, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL, MB_STATUS) 
values('yiwen123','1234566','yiwen123','1',TO_DATE('1985-09-14','YYYY-MM-DD'),'yiwen123@yahoo.com',2);
Insert into MEMBER( MB_ID, MB_PWD, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL, MB_STATUS) 
values('weijhih123','1234577','weijhih123','1',TO_DATE('1989-10-19','YYYY-MM-DD'),'weijhih123@yahoo.com',2);
Insert into MEMBER( MB_ID, MB_PWD, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL, MB_STATUS) 
values('androidlababy520','androidlababy520','安卓拉寶貝','2',TO_DATE('1989-10-19','YYYY-MM-DD'),'androidlababy520@yahoo.com',2);
Insert into MEMBER( MB_ID, MB_PWD, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL, MB_STATUS) 
values('anjavababy520','anjavababy520','一個爪哇寶貝','2',TO_DATE('1989-10-19','YYYY-MM-DD'),'anjavababy520@yahoo.com',2);

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
Insert into NOTIFY( NTF_NO, MB_ID, NTF_CONTENT) 
values('NTF'||LPAD(to_char(ntf_no_seq.NEXTVAL), 5, '0'),'anjavababy520','您的商品正在準備中');
Insert into NOTIFY( NTF_NO, MB_ID, NTF_CONTENT) 
values('NTF'||LPAD(to_char(ntf_no_seq.NEXTVAL), 5, '0'),'anjavababy520','您的商品已發貨');
Insert into NOTIFY( NTF_NO, MB_ID, NTF_CONTENT) 
values('NTF'||LPAD(to_char(ntf_no_seq.NEXTVAL), 5, '0'),'anjavababy520','您的商品已抵達');

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
values(LPAD(to_char(ABILITY_NO_SEQ.NEXTVAL), 2, '0'),'商城管理');
Insert into ABILITY( ABILITY_NO, ABILITY_NAME) 
values(LPAD(to_char(ABILITY_NO_SEQ.NEXTVAL), 2, '0'),'地標管理');
Insert into ABILITY( ABILITY_NO, ABILITY_NAME) 
values(LPAD(to_char(ABILITY_NO_SEQ.NEXTVAL), 2, '0'),'檢舉管理');
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
Insert into weather (wth_status) values ('晴時多雲');
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
values('grp'||LPAD(to_char(grp_no_seq.NEXTVAL), 5, '0'),'yiwen123','loc00001',TO_TIMESTAMP('2020-05-07 08:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-07 18:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-08 08:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-08 20:30','YYYY-MM-DD HH24:MI'),'大家一起快樂打網球','大家好，我是阿文，有人要一起打快樂網球嗎，最好是能用生命打球的朋友',6,1,1,1,0);

Insert into GROUPER(GRP_NO,MB_ID,LOC_NO,GRP_APPLYSTART,GRP_APPLYEND,GRP_START,GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW)
values('grp'||LPAD(to_char(grp_no_seq.NEXTVAL), 5, '0'),'vain123','loc00002',TO_TIMESTAMP('2020-05-08 10:00','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-10 22:00','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-10 13:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-10 17:30','YYYY-MM-DD HH24:MI'),'天氣好熱、玩水開心','天氣這麼熱就是要出來游泳消暑吧 游完再一起去鉅大吃刨冰 限女性',20,1,1,1,0);

Insert into GROUPER(GRP_NO,MB_ID,LOC_NO,GRP_APPLYSTART,GRP_APPLYEND,GRP_START,GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW)
values('grp'||LPAD(to_char(grp_no_seq.NEXTVAL), 5, '0'),'xuan123','loc00003',TO_TIMESTAMP('2020-05-14 10:00','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-14 22:00','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-17 08:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-17 11:30','YYYY-MM-DD HH24:MI'),'元大鷹出來打球','吾乃中大東方翔，絕招是三分線後仰拉桿轉身跳投，希望有人來電電我',11,1,1,1,0);

Insert into GROUPER(GRP_NO,MB_ID,LOC_NO,GRP_APPLYSTART,GRP_APPLYEND,GRP_START,GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW)
values('grp'||LPAD(to_char(grp_no_seq.NEXTVAL), 5, '0'),'soowii123','loc00004',TO_TIMESTAMP('2020-05-13 10:00','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-13 22:00','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-15 08:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-15 11:30','YYYY-MM-DD HH24:MI'),'馬來西亞的鄉聚羽球','我是從馬來西亞來讀書的學生，希望有喜歡一起打羽球的人~',50,1,1,1,0);

Insert into GROUPER(GRP_NO,MB_ID,LOC_NO,GRP_APPLYSTART,GRP_APPLYEND,GRP_START,GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW)
values('grp'||LPAD(to_char(grp_no_seq.NEXTVAL), 5, '0'),'weijhih123','loc00005',TO_TIMESTAMP('2020-05-13 10:00','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-19 22:00','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-20 18:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-21 00:30','YYYY-MM-DD HH24:MI'),'羅主任上課囉','本人是專業健身教練，利用下班時間免費教導大家的運動姿勢，一起多人運動~',6,1,6,1,0);

Insert into GROUPER(GRP_NO,MB_ID,LOC_NO,GRP_APPLYSTART,GRP_APPLYEND,GRP_START,GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW)
values('grp'||LPAD(to_char(grp_no_seq.NEXTVAL), 5, '0'),'michael123','loc00007',TO_TIMESTAMP('2020-05-07 10:00','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-09 22:00','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-10 08:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-10 09:30','YYYY-MM-DD HH24:MI'),'約晨跑','有志一同的朋友歡迎加入中大晨跑團ㄛ',6,1,1,1,0);

Insert into GROUPER(GRP_NO,MB_ID,LOC_NO,GRP_APPLYSTART,GRP_APPLYEND,GRP_START,GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW)
values('grp'||LPAD(to_char(grp_no_seq.NEXTVAL), 5, '0'),'soowii123','loc00001',TO_TIMESTAMP('2020-05-01 08:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-15 18:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-01 08:30','YYYY-MM-DD HH24:MI'),TO_TIMESTAMP('2020-05-15 20:30','YYYY-MM-DD HH24:MI'),'Fuck ball go','gogogo',30,5,15,1,30);

--揪團明細

Insert into grp_detail (mb_id,grp_no,grp_register)
values('yiwen123','grp00001',1);
Insert into grp_detail (mb_id,grp_no,grp_register)
values('vain123','grp00002',1);
Insert into grp_detail (mb_id,grp_no,grp_register)
values('xuan123','grp00003',1);
Insert into grp_detail (mb_id,grp_no,grp_register)
values('soowii123','grp00004',1);
Insert into grp_detail (mb_id,grp_no,grp_register)
values('weijhih123','grp00005',1);
Insert into grp_detail (mb_id,grp_no,grp_register)
values('michael123','grp00006',1);

Insert into grp_detail (mb_id,grp_no,grp_register)
values('yiwen123','grp00005',1);
Insert into grp_detail (mb_id,grp_no,grp_register)
values('soowii123','grp00005',1);
Insert into grp_detail (mb_id,grp_no,grp_register)
values('xuan123','grp00005',1);
Insert into grp_detail (mb_id,grp_no,grp_register)
values('michael123','grp00005',1);
Insert into grp_detail (mb_id,grp_no,grp_register)
values('vain123','grp00005',1);

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
 cp_detail VARCHAR2(100),
 cp_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
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
 pd_pic2 BLOB,
 pd_pic3 BLOB,
 pd_pic4 BLOB,
 pd_detail CLOB,
 pd_status NUMBER(1) DEFAULT '1' NOT NULL,
 pd_typeno VARCHAR2(30)
);

ALTER TABLE product ADD CONSTRAINT PK_product PRIMARY KEY (pd_no);


-----持有優惠卷表格------


CREATE TABLE cp_get (
 mb_id VARCHAR2(30) NOT NULL,
 cp_no VARCHAR2(30) NOT NULL,
 cp_status NUMBER(1) DEFAULT '1' NOT NULL,
 cp_getTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 cp_getDetail VARCHAR2(100)
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

Insert into COUPON (CP_NO,CP_NAME,CP_PRICE,CP_DETAIL,CP_TIME) values ('CPN'||LPAD(to_char(COUPON_SEQ.NEXTVAL), 5, '0'),'優惠卷50元','50','會員等級1獎勵','2020-04-26 21:25');
Insert into COUPON (CP_NO,CP_NAME,CP_PRICE,CP_DETAIL,CP_TIME) values ('CPN'||LPAD(to_char(COUPON_SEQ.NEXTVAL), 5, '0'),'優惠卷100元','100','會員等級2獎勵','2020-04-26 21:25');
Insert into COUPON (CP_NO,CP_NAME,CP_PRICE,CP_DETAIL,CP_TIME) values ('CPN'||LPAD(to_char(COUPON_SEQ.NEXTVAL), 5, '0'),'優惠卷150元','150','會員等級3獎勵','2020-04-26 21:25');
Insert into COUPON (CP_NO,CP_NAME,CP_PRICE,CP_DETAIL,CP_TIME) values ('CPN'||LPAD(to_char(COUPON_SEQ.NEXTVAL), 5, '0'),'優惠卷200元','200','會員等級4獎勵','2020-04-26 21:25');
Insert into COUPON (CP_NO,CP_NAME,CP_PRICE,CP_DETAIL,CP_TIME) values ('CPN'||LPAD(to_char(COUPON_SEQ.NEXTVAL), 5, '0'),'優惠卷250元','250','會員等級5獎勵','2020-04-26 21:25');
Insert into COUPON (CP_NO,CP_NAME,CP_PRICE,CP_DETAIL,CP_TIME) values ('CPN'||LPAD(to_char(COUPON_SEQ.NEXTVAL), 5, '0'),'優惠卷300元','300','會員等級6獎勵','2020-04-26 21:25');


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

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子速乾排汗路跑背心_螢光綠','650','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子速乾排汗路跑背心_藍綠','650','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子簡單主張銀離子運動短袖上衣_黑','850','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子素面拼接運動短袖上衣_黑色','850','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子素面拼接運動短袖上衣_螢光黃','850','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子迷彩運動短袖上衣_黑','850','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子簡單主張銀離子運動短袖上衣_青石綠','850','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子ZP定番運動短袖上衣_深藍','850','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子軍事主義迷彩拼接鋪棉外套_迷彩藍','1250','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子斜紋印花防風外套_黑色','1250','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子經典側邊字母休閒外套_麻灰','1250','PTN00001','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子五分緊身慢跑褲-黑橘','750','PTN00002','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子長條反光運動短褲_黑螢光綠','750','PTN00002','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子炫彩LOGO排汗運動短褲_深灰','750','PTN00002','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子素面防風運動褲_黑','980','PTN00002','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子素面專業運動短褲_深藍','750','PTN00002','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子撞色剪裁排汗運動短褲_鐵灰_螢光綠','750','PTN00002','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子LOGO素面防風運動褲內刷毛款_黑','980','PTN00002','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'美津濃男子路跑鞋_黑灰','2150','PTN00003','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'美津濃男子路跑鞋_藍紅','2150','PTN00003','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子雲豹系列競速路跑鞋_黑黃色','2150','PTN00003','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子雲豹系列競速路跑鞋_黑黃色','2150','PTN00003','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子雲豹系列競速路跑鞋_螢綠黑','2150','PTN00003','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子雲豹系列競速路跑鞋_藍綠','2150','PTN00003','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男子雲豹系列競速路跑鞋II代_閃電橘','2150','PTN00003','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。','2');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子科技世代運動休閒短袖上衣_水藍','580','PTN00004','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子科技感炫彩運動短袖上衣_湖水綠','580','PTN00004','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子素面拼接運動短袖上衣_玫瑰桃','580','PTN00004','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子素面拼接運動短袖上衣_寶貝橘','580','PTN00004','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子速乾排汗路跑背心_桃紅','580','PTN00004','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子多功能九分薄運動褲_黑','690','PTN00005','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子動能緊身運動訓練褲_黑','690','PTN00005','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子復古滾邊休閒長褲_粉膚','770','PTN00005','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子復古滾邊休閒長褲_寶藍','770','PTN00005','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子經典滾邊休閒長褲_湖水綠','770','PTN00005','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'美津濃女子路跑鞋_粉','1750','PTN00006','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子EASYWALK緩震健走鞋_亮橘黑','1750','PTN00006','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子EASYWALK緩震健走鞋_珊瑚紅','1750','PTN00006','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子KIRIN系列減震耐磨運動跑鞋_艷桃紅','1750','PTN00006','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'女子MissQ多功能休閒鞋_馬卡龍粉','1750','PTN00006','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。','2');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'兒童經典百搭休閒外套_珊瑚粉','1100','PTN00007','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'兒童經典百搭休閒外套_經典黑','1100','PTN00007','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'兒童撞色休閒刷毛外套_裸磚紅','1100','PTN00007','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'兒童百搭休閒長褲_珊瑚粉','1100','PTN00008','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'兒童百搭休閒長褲_藍綠','1100','PTN00008','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'兒童雙拉鍊休閒長褲_時尚藍','1100','PTN00008','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'兒童雙拉鍊休閒長褲_經典黑','1100','PTN00008','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'兒童百搭休閒長褲_經典黑','1100','PTN00008','此系列織物具備濕氣調節、吸臭、抗UV、速乾、涼感等材質技術。一般應用於運動服、戶外休閒服、家居服、一般服飾、內睡衣與寢具及配件等。國際專利的SC環保科技咖啡紗，具環保且永久的高機能性。SC環保科技咖啡紗同時有紫外線防護和絕佳的異味控制效果。','2');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'動感潮童舒適減震運動鞋_橘紅','1750','PTN00009','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'動感潮童舒適減震運動鞋_紫','1750','PTN00009','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'動感潮童舒適減震運動鞋_黑','1750','PTN00009','極輕量的REACT中底，加上輕鬆跑步就能感受到的回饋感，對所有跑者來說都是新的體驗，加上耐用性很高，應付3～4場馬拉松比賽都可以說是輕鬆寫意，包覆性和腳踝的固定性也都是這款鞋子的最大特色，不管是新手到進階都是可以使用的全方位鞋款。','2');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'美津濃-半截式頭套_黑','1680','PTN00010','每位跑者的實力不一、狀態各異，Garmin 最懂你心，發展全系列運動穿戴。從使用方便的 GPS 訓練到高階的鐵人三項強力培訓，Garmin 有如陪訓員般貼心守護。您只需設定目標，便可勇往直前。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男女運動壓力襪_藍黃','1680','PTN00010','每位跑者的實力不一、狀態各異，Garmin 最懂你心，發展全系列運動穿戴。從使用方便的 GPS 訓練到高階的鐵人三項強力培訓，Garmin 有如陪訓員般貼心守護。您只需設定目標，便可勇往直前。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'男女運動壓力襪_白黑','1680','PTN00010','每位跑者的實力不一、狀態各異，Garmin 最懂你心，發展全系列運動穿戴。從使用方便的 GPS 訓練到高階的鐵人三項強力培訓，Garmin 有如陪訓員般貼心守護。您只需設定目標，便可勇往直前。','2');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'率性經典休閒後背包_時尚藍','3310','PTN00011','每位跑者的實力不一、狀態各異，Garmin 最懂你心，發展全系列運動穿戴。從使用方便的 GPS 訓練到高階的鐵人三項強力培訓，Garmin 有如陪訓員般貼心守護。您只需設定目標，便可勇往直前。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'單色跑步運動裝備包_俄羅斯綠','3310','PTN00011','每位跑者的實力不一、狀態各異，Garmin 最懂你心，發展全系列運動穿戴。從使用方便的 GPS 訓練到高階的鐵人三項強力培訓，Garmin 有如陪訓員般貼心守護。您只需設定目標，便可勇往直前。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'經典跑步運動裝備包_玫瑰桃','3310','PTN00011','每位跑者的實力不一、狀態各異，Garmin 最懂你心，發展全系列運動穿戴。從使用方便的 GPS 訓練到高階的鐵人三項強力培訓，Garmin 有如陪訓員般貼心守護。您只需設定目標，便可勇往直前。','2');

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'Obeat1光學心率臂帶','3310','PTN00012','每位跑者的實力不一、狀態各異，Garmin 最懂你心，發展全系列運動穿戴。從使用方便的 GPS 訓練到高階的鐵人三項強力培訓，Garmin 有如陪訓員般貼心守護。您只需設定目標，便可勇往直前。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'GPS全方位運動心率錶','3310','PTN00012','每位跑者的實力不一、狀態各異，Garmin 最懂你心，發展全系列運動穿戴。從使用方便的 GPS 訓練到高階的鐵人三項強力培訓，Garmin 有如陪訓員般貼心守護。您只需設定目標，便可勇往直前。','2');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO, PD_DETAIL, pd_status)  
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'GPS三用光學心率錶','3310','PTN00012','每位跑者的實力不一、狀態各異，Garmin 最懂你心，發展全系列運動穿戴。從使用方便的 GPS 訓練到高階的鐵人三項強力培訓，Garmin 有如陪訓員般貼心守護。您只需設定目標，便可勇往直前。','2');


-----------------------CP_GET持有優惠卷-----------------------

Insert into CP_GET(MB_ID, CP_NO ,CP_GETTIME ,CP_GETDETAIL) 
values('soowii123','CPN00001','2020-04-26 21:25','得到優惠券的備註');
Insert into CP_GET(MB_ID, CP_NO ,CP_GETTIME,CP_GETDETAIL) 
values('xuan123','CPN00003','2020-04-26 21:25','得到優惠券的備註');
Insert into CP_GET(MB_ID, CP_NO ,CP_GETTIME,CP_GETDETAIL) 
values('michael123','CPN00001','2020-04-26 21:25','得到優惠券的備註');
Insert into CP_GET(MB_ID, CP_NO ,CP_GETTIME,CP_GETDETAIL) 
values('vain123','CPN00006','2020-04-26 21:25','得到優惠券的備註');
Insert into CP_GET(MB_ID, CP_NO ,CP_GETTIME,CP_GETDETAIL) 
values('yiwen123','CPN00002','2020-04-26 21:25','得到優惠券的備註');
Insert into CP_GET(MB_ID, CP_NO ,CP_GETTIME,CP_GETDETAIL) 
values('weijhih123','CPN00001','2020-04-26 21:25','得到優惠券的備註');

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
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(path_NO_SEQ.NEXTVAL), 5, '0'),'path1','1',2,to_timestamp('2020-03-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),to_timestamp('2020-03-01 00:20:00', 'YYYY-MM-DD HH24:MI:SS'),300.22,1,'[{"longitude":"121.22087","latitude":"24.96717"},{"longitude":"121.22083","latitude":"24.96898"},{"longitude":"121.21822","latitude":"24.97603"},{"longitude":"121.21805","latitude":"24.9771"},{"longitude":"121.20399","latitude":"24.99614"},{"longitude":"121.20343","latitude":"24.99705"},{"longitude":"121.20982","latitude":"25.00811"},{"longitude":"121.21244","latitude":"25.01191"},{"longitude":"121.21559","latitude":"25.01511"},{"longitude":"121.22284","latitude":"25.02241"},{"longitude":"121.21923","latitude":"25.02557"},{"longitude":"121.21676","latitude":"25.03109"},{"longitude":"121.21553","latitude":"25.04085"},{"longitude":"121.21539","latitude":"25.04312"},{"longitude":"121.21241","latitude":"25.05108"}]',121.22087,24.96717);
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(PATH_NO_SEQ.NEXTVAL), 5, '0'),'path2','2',2,to_timestamp('2020-03-05 01:12:12', 'YYYY-MM-DD HH24:MI:SS'),to_timestamp('2020-03-05 01:28:12', 'YYYY-MM-DD HH24:MI:SS'),3001.65,1,'[{"longitude":"121.21979","latitude":"25.06855"},{"longitude":"121.22083","latitude":"25.06935"},{"longitude":"121.25503","latitude":"25.09234"},{"longitude":"121.26642","latitude":"25.08648"},{"longitude":"121.27556","latitude":"25.0824"},{"longitude":"121.27864","latitude":"25.08147"},{"longitude":"121.28601","latitude":"25.08083"},{"longitude":"121.28948","latitude":"25.08075"},{"longitude":"121.2914","latitude":"25.0809"},{"longitude":"121.30134","latitude":"25.08055"},{"longitude":"121.30218","latitude":"25.08041"},{"longitude":"121.30685","latitude":"25.07917"}]',121.21979,25.06855);
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(PATH_NO_SEQ.NEXTVAL), 5, '0'),'path3','3',2,to_timestamp('2020-03-10 11:11:11', 'YYYY-MM-DD HH24:MI:SS'),to_timestamp('2020-03-10 11:17:58', 'YYYY-MM-DD HH24:MI:SS'),501,1,'[{"longitude":"121.32645","latitude":"25.06812"},{"longitude":"121.32704","latitude":"25.06744"},{"longitude":"121.3286","latitude":"25.06623"},{"longitude":"121.33049","latitude":"25.06563"},{"longitude":"121.33372","latitude":"25.06513"},{"longitude":"121.33737","latitude":"25.0663"},{"longitude":"121.34794","latitude":"25.06835"},{"longitude":"121.35194","latitude":"25.06665"},{"longitude":"121.35306","latitude":"25.06636"},{"longitude":"121.36554","latitude":"25.06534"},{"longitude":"121.36753","latitude":"25.06425"},{"longitude":"121.37078","latitude":"25.0604"},{"longitude":"121.38542","latitude":"25.0484"},{"longitude":"121.38545","latitude":"25.04125"}]',121.32645,25.06812);
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(PATH_NO_SEQ.NEXTVAL), 5, '0'),'path4','1',1,to_timestamp('2020-03-15 22:22:22', 'YYYY-MM-DD HH24:MI:SS'),to_timestamp('2020-03-15 23:22:25', 'YYYY-MM-DD HH24:MI:SS'),1500.88,1,'[{"longitude":"121.39373","latitude":"25.03438"},{"longitude":"121.39547","latitude":"25.03322"},{"longitude":"121.39928","latitude":"25.03105"},{"longitude":"121.40221","latitude":"25.03022"},{"longitude":"121.40542","latitude":"25.02887"},{"longitude":"121.40692","latitude":"25.02881"},{"longitude":"121.41201","latitude":"25.02929"},{"longitude":"121.41538","latitude":"25.03142"},{"longitude":"121.41639","latitude":"25.03178"},{"longitude":"121.42152","latitude":"25.03239"},{"longitude":"121.42261","latitude":"25.03319"},{"longitude":"121.42964","latitude":"25.04043"},{"longitude":"121.44344","latitude":"25.058"},{"longitude":"121.44587","latitude":"25.0592"},{"longitude":"121.44825","latitude":"25.06039"}]',121.39373,25.03438);
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(PATH_NO_SEQ.NEXTVAL), 5, '0'),'path5','3',2,to_timestamp('2020-03-30 18:18:18', 'YYYY-MM-DD HH24:MI:SS'),to_timestamp('2020-03-30 18:56:12', 'YYYY-MM-DD HH24:MI:SS'),464.21,1,'[{"longitude":"121.46654","latitude":"25.06701"},{"longitude":"121.4669","latitude":"25.06738"},{"longitude":"121.46919","latitude":"25.06825"},{"longitude":"121.47181","latitude":"25.06744"},{"longitude":"121.47536","latitude":"25.06568"},{"longitude":"121.47634","latitude":"25.06499"},{"longitude":"121.48083","latitude":"25.05846"},{"longitude":"121.48401","latitude":"25.05237"},{"longitude":"121.48548","latitude":"25.04958"},{"longitude":"121.48744","latitude":"25.04748"},{"longitude":"121.48793","latitude":"25.04751"},{"longitude":"121.50569","latitude":"25.04912"},{"longitude":"121.51421","latitude":"25.04868"}]',121.46654,25.06701);
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(PATH_NO_SEQ.NEXTVAL), 5, '0'),'path6','3',1,to_timestamp('2020-03-30 18:18:18', 'YYYY-MM-DD HH24:MI:SS'),to_timestamp('2020-03-30 18:56:12', 'YYYY-MM-DD HH24:MI:SS'),464.21,1,'[{"longitude":"121.26605","latitude":"24.86001"},{"longitude":"121.2664","latitude":"24.86041"},{"longitude":"121.26692","latitude":"24.86229"},{"longitude":"121.26753","latitude":"24.86303"},{"longitude":"121.26692","latitude":"24.86496"},{"longitude":"121.26657","latitude":"24.86537"},{"longitude":"121.2666","latitude":"24.8688"},{"longitude":"121.27356","latitude":"24.87394"},{"longitude":"121.27386","latitude":"24.87453"},{"longitude":"121.27572","latitude":"24.87642"},{"longitude":"121.27646","latitude":"24.87584"},{"longitude":"121.27806","latitude":"24.87743"},{"longitude":"121.27978","latitude":"24.8802"},{"longitude":"121.28009","latitude":"24.8813"},{"longitude":"121.2806","latitude":"24.88526"},{"longitude":"121.28405","latitude":"24.88468"}]',121.28405,24.88468);
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(PATH_NO_SEQ.NEXTVAL), 5, '0'),'path7','3',1,to_timestamp('2020-03-30 18:18:18', 'YYYY-MM-DD HH24:MI:SS'),to_timestamp('2020-03-30 18:56:12', 'YYYY-MM-DD HH24:MI:SS'),464.21,1,'[{"longitude":"121.20212","latitude":"24.81786"},{"longitude":"121.20356","latitude":"24.81733"},{"longitude":"121.20476","latitude":"24.81485"},{"longitude":"121.20722","latitude":"24.81385"},{"longitude":"121.21085","latitude":"24.81452"},{"longitude":"121.21242","latitude":"24.81454"},{"longitude":"121.21176","latitude":"24.81884"},{"longitude":"121.21324","latitude":"24.82159"},{"longitude":"121.21524","latitude":"24.82111"},{"longitude":"121.2222","latitude":"24.81787"},{"longitude":"121.22311","latitude":"24.81853"},{"longitude":"121.22525","latitude":"24.8201"},{"longitude":"121.22855","latitude":"24.82473"},{"longitude":"121.22864","latitude":"24.82534"},{"longitude":"121.23072","latitude":"24.82728"}]',121.23072,24.82728);
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(PATH_NO_SEQ.NEXTVAL), 5, '0'),'path8','3',1,to_timestamp('2020-03-30 18:18:18', 'YYYY-MM-DD HH24:MI:SS'),to_timestamp('2020-03-30 18:56:12', 'YYYY-MM-DD HH24:MI:SS'),464.21,1,'[{"longitude":"121.2511","latitude":"25.1166"},{"longitude":"121.25179","latitude":"25.11692"},{"longitude":"121.25416","latitude":"25.11782"},{"longitude":"121.25532","latitude":"25.1181"},{"longitude":"121.25799","latitude":"25.11868"},{"longitude":"121.25918","latitude":"25.11891"},{"longitude":"121.26134","latitude":"25.11915"},{"longitude":"121.26296","latitude":"25.11901"},{"longitude":"121.2637","latitude":"25.11887"},{"longitude":"121.26503","latitude":"25.11842"}]',121.2511,25.1166);
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(PATH_NO_SEQ.NEXTVAL), 5, '0'),'path9','3',1,to_timestamp('2020-03-30 18:18:18', 'YYYY-MM-DD HH24:MI:SS'),to_timestamp('2020-03-30 18:56:12', 'YYYY-MM-DD HH24:MI:SS'),464.21,1,'[{"longitude":"121.24746","latitude":"24.92256"},{"longitude":"121.24753","latitude":"24.92251"},{"longitude":"121.2478","latitude":"24.92233"},{"longitude":"121.24808","latitude":"24.92213"},{"longitude":"121.24858","latitude":"24.92179"},{"longitude":"121.24869","latitude":"24.92172"},{"longitude":"121.24933","latitude":"24.92127"},{"longitude":"121.24967","latitude":"24.92108"},{"longitude":"121.25001","latitude":"24.92094"},{"longitude":"121.25098","latitude":"24.92051"},{"longitude":"121.2511","latitude":"24.92045"},{"longitude":"121.25129","latitude":"24.92035"},{"longitude":"121.2515","latitude":"24.92"},{"longitude":"121.25171","latitude":"24.91963"},{"longitude":"121.25215","latitude":"24.91803"},{"longitude":"121.25217","latitude":"24.91777"}]',121.24746,24.92256);


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
INSERT INTO "CMT_RPT" (CMT_RPT_NO, RPT_REASON, RPT_STATUS, CMT_NO, MB_ID) VALUES ('cmtr'||LPAD(to_char(CMT_RPT_SEQ.nextval), 5, '0'), 'commentreport2', '1', 'cmt00002', 'soowii123');
INSERT INTO "CMT_RPT" (CMT_RPT_NO, RPT_REASON, RPT_STATUS, CMT_NO, MB_ID) VALUES ('cmtr'||LPAD(to_char(CMT_RPT_SEQ.nextval), 5, '0'), '爪哇寶貝好像五五身', '1', 'cmt00003', 'vain123');
INSERT INTO "CMT_RPT" (CMT_RPT_NO, RPT_REASON, RPT_STATUS, CMT_NO, MB_ID) VALUES ('cmtr'||LPAD(to_char(CMT_RPT_SEQ.nextval), 5, '0'), '汙辱我的美', '1', 'cmt00004', 'anjavababy520');
INSERT INTO "CMT_RPT" (CMT_RPT_NO, RPT_REASON, RPT_STATUS, CMT_NO, MB_ID) VALUES ('cmtr'||LPAD(to_char(CMT_RPT_SEQ.nextval), 5, '0'), '不要來', '1', 'cmt00005', 'anjavababy520');


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

Insert into MB_FOLLOW(MB_ID, MB_ID_FOLLOWED) 
values('anjavababy520','soowii123');

Insert into MB_FOLLOW(MB_ID, MB_ID_FOLLOWED) 
values('androidlababy520','soowii123');

Insert into MB_FOLLOW(MB_ID, MB_ID_FOLLOWED) 
values('anjavababy520','xuan123');

Insert into MB_FOLLOW(MB_ID, MB_ID_FOLLOWED) 
values('androidlababy520','weijhih123');

Insert into MB_FOLLOW(MB_ID, MB_ID_FOLLOWED) 
values('anjavababy520','weijhih123');

Insert into MB_FOLLOW(MB_ID, MB_ID_FOLLOWED) 
values('anjavababy520','androidlababy520');

Insert into MB_FOLLOW(MB_ID, MB_ID_FOLLOWED) 
values('androidlababy520','anjavababy520');

--commit--
commit;