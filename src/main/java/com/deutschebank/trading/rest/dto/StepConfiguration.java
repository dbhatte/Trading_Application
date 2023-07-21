package com.deutschebank.trading.rest.dto;

public class StepConfiguration {
    public Step step;

    public Integer param;

    public Integer value;

    public StepConfiguration(Step step, Integer param, Integer value) {
        this.step = step;
        this.param = param;
        this.value = value;
    }
}
