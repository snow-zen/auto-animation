package com.snowzen.controller;

import com.snowzen.common.result.PageResult;
import com.snowzen.common.spec.PageSpec;
import com.snowzen.entity.TaskEntity;
import com.snowzen.repository.TaskRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

/**
 * @author snow-zen
 */
@Path("/tasks")
public class TaskController {

    @Inject
    TaskRepository taskRepository;

    @GET
    @Path("/page")
    public PageResult<TaskEntity> page(@QueryParam("page") @DefaultValue("0") int page,
                                       @QueryParam("size") @DefaultValue("10") int size) {
        return taskRepository.page(new PageSpec(page, size));
    }
}