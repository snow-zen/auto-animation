package com.snowzen.configuration.error;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.snowzen.common.ApiResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.List;
import java.util.Map;

/**
 * {@link MismatchedInputException} 异常对应的响应处理器。
 *
 * @author snow-zen
 */
@Provider
public class MismatchInputExceptionMapper implements ExceptionMapper<MismatchedInputException> {
    @Override
    public Response toResponse(MismatchedInputException exception) {
        List<String> attributes = exception.getPath().stream()
            .map(JsonMappingException.Reference::getFieldName)
            .toList();

        return Response.status(Response.Status.BAD_REQUEST)
            .entity(
                ApiResponse.build(
                    ApiResponse.Status.INVALID_INPUT,
                    "Invalid input",
                    Map.of("errorAttributes", attributes)
                )
            )
            .build();
    }
}
