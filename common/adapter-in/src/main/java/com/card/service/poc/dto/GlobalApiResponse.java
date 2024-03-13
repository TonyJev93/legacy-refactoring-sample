package com.card.service.poc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class GlobalApiResponse<T> {
    public static final String PREFIX_OF_SUCCESS_CODE = "S";
    public static final String PREFIX_OF_ERROR_CODE = "E";

    protected T result;

    @Builder.Default
    private boolean successful = true;

    @Builder.Default
    private String code = PREFIX_OF_SUCCESS_CODE + "200";

    @Builder.Default
    private String message = "SUCCESS";

    @SuppressWarnings("unchecked")
    public static <T> GlobalApiResponse<T> of(T result) {
        return (GlobalApiResponse<T>) GlobalApiResponse.builder().result(result).build();
    }
}
