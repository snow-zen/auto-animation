package com.snowzen.configuration.error;

import com.snowzen.common.ApiResponse;
import com.snowzen.common.ApiResponseStatus;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link Throwable} 异常对应的响应处理器。
 *
 * @author snow-zen
 */
@Slf4j
@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable exception) {
        log.error("未知异常", exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(
                ApiResponse.buildWithoutDetail(
                    ApiResponseStatus.INTERNAL_SERVER_ERROR,
                    "未知异常"
                )
            )
            .build();
    }
}
