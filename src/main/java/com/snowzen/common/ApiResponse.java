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
    private final ApiResponseStatus code;

    /**
     * 响应消息。
     */
    private final String message;

    /**
     * 具体明细内容。
     */
    private final T detail;

    private ApiResponse(ApiResponseStatus code, String message) {
        this(code, message, null);
    }

    private ApiResponse(ApiResponseStatus code, String message, T detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

    public static <T> ApiResponse<T> build(ApiResponseStatus status, String message, T detail) {
        return new ApiResponse<>(status, message, detail);
    }

    public static ApiResponse<Void> buildWithoutDetail(ApiResponseStatus status, String message) {
        return new ApiResponse<>(status, message);
    }

}
