import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Long input;
        Scanner console = new Scanner(System.in);
        do {
            System.out.print("Введите значение в байтах: ");
            input = console.nextLong();
        }
        while (!checkBytes(input));
        System.out.print("Значение в человекочитаемом виде: ");
        SizeFormatter.printBytes(input);
    }

    private static boolean checkBytes(Long bytes) {
        if (bytes < 0) {
            System.out.println("Количество байт не может быть отрицательным.");
            return false;
        }
        return true;
    }
}