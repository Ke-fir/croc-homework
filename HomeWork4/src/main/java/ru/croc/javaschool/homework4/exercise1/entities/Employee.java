package ru.croc.javaschool.homework4.exercise1.entities;

/**
 * Employee.
 */
public class Employee {
    /**
     * Unique numerical ID.
     */
    private int id;

    /**
     * First name and last name of employee.
     */
    private final String name;

    /**
     * Manager of this employee. The manager itself if null.
     */
    private final Employee manager;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Employee getManager() {
        return manager;
    }

    /**
     * Sets checked ID. Is private to emulate final but with setting rule.
     * @param id some unique positive number less than 2147483647
     */
    private void setId(int id) {
        if (id >= 0) {
            this.id = id;
        }
        else
            System.err.println("Negative value as ID.");
    }

    /**
     * Override of hashcode counting rule for right hash map work (to put in the order due to ID).
     * @return hash code that is equal to ID
     */
    @Override
    public int hashCode() {
        return this.id;
    }

    /**
     * Employee constructor. Sets final values.
     * @param id some unique positive number less than 2147483647
     * @param name first & last names of the employee
     * @param manager employee that is manager for this employee
     */
    public Employee(int id, String name, Employee manager) {
        setId(id);
        this.name = name;
        this.manager = manager;
    }
}
