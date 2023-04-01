package ru.croc.javaschool.homework3.transport.pmv;

/**
 * Electric scooter.
 */
public class EScooter extends PMV{
    /**
     * Information about presents of gps chip in scooter.
     */
    private boolean gpsPresence;

    public boolean isGpsPresence() {
        return gpsPresence;
    }

    public void setGpsPresence(boolean gpsPresence) {
        this.gpsPresence = gpsPresence;
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
    public EScooter(int id, double rentalPrice, String brand, String model, double motorPower, double maxSpeed,
                    boolean gpsPresence) {
        super(id, rentalPrice, brand, model, motorPower, maxSpeed);
        setGpsPresence(gpsPresence);
    }
}
