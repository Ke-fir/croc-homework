package ru.croc.javaschool.homework6.converter;

import ru.croc.javaschool.homework6.inputxmlobject.Projects;
import ru.croc.javaschool.homework6.outxmlobject.People;
import ru.croc.javaschool.homework6.outxmlobject.Person;
import ru.croc.javaschool.homework6.outxmlobject.PersonsProject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The class responsible for converting Projects to People.
 */
public class ObjectConverter {
    /**
     * Converts list of projects to list of people.
     *
     * @param projects list of projects
     * @return list of people
     */
    public static People convertProjectsToPeople(Projects projects) {
        var peopleList = new ArrayList<Person>(); // list of people
        /* filling people list with managers */
        for (var project : projects.getProjects()) {
            for (var manager : project.getManagers()) {
                /* getting person with the same name as manager from people list */
                var streamPerson = peopleList.stream().filter(x -> x.getName().equals(manager.getName())).findFirst().get();
                /* if none found add new person */
                if (streamPerson == null) {
                    peopleList.add(new Person(manager.getName(), new ArrayList<>(Arrays.asList(
                            new PersonsProject(project.getTitle(), "manager", "")))));
                } else {
                    /* adding new project to person */
                    streamPerson.getProjects().add(new PersonsProject(project.getTitle(), "manager", ""));
                }
            }
        }
        for (var project : projects.getProjects()) {
            for (var manager : project.getManagers()) {
                for (var specialist : manager.getSpecialists()) {
                    var streamPerson = peopleList.stream().filter(x -> x.getName().equals(specialist.getName())).findFirst().get();
                    if (streamPerson == null) {
                        peopleList.add(new Person(manager.getName(), new ArrayList<>(Arrays.asList(
                                new PersonsProject(project.getTitle(), "specialist", manager.getName())))));
                    } else {
                        streamPerson.getProjects().add(new PersonsProject(project.getTitle(), "specialist",
                                manager.getName()));
                    }
                }
            }
        }
        return new People(peopleList);
    }
}
