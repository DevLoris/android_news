package com.a73.hugo.duval.calculator.model;

import java.util.ArrayList;
import java.util.List;

public class HistorySingleton {
    private static final HistorySingleton ourInstance = new HistorySingleton();

    public static HistorySingleton getInstance() {
        return ourInstance;
    }

    private ArrayList<Calculation> calculations = new ArrayList<>();

    private HistorySingleton() {
    }

    public ArrayList<Calculation> getCalculations() {
        return calculations;
    }

    public void addCalculation(Calculation calculation) {
        this.calculations.add(calculation);
    }

    public void setCalculations(ArrayList<Calculation> calculations) {
        this.calculations = calculations;
    }
}
