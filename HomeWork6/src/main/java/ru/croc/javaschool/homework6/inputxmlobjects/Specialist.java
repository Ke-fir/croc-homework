package ru.croc.javaschool.homework6.inputxmlobjects;

import jakarta.xml.bind.annotation.*;

/*@XmlRootElement(name = "specialist")*/
//@XmlAccessorType(XmlAccessType.FIELD)
public class Specialist {
    @XmlAttribute(name= "name")
    private String name;
    public String getName() {
        return name;
    }
}
