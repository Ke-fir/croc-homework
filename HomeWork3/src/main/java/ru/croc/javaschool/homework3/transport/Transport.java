package ru.croc.javaschool.homework3.transport;

/**
 * Base class for all transports.
 */
public abstract class Transport {
    /**
     * Transport ID
     * Should be unique positive integer.
     */
    private int id;

    /**
     * Prise of rent per day in rubbles.
     */
    protected double rentalPrice;
    /**
     * Name of brand.
     */
    protected final String brand;
    /**
     * Name of model.
     */
    protected final String model;

    public int getId() {
        return id;
    }

    /**
     * Sets checked ID value.
     *
     * @param id should be positive integer
     */
    /* Is private to simulate final but with condition on input value*/
    private void setId(int id) {
        if (id >= 0)
            this.id = id;
        else
            System.err.println("Negative ID");
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    /**
     * Sets checked rental price value.
     *
     * @param rentalPrice
     */
    public void setRentalPrice(double rentalPrice) {
        if (rentalPrice > 0)
            this.rentalPrice = rentalPrice;
        else
            System.err.println("Negative or zero price");
    }

    /**
     * Transport constructor.
     *
     * @param id unique positive integer
     * @param rentalPrice price in rubbles
     * @param brand name of brand
     * @param model name of model
     */
    public Transport(int id, double rentalPrice, String brand, String model) {
        setId(id);
        setRentalPrice(rentalPrice);
        this.brand = brand;
        this.model = model;
    }
}
