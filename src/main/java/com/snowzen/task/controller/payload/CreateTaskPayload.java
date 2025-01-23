package com.snowzen.task.controller.payload;

import com.snowzen.task.enums.TaskType;
import com.snowzen.task.service.stage.AbstractTaskStageData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 创建任务 Payload。
 *
 * @author snow-zen
 */
@Getter
@Setter
public class CreateTaskPayload {

    /**
     * 任务类型。
     */
    @NotNull
    private TaskType type;

    /**
     * 任务阶段数据。如果不为空，可在创建任务完成后自动提交任务阶段数据。
     */
    @Valid
    private AbstractTaskStageData stage;
}
