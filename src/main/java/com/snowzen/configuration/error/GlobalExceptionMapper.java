package com.snowzen.configuration.error;

import com.snowzen.common.ApiResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * {@link Throwable} 异常对应的响应处理器。
 *
 * @author snow-zen
 */
@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(
                ApiResponse.buildWithoutDetail(
                    ApiResponse.Status.INTERNAL_SERVER_ERROR,
                    "未知异常"
                )
            )
            .build();
    }
}
