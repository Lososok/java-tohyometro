package com.github.Lososok.tokyometro.model;

public class NonCrossingStation extends Station {
    public NonCrossingStation(String name, Line line) {
        super(name, line);
        this.setType(Type.NonCrossing);
    }

    public String info() {
        return getLine().getName() +
                "line " + this.getType() +
                "station " + this.getName();
    }
}
