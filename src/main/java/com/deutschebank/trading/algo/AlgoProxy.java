package com.deutschebank.trading.algo;

import org.springframework.stereotype.Component;

/**
 * This is a Proxy to encapsulate the Algo class from the library.
 * This helps to handle any changes in the Algo classes from affecting the application code
 * and also increases testability of the code
 *
 * */
@Component
public class AlgoProxy {
    private final Algo algo = new Algo();

    public void doAlgo() {
        algo.doAlgo();
    }

    public void cancelTrades() {
        algo.cancelTrades();
    }

    public void reverse() {
        algo.reverse();
    }

    public void submitToMarket() {
        algo.submitToMarket();
    }

    public void performCalc() {
        algo.performCalc();
    }

    public void setUp() {
        algo.setUp();
    }

    public void setAlgoParam(int param, int value) {
        algo.setAlgoParam(param, value);
    }
}
