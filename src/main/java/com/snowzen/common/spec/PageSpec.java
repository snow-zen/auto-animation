package com.snowzen.common.spec;

import lombok.Getter;

/**
 * 分页参数说明。
 *
 * @param page 分页页码（从 0 开始）。
 * @param size 分页大小。
 * @author snow-zen
 */
@Getter
public record PageSpec(int page, int size) {
}
