
--刪除資料表
DROP TABLE STUDENT_HOMEWORK;  --學生作業
DROP TABLE TEACHER_HOMEWORK;  --作業題目(老師) 
DROP TABLE verify_list;       --審核資料表 
DROP TABLE post_message;      --公告訊息
DROP TABLE sub_message;       --小留言資料表 
DROP TABLE main_message;      --大留言資料表 
DROP TABLE evaluation;        --評價
DROP TABLE order_list;        --訂單明細 
DROP TABLE order_info;        --訂單資訊 
DROP TABLE coupon;            --優惠券 
DROP TABLE test;              --隋堂考試 
DROP TABLE video_record;      --影片瀏覽紀錄時間表 
DROP TABLE CLASS_UNIT;        --課程單元 
DROP TABLE CLASS_CHAPTER;     --課程章節 
DROP TABLE login_history;     --登入歷程 
DROP TABLE member_interest;   --會員興趣 
DROP TABLE keyword_form;      --關鍵字排行表格 
DROP TABLE CLASS_INFO;        --課程
DROP TABLE sub_class;         --副課程類別 
DROP TABLE main_class;        --主課程類別 
DROP TABLE admin_auth;      --管理員權限
DROP TABLE auth_list;        --權限代號 
DROP TABLE admin_list;        --管理員資料  
DROP TABLE member_info;       --會員資料 

commit;


--刪除序列
DROP SEQUENCE class_info_SEQ;            --課程
DROP SEQUENCE STUDENT_HOMEWORK_SEQ;  	 --學生作業
DROP SEQUENCE TEACHER_HOMEWORK_SEQ;  	 --作業題目
DROP SEQUENCE CLASS_UNIT_SEQ;        	 --課程單元
DROP SEQUENCE CLASS_CHAPTER_SEQ;     	 --課程章節
DROP SEQUENCE verify_list_SEQ;           --審核資料表
DROP SEQUENCE post_message_SEQ;          --公告訊息
DROP SEQUENCE sub_message_SEQ;           --小留言資料表
DROP SEQUENCE main_message_SEQ;          --大留言資料表
DROP SEQUENCE evaluation_SEQ;            --評價
DROP SEQUENCE video_record_CS;           --影片瀏覽紀錄時間表
DROP SEQUENCE TEST_SEQ;                  --隋堂考試
DROP SEQUENCE MAIN_CLASS_SEQ;            --主課程類別
DROP SEQUENCE SUB_CLASS_SEQ;             --副課程類別
DROP SEQUENCE order_infoSQE;             --訂單資訊
DROP SEQUENCE order_list_SQE;            --訂單明細
DROP SEQUENCE coupon_SQE;                --優惠券
DROP SEQUENCE admin_list_SEQ;            --管理員資料
DROP SEQUENCE LOGID_SEQ;                 --登入歷程
DROP SEQUENCE MEM_SEQ;                   --會員
DROP SEQUENCE MEBIN_SEQ;                 --會員興趣
DROP SEQUENCE KEYI_SEQ;                  --關鍵字排行表格
commit;






--member_info會員資料序列
CREATE SEQUENCE MEM_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

--member_info會員資料
create table member_info (
	primary key(member_id),	
	member_id                  varchar2(20) not null	,
	member_name                varchar2(50) not null,
    member_email               varchar2(50) not null  unique,
	member_password            varchar2(50) not null,
	member_role                number(1,0)	DEFAULT 0,
	member_gender              number(1,0),
	member_birthday            Date,
	member_occupation          varchar2(20),
	member_address             varchar2(100),
	member_invoice             varchar2(50)	,
	member_pic                 Blob	,
	teachclass_on              number(1,0)	DEFAULT 1,
	learnclass_on              number(1,0)	DEFAULT 1,
	member_homework            number(1,0)	DEFAULT 1,
	member_about               CLOB,
	member_good_for            CLOB,
	register_time              Timestamp    not null,
	member_update              Timestamp    not null,
	traccount                  varchar2(20),
	bank_code                  varchar2(10));

INSERT INTO member_info VALUES ('MEM' || lpad(MEM_SEQ.NEXTVAL,5, '0'),'NICK','n30764@gmail.com','111111',1,0,TO_DATE('1991-09-17','YYYY-MM-DD'),'老師','彰化縣員林市','/1111111',null,1,1,1,null,null,TO_TIMESTAMP('2020-11-27 16:48:50','yyyy-mm-dd hh24:mi:ss'),TO_TIMESTAMP('2020-12-27 16:48:50','yyyy-mm-dd hh24:mi:ss'),'111111111111','004');
INSERT INTO member_info VALUES ('MEM' || lpad(MEM_SEQ.NEXTVAL,5, '0'),'WIN','ccps920167@gmail.com','222222',0,0,TO_DATE('1991-09-18','YYYY-MM-DD'),'連長','高雄市燕巢區','/2222222',null,1,1,1,null,null,TO_TIMESTAMP('2020-11-27 16:48:51','yyyy-mm-dd hh24:mi:ss'),TO_TIMESTAMP('2020-12-27 16:48:51','yyyy-mm-dd hh24:mi:ss'),'222222222222','005');
INSERT INTO member_info VALUES ('MEM' || lpad(MEM_SEQ.NEXTVAL,5, '0'),'POYA','q262633@gmail.com','333333',1,0,TO_DATE('1991-09-19','YYYY-MM-DD'),'活動企劃','新北市土城區','/3333333',null,1,1,1,null,null,TO_TIMESTAMP('2020-11-27 16:48:52','yyyy-mm-dd hh24:mi:ss'),TO_TIMESTAMP('2020-12-27 16:48:52','yyyy-mm-dd hh24:mi:ss'),'333333333333','006');
INSERT INTO member_info VALUES ('MEM' || lpad(MEM_SEQ.NEXTVAL,5, '0'),'MINGJ','aa556699aa@yahoo.com.tw','444444',1,0,TO_DATE('1991-09-20','YYYY-MM-DD'),'美術總監','台北市信義區','/4444444',null,1,1,1,null,null,TO_TIMESTAMP('2020-11-27 16:48:53','yyyy-mm-dd hh24:mi:ss'),TO_TIMESTAMP('2020-12-27 16:48:53','yyyy-mm-dd hh24:mi:ss'),'444444444444','007');
INSERT INTO member_info VALUES ('MEM' || lpad(MEM_SEQ.NEXTVAL,5, '0'),'YUKI','yuki33029@gmail.com','555555',1,0,TO_DATE('1991-09-21','YYYY-MM-DD'),'金牌業務','台北市信義區','/5555555',null,1,1,1,null,null,TO_TIMESTAMP('2020-11-27 16:48:54','yyyy-mm-dd hh24:mi:ss'),TO_TIMESTAMP('2020-12-27 16:48:54','yyyy-mm-dd hh24:mi:ss'),'555555555555','008');
INSERT INTO member_info VALUES ('MEM' || lpad(MEM_SEQ.NEXTVAL,5, '0'),'HAULMNG','haulmng3458@gmail.com','666666',1,0,TO_DATE('1991-09-22','YYYY-MM-DD'),'資深工程師','新北市新莊區','/6666666',null,1,1,1,null,null,TO_TIMESTAMP('2020-11-27 16:48:55','yyyy-mm-dd hh24:mi:ss'),TO_TIMESTAMP('2020-12-27 16:48:55','yyyy-mm-dd hh24:mi:ss'),'666666666666','009');
INSERT INTO member_info VALUES ('MEM' || lpad(MEM_SEQ.NEXTVAL,5, '0'),'JSES','jses9650717@gmail.com','777777',1,0,TO_DATE('1991-09-23','YYYY-MM-DD'),'數據工程師','台北市大安區','/7777777',null,1,1,1,null,null,TO_TIMESTAMP('2020-11-27 16:48:56','yyyy-mm-dd hh24:mi:ss'),TO_TIMESTAMP('2020-12-27 16:48:56','yyyy-mm-dd hh24:mi:ss'),'777777777777','010');
INSERT INTO member_info VALUES ('MEM' || lpad(MEM_SEQ.NEXTVAL,5, '0'),'DAVID','vladylo98@gmail.com','888888',1,0,TO_DATE('1991-09-24','YYYY-MM-DD'),'講師','桃園市中壢區','/8888888',null,1,1,1,null,null,TO_TIMESTAMP('2020-11-27 16:48:57','yyyy-mm-dd hh24:mi:ss'),TO_TIMESTAMP('2020-12-27 16:48:57','yyyy-mm-dd hh24:mi:ss'),'888888888888','011');
INSERT INTO member_info VALUES ('MEM' || lpad(MEM_SEQ.NEXTVAL,5, '0'),'PETER','peter1@gmail.com','999999',1,0,TO_DATE('1991-09-25','YYYY-MM-DD'),'講師','桃園市中壢區','/9999999',null,1,1,1,null,null,TO_TIMESTAMP('2020-11-27 16:48:58','yyyy-mm-dd hh24:mi:ss'),TO_TIMESTAMP('2020-12-27 16:48:58','yyyy-mm-dd hh24:mi:ss'),'999999999999','012');
INSERT INTO member_info VALUES ('MEM' || lpad(MEM_SEQ.NEXTVAL,5, '0'),'MURNA','murna113077@gmail.com','aaaaaa',1,0,TO_DATE('1991-09-26','YYYY-MM-DD'),'講師','新北市中和區景新街347號8樓之8','/1010101',null,1,1,1,null,null,TO_TIMESTAMP('2020-11-27 16:48:59','yyyy-mm-dd hh24:mi:ss'),TO_TIMESTAMP('2020-12-27 16:48:59','yyyy-mm-dd hh24:mi:ss'),'101010101010','013');
--會員資料新增
INSERT INTO MEMBER_INFO VALUES ('MEM' || lpad(MEM_SEQ.NEXTVAL,5, '0'),'NANA','qw359999@gmail.com','bbbbbb',0,0,TO_DATE('1991-09-17','YYYY-MM-DD'),'學生','彰化縣員林市','/1212121',null,1,1,1,null,null,TO_TIMESTAMP('2020-11-27 16:48:50','yyyy-mm-dd hh24:mi:ss'),TO_TIMESTAMP('2020-12-27 16:48:50','yyyy-mm-dd hh24:mi:ss'),'111111111111','004');
INSERT INTO MEMBER_INFO VALUES ('MEM' || lpad(MEM_SEQ.NEXTVAL,5, '0'),'LULU','cs920@gmail.com','cccccc',0,0,TO_DATE('1991-09-18','YYYY-MM-DD'),'學生','高雄市燕巢區','/1313131',null,1,1,1,null,null,TO_TIMESTAMP('2020-11-27 16:48:51','yyyy-mm-dd hh24:mi:ss'),TO_TIMESTAMP('2020-12-27 16:48:51','yyyy-mm-dd hh24:mi:ss'),'222222222222','005');
INSERT INTO MEMBER_INFO VALUES ('MEM' || lpad(MEM_SEQ.NEXTVAL,5, '0'),'YOYO','masi26@gmail.com','dddddd',0,0,TO_DATE('1991-09-19','YYYY-MM-DD'),'學生','新北市土城區','/1414141',null,1,1,1,null,null,TO_TIMESTAMP('2020-11-27 16:48:52','yyyy-mm-dd hh24:mi:ss'),TO_TIMESTAMP('2020-12-27 16:48:52','yyyy-mm-dd hh24:mi:ss'),'333333333333','006');
INSERT INTO MEMBER_INFO VALUES ('MEM' || lpad(MEM_SEQ.NEXTVAL,5, '0'),'海鮮','aa5op99aa@yahoo.com.tw','eeeeee',0,0,TO_DATE('1991-09-20','YYYY-MM-DD'),'學生','台北市信義區','/1515151',null,1,1,1,null,null,TO_TIMESTAMP('2020-11-27 16:48:53','yyyy-mm-dd hh24:mi:ss'),TO_TIMESTAMP('2020-12-27 16:48:53','yyyy-mm-dd hh24:mi:ss'),'444444444444','007');
INSERT INTO MEMBER_INFO VALUES ('MEM' || lpad(MEM_SEQ.NEXTVAL,5, '0'),'焦糖布丁','yusd933029@gmail.com','ffffff',0,0,TO_DATE('1991-09-21','YYYY-MM-DD'),'學生','台北市信義區','/1616161',null,1,1,1,null,null,TO_TIMESTAMP('2020-11-27 16:48:54','yyyy-mm-dd hh24:mi:ss'),TO_TIMESTAMP('2020-12-27 16:48:54','yyyy-mm-dd hh24:mi:ss'),'555555555555','008');
INSERT INTO MEMBER_INFO VALUES ('MEM' || lpad(MEM_SEQ.NEXTVAL,5, '0'),'柳丁好吃','haug3458@gmail.com','gggggg',0,0,TO_DATE('1991-09-22','YYYY-MM-DD'),'學生','新北市新莊區','/1717171',null,1,1,1,null,null,TO_TIMESTAMP('2020-11-27 16:48:55','yyyy-mm-dd hh24:mi:ss'),TO_TIMESTAMP('2020-12-27 16:48:55','yyyy-mm-dd hh24:mi:ss'),'666666666666','009');
INSERT INTO MEMBER_INFO VALUES ('MEM' || lpad(MEM_SEQ.NEXTVAL,5, '0'),'yesun','jsPO9650717@gmail.com','hhhhhh',0,0,TO_DATE('1991-09-23','YYYY-MM-DD'),'學生','台北市大安區','/1818181',null,1,1,1,null,null,TO_TIMESTAMP('2020-11-27 16:48:56','yyyy-mm-dd hh24:mi:ss'),TO_TIMESTAMP('2020-12-27 16:48:56','yyyy-mm-dd hh24:mi:ss'),'777777777777','010');
INSERT INTO MEMBER_INFO VALUES ('MEM' || lpad(MEM_SEQ.NEXTVAL,5, '0'),'MUMU','vPQWW98@gmail.com','iiiiii',0,0,TO_DATE('1991-09-24','YYYY-MM-DD'),'學生','桃園市中壢區','/1919191',null,1,1,1,null,null,TO_TIMESTAMP('2020-11-27 16:48:57','yyyy-mm-dd hh24:mi:ss'),TO_TIMESTAMP('2020-12-27 16:48:57','yyyy-mm-dd hh24:mi:ss'),'888888888888','011');
INSERT INTO MEMBER_INFO VALUES ('MEM' || lpad(MEM_SEQ.NEXTVAL,5, '0'),'MASA','petKK@gmail.com','jjjjjj',0,0,TO_DATE('1991-09-25','YYYY-MM-DD'),'學生','桃園市中壢區','/2020202',null,1,1,1,null,null,TO_TIMESTAMP('2020-11-27 16:48:58','yyyy-mm-dd hh24:mi:ss'),TO_TIMESTAMP('2020-12-27 16:48:58','yyyy-mm-dd hh24:mi:ss'),'999999999999','012');
INSERT INTO MEMBER_INFO VALUES ('MEM' || lpad(MEM_SEQ.NEXTVAL,5, '0'),'KAKA','muOLa11@gmail.com','kkkkkk',0,0,TO_DATE('1991-09-26','YYYY-MM-DD'),'學生','新北市中和區景新街347號8樓之8','/2121212',null,1,1,1,null,null,TO_TIMESTAMP('2020-11-27 16:48:59','yyyy-mm-dd hh24:mi:ss'),TO_TIMESTAMP('2020-12-27 16:48:59','yyyy-mm-dd hh24:mi:ss'),'101010101010','013');



