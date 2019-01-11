package com.lorispinna.test.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lorispinna.test.R;
import com.lorispinna.test.listeners.DialogButtonListener;
import com.lorispinna.test.listeners.PhoneListener;
import com.lorispinna.test.listeners.PlanetListListener;
import com.lorispinna.test.listeners.ShareListener;
import com.lorispinna.test.models.Planet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class PlanetRecyclerAdapter extends RecyclerView.Adapter<PlanetRecyclerAdapter.ViewHandler> {
    private ArrayList<Planet> planets;
    public PlanetRecyclerAdapter(ArrayList<Planet> planets) {
        this.planets = planets;
    }

    @NonNull
    @Override
    public PlanetRecyclerAdapter.ViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.planet_item, parent, false);
        ViewHandler vh = new ViewHandler(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetRecyclerAdapter.ViewHandler holder, int position) {
        holder.bindItem(planets.get(position));
    }

    @Override
    public int getItemCount() {
        return planets.size();
    }

    static class ViewHandler extends RecyclerView.ViewHolder {
        TextView title;
        TextView descript;
        ImageView imageView;
        ImageButton share;
        ImageButton dialog;
        ImageButton phone;
        View v;

        ViewHandler(View v) {
            super(v);
            title = v.findViewById(R.id.title);
            descript = v.findViewById(R.id.description);
            imageView = v.findViewById(R.id.image);
            share = v.findViewById(R.id.share);
            dialog = v.findViewById(R.id.dialog);
            phone = v.findViewById(R.id.phone);
            this.v = v;
        }

        public void bindItem(final Planet planet) {
            title.setText(planet.getName());
            descript.setText(planet.getDesc());
            Picasso.get().load(planet.getImage()).fit().centerCrop().into(imageView);
            share.setOnClickListener(new ShareListener(planet.getName(), planet.getDesc()));
            dialog.setOnClickListener(new DialogButtonListener());
            phone.setOnClickListener(new PhoneListener());
            v.setOnClickListener(new PlanetListListener(planet));
        }
    }
}
