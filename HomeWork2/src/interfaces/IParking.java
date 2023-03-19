package interfaces;

import entities.EntryAttempt;
import entities.EntryPoint;
import entities.ExitPoint;

import java.util.ArrayList;

/**
 * {@link IParking} является интерфейсом, описывающим набор
 * возможностей, которые реализует парковка
 */
public interface IParking {

    /**
     * Возвращает список въездов
     *
     * @return ArrayList entryPoints
     */
    ArrayList<EntryPoint> getEntryPoints();


    /**
     * Возвращает список въездов
     *
     * @return ArrayList exitPoints
     */
    ArrayList<ExitPoint> getExitPoints();


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
     * @return attemptList
     */
    ArrayList<EntryAttempt> getAttemptList();


    /**
     * Проверяет возможность въезда нового автомобиля
     *
     * @return boolean entryPossibility
     */
    boolean checkEntryPossibility();

}
