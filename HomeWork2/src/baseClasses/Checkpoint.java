package baseClasses;

import entities.Car;
import interfaces.ICheckpoint;

public class Checkpoint implements ICheckpoint {

    /**
     * Номер пропускного пункта
     */
    private Integer number;

    /**
     * Описание пропускного пункта
     */
    private String description;

    /**
     * Список проехавших машин
     */
    private Car[] pastCars;

    @Override
    public int getNumber() {
        return this.number;
    }

    /**
     * Устанавливает номер пропускного пункта с учётом проверки на отрицательное значение
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

    /**
     * Пустой конструктор класса {@link Checkpoint}
     */
    public Checkpoint() {
    }

    /**
     * Конструктор класса {@link Checkpoint}
     * @param number -- номер пункта
     * @param description -- описание пункта
     * @param pastCars -- список проехавших автомобилей
     */
    protected Checkpoint(Integer number, String description, Car[] pastCars) {
        setNumber(number);
        this.description = description;
        this.pastCars = pastCars;
    }
}
