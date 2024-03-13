package com.card.service.poc.exceptions;

import com.card.service.poc.dto.GlobalErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${exception.stack-trace.enable:true}")
    private boolean traceEnabled;

    @Value("${exception.stack-trace.param-name:trace}")
    private String traceParamName;

    private void setStackTraceIfSettingOn(
            Exception exception,
            WebRequest request,
            GlobalErrorResponse<Object> errorResponse
    ) {
        if (traceEnabled) {
            var values = request.getParameterValues(traceParamName);
            var isTraceOn = Objects.nonNull(values) && values.length > 0 && values[0].contentEquals("true");

            if (isTraceOn) {
                errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));
            }
        }
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(
            RuntimeException exception,
            WebRequest request
    ) {
        log.error(exception.getMessage());

        var errorConstant = GlobalError.findMatchedError(exception);

        var errorResponse = GlobalErrorResponse.builder()
                .code(errorConstant.getErrorCode())
                .message(exception.getMessage())
                .build();

        setStackTraceIfSettingOn(exception, request, errorResponse);

        return ResponseEntity.status(errorConstant.getHttpStatus()).body(errorResponse);
    }
}