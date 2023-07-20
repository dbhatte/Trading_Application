package com.deutschebank.trading.signal;

import com.deutschebank.trading.algo.AlgoProxy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ConcreteStrategyTest {

    private ConcreteStrategy concreteStrategy;

    @Mock
    private AlgoProxy algo;

    @Test
    void testDefaultStrategy() {
        concreteStrategy = new ConcreteStrategy(List.of(algo::cancelTrades));

        concreteStrategy.execute();

        Mockito.verify(algo).cancelTrades();
        Mockito.verify(algo, Mockito.never()).submitToMarket();
    }

    @Test
    void testFirstSignal() {
        concreteStrategy = new ConcreteStrategy(List.of(algo::setUp, () -> algo.setAlgoParam(1, 60), algo::performCalc, algo::submitToMarket));

        concreteStrategy.execute();

        Mockito.verify(algo).setUp();
        Mockito.verify(algo).setAlgoParam(1, 60);
        Mockito.verify(algo).performCalc();
        Mockito.verify(algo).submitToMarket();
        Mockito.verify(algo, Mockito.never()).reverse();
    }
}
