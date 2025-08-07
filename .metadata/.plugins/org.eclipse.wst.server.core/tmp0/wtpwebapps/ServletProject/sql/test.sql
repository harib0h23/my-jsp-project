select * from DEPT;

create table login(
id varchar2(12) not null,
pass varchar2(12) not null,
constraint LOGIN_PK primary key(id)
);

insert into LOGIN values('admin', '1234');
select * from LOGIN;


