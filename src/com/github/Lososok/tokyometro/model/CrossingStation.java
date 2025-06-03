package com.github.Lososok.tokyometro.model;

import java.util.Arrays;

public class CrossingStation extends Station{
    private final Station[] cross;

    public CrossingStation(String name, Line line) {
        super(name, line);
        this.setType(Type.Crossing);
        this.cross = new Station[3];
    }

    public Station[] getCross() {
        return cross;
    }

    public void addCross(Station station) {
        for (int index = 0; index < 3; index++) {
            if (this.getCross()[index] == null) {
                this.getCross()[index] = station;
                return;
            }
        }
    }

    public String info() {
        return getLine().getName() +
                " line " + this.getType() +
                " station " + this.getName() +
                " with crosses: " +
                Arrays.toString(this.getCross()).replaceAll(", null", "");
    }
}
