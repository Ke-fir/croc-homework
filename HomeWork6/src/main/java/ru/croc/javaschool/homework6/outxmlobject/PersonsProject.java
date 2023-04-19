package ru.croc.javaschool.homework6.outxmlobject;

import jakarta.xml.bind.annotation.*;

/**
 * Class responsible for projects in out XML.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonsProject {
    /**
     * Title.
     */
    @XmlAttribute(name = "title")
    private String title;
    /**
     * Role.
     */
    @XmlElement(name = "role")
    private String role;
    /**
     * Manager.
     */
    @XmlElement(name = "manager")
    private String manager;

    /**
     * PersonProject constructor
     * @param title
     * @param role
     * @param manager
     */
    public PersonsProject(String title, String role, String manager) {
        this.title = title;
        this.role = role;
        this.manager = manager;
    }

    /**
     * Empty constructor.
     */
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
