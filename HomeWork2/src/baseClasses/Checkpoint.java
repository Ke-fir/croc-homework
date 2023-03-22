package baseClasses;

import entities.Car;

import interfaces.ICheckpoint;

public class Checkpoint implements ICheckpoint {

    /**
     * Номер пропускного пункта
     */
    protected Integer number;

    /**
     * Описание пропускного пункта
     */
    protected String description = "";

    /**
     * Список проехавших машин
     */
    protected Car[] pastCars = new Car[10];

    /**
     *
     * @return строковое представление КПП в виде номера и описания
     */
    @Override
    public String toString() {
        return this.number +  this.description;
    }

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
        if (number < 0)
            System.err.println("Номер пропускного пункта не может быть отрицательным.");
        else
            this.number = number;
    }


    @Override
    public String getDescription() {

        return this.description;
    }


    public void setPastCars(Car[] cars) {
        this.pastCars = cars;
    }

    @Override
    public Car[] getPastCars() {
        return this.pastCars;
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
    public Checkpoint(Integer number, String description, Car[] pastCars) {
        setNumber(number);
        this.description = description;
        this.pastCars = pastCars;
    }
}
