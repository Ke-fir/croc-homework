package ru.croc.javaschool.homework6.inputxmlobjects;


import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Manager {
    /**
     * Name of manager.
     */
    @XmlAttribute(name = "name")
    private String name;

    /**
     * List of specialists.
     */
    @XmlElementWrapper(name = "specialists")
    @XmlElement(name = "specialist")
    private List<Specialist> specialists;

    public Manager(String name, List<Specialist> specialists) {
        this.name = name;
        this.specialists = specialists;
    }

    public Manager() {
    }

    public String getName() {
        return name;
    }

    public List<Specialist> getSpecialists() {
        return specialists;
    }

    @Override
    public boolean equals(Object obj) {
        var manager = (Manager)obj;
        var result = true;
        result &= this.name.equals(manager.getName());
        result &= this.specialists.equals(manager.getSpecialists());
        return result;
    }
}
