package com.canok.fraud.controller;

import com.canok.fraud.service.FraudCheckService;
import com.canok.clients.dto.FraudCheckResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@RequiredArgsConstructor
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @GetMapping(path ="{customerId}" )
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Long customerId){
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
