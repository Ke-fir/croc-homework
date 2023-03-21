package entities;

/**
 * {@link Car} - класс, отображающий сущность "машина"
 */
public class Car {
    /**
     * Регистрационный знак транспортного средства РФ
     */
    private String number;


    /**
     * Метод, возвращающий автомобильный номер
     */
    public String getNumber() {
        return this.number;
    }


    /**
     * Метод, задающий автомобильный номер по шаблону
     *
     * @param number
     */
    protected void setNumber(String number) {
        String regex = "[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}(?<!000)\\d{2,3}";
        if (number.matches(regex))
            this.number = number;
        else
            System.err.println("Полученный номер не соответствует шаблону.");
    }

    /**
     * Пустой конструктор класса {@link Car}
     */
    public Car(){}

    /**
     * Конструктор класса {@link Car}
     * @param number номер автомобиля
     */
    public Car(String number){
        setNumber(number);
    }
}
