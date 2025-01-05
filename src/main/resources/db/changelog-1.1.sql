-- liquibase formatted sql

-- changeset snow-zen:1
alter table task
    add column labels json null default '{}' FORMAT JSON comment '任务标签，JSON 数据格式';
--  rollback alter table task drop column labels;
