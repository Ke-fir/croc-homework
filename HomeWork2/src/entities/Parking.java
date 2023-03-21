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
    private Integer occupiedSpaceCount = 0;

    /**
     * Позволяет изменять значение занятых мест
     *
     * @param value новое количество занятых мест
     */
    public void setOccupiedSpaceCount(int value) {
        if (value < 0)
            System.err.println("Количество занятых мест не может быть отрицательным.");
        else if (value <= spaceCount)
            this.occupiedSpaceCount = value;

    }

    @Override
    public Integer getOccupiedSpaceCount() {
        return occupiedSpaceCount;
    }

    @Override
    public void addFailedAttempt(Car car, Date date) {
        var attempt = new FailedEntryAttempt(car, date); // создание новой неудачной попытки

        if (Arrays.stream(attempts).anyMatch(x -> x != null && x.getCarNumber() == attempt.getCarNumber() && x.getAttemptTime() == attempt.getAttemptTime())) {
            System.err.println("Неудачные попытки въезда не должны дублироваться.");
            return;
        }
        // нахождение индекса реального последнего объекта в массиве c избыточным кол-вом ячеек
        int lastAttemptIndex = 0;
        for (int i = 0; i < attempts.length - 1; i++)
            if (attempts[i] == null) {
                lastAttemptIndex = i;
                break;
            }
        // проверка, что новый элемент не поместится в существующий массив
        if (lastAttemptIndex + 1 >= attempts.length - 1) {
            var tempArray = new FailedEntryAttempt[attempts.length + (attempts.length >> 1)]; // увеличение массива в 1,5 раза (>>1 == /2)
            System.arraycopy(attempts, 0, tempArray, 0, attempts.length); //???
            attempts = tempArray;
        }
        // добавление нового элемента
        else {
            attempts[lastAttemptIndex] = attempt; //записывание нового элемента в массив
            setOccupiedSpaceCount(occupiedSpaceCount + 1); //инкрементирование числа занятых мест парковки
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
     *
     * @param entryPoint заезд
     * @return null, если указанного заезда нет на парковке; список машин, проехавших череез КПП, если таковой нашелся
     */
    public Car[] getDrivenCars(EntryPoint entryPoint) {
        EntryPoint rightOne = null;
        for (var enter : entryPoints) {
            if (enter.getNumber() == entryPoint.getNumber())
                rightOne = entryPoint;
        }
        if (rightOne != null) {
            return rightOne.getPastCars();
        } else
            System.err.println("Въезда с таким номером не существует.");
        return null;
    }

    /**
     * Метод, возвращающий список машин, проехавших через указанный выезд
     *
     * @param exitPoint заезд
     * @return null, если указанного выезда нет на парковке; список машин, проехавших череез КПП, если таковой нашелся
     */
    public Car[] getLeftCars(ExitPoint exitPoint) {
        ExitPoint rightOne = null;
        for (var exit : exitPoints) {
            if (exit.getNumber() == exitPoint.getNumber()) {
                rightOne = exitPoint;
                break;
            }
        }
        if (rightOne != null) {
            return rightOne.getPastCars();
        } else
            System.err.println("Выезда с таким номером не существует.");
        return null;
    }

    /**
     * Добавляет машину в список проехавших через {@link ExitPoint} машин.
     *
     * @param car машина, которую необходимо добавить
     */
    public void passCarOut(Car car, ExitPoint exit) {
        // нахождение индекса реального последнего объекта в массиве c избыточным кол-вом ячеек
        int lastCarIndex = 0;
        for (int i = 0; exit.getPastCars() != null && i <  exit.getPastCars().length - 1; i++)
            if (exit.getPastCars()[i] == null) {
                lastCarIndex = i;
                break;
            }
        // проверка, что новый элемент не поместится в существующий массив
        if (lastCarIndex + 1 >= exit.getPastCars().length - 1) {
            var tempArray = new Car[exit.getPastCars().length + (exit.getPastCars().length >> 1)]; // увеличение массива в 1,5 раза (>>1 == /2)
            System.arraycopy(exit.getPastCars(), 0, tempArray, 0, exit.getPastCars().length); //???
            exit.setPastCars(tempArray);
        }
        // добавление нового элемента
        else {
            exit.getPastCars()[lastCarIndex] = car; //записывание нового элемента в массив
            setOccupiedSpaceCount(occupiedSpaceCount - 1); //декрементирование числа занятых мест парковки
        }
    }


    /**
     * Обрабатывает попытку заезда и или впускает машину на парковку, или добавляет запись о неудачной попытке въезда.
     *
     * @param newCar машина, чью попытку заезда необходимо обработать
     */
    public void processEntryAttempt(Car newCar, EntryPoint entryPoint) {
        if (checkEntryPossibility()) { // если на парковке достаточно мест
            if (Arrays.stream(entryPoints).anyMatch(x -> x.getNumber() == entryPoint.getNumber()))
                passCarIn(newCar, entryPoint); // добавление машины на парковку
            else
                System.err.println("Такого КПП не существует");
        } else
            addFailedAttempt(newCar, new Date()); // добавление неудачной попытки
    }


    /**
     * Добавляет машину в список проехавших через {@link EntryPoint} машин.
     *
     * @param car машина, которую необходимо добавить
     */
    protected void passCarIn(Car car, EntryPoint checkIn) {
        // нахождение индекса реального последнего объекта в массиве c избыточным кол-вом ячеек
        int lastCarIndex = 0;
        for (int i = 0; checkIn.getPastCars() != null && i < checkIn.getPastCars().length - 1; i++)
            if (checkIn.getPastCars()[i] == null) {
                lastCarIndex = i;
                break;
            }
        // проверка, что новый элемент не поместится в существующий массив
        if (lastCarIndex + 1 >= checkIn.getPastCars().length - 1) {
            var tempArray = new Car[checkIn.getPastCars().length + (checkIn.getPastCars().length >> 1)]; // увеличение массива в 1,5 раза (>>1 == /2)
            System.arraycopy(checkIn.getPastCars(), 0, tempArray, 0, checkIn.getPastCars().length); //???
            checkIn.setPastCars(tempArray);
        }
        // добавление нового элемента
        else {
            checkIn.getPastCars()[lastCarIndex] = car; //записывание нового элемента в массив
            occupiedSpaceCount++; //инкрементирование числа занятых мест парковки
        }
    }

    public Parking() {
    }

    public Parking(int spaceCount, EntryPoint[] entryPoints, ExitPoint[] exitPoints) {
        this.spaceCount = spaceCount;
        this.exitPoints = exitPoints;
        this.entryPoints = entryPoints;
    }
}
