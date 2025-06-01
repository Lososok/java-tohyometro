package com.github.Lososok.tokyometro.service;

import com.github.Lososok.tokyometro.model.Line;
import com.github.Lososok.tokyometro.model.Station;

import java.util.ArrayList;

public class ApplicationServiceImpl implements ApplicationService{
    public boolean editLineDescription(Line line) {
        return false;
    }

    public Station findStation(Station station, Line line) {    // TODO: add find by trans station
        return line.getStation(line.findStation(station.getName()));
    }

    public boolean checkDescriptions(ArrayList<Line> lines) {
        return false;
    }
}
