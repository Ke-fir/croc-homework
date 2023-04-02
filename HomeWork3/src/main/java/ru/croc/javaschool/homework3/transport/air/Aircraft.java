package ru.croc.javaschool.homework3.transport.air;

import ru.croc.javaschool.homework3.interfaces.AircraftTyped;
import ru.croc.javaschool.homework3.transport.Transport;

/**
 * Abstract class of air transport that extends basic transport
 */
public abstract class Aircraft extends Transport implements AircraftTyped {
    private int numberOfSeats;
    private int numberOfEngines;

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        if (numberOfSeats > 0)
            this.numberOfSeats = numberOfSeats;
        else
            System.err.println("Invalid number of seats");
    }

    public int getNumberOfEngines() {
        return numberOfEngines;
    }

    public void setNumberOfEngines(int numberOfEngines) {
        if (numberOfEngines > 0)
            this.numberOfEngines = numberOfEngines;
        else
            System.err.println("Invalid number of seats");
    }

    /**
     * Aircraft constructor.
     *
     * @param id              unique positive integer
     * @param rentalPrice     price in rubbles
     * @param brand           name of brand
     * @param model           name of model
     * @param numberOfEngines positive number of engines
     * @param numberOfSeats   positive number of seats
     */
    public Aircraft(int id, double rentalPrice, String brand, String model, int numberOfEngines, int numberOfSeats) {
        super(id, rentalPrice, brand, model);
        setNumberOfEngines(numberOfEngines);
        setNumberOfSeats(numberOfSeats);
    }
}
