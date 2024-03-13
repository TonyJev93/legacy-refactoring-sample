package com.card.service.poc.application.service;

import com.card.service.poc.application.port.in.GetUsageResultUseCase;
import org.springframework.stereotype.Service;

@Service
public class GetUsageResultService implements GetUsageResultUseCase {
    @Override
    public String get(String request) {
        return "Usage Result : " + request;
    }
}
