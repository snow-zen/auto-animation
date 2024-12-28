package com.snowzen.repository;

import com.snowzen.common.result.PageResult;
import com.snowzen.common.spec.PageSpec;
import com.snowzen.entity.TaskEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * 任务存储库。
 *
 * @author snow-zen
 */
@ApplicationScoped
public class TaskRepository implements PanacheRepository<TaskEntity> {

    /**
     * 分页查询任务信息。
     *
     * @param pageSpec 分页说明。
     * @return 分页结果。
     */
    public PageResult<TaskEntity> page(PageSpec pageSpec) {
        PanacheQuery<TaskEntity> query = findAll()
            .page(Page.of(pageSpec.page(), pageSpec.size()));
        return new PageResult<>(pageSpec.page(), query.list(), query.count());
    }
}
