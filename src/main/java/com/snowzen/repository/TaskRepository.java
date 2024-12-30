package com.snowzen.repository;

import com.snowzen.common.result.PageResult;
import com.snowzen.common.spec.PageSpec;
import com.snowzen.common.util.PageUtil;
import com.snowzen.entity.TaskEntity;
import com.snowzen.enums.TaskStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
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
            result.add(criteriaBuilder.like(root.get("title"), "%" + title + "%"));
        }
        if (CollectionUtils.isNotEmpty(statuses)) {
            result.add(root.get("status").in(statuses));
        }
        return result;
    }
}
