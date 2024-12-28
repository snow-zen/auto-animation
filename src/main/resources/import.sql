create table task
(
    id     long         not null auto_increment comment '主键id',
    name   varchar(100) not null comment '任务名称',
    status varchar(100) not null comment '任务状态',
    primary key (id)
);
