package entities;

import baseClasses.Checkpoint;

/**
 * Въезд. Наследует {@link Checkpoint}
 */
public class EntryPoint extends Checkpoint {

    /**
     * Конструктор класса {@link EntryPoint}
     *
     * @param number      -- номер пункта
     * @param description -- описание
     * @param pastCars    -- список проехавших машин
     */
    public EntryPoint(Integer number, String description, Car[] pastCars) {
        super( number, description, pastCars);
    }

    /**
     * Конструктор класса {@link EntryPoint}
     *
     * @param number      -- номер пункта
     * @param description -- описание
     */
    public EntryPoint(Integer number, String description) {
        super(number, description, new Car[10]);
    }

    /**
     * Конструктор класса {@link EntryPoint}
     *
     * @param number  -- номер пункта
     */
    public EntryPoint(Integer number) {
        super(number, null, new Car[10]);
    }



}
