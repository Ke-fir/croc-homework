package ru.croc.javaschool.homework3.transport.air;

/**
 * Business jet.
 */
public class Jet extends Aircraft {
    /**
     * Assessment of eco in range of [0;10]
     */
    private double ecologyClass;

    public double getEcologyClass() {
        return ecologyClass;
    }

    public void setEcologyClass(double ecologyClass) {
        if (ecologyClass >= 0 && ecologyClass <= 10)
            this.ecologyClass = ecologyClass;
        else
            System.err.println("Invalid value of ecology class");
    }

    /**
     * Jet constructor.
     *
     * @param id              unique positive integer
     * @param rentalPrice     price in rubbles
     * @param brand           name of brand
     * @param model           name of model
     * @param numberOfEngines positive number of engines
     * @param numberOfSeats   positive number of seats
     * @param ecologyClass    positive assessment of eco friendliness
     */
    public Jet(int id, double rentalPrice, String brand, String model, int numberOfEngines, int numberOfSeats, double ecologyClass) {
        super(id, rentalPrice, brand, model, numberOfEngines, numberOfSeats);
        setEcologyClass(ecologyClass);
    }
}
