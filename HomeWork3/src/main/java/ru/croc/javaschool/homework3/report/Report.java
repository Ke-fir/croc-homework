package ru.croc.javaschool.homework3.report;

import ru.croc.javaschool.homework3.Rent;
import ru.croc.javaschool.homework3.interfaces.TransportTyped;
import ru.croc.javaschool.homework3.transport.air.Helicopter;
import ru.croc.javaschool.homework3.transport.air.Jet;
import ru.croc.javaschool.homework3.transport.auto.PassengerCar;
import ru.croc.javaschool.homework3.transport.auto.Truck;
import ru.croc.javaschool.homework3.transport.pmv.EScooter;
import ru.croc.javaschool.homework3.transport.pmv.MonoWheel;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Report builder.
 */
public class Report {
    /**
     * Text of report.
     */
    private String report;

    /**
     * System for which report is made.
     */
    private final ReportHelper helper;

    public void print(){
        System.out.println(report);
    }

    /**
     * Builds report by joining it's header and body.
     */
    private void buildReport() {
        var text = new StringBuilder();
        text.append(buildHead());
        text.append(buildBody());
        this.report = text.toString();
    }

    private String buildHead(){
        var dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        var timeFormat = new SimpleDateFormat(" HH:mm:ss");
        var now = Calendar.getInstance();
        var head =
                        "-------------------------------------------------------------------\n" +
                        "************************** REPORT *********************************\n" +
                        "-------------------------------------------------------------------\n" +
                        "Date of reportation: " + dateFormat.format(helper.getDate().getTime()) + "\n" +
                        "Current date&time: " + dateFormat.format(now.getTime()) +
                                timeFormat.format(now.getTime()) + "\n" +
                        "--------------------------------------------------------------------\n";
        return head;
    }

    private String buildBody(){
        var body =
                "Total number of booked transport: " + (helper.getOccupiedTransportList(TransportTyped.class).size()) + "\n" +
                "Total number of free transport: " + (helper.getFreeTransportList(TransportTyped.class).size()) + "\n" +
                "--------------------------------------------------------------------\n\n" +

                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~AUTOMOBILES~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n" +
                "# PASSENGER CARS #\n" +
                        "Number of occupied cars: " + helper.getOccupiedTransportList(PassengerCar.class).size() + "\n" +
                        helper.getOccupiedTransportList(PassengerCar.class).toString() + "\n" +
                        "Number of free cars: " + helper.getFreeTransportList(PassengerCar.class).size() + "\n" +
                        helper.getFreeTransportList(PassengerCar.class).toString() + "\n\n" +
                "# TRUCKS #\n" +
                        "Number of occupied trucks: " + helper.getOccupiedTransportList(Truck.class).size() + "\n" +
                        helper.getOccupiedTransportList(Truck.class).toString() + "\n" +
                        "Number of free trucks: " + helper.getFreeTransportList(Truck.class).size() + "\n" +
                        helper.getFreeTransportList(Truck.class).toString() + "\n\n" +

                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~AIRCRAFTS~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n" +
                "# JETS #\n" +
                        "Number of occupied jets: " + helper.getOccupiedTransportList(Jet.class).size() + "\n" +
                        helper.getOccupiedTransportList(Jet.class).toString() + "\n" +
                        "Number of free jets: " + helper.getFreeTransportList(Jet.class).size() + "\n" +
                        helper.getFreeTransportList(Jet.class).toString() + "\n\n" +
                "# HELICOPTERS #\n" +
                        "Number of occupied helicopters: " + helper.getOccupiedTransportList(Helicopter.class).size() + "\n" +
                        helper.getOccupiedTransportList(Helicopter.class).toString() + "\n" +
                        "Number of free helicopters: " + helper.getFreeTransportList(Helicopter.class).size() + "\n" +
                        helper.getFreeTransportList(Helicopter.class).toString() + "\n\n" +

                "~~~~~~~~~~~~~~~~~~~~~~~PERSONAL MOBILITY VEHICLES~~~~~~~~~~~~~~~~~~~~~~~\n\n" +
                "# ELECTRIC SCOOTERS #\n" +
                        "Number of occupied scooters: " + helper.getOccupiedTransportList(EScooter.class).size() + "\n" +
                        helper.getOccupiedTransportList(EScooter.class).toString() + "\n" +
                        "Number of free autos: " + helper.getFreeTransportList(EScooter.class).size() + "\n" +
                        helper.getFreeTransportList(EScooter.class).toString() + "\n\n" +
                "# MONO WHEELS #\n" +
                        "Number of occupied wheels: " + helper.getOccupiedTransportList(MonoWheel.class).size() + "\n" +
                        helper.getOccupiedTransportList(MonoWheel.class).toString() + "\n" +
                        "Number of free wheels: " + helper.getFreeTransportList(MonoWheel.class).size() + "\n" +
                        helper.getFreeTransportList(MonoWheel.class).toString() + "\n\n";

        return body;
    }

    public Report(Calendar date, Rent rentalSystem) {
        helper = new ReportHelper(date, rentalSystem);
        buildReport();
    }
}

