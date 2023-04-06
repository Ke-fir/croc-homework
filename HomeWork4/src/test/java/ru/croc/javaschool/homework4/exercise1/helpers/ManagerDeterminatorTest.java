package ru.croc.javaschool.homework4.exercise1.helpers;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.homework4.exercise1.entities.Employee;
import ru.croc.javaschool.homework4.exercise1.entities.Organization;
import ru.croc.javaschool.homework4.exercise1.helpers.ManagerDeterminator;
import java.util.ArrayList;
import java.util.Arrays;

public class ManagerDeterminatorTest {
    /**
     * List of employees.
     */
    ArrayList<Employee> employees = new ArrayList<>();
    /**
     * Expected list of managers.
     */
    ArrayList<Employee> expectedManagers = new ArrayList<>();

    /**
     * Table of managers with their employees.
     */
    ArrayList<Organization> expectedOrganizations = new ArrayList<>();

    /**
     * Initialization of data for tests.
     */
    @BeforeEach
    public void init() {
        /* Init of managers */
        var managerSergey = new Employee(0, "Sergey Gennadiyevich", null); // 3 employees
        var managerAndrey = new Employee(1, "Andrey Petrovich", null); // 2 employees
        var managerYuriy = new Employee(2, "Yuriy Vadimovich", null); // 1 employee
        /* Init of workers */
        var ivan = new Employee(3, "Ivan Ivanov", managerSergey);
        var sidor = new Employee(4, "Sidor Sidorov", managerAndrey);
        var valentin = new Employee(5, "Valentin Valentinov", managerYuriy);
        var anastasia = new Employee(6, "Anastasia Black", managerAndrey);
        var viktoria = new Employee(7, "Viktoria Pretty", managerSergey);
        var viktor = new Employee(8, "Viktor Epanchintsev", managerSergey);
        /* Adding managers for expected list */
        expectedManagers.addAll(Arrays.asList(managerSergey, managerAndrey, managerYuriy));
        /* Adding employees */
        employees.addAll(expectedManagers);
        employees.addAll(Arrays.asList(ivan, sidor, valentin, anastasia, viktoria, viktor));
        /* Adding records to table of expectedOrganizations */
        expectedOrganizations.add(new Organization(managerSergey, new ArrayList<>(Arrays.asList(ivan, viktoria, viktor))));
        expectedOrganizations.add(new Organization(managerAndrey, new ArrayList<>(Arrays.asList(sidor, anastasia))));
        expectedOrganizations.add(new Organization(managerYuriy, new ArrayList<>(Arrays.asList(valentin))));
    }


    /**
     * Test of finding managers in employees.
     */
    @Test
    public void findManagersTest() {
        var actualManagers = ManagerDeterminator.findManagers(this.employees);
        Assertions.assertArrayEquals(this.expectedManagers.toArray(), actualManagers.toArray());
    }

    /**
     * Test of separating employees into groups by organizations.
     */
    @Test
    public void separateEmployeesToOrganizationsTest() {
        var determinator = new ManagerDeterminator(this.employees); // .separateEmployeesToOrganizations() is used in constructor
        var actualOrganizations = determinator.getOrganizations();
        Assertions.assertArrayEquals(expectedOrganizations.toArray(), actualOrganizations.toArray());
    }

    /**
     * Test of sorting managers due to count of employees.
     */
    @Test
    public void getSortedListOfManagers(){
        var determinator = new ManagerDeterminator(this.employees);
        var actualSortedManagersList = determinator.getSortedListOfManagers();
        Assertions.assertArrayEquals(expectedManagers.toArray(), actualSortedManagersList.toArray());
    }
}
