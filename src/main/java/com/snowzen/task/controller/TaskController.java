package com.snowzen.task.controller;

import com.snowzen.common.ApiResponse;
import com.snowzen.common.ApiResponseStatus;
import com.snowzen.common.result.PageResult;
import com.snowzen.common.spec.PageSpec;
import com.snowzen.task.controller.payload.CreateTaskPayload;
import com.snowzen.task.entity.TaskEntity;
import com.snowzen.task.enums.TaskStatus;
import com.snowzen.task.repository.TaskRepository;
import com.snowzen.task.service.TaskService;
import com.snowzen.task.service.TaskStageService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import java.util.List;

/**
 * 任务控制器。
 *
 * @author snow-zen
 */
@Path("/tasks")
public class TaskController {

    @Inject
    TaskRepository taskRepository;

    @Inject
    TaskService taskService;

    @Inject
    TaskStageService taskStageService;

    /**
     * 任务分页查询。
     *
     * @param pageIndex 分页页码，默认为 0。
     * @param pageSize  分页大小，默认为 10。
     * @param title     任务标题，支持模糊查询。
     * @param statuses  任务状态，支持多值查询。
     * @return 任务分页结果。
     */
    @GET
    @Path("/page")
    public PageResult<TaskEntity> page(@QueryParam("pageIndex") @DefaultValue("0") int pageIndex,
                                       @QueryParam("pageSize") @DefaultValue("10") int pageSize,
                                       @QueryParam("title") String title,
                                       @QueryParam("status") List<TaskStatus> statuses) {
        return taskRepository.page(new PageSpec(pageIndex, pageSize), title, statuses);
    }

    /**
     * 创建任务。
     *
     * @param payload 任务创建 payload 对象。
     * @return 携带已创建任务的 id 的统一响应体。
     */
    @POST
    public ApiResponse<Long> createTask(@Valid CreateTaskPayload payload) {
        TaskEntity taskEntity = taskService.createDraftTask(payload.getType());
        if (payload.getStage() != null) {
            taskStageService.submit(taskEntity, payload.getStage());

            // todo 任务的状态流转不应该是固定写死的，而是通过任务模板决策的，后续有任务模板的时候处理。
            // todo 也许任务不需要状态流转，只需要显示进度即可？
            taskEntity.setStatus(TaskStatus.PREPARE);
            taskService.updateTask(taskEntity);
        }
        return ApiResponse.build(ApiResponseStatus.OK, null, taskEntity.getId());
    }

    /**
     * 通过任务 id 删除指定任务。
     *
     * @param id 任务 id 不能为空。
     */
    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") Long id) {
        taskRepository.deleteById(id);
    }
}
