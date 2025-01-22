package com.snowzen.task.service;

import com.snowzen.common.ApiResponseStatus;
import com.snowzen.common.exception.ApiException;
import com.snowzen.task.TaskLabelConstant;
import com.snowzen.task.entity.TaskEntity;
import com.snowzen.task.enums.TaskStatus;
import com.snowzen.task.enums.TaskType;
import com.snowzen.task.repository.TaskRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Map;

/**
 * 任务服务。
 *
 * @author snow-zen
 */
@ApplicationScoped
public class TaskService {

    @Inject
    TaskRepository taskRepository;

    /**
     * 通过任务 id 获取对应任务。
     *
     * @param taskId 任务 id。
     * @return 返回获取到的对应任务，否则抛出异常。
     */
    public TaskEntity ensureTaskById(Long taskId) {
        return taskRepository.selectById(taskId)
            .orElseThrow(() -> new ApiException("未找到任务id对应任务", ApiResponseStatus.DATA_NOT_FOUND));
    }

    /**
     * 创建状态为草稿的任务。
     *
     * @param taskType 任务类型。
     * @return 返回创建的任务。
     */
    public TaskEntity createDraftTask(TaskType taskType) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setLabels(Map.of(TaskLabelConstant.TASK_TYPE, taskType.name()));
        taskEntity.setStatus(TaskStatus.DRAFT);
        taskRepository.insert(taskEntity);
        return taskEntity;
    }

    /**
     * 更新任务实体。
     *
     * @param taskEntity 任务实体。
     */
    public void updateTask(TaskEntity taskEntity) {
        taskRepository.update(taskEntity);
    }
}
