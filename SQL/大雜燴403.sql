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
 ability_name VARCHAR2(20) NOT NULL
);

ALTER TABLE ability ADD CONSTRAINT PK_ability PRIMARY KEY (ability_no);


CREATE TABLE member (
 mb_id VARCHAR2(20) NOT NULL,
 mb_pwd VARCHAR2(20) NOT NULL,
 mb_name VARCHAR2(20) NOT NULL,
 mb_gender NUMBER(1) NOT NULL,
 mb_line VARCHAR2(20),
 mb_birthday DATE,
 mb_email VARCHAR2(20) NOT NULL,
 mb_pic BLOB,
 mb_lv NUMBER(2) DEFAULT '1' NOT NULL,
 mb_rpt_times NUMBER(2) DEFAULT '0' NOT NULL,
 mb_status NUMBER(1) DEFAULT '1' NOT NULL
);

ALTER TABLE member ADD CONSTRAINT PK_member PRIMARY KEY (mb_id);


CREATE TABLE message (
 msg_no VARCHAR2(20) NOT NULL,
 mb_id_1 VARCHAR2(20) NOT NULL,
 mb_id_2 VARCHAR2(20) NOT NULL,
 msg_content CLOB NOT NULL,
 msg_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 msg_status NUMBER(1) DEFAULT '1' NOT NULL
);

ALTER TABLE message ADD CONSTRAINT PK_message PRIMARY KEY (msg_no);


CREATE TABLE notify (
 ntf_no VARCHAR2(20) NOT NULL,
 mb_id VARCHAR2(20) NOT NULL,
 ntf_content CLOB NOT NULL,
 ntf_status NUMBER(1) DEFAULT '1' NOT NULL
);

ALTER TABLE notify ADD CONSTRAINT PK_notify PRIMARY KEY (ntf_no);


CREATE TABLE question_rpt (
 question_no VARCHAR2(20) NOT NULL,
 mb_id VARCHAR2(20) NOT NULL,
 question_content CLOB NOT NULL,
 question_status NUMBER(1) DEFAULT '1' NOT NULL
);

ALTER TABLE question_rpt ADD CONSTRAINT PK_question_rpt PRIMARY KEY (question_no);


CREATE TABLE staff (
 staff_id VARCHAR2(20) NOT NULL,
 staff_pwd VARCHAR2(20) NOT NULL,
 staff_name VARCHAR2(20) NOT NULL,
 staff_join TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 staff_status NUMBER(1) DEFAULT '1' NOT NULL
);

ALTER TABLE staff ADD CONSTRAINT PK_staff PRIMARY KEY (staff_id);


CREATE TABLE authority (
 staff_id VARCHAR2(20) NOT NULL,
 ability_no VARCHAR2(2) NOT NULL
);

ALTER TABLE authority ADD CONSTRAINT PK_authority PRIMARY KEY (staff_id,ability_no);


