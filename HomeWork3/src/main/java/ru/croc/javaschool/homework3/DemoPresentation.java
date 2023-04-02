package ru.croc.javaschool.homework3;


import ru.croc.javaschool.homework3.interfaces.AircraftTyped;
import ru.croc.javaschool.homework3.report.Report;
import ru.croc.javaschool.homework3.transport.Transport;
import ru.croc.javaschool.homework3.transport.air.Helicopter;
import ru.croc.javaschool.homework3.transport.air.Jet;
import ru.croc.javaschool.homework3.transport.auto.PassengerCar;
import ru.croc.javaschool.homework3.transport.auto.Truck;
import ru.croc.javaschool.homework3.transport.pmv.EScooter;
import ru.croc.javaschool.homework3.transport.pmv.MonoWheel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Presentation class for rent system
 */
public class DemoPresentation {
    public static void main(String[] args) {
        /* START OF INITIATING TRANSPORT */
        var zubilo = new PassengerCar(0, 2500, "VAZ", "2108", "С234РУ77", 1.2);
        var taxi = new PassengerCar(1, 5000, "Peugeot", "408", "О628АХ123", 3.8);
        var volvoTruck = new Truck(2, 9000, "Volvo", "Super truck", "В656ОС98", 10000,
                4000);
        var kamaz = new Truck(3, 7800, "KamAZ", "Samosval", "Р987КА09", 25000,
                10000);
        var trucks = new ArrayList<Transport>();
        trucks.add(volvoTruck);
        trucks.add(kamaz);
        var suhoi = new Jet(4, 150000, "Suhoi", "Business jet", 2, 24, 5);
        var boeng = new Jet(5, 200000, "Boeng", "737", 4, 58, 3);
        var heli = new Helicopter(6, 180000, "Mi", "8", 1, 6, 2);
        var cargoBob = new Helicopter(7, 260000, "GTA", "5", 2, 10, 2);
        var airCrafts = new ArrayList<Transport>(Arrays.asList(suhoi, boeng, heli, cargoBob));
        var nineScoot = new EScooter(8, 350, "NineBot", "Scooter", 3.2, 40, false);
        var yandexScoot = new EScooter(9, 200, "Yandex", "Scooter", 1.5, 25, true);
        var nineWheel = new MonoWheel(10, 400, "NineBot", "Wheel", 2.1, 60, 35);
        var someWheel = new MonoWheel(11, 300, "Brand", "Model", 1.8, 36, 18);
        var pmvs = new ArrayList<Transport>(Arrays.asList(nineScoot, yandexScoot, nineWheel, someWheel));
        /* END OF INITIATING */

        /* PRESENTATION OF ADDING NEW TRANSPORT*/
        var rentSystem = new Rent();
        rentSystem.addTransport(zubilo); // adding single transports
        rentSystem.addTransport(taxi);
        rentSystem.addTransport(trucks); // adding list of transports
        rentSystem.addTransport(airCrafts);
        rentSystem.addTransport(pmvs);

        /* PRESENTATION OF REMOVING TRANSPORT */
        rentSystem.removeTransport(someWheel); // removing transport

        /* PRESENTATION OF ADDING NEW BOOKING BY SELECTED DATE */
        rentSystem.addBooking(new GregorianCalendar(2023, 03, 03), trucks); // booking trucks on 03.04.2023
        rentSystem.addBooking(new GregorianCalendar(2023,03, 01), rentSystem.getTransportUnits()); // booking all transport on 01.04.2023
        rentSystem.addBooking(new GregorianCalendar(2023,03,03), suhoi); // booking suhoi jet on my friend's birthday
        var dates = new ArrayList<Calendar>(Arrays.asList(new GregorianCalendar(2023, 03, 03), new GregorianCalendar(2023, 03, 07),
                new GregorianCalendar(2023, 03, 21)));
        rentSystem.addBooking(dates, zubilo); // booking vaz 2108 for several days

        /* PRESENTATION OF CHECKING POSSIBILITY OF RENT ON SELECTED DATE */
        var zubiloCheckingResult = rentSystem.checkRentPossibility(new GregorianCalendar(2023, 03, 03), zubilo);

        /* PRESENTATION OF SEARCHING FREE TRANSPORT ON SELECTED DATES */
        var freeTransports = rentSystem.findFreeTransportUnits(dates, AircraftTyped.class);

        /* PRESENTATION OF REPORT */
        var report = new Report(new GregorianCalendar(2023, 03,03), rentSystem);
        report.print();
    }
}