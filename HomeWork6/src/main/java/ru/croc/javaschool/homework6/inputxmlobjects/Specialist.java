package ru.croc.javaschool.homework6.inputxmlobjects;

import jakarta.xml.bind.annotation.*;

/**
 * The class responsible for specialist. Contains field name.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Specialist {
    /**
     * Name of specialist.
     */
    @XmlAttribute(name = "name")
    private String name;

    public Specialist(String name) {
        this.name = name;
    }

    public Specialist() {
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Specialist)obj).getName());
    }
}
