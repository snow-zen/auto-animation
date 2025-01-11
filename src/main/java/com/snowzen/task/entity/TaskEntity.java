package com.snowzen.task.entity;

import com.snowzen.task.enums.TaskStatus;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

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
     * 任务标题。
     */
    @Column(name = "title")
    private String title;

    /**
     * 任务状态。
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;

    /**
     * 任务标签，JSON 数据格式。
     */
    @Type(JsonType.class)
    @Column(name = "labels", columnDefinition = "json")
    private Map<String, String> labels;
}
