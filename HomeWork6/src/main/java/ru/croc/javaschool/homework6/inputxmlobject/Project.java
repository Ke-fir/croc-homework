package ru.croc.javaschool.homework6.inputxmlobject;

import jakarta.xml.bind.annotation.*;

import java.util.List;

/**
 * A class representing the project. Contains fields: name, description, list of managers.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Project {
    /**
     * Title of project.
     */
    @XmlElement(name = "title")
    private String title;

    /**
     * Description of project.
     */
    @XmlElement(name = "description")
    private String description;

    /**
     * List of managers that works in project.
     */
    @XmlElementWrapper(name = "managers")
    @XmlElement(name = "manager")
    private List<Manager> managers;

    /**
     * Project constructor.
     *
     * @param title       title of project
     * @param description description of the project
     * @param managers    list of managers
     */
    public Project(String title, String description, List<Manager> managers) {
        this.title = title;
        this.description = description;
        this.managers = managers;
    }

    /**
     * Empty constructor of Project.
     */
    public Project() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    @Override
    public boolean equals(Object obj) {
        var project = (Project)obj;
        var result = true;
        result &= this.title.equals(project.getTitle());
        result &= this.description.equals(project.getDescription());
        result &= this.managers.equals(project.getManagers());
        return result;
    }
}
