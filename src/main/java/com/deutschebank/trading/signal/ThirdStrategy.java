package com.deutschebank.trading.signal;

import com.deutschebank.trading.algo.AlgoProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThirdStrategy implements AlgoStrategy {

    @Autowired
    private AlgoProxy algoProxy;

    @Override
    public void execute() {
        algoProxy.setAlgoParam(1,90);
        algoProxy.setAlgoParam(2,15);
        algoProxy.performCalc();
        algoProxy.submitToMarket();
    }
}
