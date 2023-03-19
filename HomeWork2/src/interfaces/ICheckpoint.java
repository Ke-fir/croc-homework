package interfaces;

import entities.Car;

import java.util.ArrayList;

/**
 * {@link ICheckpoint} является интерфейсом, реализующим возможности пропускного пункта
 */
public interface ICheckpoint {
    /**
     * Предоставляет доступ к полю number
     *
     * @return Integer number
     */
    int getNumber();

    /**
     * Предоставляет доступ к полю description
     *
     * @return String description
     */
    String getDescription();

    /**
     * Предоставляет доступ к полю attemptList
     *
     * @return ArrayList pastCars
     */
    ArrayList<Car> getPastCars();
}
