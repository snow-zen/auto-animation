package com.snowzen.task.controller;

import com.snowzen.common.ApiResponse;
import com.snowzen.common.ApiResponseStatus;
import com.snowzen.task.controller.payload.SubmitTaskStagePayload;
import com.snowzen.task.entity.TaskEntity;
import com.snowzen.task.service.TaskService;
import com.snowzen.task.service.TaskStageService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 任务阶段的控制器。
 *
 * @author snow-zen
 */
@Slf4j
@Path("/tasks/{taskId}/stages/")
public class TaskStageController {

    @Inject
    TaskService taskService;

    @Inject
    TaskStageService taskStageService;

    /**
     * 提交任务阶段。
     *
     * @param taskId  任务类型。
     * @param payload 任务阶段 payload 对象。
     */
    @POST
    public ApiResponse<Long> submitTaskStage(@PathParam("taskId") Long taskId,
                                             @Valid SubmitTaskStagePayload payload) {
        TaskEntity taskEntity = taskService.ensureTaskById(taskId);

        // todo submit 方法可能会抛出异常，需要一个定时任务将创建时间超过某个期限的草稿状态任务给删除。
        taskStageService.submit(taskEntity, payload.getStage());

        // todo 什么时候把任务状态调整？
        return ApiResponse.build(ApiResponseStatus.OK, null, taskEntity.getId());
    }
}
