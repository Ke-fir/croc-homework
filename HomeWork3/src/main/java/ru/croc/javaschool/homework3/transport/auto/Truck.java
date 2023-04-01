package ru.croc.javaschool.homework3.transport.auto;

/**
 * Cargo truck.
 */
public class Truck extends Automobile {
    /**
     * How many kilos can be loaded.
     */
    private double loadCapacity;

    /**
     * How many cube meters can be loaded.
     */
    private double cargoDimensions;

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        if (loadCapacity >= 0)
            this.loadCapacity = loadCapacity;
        else
            System.err.println("Negative count of kilograms");
    }

    public double getCargoDimensions() {
        return cargoDimensions;
    }

    public void setCargoDimensions(double cargoDimensions) {
        if (cargoDimensions >= 0)
            this.cargoDimensions = cargoDimensions;
        else
            System.err.println("Negative count of volume");
    }

    /**
     * Truck constructor.
     *
     * @param id              unique positive integer
     * @param rentalPrice     price in rubbles
     * @param brand           name of brand
     * @param model           name of model
     * @param number          car number
     * @param loadCapacity    quantity of kilos that can be loaded
     * @param cargoDimensions volume of possible loaded cargo
     */
    public Truck(int id, double rentalPrice, String brand, String model, String number, double loadCapacity, double cargoDimensions) {
        super(id, rentalPrice, brand, model, number);
        this.setLoadCapacity(loadCapacity);
        this.setCargoDimensions(cargoDimensions);
    }
}
