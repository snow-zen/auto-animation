package com.snowzen.task.controller;

import com.snowzen.common.ApiResponse;
import com.snowzen.common.ApiResponseStatus;
import com.snowzen.common.exception.ApiException;
import com.snowzen.task.controller.payload.SubmitTaskStagePayload;
import com.snowzen.task.entity.TaskEntity;
import com.snowzen.task.enums.TaskStatus;
import com.snowzen.task.enums.TaskType;
import com.snowzen.task.service.TaskService;
import com.snowzen.task.service.TaskStageService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;

/**
 * 任务阶段的控制器。
 *
 * @author snow-zen
 */
@Slf4j
@Path("/tasks/{task-type}/task-stages/")
public class TaskStageController {

    @Inject
    TaskService taskService;

    @Inject
    TaskStageService taskStageService;

    /**
     * 提交任务阶段。
     *
     * @param taskType 任务类型。
     * @param payload  任务阶段 payload 对象。
     */
    @POST
    public ApiResponse<Long> submitTaskStage(@PathParam("task-type") String taskType,
                                             @Valid SubmitTaskStagePayload payload) {
        TaskType taskTypeEnum = Optional.ofNullable(EnumUtils.getEnum(TaskType.class, taskType))
            .orElseThrow(() -> new ApiException("无效的任务类型", ApiResponseStatus.INVALID_INPUT));

        TaskEntity taskEntity = payload.getTaskId() == null
            ? taskService.createDraftTask(taskTypeEnum)
            : taskService.ensureTaskById(payload.getTaskId());

        // todo submit 方法可能会抛出异常，需要一个定时任务将创建时间超过某个期限的草稿状态任务给删除。
        taskStageService.submit(taskEntity, payload.getStage());

        if (payload.getTaskId() == null) {
            // todo 任务的状态流转不应该是固定写死的，而是通过任务模板决策的，后续有任务模板的时候处理。
            // todo 也许任务不需要状态流转，只需要显示进度即可？
            taskEntity.setStatus(TaskStatus.PREPARE);
        }
        taskService.updateTask(taskEntity);
        return ApiResponse.build(ApiResponseStatus.OK, null, taskEntity.getId());
    }
}
