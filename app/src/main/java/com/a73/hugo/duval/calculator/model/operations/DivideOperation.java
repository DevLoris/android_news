package com.a73.hugo.duval.calculator.model.operations;

public final class DivideOperation extends Operation {

    /**
     *
     */
    DivideOperation() {
        this.label = "/";
    }

    @Override
    public Double calculate(Double firstValue, Double secondValue) {
        return firstValue / secondValue;
    }
}
