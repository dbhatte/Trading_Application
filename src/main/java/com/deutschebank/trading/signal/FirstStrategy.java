package com.deutschebank.trading.signal;

import com.deutschebank.trading.algo.AlgoProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FirstStrategy implements AlgoStrategy {

    @Autowired
    private AlgoProxy algoProxy;

    @Override
    public void execute() {
        algoProxy.setUp();
        algoProxy.setAlgoParam(1, 60);
        algoProxy.performCalc();
        algoProxy.submitToMarket();
    }
}
