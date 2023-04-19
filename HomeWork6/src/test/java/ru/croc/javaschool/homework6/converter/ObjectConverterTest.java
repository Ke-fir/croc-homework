package ru.croc.javaschool.homework6.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.homework6.inputxmlobject.Manager;
import ru.croc.javaschool.homework6.inputxmlobject.Project;
import ru.croc.javaschool.homework6.inputxmlobject.Projects;
import ru.croc.javaschool.homework6.inputxmlobject.Specialist;
import ru.croc.javaschool.homework6.outxmlobject.People;
import ru.croc.javaschool.homework6.outxmlobject.Person;
import ru.croc.javaschool.homework6.outxmlobject.PersonsProject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The class responsible for testing {@link ObjectConverter}.
 */
public class ObjectConverterTest {

    @Test
    public void convertObjectsToPeopleTest(){
        //region Init
        var spec1 = new Specialist("Человек 3");
        var manager1 = new Manager("Человек 1", new ArrayList<>(Arrays.asList(spec1)));
        var manager2 = new Manager("Человек 2", new ArrayList<>());
        var manager3 = new Manager("Человек 3", new ArrayList<>());
        var project1 = new Project("Проект 1", "Описание 1", new ArrayList<>(Arrays.asList(manager1, manager2)));
        var project2 = new Project("Проект 2", "Описание 2", new ArrayList<>(Arrays.asList(manager3)));
        var projects = new Projects(new ArrayList<>(Arrays.asList(project1, project2)));

        var pers1 = new Person("Человек 1",
                new ArrayList<>(Arrays.asList(new PersonsProject("Проект 1", "Менеджер", ""))));
        var pers2 = new Person("Человек 2",
                new ArrayList<>(Arrays.asList(new PersonsProject("Проект 1", "Менеджер", ""))));
        var pers3 = new Person("Человек 3",
                new ArrayList<>(Arrays.asList(new PersonsProject("Проект 1", "Специалист", "Человек 1"),
                        new PersonsProject("Проект 2", "Менеджер", ""))));
        var expectedPeople = new People(new ArrayList<>(Arrays.asList(pers1, pers2, pers3)));
        //endregion

        var actualPeople = ObjectConverter.convertProjectsToPeople(projects);
        Assertions.assertArrayEquals(expectedPeople.getPeople().toArray(), actualPeople.getPeople().toArray());
    }
}
