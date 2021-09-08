drop table task_tb;

create table task_tb (
    id varchar(100) not null,
    task_desc varchar(100) default null,
    task_date DATE default null,
    task_status varchar(20) default null,
    primary key(id)
);
