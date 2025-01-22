package com.snowzen.common;

/**
 * api 响应状态枚举。
 */
public enum ApiResponseStatus {
    /**
     * 参数验证失败。
     */
    VIOLATION_FAIL,
    /**
     * 输入参数无法被反序列化或者解析。
     */
    INVALID_INPUT,
    /**
     * 数据未找到。
     */
    DATA_NOT_FOUND,
    /**
     * 任务阶段数据无法转换。
     */
    TASK_STAGE_DATA_CANNOT_BE_CONVERT,
    /**
     * 任务阶段无法解析。
     */
    TASK_STAGE_CANNOT_BE_RESOLVED,
    /**
     * 服务器错误。
     */
    INTERNAL_SERVER_ERROR,
    /**
     * 成功。
     */
    OK
}
