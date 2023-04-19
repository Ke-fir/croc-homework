package ru.croc.javaschool.homework6.inputxmlobject;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The class responsible for wrapping projects in input XML.
 */
@XmlRootElement(name = "projects")
public class Projects {

    /**
     * List of projects
     */
    @XmlElement(name = "project")
    private final List<Project> projects = new ArrayList<>();

    /**
     * Projects constructor.
     *
     * @param projects list of projects.
     */
    public Projects(List<Project> projects) {
        this.projects.addAll(projects);
    }

    /**
     * Empty constructor Projects constructor.
     */
    public Projects() {
    }

    public List<Project> getProjects() {
        return projects;
    }

    @Override
    public boolean equals(Object obj) {
        var projects = (Projects) obj;
        return this.projects.equals(projects.getProjects());
    }
}
