package com.snowzen.repository;

import com.snowzen.common.result.PageResult;
import com.snowzen.common.spec.PageSpec;
import com.snowzen.entity.TaskEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * @author snow-zen
 */
@ApplicationScoped
public class TaskRepository implements PanacheRepository<TaskEntity> {

    public PageResult<TaskEntity> page(PageSpec pageSpec) {
        PanacheQuery<TaskEntity> query = findAll()
            .page(Page.of(pageSpec.getPage(), pageSpec.getSize()));
        return new PageResult<>(pageSpec.getPage(), query.list(), query.count());
    }
}
