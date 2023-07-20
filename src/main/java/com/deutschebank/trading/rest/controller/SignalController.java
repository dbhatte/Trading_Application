package com.deutschebank.trading.rest.controller;

import com.deutschebank.trading.algo.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class SignalController {

    @Autowired
    private Application application;

    @PostMapping("/api/signal/{signal}")
    public ResponseEntity<String> handleSignal(@PathVariable final Integer signal) {
        application.handleSignal(signal);
        return ResponseEntity.accepted().build();
    }
}
