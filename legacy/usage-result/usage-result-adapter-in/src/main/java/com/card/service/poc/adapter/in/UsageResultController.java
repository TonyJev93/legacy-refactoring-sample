package com.card.service.poc.adapter.in;

import com.card.service.poc.dto.GlobalApiResponse;
import com.card.service.poc.application.port.in.GetUsageResultUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usage-result")
@RequiredArgsConstructor
public class UsageResultController {

    private final GetUsageResultUseCase useCase;

    @GetMapping
    public GlobalApiResponse<String> usageResult(
            @RequestParam String param1
    ) {
        return GlobalApiResponse.of(
                useCase.get(param1)
        );
    }

    @GetMapping("/error")
    public GlobalApiResponse<String> usageResultError() {
        throw new RuntimeException("hello exception");
    }
}
