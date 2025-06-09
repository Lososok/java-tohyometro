package com.github.Lososok.tokyometro.service;

import com.github.Lososok.tokyometro.model.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.*;

public class ApplicationServiceImpl implements ApplicationService {
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

    /// file with structure like:
    /// <p>lineName-stationName-type:lineName_cross|transferStationName,...,...</p>
    /// <p>...</p>
    /// <p>num of lines in file == num of unique pair line-station</p>
    public boolean parseConfig(ArrayList<Line> lines, ArrayList<Station> stations, String file) {
        try {
            var reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!parseStation(lines, stations, line)) return false;
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    //TODO: test it
    private boolean parseStation(ArrayList<Line> lines, ArrayList<Station> stations, String parsLine) {
        String[] split = parsLine.split("[-,]");
        Line line = lines.stream().anyMatch(x -> x.getName().equals(split[0])) ?
                lines.stream().filter(x -> x.getName().equals(split[0])).findFirst().get() :
                new Line(split[0]);
        if (stations.stream().anyMatch(x -> x.getName().equals(split[1]))) return false;
        // repeating the code, I know
        switch (split[2]) {
            case "NonCrossing":
                stations.add(new NonCrossingStation(split[1], line));
                break;
            case "Crossing":
                CrossingStation station1 = new CrossingStation(split[1], line);
                for (int i = 3; i < split.length; i++) {
                    String[] crosses = split[i].split("_");
                    Line crossLine = lines.stream().anyMatch(x -> x.getName().equals(crosses[0])) ?
                            lines.stream().filter(x -> x.getName().equals(crosses[0])).findFirst().get() :
                            null;
                    Station crossStation = stations.stream().anyMatch(x -> x.getName().equals(crosses[1])) ?
                            stations.stream().filter(x -> x.getName().equals(crosses[1])).findFirst().get() :
                            null;
                    if (crossLine == null || crossStation == null || crossStation.getType() != Type.Crossing)
                        return false;
                    station1.addCross(crossStation);
                    ((CrossingStation) crossStation).addCross(station1);
                    stations.add(station1);
                }
                break;
            case "Transfer":
                TransferHub station2 = new TransferHub(split[1], line);
                for (int i = 3; i < split.length; i++) {
                    String[] transfers = split[i].split("_");
                    Line transferLine = lines.stream().anyMatch(x -> x.getName().equals(transfers[0])) ?
                            lines.stream().filter(x -> x.getName().equals(transfers[0])).findFirst().get() :
                            null;
                    Station transferStation = stations.stream().anyMatch(x -> x.getName().equals(transfers[1])) ?
                            stations.stream().filter(x -> x.getName().equals(transfers[1])).findFirst().get() :
                            null;
                    if (transferLine == null || transferStation == null || transferStation.getType() != Type.Transfer)
                        return false;
                    station2.addTransfer(transferStation, transferLine);
                    ((TransferHub) transferStation).addTransfer(station2, transferLine);
                    stations.add(station2);
                }
                break;
            default:
                return false;
        }
        return true;
    }
}
