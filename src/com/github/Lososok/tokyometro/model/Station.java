package com.github.Lososok.tokyometro.model;

public abstract class Station {
    private String name;
    private Line line;
    private Type type;
    private Station[] neighbors;

    public Station(String name, Line line, Type type) {
        this.name = name;
        this.line = line;
        this.type = type;
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

    public Station[] getNeighbors() {
        return this.neighbors;
    }

    public void setNeighbors(Station[] neighbors) {
        this.neighbors = neighbors;
    }

    public abstract String info();
}
