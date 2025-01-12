package com.snowzen.task.controller;

import com.snowzen.task.controller.payload.SubmitTaskStagePayload;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 任务阶段控制器
 *
 * @author snow-zen
 */
@Slf4j
@Path("/tasks/{task-type}/task-stages/")
public class TaskStageController {

    /**
     * 提交任务阶段。
     *
     * @param taskType 任务类型。
     * @param payload  任务阶段 payload 对象。
     */
    @POST
    public void submitTaskStage(@PathParam("task-type") String taskType,
                                @Valid SubmitTaskStagePayload payload) {
        // todo
    }
}
