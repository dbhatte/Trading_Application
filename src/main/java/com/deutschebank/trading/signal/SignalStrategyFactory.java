package com.deutschebank.trading.signal;

import com.deutschebank.trading.algo.AlgoProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SignalStrategyFactory {

    @Autowired
    private AlgoProxy algo;

    public AlgoStrategy getSignalStrategy(int signal) {
        AlgoStrategy algoStrategy;
        switch (signal) {
            case 1:
                algoStrategy = new ConcreteStrategy(List.of(algo::setUp, () -> algo.setAlgoParam(1, 60), algo::performCalc, algo::submitToMarket));
                break;

            case 2:
                algoStrategy = new ConcreteStrategy(List.of(algo::reverse, () -> algo.setAlgoParam(1, 80), algo::submitToMarket));
                break;

            case 3:
                algoStrategy = new ConcreteStrategy(List.of(() -> algo.setAlgoParam(1, 90), () -> algo.setAlgoParam(2, 15), algo::performCalc, algo::submitToMarket));
                break;

            default:
                algoStrategy = new ConcreteStrategy(List.of(algo::cancelTrades));
                break;
        }
        return algoStrategy;
    }
}
