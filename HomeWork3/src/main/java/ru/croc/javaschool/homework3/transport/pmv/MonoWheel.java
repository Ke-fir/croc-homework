package ru.croc.javaschool.homework3.transport.pmv;

/**
 * Mono wheel.
 */
public class MonoWheel extends PMV {
    /**
     * Suspension stroke in millimetres.
     */
    private double suspensionStroke;

    public double getSuspensionStroke() {
        return suspensionStroke;
    }

    /**
     * Sets checked suspension stroke.
     * If value is negative prints system error.
     * @param suspensionStroke stroke in millimeters.
     */
    /* Is private to emulate final but with condition on input value. */
    private void setSuspensionStroke(double suspensionStroke) {
        if (suspensionStroke > 0)
            this.suspensionStroke = suspensionStroke;
        else
            System.err.println("Invalid value of stroke");
    }

    /**
     * PMV constructor.
     *
     * @param id          unique positive integer
     * @param rentalPrice price in rubbles
     * @param brand       name of brand
     * @param model       name of model
     * @param motorPower  power in kW
     * @param maxSpeed    speed in km/h
     */
    public MonoWheel(int id, double rentalPrice, String brand, String model, double motorPower, double maxSpeed,
                     double suspensionStroke) {
        super(id, rentalPrice, brand, model, motorPower, maxSpeed);
        setSuspensionStroke(suspensionStroke);
    }
}
