package com.a73.hugo.duval.calculator.model;

import com.a73.hugo.duval.calculator.model.operations.Operation;

public final class Calculation {

    private Double firstValue  = 0.0;
    private Double secondValue;
    private Operation operation;

    public Calculation(Double firstValue, Double secondValue, Operation operation) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.operation = operation;
    }

    public Calculation() {

    }
    /**
     *
     * @param firstValue double
     */
    void setFirstValue(Double firstValue) {
        this.firstValue = firstValue;
    }

    /**
     *
     * @param secondValue double
     */
    void setSecondValue(Double secondValue) {
        this.secondValue = secondValue;
    }

    /**
     *
     * @return boolean
     */
    boolean hasSecondValue() {
        return this.secondValue != null;
    }

    /**
     *
     * @return boolean
     */
    boolean hasFirstValue() {
        return this.firstValue != null;
    }


    public Operation getOperation() {
        return operation;
    }

    /**
     *
     * @param operationType String
     */
    void setOperation(String operationType) {
        if(operationType == null) {
            this.operation = null;
            return;
        }
        this.operation = Operation.createOperation(operationType);
    }

    public Double getSecondValue() {
        return secondValue;
    }

    public Double getFirstValue() {
        return firstValue;
    }

    /**
     *
     * @return boolean
     */
    boolean hasOperation() {
        return this.operation != null;
    }

    /**
     *
     * @return String
     */
    String getOperationLabel() {
        return (this.operation == null) ?  null : this.operation.getLabel();
    }

    /**
     *
     * @return Double
     */
    Double processCalculation() {
        return this.operation.calculate(this.firstValue, this.secondValue);
    }

    /**
     *
     */
    void reset() {
        this.firstValue  = 0.0;
        this.operation   = null;
        this.secondValue = null;
    }

    @Override
    public String toString() {
        return this.getFirstValue()+ "" +this.getOperationLabel() + "" + this.getSecondValue();
    }
}