--LOGID_SEQ  登入序列
CREATE SEQUENCE LOGID_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

--login_history登入歷程
create table login_history(
	primary key(login_ID),
	login_ID          varchar2(50)  not null	,
	login_time        Timestamp     not null ,
	login_arrange     varchar2(50)  not null,
	login_IP          varchar2(50)  not null,
	foreign key (member_id)REFERENCES member_info(member_id),
	member_id         varchar2(20)  not null
);
	
INSERT INTO login_history VALUES ('LOGIN' || lpad(LOGID_SEQ.NEXTVAL,5, '0'),TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),'IOS6','140.115.19.42','MEM00001');
INSERT INTO login_history VALUES ('LOGIN' || lpad(LOGID_SEQ.NEXTVAL,5, '0'),TO_TIMESTAMP('2020-11-29 19:48:51','yyyy-mm-dd hh24:mi:ss'),'CHROME','1.164.219.157','MEM00010');
INSERT INTO login_history VALUES ('LOGIN' || lpad(LOGID_SEQ.NEXTVAL,5, '0'),TO_TIMESTAMP('2020-11-30 19:48:51','yyyy-mm-dd hh24:mi:ss'),'IPHONE','140.124.88.50','MEM00005');
INSERT INTO login_history VALUES ('LOGIN' || lpad(LOGID_SEQ.NEXTVAL,5, '0'),TO_TIMESTAMP('2020-11-26 19:48:51','yyyy-mm-dd hh24:mi:ss'),'IOS6','140.115.19.42','MEM00001');
INSERT INTO login_history VALUES ('LOGIN' || lpad(LOGID_SEQ.NEXTVAL,5, '0'),TO_TIMESTAMP('2020-11-28 19:48:51','yyyy-mm-dd hh24:mi:ss'),'CHROME','1.164.219.157','MEM00010');
INSERT INTO login_history VALUES ('LOGIN' || lpad(LOGID_SEQ.NEXTVAL,5, '0'),TO_TIMESTAMP('2020-11-03 19:48:51','yyyy-mm-dd hh24:mi:ss'),'IPHONE','140.124.88.50','MEM00007');
INSERT INTO login_history VALUES ('LOGIN' || lpad(LOGID_SEQ.NEXTVAL,5, '0'),TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),'IOS6','140.115.19.42','MEM00001');
INSERT INTO login_history VALUES ('LOGIN' || lpad(LOGID_SEQ.NEXTVAL,5, '0'),TO_TIMESTAMP('2020-12-01 19:48:51','yyyy-mm-dd hh24:mi:ss'),'CHROME','1.164.219.157','MEM00008');
INSERT INTO login_history VALUES ('LOGIN' || lpad(LOGID_SEQ.NEXTVAL,5, '0'),TO_TIMESTAMP('2020-12-03 19:48:51','yyyy-mm-dd hh24:mi:ss'),'IPHONE','140.124.88.50','MEM00004');
INSERT INTO login_history VALUES ('LOGIN' || lpad(LOGID_SEQ.NEXTVAL,5, '0'),TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),'IOS6','140.115.19.42','MEM00001');
INSERT INTO login_history VALUES ('LOGIN' || lpad(LOGID_SEQ.NEXTVAL,5, '0'),TO_TIMESTAMP('2020-12-04 19:48:51','yyyy-mm-dd hh24:mi:ss'),'CHROME','1.164.219.157','MEM00006');
INSERT INTO login_history VALUES ('LOGIN' || lpad(LOGID_SEQ.NEXTVAL,5, '0'),TO_TIMESTAMP('2020-12-05 19:48:51','yyyy-mm-dd hh24:mi:ss'),'IPHONE','140.124.88.50','MEM00002');



--admin_list管理員資料序列
CREATE SEQUENCE admin_list_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;
   
   
--admin_list管理員資料
CREATE TABLE admin_list (
 primary key(admin_id),
 admin_id                          VARCHAR2(20) NOT NULL,
 admin_name                        VARCHAR2(50) NOT NULL,
 admin_pwd                         VARCHAR2(50) NOT NULL,
 admin_status                      NUMBER(1,0)  NOT NULL
 );

INSERT INTO admin_list VALUES ('AI' || lpad(admin_list_SEQ.NEXTVAL,5,'0'),'admin1','123456',1);
INSERT INTO admin_list VALUES ('AI' || lpad(admin_list_SEQ.NEXTVAL,5,'0'),'admin2','123456',1);
INSERT INTO admin_list VALUES ('AI' || lpad(admin_list_SEQ.NEXTVAL,5,'0'),'admin3','123456',1);
INSERT INTO admin_list VALUES ('AI' || lpad(admin_list_SEQ.NEXTVAL,5,'0'),'admin4','123456',1);
INSERT INTO admin_list VALUES ('AI' || lpad(admin_list_SEQ.NEXTVAL,5,'0'),'admin5','123456',1);
INSERT INTO admin_list VALUES ('AI' || lpad(admin_list_SEQ.NEXTVAL,5,'0'),'admin6','123456',1);
INSERT INTO admin_list VALUES ('AI' || lpad(admin_list_SEQ.NEXTVAL,5,'0'),'admin7','123456',1);
INSERT INTO admin_list VALUES ('AI' || lpad(admin_list_SEQ.NEXTVAL,5,'0'),'admin8','123456',1);
INSERT INTO admin_list VALUES ('AI' || lpad(admin_list_SEQ.NEXTVAL,5,'0'),'admin9','123456',1);
INSERT INTO admin_list VALUES ('AI' || lpad(admin_list_SEQ.NEXTVAL,5,'0'),'admin10','123456',1);

--auth_list 權限代號
create table auth_list(
primary key(auth_id),	
	auth_id varchar2(5) not null,
	auth_func varchar2(50) not null);
	
INSERT INTO auth_list VALUES ('A','審核課程權限');
INSERT INTO auth_list VALUES ('B','編輯課程權限');
INSERT INTO auth_list VALUES ('C','會員資料權限');
INSERT INTO auth_list VALUES ('D','訊息管理權限');
INSERT INTO auth_list VALUES ('E','修改管理員權限');


--admin_auth 管理員權限
create table admin_auth(
admin_id varchar2(20) not null,
auth_id varchar2(5) not null,
auth_status number(1,0) not null,
auth_update TIMESTAMP not null,
foreign key (admin_id)REFERENCES admin_list(admin_id),
foreign key (auth_id)REFERENCES auth_list(auth_id),
constraint admin_auth_PK primary key(admin_id , auth_id));

INSERT INTO admin_auth VALUES('AI00001','A','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00001','B','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00001','C','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00001','D','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00001','E','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00002','A','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00002','B','0',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00002','C','0',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00002','D','0',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00002','E','0',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00003','A','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00003','B','0',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00003','C','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00003','D','0',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00003','E','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00004','A','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00004','B','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00004','C','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00004','D','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00004','E','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00005','A','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00005','B','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00005','C','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00005','D','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00005','E','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00006','A','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00006','B','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00006','C','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00006','D','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00006','E','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00007','A','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00007','B','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00007','C','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00007','D','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00007','E','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00008','A','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00008','B','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00008','C','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00008','D','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00008','E','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00009','A','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00009','B','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00009','C','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00009','D','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00009','E','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00010','A','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00010','B','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00010','C','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00010','D','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));
INSERT INTO admin_auth VALUES('AI00010','E','1',TO_TIMESTAMP('2020-12-05 09:00:00','syyyy-mm-dd hh24:mi:ss'));

--課程主類別流水號
CREATE SEQUENCE MAIN_CLASS_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;
--課程主類別

CREATE TABLE MAIN_CLASS(
	PRIMARY KEY(MAINCLASS_ID),
	MAINCLASS_ID		VARCHAR2(20)	NOT NULL,
	MAINCLASS_NAME		VARCHAR2(50)	NOT NULL,
	MAINCLASS_STATUS	NUMBER(1,0)	 DEFAULT 1 NOT NULL
);

INSERT INTO MAIN_CLASS VALUES('MCI'||lPAD(MAIN_CLASS_SEQ.NEXTVAL,5,'0'),'語言',1);
INSERT INTO MAIN_CLASS VALUES('MCI'||lPAD(MAIN_CLASS_SEQ.NEXTVAL,5,'0'),'攝影',1);
INSERT INTO MAIN_CLASS VALUES('MCI'||lPAD(MAIN_CLASS_SEQ.NEXTVAL,5,'0'),'設計',1);
INSERT INTO MAIN_CLASS VALUES('MCI'||lPAD(MAIN_CLASS_SEQ.NEXTVAL,5,'0'),'人文',1);
INSERT INTO MAIN_CLASS VALUES('MCI'||lPAD(MAIN_CLASS_SEQ.NEXTVAL,5,'0'),'行銷',1);
INSERT INTO MAIN_CLASS VALUES('MCI'||lPAD(MAIN_CLASS_SEQ.NEXTVAL,5,'0'),'程式',1);



--課程副類別流水號
CREATE SEQUENCE SUB_CLASS_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

--課程副類別

CREATE TABLE SUB_CLASS(
	PRIMARY KEY(SUBCLASS_ID),
	FOREIGN KEY(MAINCLASS_ID)REFERENCES MAIN_CLASS(MAINCLASS_ID),
	SUBCLASS_ID		VARCHAR2(20)	NOT NULL,
	MAINCLASS_ID		VARCHAR2(20)	NOT NULL,
	SUBCLASS_NAME		VARCHAR2(50)	NOT NULL,
	SUBCLASS_STATUS	number(1,0)		DEFAULT 1 NOT NULL	
);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00001','英文',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00001','日文',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00001','韓文',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00001','西班牙文',1);


INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00002','影像創作',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00002','商業攝影',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00002','後製剪輯',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00002','動態攝影',1);

INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00003','動態設計',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00003','平面設計',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00003','應用設計',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00003','網頁設計',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00003','介面設計',1);

INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00004','文學',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00004','社會科學',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00004','心理',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00004','社會',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00004','法律',1);

INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00005','文案',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00005','數位行銷',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00005','社群行銷',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00005','區塊鏈',1);


INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00006','程式理財',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00006','程式入門',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00006','網站架設',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00006','手機程式開發',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00006','網頁前端',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00006','網頁後端',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00006','軟體程式開發與維護',1);
INSERT INTO SUB_CLASS VALUES('SCI'||lPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),'MCI00006','資訊安全',1);


--課程流水號
CREATE SEQUENCE class_info_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

--class_info課程資料
CREATE TABLE CLASS_INFO(
	PRIMARY KEY(class_ID),
	FOREIGN KEY(member_id) REFERENCES member_info(member_id),
	FOREIGN KEY(subclass_ID) REFERENCES SUB_CLASS(subclass_ID),
	FOREIGN KEY(admin_ID) REFERENCES admin_list(admin_ID),
	class_ID				VARCHAR2(20)	NOT NULL,
	member_id				VARCHAR2(20)	NOT NULL,
	subclass_ID			VARCHAR2(20)	NOT NULL,
	admin_ID				VARCHAR2(20)	,
	class_name				VARCHAR2(100)	,
	class_status			number(1,0)		DEFAULT 7 NOT NULL,
	startfund_date	Timestamp		,
	startclass_time		Timestamp		,
	class_description		CLOB			,
	class_picture			BLOB			,
	original_price    	    number(10,1)	,
	startfund_price	number(10,1)	,
	people_threshold		number(10,1)	,
	class_length			VARCHAR2(20)	,
	video_fundraising		Blob			,
	update_time				Timestamp		NOT NULL
);


--class_info課程資料假資料
--模擬用戶課程沒有填完送出狀態
INSERT INTO CLASS_INFO 
(class_ID,member_id,subclass_ID,admin_ID,class_name,update_time)
VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00008',   --會員編號
'SCI00001',   --課程副類別
'AI00006',    --更新人員
'測試測試測試測試測試測試測試 課程',   --課程名稱
TO_TIMESTAMP('2020-09-14 12:52:42','syyyy-mm-dd hh24:mi:ss')   --更新時間
);

