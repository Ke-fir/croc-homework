package entities;

import interfaces.IParking;

import java.util.Arrays;
import java.util.Date;

public class Parking implements IParking {
    /**
     * Список въездов
     */
    private EntryPoint[] entryPoints = new EntryPoint[10];

    /**
     * Список выездов
     */
    private ExitPoint[] exitPoints = new ExitPoint[10];

    /**
     * Общее количество мест
     */
    private Integer spaceCount;

    /**
     * Список неудачных попыток въезда
     */
    private FailedEntryAttempt[] attempts = new FailedEntryAttempt[10];

    /**
     * Количество занятых мест
     */
    private Integer occupiedSpaceCount;

    /**
     * Позволяет изменять значение занятых мест
     *
     * @param value новое количество занятых мест
     */
    public void setOccupiedSpaceCount(int value) {
        if (value >= 0)
            this.occupiedSpaceCount = value;
        else
            System.err.println("Количество занятых мест не может быть отрицательным.");
    }

    @Override
    public Integer getOccupiedSpaceCount() {
        return occupiedSpaceCount;
    }

    @Override
    public void addFailedAttempt(Car car, Date date) {
        var attempt = new FailedEntryAttempt(car, date); // создание новой неудачной попытки

        if (Arrays.stream(attempts).anyMatch(x -> x.equals(attempt))) {
            System.err.println("Неудачные попытки въезда не должны дублироваться.");
            return;
        }
        // нахождение индекса реального последнего объекта в массиве c избыточным кол-вом ячеек
        int lastAttemptIndex = 0;
        for (int i = 0; i < attempts.length - 1; i++)
            if (attempts[i + 1] == null) {
                lastAttemptIndex = i;
                break;
            }
        // проверка, что новый элемент не поместится в существующий массив
        if (lastAttemptIndex >= attempts.length - 1) {
            var tempArray = new FailedEntryAttempt[attempts.length + (attempts.length >> 1)]; // увеличение массива в 1,5 раза (>>1 == /2)
            System.arraycopy(attempts, 0, tempArray, 0, attempts.length); //???
            attempts = tempArray;
        }
        // добавление нового элемента
        else {
            attempts[lastAttemptIndex + 1] = attempt; //записывание нового элемента в массив
            occupiedSpaceCount++; //инкрементирование числа занятых мест парковки
        }
    }

    @Override
    public EntryPoint[] getEntryPoints() {
        return this.entryPoints;
    }

    @Override
    public ExitPoint[] getExitPoints() {

        return this.exitPoints;
    }

    @Override
    public Integer getSpaceCount() {

        return this.spaceCount;
    }

    @Override
    public Integer getEmptySpaceCount() {

        return (this.spaceCount - this.occupiedSpaceCount);
    }

    @Override
    public FailedEntryAttempt[] getFailedAttempts() {
        return this.attempts;
    }

    @Override
    public boolean checkEntryPossibility() {

        return (this.spaceCount > this.occupiedSpaceCount);
    }

    /**
     * Метод, возвращающий список машин, проехавших через указанный въезд
     * @param entryPoint заезд
     * @return null, если указанного заезда нет на парковке
     * @return список машин, проехавших череез КПП, если таковой нашелся
     */
    public Car[] getDrivenCars(EntryPoint entryPoint) {
        EntryPoint rightOne = null;
        for (var enter: entryPoints){
            if (enter.getNumber() == entryPoint.getNumber())
                rightOne = entryPoint;
        }
        if (rightOne != null) {
            return rightOne.getPastCars();
        }
        else
            System.err.println("Въезда с таким номером не существует.");
        return null;
    }

    /**
     * Метод, возвращающий список машин, проехавших через указанный выезд
     * @param exitPoint заезд
     * @return null, если указанного выезда нет на парковке
     * @return список машин, проехавших череез КПП, если таковой нашелся
     */
    public Car[] getLeftCars(ExitPoint exitPoint) {
        ExitPoint rightOne = null;
        for (var exit: entryPoints){
            if (exit.getNumber() == exitPoint.getNumber())
                rightOne = exitPoint;
        }
        if (rightOne != null) {
            return rightOne.getPastCars();
        }
        else
            System.err.println("Выезда с таким номером не существует.");
        return null;
    }
}
