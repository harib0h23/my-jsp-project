create table tempmember(
id varchar2(20) not null,
passwd varchar2(20),
name varchar2(20),
mem_num1 varchar2(6),
mem_num2 varchar2(7),
e_mail varchar2(30),
phone varchar2(30),
zipcode varchar2(7),
address varchar2(60),
job varchar2(30),
primary key(id)
);

select * from tempmember;

insert into tempmember values('aaaa','1111','홍길동','123456','7654321','hong@naver.com',
'02-1234-5678','100-100','서울특별시 영등포구 영등포동 신안빌딩','프로그래머');

insert into tempmember values('bbbb','2222','강감찬','123456','7654321','kang@naver.com',
'041-1234-5678','100-100','함경북도 개성시 개성군','개발자');

insert into tempmember values('cccc','3333','이순신','123456','7654321','lee@naver.com',
'066-1234-5678','100-100','충청남도 아산시 아산군 아산면','분석가');
