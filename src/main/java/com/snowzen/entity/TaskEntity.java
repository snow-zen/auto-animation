package com.snowzen.entity;

import com.snowzen.enums.TaskStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 任务实体。
 *
 * @author snow-zen
 */
@Getter
@Setter
@Entity
@Table(name = "task")
public class TaskEntity {

    /**
     * 主键 id。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 任务名称。
     */
    @Column(name = "name")
    private String name;

    /**
     * 任务状态。
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;
}
