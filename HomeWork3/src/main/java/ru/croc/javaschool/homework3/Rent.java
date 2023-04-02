package ru.croc.javaschool.homework3;

import ru.croc.javaschool.homework3.transport.Transport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Calendar;
import java.util.List;

/**
 * Class of rental system
 */
public class Rent {
    /**
     * Structure that has days and list of vehicles that have reservations on this day.
     */
    private HashMap<Calendar, ArrayList<Transport>> rentalCalendar = new HashMap<>();

    /**
     * List of all company's transport units.
     */
    private ArrayList<Transport> transportUnits = new ArrayList<>();

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
            if (this.checkRentPossibility(day, transport)) {
                rentalCalendar.get(day).add(transport);
            } else {
                System.err.println("This transport is already booked at that day");
            }
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
        for (Transport transport : transports) {
            addBooking(day, transport);
        }
    }

    public void addBooking(ArrayList<Calendar> days, Transport transport) {
        for (var day : days) {
            addBooking(day, transport);
        }
    }

    public ArrayList<Transport> getTransportUnits() {
        return transportUnits;
    }

    /**
     * Register new transport unit in company's base.
     *
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
     * Regs list of new transport units in company's base.
     *
     * @param transportUnits list of new transport units.
     */
    public void addTransport(ArrayList<Transport> transportUnits){
        for (var transport: transportUnits){
            addTransport(transport);
        }
    }

    /**
     * Deducts transport from company's base.
     *
     * @param transport some transport.
     */
    public void removeTransport(Transport transport) {
        if (transportUnits.contains(transport)) {
            transportUnits.remove(transport);
        } else {
            System.err.println("Transport list doesn't contain transport with ID: " + transport.getId());
        }
    }

    /**
     * Checks possibility of rent of the transport in selected date.
     *
     * @param transport some transport.
     * @param day       some day.
     * @return true if transport is free at selected day.
     */
    public boolean checkRentPossibility(Calendar day, Transport transport) {
        return !rentalCalendar.get(day).contains(transport);
    }

    /**
     * Checks possibility of rent of the transport in list of dates
     * (from explanation of the task in telegram).
     *
     * @param transport some transport.
     * @param days      list of dates.
     * @return true if transport is free on all selected days.
     */
    public boolean checkRentPossibility(List<Calendar> days, Transport transport) {
        boolean tempFlag = true;
        for (var day : days) {
            tempFlag &= checkRentPossibility(day, transport);
        }
        return tempFlag;
    }

    /**
     * Finds free transport at selected date.
     * @param day some date.
     * @param typeOfTransport plug to take type.
     * @return list of free transport.
     */
    public ArrayList<Transport> findFreeTransportUnits(Calendar day, Class<?> typeOfTransport) {
        var freeTransportUnits = new ArrayList<Transport>();
        /* Firstly we fill all transport with selected type */
        for (var transport: transportUnits){
            if (transport.getClass() == typeOfTransport){
                freeTransportUnits.add(transport);
            }
        }
        /* Then we remove all rented units */
        if (rentalCalendar.containsKey(day)) {
            for (var transport : rentalCalendar.get(day)) {
                if (transport.getClass() == typeOfTransport){
                    freeTransportUnits.remove(transport);
                }
            }
        }
        /* And return remaining transport */
        return freeTransportUnits;
    }

    public HashMap<Calendar, ArrayList<Transport>> findFreeTransportUnits(ArrayList<Calendar> days, Class<?> typeOfTransport){
        var freeTransportCalendar = new HashMap<Calendar, ArrayList<Transport>>();
        for (var day: days){
            freeTransportCalendar.put(day, findFreeTransportUnits(day, typeOfTransport));
        }
        return freeTransportCalendar;
    }

    /**
     * Rent constructor.
     * @param transportUnits list of transport.
     */
    public Rent(ArrayList<Transport> transportUnits)
    {
        addTransport(transportUnits);
    }

    public Rent(){}
}
