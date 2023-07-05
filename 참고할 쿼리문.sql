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

-----------------------------------------------------------------참고했던 select 쿼리들------------------------------------------------------------------------------------
select member_num,member_id,member_pw from		MEMBER_T where member_id = 'simba' and member_pw='0212';
select * from bookedclass where member_num = 1 and rownum = 1;

INSERT INTO customer(CUSTID, NAME, ADDRESS, PHONE)
VALUES(CUSTOMER_SEQ.NEXTVAL, '박지성', '영국 맨체스타', '000-5000-0001');

 ALTER TABLE TEACHER ADD(TEACHER_TYPE number);
 
 
 
 