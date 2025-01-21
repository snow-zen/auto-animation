-- liquibase formatted sql

-- changeset snow-zen:1
create table task
(
    id     long         not null auto_increment comment '主键id',
    status varchar(100) not null comment '任务状态',
    labels json         not null default '{}' FORMAT JSON comment '任务标签，JSON 数据格式',
    primary key (id)
);
-- rollback drop table if exists task;
