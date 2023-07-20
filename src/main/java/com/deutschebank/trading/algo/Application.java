package com.deutschebank.trading.algo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This is your team’s code and should be changed as you see fit.
 */
@Component
public
class Application implements SignalHandler {
    @Autowired
    private AlgoProxy algoProxy;

    public void handleSignal(int signal) {

        switch (signal) {
            case 1:
                algoProxy.setUp();
                algoProxy.setAlgoParam(1,60);
                algoProxy.performCalc();
                algoProxy.submitToMarket();
                break;

            case 2:
                algoProxy.reverse();
                algoProxy.setAlgoParam(1,80);
                algoProxy.submitToMarket();
                break;

            case 3:
                algoProxy.setAlgoParam(1,90);
                algoProxy.setAlgoParam(2,15);
                algoProxy.performCalc();
                algoProxy.submitToMarket();
                break;

            default:
                algoProxy.cancelTrades();
                break;
        }

        algoProxy.doAlgo();
    }
}


