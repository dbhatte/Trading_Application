package com.deutschebank.trading.algo;

import com.deutschebank.trading.signal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This is your team’s code and should be changed as you see fit.
 */
@Component
public class Application implements SignalHandler {
    @Autowired
    private AlgoProxy algoProxy;

    @Autowired
    private SignalStrategyFactory signalStrategyFactory;

    public void handleSignal(int signal) {
        signalStrategyFactory.getSignalStrategy(signal).execute();
        algoProxy.doAlgo();
    }
}


