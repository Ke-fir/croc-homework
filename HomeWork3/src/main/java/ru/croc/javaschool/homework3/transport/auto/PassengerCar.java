package ru.croc.javaschool.homework3.transport.auto;

/**
 * Passenger car.
 */

public class PassengerCar extends Automobile {
    private double safetyAssessment;

    public double getSafetyAssessment() {
        return safetyAssessment;
    }

    /**
     * Realisation of final but with restriction on input value
     * @param safetyAssessment should be in range [0; 5]
     */
    private void setSafetyAssessment(double safetyAssessment) {
        if (safetyAssessment >= 0 && safetyAssessment <= 5)
            this.safetyAssessment = safetyAssessment;
        else
            System.err.println("Invalid safety mark");
    }

    /**
     * Automobile constructor.
     *
     * @param id should be unique positive integer
     * @param rentalPrice price in rubbles
     * @param brand name of brand
     * @param model name of model
     * @param number car number
     * @param safetyAssessment assessment of safety in stars [0; 5]
     */
    public PassengerCar(int id, double rentalPrice, String brand, String model, String number, double safetyAssessment) {
        super(id, rentalPrice, brand, model, number);
        setSafetyAssessment(safetyAssessment);
    }
}
