package com.snowzen.configuration.error;

import com.snowzen.common.ApiResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@link ConstraintViolationException} 异常对应的响应处理器。
 *
 * @author snow-zen
 */
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Map<String, String> violations = exception.getConstraintViolations()
            .stream()
            .collect(Collectors.toMap(
                v -> v.getPropertyPath().toString(),
                ConstraintViolation::getMessage
            ));
        return Response.status(Response.Status.BAD_REQUEST)
            .entity(ApiResponse.build(
                ApiResponse.Status.VIOLATION_FAIL,
                "Parameter validation failed",
                violations
            ))
            .build();
    }
}
