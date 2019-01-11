package com.a73.hugo.duval.calculator.model.operations;

public final class MultiplyOperation extends Operation {

    /**
     *
     */
    MultiplyOperation() {
        this.label = "x";
    }

    @Override
    public Double calculate(Double firstValue, Double secondValue) {
        return firstValue * secondValue;
    }
}
