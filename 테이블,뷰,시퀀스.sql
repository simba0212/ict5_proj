DROP TABLE ADMIN_T CASCADE CONSTRAINTS;
DROP TABLE BOOK CASCADE CONSTRAINTS;
DROP TABLE POINTBACK CASCADE CONSTRAINTS;
DROP TABLE CLASS CASCADE CONSTRAINTS;
DROP TABLE POINT CASCADE CONSTRAINTS;
DROP TABLE POINTAPPROVE CASCADE CONSTRAINTS;
DROP TABLE MEMBER_T CASCADE CONSTRAINTS;
DROP TABLE TEACHER CASCADE CONSTRAINTS;
DROP TABLE NOTICE CASCADE CONSTRAINTS;

/* Create Tables */

CREATE TABLE ADMIN_T
(
	ADMIN_NUM number NOT NULL,
	ADMIN_ID varchar2(50),
	ADMIN_PW varchar2(50),
	PRIMARY KEY (ADMIN_NUM)
);


CREATE TABLE BOOK
(
	BOOK_NUM number NOT NULL,
	BOOK_DATE date,
	ATTEND_DATE date,
	CLASS_NUM number NOT NULL,
	MEMBER_NUM number NOT NULL,
	PRIMARY KEY (BOOK_NUM)
);


CREATE TABLE CLASS
(
	CLASS_NUM number NOT NULL,
	CLASS_TIME varchar2(50),
	CLASS_DATE date,
	CLASS_RES number,
	CLASS_MAX number,
	CLASS_POINT number,
	EVENT varchar2(50),
	CLASS_ROOM varchar2(50),
	CLASS_TYPE number,
	TEACHER_NUM number NOT NULL,
	PRIMARY KEY (CLASS_NUM)
);


CREATE TABLE MEMBER_T
(
	MEMBER_NUM number NOT NULL,
	MEMBER_ID varchar2(50) NOT NULL,
	MEMBER_NAME varchar2(50),
	MEMBER_GEN varchar2(50),
	MEMBER_BIRTH date,
	MEMBER_SIGNUP_DATE date,
	MEMBER_ADDR varchar2(50),
	MEMBER_PW varchar2(50),
	PRIMARY KEY (MEMBER_NUM)
);


CREATE TABLE POINT
(
	POINT_NUM number NOT NULL,
	POINT number,
	POINT_TYPE varchar2(50),
	POINT_ACCURE number,
	POINT_SIGNUP_DATE date,
	MEMBER_NUM number NOT NULL,
	PRIMARY KEY (POINT_NUM)
);


CREATE TABLE POINTAPPROVE
(
	CHARGE_NUM number NOT NULL,
	POINT number,
	POINT_SIGNUP_DATE date,
	POINT_CHARGE_DATE date,
	POINT_APPROVE date,
	MEMBER_NUM number NOT NULL,
	PRIMARY KEY (CHARGE_NUM)
);


CREATE TABLE POINTBACK
(
	REFUND_NUM number NOT NULL,
	BOOK_CANCLE number,
	POINT_CANCLE number,
	POINT_NUM number NOT NULL,
	CLASS_NUM number NOT NULL,
	PRIMARY KEY (REFUND_NUM)
);


CREATE TABLE TEACHER
(
	TEACHER_NUM number NOT NULL,
	TEACHER_NAME varchar2(50),
	TEACHER_PHONE varchar2(50),
	TEACHER_ADDR varchar2(50),
	TEACHER_GEN varchar2(50),
	TEACHER_CAREER varchar2(50),
	TEACHER_IMG varchar2(50),
	PRIMARY KEY (TEACHER_NUM)
);

CREATE TABLE NOTICE(
    NOTICE_NUM NUMBER NOT NULL PRIMARY KEY,
    NOTICE_CONTENT VARCHAR2(4000)
);

/* Create Foreign Keys */

ALTER TABLE BOOK
	ADD FOREIGN KEY (CLASS_NUM)
	REFERENCES CLASS (CLASS_NUM)
;
ALTER TABLE POINTBACK
	ADD FOREIGN KEY (CLASS_NUM)
	REFERENCES CLASS (CLASS_NUM)
;
ALTER TABLE BOOK
	ADD FOREIGN KEY (MEMBER_NUM)
	REFERENCES MEMBER_T (MEMBER_NUM)
;
ALTER TABLE POINT
	ADD FOREIGN KEY (MEMBER_NUM)
	REFERENCES MEMBER_T (MEMBER_NUM)
;
ALTER TABLE POINTAPPROVE
	ADD FOREIGN KEY (MEMBER_NUM)
	REFERENCES MEMBER_T (MEMBER_NUM)
;
ALTER TABLE POINTBACK
	ADD FOREIGN KEY (POINT_NUM)
	REFERENCES POINT (POINT_NUM)
;
ALTER TABLE CLASS
	ADD FOREIGN KEY (TEACHER_NUM)
	REFERENCES TEACHER (TEACHER_NUM)
;

--  select  A테이블명.컬럼명 , A테이블명.컬럼명, B테이블명.컬럼명,B테이블명.컬럼명
--  from A테이블명, B테이블명
--  where  A테이블명.컬럼명 = B테이블명.컬럼명 
--       ** A테이블명.컬럼명 = B테이블명.컬럼명 이때 컬럼은 외래키가 기본이다.
--       ** 두 테이블이 모두 같은 컬럼을 가지고 있을때 사용 

-- 가까운 클래스 뷰(JOIN 예시)
CREATE OR REPLACE FORCE EDITIONABLE VIEW bookedclass 
as select b.member_num, c.class_num, c.class_date,c.class_time, c.class_room, c.class_type, c.class_res,c.class_max, t.teacher_name
from book b, class c, teacher t
where b.class_num = c.class_num and c.teacher_num = t.teacher_num
ORDER BY c.class_date, c.class_time;

-- 시퀀스 만들기
-- 생성방법 :  
--   CREATE SEQUENCE 이름(테이블이름_seq)
--   increment by 증가숫자(보통은 1)
--   start with 시작숫자(보통은 1)
--   nocycle ; 
--   nocycle =>  번호가 끝까지 간 후 다시 시작 안함 설정

--  사용방법 ;  insert 할때  시퀀스이름.nextval 를 사용하면 
--             자동으로 삽입 할때 번호가 증가 된다.
CREATE SEQUENCE admin_t_seq
INCREMENT BY 1
START WITH 1;

CREATE SEQUENCE book_seq
INCREMENT BY 1
START WITH 9;

CREATE SEQUENCE class_seq
INCREMENT BY 1
START WITH 10;

CREATE SEQUENCE member_t_seq
INCREMENT BY 1
START WITH 7;

CREATE SEQUENCE notice_seq
INCREMENT BY 1
START WITH 2;

CREATE SEQUENCE point_seq
INCREMENT BY 1
START WITH 1;

CREATE SEQUENCE pointapprove_seq
INCREMENT BY 1
START WITH 1;

CREATE SEQUENCE pointback_seq
INCREMENT BY 1
START WITH 1;

CREATE SEQUENCE teacher_seq
INCREMENT BY 1
START WITH 2;
