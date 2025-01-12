package com.snowzen.common;

import lombok.Getter;

/**
 * api 响应体。
 *
 * @author snow-zen
 */
@Getter
public class ApiResponse<T> {

    /**
     * api 响应代码。
     */
    private final Status code;

    /**
     * 响应消息。
     */
    private final String message;

    /**
     * 具体明细内容。
     */
    private final T detail;

    private ApiResponse(Status code, String message) {
        this(code, message, null);
    }

    private ApiResponse(Status code, String message, T detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

    public static <T> ApiResponse<T> build(Status status, String message, T detail) {
        return new ApiResponse<>(status, message, detail);
    }

    public static ApiResponse<Void> buildWithoutDetail(Status status, String message) {
        return new ApiResponse<>(status, message);
    }

    /**
     * api 响应状态枚举。
     */
    public enum Status {
        VIOLATION_FAIL,
        INVALID_INPUT,
        INTERNAL_SERVER_ERROR
    }

}
