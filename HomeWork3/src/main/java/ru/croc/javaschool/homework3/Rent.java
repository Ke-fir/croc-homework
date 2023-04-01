package ru.croc.javaschool.homework3;

import ru.croc.javaschool.homework3.transport.Transport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Calendar;

/**
 * Class of rental system
 */
public class Rent {
    /**
     * Structure that has days and list of vehicles that have reservations on this day.
     */
    private HashMap<Calendar, ArrayList<Transport>> rentalCalendar;

    /**
     * List of all companies' transport units.
     */
    private ArrayList<Transport> transportUnits;

    public HashMap<Calendar, ArrayList<Transport>> getRentalCalendar() {
        return rentalCalendar;
    }

    /**
     * Adds record about transport reservation on some day to rental calendar.
     *
     * @param day       some day
     * @param transport some transport unit
     */
    public void addBooking(Calendar day, Transport transport) {
        if (rentalCalendar.containsKey(day)) {
            rentalCalendar.get(day).add(transport);
        } else {
            var tempList = new ArrayList<Transport>();
            tempList.add(transport);
            rentalCalendar.put(day, tempList);
        }
    }

    /**
     * Adds record about several transport units reservation on some day to rental calendar.
     *
     * @param day        some day
     * @param transports list of some transport units
     */
    public void addBooking(Calendar day, ArrayList<Transport> transports) {
        if (rentalCalendar.containsKey(day)) {
            for (Transport transport : transports)
                rentalCalendar.get(day).add(transport);
        } else {
            rentalCalendar.put(day, transports);
        }
    }

    public ArrayList<Transport> getTransportUnits() {
        return transportUnits;
    }

    /**
     * Register new transport in companies' base.
     * @param transport some transport
     */
    public void addTransport(Transport transport) {
        if (!transportUnits.contains(transport)) {
            transportUnits.add(transport);
        } else {
            System.err.println("Transport list already contains this transport");
        }
    }

    /**
     * Deducts transport from companies' base.
     * @param transport some transport.
     */
    public void removeTransport(Transport transport){
        if (!transportUnits.contains(transport)) {
            transportUnits.remove(transport);
        } else {
            System.err.println("Transport list doesn't contain this transport");
        }
    }
}
