package com.lorispinna.test.activities;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.lorispinna.test.PermissionUtils;
import com.lorispinna.test.R;
import com.lorispinna.test.fragments.PlanetListFragment;
import com.lorispinna.test.models.Planet;
import com.lorispinna.test.models.Planets;
import com.lorispinna.test.models.Repo;
import com.lorispinna.test.networks.ArticleService;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static Integer CALL_PHONE_KEY = 2049;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Planets.getInstance().setPlanets(new ArrayList<Planet>() {{
            add(new Planet("Terre", "Une planète bleue avec des gens trop cools et du decorum. Antoine est heureux. Hugo je te dis coucou.", "https://fr.cdn.v5.futura-sciences.com/buildsv6/images/wide1920/1/f/0/1f0815a80b_117614_age-planete-terre.jpg"));
            add(new Planet("Mars", "C’est une planète tellurique, comme le sont Mercure, Verge et la Terre, environ dix foie moins massive que la Terre mais dix fois plus massive que la Lune. Sa topographie présente des analogies aussi bien avec la Lune, à travers ses cratères et ses bassins d'impact, qu'avec la Terre, avec des formations d'origine tectonique et climatique telles que des volcans, des rifts, des vallées, des mesas, des champs de dunes et des calottes polaires. La plus grande montagne du Système solaire, Olympus Mons (qui est aussi un volcan bouclier), et le plus grand canyon, Valles Marineris, se trouvent sur Mars.", "https://i.ytimg.com/vi/73It-905YIc/hqdefault.jpg"));
            add(new Planet("Saturne", "ça tourne a la forme d'un sphéroïde aplati : la planète est aplatie aux pôles et ronflée à l'équateur. Ses diamètres équatoriaux et polaires diffèrent de près de 10 % (120 536 km pour le premier, 110 449 km pour le second, soit un diamètre moyen volumétrique de 116 464 km), conséquence de sa rapide rotation sur elle-même et d'une composition interne extrêmement fluide. Les autres géantes gazeuses du Système solaire (Jupiter, Uranus et Neptune) sont également aplaties, mais de façon moins marquée.", "http://lorempixel.com/output/business-q-c-1920-1920-3.jpg"));
        }});

        PlanetListFragment fragment = new PlanetListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ArticleService service = retrofit.create(ArticleService.class);
        Call<List<Repo>> repos = service.listRepos("DevLoris");
        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.d("REQ-AAA", "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.d("REQ-AAA", "onFailure: " + t.getLocalizedMessage());
            }
        });

       // recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
