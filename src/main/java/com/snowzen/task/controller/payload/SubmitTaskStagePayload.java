package com.snowzen.task.controller.payload;

import com.snowzen.task.service.stage.AbstractTaskStageData;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 提交任务阶段 Payload。
 *
 * @author snow-zen
 */
@Getter
@Setter
public class SubmitTaskStagePayload {

    /**
     * 任务阶段数据。
     */
    @NotNull
    private AbstractTaskStageData stage;
}
