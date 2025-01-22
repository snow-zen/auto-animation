package com.snowzen.task.service.stage;

import com.snowzen.task.TaskExecutionContext;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * 抓取任务阶段数据处理器。
 *
 * @author snow-zen
 */
@ApplicationScoped
public class FetchInfoTaskStageHandler implements TaskStageHandler<FetchInfoTaskStageData> {

    @Override
    public FetchInfoTaskStageData convertStageData(AbstractTaskStageData rawStageData) {
        if (rawStageData instanceof FetchInfoTaskStageData data) {
            return data;
        }
        return null;
    }

    @Override
    public boolean doHandle(FetchInfoTaskStageData stageData, TaskExecutionContext executionContext) {
        // todo 实现抓取数据逻辑
        return true;
    }
}
