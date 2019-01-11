package com.a73.hugo.duval.calculator.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.a73.hugo.duval.calculator.R;
import com.a73.hugo.duval.calculator.model.Calculation;
import com.a73.hugo.duval.calculator.model.Calculator;
import com.a73.hugo.duval.calculator.model.HistorySingleton;
import com.a73.hugo.duval.calculator.model.operations.AddOperation;
import com.a73.hugo.duval.calculator.model.operations.Operation;

public class MainActivity extends CommonActivity  {

    TextView textView;
    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.textView   = findViewById(R.id.result);
        this.calculator = new Calculator(textView);

        HistorySingleton.getInstance().addCalculation(new Calculation(2D, 3D, new Operation() {
            @Override
            public Double calculate(Double firstValue, Double secondValue) {
                return firstValue + secondValue;
            }
        }));
    }

    @Override
    public void clickNightHandler(View v) {
        super.clickNightHandler(v);
    }

    @Override
    public void clickMenuHandler(View v) {
        super.clickMenuHandler(v);
    }

    /**
     *
     * @param v View
     */
    public void clickHandler(View v) {
        calculator.clickHandler(v.getTag().toString());
    }
}
