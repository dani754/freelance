create table users(
	userid serial primary key,
	username varchar(20) unique not null,
	upassword varchar(20) not null,
	companies int[] default '{1}'
);

insert into users (username,upassword) values
('admin','admin');

create table companies(
	companyid int primary key,
	companyname varchar(20) unique not null,
	adminid int default 1
);

insert into companies (companyid,companyname) values
(1,'template');

