package com.a73.hugo.duval.calculator.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.a73.hugo.duval.calculator.R;
import com.a73.hugo.duval.calculator.adapters.HistoryRecyclerAdapter;
import com.a73.hugo.duval.calculator.model.HistorySingleton;

public class HistoryActivity extends CommonActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        this.recyclerView = findViewById(R.id.history_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        HistoryRecyclerAdapter historyRecyclerAdapter = new HistoryRecyclerAdapter(HistorySingleton.getInstance().getCalculations());
        recyclerView.setAdapter(historyRecyclerAdapter);
    }

    @Override
    public void clickNightHandler(View v) {
        super.clickNightHandler(v);
    }

    @Override
    public void clickMenuHandler(View v) {
        super.clickMenuHandler(v);
    }
}
