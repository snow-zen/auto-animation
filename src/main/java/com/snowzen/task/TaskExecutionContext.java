package com.snowzen.task;

import com.snowzen.task.entity.TaskEntity;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * 任务执行上下文。
 *
 * @author snow-zen
 */
@Getter
@Setter
public class TaskExecutionContext {

    /**
     * 任务。
     */
    private TaskEntity taskEntity;

    /**
     * 任务携带信息。
     */
    private Map<String, String> taskInfo;

    public TaskExecutionContext(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }

}
