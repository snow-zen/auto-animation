package com.snowzen.controller;

import com.snowzen.common.result.PageResult;
import com.snowzen.common.spec.PageSpec;
import com.snowzen.entity.TaskEntity;
import com.snowzen.enums.TaskStatus;
import com.snowzen.repository.TaskRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
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
}
