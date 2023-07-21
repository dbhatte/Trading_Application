package com.deutschebank.trading.signal;

import com.deutschebank.trading.algo.AlgoProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class SignalStrategyFactoryTest {

    public static final int RANDOM_SIGNAL = 4;
    @Mock
    private AlgoProxy algo;

    private SignalStrategyFactory signalStrategyFactory;

    @BeforeEach
    void init() {
        signalStrategyFactory = new SignalStrategyFactory(algo);
    }

    @Test
    void testConfiguredSignal() {
        ConcreteStrategy concreteStrategy = new ConcreteStrategy(
                List.of(algo::setUp, () -> algo.setAlgoParam(1, 30), algo::performCalc, algo::submitToMarket)
        );
        signalStrategyFactory.putSignalStrategy(RANDOM_SIGNAL, concreteStrategy);

        signalStrategyFactory.getSignalStrategy(RANDOM_SIGNAL).execute();

        Mockito.verify(algo).setUp();
        Mockito.verify(algo).setAlgoParam(1, 30);
        Mockito.verify(algo).performCalc();
        Mockito.verify(algo).submitToMarket();
        Mockito.verify(algo, Mockito.never()).reverse();
    }
}
