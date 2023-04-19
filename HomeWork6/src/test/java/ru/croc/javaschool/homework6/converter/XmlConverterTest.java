package ru.croc.javaschool.homework6.converter;

import jakarta.xml.bind.JAXBException;
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
 * The class responsible for testing {@link XmlConverter}
 */
public class XmlConverterTest {

    @Test
    public void deserializeProjectsTest() throws JAXBException {

        //region Initialization
        var testXml = "<projects>\n" +
                "    <project>\n" +
                "        <title>Проект 1</title>\n" +
                "        <description>Описание 1</description>\n" +
                "        <managers>\n" +
                "            <manager name=\"Человек 1\">\n" +
                "                <specialists>\n" +
                "                    <specialist name=\"Человек 3\"/>\n" +
                "                    <specialist name=\"Человек 4\"/>\n" +
                "                </specialists>\n" +
                "            </manager>\n" +
                "            <manager name=\"Человек 2\">\n" +
                "                <specialists>\n" +
                "                    <specialist name=\"Человек 5\"/>\n" +
                "                    <specialist name=\"Человек 6\"/>\n" +
                "                </specialists>\n" +
                "            </manager>\n" +
                "        </managers>\n" +
                "    </project>\n" +
                "</projects>";

        var spec1 = new Specialist("Человек 3");
        var spec2 = new Specialist("Человек 4");
        var spec3 = new Specialist("Человек 5");
        var spec4 = new Specialist("Человек 6");
        var manager1 = new Manager("Человек 1", new ArrayList<>(Arrays.asList(spec1, spec2)));
        var manager2 = new Manager("Человек 2", new ArrayList<>(Arrays.asList(spec3, spec4)));
        var project1 = new Project("Проект 1", "Описание 1", new ArrayList<>(Arrays.asList(manager1, manager2)));
        var expectedProjects = new Projects(new ArrayList<>(Arrays.asList(project1)));
        //endregion
        var projects = new XmlConverter().deserializeProjects(testXml);
        Assertions.assertEquals(expectedProjects, projects);
    }

    @Test
    public void serializePeopleTest() throws JAXBException {

        //region Initiation
        var expectedXml = "<people>" +
                "<person>" +
                "<name>Человек 1</name>" +
                "<projects>" +
                "<project title=\"Проект 1\">" +
                "<role>Менеджер</role>" +
                "<manager></manager>" +
                "</project>" +
                "</projects>" +
                "</person>" +
                "<person>" +
                "<name>Человек 2</name>" +
                "<projects>" +
                "<project title=\"Проект 1\">" +
                "<role>Менеджер</role>" +
                "<manager></manager>" +
                "</project>" +
                "</projects>" +
                "</person>" +
                "<person>" +
                "<name>Человек 3</name>" +
                "<projects>" +
                "<project title=\"Проект 1\">" +
                "<role>Специалист</role>" +
                "<manager>Человек 1</manager>" +
                "</project>" +
                "<project title=\"Проект 2\">" +
                "<role>Менеджер</role>" +
                "<manager></manager>" +
                "</project>" +
                "</projects>" +
                "</person>" +
                "</people>";
        var pers1 = new Person("Человек 1",
                new ArrayList<>(Arrays.asList(new PersonsProject("Проект 1", "Менеджер", ""))));
        var pers2 = new Person("Человек 2",
                new ArrayList<>(Arrays.asList(new PersonsProject("Проект 1", "Менеджер", ""))));
        var pers3 = new Person("Человек 3",
                new ArrayList<>(Arrays.asList(new PersonsProject("Проект 1", "Специалист", "Человек 1"),
                        new PersonsProject("Проект 2", "Менеджер", ""))));
        var people = new People(new ArrayList<>(Arrays.asList(pers1, pers2, pers3)));
        //endregion
        var actualXml = new XmlConverter().serializePeople(people);
        Assertions.assertEquals(expectedXml, actualXml);
    }

