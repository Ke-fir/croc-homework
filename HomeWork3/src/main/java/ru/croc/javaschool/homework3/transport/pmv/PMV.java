package ru.croc.javaschool.homework3.transport.pmv;

import ru.croc.javaschool.homework3.transport.Transport;

/**
 * Personal Mobility Vehicle base class.
 */
public abstract class PMV extends Transport {
    /**
     * Motor power in kilowatts.
     */
    private double motorPower;
    private double maxSpeed;

    public double getMotorPower() {
        return motorPower;
    }

    /**
     * Sets positive value of power.
     * If it is negative or zero prints system error.
     *
     * @param motorPower power in kW
     */
    /* Is private to emulate final but with condition on input value*/
    private void setMotorPower(double motorPower) {
        if (motorPower > 0)
            this.motorPower = motorPower;
        else
            System.err.println("Invalid value of power");
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * Sets positive max speed value.
     * If speed < 0 prints system error.
     *
     * @param maxSpeed speed in km/h
     */
    public void setMaxSpeed(double maxSpeed) {
        if (maxSpeed >= 0)
            this.maxSpeed = maxSpeed;
        else
            System.err.println("Invalid maximum speed");
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
    public PMV(int id, double rentalPrice, String brand, String model, double motorPower, double maxSpeed) {
        super(id, rentalPrice, brand, model);
        setMotorPower(motorPower);
        setMaxSpeed(maxSpeed);
    }
}
