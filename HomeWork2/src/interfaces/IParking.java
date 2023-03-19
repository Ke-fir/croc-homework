package interfaces;

import entities.EntryAttempt;
import entities.EntryPoint;
import entities.ExitPoint;

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
    EntryAttempt[] getAttempts();


    /**
     * Проверяет возможность въезда нового автомобиля
     *
     * @return boolean entryPossibility
     */
    boolean checkEntryPossibility();

}
