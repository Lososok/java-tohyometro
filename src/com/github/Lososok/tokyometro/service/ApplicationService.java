package com.github.Lososok.tokyometro.service;

import com.github.Lososok.tokyometro.model.Line;
import com.github.Lososok.tokyometro.model.Station;

import java.util.ArrayList;

public interface ApplicationService {
    boolean editLineDescription(Line line);
    Station findStation(Station station, Line line);
    boolean checkDescriptions(ArrayList<Line> lines);
}
