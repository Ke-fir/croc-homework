package program;

import entities.*;

import java.util.Arrays;

public class ProgramDemonstrator {
    public static void main(String[] args) {

        // пример неправильного формата номера (4 цифры вместо 3х) и соответствующей ошибки
        var wrongNumberedCar = new Car("О6278АХ123");

        // создание списка въездов
        var entryPoints = new EntryPoint[]{new EntryPoint(0), new EntryPoint(1)};
        // создание списка выездов
        var exitPoints = new ExitPoint[]{new ExitPoint(0), new ExitPoint(1)};
        var parking = new Parking(3, entryPoints, exitPoints); // создание парковки на 3 места


        var carArray = new Car[]{new Car("О628АХ123"), new Car("А001АА777"), new Car("С345РУ01"), new Car("О777ОО777")};

        // впускаем на 3 места 4 машины (одна в неудачные попытки)
        for (int i = 0; i < carArray.length; i++) {
            parking.processEntryAttempt(carArray[i], entryPoints[i % 2]); // нечетные машины в 1й заезд, четные -- в нулевой
        }

        parking.passCarOut(carArray[0], exitPoints[0]); // выпускаем 1 машину

        System.out.println("1) " + parking.checkEntryPossibility()); // проверяем возможность въехать
        System.out.println("2) " + parking.getEmptySpaceCount()); // проверяем количество свободных мест

        System.out.println("3) " + Arrays.toString(parking.getDrivenCars(entryPoints[0]))); // список машин заехавших через первый заезд
        System.out.println("4) " + Arrays.toString(parking.getDrivenCars(entryPoints[1]))); // список машин, заехавших через второй
        System.out.println("5) " + Arrays.toString(parking.getLeftCars(exitPoints[0]))); // список машин, выехавших через первый выезд
        System.out.println("6) " + Arrays.toString(parking.getLeftCars(exitPoints[1]))); // список машин, выехавших через второй
        System.out.println("7) " + Arrays.toString(parking.getFailedAttempts())); // проверяем список неудачных попыток въезда
    }
}