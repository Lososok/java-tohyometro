package com.github.Lososok.tokyometro.model;

public abstract class Station {
    private String name;
    private Line line;
    private Type type;

    public Station(String name, Line line) {
        this.name = name;
        this.line = line;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Line getLine() {
        return this.line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public abstract String info();

    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", line=" + line +
                ", type=" + type +
                '}';
    }
}
