package com.deutschebank.trading.algo;

import com.deutschebank.trading.signal.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class is the main class to handle the signals
 */
@Component
public class Application implements SignalHandler {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private AlgoProxy algoProxy;

    @Autowired
    private SignalStrategyFactory signalStrategyFactory;

    public void handleSignal(int signal) {
        logger.info("Handling signal {}", signal);
        signalStrategyFactory.getSignalStrategy(signal)
                .execute();
        algoProxy.doAlgo();
    }
}


