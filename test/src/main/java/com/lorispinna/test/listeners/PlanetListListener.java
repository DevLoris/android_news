package com.lorispinna.test.listeners;

import android.os.Bundle;
import android.view.View;

import com.lorispinna.test.R;
import com.lorispinna.test.activities.MainActivity;
import com.lorispinna.test.fragments.PlanetDetailsFragment;
import com.lorispinna.test.models.Planet;

import androidx.fragment.app.FragmentTransaction;

public class PlanetListListener implements View.OnClickListener {
    private Planet planet;

    public PlanetListListener(Planet planet) {
        this.planet = planet;
    }

    @Override
    public void onClick(View v) {
        MainActivity activity = (MainActivity) v.getContext();
        PlanetDetailsFragment fragment = new PlanetDetailsFragment();
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);

        Bundle bundle = new Bundle();
        bundle.putSerializable("PLANET", planet);
        fragment.setArguments(bundle);

        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
