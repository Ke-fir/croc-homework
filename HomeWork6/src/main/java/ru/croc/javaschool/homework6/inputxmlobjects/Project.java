package ru.croc.javaschool.homework6.inputxmlobjects;

import jakarta.xml.bind.annotation.*;

import java.util.List;

/**
 * Project.
 */
//@XmlAccessorType(XmlAccessType.FIELD)
public class Project {
    @XmlElement(name = "title")
    private String title;
    @XmlElement(name = "description")
    private String description;
    @XmlElementWrapper(name = "managers")
    @XmlElement(name = "manager")
    private List<Manager> managers;

    public Project(String title, String description, List<Manager> managers) {
        this.title = title;
        this.description = description;
        this.managers = managers;
    }

    public Project (){ }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Manager> getManagers() {
        return managers;
    }
}
