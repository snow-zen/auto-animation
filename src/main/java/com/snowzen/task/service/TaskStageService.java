package com.snowzen.task.service;

import com.snowzen.common.ApiResponseStatus;
import com.snowzen.common.exception.ApiException;
import com.snowzen.task.TaskExecutionContext;
import com.snowzen.task.TaskExecutionContextFactory;
import com.snowzen.task.entity.TaskEntity;
import com.snowzen.task.service.stage.AbstractTaskStageData;
import com.snowzen.task.service.stage.TaskStageHandler;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

/**
 * 任务阶段处理服务。
 *
 * @author snow-zen
 */
@ApplicationScoped
public class TaskStageService {

    @Any
    @Inject
    Instance<TaskStageHandler<?>> handlerList;

    @Inject
    TaskExecutionContextFactory taskExecutionContextFactory;

    /**
     * 提交任务阶段数据进行处理。
     *
     * @param taskEntity    任务实体。
     * @param taskStageData 任务阶段数据。
     */
    public void submit(TaskEntity taskEntity, AbstractTaskStageData taskStageData) {
        TaskExecutionContext executionContext = taskExecutionContextFactory.createContext(taskEntity);

        for (TaskStageHandler<?> taskStageHandler : handlerList) {
            boolean result = taskStageHandler.handle(taskStageData, executionContext);
            if (result) {
                return;
            }
        }
        throw new ApiException("未找到对应的任务阶段处理逻辑", ApiResponseStatus.TASK_STAGE_CANNOT_BE_RESOLVED);
    }

}
