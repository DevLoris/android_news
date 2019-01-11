package com.lorispinna.test.models;

import java.util.ArrayList;

public class Planets {
    private static final Planets ourInstance = new Planets();
    private ArrayList<Planet> planets;

    public static Planets getInstance() {
        return ourInstance;
    }

    private Planets() {
    }

    public ArrayList<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(ArrayList<Planet> planets) {
        this.planets = planets;
    }
}
