package entities;

import baseClasses.Checkpoint;

/**
 * Въезд. Наследует {@link Checkpoint}
 */
public class ExitPoint extends Checkpoint {

    /**
     * Конструктор класса {@link ExitPoint}
     *
     * @param number      -- номер пункта
     * @param description -- описание
     * @param pastCars    -- список проехавших машин
     */
    public ExitPoint(Integer number, String description, Car[] pastCars) {
        super(number, description, pastCars);
    }

    /**
     * Конструктор класса {@link ExitPoint}
     *
     * @param number      -- номер пункта
     * @param description -- описание
     */
    public ExitPoint(Integer number, String description) {
        super(number, description, new Car[10]);
    }

    /**
     * Конструктор класса {@link ExitPoint}
     *
     * @param number  -- номер пункта
     */
    public ExitPoint(Integer number) {
        super(number, null, new Car[10]);
    }

}