    @Test
    public void convertProjectsXmlToPeopleXmlTest() throws JAXBException {
        
        //region Init
        var inputXML = "<projects>\n" +
                "    <project>\n" +
                "        <title>Проект 1</title>\n" +
                "        <description>Описание 1</description>\n" +
                "        <managers>\n" +
                "            <manager name=\"Человек 1\">\n" +
                "                <specialists>\n" +
                "                    <specialist name=\"Человек 3\"/>\n" +
                "                    <specialist name=\"Человек 4\"/>\n" +
                "                </specialists>\n" +
                "            </manager>\n" +
                "            <manager name=\"Человек 2\">\n" +
                "                <specialists>\n" +
                "                    <specialist name=\"Человек 5\"/>\n" +
                "                    <specialist name=\"Человек 6\"/>\n" +
                "                </specialists>\n" +
                "            </manager>\n" +
                "        </managers>\n" +
                "    </project>\n" +
                "    <project>\n" +
                "        <title>Проект 2</title>\n" +
                "        <description>Описание 2</description>\n" +
                "        <managers>\n" +
                "            <manager name=\"Человек 3\">\n" +
                "                <specialists>\n" +
                "                    <specialist name=\"Человек 5\"/>\n" +
                "                    <specialist name=\"Человек 6\"/>\n" +
                "                </specialists>\n" +
                "            </manager>\n" +
                "            <manager name=\"Человек 4\">\n" +
                "                <specialists>\n" +
                "                    <specialist name=\"Человек 7\"/>\n" +
                "                </specialists>\n" +
                "            </manager>\n" +
                "        </managers>\n" +
                "    </project>\n" +
                "</projects>";

        var expectedOutXml = "<people>" +
                    "<person>" +
                        "<name>Человек 1</name>" +
                        "<projects>" +
                            "<project title=\"Проект 1\">" +
                                "<role>Менеджер</role>" +
                                "<manager></manager>" +
                            "</project>" +
                        "</projects>" +
                    "</person>" +
                    "<person>" +
                        "<name>Человек 2</name>" +
                        "<projects>" +
                            "<project title=\"Проект 1\">" +
                                "<role>Менеджер</role>" +
                                "<manager></manager>" +
                            "</project>" +
                        "</projects>" +
                    "</person>" +
                    "<person>" +
                        "<name>Человек 3</name>" +
                        "<projects>" +
                            "<project title=\"Проект 1\">" +
                                "<role>Специалист</role>" +
                                "<manager>Человек 1</manager>" +
                            "</project>" +
                            "<project title=\"Проект 2\">" +
                                "<role>Менеджер</role>" +
                                "<manager></manager>" +
                            "</project>" +
                        "</projects>" +
                    "</person>" +
                    "<person>" +
                        "<name>Человек 4</name>" +
                        "<projects>" +
                            "<project title=\"Проект 1\">" +
                                "<role>Специалист</role>" +
                                "<manager>Человек 1</manager>" +
                            "</project>" +
                            "<project title=\"Проект 2\">" +
                                "<role>Менеджер</role>" +
                                "<manager></manager>" +
                            "</project>" +
                        "</projects>" +
                    "</person>" +
                    "<person>" +
                        "<name>Человек 5</name>" +
                        "<projects>" +
                            "<project title=\"Проект 1\">" +
                                "<role>Специалист</role>" +
                                "<manager>Человек 2</manager>" +
                            "</project>" +
                            "<project title=\"Проект 2\">" +
                                "<role>Специалист</role>" +
                                "<manager>Человек 3</manager>" +
                            "</project>" +
                        "</projects>" +
                    "</person>" +
                    "<person>" +
                        "<name>Человек 6</name>" +
                        "<projects>" +
                            "<project title=\"Проект 1\">" +
                                "<role>Специалист</role>" +
                                "<manager>Человек 2</manager>" +
                            "</project>" +
                            "<project title=\"Проект 2\">" +
                                "<role>Специалист</role>" +
                                "<manager>Человек 3</manager>" +
                            "</project>" +
                        "</projects>" +
                    "</person>" +
                    "<person>" +
                        "<name>Человек 7</name>" +
                        "<projects>" +
                            "<project title=\"Проект 2\">" +
                                "<role>Специалист</role>" +
                                "<manager>Человек 4</manager>" +
                            "</project>" +
                        "</projects>" +
                    "</person>" +
                "</people>";
        //endregion
        var actualXml = new XmlConverter().convertProjectsXmlToPeopleXml(inputXML);
        Assertions.assertArrayEquals(expectedOutXml.toCharArray(), actualXml.toCharArray());
    }
}