package com.github.Lososok.tokyometro.model;

import java.util.ArrayList;

public class Line {
    private String name;
    private ArrayList<Station> stations; // must be doubly linked list

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Station> getStations() {
        return this.stations;
    }

    public void addStation(Station station) {
        this.stations.add(station);
    }

//    public Station findStation(String name) {
//
//    }
}
