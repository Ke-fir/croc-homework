package ru.croc.javaschool.homework3.transport.aircraft;

/**
 * Helicopter.
 */
public class Helicopter extends Aircraft {
    private int numberOfScrews;


    public int getNumberOfScrews() {
        return numberOfScrews;
    }

    /**
     * Sets checked number of screws
     * @param numberOfScrews positive number
     */
    public void setNumberOfScrews(int numberOfScrews) {
        if (numberOfScrews > 0)
            this.numberOfScrews = numberOfScrews;
        else
            System.err.println("Invalid number of screws");
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
     * @param numberOfScrews  positive number of screws
     */
    public Helicopter(int id, double rentalPrice, String brand, String model, int numberOfEngines, int numberOfSeats,
                      int numberOfScrews) {
        super(id, rentalPrice, brand, model, numberOfEngines, numberOfSeats);
        setNumberOfScrews(numberOfScrews);
    }
}
