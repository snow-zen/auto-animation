-- liquibase formatted sql

-- changeset snow-zen:1
create table task
(
    id     long         not null auto_increment comment '主键id',
    title  varchar(100) not null comment '任务标题',
    status varchar(100) not null comment '任务状态',
    primary key (id)
);
-- rollback drop table if exists task;