CREATE TABLE mb_rpt (
 mb_rpt_no VARCHAR2(20) NOT NULL,
 mb_id_1 VARCHAR2(20) NOT NULL,
 mb_id_2 VARCHAR2(20) NOT NULL,
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
Insert into MEMBER( MB_ID, MB_PWD, MB_LINE, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL) 
values('soowii123','123456','soowii_line','soowii','1',TO_DATE('1990-02-15','YYYY-MM-DD'),'soowii123@yahoo.com');
Insert into MEMBER( MB_ID, MB_PWD, MB_LINE, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL) 
values('xuan123','1234567','xuan_line','xuan','1',TO_DATE('1992-05-01','YYYY-MM-DD'),'xuan123@hotmail.com');
Insert into MEMBER( MB_ID, MB_PWD, MB_LINE, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL) 
values('michael123','12345678','michael_line','Michael','1',TO_DATE('1989-06-25','YYYY-MM-DD'),'Michael123@yahoo.com');
Insert into MEMBER( MB_ID, MB_PWD, MB_LINE, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL) 
values('vain123','123456789','vain)line','vain','1',TO_DATE('1987-07-01','YYYY-MM-DD'),'vain123@hotmail.com');
Insert into MEMBER( MB_ID, MB_PWD, MB_LINE, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL) 
values('yiwen123','1234566','yiwen_line','yiwen123','1',TO_DATE('1985-09-14','YYYY-MM-DD'),'yiwen123@yahoo.com');
Insert into MEMBER( MB_ID, MB_PWD, MB_LINE, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL) 
values('weijhih123','1234577','weijhih_line','weijhih123','1',TO_DATE('1989-10-19','YYYY-MM-DD'),'weijhih123@yahoo.com');

--訊息
Insert into MESSAGE( MSG_NO, MB_ID_1, MB_ID_2, MSG_CONTENT) 
values('MSN'||LPAD(to_char(msg_no_seq.NEXTVAL), 5, '0'),'soowii123','xuan123','哈囉你好啊');
Insert into MESSAGE( MSG_NO, MB_ID_1, MB_ID_2, MSG_CONTENT) 
values('MSN'||LPAD(to_char(msg_no_seq.NEXTVAL), 5, '0'),'xuan123','soowii123','嗨嗨你好');
Insert into MESSAGE( MSG_NO, MB_ID_1, MB_ID_2, MSG_CONTENT) 
values('MSN'||LPAD(to_char(msg_no_seq.NEXTVAL), 5, '0'),'michael123','vain123','我是麥可');
Insert into MESSAGE( MSG_NO, MB_ID_1, MB_ID_2, MSG_CONTENT) 
values('MSN'||LPAD(to_char(msg_no_seq.NEXTVAL), 5, '0'),'vain123','michael123','我是Vain');
Insert into MESSAGE( MSG_NO, MB_ID_1, MB_ID_2, MSG_CONTENT) 
values('MSN'||LPAD(to_char(msg_no_seq.NEXTVAL), 5, '0'),'vain123','michael123','哩賀');

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
values(LPAD(to_char(ABILITY_NO_SEQ.NEXTVAL), 2, '0'),'權限管理');
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
values('staff_michael','03');
Insert into AUTHORITY( STAFF_ID, ABILITY_NO) 
values('staff_michael','01');
Insert into AUTHORITY( STAFF_ID, ABILITY_NO) 
values('staff_michael','02');

--------------------------------------------------------
--  2.戍乂 天氣、地標 Drop
--------------------------------------------------------
DROP SEQUENCE "DA106G5"."LOC_NO_SEQ";
DROP TABLE "DA106G5"."LOCATION" cascade constraints;
DROP TABLE "DA106G5"."LOC_TYPE" cascade constraints;
DROP TABLE "DA106G5"."WEATHER" cascade constraints;
DROP TABLE "DA106G5"."WEATHER_DETAIL" cascade constraints;

--地標種類 Create--
CREATE TABLE loc_type (
 loc_typeno VARCHAR2(20) NOT NULL,
 loc_info VARCHAR2(20) NOT NULL
);

ALTER TABLE loc_type ADD CONSTRAINT PK_loc_type PRIMARY KEY (loc_typeno);

--地標種類 應該是真資料--
Insert into loc_type (loc_typeno,loc_info) values (1, '地點');
Insert into loc_type (loc_typeno,loc_info) values (2, '廁所');
Insert into loc_type (loc_typeno,loc_info) values (3, '補水點');

--地標 Create--
CREATE TABLE location (
 loc_no VARCHAR2(20) NOT NULL,
 loc_typeno VARCHAR2(20) NOT NULL,
 longitude VARCHAR2(20) NOT NULL,
 latitude VARCHAR2(20) NOT NULL,
 loc_status NUMBER(1) DEFAULT 1 NOT NULL,
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
Insert into location (loc_no,loc_typeno,longitude,latitude,loc_address,loc_status) values ('loc'||LPAD(to_char(loc_no_seq.NEXTVAL), 5, '0'),1,123.456,78.90,'台北',2);
Insert into location (loc_no,loc_typeno,longitude,latitude,loc_address) values ('loc'||LPAD(to_char(loc_no_seq.NEXTVAL), 5, '0'),1,123.456,78.90,'桃園');
Insert into location (loc_no,loc_typeno,longitude,latitude,loc_address,loc_status) values ('loc'||LPAD(to_char(loc_no_seq.NEXTVAL), 5, '0'),2,123.456,78.90,'桃園',2);
Insert into location (loc_no,loc_typeno,longitude,latitude,loc_address) values ('loc'||LPAD(to_char(loc_no_seq.NEXTVAL), 5, '0'),2,123.456,78.90,'新竹');
Insert into location (loc_no,loc_typeno,longitude,latitude,loc_address,loc_status) values ('loc'||LPAD(to_char(loc_no_seq.NEXTVAL), 5, '0'),3,123.456,78.90,'苗栗',2);
Insert into location (loc_no,loc_typeno,longitude,latitude,loc_address) values ('loc'||LPAD(to_char(loc_no_seq.NEXTVAL), 5, '0'),3,123.456,78.90,'高雄');


--天氣 Create--
CREATE TABLE weather (
 wth_status VARCHAR2(30) NOT NULL,
 weather_pic BLOB
);

ALTER TABLE weather ADD CONSTRAINT PK_weather PRIMARY KEY (wth_status);

--天氣 假資料--
Insert into weather (wth_status) values ('晴');
Insert into weather (wth_status) values ('雨');
Insert into weather (wth_status) values ('陰');
Insert into weather (wth_status) values ('晴時多雲偶陣雨');
Insert into weather (wth_status) values ('颱風');

--詳細天氣 Create--
CREATE TABLE weather_detail (
 weather_time TIMESTAMP(6) NOT NULL,
 weather_place VARCHAR2(30) NOT NULL,
 wth_status VARCHAR2(30) NOT NULL,
 wth_high NUMBER(3),
 wth_low NUMBER(3),
 wth_comfort VARCHAR2(30),
 wth_rain_chance NUMBER(3)
);

ALTER TABLE weather_detail ADD CONSTRAINT PK_weather_detail PRIMARY KEY (weather_time,weather_place);

--詳細天氣有個外鍵在天氣--
ALTER TABLE weather_detail ADD CONSTRAINT FK_weather_detail FOREIGN KEY (wth_status) REFERENCES weather (wth_status);

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
   (	"GRP_NO" VARCHAR2(20 BYTE), 
	"MB_ID" VARCHAR2(20 BYTE), 
	"LOC_NO" VARCHAR2(20 BYTE), 
	"GRP_APPLYSTART" DATE, 
	"GRP_APPLYEND" DATE, 
	"GRP_START" DATE, 
	"GRP_END" DATE, 
	"GRP_NAME" VARCHAR2(20 BYTE), 
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
   (	"GROUP_RPT_NO" VARCHAR2(20 BYTE), 
	"GRP_NO" VARCHAR2(20 BYTE), 
	"MB_ID" VARCHAR2(20 BYTE), 
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
   (	"MB_ID" VARCHAR2(20 BYTE), 
	"GRP_NO" VARCHAR2(20 BYTE), 
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
   (	"GRP_NO" VARCHAR2(20 BYTE), 
	"MB_ID" VARCHAR2(20 BYTE)
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
values('grp'||LPAD(to_char(grp_no_seq.NEXTVAL), 5, '0'),'soowii123','loc00001',TO_DATE('2020-05-15 08:30','YYYY-MM-DD HH24:MI'),TO_DATE('2020-05-15 18:30','YYYY-MM-DD HH24:MI'),TO_DATE('2020-05-10 08:30','YYYY-MM-DD HH24:MI'),TO_DATE('2020-05-13 20:30','YYYY-MM-DD HH24:MI'),'luck ball go','gogogo',20,5,15,1,30);

Insert into GROUPER(GRP_NO,MB_ID,LOC_NO,GRP_APPLYSTART,GRP_APPLYEND,GRP_START,GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW)
values('grp'||LPAD(to_char(grp_no_seq.NEXTVAL), 5, '0'),'xuan123','loc00002',TO_DATE('2020-05-16 08:30','YYYY-MM-DD HH24:MI'),TO_DATE('2020-05-16 18:30','YYYY-MM-DD HH24:MI'),TO_DATE('2020-05-11 08:30','YYYY-MM-DD HH24:MI'),TO_DATE('2020-05-14 20:30','YYYY-MM-DD HH24:MI'),'happy ball go','gogogo',25,10,20,1,35);

Insert into GROUPER(GRP_NO,MB_ID,LOC_NO,GRP_APPLYSTART,GRP_APPLYEND,GRP_START,GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW)
values('grp'||LPAD(to_char(grp_no_seq.NEXTVAL), 5, '0'),'michael123','loc00003',TO_DATE('2020-05-17 08:30','YYYY-MM-DD HH24:MI'),TO_DATE('2020-05-17 18:30','YYYY-MM-DD HH24:MI'),TO_DATE('2020-05-12 08:30','YYYY-MM-DD HH24:MI'),TO_DATE('2020-05-15 20:30','YYYY-MM-DD HH24:MI'),'sad ball go','gogogo',30,15,20,1,37);

Insert into GROUPER(GRP_NO,MB_ID,LOC_NO,GRP_APPLYSTART,GRP_APPLYEND,GRP_START,GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW)
values('grp'||LPAD(to_char(grp_no_seq.NEXTVAL), 5, '0'),'vain123','loc00004',TO_DATE('2020-05-18 08:30','YYYY-MM-DD HH24:MI'),TO_DATE('2020-05-18 18:30','YYYY-MM-DD HH24:MI'),TO_DATE('2020-05-13 08:30','YYYY-MM-DD HH24:MI'),TO_DATE('2020-05-16 20:30','YYYY-MM-DD HH24:MI'),'laugh ball go','gogogo',35,20,20,1,38);

Insert into GROUPER(GRP_NO,MB_ID,LOC_NO,GRP_APPLYSTART,GRP_APPLYEND,GRP_START,GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW)
values('grp'||LPAD(to_char(grp_no_seq.NEXTVAL), 5, '0'),'yiwen123','loc00005',TO_DATE('2020-05-19 08:30','YYYY-MM-DD HH24:MI'),TO_DATE('2020-05-19 18:30','YYYY-MM-DD HH24:MI'),TO_DATE('2020-05-14 08:30','YYYY-MM-DD HH24:MI'),TO_DATE('2020-05-17 20:30','YYYY-MM-DD HH24:MI'),'yummy ball go','gogogo',40,25,30,1,39);

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
 cp_no VARCHAR2(20) NOT NULL, 
 cp_name VARCHAR2(20) NOT NULL,
 cp_pic BLOB
);

ALTER TABLE coupon ADD CONSTRAINT PK_coupon PRIMARY KEY (cp_no);


-----訂單表格------

CREATE TABLE orders (
 od_no VARCHAR2(20) NOT NULL,
 mb_id VARCHAR2(20) NOT NULL,
 od_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
 od_status NUMBER(1) DEFAULT '1' NOT NULL,
 od_totalprice NUMBER(10) NOT NULL,
 cp_no VARCHAR2(20),
 od_discount NUMBER(10),
 od_add VARCHAR2(100) NOT NULL
);

ALTER TABLE orders ADD CONSTRAINT PK_orders PRIMARY KEY (od_no);


-----產品類型表格----

CREATE TABLE pd_type (
 pd_typeno VARCHAR2(20) NOT NULL,
 pd_typename VARCHAR2(40) NOT NULL
);

ALTER TABLE pd_type ADD CONSTRAINT PK_pd_type PRIMARY KEY (pd_typeno);

-----產品表格------

CREATE TABLE product (
 pd_no VARCHAR2(20) NOT NULL,
 pd_name VARCHAR2(100) NOT NULL,
 pd_price NUMBER(10) NOT NULL,
 pd_pic BLOB,
 pd_detail CLOB,
 pd_status NUMBER(1) DEFAULT '1' NOT NULL,
 pd_typeno VARCHAR2(20)
);

ALTER TABLE product ADD CONSTRAINT PK_product PRIMARY KEY (pd_no);


-----持有優惠卷表格------


CREATE TABLE cp_get (
 mb_id VARCHAR2(20) NOT NULL,
 cp_no VARCHAR2(20) NOT NULL,
 cp_status NUMBER(1) DEFAULT '1' NOT NULL
);

ALTER TABLE cp_get ADD CONSTRAINT PK_cp_get PRIMARY KEY (mb_id,cp_no);


-----訂單明細表格-----


CREATE TABLE od_detail (
 od_no VARCHAR2(20) NOT NULL,
 pd_no VARCHAR2(20) NOT NULL,
 od_amount NUMBER(2) NOT NULL,
 od_price NUMBER(10) NOT NULL
);

ALTER TABLE od_detail ADD CONSTRAINT PK_od_detail PRIMARY KEY (pd_no,od_no);


-----商品收藏表格-----


CREATE TABLE pd_follow (
 mb_id VARCHAR2(20) NOT NULL,
 pd_no VARCHAR2(20) NOT NULL
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

Insert into COUPON (CP_NO,CP_NAME) values ('CPN'||LPAD(to_char(COUPON_SEQ.NEXTVAL), 5, '0'),'優惠卷50元');
Insert into COUPON (CP_NO,CP_NAME) values ('CPN'||LPAD(to_char(COUPON_SEQ.NEXTVAL), 5, '0'),'優惠卷100元');
Insert into COUPON (CP_NO,CP_NAME) values ('CPN'||LPAD(to_char(COUPON_SEQ.NEXTVAL), 5, '0'),'優惠卷150元');
Insert into COUPON (CP_NO,CP_NAME) values ('CPN'||LPAD(to_char(COUPON_SEQ.NEXTVAL), 5, '0'),'優惠卷200元');
Insert into COUPON (CP_NO,CP_NAME) values ('CPN'||LPAD(to_char(COUPON_SEQ.NEXTVAL), 5, '0'),'優惠卷250元');
Insert into COUPON (CP_NO,CP_NAME) values ('CPN'||LPAD(to_char(COUPON_SEQ.NEXTVAL), 5, '0'),'優惠卷300元');


---------------------ORDERS訂單---------------------
CREATE SEQUENCE ORDERS_seq
INCREMENT BY 1
START WITH 6
NOMAXVALUE
NOCYCLE;

Insert into ORDERS(OD_NO, MB_ID, OD_TOTALPRICE, OD_ADD) 
values('20200324-000001','soowii123','200000','中央大學A');
Insert into ORDERS(OD_NO, MB_ID, OD_TOTALPRICE, OD_ADD) 
values('20200324-000002','xuan123','200000','中央大學C');
Insert into ORDERS(OD_NO, MB_ID, OD_TOTALPRICE, OD_ADD) 
values('20200324-000003','michael123','200000','中央大學B');
Insert into ORDERS(OD_NO, MB_ID, OD_TOTALPRICE, OD_ADD) 
values('20200324-000004','vain123','200000','中央大學D');
Insert into ORDERS(OD_NO, MB_ID, OD_TOTALPRICE, OD_ADD) 
values('20200324-000005', 'yiwen123','200000','中央大學E');
Insert into ORDERS(OD_NO, MB_ID, OD_TOTALPRICE, OD_ADD) 
values('20200324-000006', 'weijhih123','200000','中央大學F');

---------------------PD_TYPE商品類別---------------------

CREATE SEQUENCE PD_TYPE_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE;

Insert into PD_TYPE(PD_TYPENO, PD_TYPENAME) 
values('PTN'||LPAD(to_char(PD_TYPE_SEQ.NEXTVAL), 5, '0'),'服飾類＿上身');
Insert into PD_TYPE(PD_TYPENO, PD_TYPENAME) 
values('PTN'||LPAD(to_char(PD_TYPE_SEQ.NEXTVAL), 5, '0'),'服飾類＿下身');
Insert into PD_TYPE(PD_TYPENO, PD_TYPENAME) 
values('PTN'||LPAD(to_char(PD_TYPE_SEQ.NEXTVAL), 5, '0'),'鞋類');
Insert into PD_TYPE(PD_TYPENO, PD_TYPENAME) 
values('PTN'||LPAD(to_char(PD_TYPE_SEQ.NEXTVAL), 5, '0'),'配件類');
Insert into PD_TYPE(PD_TYPENO, PD_TYPENAME) 
values('PTN'||LPAD(to_char(PD_TYPE_SEQ.NEXTVAL), 5, '0'),'運動食品類');

-----------------------PRODUCT商品-----------------------

CREATE SEQUENCE PRODUCT_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE;

Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'+8鐵手套','10000','PTN00004');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'+10黃金甲','10000','PTN00001');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'+8卍褲子卍','10000','PTN00002');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'洛克人Ｅ罐','10000','PTN00005');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'館長台灣價值雞胸肉','10000','PTN00005');
Insert into PRODUCT(PD_NO, PD_NAME, PD_PRICE, PD_TYPENO) 
values('PDN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),'走到飛球鞋','10000','PTN00003');

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

Insert into OD_DETAIL(OD_NO, PD_NO, OD_AMOUNT, OD_PRICE) 
values('20200324-000001','PDN00001','20','200000');
Insert into OD_DETAIL(OD_NO, PD_NO, OD_AMOUNT, OD_PRICE) 
values('20200324-000001','PDN00002','20','200000');
Insert into OD_DETAIL(OD_NO, PD_NO, OD_AMOUNT, OD_PRICE) 
values('20200324-000002','PDN00003','20','200000');
Insert into OD_DETAIL(OD_NO, PD_NO, OD_AMOUNT, OD_PRICE) 
values('20200324-000002','PDN00004','20','200000');
Insert into OD_DETAIL(OD_NO, PD_NO, OD_AMOUNT, OD_PRICE) 
values('20200324-000003','PDN00005','20','200000');
Insert into OD_DETAIL(OD_NO, PD_NO, OD_AMOUNT, OD_PRICE) 
values('20200324-000003','PDN00006','20','200000');


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
-- mb_id VARCHAR2(20) NOT NULL,
-- mb_pwd VARCHAR2(20) NOT NULL,
-- mb_line VARCHAR2(20),
-- mb_name VARCHAR2(20) NOT NULL,
-- mb_gender NUMBER(1) NOT NULL,
-- mb_birthday DATE,
-- mb_lv NUMBER(2) DEFAULT '1' NOT NULL,
-- mb_pic BLOB,
-- mb_rpt_times NUMBER(2) DEFAULT '0' NOT NULL,
-- mb_email VARCHAR2(20) NOT NULL,
-- mb_status NUMBER(1) DEFAULT '1' NOT NULL
--);

--ALTER TABLE member ADD CONSTRAINT PK_member PRIMARY KEY (mb_id);


CREATE TABLE path (
 path_no         VARCHAR2(20)               NOT NULL,
 path_name       VARCHAR2(200),
 path_difficulty NUMBER(1)    DEFAULT '1',
 path_popular    NUMBER(1)    DEFAULT '1'   NOT NULL,
 path_start      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 path_end        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 path_pic        BLOB,
 path_distance   NUMBER(10,2),
 path_status     NUMBER(1)    DEFAULT '1'   NOT NULL,
 path_kml        CLOB,
 path_lng        NUMBER(20, 6),
 path_lat        NUMBER(20, 6)
);

ALTER TABLE path ADD CONSTRAINT PK_path PRIMARY KEY (path_no);

CREATE SEQUENCE path_no_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;


CREATE TABLE record (
 rcd_no            VARCHAR2(20)  NOT NULL,
 rcd_uploadtime    DATE,
 rcd_content       CLOB,
 rcd_thumb_amount  NUMBER(5),
 rcd_metoo_amount  NUMBER(5),
 rcd_status        NUMBER(1)  DEFAULT'1',
 path_no           VARCHAR2(20),
 mb_id             VARCHAR2(20)  NOT NULL
);

ALTER TABLE record ADD CONSTRAINT PK_record PRIMARY KEY (rcd_no);


CREATE SEQUENCE rcd_no_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;


CREATE TABLE commentt (
 cmt_no       VARCHAR2(20)          NOT NULL,
 cmt_content  CLOB,
 cmt_time     DATE,
 cmt_status   NUMBER(1) DEFAULT'1'  NOT NULL,
 rcd_no       VARCHAR2(20)          NOT NULL,
 mb_id        VARCHAR2(20)          NOT NULL
);

ALTER TABLE commentt ADD CONSTRAINT PK_comment PRIMARY KEY (cmt_no);

CREATE SEQUENCE cmt_no_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;


CREATE TABLE path_follow (
 path_no   VARCHAR2(20) NOT NULL,
 mb_id     VARCHAR2(20) NOT NULL
);

ALTER TABLE path_follow ADD CONSTRAINT PK_path_follow PRIMARY KEY (path_no,mb_id);


CREATE TABLE thumb (
 rcd_no   VARCHAR2(20) NOT NULL,
 mb_id    VARCHAR2(20) NOT NULL
);

ALTER TABLE thumb ADD CONSTRAINT PK_thumb PRIMARY KEY (rcd_no,mb_id);


CREATE TABLE metoo (
 rcd_no   VARCHAR2(20) NOT NULL,
 mb_id    VARCHAR2(20) NOT NULL
);

ALTER TABLE metoo ADD CONSTRAINT PK_metoo PRIMARY KEY (rcd_no,mb_id);


CREATE TABLE rcd_rpt (
 rcd_rpt_no   VARCHAR2(20)          NOT NULL,
 rpt_reason   CLOB,
 rpt_status   NUMBER(1) DEFAULT'1'  NOT NULL,
 rcd_no       VARCHAR2(20)          NOT NULL,
 mb_id        VARCHAR2(20)
);

ALTER TABLE rcd_rpt ADD CONSTRAINT PK_rcd_rpt PRIMARY KEY (rcd_rpt_no);

CREATE SEQUENCE rcd_rpt_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;



CREATE TABLE cmt_rpt (
 cmt_rpt_no   VARCHAR2(20)          NOT NULL,
 rpt_reason   CLOB,
 rpt_status   NUMBER(1) DEFAULT'1'  NOT NULL,
 cmt_no       VARCHAR2(20)          NOT NULL,
 mb_id        VARCHAR2(20)
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



--會員
--Insert into MEMBER( MB_ID, MB_PWD, MB_LINE, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL) values('soowii123','123456','soowii_line','soowii','1',TO_DATE('1990-02-15','YYYY-MM-DD'),'soowii123@yahoo.com');
--Insert into MEMBER( MB_ID, MB_PWD, MB_LINE, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL) values('xuan123','1234567','xuan_line','xuan','1',TO_DATE('1992-05-01','YYYY-MM-DD'),'xuan123@hotmail.com');
--Insert into MEMBER( MB_ID, MB_PWD, MB_LINE, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL) values('michael123','12345678','michael_line','Michael','1',TO_DATE('1989-06-25','YYYY-MM-DD'),'Michael123@yahoo.com');
--Insert into MEMBER( MB_ID, MB_PWD, MB_LINE, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL) values('vain123','123456789','vain)line','vain','1',TO_DATE('1987-07-01','YYYY-MM-DD'),'vain123@hotmail.com');
--Insert into MEMBER( MB_ID, MB_PWD, MB_LINE, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL) values('yiwen123','1234566','yiwen_line','yiwen123','1',TO_DATE('1985-09-14','YYYY-MM-DD'),'yiwen123@yahoo.com');
--Insert into MEMBER( MB_ID, MB_PWD, MB_LINE, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_EMAIL) values('weijhih123','1234577','weijhih_line','weijhih123','1',TO_DATE('1989-10-19','YYYY-MM-DD'),'weijhih123@yahoo.com');


--PATH   
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(path_NO_SEQ.NEXTVAL), 5, '0'),'path1','1',1,to_date('22-MAR-20 10.49.38 PM','DD-MON-RR HH.MI.SS AM'),to_timestamp('22-MAR-20 10.49.38 PM','DD-MON-RR HH.MI.SS AM'),200.22,1,'[{"LNG":"121.083945","LAT":"24.964264"},{"LNG":"121.083950","LAT":"24.964270"},{"LNG":"121.083955","LAT":"24.964275"},{"LNG":"121.083960","LAT":"24.964275"},{"LNG":"121.083965","LAT":"24.964280"},{"LNG":"121.083970","LAT":"24.964280"},{"LNG":"121.083975","LAT":"24.964285"},{"LNG":"121.083980","LAT":"24.964290"}]',121.083945,24.964264);
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(PATH_NO_SEQ.NEXTVAL), 5, '0'),'path2','2',2,to_date('22-MAR-20 01.49.38 PM','DD-MON-RR HH.MI.SS AM'),to_timestamp('22-MAR-20 02.49.38 PM','DD-MON-RR HH.MI.SS AM'),2001.65,1,'[{"LNG":"121.068628","LAT":"24.956572"},{"LNG":"121.068630","LAT":"24.956575"},{"LNG":"121.068635","LAT":"24.956580"},{"LNG":"121.068640","LAT":"24.956585"}{"LNG":"121.068645","LAT":"24.956590"},{"LNG":"121.068650","LAT":"24.956595"},{"LNG":"121.068655","LAT":"24.956600"},{"LNG":"121.068660","LAT":"24.956600"}]',121.068628,24.956572);
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(PATH_NO_SEQ.NEXTVAL), 5, '0'),'path3','3',1,to_date('22-MAR-20 09.49.38 PM','DD-MON-RR HH.MI.SS AM'),to_timestamp('22-MAR-20 10.49.38 PM','DD-MON-RR HH.MI.SS AM'),501,1,'[{"LNG":"121.020953","LAT":"24.954836"},{"LNG":"121.020955","LAT":"24.954840"},{"LNG":"121.020960","LAT":"24.954845"},{"LNG":"121.020965","LAT":"24.954850"},{"LNG":"121.020970","LAT":"24.954855"},{"LNG":"121.020975","LAT":"24.954860"},{"LNG":"121.020980","LAT":"24.954865"},{"LNG":"121.020985","LAT":"24.954870"}]',121.020953,24.954836);
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(PATH_NO_SEQ.NEXTVAL), 5, '0'),'path4','1',1,to_date('22-MAR-20 12.49.38 PM','DD-MON-RR HH.MI.SS AM'),to_timestamp('22-MAR-20 02.49.38 PM','DD-MON-RR HH.MI.SS AM'),1500.88,1,'[{"LNG":"121.199769","LAT":"24.963707"},{"LNG":"121.199770","LAT":"24.963710"},{"LNG":"121.199775","LAT":"24.963715"},{"LNG":"121.199780","LAT":"24.963720"},{"LNG":"121.199785","LAT":"24.963725"},{"LNG":"121.199790","LAT":"24.963730"},{"LNG":"121.199795","LAT":"24.963735"},{"LNG":"121.199800","LAT":"24.963740"}]',121.199769,24.963707);
Insert into PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_POPULAR,PATH_START,PATH_END,PATH_DISTANCE,PATH_STATUS,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(PATH_NO_SEQ.NEXTVAL), 5, '0'),'path5','3',2,to_date('22-MAR-20 05.49.38 PM','DD-MON-RR HH.MI.SS AM'),to_timestamp('22-MAR-20 06.02.38 PM','DD-MON-RR HH.MI.SS AM'),464.21,1,'[{"LNG":"121.236168","LAT":"24.985739"},{"LNG":"121.236170","LAT":"24.985740"},{"LNG":"121.236175","LAT":"24.985745"},{"LNG":"121.236180","LAT":"24.985750"},{"LNG":"121.236185","LAT":"24.985755"},{"LNG":"121.236190","LAT":"24.985760"},{"LNG":"121.236195","LAT":"24.985765"},{"LNG":"121.236200","LAT":"24.985780"}]',121.236168,24.985739);

