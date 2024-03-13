package com.card.service.poc.adapter.in;

import com.card.service.poc.application.port.in.GetCashbackBenefitUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cashback")
@RequiredArgsConstructor
public class GetCashbackBenefitController {

    private final GetCashbackBenefitUseCase useCase;

    @GetMapping
    public String cashbackBenefit(
            @RequestParam("param1") String param1
    ) {
        return useCase.get(param1);
    }
}
