package com.github.Lososok.tokyometro.model;

import java.util.HashMap;

public class TransferHub extends Station{
    private final HashMap<Station, Line> transfers;

    public TransferHub(String name, Line line) {
        super(name, line);
        this.setType(Type.Transfer);
        this.transfers = new HashMap<>();
    }

    public HashMap<Station, Line> getTransfers() {
        return this.transfers;
    }

    public void addTransfer(Station station, Line line) {
        if (this.transfers.size() < 3) {
            this.transfers.put(station, line);
        }
    }

    public String info() {
        return getLine().getName() +
                " line " + this.getType() +
                " station " + this.getName() +
                " with transfers: " +
                this.getTransfers().keySet();
    }
}
