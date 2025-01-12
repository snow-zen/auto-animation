package com.snowzen.task.controller.payload;

import com.snowzen.task.enums.TaskStages;
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
     * 任务 id，如果为 null 则创建对应的任务。
     */
    private Long taskId;

    /**
     * 任务阶段标识。
     */
    @NotNull
    private TaskStages stage;

    /**
     * 任务阶段数据。
     */
    @NotNull
    private Object data;
}
