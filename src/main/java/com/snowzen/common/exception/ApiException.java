package com.snowzen.common.exception;

import com.snowzen.common.ApiResponseStatus;
import lombok.Getter;

/**
 * api 异常。
 *
 * @author snow-zen
 */
@Getter
public class ApiException extends RuntimeException {

    private final ApiResponseStatus responseStatus;

    public ApiException(String message, ApiResponseStatus responseStatus) {
        super(message);
        this.responseStatus = responseStatus;
    }
}