--RECORD  TO_DATE('2020-03-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS')
INSERT INTO RECORD (RCD_NO, RCD_UPLOADTIME, RCD_CONTENT, RCD_THUMB_AMOUNT, RCD_METOO_AMOUNT, RCD_STATUS, PATH_NO, MB_ID)
VALUES ('rcd'||LPAD(to_char(RCD_NO_SEQ.nextval), 5, '0'), TO_DATE('2020-03-01 11:11:11', 'YYYY-MM-DD HH24:MI:SS'), 'record1', '100', '100', '1', 'p00001', 'xuan123');
INSERT INTO RECORD (RCD_NO, RCD_UPLOADTIME, RCD_CONTENT, RCD_THUMB_AMOUNT, RCD_METOO_AMOUNT, RCD_STATUS, PATH_NO, MB_ID)
VALUES ('rcd'||LPAD(to_char(RCD_NO_SEQ.nextval), 5, '0'), TO_DATE('2020-03-01 11:22:33', 'YYYY-MM-DD HH24:MI:SS'), 'record2', '200', '20', '1', 'p00002', 'soowii123');
INSERT INTO RECORD (RCD_NO, RCD_UPLOADTIME, RCD_CONTENT, RCD_THUMB_AMOUNT, RCD_METOO_AMOUNT, RCD_STATUS, PATH_NO, MB_ID)
VALUES ('rcd'||LPAD(to_char(RCD_NO_SEQ.nextval), 5, '0'), TO_DATE('2020-03-12 22:22:22', 'YYYY-MM-DD HH24:MI:SS'), 'record3', '50', '50', '1', 'p00003', 'michael123');
INSERT INTO RECORD (RCD_NO, RCD_UPLOADTIME, RCD_CONTENT, RCD_THUMB_AMOUNT, RCD_METOO_AMOUNT, RCD_STATUS, PATH_NO, MB_ID)
VALUES ('rcd'||LPAD(to_char(RCD_NO_SEQ.nextval), 5, '0'), TO_DATE('2020-03-01 18:22:33', 'YYYY-MM-DD HH24:MI:SS'), 'record4', '65', '300', '1', 'p00004', 'yiwen123');
INSERT INTO RECORD (RCD_NO, RCD_UPLOADTIME, RCD_CONTENT, RCD_THUMB_AMOUNT, RCD_METOO_AMOUNT, RCD_STATUS, PATH_NO, MB_ID)
VALUES ('rcd'||LPAD(to_char(RCD_NO_SEQ.nextval), 5, '0'), TO_DATE('2020-03-01 20:22:33', 'YYYY-MM-DD HH24:MI:SS'), 'record5', '99', '99', '1', 'p00005', 'weijhih123');


