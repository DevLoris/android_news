package com.a73.hugo.duval.calculator.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a73.hugo.duval.calculator.R;
import com.a73.hugo.duval.calculator.model.Calculation;

import java.util.ArrayList;

public class HistoryRecyclerAdapter  extends RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHandler> {
    private ArrayList<Calculation> calculations;
    public HistoryRecyclerAdapter(ArrayList<Calculation> calculations) {
        this.calculations = calculations;
    }

    @NonNull
    @Override
    public HistoryRecyclerAdapter.ViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        ViewHandler vh = new ViewHandler(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryRecyclerAdapter.ViewHandler holder, int position) {
        holder.bindItem(calculations.get(position));
    }

    @Override
    public int getItemCount() {
        return calculations.size();
    }

    static class ViewHandler extends RecyclerView.ViewHolder {
        TextView calculation;
        TextView result;
        View v;

        ViewHandler(View v) {
            super(v);
            calculation = v.findViewById(R.id.calculation);
            result = v.findViewById(R.id.result);
            this.v = v;
        }

        public void bindItem(final Calculation calculation) {
            this.calculation.setText(calculation.toString());
            this.result.setText(calculation.getOperation().calculate(calculation.getFirstValue(), calculation.getSecondValue()).toString());
        }
    }

}
