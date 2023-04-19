package ru.croc.javaschool.homework6.inputxmlobjects;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "projects")
public class Projects {
    //@XmlElementWrapper(name = "projects")
    @XmlElement(name = "project")
    private List<Project> projects;


    public List<Project> getProjects() {
        return projects;
    }
}
