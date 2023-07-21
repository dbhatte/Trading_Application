package com.deutschebank.trading.rest.service;

import com.deutschebank.trading.algo.AlgoProxy;
import com.deutschebank.trading.rest.dto.StepConfiguration;
import com.deutschebank.trading.signal.ConcreteStrategy;
import com.deutschebank.trading.signal.SignalStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StrategyService {

    @Autowired
    private SignalStrategyFactory signalStrategyFactory;

    public void createStrategy(Integer signal) {
        signalStrategyFactory.putSignalStrategy(signal, new ConcreteStrategy(new ArrayList<>()));
    }

    public void updateStrategy(Integer signal, StepConfiguration stepConfiguration) {
        Runnable runnable = getRunnableFromStep(stepConfiguration);
        signalStrategyFactory.getSignalStrategyOnlyIfPresent(signal)
                .ifPresent(concreteStrategy -> ((ConcreteStrategy) concreteStrategy).addToList(runnable));
    }

    private Runnable getRunnableFromStep(StepConfiguration stepConfiguration) {
        AlgoProxy algoProxy = new AlgoProxy();
        Runnable runnable;
        switch (stepConfiguration.step) {
            case SETUP:
                runnable = algoProxy::setUp;
                break;
            case DO_ALGO:
                runnable = algoProxy::doAlgo;
                break;
            case CANCEL_TRADES:
                runnable = algoProxy::cancelTrades;
                break;
            case REVERSE:
                runnable = algoProxy::reverse;
                break;
            case SUBMIT_TO_MARKET:
                runnable = algoProxy::submitToMarket;
                break;
            case PERFORM_CALC:
                runnable = algoProxy::performCalc;
                break;
            case SET_ALGO_PARAM:
                runnable = () -> algoProxy.setAlgoParam(stepConfiguration.param, stepConfiguration.value);
                break;
            default:
                runnable = () -> {
                };
                break;
        }
        return runnable;
    }

}
