package com.snowzen.task;

import com.snowzen.task.entity.TaskEntity;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * {@link TaskExecutionContext} 的工厂类。
 *
 * @author snow-zen
 */
@ApplicationScoped
public class TaskExecutionContextFactory {

    /**
     * 创建任务上下文信息。
     *
     * @param taskEntity 任务实体。
     * @return 返回基于任务实体创建的任务上下文信息。
     */
    public TaskExecutionContext createContext(TaskEntity taskEntity) {
        return new TaskExecutionContext(taskEntity);
    }
}