--留言
INSERT INTO "COMMENTT" (CMT_NO, CMT_CONTENT, CMT_TIME, CMT_STATUS, RCD_NO, MB_ID) VALUES ('cmt'||LPAD(to_char(CMT_NO_SEQ.nextval), 5, '0'), 'commemt1', TO_DATE('2020-03-05', 'YYYY-MM-DD'), '1', 'rcd00001', 'soowii123');
INSERT INTO "COMMENTT" (CMT_NO, CMT_CONTENT, CMT_TIME, CMT_STATUS, RCD_NO, MB_ID) VALUES ('cmt'||LPAD(to_char(CMT_NO_SEQ.nextval), 5, '0'), 'comment2', TO_DATE('2020-03-05', 'YYYY-MM-DD'), '1', 'rcd00002', 'xuan123');
INSERT INTO "COMMENTT" (CMT_NO, CMT_CONTENT, CMT_TIME, CMT_STATUS, RCD_NO, MB_ID) VALUES ('cmt'||LPAD(to_char(CMT_NO_SEQ.nextval), 5, '0'), 'comment3', TO_DATE('2020-03-10', 'YYYY-MM-DD'), '1', 'rcd00003', 'michael123');
INSERT INTO "COMMENTT" (CMT_NO, CMT_CONTENT, CMT_TIME, CMT_STATUS, RCD_NO, MB_ID) VALUES ('cmt'||LPAD(to_char(CMT_NO_SEQ.nextval), 5, '0'), 'comment4', TO_DATE('2020-03-15', 'YYYY-MM-DD'), '1', 'rcd00001', 'vain123');
INSERT INTO "COMMENTT" (CMT_NO, CMT_CONTENT, CMT_TIME, CMT_STATUS, RCD_NO, MB_ID) VALUES ('cmt'||LPAD(to_char(CMT_NO_SEQ.nextval), 5, '0'), 'comment5', TO_DATE('2020-03-20', 'YYYY-MM-DD'), '1', 'rcd00001', 'yiwen123');


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


