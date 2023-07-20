package com.deutschebank.trading.algo;

import com.deutschebank.trading.signal.DefaultStrategy;
import com.deutschebank.trading.signal.FirstStrategy;
import com.deutschebank.trading.signal.SecondStrategy;
import com.deutschebank.trading.signal.ThirdStrategy;
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
    private FirstStrategy firstStrategy;

    @Autowired
    private SecondStrategy secondStrategy;

    @Autowired
    private ThirdStrategy thirdStrategy;

    @Autowired
    private DefaultStrategy defaultStrategy;

    public void handleSignal(int signal) {

        switch (signal) {
            case 1:
                firstStrategy.execute();
                break;

            case 2:
                secondStrategy.execute();
                break;

            case 3:
                thirdStrategy.execute();
                break;

            default:
                defaultStrategy.execute();
                break;
        }

        algoProxy.doAlgo();
    }
}


