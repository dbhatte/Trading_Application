package com.deutschebank.trading.rest.controller;

import com.deutschebank.trading.rest.dto.StepConfiguration;
import com.deutschebank.trading.rest.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * Controller to create and configure signals
 * This is a very basic implementation
 * TODO add delete/list etc
 * */
@RestController
public class StrategyController {

    @Autowired
    private StrategyService strategyService;

    @PostMapping("/api/strategy/{signal}")
    ResponseEntity<String> create(@PathVariable Integer signal) {
        strategyService.createStrategy(signal);
        return ResponseEntity.accepted().build();
    }

    @PutMapping(value = "/api/strategy/{signal}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> update(@PathVariable Integer signal, @RequestBody StepConfiguration stepConfiguration) {
        strategyService.updateStrategy(signal, stepConfiguration);
        return ResponseEntity.accepted().build();
    }

}
