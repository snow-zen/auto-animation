package com.snowzen.common.result;

import java.util.List;

/**
 * 分页结果。
 *
 * @param pageIndex 当前分页页码。
 * @param data      当前分页数据列表。
 * @param count     所有数据统计量。
 * @param <T>       分页数据具体类型。
 * @author snow-zen
 */
public record PageResult<T>(Integer pageIndex, List<T> data, Long count) {
}
