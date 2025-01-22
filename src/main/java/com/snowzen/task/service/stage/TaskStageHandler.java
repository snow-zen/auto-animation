package com.snowzen.task.service.stage;

import com.snowzen.task.TaskExecutionContext;

/**
 * 任务阶段处理器。
 *
 * @author snow-zen
 */
public interface TaskStageHandler<T extends AbstractTaskStageData> {

    /**
     * 尝试转换任务阶段提交数据为指定类型数据。
     *
     * @param rawStageData 原始任务阶段数据。
     * @return 转换成功返回指定类型数据，转换失败则返回 null。
     */
    T convertStageData(AbstractTaskStageData rawStageData);

    /**
     * 实际执行任务阶段处理逻辑抽象方法。
     *
     * @param stageData        指定类型的任务阶段数据。
     * @param executionContext 任务执行上下文。
     */
    boolean doHandle(T stageData, TaskExecutionContext executionContext);

    /**
     * 执行任务阶段处理逻辑。
     *
     * @param rawStageData     任务阶段数据。
     * @param executionContext 任务执行上下文。
     */
    default boolean handle(AbstractTaskStageData rawStageData, TaskExecutionContext executionContext) {
        T stageData = convertStageData(rawStageData);
        if (stageData == null) {
            return false;
        }
        return doHandle(stageData, executionContext);
    }
}
