package com.deutschebank.trading.algo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
public class ApplicationTest {

    @SpyBean
    private AlgoProxy algo;

    @Autowired
    private Application application;

    @Test
    void testDefaultStrategy() {
        application.handleSignal(-1);

        Mockito.verify(algo).cancelTrades();
        Mockito.verify(algo).doAlgo();
    }

    @Test
    void testFirstSignal() {
        application.handleSignal(1);

        Mockito.verify(algo).setUp();
        Mockito.verify(algo).setAlgoParam(1, 60);
        Mockito.verify(algo).performCalc();
        Mockito.verify(algo).submitToMarket();
        Mockito.verify(algo).doAlgo();
        Mockito.verify(algo, Mockito.never()).reverse();
    }

    @Test
    void testSecondSignal() {
        application.handleSignal(2);

        Mockito.verify(algo).reverse();
        Mockito.verify(algo).setAlgoParam(1, 80);
        Mockito.verify(algo).submitToMarket();
        Mockito.verify(algo).doAlgo();
        Mockito.verify(algo, Mockito.never()).performCalc();
    }

    @Test
    void testThirdSignal() {
        application.handleSignal(3);

        Mockito.verify(algo).setAlgoParam(1, 90);
        Mockito.verify(algo).setAlgoParam(2, 15);
        Mockito.verify(algo).performCalc();
        Mockito.verify(algo).submitToMarket();
        Mockito.verify(algo).doAlgo();
    }
}
