create table groupcodes_1
(code numeric(5,0) primary key,
title varchar(30) default 'blank');

insert into groupcodes_1 values (0,'default');

create table accounts_1
(account_id numeric(9,0) primary key,
account_name varchar(30) default 'blank',
group_code numeric(5,0) default 0,
foreign key (group_code) references groupcodes_1(code));

create table records_1
(serial_id serial primary key,
record_date date not null,
adding_date date not null,
value_date date not null,
is_locked boolean default false,
details varchar(200));

create table registries_1
(serial_id serial primary key,
record_id int not null,
account_id numeric(9,0),
amount numeric (9,3) default 0,
is_debit boolean default true, 
foreign key (record_id) references records_1(serial_id),
foreign key (account_id) references accounts_1(account_id));