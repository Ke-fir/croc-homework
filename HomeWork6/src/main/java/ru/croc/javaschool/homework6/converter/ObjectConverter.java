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
        /* firstly filling people list with managers of this project then with specialists */
        for (var project : projects.getProjects()) {
            for (var manager : project.getManagers()) {
                /* if none found add new person */
                if (peopleList.stream().noneMatch(x -> x.getName().equals(manager.getName()))) {
                    peopleList.add(new Person(manager.getName(), new ArrayList<>(Arrays.asList(
                            new PersonsProject(project.getTitle(), "Менеджер", "")))));
                } else {
                    /* getting person with the same name as manager from people list */
                    var streamPerson = peopleList.stream().filter(x -> x.getName().equals(manager.getName())).findFirst().get();
                    /* adding new project to person */
                    streamPerson.getProjects().add(new PersonsProject(project.getTitle(), "Менеджер", ""));
                }
            }
            for (var manager : project.getManagers()) {
                for (var specialist : manager.getSpecialists()) {
                    if (peopleList.stream().noneMatch(x -> x.getName().equals(specialist.getName()))) {
                        peopleList.add(new Person(specialist.getName(), new ArrayList<>(Arrays.asList(
                                new PersonsProject(project.getTitle(), "Специалист", manager.getName())))));
                    } else {
                        var streamPerson = peopleList.stream().filter(x -> x.getName().equals(specialist.getName()))
                                .findFirst().get();
                        streamPerson.getProjects().add(new PersonsProject(project.getTitle(), "Специалист",
                                manager.getName()));
                    }
                }
            }
        }
        return new People(peopleList);
    }
}
