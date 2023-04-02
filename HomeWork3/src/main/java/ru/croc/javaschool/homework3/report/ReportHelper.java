package ru.croc.javaschool.homework3.report;

import ru.croc.javaschool.homework3.Rent;
import ru.croc.javaschool.homework3.interfaces.TransportTyped;
import ru.croc.javaschool.homework3.transport.Transport;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Helps to build report.
 */
public class ReportHelper {
    /**
     * Date of reportation.
     */
    private final Calendar date;

    /**
     * Rental system of report.
     */
    private final Rent rentalSystem;

    public Calendar getDate() {
        return date;
    }

    /**
     * Gives list of typed free transport.
     * @param typeOfTransport some type of transport or it's generation.
     * @return list of selected type transport
     */
    public ArrayList<Transport> getFreeTransportList(Class<?> typeOfTransport){
        var freeTransports = rentalSystem.findFreeTransportUnits(date, TransportTyped.class);
        for (var transport: rentalSystem.getTransportUnits()){
            if (!typeOfTransport.isAssignableFrom(transport.getClass())){
                freeTransports.remove(transport);
            }
        }
        return freeTransports;
    }
    public ArrayList<Transport> getOccupiedTransportList(Class<?> typeOfTransport){
        var occupiedTransports = new ArrayList<Transport>();
        occupiedTransports.addAll(rentalSystem.getTransportUnits());
        var freeTransports = rentalSystem.findFreeTransportUnits(date, TransportTyped.class);
        for (var transport: rentalSystem.getTransportUnits()){
            if (!typeOfTransport.isAssignableFrom(transport.getClass())){
                occupiedTransports.remove(transport);
            }
        }
        occupiedTransports.removeAll(freeTransports);
        return occupiedTransports;
    }

    public ReportHelper(Calendar date, Rent rentalSystem){
        this.date = date;
        this.rentalSystem = rentalSystem;
    }
}
