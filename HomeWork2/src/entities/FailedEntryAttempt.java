package entities;

import java.util.Date;

/**
 * {@link FailedEntryAttempt} - класс, отображающий сущность неудачной попытки въезда авто
 */
public class FailedEntryAttempt {

    /**
     * Поле, содержащее информацию об автомобиле
     */
    private Car car;

    /**
     * Поле, содержащее информацию о времени, в которое была совершена попытка въезда
     */
    private Date attemptTime;

    /**
     * Создание сущности неудачной попытки въезда с пустыми полями
     */
    public FailedEntryAttempt() {
    }

    /**
     * Создание сущности неудачной попытки въезда с заданными полями
     *
     * @param car
     * @param attemptTime
     */
    public FailedEntryAttempt(Car car, Date attemptTime) {
        this.car = car;
        this.attemptTime = attemptTime;
    }

    /**
     * Предоставляет доступ к номеру машины
     *
     * @return String carNumber
     */
    public String getCarNumber() {
        return this.car.getNumber();
    }

    /**
     * Возвращает время, когда была совершена попытка
     *
     * @return Date attemptTime
     */
    public Date getAttemptTime() {
        return this.attemptTime;
    }
}
