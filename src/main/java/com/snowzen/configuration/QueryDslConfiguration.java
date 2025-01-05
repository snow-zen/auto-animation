package com.snowzen.configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;

/**
 * QueryDSL 相关配置。
 *
 * @author snow-zen
 */
public class QueryDslConfiguration {

    @Inject
    EntityManager entityManager;

    @Singleton
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
