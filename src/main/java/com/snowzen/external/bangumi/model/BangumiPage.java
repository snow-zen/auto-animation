package com.snowzen.external.bangumi.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Bangumi 分页模型。
 *
 * @author snow-zen
 */
@Getter
@Setter
public class BangumiPage<T> {

    /**
     * 数据总量。
     */
    private Integer total;

    /**
     * 分页大小。
     */
    private Integer limit;

    /**
     * 分页偏移量。
     */
    private Integer offset;

    /**
     * 当前分页数据。
     */
    private List<T> data;
}
