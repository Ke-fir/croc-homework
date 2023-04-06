package ru.croc.javaschool.homework4.exercise1.helpers;

import ru.croc.javaschool.homework4.exercise1.entities.Employee;
import ru.croc.javaschool.homework4.exercise1.entities.Organization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Auxiliary class that determines managers from basic employees and gives some information for report.
 */
public class ManagerDeterminator {
    /**
     * List of organizations.
     */
    private ArrayList<Organization> organizations = new ArrayList<>();

    /**
     * List of employees.
     */
    private final ArrayList<Employee> employees = new ArrayList<>();


    public ArrayList<Organization> getOrganizations() {
        return organizations;
    }

    /**
     * Auxiliary function.
     * Finds all managers in employees and returns them as arraylist.
     *
     * @param employees list of employees.
     * @return managers list.
     */
    /* Is static not to initiate object of the class to get list of managers */
    public static ArrayList<Employee> findManagers(ArrayList<Employee> employees) {
        var managers = new ArrayList<Employee>();
        for (var employee : employees) {
            if (employee != null && employee.getManager() == null) {
                managers.add(employee);
            }
        }
        return managers;
    }

    /**
     * Fills in organizations with employees.
     */
    private void addEmployeesToOrganizations() {
        var organizationsTable = separateEmployeesToOrganizations(this.employees);
        for (var manager : organizationsTable.keySet()) {
            this.organizations.add(new Organization(manager, organizationsTable.get(manager)));
        }
    }

    /**
     * Auxiliary function.
     * Separates employees to groups by their manager (organization).
     *
     * @param employees list of employees
     * @return table of managers and their employees
     */
    private static HashMap<Employee, ArrayList<Employee>> separateEmployeesToOrganizations(ArrayList<Employee> employees) {
        var managers = findManagers(employees);
        var organizationTable = new HashMap<Employee, ArrayList<Employee>>();
        for (var manager : managers) {
            organizationTable.put(manager, new ArrayList<>());
        }
        for (var employee : employees) {
            if (employee != null && employee.getManager() != null) {
                organizationTable.get(employee.getManager()).add(employee);
            }
        }
        return organizationTable;
    }

    public ArrayList<Employee> getSortedListOfManagers() {
        var tempOrganizations = new ArrayList<Organization>();
        tempOrganizations.addAll(this.organizations);
        Collections.sort(tempOrganizations);
        Collections.reverse(tempOrganizations);
        var sortedManagers = new ArrayList<Employee>();
        for (var organization : tempOrganizations){
            sortedManagers.add(organization.getManager());
        }
        return sortedManagers;
    }

    /**
     * Manager determinator constructor.
     */
    public ManagerDeterminator(ArrayList<Employee> employees) {
        this.employees.addAll(employees);
        addEmployeesToOrganizations();

    }
}
