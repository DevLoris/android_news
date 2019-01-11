package com.lorispinna.test.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.lorispinna.test.R;
import com.lorispinna.test.adapter.PlanetRecyclerAdapter;
import com.lorispinna.test.models.Planets;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PlanetListFragment extends Fragment {
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_planet_list, container, false);


        this.recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        PlanetRecyclerAdapter planetRecyclerAdapter = new PlanetRecyclerAdapter(Planets.getInstance().getPlanets());
        recyclerView.setAdapter(planetRecyclerAdapter);
        return view;
    }


}
