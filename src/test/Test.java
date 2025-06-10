package test;

import com.github.Lososok.tokyometro.model.*;
import com.github.Lososok.tokyometro.service.ApplicationService;
import com.github.Lososok.tokyometro.service.ApplicationServiceImpl;

import java.util.ArrayList;

// TODO: хотя бы приличия ради добавить обработку исключений
public class Test {
    private static final ApplicationService app = new ApplicationServiceImpl();

    public static void main(String[] args) {
//        Test.addAndFindTest();
//        Test.findStationTest();
        Test.parseConfigTest("src/test/resources/1");
    }

    private static void addAndFindTest() {
        Line line1 = new Line("red");
        for (int i = 0; i < 3; i++) {
            line1.addStation(new NonCrossingStation(Integer.toString(i), line1));
        }

        System.out.println(line1.getStations());
        System.out.println("0 - " + line1.findStation("0") + ";" +
                " 1 - " + line1.findStation("1") + ";" +
                " 2 - " + line1.findStation("2") + ";" +
                " 4 - " + line1.findStation("4") + ";");

        System.out.println(line1.getStation(line1.findStation("0")).info());
        System.out.println(line1.getStation(line1.findStation("1")).info());
        System.out.println(line1.getStation(line1.findStation("2")).info());

        System.out.println("-----------------------------");

        Line line2 = new Line("green");

        for (int i = 0; i < 3; i++) {
            line2.addStation(new CrossingStation(Integer.toString(2 - i), line2));
            CrossingStation crossStation = (CrossingStation) line2.getStation(line2.findStation(Integer.toString(2 - i)));
            crossStation.addCross(line1.getStation(line1.findStation(Integer.toString(2 - i))));

        }

        System.out.println(line2.getStations());
        System.out.println("0 - " + line2.findStation("0") + ";" +
                " 1 - " + line2.findStation("1") + ";" +
                " 2 - " + line2.findStation("2") + ";" +
                " 4 - " + line2.findStation("4") + ";");

        System.out.println(line2.getStation(line2.findStation("0")).info());
        System.out.println(line2.getStation(line2.findStation("1")).info());
        System.out.println(line2.getStation(line2.findStation("2")).info());

        System.out.println("-----------------------------");

        Line line3 = new Line("blue");

        for (int i = 0; i < 3; i++) {
            line3.addStation(new TransferHub(Integer.toString(i), line3));
            TransferHub transferHub = (TransferHub) line3.getStation(line3.findStation(Integer.toString(i)));
            transferHub.addTransfer(line2.getStation(line2.findStation(Integer.toString((i + 1) % 3))),
                    line2.getStation(line2.findStation(Integer.toString((i + 1) % 3))).getLine());
        }

        System.out.println(line3.getStations());
        System.out.println("0 - " + line3.findStation("0") + ";" +
                " 1 - " + line3.findStation("1") + ";" +
                " 2 - " + line3.findStation("2") + ";" +
                " 4 - " + line3.findStation("4") + ";");

        System.out.println(line3.getStation(line3.findStation("0")).info());
        System.out.println(line3.getStation(line3.findStation("1")).info());
        System.out.println(line3.getStation(line3.findStation("2")).info());

        System.out.println("-----------------------------");
    }
    // TODO: добавить чтение из файла конфигурации метро
    // TODO: после этого нужно переделать тест, чтобы он соответствовал названию
    private static void findStationTest() {
        // чет не придумал как это нормально сделать сейчас


        // ||||||||||                                       ||||||||||
        // ||||||||||                                       ||||||||||
        // \/\/\/\/\/ findStationTest.png -> как я вижу это \/\/\/\/\/
        Line line1 = new Line("red"),
                line2 = new Line("green"),
                line3 = new Line("blue");


        line1.addStation(new CrossingStation("abc", line1));
        line1.addStation(new TransferHub("bac", line1));

        line2.addStation(new TransferHub("cab", line2));
        line2.addStation(new CrossingStation("bab", line2));

        line3.addStation(new CrossingStation("bab", line3));
        line3.addStation(new NonCrossingStation("deb", line3));
        line3.addStation(new CrossingStation("abc", line3));

        ((CrossingStation) app.findStation("abc", line1)).addCross(
                                                                    app.findStation("abc", line3)
        );
        ((TransferHub) app.findStation("bac", line1)).addTransfer(
                                                                    app.findStation("cab", line2),
                                                                    app.findStation("cab", line2).getLine()
        );
        ((TransferHub) app.findStation("cab", line2)).addTransfer(
                                                                    app.findStation("bac", line1),
                                                                    app.findStation("bac", line1).getLine()
        );
        ((CrossingStation) app.findStation("bab", line2)).addCross(
                                                                    app.findStation("bab", line3)
        );
        ((CrossingStation) app.findStation("bab", line3)).addCross(
                                                                    app.findStation("bab", line2)
        );
        ((CrossingStation) app.findStation("abc", line3)).addCross(
                                                                    app.findStation("abc", line1)
        );

        System.out.println(line1 + " " + line2 + " " + line3);

        System.out.println("Normal");
        System.out.println(app.findStation("abc", line1));
        System.out.println(app.findStation("bac", line1));

        System.out.println(app.findStation("cab", line2));
        System.out.println(app.findStation("bab", line2));

        System.out.println(app.findStation("abc", line3));
        System.out.println(app.findStation("bab", line3));
        System.out.println(app.findStation("deb", line3));

        System.out.println("By transfer");

        System.out.println(app.findStation("cab", line1));
        System.out.println(app.findStation("bac", line2));
    }

    private static void parseConfigTest(String file) {
        var lines = new ArrayList<Line>();
        var stations = new ArrayList<Station>();
        app.parseConfig(lines, stations, file);

        System.out.println(lines.size());
        System.out.println(stations.size());

        System.out.println("-------------------");
        lines.forEach(System.out::println);
        stations.forEach(x -> System.out.println(x.info()));
    }
}
