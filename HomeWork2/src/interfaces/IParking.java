package interfaces;

import entities.*;

import java.time.LocalTime;

/**
 * {@link IParking} является интерфейсом, описывающим набор
 * возможностей, которые реализует парковка
 */
public interface IParking {

    /**
     * Возвращает список въездов
     *
     * @return array of entryPoints
     */
    EntryPoint[] getEntryPoints();


    /**
     * Возвращает список въездов
     *
     * @return array of exitPoints
     */
    ExitPoint[] getExitPoints();


    /**
     * Возвращает общее количество мест на парковке
     *
     * @return Integer spaceCount
     */
    Integer getSpaceCount();


    /**
     * Возвращает количество свободных мест на парковке
     *
     * @return Integer emptySpaceCount
     */
    Integer getEmptySpaceCount();


    /**
     * Возвращает список неудачных попыток въезда
     *
     * @return array of attempts
     */
    FailedEntryAttempt[] getFailedAttempts();


    /**
     * Проверяет возможность въезда нового автомобиля
     *
     * @return boolean entryPossibility
     */
    boolean checkEntryPossibility();


    /**
     * Возвращает количество занятых мест
     *
     * @return occupiedSpaceCount
     */
    Integer getOccupiedSpaceCount();

    /**
     * Добавляеет запись в список неудачных попыток въезда.
     * @param car
     * @param time время, когда произошла попытка въезда
     */
    void addFailedAttempt(Car car, LocalTime time);
}
