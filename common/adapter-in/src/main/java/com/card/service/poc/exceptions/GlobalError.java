package com.card.service.poc.exceptions;

import jakarta.validation.ConstraintViolationException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static com.card.service.poc.dto.GlobalApiResponse.PREFIX_OF_ERROR_CODE;


@Getter
@RequiredArgsConstructor
public enum GlobalError {
    // Common Error Code
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "", new Class[]{ConstraintViolationException.class}),
    UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY, "", new Class[]{MethodArgumentNotValidException.class}),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "", new Class[]{RuntimeException.class}),
    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final Class<? extends Exception>[] matchedExceptions;

    public static GlobalError findMatchedError(Exception exception) {
        // Stream 으로 변경...
        for (var error : GlobalError.values()) {
            for (var clazz : error.getMatchedExceptions()) {
                if (clazz.isInstance(exception)) {
                    return error;
                }
            }
        }

        return INTERNAL_SERVER_ERROR;
    }

    public String getErrorCode() {
        if (Strings.isEmpty(errorCode)) {
            return PREFIX_OF_ERROR_CODE + httpStatus.value();
        }

        return PREFIX_OF_ERROR_CODE + errorCode;
    }
}
