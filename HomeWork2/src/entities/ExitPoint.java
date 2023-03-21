package entities;

import baseClasses.Checkpoint;

/**
 * Въезд. Наследует {@link Checkpoint}
 */
public class ExitPoint extends Checkpoint {

    /**
     * Конструктор класса {@link ExitPoint}
     *
     * @param parking     -- парковка, на которой находится пропускной пункт
     * @param number      -- номер пункта
     * @param description -- описание
     * @param pastCars    -- список проехавших машин
     */
    public ExitPoint(Parking parking, Integer number, String description, Car[] pastCars) {
        super(parking, number, description, pastCars);
    }

    /**
     * Конструктор класса {@link ExitPoint}
     *
     * @param parking     -- парковка, на которой находится пропускной пункт
     * @param number      -- номер пункта
     * @param description -- описание
     */
    public ExitPoint(Parking parking, Integer number, String description) {
        super(parking, number, description, null);
    }

    /**
     * Конструктор класса {@link ExitPoint}
     *
     * @param parking -- парковка, на которой находится пропускной пункт
     * @param number  -- номер пункта
     */
    public ExitPoint(Parking parking, Integer number) {
        super(parking, number, null, null);
    }


    /**
     * Обрабатывает выезд машины
     *
     * @param newCar
     */
    public void processCarExit(Car newCar) {
        parking.setOccupiedSpaceCount(parking.getOccupiedSpaceCount() - 1);
        passThisCar(newCar);
    }


    /**
     * Добавляет машину в список проехавших через {@link ExitPoint} машин.
     *
     * @param car машина, которую необходимо добавить
     */
    protected void passThisCar(Car car) {
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
