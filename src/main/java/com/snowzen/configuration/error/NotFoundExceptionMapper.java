package com.snowzen.configuration.error;

import com.snowzen.common.ApiResponse;
import com.snowzen.common.ApiResponseStatus;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * {@link NotFoundException} 异常对应的异常处理器。
 *
 * @author snow-zen
 */
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND)
            .entity(
                ApiResponse.buildWithoutDetail(
                    ApiResponseStatus.DATA_NOT_FOUND,
                    "未找到资源"
                )
            )
            .build();
    }
}
