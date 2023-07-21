package com.deutschebank.trading.signal;

import com.deutschebank.trading.algo.AlgoProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
/**
 * This class is the main class to register new Signals
 * They can either be configured manually or added programmatically
 * TODO: Make this class store the programmatically added signals to outside store as they are currently in-memory
 * That will also help in running multiple instances of the application if required
 * */
@Component
public class SignalStrategyFactory {

    private final Map<Integer, AlgoStrategy> strategyMap = new HashMap<>();
    @Autowired
    private AlgoProxy algo;

    public SignalStrategyFactory(AlgoProxy algo) {
        this.algo = algo;
    }

    @PostConstruct
    void init() {
        putSignalStrategy(1, new ConcreteStrategy(List.of(algo::setUp, () -> algo.setAlgoParam(1, 60), algo::performCalc, algo::submitToMarket)));
        putSignalStrategy(2, new ConcreteStrategy(List.of(algo::reverse, () -> algo.setAlgoParam(1, 80), algo::submitToMarket)));
        putSignalStrategy(3, new ConcreteStrategy(List.of(() -> algo.setAlgoParam(1, 90), () -> algo.setAlgoParam(2, 15), algo::performCalc, algo::submitToMarket)));
    }

    public AlgoStrategy getSignalStrategy(int signal) {
        return strategyMap.getOrDefault(signal, new ConcreteStrategy(List.of(algo::cancelTrades)));
    }

    public Optional<AlgoStrategy> getSignalStrategyOnlyIfPresent(int signal) {
        return Optional.ofNullable(strategyMap.get(signal));
    }

    public void putSignalStrategy(Integer signal, ConcreteStrategy concreteStrategy) {
        strategyMap.put(signal, concreteStrategy);
    }

}
