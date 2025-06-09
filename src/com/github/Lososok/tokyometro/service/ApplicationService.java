package com.github.Lososok.tokyometro.service;

import com.github.Lososok.tokyometro.model.Line;
import com.github.Lososok.tokyometro.model.Station;

import java.util.ArrayList;
import java.util.Arrays;

public interface ApplicationService {
    boolean editLineDescription(Line line);
    Station findStation(String station, Line line);
    boolean checkDescriptions(ArrayList<Line> lines);
    boolean parseConfig(ArrayList<Line> lines, ArrayList<Station> stations, String file);
}
