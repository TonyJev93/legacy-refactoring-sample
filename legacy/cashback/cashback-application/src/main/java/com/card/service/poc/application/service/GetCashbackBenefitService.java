package com.card.service.poc.application.service;

import com.card.service.poc.application.port.in.GetCashbackBenefitUseCase;
import org.springframework.stereotype.Service;

@Service
public class GetCashbackBenefitService implements GetCashbackBenefitUseCase {
    @Override
    public String get(String request) {
        return "Cashback Benefit Service : " + request;
    }
}
