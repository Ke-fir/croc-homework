package entities;

import baseClasses.Checkpoint;

import java.util.Arrays;
import java.util.Date;

/**
 * Въезд. Наследует {@link Checkpoint}
 */
public class EntryPoint extends Checkpoint {
    /**
     * Конструктор класса {@link EntryPoint}
     *
     * @param parking     -- парковка, на которой находится пропускной пункт
     * @param number      -- номер пункта
     * @param description -- описание
     * @param pastCars    -- список проехавших машин
     */
    public EntryPoint(Parking parking, Integer number, String description, Car[] pastCars) {
        super(parking, number, description, pastCars);
    }

    /**
     * Конструктор класса {@link EntryPoint}
     *
     * @param parking     -- парковка, на которой находится пропускной пункт
     * @param number      -- номер пункта
     * @param description -- описание
     */
    public EntryPoint(Parking parking, Integer number, String description) {
        super(parking, number, description, null);
    }

    /**
     * Конструктор класса {@link EntryPoint}
     *
     * @param parking -- парковка, на которой находится пропускной пункт
     * @param number  -- номер пункта
     */
    public EntryPoint(Parking parking, Integer number) {
        super(parking, number, null, null);
    }


    /**
     * Обрабатывает попытку заезда и или впускает машину на парковку, или добавляет запись о неудачной попытке въезда.
     * @param newCar машина, чью попытку заезда необходимо обработать
     */
    public void processEntryAttempt(Car newCar) {
        if (parking.checkEntryPossibility()) // если на парковке достаточно мест
        passThisCar(newCar); // добавление машины на парковку
        else
         parking.addFailedAttempt(newCar, new Date()); // добавление неудачной попытки
    }


    /**
     * Добавляет машину в список проехавших через {@link EntryPoint} машин.
     * @param car машина, которую необходимо добавить
     */
    protected void passThisCar(Car car) {
        if (Arrays.stream(pastCars).anyMatch(x -> x == car)){
            System.err.println("На парковке не может быть 2 машины с одинаковыми номерами.");
            return;
        }
        // нахождение индекса реального последнего объекта в массиве c избыточным кол-вом ячеек
        int lastCarIndex = 0;
        for (int i = 0; i < pastCars.length - 1; i++)
            if (pastCars[i + 1] == null) {
                lastCarIndex = i;
                break;
            }
        // проверка, что новый элемент не поместится в существующий массив
        if (lastCarIndex >= pastCars.length - 1) {
            var tempArray = new Car[pastCars.length + (pastCars.length >> 1)]; // увеличение массива в 1,5 раза (>>1 == /2)
            System.arraycopy(pastCars, 0, tempArray, 0, pastCars.length); //???
            pastCars = tempArray;
        }
        // добавление нового элемента
        else {
            pastCars[lastCarIndex + 1] = car; //записывание нового элемента в массив
            parking.setOccupiedSpaceCount(parking.getOccupiedSpaceCount() + 1); //инкрементирование числа занятых мест парковки
        }
    }
}