--METOO
INSERT INTO "METOO" (RCD_NO, MB_ID) VALUES ('rcd00001', 'soowii123');
INSERT INTO "METOO" (RCD_NO, MB_ID) VALUES ('rcd00002', 'xuan123');
INSERT INTO "METOO" (RCD_NO, MB_ID) VALUES ('rcd00003', 'vain123');
INSERT INTO "METOO" (RCD_NO, MB_ID) VALUES ('rcd00004', 'yiwen123');
INSERT INTO "METOO" (RCD_NO, MB_ID) VALUES ('rcd00005', 'weijhih123');


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
 live_no VARCHAR2(20) NOT NULL,
 live_content VARCHAR2(100),
 live_status NUMBER(1) NOT NULL,
 live_startteaser TIMESTAMP,
 live_start TIMESTAMP,
 live_store BLOB,
 mb_id VARCHAR2(20) NOT NULL
);

ALTER TABLE live ADD CONSTRAINT PK_live PRIMARY KEY (live_no);


CREATE TABLE live_rpt (
 live_rpt_no VARCHAR2(20) NOT NULL,
 rpt_status NUMBER(1) NOT NULL,
 rpt_reason CLOB,
 live_no VARCHAR2(20) NOT NULL,
 mb_id VARCHAR2(20) NOT NULL
);

ALTER TABLE live_rpt ADD CONSTRAINT PK_live_rpt PRIMARY KEY (live_rpt_no);


CREATE TABLE mb_follow (
 mb_id VARCHAR2(20) NOT NULL,
 mb_id_followed VARCHAR2(20) NOT NULL
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
