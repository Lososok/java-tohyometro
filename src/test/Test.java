package test;

import com.github.Lososok.tokyometro.model.*;
import com.github.Lososok.tokyometro.service.ApplicationService;
import com.github.Lososok.tokyometro.service.ApplicationServiceImpl;

public class Test {
    private static final ApplicationService app = new ApplicationServiceImpl();

    public static void main(String[] args) {
        Test.addAndFindTest();
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
}
