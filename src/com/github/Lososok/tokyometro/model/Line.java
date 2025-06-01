package com.github.Lososok.tokyometro.model;

import java.util.LinkedList;

public class Line {
    private String name;
    private final LinkedList<Station> stations;

    public Line(String name) {
        this.name = name;
        this.stations = new LinkedList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Station> getStations() {
        return this.stations;
    }

    public void addStationFirst(Station station) {
        this.stations.addFirst(station);
    }

    public void addStation(Integer index, Station station) {
        this.stations.add(index, station);
    }

    public void addStation(Station station) {
        this.stations.addLast(station);
    }

    public Integer getNumberOfStations() {
        return this.stations.size();
    }

    public Integer findStation(String name) {
        for (int index = 0; index < this.getNumberOfStations(); index++) {
            if (this.stations.get(index).getName().equals(name)) { return index; }
        }
        return null;
    }

    public Station getStation(Integer index) {
        return this.stations.get(index);
    }

    public String toString() {
        return "Line{" +
                "name=" + name +
                '}';
    }
}
