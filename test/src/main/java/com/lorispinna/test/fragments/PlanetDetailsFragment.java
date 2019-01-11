package com.lorispinna.test.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lorispinna.test.R;
import com.lorispinna.test.adapter.PlanetRecyclerAdapter;
import com.lorispinna.test.models.Planet;
import com.lorispinna.test.models.Planets;
import com.squareup.picasso.Picasso;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

public class PlanetDetailsFragment extends Fragment {
    TextView title;
    TextView descript;
    ImageView imageView;

    private float x1,x2;
    static final int MIN_DISTANCE = 150;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_planet_details, container, false);

        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        float deltaX = x2 - x1;
                        if (Math.abs(deltaX) > MIN_DISTANCE && x2 > x1)
                            Toast.makeText(v.getContext(), "swipe", Toast.LENGTH_SHORT).show ();
                        break;
                }
                return true;
            }
        });


        title = view.findViewById(R.id.title_planet_details);
        descript = view.findViewById(R.id.description_planet_details);
        imageView = view.findViewById(R.id.image_planet_details);

        Bundle b = getArguments();
        Planet planet = (Planet) b.getSerializable("PLANET");

        Log.d("PLANET", planet.getName());

        title.setText(planet.getName());
        descript.setText(planet.getDesc());
        Picasso.get().load(planet.getImage()).fit().centerCrop().into(imageView);

        return view;
    }
}