INSERT INTO CLASS_INFO VALUES(
	'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
	'MEM00010',   --會員編號
	'SCI00024',   --課程副類別
	'AI00006',    --更新人員
	'C Programming For Beginners - Master the C Language',   --課程名稱
	6,   --狀態
	TO_TIMESTAMP('2019-03-05 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
	null,   --開課時間
	NULL,   --課程介紹
	NULL,   --圖片
	3440,  --早鳥
	4300,  --售價
	120,  --門檻人數
	'32:50:50',   --課程長度
	NULL,   --募資影片
	TO_TIMESTAMP('2019-03-05 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
	'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
	'MEM00004',   --會員編號
	'SCI00024',   --課程副類別
	'AI00006',    --更新人員
	'Java编程语言从入门到精通',   --課程名稱
	6,   --狀態
	TO_TIMESTAMP('2019-03-14 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
	null,   --開課時間
	NULL,   --課程介紹
	NULL,   --圖片
	3280,  --早鳥
	4100,  --售價
	90,  --門檻人數
	'20:10:10',   --課程長度
	NULL,   --募資影片
	TO_TIMESTAMP('2019-03-14 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00007',   --會員編號
'SCI00001',   --課程副類別
'AI00002',    --更新人員
'跟著 Ying 瘋句型・言之有物生活英文',   --課程名稱
6,   --狀態
TO_TIMESTAMP('2019-04-15 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
	null,   --開課時間
NULL,   --課程介紹
NULL,   --圖片
2560,  --早鳥
3200,  --售價
75,  --門檻人數
'30:12:12',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2019-04-15 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00007',   --會員編號
'SCI00001',   --課程副類別
'AI00006',    --更新人員
'英文小聊天・聊出人生大交集 | Small Talk 全心法',   --課程名稱
7,   --狀態
TO_TIMESTAMP('2019-04-08 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
	null,   --開課時間
NULL,   --課程介紹
NULL,   --圖片
4800,  --早鳥
6000,  --售價
50,  --門檻人數
'10:40:40',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2019-04-08 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00009',   --會員編號
'SCI00029',   --課程副類別
'AI00001',    --更新人員
'資料庫設計 - 有效的使用系統資料',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2019-05-13 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2019-06-13 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
6240,  --早鳥
7800,  --售價
80,  --門檻人數
'20:10:00',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2019-06-13 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00008',   --會員編號
'SCI00029',   --課程副類別
'AI00006',    --更新人員
'資料庫設計 - 有效的使用系統資料',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2019-05-14 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2019-06-14 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
6240,  --早鳥
7800,  --售價
80,  --門檻人數
'20:10:00',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2019-06-14 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00001',   --會員編號
'SCI00024',   --課程副類別
'AI00006',    --更新人員
'Unity 遊戲開發- [進階篇] 打造堆疊遊戲',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2019-06-15 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2019-07-15 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
6400,  --早鳥
8000,  --售價
80,  --門檻人數
'20:10:00',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2019-07-15 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00004',   --會員編號
'SCI00024',   --課程副類別
'AI00006',    --更新人員
'Github 免費架站術！輕鬆打造個人品牌',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2019-06-18 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2019-07-18 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
3680,  --早鳥
4600,  --售價
80,  --門檻人數
'18:10:00',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2019-07-18 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00004',   --會員編號
'SCI00024',   --課程副類別
'AI00002',    --更新人員
'JavaScript 程式設計新手村',   --課程名稱
6,   --狀態
TO_TIMESTAMP('2019-07-20 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
	null,   --開課時間
NULL,   --課程介紹
NULL,   --圖片
4640,  --早鳥
5800,  --售價
80,  --門檻人數
'10:10:00',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2019-07-20 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00003',   --會員編號
'SCI00024',   --課程副類別
'AI00006',    --更新人員
'Java 與 Kotlin 一起學 : 程式設計的起點',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2019-07-09 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2019-08-09 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
2080,  --早鳥
2600,  --售價
80,  --門檻人數
'20:10:00',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2019-08-09 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00001',   --會員編號
'SCI00011',   --課程副類別
'AI00001',    --更新人員
'我的設計超美感 – C4D 視覺密碼懶人包',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2019-08-04 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2019-09-04 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
4000,  --早鳥
5000,  --售價
110,  --門檻人數
'22:20:20',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2019-09-04 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00009',   --會員編號
'SCI00011',   --課程副類別
'AI00006',    --更新人員
'超強印象！人與動物的 logo 設計課',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2019-08-05 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2019-09-05 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
3440,  --早鳥
4300,  --售價
120,  --門檻人數
'32:50:50',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2019-09-05 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00005',   --會員編號
'SCI00002',   --課程副類別
'AI00003',    --更新人員
'商用日語：一次學好各種敬語及書信禮儀',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2019-09-14 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2019-10-14 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
3280,  --早鳥
4100,  --售價
90,  --門檻人數
'20:10:10',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2019-10-14 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00007',   --會員編號
'SCI00001',   --課程副類別
'AI00002',    --更新人員
'王梓沅的 3D 英文筆記術：打造最強「說」「寫」英語資料庫',   --課程名稱
6,   --狀態
TO_TIMESTAMP('2019-09-15 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
	null,   --開課時間
NULL,   --課程介紹
NULL,   --圖片
2560,  --早鳥
3200,  --售價
75,  --門檻人數
'30:12:12',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2019-09-15 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00003',   --會員編號
'SCI00029',   --課程副類別
'AI00006',    --更新人員
'提升工程師的科技力！AWS 雲端網站建置',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2019-10-08 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2019-11-08 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
6400,  --早鳥
8000,  --售價
60,  --門檻人數
'20:30:30',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2019-11-08 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00007',   --會員編號
'SCI00024',   --課程副類別
'AI00006',    --更新人員
'Python 3零基础完全入门',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2019-10-13 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2019-11-13 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
4640,  --早鳥
5800,  --售價
45,  --門檻人數
'44:50:50',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2019-11-13 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00005',   --會員編號
'SCI00001',   --課程副類別
'AI00001',    --更新人員
'不只為考試的人生英文單字課',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2019-11-14 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2019-12-14 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
6400,  --早鳥
8000,  --售價
60,  --門檻人數
'20:30:30',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2019-12-14 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00004',   --會員編號
'SCI00001',   --課程副類別
'AI00003',    --更新人員
'托福口說拿分 4 原則｜發音、表達一把罩',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2019-11-15 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2019-12-15 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
4640,  --早鳥
5800,  --售價
45,  --門檻人數
'44:50:50',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2019-12-15 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00004',   --會員編號
'SCI00025',   --課程副類別
'AI00002',    --更新人員
'Node.js 網站開發 with React.js',   --課程名稱
6,   --狀態
TO_TIMESTAMP('2019-12-09 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
	null,   --開課時間
NULL,   --課程介紹
NULL,   --圖片
6720,  --早鳥
8400,  --售價
80,  --門檻人數
'60:40:40',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2019-12-09 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00009',   --會員編號
'SCI00024',   --課程副類別
'AI00002',    --更新人員
'跟著商管女孩一起學 Python',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2019-12-04 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2019-02-04 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
5200,  --早鳥
6500,  --售價
130,  --門檻人數
'40:40:40',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2019-02-04 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00008',   --會員編號
'SCI00024',   --課程副類別
'AI00002',    --更新人員
'.NET 技術講座：打造堅固耐用的 C# 程式碼',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2020-01-18 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-03-18 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
3600,  --早鳥
4500,  --售價
150,  --門檻人數
'20:30:30',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-03-18 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00006',   --會員編號
'SCI00024',   --課程副類別
'AI00006',    --更新人員
'從零開始學 JAVA 程式設計',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2020-02-20 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-03-20 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
5200,  --早鳥
6500,  --售價
100,  --門檻人數
'34:20:20',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-03-20 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00005',   --會員編號
'SCI00024',   --課程副類別
'AI00006',    --更新人員
'Python基礎課程和網路爬蟲入門實戰',   --課程名稱
6,   --狀態
TO_TIMESTAMP('2020-03-09 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP(null,'syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
2720,  --早鳥
3400,  --售價
55,  --門檻人數
'21:10:10',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-03-09 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00001',   --會員編號
'SCI00025',   --課程副類別
'AI00001',    --更新人員
'JavaScript全端開發',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2020-03-09 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-04-09 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
2560,  --早鳥
3200,  --售價
70,  --門檻人數
'32:40:40',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-04-09 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00003',   --會員編號
'SCI00003',   --課程副類別
'AI00003',    --更新人員
'哥教的是語感・入門韓文',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2020-04-04 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-05-04 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
3520,  --早鳥
4400,  --售價
65,  --門檻人數
'60:30:30',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-05-04 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00008',   --會員編號
'SCI00003',   --課程副類別
'AI00006',    --更新人員
'MuMu 語學堂：韓國語 1A',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2020-04-05 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-05-05 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
1920,  --早鳥
2400,  --售價
80,  --門檻人數
'40:10:10',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-05-05 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00005',   --會員編號
'SCI00003',   --課程副類別
'AI00006',    --更新人員
'韓文初級文法全攻略・讓你征服生活會話',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2020-05-14 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-06-14 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
2000,  --早鳥
2500,  --售價
45,  --門檻人數
'25:50:50',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-06-14 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00006',   --會員編號
'SCI00004',   --課程副類別
'AI00003',    --更新人員
'與法文的初次邂逅・入門法文',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2020-05-15 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-06-15 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
6720,  --早鳥
8400,  --售價
80,  --門檻人數
'60:40:40',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-06-15 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00008',   --會員編號
'SCI00011',   --課程副類別
'AI00006',    --更新人員
'Seagate 講堂 | 動態導演林呈軒教你做履歷',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2020-06-08 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-07-08 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
5200,  --早鳥
6500,  --售價
130,  --門檻人數
'40:40:40',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-07-08 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00007',   --會員編號
'SCI00011',   --課程副類別
'AI00002',    --更新人員
'作品集必備！Mockup 模擬圖合成術',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2020-06-13 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-07-13 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
3600,  --早鳥
4500,  --售價
150,  --門檻人數
'20:30:30',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-07-13 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00006',   --會員編號
'SCI00011',   --課程副類別
'AI00006',    --更新人員
'Today at Apple:和設計師馮宇拆解商業 LOGO 案例',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2020-07-14 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-08-14 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
5200,  --早鳥
6500,  --售價
100,  --門檻人數
'34:20:20',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-08-14 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00003',   --會員編號
'SCI00003',   --課程副類別
'AI00003',    --更新人員
'追劇學韓文—韓語初級會話課程',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2020-07-15 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-08-15 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
2720,  --早鳥
3400,  --售價
55,  --門檻人數
'21:10:10',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-08-15 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00001',   --會員編號
'SCI00001',   --課程副類別
'AI00001',    --更新人員
'遠端工作必備：跨國英語 con-call 實戰力',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2020-08-18 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-09-18 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
2560,  --早鳥
3200,  --售價
70,  --門檻人數
'32:40:40',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-09-18 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00010',   --會員編號
'SCI00002',   --課程副類別
'AI00006',    --更新人員
'第一堂德語課：開啟德國留學之路',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2020-08-20 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-09-20 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
3520,  --早鳥
4400,  --售價
65,  --門檻人數
'60:30:30',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-09-20 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00008',   --會員編號
'SCI00001',   --課程副類別
'AI00006',    --更新人員
'職場英文大補帖｜上班族的跨國會議必修課',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2020-09-09 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-10-09 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
1920,  --早鳥
2400,  --售價
80,  --門檻人數
'40:10:10',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-10-09 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00006',   --會員編號
'SCI00024',   --課程副類別
'AI00006',    --更新人員
'Python 進階課程：觀念、語法、專案、爬蟲',   --課程名稱
4,   --狀態
TO_TIMESTAMP('2020-09-04 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-10-04 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
2000,  --早鳥
2500,  --售價
45,  --門檻人數
'25:50:50',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-10-04 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00005',   --會員編號
'SCI00024',   --課程副類別
'AI00001',    --更新人員
'R語言和文字探勘 - 洞悉巨量文字的商業價值',   --課程名稱
5,   --狀態
TO_TIMESTAMP('2020-10-05 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-11-05 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
5200,  --早鳥
6500,  --售價
90,  --門檻人數
'40:20:20',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-11-05 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00004',   --會員編號
'SCI00004',   --課程副類別
'AI00002',    --更新人員
'德文就是這麼簡單 | 入門德文一次搞定 (A1)',   --課程名稱
5,   --狀態
TO_TIMESTAMP('2020-10-14 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-11-14 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
2400,  --早鳥
3000,  --售價
60,  --門檻人數
'80:30:30',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-11-14 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00004',   --會員編號
'SCI00002',   --課程副類別
'AI00003',    --更新人員
'砍掉重練！跟著林老師，卡好日文文法！',   --課程名稱
5,   --狀態
TO_TIMESTAMP('2020-11-15 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-12-15 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
5200,  --早鳥
6500,  --售價
90,  --門檻人數
'40:20:20',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-12-15 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00009',   --會員編號
'SCI00001',   --課程副類別
'AI00006',    --更新人員
'跟 YouTuber 莫彩曦學美國道地的說話習慣',   --課程名稱
5,   --狀態
TO_TIMESTAMP('2020-11-09 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-12-09 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
2400,  --早鳥
3000,  --售價
60,  --門檻人數
'80:30:30',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-12-09 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00008',   --會員編號
'SCI00025',   --課程副類別
'AI00006',    --更新人員
'使用 jQuery 打造互動性網頁動畫效果',   --課程名稱
2,   --狀態
TO_TIMESTAMP('2020-12-14 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-01-14 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
6400,  --早鳥
8000,  --售價
100,  --門檻人數
'60:30:30',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-01-14 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00007',   --會員編號
'SCI00002',   --課程副類別
'AI00002',    --更新人員
'一期一會-日文一週速成班青少年專班LV2(LV1接續課程)',   --課程名稱
2,   --狀態
TO_TIMESTAMP('2020-12-15 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-01-15 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
4640,  --早鳥
5800,  --售價
55,  --門檻人數
'40:10:10',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-01-15 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00005',   --會員編號
'SCI00002',   --課程副類別
'AI00006',    --更新人員
'中高級日文聽說讀解班(程度:N3以上)',   --課程名稱
2,   --狀態
TO_TIMESTAMP('2020-12-16 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-01-16 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
6720,  --早鳥
8400,  --售價
70,  --門檻人數
'25:50:50',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-01-16 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00004',   --會員編號
'SCI00002',   --課程副類別
'AI00003',    --更新人員
'輕鬆學網路新聞日語(程度:N4)',   --課程名稱
2,   --狀態
TO_TIMESTAMP('2020-12-17 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-01-17 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
5200,  --早鳥
6500,  --售價
65,  --門檻人數
'60:40:40',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-01-17 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00004',   --會員編號
'SCI00002',   --課程副類別
'AI00001',    --更新人員
'會話歌曲學日語VIII(生活、旅遊會話篇)',   --課程名稱
2,   --狀態
TO_TIMESTAMP('2020-12-18 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-01-18 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
3600,  --早鳥
4500,  --售價
80,  --門檻人數
'40:40:40',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-01-18 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00009',   --會員編號
'SCI00002',   --課程副類別
'AI00006',    --更新人員
'日語基礎密集1A班(含50音教學)',   --課程名稱
2,   --狀態
TO_TIMESTAMP('2020-12-19 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2020-01-19 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
5200,  --早鳥
6500,  --售價
130,  --門檻人數
'20:30:30',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2020-01-19 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00008',   --會員編號
'SCI00002',   --課程副類別
'AI00006',    --更新人員
'輕鬆學好日語50音集訓班',   --課程名稱
1,   --狀態
TO_TIMESTAMP('2021-01-15 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2021-02-15 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
2720,  --早鳥
3400,  --售價
150,  --門檻人數
'34:20:20',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2021-01-15 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00006',   --會員編號
'SCI00003',   --課程副類別
'AI00006',    --更新人員
'韓語快易通基礎1',   --課程名稱
1,   --狀態
TO_TIMESTAMP('2021-01-18 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2021-02-18 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
2560,  --早鳥
3200,  --售價
100,  --門檻人數
'21:10:10',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2021-01-18 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00005',   --會員編號
'SCI00003',   --課程副類別
'AI00001',    --更新人員
'小班輕學習-韓語',   --課程名稱
1,   --狀態
TO_TIMESTAMP('2021-01-20 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2021-02-20 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
3520,  --早鳥
4400,  --售價
55,  --門檻人數
'32:40:40',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2021-01-20 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00001',   --會員編號
'SCI00003',   --課程副類別
'AI00002',    --更新人員
'韓語一點靈',   --課程名稱
1,   --狀態
TO_TIMESTAMP('2021-01-09 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2021-02-09 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
1920,  --早鳥
2400,  --售價
70,  --門檻人數
'60:30:30',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2021-01-09 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00003',   --會員編號
'SCI00003',   --課程副類別
'AI00006',    --更新人員
'LIVE韓語-TOPIK Ⅰ考試班(週六上午/4月考試)',   --課程名稱
1,   --狀態
TO_TIMESTAMP('2021-01-14 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2021-02-14 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
2000,  --早鳥
2500,  --售價
65,  --門檻人數
'40:10:10',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2021-01-14 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00008',   --會員編號
'SCI00003',   --課程副類別
'AI00003',    --更新人員
'HAHAHA KOREAN 基礎1A密集班(週一~五下午/發音開始)',   --課程名稱
1,   --狀態
TO_TIMESTAMP('2021-01-15 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2021-02-15 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
6720,  --早鳥
8400,  --售價
80,  --門檻人數
'25:50:50',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2021-01-15 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00005',   --會員編號
'SCI00011',   --課程副類別
'AI00001',    --更新人員
'俄羅斯學院人物肖像油畫',   --課程名稱
1,   --狀態
TO_TIMESTAMP('2021-01-09 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2021-02-09 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
5200,  --早鳥
6500,  --售價
45,  --門檻人數
'60:30:30',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2021-01-09 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00006',   --會員編號
'SCI00011',   --課程副類別
'AI00006',    --更新人員
'翰墨澄心',   --課程名稱
1,   --狀態
TO_TIMESTAMP('2021-01-04 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
TO_TIMESTAMP('2021-02-04 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --開課時間
NULL,   --課程介紹
NULL,   --圖片
6400,  --早鳥
8000,  --售價
80,  --門檻人數
'40:10:10',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2021-01-04 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00008',   --會員編號
'SCI00011',   --課程副類別
null,    --更新人員
'零基礎水彩-風景水彩與風景速寫',   --課程名稱
0,   --狀態
TO_TIMESTAMP('2021-01-18 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
	null,   --開課時間
NULL,   --課程介紹
NULL,   --圖片
4640,  --早鳥
5800,  --售價
80,  --門檻人數
'25:50:50',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2021-01-18 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00007',   --會員編號
'SCI00011',   --課程副類別
null,    --更新人員
'零基礎油畫-從最基礎打底學油畫',   --課程名稱
0,   --狀態
TO_TIMESTAMP('2021-01-19 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
	null,   --開課時間
NULL,   --課程介紹
NULL,   --圖片
6720,  --早鳥
8400,  --售價
130,  --門檻人數
'60:40:40',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2021-01-19 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00006',   --會員編號
'SCI00011',   --課程副類別
null,    --更新人員
'私房茶路- 品茗之道',   --課程名稱
0,   --狀態
TO_TIMESTAMP('2021-01-12 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
	null,   --開課時間
NULL,   --課程介紹
NULL,   --圖片
5200,  --早鳥
6500,  --售價
150,  --門檻人數
'40:40:40',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2021-01-12 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00003',   --會員編號
'SCI00011',   --課程副類別
null,    --更新人員
'私房茶路-普洱茶之旅',   --課程名稱
0,   --狀態
TO_TIMESTAMP('2021-01-21 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
	null,   --開課時間
NULL,   --課程介紹
NULL,   --圖片
3600,  --早鳥
4500,  --售價
100,  --門檻人數
'20:30:30',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2021-01-21 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);
INSERT INTO CLASS_INFO VALUES(
'CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),  --CLASS ID
'MEM00008',   --會員編號
'SCI00011',   --課程副類別
null,    --更新人員
'寫一手好字 -硬筆行書之美(週二晚間班)',   --課程名稱
0,   --狀態
TO_TIMESTAMP('2021-01-22 09:00:00','syyyy-mm-dd hh24:mi:ss'),   --募資時間
	null,   --開課時間
NULL,   --課程介紹
NULL,   --圖片
5200,  --早鳥
6500,  --售價
55,  --門檻人數
'34:20:20',   --課程長度
NULL,   --募資影片
TO_TIMESTAMP('2021-01-22 09:00:00','syyyy-mm-dd hh24:mi:ss')   --更新時間
);




--課程章節流水號
CREATE SEQUENCE CLASS_CHAPTER_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

--課程章節

CREATE TABLE CLASS_CHAPTER(
	PRIMARY KEY(CHAPTER_ID),
	FOREIGN KEY(CLASS_ID)REFERENCES CLASS_INFO(CLASS_ID),
	CHAPTER_ID			VARCHAR2(20)	NOT NULL,
	CLASS_ID			VARCHAR2(20)	NOT NULL,
	CHAPTER_NAME		VARCHAR2(50)		
);
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00002','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00002','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00002','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00002','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00002','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00003','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00003','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00003','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00003','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00003','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00001','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00001','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00001','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00001','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00001','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00002','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00002','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00002','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00002','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00002','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00003','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00003','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00003','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00003','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00003','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00004','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00004','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00004','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00004','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00004','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00005','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00005','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00005','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00005','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00005','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00006','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00006','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00006','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00006','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00006','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00007','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00007','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00007','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00007','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00007','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00008','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00008','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00008','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00008','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00008','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00009','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00009','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00009','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00009','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00009','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00010','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00010','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00010','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00010','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00010','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00011','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00011','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00011','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00011','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00011','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00012','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00012','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00012','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00012','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00012','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00013','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00013','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00013','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00013','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00013','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00014','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00014','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00014','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00014','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00014','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00015','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00015','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00015','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00015','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00015','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00016','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00016','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00016','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00016','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00016','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00017','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00017','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00017','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00017','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00017','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00018','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00018','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00018','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00018','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00018','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00019','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00019','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00019','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00019','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00019','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00020','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00020','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00020','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00020','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00020','章節五');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00021','章節一');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00021','章節二');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00021','章節三');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00021','章節四');
INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),'CLA00021','章節五');



--課程單元流水號
CREATE SEQUENCE CLASS_UNIT_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

--課程單元
CREATE TABLE CLASS_UNIT(
	PRIMARY KEY(UNIT_ID),
	FOREIGN KEY(CHAPTER_ID)REFERENCES CLASS_CHAPTER(CHAPTER_ID),
	UNIT_ID				VARCHAR2(20)	NOT NULL,
	CHAPTER_ID			VARCHAR2(20)	NOT NULL,
	UNIT_NAME			VARCHAR2(50)	NOT NULL,
	VIDEO				BLOB			,
	VIDEO_LONG			VARCHAR2(50)	,
	VIDEO_UPDATETIME	TIMESTAMP		,
	VIDEO_STATUS		NUMBER(1,0)		DEFAULT 0 NOT NULL	
);
INSERT INTO CLASS_UNIT VALUES('UNI'||lPAD(CLASS_UNIT_SEQ.NEXTVAL,5,'0'),'CLC00001','JAVA變數',NULL,'01:01:01',TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),0);
INSERT INTO CLASS_UNIT VALUES('UNI'||lPAD(CLASS_UNIT_SEQ.NEXTVAL,5,'0'),'CLC00001','JAVA繼承',NULL,'02:02:02',TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),0);
INSERT INTO CLASS_UNIT VALUES('UNI'||lPAD(CLASS_UNIT_SEQ.NEXTVAL,5,'0'),'CLC00001','JAVA介面',NULL,'03:03:03',TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),0);
INSERT INTO CLASS_UNIT VALUES('UNI'||lPAD(CLASS_UNIT_SEQ.NEXTVAL,5,'0'),'CLC00001','JAVA泛型',NULL,'04:04:04',TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),1);
INSERT INTO CLASS_UNIT VALUES('UNI'||lPAD(CLASS_UNIT_SEQ.NEXTVAL,5,'0'),'CLC00001','JAVA包裝',NULL,'05:05:05',TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),1);
INSERT INTO CLASS_UNIT VALUES('UNI'||lPAD(CLASS_UNIT_SEQ.NEXTVAL,5,'0'),'CLC00001','JAVA環境',NULL,'06:06:06',TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),0);
INSERT INTO CLASS_UNIT VALUES('UNI'||lPAD(CLASS_UNIT_SEQ.NEXTVAL,5,'0'),'CLC00001','JAVA驅動',NULL,'07:07:07',TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),0);
INSERT INTO CLASS_UNIT VALUES('UNI'||lPAD(CLASS_UNIT_SEQ.NEXTVAL,5,'0'),'CLC00001','JAVA多型',NULL,'08:08:08',TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),0);
INSERT INTO CLASS_UNIT VALUES('UNI'||lPAD(CLASS_UNIT_SEQ.NEXTVAL,5,'0'),'CLC00001','JAVA建構子',NULL,'09:09:09',TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),1);
INSERT INTO CLASS_UNIT VALUES('UNI'||lPAD(CLASS_UNIT_SEQ.NEXTVAL,5,'0'),'CLC00001','JAVA方法',NULL,'10:10:10',TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),1);
INSERT INTO CLASS_UNIT VALUES('UNI'||lPAD(CLASS_UNIT_SEQ.NEXTVAL,5,'0'),'CLC00001','單元1',NULL,'01:01:01',TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),0);
INSERT INTO CLASS_UNIT VALUES('UNI'||lPAD(CLASS_UNIT_SEQ.NEXTVAL,5,'0'),'CLC00001','單元2',NULL,'02:02:02',TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),0);
INSERT INTO CLASS_UNIT VALUES('UNI'||lPAD(CLASS_UNIT_SEQ.NEXTVAL,5,'0'),'CLC00001','單元3',NULL,'03:03:03',TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),0);
INSERT INTO CLASS_UNIT VALUES('UNI'||lPAD(CLASS_UNIT_SEQ.NEXTVAL,5,'0'),'CLC00001','單元4',NULL,'04:04:04',TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),1);
INSERT INTO CLASS_UNIT VALUES('UNI'||lPAD(CLASS_UNIT_SEQ.NEXTVAL,5,'0'),'CLC00001','單元5',NULL,'05:05:05',TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),1);


---------------------------------------------------------------

--作業題目流水號
CREATE SEQUENCE TEACHER_HOMEWORK_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

--作業題目
CREATE TABLE TEACHER_HOMEWORK(
	PRIMARY KEY(TEACHERHW_ID),
	FOREIGN KEY(UNIT_ID)REFERENCES CLASS_UNIT(UNIT_ID),
	TEACHERHW_ID		VARCHAR2(20)	NOT NULL,
	UNIT_ID				VARCHAR2(20)	NOT NULL,
	HW_NAME				VARCHAR2(20)	NOT NULL,
	HW_CONTENT			VARCHAR2(2000)	NOT NULL,
	FILE_DATA			BLOB			,
	HW_UPLOADTIME		TIMESTAMP		NOT NULL,
	HW_UPDATETIME		TIMESTAMP		NOT NULL
);
INSERT INTO TEACHER_HOMEWORK VALUES('TH'||lPAD(TEACHER_HOMEWORK_SEQ.NEXTVAL,5,'0'),'UNI00001','JAVA變數練習','THIS IS A HOMEWORK',NULL,TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'));
INSERT INTO TEACHER_HOMEWORK VALUES('TH'||lPAD(TEACHER_HOMEWORK_SEQ.NEXTVAL,5,'0'),'UNI00002','JAVA繼承練習','THIS IS A HOMEWORK',NULL,TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'));
INSERT INTO TEACHER_HOMEWORK VALUES('TH'||lPAD(TEACHER_HOMEWORK_SEQ.NEXTVAL,5,'0'),'UNI00003','JAVA介面練習','THIS IS A HOMEWORK',NULL,TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'));
INSERT INTO TEACHER_HOMEWORK VALUES('TH'||lPAD(TEACHER_HOMEWORK_SEQ.NEXTVAL,5,'0'),'UNI00004','JAVA泛型練習','THIS IS A HOMEWORK',NULL,TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'));
INSERT INTO TEACHER_HOMEWORK VALUES('TH'||lPAD(TEACHER_HOMEWORK_SEQ.NEXTVAL,5,'0'),'UNI00005','JAVA包裝練習','THIS IS A HOMEWORK',NULL,TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'));
INSERT INTO TEACHER_HOMEWORK VALUES('TH'||lPAD(TEACHER_HOMEWORK_SEQ.NEXTVAL,5,'0'),'UNI00006','JAVA環境練習','THIS IS A HOMEWORK',NULL,TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'));
INSERT INTO TEACHER_HOMEWORK VALUES('TH'||lPAD(TEACHER_HOMEWORK_SEQ.NEXTVAL,5,'0'),'UNI00007','JAVA驅動練習','THIS IS A HOMEWORK',NULL,TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'));
INSERT INTO TEACHER_HOMEWORK VALUES('TH'||lPAD(TEACHER_HOMEWORK_SEQ.NEXTVAL,5,'0'),'UNI00008','JAVA多型練習','THIS IS A HOMEWORK',NULL,TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'));
INSERT INTO TEACHER_HOMEWORK VALUES('TH'||lPAD(TEACHER_HOMEWORK_SEQ.NEXTVAL,5,'0'),'UNI00009','JAVA建構子練習','THIS IS A HOMEWORK',NULL,TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'));
INSERT INTO TEACHER_HOMEWORK VALUES('TH'||lPAD(TEACHER_HOMEWORK_SEQ.NEXTVAL,5,'0'),'UNI00010','JAVA方法練習','THIS IS A HOMEWORK',NULL,TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'));

---------------------------------------------------------------

--學生作業流水號
CREATE SEQUENCE STUDENT_HOMEWORK_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

--學生作業
CREATE TABLE STUDENT_HOMEWORK(
	PRIMARY KEY(STUDENTHW_ID),
	FOREIGN KEY(TEACHERHW_ID)REFERENCES TEACHER_HOMEWORK(TEACHERHW_ID),
	FOREIGN KEY(MEMBER_ID)REFERENCES MEMBER_INFO(MEMBER_ID),
	STUDENTHW_ID 		VARCHAR2(20) 	NOT NULL,
	TEACHERHW_ID		VARCHAR2(20)	NOT NULL,
	MEMBER_ID			VARCHAR2(50)	NOT NULL,
	FILE_DATA 			BLOB			,
	HW_UPLOADTIME 		TIMESTAMP		NOT NULL,
	HW_UPDATETIME 		TIMESTAMP		NOT NULL
);
INSERT INTO STUDENT_HOMEWORK VALUES('SH'||lPAD(STUDENT_HOMEWORK_SEQ.NEXTVAL,5,'0'),'TH00001','MEM00001',NULL,TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'));
INSERT INTO STUDENT_HOMEWORK VALUES('SH'||lPAD(STUDENT_HOMEWORK_SEQ.NEXTVAL,5,'0'),'TH00002','MEM00001',NULL,TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'));
INSERT INTO STUDENT_HOMEWORK VALUES('SH'||lPAD(STUDENT_HOMEWORK_SEQ.NEXTVAL,5,'0'),'TH00003','MEM00002',NULL,TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'));
INSERT INTO STUDENT_HOMEWORK VALUES('SH'||lPAD(STUDENT_HOMEWORK_SEQ.NEXTVAL,5,'0'),'TH00004','MEM00002',NULL,TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'));
INSERT INTO STUDENT_HOMEWORK VALUES('SH'||lPAD(STUDENT_HOMEWORK_SEQ.NEXTVAL,5,'0'),'TH00005','MEM00003',NULL,TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'));
INSERT INTO STUDENT_HOMEWORK VALUES('SH'||lPAD(STUDENT_HOMEWORK_SEQ.NEXTVAL,5,'0'),'TH00006','MEM00004',NULL,TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'));
INSERT INTO STUDENT_HOMEWORK VALUES('SH'||lPAD(STUDENT_HOMEWORK_SEQ.NEXTVAL,5,'0'),'TH00007','MEM00005',NULL,TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'));
INSERT INTO STUDENT_HOMEWORK VALUES('SH'||lPAD(STUDENT_HOMEWORK_SEQ.NEXTVAL,5,'0'),'TH00008','MEM00006',NULL,TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'));
INSERT INTO STUDENT_HOMEWORK VALUES('SH'||lPAD(STUDENT_HOMEWORK_SEQ.NEXTVAL,5,'0'),'TH00009','MEM00007',NULL,TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'));
INSERT INTO STUDENT_HOMEWORK VALUES('SH'||lPAD(STUDENT_HOMEWORK_SEQ.NEXTVAL,5,'0'),'TH00010','MEM00008',NULL,TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'),TO_TIMESTAMP('2011-09-14 12:52:42','syyyy-mm-dd hh:mi:ss'));



--coupon優惠券序列
CREATE SEQUENCE coupon_SQE --序列
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

	
--coupon優惠券
create table coupon(
	primary key(coupon_ID),
	coupon_ID  varchar2(20) not null,
	coupon_code varchar2(20) not null,
	coupon_amount number(5,0) not null,
	coupon_time Timestamp not null,
	coupon_expiry  Timestamp not null);
	
	
--coupon優惠券假資料
INSERT INTO coupon VALUES ('COU' || lpad(coupon_SQE.NEXTVAL,5, '0'),'123456',35,TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'));
INSERT INTO coupon VALUES ('COU' || lpad(coupon_SQE.NEXTVAL,5, '0'),'123456',35,TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'));
INSERT INTO coupon VALUES ('COU' || lpad(coupon_SQE.NEXTVAL,5, '0'),'123456',35,TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'));
INSERT INTO coupon VALUES ('COU' || lpad(coupon_SQE.NEXTVAL,5, '0'),'123456',35,TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'));
INSERT INTO coupon VALUES ('COU' || lpad(coupon_SQE.NEXTVAL,5, '0'),'123456',35,TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'));
INSERT INTO coupon VALUES ('COU' || lpad(coupon_SQE.NEXTVAL,5, '0'),'123456',35,TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'));
INSERT INTO coupon VALUES ('COU' || lpad(coupon_SQE.NEXTVAL,5, '0'),'123456',35,TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'));
INSERT INTO coupon VALUES ('COU' || lpad(coupon_SQE.NEXTVAL,5, '0'),'123456',35,TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'));
INSERT INTO coupon VALUES ('COU' || lpad(coupon_SQE.NEXTVAL,5, '0'),'123456',35,TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'));
INSERT INTO coupon VALUES ('COU' || lpad(coupon_SQE.NEXTVAL,5, '0'),'123456',35,TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'));
			


--order_info訂單序列
CREATE SEQUENCE order_infoSQE --序列
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;


--order_info訂單
create table order_info(
	primary key(order_ID),
	order_ID varchar2(20) not null	,
	member_id varchar2(20) not null	, --FK 
	order_time Timestamp not null	,
	payment_time Timestamp not null	,
	pay_way varchar2(10)  not null  ,
	order_status number(1,0)DEFAULT 1 not null,
	coupon_ID  varchar2(50),
	amount number(9,0) not null,
	foreign key (member_id)REFERENCES member_info(member_id),
	foreign key (coupon_ID)REFERENCES coupon(coupon_ID)
    );
	
--order_info 假資料	
INSERT INTO order_info (order_ID,member_id,order_time,payment_time,pay_way,order_status,coupon_ID,amount) VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00011',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'信用卡',1,'COU00001',5555);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00011',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'信用卡',1,'COU00001',5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00012',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'匯款',1,'COU00002',5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00013',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'信用卡',1,'COU00003',5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00014',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'匯款',1,null,5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00015',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'信用卡',1,'COU00005',5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00016',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'匯款',1,'COU00006',5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00017',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'信用卡',1,'COU00007',5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00018',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'匯款',1,null,5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00019',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'信用卡',1,'COU00009',5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00011',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'信用卡',1,null,5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00012',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'匯款',1,'COU00002',5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00013',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'信用卡',1,'COU00003',5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00014',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'匯款',1,'COU00004',5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00015',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'信用卡',1,null,5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00016',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'匯款',1,'COU00006',5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00017',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'信用卡',1,null,5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00018',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'匯款',1,'COU00008',5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00019',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'信用卡',1,'COU00009',5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00011',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'早鳥',1,'COU00009',5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00012',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'原價',1,'COU00009',5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00013',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'早鳥',1,'COU00009',5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00014',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'原價',1,null,5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00015',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'早鳥',1,'COU00009',5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00016',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'原價',1,null,5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00017',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'早鳥',1,'COU00009',5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00018',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'原價',1,null,5000);
INSERT INTO order_info VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'),'MEM00019',TO_DATE('2020-11-27','YYYY-MM-DD'),TO_DATE('2020-11-26','YYYY-MM-DD'),'早鳥',1,'COU00009',5000);


-- order_list 訂單明細序列
CREATE SEQUENCE order_list_SQE --序列
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;
      

--order_list課程明細
create table order_list(
	primary key(order_list_id),
	order_list_id varchar2(50) not null	,
	order_id varchar2(20) not null , --FK
	class_id varchar2(20) not null,  --FK
	purchase_plan varchar2(20) DEFAULT '募資價' not null,
	foreign key (class_id)REFERENCES CLASS_INFO(class_id),
 	foreign key (order_id)REFERENCES order_info(order_ID)
	);
	


--order_list課程明細 假資料
INSERT INTO order_list (order_list_id,order_id,class_id) VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00001','CLA00005');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00001','CLA00036','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00001','CLA00032','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00002','CLA00036','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00002','CLA00037','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00003','CLA00034','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00003','CLA00034','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00004','CLA00031','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00004','CLA00017','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00005','CLA00023','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00005','CLA00018','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00005','CLA00014','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00006','CLA00009','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00006','CLA00017','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00006','CLA00031','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00007','CLA00008','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00007','CLA00053','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00007','CLA00055','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00008','CLA00053','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00008','CLA00027','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00009','CLA00011','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00009','CLA00051','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00010','CLA00014','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00010','CLA00051','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00011','CLA00013','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00011','CLA00042','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00011','CLA00016','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00012','CLA00034','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00012','CLA00031','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00013','CLA00043','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00013','CLA00035','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00013','CLA00007','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00014','CLA00032','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00014','CLA00053','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00014','CLA00050','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00015','CLA00019','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00015','CLA00055','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00016','CLA00054','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00016','CLA00052','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00017','CLA00053','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00017','CLA00014','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00017','CLA00035','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00018','CLA00013','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00018','CLA00030','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00019','CLA00055','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00019','CLA00017','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00019','CLA00030','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00020','CLA00046','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00020','CLA00023','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00020','CLA00026','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00021','CLA00044','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00021','CLA00008','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00021','CLA00018','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00022','CLA00048','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00022','CLA00026','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00022','CLA00017','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00023','CLA00014','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00023','CLA00007','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00024','CLA00017','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00024','CLA00044','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00024','CLA00022','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00025','CLA00030','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00025','CLA00027','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00026','CLA00009','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00026','CLA00050','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00026','CLA00026','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00027','CLA00033','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00027','CLA00035','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00027','CLA00008','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00028','CLA00013','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00028','CLA00053','募資價');
INSERT INTO order_list VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'),'OID00028','CLA00022','募資價');





--video_record觀看紀錄時間表  
--record_id,class_last,video_update_time ,member_id,unit_id	 
create table video_record (
	foreign key (member_id)REFERENCES member_info(member_id),
	foreign key (unit_id)REFERENCES class_unit(unit_id),
	primary key(record_id ),	
	record_id varchar2(20) not null,
	class_last varchar2(50) not null,
	video_updatetime Timestamp not null,
	member_id  varchar2(20) not null,
	unit_id	varchar2(50) not null
);

--序列產生器 create sequence 產生器名稱
create sequence video_record_CS   --名稱
start with 1 --起始值
increment by 1 --每次增加多少
minvalue 1    --最小值
maxvalue 9999999999999    --最大值
nocache --不設緩存
nocycle ;    --不循環  
	
--video_record 資料表
INSERT INTO video_record  VALUES('VR'|| lpad(video_record_CS.NEXTVAL,5, '0'),'00:01:00', TO_TIMESTAMP('2012-11-11 12:10:10','yyyy-MM-dd HH24:mi:ss') ,'MEM00001','UNI00001');
INSERT INTO video_record  VALUES('VR'|| lpad(video_record_CS.NEXTVAL,5, '0'),'00:01:00', TO_TIMESTAMP('2012-12-11 12:10:10','yyyy-MM-dd HH24:mi:ss') ,'MEM00002','UNI00002');
INSERT INTO video_record  VALUES('VR'|| lpad(video_record_CS.NEXTVAL,5, '0'),'00:01:01', TO_TIMESTAMP('2012-08-11 12:10:10','yyyy-MM-dd HH24:mi:ss') ,'MEM00003','UNI00003');
INSERT INTO video_record  VALUES('VR'|| lpad(video_record_CS.NEXTVAL,5, '0'),'00:01:01', TO_TIMESTAMP('2013-10-20 12:10:10','yyyy-MM-dd HH24:mi:ss') ,'MEM00004','UNI00004');
INSERT INTO video_record  VALUES('VR'|| lpad(video_record_CS.NEXTVAL,5, '0'),'00:01:01', TO_TIMESTAMP('2019-09-11 12:10:10','yyyy-MM-dd HH24:mi:ss') ,'MEM00005','UNI00005');
INSERT INTO video_record  VALUES('VR'|| lpad(video_record_CS.NEXTVAL,5, '0'),'00:01:01', TO_TIMESTAMP('2020-05-20 12:10:10','yyyy-MM-dd HH24:mi:ss') ,'MEM00006','UNI00006');
INSERT INTO video_record  VALUES('VR'|| lpad(video_record_CS.NEXTVAL,5, '0'),'00:01:01', TO_TIMESTAMP('2008-03-11 12:10:10','yyyy-MM-dd HH24:mi:ss') ,'MEM00007','UNI00007');
INSERT INTO video_record  VALUES('VR'|| lpad(video_record_CS.NEXTVAL,5, '0'),'00:01:01', TO_TIMESTAMP('2007-01-23 12:10:10','yyyy-MM-dd HH24:mi:ss') ,'MEM00008','UNI00008');
INSERT INTO video_record  VALUES('VR'|| lpad(video_record_CS.NEXTVAL,5, '0'),'00:01:01', TO_TIMESTAMP('2010-12-11 12:10:10','yyyy-MM-dd HH24:mi:ss') ,'MEM00009','UNI00009');
INSERT INTO video_record  VALUES('VR'|| lpad(video_record_CS.NEXTVAL,5, '0'),'00:01:01', TO_TIMESTAMP('2011-09-12 12:10:10','yyyy-MM-dd HH24:mi:ss') ,'MEM00010','UNI00010');



--隨堂考試流水號
CREATE SEQUENCE TEST_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;


--隨堂考試

CREATE TABLE TEST(
	PRIMARY KEY(TEST_ID),
	FOREIGN KEY(UNIT_ID)REFERENCES CLASS_UNIT(UNIT_ID),
	TEST_ID				VARCHAR2(50)	NOT NULL,
	UNIT_ID				VARCHAR2(20)	NOT NULL,
	TEST_ANSWER			VARCHAR2(50)	NOT NULL,
	TEST_CONTENT		VARCHAR2(2000)	NOT NULL,
	opta		        VARCHAR2(200)	NOT NULL,
	optb		        VARCHAR2(200)	NOT NULL,
	optc		        VARCHAR2(200)	NOT NULL,
	optd		        VARCHAR2(200)	NOT NULL	
);
INSERT INTO TEST VALUES('TU'||lPAD(TEST_SEQ.NEXTVAL,5,'0'),'UNI00001','A','螞蟻能夠安全過冬是因為bai螞蟻儲存了足夠的食物。而蟋蟀因為沒有儲存食物，冬天裡被餓死、凍死的不計其數。那麼，是什麼原因讓蟋蟀沒能儲存食物呢？','沒有倉庫','演出去了','打仗去了','睡著惹');
INSERT INTO TEST VALUES('TU'||lPAD(TEST_SEQ.NEXTVAL,5,'0'),'UNI00002','B','為什麼關羽比張飛死的早？','關羽比張飛年長','關羽比張飛好強','關羽比張飛臉紅','關羽比張飛臉黑');
INSERT INTO TEST VALUES('TU'||lPAD(TEST_SEQ.NEXTVAL,5,'0'),'UNI00003','C','張三釀得好酒，酒香飄四方。但他的酒往往因賣不出去而變酸。他的酒為什麼賣不出去？','他家有惡狗','他家太偏僻','他的酒太貴','他的酒太便宜');
INSERT INTO TEST VALUES('TU'||lPAD(TEST_SEQ.NEXTVAL,5,'0'),'UNI00004','D','人到中年才相識”指的是','以前不認識','朋友少','夾生飯','夾碗豆');
INSERT INTO TEST VALUES('TU'||lPAD(TEST_SEQ.NEXTVAL,5,'0'),'UNI00005','A','一條蜈蚣過了一個臭水溝後有幾隻腳沒濕？','2','3','4','0');
INSERT INTO TEST VALUES('TU'||lPAD(TEST_SEQ.NEXTVAL,5,'0'),'UNI00006','A','螞蟻能夠安全過冬是因為bai螞蟻儲存了足夠的食物。而蟋蟀因為沒有儲存食物，冬天裡被餓死、凍死的不計其數。那麼，是什麼原因讓蟋蟀沒能儲存食物呢？','沒有倉庫','演出去了','打仗去了','睡著惹');
INSERT INTO TEST VALUES('TU'||lPAD(TEST_SEQ.NEXTVAL,5,'0'),'UNI00007','B','為什麼關羽比張飛死的早？','關羽比張飛年長','關羽比張飛好強','關羽比張飛臉紅','關羽比張飛臉黑');
INSERT INTO TEST VALUES('TU'||lPAD(TEST_SEQ.NEXTVAL,5,'0'),'UNI00008','C','張三釀得好酒，酒香飄四方。但他的酒往往因賣不出去而變酸。他的酒為什麼賣不出去？','他家有惡狗','他家太偏僻','他的酒太貴','他的酒太便宜');
INSERT INTO TEST VALUES('TU'||lPAD(TEST_SEQ.NEXTVAL,5,'0'),'UNI00009','D','人到中年才相識”指的是','以前不認識','朋友少','夾生飯','夾碗豆');
INSERT INTO TEST VALUES('TU'||lPAD(TEST_SEQ.NEXTVAL,5,'0'),'UNI00010','A','一條蜈蚣過了一個臭水溝後有幾隻腳沒濕？','2','3','4','0');



--MEBIN_SEQ會員興趣序列
CREATE SEQUENCE MEBIN_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

--member_interest 會員興趣
CREATE TABLE member_interest(
primary key(interest_id),	
  interest_id       VARCHAR2(20) NOT NULL,
  member_id         VARCHAR2(20) NOT NULL,
  subclass_ID      VARCHAR2(20) NOT NULL,
  interest_status   NUMBER(1,0) DEFAULT 1 NOT NULL,
  foreign key (member_id) REFERENCES member_info (member_id) ,
  foreign key (subclass_ID) REFERENCES sub_class (subclass_ID)
	);

INSERT INTO member_interest (interest_id,member_id,subclass_ID,interest_status)VALUES ('MEBIN' || lpad(MEBIN_SEQ.NEXTVAL,5, '0'),'MEM00001','SCI00001','1');
--讓系統知道是那4個要放值

INSERT INTO member_interest VALUES ('MEBIN' || lpad(MEBIN_SEQ.NEXTVAL,5,'0'),'MEM00002','SCI00003','1');
INSERT INTO member_interest VALUES ('MEBIN' || lpad(MEBIN_SEQ.NEXTVAL,5,'0'),'MEM00003','SCI00005','1');
INSERT INTO member_interest VALUES ('MEBIN' || lpad(MEBIN_SEQ.NEXTVAL,5,'0'),'MEM00004','SCI00007','1');
INSERT INTO member_interest VALUES ('MEBIN' || lpad(MEBIN_SEQ.NEXTVAL,5,'0'),'MEM00005','SCI00009','1');
INSERT INTO member_interest VALUES ('MEBIN' || lpad(MEBIN_SEQ.NEXTVAL,5,'0'),'MEM00006','SCI00011','0');
INSERT INTO member_interest VALUES ('MEBIN' || lpad(MEBIN_SEQ.NEXTVAL,5,'0'),'MEM00007','SCI00013','0');
INSERT INTO member_interest VALUES ('MEBIN' || lpad(MEBIN_SEQ.NEXTVAL,5,'0'),'MEM00008','SCI00025','0');
INSERT INTO member_interest VALUES ('MEBIN' || lpad(MEBIN_SEQ.NEXTVAL,5,'0'),'MEM00009','SCI00028','0');
INSERT INTO member_interest VALUES ('MEBIN' || lpad(MEBIN_SEQ.NEXTVAL,5,'0'),'MEM00010','SCI00030','0');


--KEYI_SEQ關鍵字排行表格序列
CREATE SEQUENCE KEYI_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;


--keyword_form關鍵字排行表格
CREATE TABLE keyword_form(
primary key(keyword_id),	
  keyword_id       VARCHAR2(20)  NOT NULL,
  member_id        VARCHAR2(20)  NOT NULL,
  search_date      date          NOT NULL,
  search_word      VARCHAR2(100) NOT NULL,
  foreign key (member_id) REFERENCES member_info (member_id)
	);

INSERT INTO keyword_form (keyword_id,member_id,search_date,search_word)VALUES ('KEYI' || lpad(KEYI_SEQ.NEXTVAL,5, '0'),'MEM00001',TO_DATE('2020-10-10','YYYY-MM-DD'),'java');
--讓系統知道是那4個要放值

INSERT INTO keyword_form VALUES ('KEYI' || lpad(KEYI_SEQ.NEXTVAL,5,'0'),'MEM00002',TO_DATE('2020-10-10','YYYY-MM-DD'),'免費');
INSERT INTO keyword_form VALUES ('KEYI' || lpad(KEYI_SEQ.NEXTVAL,5,'0'),'MEM00003',TO_DATE('2020-10-11','YYYY-MM-DD'),'javascript');
INSERT INTO keyword_form VALUES ('KEYI' || lpad(KEYI_SEQ.NEXTVAL,5,'0'),'MEM00004',TO_DATE('2020-10-13','YYYY-MM-DD'),'英文');
INSERT INTO keyword_form VALUES ('KEYI' || lpad(KEYI_SEQ.NEXTVAL,5,'0'),'MEM00005',TO_DATE('2020-10-15','YYYY-MM-DD'),'炒飯');
INSERT INTO keyword_form VALUES ('KEYI' || lpad(KEYI_SEQ.NEXTVAL,5,'0'),'MEM00006',TO_DATE('2020-10-15','YYYY-MM-DD'),'java');
INSERT INTO keyword_form VALUES ('KEYI' || lpad(KEYI_SEQ.NEXTVAL,5,'0'),'MEM00007',TO_DATE('2020-10-15','YYYY-MM-DD'),'vue');
INSERT INTO keyword_form VALUES ('KEYI' || lpad(KEYI_SEQ.NEXTVAL,5,'0'),'MEM00008',TO_DATE('2020-10-18','YYYY-MM-DD'),'試看');
INSERT INTO keyword_form VALUES ('KEYI' || lpad(KEYI_SEQ.NEXTVAL,5,'0'),'MEM00009',TO_DATE('2020-10-19','YYYY-MM-DD'),'jsp');
INSERT INTO keyword_form VALUES ('KEYI' || lpad(KEYI_SEQ.NEXTVAL,5,'0'),'MEM00010',TO_DATE('2020-10-20','YYYY-MM-DD'),'spring');



 --main_message_SEQ 大留言序列
 CREATE SEQUENCE main_message_SEQ --序列指令--
 INCREMENT BY 1
 START WITH 1 --設定起始值--
 NOMAXVALUE --設定遞增量--
 NOCYCLE --不循環--
 NOCACHE;
 
 --main_message大留言資料表--
create table main_message(
	primary key(mainmsg_id),	
	foreign key (class_id )REFERENCES class_info(class_id), 
	foreign key (member_id)REFERENCES member_info(member_id),
	mainmsg_id varchar2(20) not null,
	class_id varchar2(20) not null,
	member_id varchar2(20) not null,
	mainmsg_time timestamp not null,
	mainmsg_text varchar2(2000) not null,
	msg_source varchar2(50) not null,
	msg_status number(1,0) default 1 not null
	);
	
	
INSERT INTO main_message (mainmsg_id,class_id,member_id,mainmsg_time,mainmsg_text,msg_source)VALUES ('MM' || lpad(main_message_SEQ.NEXTVAL,5, '0'),'CLA00001','MEM00001',TO_DATE('2020-11-27 06:13','YYYY-MM-DD HH24:mi:ss'),'已經開課了嗎','課程頁面');
--讓系統知道是那6個要放值

INSERT INTO main_message VALUES ('MM' || lpad(main_message_SEQ.NEXTVAL,5,'0'),'CLA00002','MEM00002',TO_TIMESTAMP('2012-11-11 12:10:10','yyyy-MM-dd HH24:mi:ss'),'可以學到什麼?','課程頁面',1);
INSERT INTO main_message VALUES ('MM' || lpad(main_message_SEQ.NEXTVAL,5,'0'),'CLA00003','MEM00003',TO_TIMESTAMP('2012-11-11 12:10:10','yyyy-MM-dd HH24:mi:ss'),'課程裡面有包含什麼課程','學習頁面',1);
INSERT INTO main_message VALUES ('MM' || lpad(main_message_SEQ.NEXTVAL,5,'0'),'CLA00004','MEM00004',TO_TIMESTAMP('2012-11-11 12:10:10','yyyy-MM-dd HH24:mi:ss'),'適合沒學過的人嗎?','課程頁面',1);
INSERT INTO main_message VALUES ('MM' || lpad(main_message_SEQ.NEXTVAL,5,'0'),'CLA00005','MEM00005',TO_TIMESTAMP('2012-11-11 12:10:10','yyyy-MM-dd HH24:mi:ss'),'有學過的人有適合嗎?','學習頁面',1);
INSERT INTO main_message VALUES ('MM' || lpad(main_message_SEQ.NEXTVAL,5,'0'),'CLA00006','MEM00006',TO_TIMESTAMP('2012-11-11 12:10:10','yyyy-MM-dd HH24:mi:ss'),'有幾個章節?','課程頁面',1);
INSERT INTO main_message VALUES ('MM' || lpad(main_message_SEQ.NEXTVAL,5,'0'),'CLA00007','MEM00007',TO_TIMESTAMP('2012-11-11 12:10:10','yyyy-MM-dd HH24:mi:ss'),'這位老師還有出其他的課程嗎?','課程頁面',1);
INSERT INTO main_message VALUES ('MM' || lpad(main_message_SEQ.NEXTVAL,5,'0'),'CLA00008','MEM00008',TO_TIMESTAMP('2012-11-11 12:10:10','yyyy-MM-dd HH24:mi:ss'),'難易度?','學習頁面',1);
INSERT INTO main_message VALUES ('MM' || lpad(main_message_SEQ.NEXTVAL,5,'0'),'CLA00009','MEM00009',TO_TIMESTAMP('2012-11-11 12:10:10','yyyy-MM-dd HH24:mi:ss'),'需要先學會什麼嗎?','課程頁面',1);
INSERT INTO main_message VALUES ('MM' || lpad(main_message_SEQ.NEXTVAL,5,'0'),'CLA00010','MEM00010',TO_TIMESTAMP('2012-11-11 12:10:10','yyyy-MM-dd HH24:mi:ss'),'你有在嗎','學習頁面',1);


	


 --sub_message_SEQ 小留言序列
 CREATE SEQUENCE sub_message_SEQ --序列指令
 INCREMENT BY 1
 START WITH 1 --設定起始值
 NOMAXVALUE --設定遞增量
 NOCYCLE --不循環
 NOCACHE;
 
 --sub_message小留言資料表
create table sub_message(
	primary key(submsg_id),
	foreign key (mainmsg_id)REFERENCES main_message(mainmsg_id),
	foreign key (member_id)REFERENCES member_info(member_id),
	submsg_id varchar2(20) not null,
        mainmsg_id varchar2(20) not null,
	member_id varchar2(20) not null,
	submsg_time timestamp not null,
	submsg_text varchar2(2000) not null,
	submsg_status number(1,0) default 1 not null
	
	);

INSERT INTO sub_message (submsg_id,mainmsg_id,member_id,submsg_time,submsg_text)VALUES ('SM' || lpad(sub_message_SEQ.NEXTVAL,5,'0'),'MM00001','MEM00001',TO_TIMESTAMP('2020-11-27 06:13:00','YYYY-MM-DD HH24:mi:ss'),'大推這個課程');
--讓系統知道是那6個要放值

INSERT INTO sub_message VALUES ('SM' || lpad(sub_message_SEQ.NEXTVAL,5,'0'),'MM00002','MEM00002',TO_TIMESTAMP('2020-11-27 06:13:00','YYYY-MM-DD HH24:mi:ss'),'CP值很高',1);
INSERT INTO sub_message VALUES ('SM' || lpad(sub_message_SEQ.NEXTVAL,5,'0'),'MM00003','MEM00003',TO_TIMESTAMP('2020-11-27 06:13:00','YYYY-MM-DD HH24:mi:ss'),'老師很會教',1);
INSERT INTO sub_message VALUES ('SM' || lpad(sub_message_SEQ.NEXTVAL,5,'0'),'MM00004','MEM00004',TO_TIMESTAMP('2020-11-27 06:13:00','YYYY-MM-DD HH24:mi:ss'),'淺顯易懂',1);
INSERT INTO sub_message VALUES ('SM' || lpad(sub_message_SEQ.NEXTVAL,5,'0'),'MM00005','MEM00005',TO_TIMESTAMP('2020-11-27 06:13:00','YYYY-MM-DD HH24:mi:ss'),'教得不好',1);
INSERT INTO sub_message VALUES ('SM' || lpad(sub_message_SEQ.NEXTVAL,5,'0'),'MM00006','MEM00006',TO_TIMESTAMP('2020-11-27 06:13:00','YYYY-MM-DD HH24:mi:ss'),'課程太雷',1);
INSERT INTO sub_message VALUES ('SM' || lpad(sub_message_SEQ.NEXTVAL,5,'0'),'MM00007','MEM00007',TO_TIMESTAMP('2020-11-27 06:13:00','YYYY-MM-DD HH24:mi:ss'),'沒有重點',1);
INSERT INTO sub_message VALUES ('SM' || lpad(sub_message_SEQ.NEXTVAL,5,'0'),'MM00008','MEM00008',TO_TIMESTAMP('2020-11-27 06:13:00','YYYY-MM-DD HH24:mi:ss'),'邏輯很好',1);
INSERT INTO sub_message VALUES ('SM' || lpad(sub_message_SEQ.NEXTVAL,5,'0'),'MM00009','MEM00009',TO_TIMESTAMP('2020-11-27 06:13:00','YYYY-MM-DD HH24:mi:ss'),'一點就通',1);
INSERT INTO sub_message VALUES ('SM' || lpad(sub_message_SEQ.NEXTVAL,5,'0'),'MM00010','MEM00010',TO_TIMESTAMP('2020-11-27 06:13:00','YYYY-MM-DD HH24:mi:ss'),'有學跟沒學一樣',1);


 -- post_message_SEQ 公告訊息序列
 CREATE SEQUENCE post_message_SEQ --序列指令
 INCREMENT BY 1
 START WITH 1 --設定起始值
 NOMAXVALUE --設定遞增量
 NOCYCLE --不循環
 NOCACHE;
 
 --post_message公告訊息
create table post_message (
	primary key(post_id),
	foreign key (admin_id)REFERENCES admin_list(admin_id),
	post_id varchar2(20) not null,
	post_content varchar2(2000) not null,
	send_time Timestamp not null,
	admin_id varchar2(20) not null,
	target_type varchar2(50) default 1 not null
	);
	
	
INSERT INTO post_message (post_id,post_content,send_time,admin_id)VALUES ('PI' || lpad(post_message_SEQ.NEXTVAL,5,'0'),'發放優惠卷',TO_TIMESTAMP('2020-11-27 06:13:00','YYYY-MM-DD HH24:mi:ss'),'AI00001');
--讓系統知道是那6個要放值

INSERT INTO post_message VALUES ('PI' || lpad(post_message_SEQ.NEXTVAL,5,'0'),'發放優惠卷',TO_TIMESTAMP('2020-11-27 06:13:00','YYYY-MM-DD HH24:mi:ss'),'AI00002',1);
INSERT INTO post_message VALUES ('PI' || lpad(post_message_SEQ.NEXTVAL,5,'0'),'發放優惠卷',TO_TIMESTAMP('2020-11-27 06:13:00','YYYY-MM-DD HH24:mi:ss'),'AI00003',1);
INSERT INTO post_message VALUES ('PI' || lpad(post_message_SEQ.NEXTVAL,5,'0'),'發放優惠卷',TO_TIMESTAMP('2020-11-27 06:13:00','YYYY-MM-DD HH24:mi:ss'),'AI00004',1);
INSERT INTO post_message VALUES ('PI' || lpad(post_message_SEQ.NEXTVAL,5,'0'),'發放優惠卷',TO_TIMESTAMP('2020-11-27 06:13:00','YYYY-MM-DD HH24:mi:ss'),'AI00005',1);
INSERT INTO post_message VALUES ('PI' || lpad(post_message_SEQ.NEXTVAL,5,'0'),'發放優惠卷',TO_TIMESTAMP('2020-11-27 06:13:00','YYYY-MM-DD HH24:mi:ss'),'AI00006',1);
INSERT INTO post_message VALUES ('PI' || lpad(post_message_SEQ.NEXTVAL,5,'0'),'發放優惠卷',TO_TIMESTAMP('2020-11-27 06:13:00','YYYY-MM-DD HH24:mi:ss'),'AI00007',1);
INSERT INTO post_message VALUES ('PI' || lpad(post_message_SEQ.NEXTVAL,5,'0'),'發放優惠卷',TO_TIMESTAMP('2020-11-27 06:13:00','YYYY-MM-DD HH24:mi:ss'),'AI00008',1);
INSERT INTO post_message VALUES ('PI' || lpad(post_message_SEQ.NEXTVAL,5,'0'),'發放優惠卷',TO_TIMESTAMP('2020-11-27 06:13:00','YYYY-MM-DD HH24:mi:ss'),'AI00009',1);
INSERT INTO post_message VALUES ('PI' || lpad(post_message_SEQ.NEXTVAL,5,'0'),'發放優惠卷',TO_TIMESTAMP('2020-11-27 06:13:00','YYYY-MM-DD HH24:mi:ss'),'AI00010',1);


--verify_list_SEQ審核資料表序列
CREATE SEQUENCE verify_list_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;
   
   
--verify_list審核資料表
CREATE TABLE verify_list (
 primary key(verify_id),
 verify_id               VARCHAR2(20) NOT NULL,
 class_id                VARCHAR2(20) NOT NULL,
 admin_id                VARCHAR2(20) NOT NULL,
 class_check             VARCHAR2(1000),
 upload_time             TIMESTAMP NOT NULL,
 foreign key (class_id)REFERENCES class_info(class_id),
 foreign key (admin_id)REFERENCES admin_list(admin_id)
 );

INSERT INTO verify_list VALUES ('VL' || lpad(verify_list_SEQ.NEXTVAL,5, '0'),'CLA00001','AI00001','測試',TO_DATE('2020-11-27 19:29:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO verify_list VALUES ('VL' || lpad(verify_list_SEQ.NEXTVAL,5, '0'),'CLA00002','AI00002','測試',TO_DATE('2020-11-27 19:29:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO verify_list VALUES ('VL' || lpad(verify_list_SEQ.NEXTVAL,5, '0'),'CLA00003','AI00003','測試',TO_DATE('2020-11-27 19:29:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO verify_list VALUES ('VL' || lpad(verify_list_SEQ.NEXTVAL,5, '0'),'CLA00004','AI00004','測試',TO_DATE('2020-11-27 19:29:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO verify_list VALUES ('VL' || lpad(verify_list_SEQ.NEXTVAL,5, '0'),'CLA00005','AI00005','測試',TO_DATE('2020-11-27 19:29:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO verify_list VALUES ('VL' || lpad(verify_list_SEQ.NEXTVAL,5, '0'),'CLA00006','AI00006','測試',TO_DATE('2020-11-27 19:29:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO verify_list VALUES ('VL' || lpad(verify_list_SEQ.NEXTVAL,5, '0'),'CLA00007','AI00007','測試',TO_DATE('2020-11-27 19:29:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO verify_list VALUES ('VL' || lpad(verify_list_SEQ.NEXTVAL,5, '0'),'CLA00008','AI00008','測試',TO_DATE('2020-11-27 19:29:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO verify_list VALUES ('VL' || lpad(verify_list_SEQ.NEXTVAL,5, '0'),'CLA00009','AI00009','測試',TO_DATE('2020-11-27 19:29:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO verify_list VALUES ('VL' || lpad(verify_list_SEQ.NEXTVAL,5, '0'),'CLA00010','AI00010','測試',TO_DATE('2020-11-27 19:29:00','YYYY-MM-DD HH24:MI:SS'));


--evaluation_SEQ評價序列
CREATE SEQUENCE evaluation_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;
 
 
 
 --evaluation評價
create table evaluation(
 primary key(evaluation_ID),
 evaluation_ID varchar2(20) not null,
 class_ID varchar2(20) not null,
 member_id varchar2(20) not null ,
 evaluation_class varchar2(2000) not null,
 evaluation_score number(2,0) not null,
 evaluation_time Timestamp not null,
 foreign key (class_id)REFERENCES CLASS_INFO(class_ID),
 foreign key (member_id)REFERENCES member_info(member_id),
 evaluation_status number(1,0) default 1 not null 
 
);

 --evaluation評價假資料
 
 INSERT INTO evaluation (evaluation_id,class_id,member_id,evaluation_class,evaluation_score,evaluation_time)
                       VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5,'0'),'CLA00001','MEM00001','我的天哪',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'));
	--讓系統知道是那6個要放值
 
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'), 'CLA00001','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'), 'CLA00002','MEM00002','老師好帥',6,TO_TIMESTAMP('2020-11-29 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'), 'CLA00003','MEM00003','老師好漂亮',8,TO_TIMESTAMP('2020-12-29 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'), 'CLA00004','MEM00004','老師上課好慢',8,TO_TIMESTAMP('2020-10-29 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'), 'CLA00005','MEM00005','無意見',2,TO_TIMESTAMP('2020-11-30 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'), 'CLA00006','MEM00006','冷氣好冷',4,TO_TIMESTAMP('2020-11-12 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'), 'CLA00007','MEM00007','考試太難了',10,TO_TIMESTAMP('2020-11-29 20:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'), 'CLA00008','MEM00008','作業太多',8,TO_TIMESTAMP('2020-11-29 12:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'), 'CLA00009','MEM00009','作業太少',6,TO_TIMESTAMP('2020-11-29 20:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'), 'CLA00010','MEM00010','完全聽不懂',1,TO_TIMESTAMP('2020-11-29 19:48:50','yyyy-mm-dd hh24:mi:ss'),1);

INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00013','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00006','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00011','MEM00001','老師上課好慢',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00008','MEM00001','考試太難了',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00008','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00004','MEM00001','考試太難了',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00032','MEM00001','完全聽不懂',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00015','MEM00001','老師上課好慢',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00006','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00035','MEM00001','考試太難了',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00032','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00034','MEM00001','完全聽不懂',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00016','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00015','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00040','MEM00001','考試太難了',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00011','MEM00001','老師上課好慢',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00041','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00011','MEM00001','完全聽不懂',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00011','MEM00001','老師上課好慢',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00006','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00011','MEM00001','老師上課好慢',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00006','MEM00001','考試太難了',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00037','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00006','MEM00001','考試太難了',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00035','MEM00001','老師上課好慢',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00015','MEM00001','完全聽不懂',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00040','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00015','MEM00001','考試太難了',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00034','MEM00001','老師上課好慢',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00010','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00010','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00040','MEM00001','考試太難了',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00032','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00035','MEM00001','老師上課好慢',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00035','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00035','MEM00001','考試太難了',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00015','MEM00001','完全聽不懂',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00016','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00017','MEM00001','完全聽不懂',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00028','MEM00001','考試太難了',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00040','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00013','MEM00001','老師上課好慢',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00039','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00004','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00011','MEM00001','完全聽不懂',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00030','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00006','MEM00001','考試太難了',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00006','MEM00001','老師上課好慢',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00035','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00035','MEM00001','考試太難了',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00041','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00030','MEM00001','老師上課好慢',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00037','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00013','MEM00001','完全聽不懂',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00016','MEM00001','老師上課講太快',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),'CLA00028','MEM00001','完全聽不懂',2,TO_TIMESTAMP('2020-11-27 19:48:51','yyyy-mm-dd hh24:mi:ss'),1);


DROP VIEW view_class_income;

CREATE VIEW view_class_income AS
SELECT class_info.class_ID,class_info.member_id,class_info.class_name,
		SUM(REPLACE(replace(order_list.purchase_plan,'募資價',class_info.startfund_price), '原價' , class_info.original_price)) as price

FROM class_info, order_list
WHERE class_info.class_ID = order_list.class_id 
group by class_info.class_ID,class_info.class_name,class_info.member_id
;





COMMIT;