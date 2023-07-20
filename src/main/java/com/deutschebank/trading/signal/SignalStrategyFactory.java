package com.deutschebank.trading.signal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignalStrategyFactory {
    @Autowired
    private FirstStrategy firstStrategy;

    @Autowired
    private SecondStrategy secondStrategy;

    @Autowired
    private ThirdStrategy thirdStrategy;

    @Autowired
    private DefaultStrategy defaultStrategy;

    public AlgoStrategy getSignalStrategy(int signal) {
        AlgoStrategy algoStrategy;
        switch (signal) {
            case 1:
                algoStrategy = firstStrategy;
                break;

            case 2:
                algoStrategy = secondStrategy;
                break;

            case 3:
                algoStrategy = thirdStrategy;
                break;

            default:
                algoStrategy = defaultStrategy;
                break;
        }
        return algoStrategy;
    }
}
