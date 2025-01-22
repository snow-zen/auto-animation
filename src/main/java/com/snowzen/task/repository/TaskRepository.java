package com.snowzen.task.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.snowzen.common.result.PageResult;
import com.snowzen.common.spec.PageSpec;
import com.snowzen.common.util.PageUtil;
import com.snowzen.task.TaskLabelConstant;
import com.snowzen.task.entity.QTaskEntity;
import com.snowzen.task.entity.TaskEntity;
import com.snowzen.task.enums.TaskStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 任务存储库。
 *
 * @author snow-zen
 */
@ApplicationScoped
public class TaskRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    JPAQueryFactory jpaQueryFactory;

    /**
     * 分页查询任务信息。
     *
     * @param pageSpec 分页说明。
     * @param title    任务标题，支持模糊查询。
     * @param statuses 任务状态，支持多值查询。
     * @return 分页结果。
     */
    public PageResult<TaskEntity> page(PageSpec pageSpec, String title, List<TaskStatus> statuses) {
        return PageUtil.page(
            entityManager, TaskEntity.class, pageSpec,
            (builder, root) -> buildPagePredicate(builder, root, title, statuses).toArray(new Predicate[0])
        );
    }

    /**
     * 构建分页查询条件。
     */
    private List<Predicate> buildPagePredicate(CriteriaBuilder criteriaBuilder,
                                               Root<TaskEntity> root, String title, List<TaskStatus> statuses) {
        List<Predicate> result = new ArrayList<>();
        if (StringUtils.isNotEmpty(title)) {
            result.add(criteriaBuilder.like(
                criteriaBuilder.function(
                    "JSON_EXTRACT", String.class,
                    root.get(QTaskEntity.taskEntity.labels.getMetadata().getName()),
                    criteriaBuilder.literal("$." + TaskLabelConstant.TASK_TYPE)
                ),
                "%" + title + "%"));
        }
        if (CollectionUtils.isNotEmpty(statuses)) {
            result.add(root.get(QTaskEntity.taskEntity.status.getMetadata().getName()).in(statuses));
        }
        return result;
    }

    /**
     * 通过任务 id 查询任务数据。
     *
     * @param id 任务 id。
     * @return 任务数据对应 {@link Optional} 包装对象。
     */
    public Optional<TaskEntity> selectById(Long id) {
        return Optional.ofNullable(entityManager.find(TaskEntity.class, id));
    }

    /**
     * 保存任务数据。
     *
     * @param taskEntity 任务。
     */
    @Transactional(rollbackOn = Exception.class)
    public void insert(TaskEntity taskEntity) {
        entityManager.persist(taskEntity);
    }

    /**
     * 更新任务数据。
     *
     * @param taskEntity 任务。
     */
    @Transactional(rollbackOn = Exception.class)
    public void update(TaskEntity taskEntity) {
        entityManager.merge(taskEntity);
    }

    /**
     * 通过主键 id 删除对应实体。
     *
     * @param id 主键 id。
     */
    @Transactional(rollbackOn = Exception.class)
    public void deleteById(Long id) {
        jpaQueryFactory.delete(QTaskEntity.taskEntity)
            .where(QTaskEntity.taskEntity.id.eq(id))
            .execute();
    }
}
