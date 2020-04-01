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