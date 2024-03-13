package com.card.service.poc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class GlobalErrorResponse<T> extends GlobalApiResponse<T> {
    @Builder.Default
    private boolean successful = false;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String stackTrace;

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
