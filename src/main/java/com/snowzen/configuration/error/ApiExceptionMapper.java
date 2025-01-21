package com.snowzen.configuration.error;

import com.snowzen.common.ApiResponse;
import com.snowzen.common.exception.ApiException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * {@link ApiException} 异常对应的异常处理器。
 *
 * @author snow-zen
 */
@Provider
public class ApiExceptionMapper implements ExceptionMapper<ApiException> {
    @Override
    public Response toResponse(ApiException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
            .entity(
                ApiResponse.buildWithoutDetail(
                    exception.getResponseStatus(),
                    exception.getMessage()
                )
            )
            .build();
    }
}
