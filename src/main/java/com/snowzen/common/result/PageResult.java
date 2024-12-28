package com.snowzen.common.result;

import java.util.List;

/**
 * @author snow-zen
 */
public record PageResult<T>(Integer page, List<T> data, Long count) {
}
