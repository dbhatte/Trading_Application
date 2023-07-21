package com.deutschebank.trading.rest.controller;

import com.deutschebank.trading.algo.AlgoProxy;
import com.deutschebank.trading.rest.dto.StepConfiguration;
import com.deutschebank.trading.signal.ConcreteStrategy;
import com.deutschebank.trading.signal.SignalStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class StrategyController {

    @Autowired
    private SignalStrategyFactory signalStrategyFactory;

    @PostMapping("/api/strategy/{signal}")
    ResponseEntity<String> create(@PathVariable Integer signal) {
        signalStrategyFactory.putSignalStrategy(signal, new ConcreteStrategy(new ArrayList<>()));
        return ResponseEntity.accepted().build();
    }

    @PutMapping(value = "/api/strategy/{signal}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> update(@PathVariable Integer signal, @RequestBody StepConfiguration stepConfiguration) {
        AlgoProxy algoProxy = new AlgoProxy();
        Runnable runnable;
        switch (stepConfiguration.step) {
            case SETUP:
                runnable = algoProxy::setUp;
                break;
            case DO_ALGO:
                runnable = algoProxy::doAlgo;
                break;
            case CANCEL_TRADES:
                runnable = algoProxy::cancelTrades;
                break;
            case REVERSE:
                runnable = algoProxy::reverse;
                break;
            case SUBMIT_TO_MARKET:
                runnable = algoProxy::submitToMarket;
                break;
            case PERFORM_CALC:
                runnable = algoProxy::performCalc;
                break;
            case SET_ALGO_PARAM:
                runnable = () -> algoProxy.setAlgoParam(stepConfiguration.param, stepConfiguration.value);
                break;
            default:
                runnable = () -> {
                };
                break;
        }
        signalStrategyFactory.getSignalStrategyOnlyIfPresent(signal).ifPresent(concreteStrategy -> ((ConcreteStrategy) concreteStrategy).addToList(runnable));
        return ResponseEntity.accepted().build();
    }
}
