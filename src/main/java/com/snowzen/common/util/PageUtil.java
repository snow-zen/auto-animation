package com.snowzen.common.util;

import com.snowzen.common.result.PageResult;
import com.snowzen.common.spec.PageSpec;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.function.BiFunction;
import org.apache.commons.lang3.ArrayUtils;

/**
 * 分页工具。
 *
 * @author snow-zen
 */
public final class PageUtil {

    private PageUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 分页操作。
     *
     * @param entityManager     实体管理器，不能为 null。
     * @param entityClass       实体类型对象，不能为 null。
     * @param pageSpec          分页说明，不能为 null。
     * @param predicateFunction 条件生成函数，不能为 null。
     * @param <T>               实体类型。
     * @return 分页结果。
     */
    public static <T> PageResult<T> page(EntityManager entityManager,
                                         Class<T> entityClass,
                                         PageSpec pageSpec,
                                         BiFunction<CriteriaBuilder, Root<T>, Predicate[]> predicateFunction) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);

        Predicate[] predicateList = predicateFunction.apply(criteriaBuilder, root);
        if (ArrayUtils.isNotEmpty(predicateList)) {
            criteriaQuery.where(predicateList);
        }

        List<T> resultList = entityManager.createQuery(criteriaQuery)
            .setFirstResult(pageSpec.pageIndex() * pageSpec.pageSize())
            .setMaxResults(pageSpec.pageSize())
            .getResultList();

        long count = getTotalCount(entityManager, entityClass, predicateFunction);
        return new PageResult<>(pageSpec.pageIndex(), resultList, count);
    }

    /**
     * 分页时查询总数。
     */
    private static <T> long getTotalCount(EntityManager entityManager,
                                          Class<T> entityClass,
                                          BiFunction<CriteriaBuilder, Root<T>, Predicate[]> predicateFunction) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(builder.count(root));

        Predicate[] predicateList = predicateFunction.apply(builder, root);
        if (ArrayUtils.isNotEmpty(predicateList)) {
            criteriaQuery.where(predicateList);
        }

        return entityManager.createQuery(criteriaQuery)
            .getSingleResult();
    }


}
