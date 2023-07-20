package com.deutschebank.trading.signal;

import java.util.List;

public class ConcreteStrategy implements AlgoStrategy {
    private final List<Runnable> list;

    public ConcreteStrategy(List<Runnable> list) {
        this.list = list;
    }

    public void addToList(Runnable runnable) {
        this.list.add(runnable);
    }

    @Override
    public void execute() {
        this.list.forEach(Runnable::run);
    }
}
