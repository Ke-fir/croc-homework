package ru.croc.javaschool.homework6.inputxmlobjects;


import jakarta.xml.bind.annotation.*;

import java.util.List;

//@XmlAccessorType(XmlAccessType.FIELD)
/*@XmlRootElement(name = "manager")*/
public class Manager {
    @XmlAttribute(name = "name")
    private String name;
    @XmlElementWrapper(name = "specialists")
    @XmlElement(name = "specialist")
    private List<Specialist> specialists;

    public String getName() {
        return name;
    }

    public List<Specialist> getSpecialists() {
        return specialists;
    }
}
