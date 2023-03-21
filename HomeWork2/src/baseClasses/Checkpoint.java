package baseClasses;

import entities.Car;
import entities.Parking;
import interfaces.ICheckpoint;

public class Checkpoint implements ICheckpoint {

    protected Parking parking;

    /**
     * Номер пропускного пункта
     */
    protected Integer number;

    /**
     * Описание пропускного пункта
     */
    protected String description;

    /**
     * Список проехавших машин
     */
    protected Car[] pastCars = new Car[10];

    @Override
    public int getNumber() {
        return this.number;
    }

    /**
     * Устанавливает номер пропускного пункта с учётом проверки на отрицательное значение
     *
     * @param number -- номер пункта
     */
    protected void setNumber(Integer number) {
        if (number >= 0)
            this.number = number;
        else
            System.err.println("Номер пропускного пункта не может быть отрицательным.");
    }

    @Override
    public String getDescription() {

        return this.description;
    }

    @Override
    public Car[] getPastCars() {
        return this.pastCars;
    }



    @Override
    public Parking getParking() {
        return this.parking;
    }

    /**
     * Пустой конструктор класса {@link Checkpoint}
     */
    public Checkpoint() {
    }

    /**
     * Конструктор класса {@link Checkpoint}
     *
     * @param number      -- номер пункта
     * @param description -- описание пункта
     * @param pastCars    -- список проехавших автомобилей
     */
    protected Checkpoint(Parking parking, Integer number, String description, Car[] pastCars) {
        this.parking = parking;
        setNumber(number);
        this.description = description;
        this.pastCars = pastCars;
    }
}
