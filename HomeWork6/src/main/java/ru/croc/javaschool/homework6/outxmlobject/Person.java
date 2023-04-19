package ru.croc.javaschool.homework6.outxmlobject;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The class responsible for person object. Contains fields: name, projects.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    /**
     * Name of person
     */
    @XmlElement(name = "name")
    private String name;

    /**
     * List of projects.
     */
    @XmlElementWrapper(name = "projects")
    @XmlElement(name = "project")
    private final List<PersonsProject> projects = new ArrayList<>();

    /**
     * Persons constructor
     * @param name name of pearson
     * @param projects list of projects
     */
    public Person(String name, List<PersonsProject> projects) {
        this.name = name;
        this.projects.addAll(projects);
    }

    /**
     * Empty person constructor
     */
    public Person() {
    }

    public String getName() {
        return name;
    }

    public List<PersonsProject> getProjects() {
        return projects;
    }

    @Override
    public boolean equals(Object obj) {
        var person = (Person)obj;
        var result = this.name.equals(person.getName());
        result &= this.projects.equals(person.getProjects());
        return result;
    }
}
