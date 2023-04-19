package ru.croc.javaschool.homework6.outxmlobjects;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class PersonsProject {
    @XmlAttribute(name = "title")
    private String title;
    @XmlElement(name = "role")
    private String role;
    @XmlElement(name = "manager")
    private String manager;

    public PersonsProject(String title, String role, String manager) {
        this.title = title;
        this.role = role;
        this.manager = manager;
    }

    public PersonsProject() {
    }

    public String getTitle() {
        return title;
    }

    public String getRole() {
        return role;
    }

    public String getManager() {
        return manager;
    }

    @Override
    public boolean equals(Object obj) {
        var project = (PersonsProject)obj;
        var result = true;
        result &= this.title.equals(project.getTitle());
        result &= this.manager.equals(project.getManager());
        result &= this.role.equals(project.getRole());
        return result;
    }
}
