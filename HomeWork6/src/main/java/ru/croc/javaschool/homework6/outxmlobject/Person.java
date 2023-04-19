package ru.croc.javaschool.homework6.outxmlobject;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    @XmlElement(name = "name")
    private String name;
    @XmlElementWrapper(name = "projects")
    @XmlElement(name = "project")
    private List<PersonsProject> projects = new ArrayList<>();

    public Person(String name, List<PersonsProject> projects) {
        this.name = name;
        this.projects.addAll(projects);
    }

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
