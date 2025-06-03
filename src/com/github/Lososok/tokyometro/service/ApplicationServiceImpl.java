package com.github.Lososok.tokyometro.service;

import com.github.Lososok.tokyometro.model.Line;
import com.github.Lososok.tokyometro.model.Station;
import com.github.Lososok.tokyometro.model.TransferHub;

import java.util.*;

public class ApplicationServiceImpl implements ApplicationService{
    public boolean editLineDescription(Line line) {
        return false;
    }

    public Station findStation(String station, Line line) {
        if (line.findStation(station) == null) {
            return line.getStations().stream()
                    .filter((s) -> s instanceof TransferHub)
                    .map((s) -> ((TransferHub) s).getTransfers())
                    .map(HashMap::keySet)
                    .flatMap(Collection::stream)
                    .filter((s) -> Objects.equals(s.getName(), station))
                    .findAny().orElse(null);
        }
        return line.getStation(line.findStation(station));
    }

    public boolean checkDescriptions(ArrayList<Line> lines) {
        return false;
    }
}
