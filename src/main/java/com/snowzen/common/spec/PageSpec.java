package com.snowzen.common.spec;

/**
 * 分页参数说明。
 *
 * @param pageIndex 分页页码（从 0 开始）。
 * @param pageSize 分页大小。
 * @author snow-zen
 */
public record PageSpec(int pageIndex, int pageSize) {
}
