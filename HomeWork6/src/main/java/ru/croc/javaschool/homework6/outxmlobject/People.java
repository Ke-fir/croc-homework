package ru.croc.javaschool.homework6.outxmlobject;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

/**
 * Wrapper class for people.
 */
@XmlRootElement(name = "people")
@XmlAccessorType(XmlAccessType.FIELD)
public class People {
    @XmlElement(name = "person")
    private List<Person> people;

    /**
     * People constructor
     *
     * @param people
     */
    public People(List<Person> people) {
        this.people = people;
    }

    /**
     * Empty people constructor
     */
    public People() {
    }

    public List<Person> getPeople() {
        return people;
    }

    @Override
    public boolean equals(Object obj) {
        return this.people.equals(((People) obj).getPeople());
    }
}
