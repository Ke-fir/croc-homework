package ru.croc.javaschool.homework3.transport.auto;

import ru.croc.javaschool.homework3.transport.Transport;

/**
 * Abstract class that extends basically transport.
 */
public abstract class Automobile extends Transport {
    /**
     * Car number.
     */
    protected String number;

    public String getNumber() {
        return number;
    }

    /**
     * Car number setter that checks number format with regex.
     * @param number string with format like "A111AA01"
     */
    public void setNumber(String number) {
        String regex = "[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}(?<!000)\\d{2,3}";
        if (number.matches(regex))
            this.number = number;
        else
            System.err.println("Invalid car number format.");
    }

    /**
     * Automobile constructor.
     *
     * @param id unique integer
     * @param rentalPrice price in rubbles
     * @param brand name of the brand
     * @param model name of the model
     */
    public Automobile(int id, double rentalPrice, String brand, String model, String number) {
        super(id, rentalPrice, brand, model);
        this.setNumber(number);
    }
}
