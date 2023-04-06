package ru.croc.javaschool.homework4.exercise1;

import java.util.ArrayList;

/**
 * Organization with manager and employees.
 */
public class Organization implements Comparable<Organization> {
    private Employee manager;
    private ArrayList<Employee> employees = new ArrayList<>();

    public Employee getManager() {
        return manager;
    }

    /**
     * Sets organization's manager with validation of employee.
     *
     * @param manager
     */
    public void setManager(Employee manager) {
        if (manager.getManager() == null) {
            this.manager = manager;
        } else {
            System.err.println("This employee isn't a manager");
        }
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    /**
     * Adds employee to organization or setting the manager after checking validation.
     *
     * @param employee some employee
     */
    public void addEmployee(Employee employee) {
        if (employee.getManager() == null && this.manager == null) {
            setManager(employee);
            return; // not to add manager to employees list
        }
        if (this.manager != null && employee.getManager() == this.manager) {
            this.employees.add(employee);
        } else {
            System.err.println("This employee isn't part of organization or the manager isn't set");
        }
    }

    /**
     * Compares 2 organization by their employees count.
     *
     * @param organization the object to be compared.
     * @return 1 if more & 0 if less.
     */
    @Override
    public int compareTo(Organization organization) {
        return Integer.compare(this.employees.size(), organization.employees.size());
    }

    /**
     * Empty constructor for some organization.
     */
    public Organization() {
    }

    /**
     * Constructor for some organization.
     *
     * @param manager   employee that is main manager
     * @param employees list of employees without manager
     */
    public Organization(Employee manager, ArrayList<Employee> employees) {
        setManager(manager);
        this.employees.addAll(employees);
    }
}
