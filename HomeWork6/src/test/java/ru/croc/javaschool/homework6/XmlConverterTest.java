package ru.croc.javaschool.homework6;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.homework6.inputxmlobjects.Project;

import java.util.ArrayList;

public class XmlConverterTest {
    private String inputXML;

    private String outXml;

    private ArrayList<Project> projects = new ArrayList<>();

    @BeforeEach
    public void init(){
        inputXML = "<projects>\n" +
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

        outXml = "<people>\n" +
                "    <person>\n" +
                "        <name>Человек 1</name>\n" +
                "        <projects>\n" +
                "            <project title=\"Проект 1\">\n" +
                "                <role>Менеджер</role>\n" +
                "                <manager></manager>\n" +
                "            </project>\n" +
                "        </projects>\n" +
                "    </person>\n" +
                "    <person>\n" +
                "        <name>Человек 2</name>\n" +
                "        <projects>\n" +
                "            <project title=\"Проект 1\">\n" +
                "                <role>Менеджер</role>\n" +
                "                <manager></manager>\n" +
                "            </project>\n" +
                "        </projects>\n" +
                "    </person>\n" +
                "    <person>\n" +
                "        <name>Человек 3</name>\n" +
                "        <projects>\n" +
                "            <project title=\"Проект 1\">\n" +
                "                <role>Специалист</role>\n" +
                "                <manager>Человек 1</manager>\n" +
                "            </project>\n" +
                "            <project title=\"Проект 2\">\n" +
                "                <role>Менеджер</role>\n" +
                "                <manager></manager>\n" +
                "            </project>\n" +
                "        </projects>\n" +
                "    </person>\n" +
                "    <person>\n" +
                "        <name>Человек 4</name>\n" +
                "        <projects>\n" +
                "            <project title=\"Проект 1\">\n" +
                "                <role>Специалист</role>\n" +
                "                <manager>Человек 1</manager>\n" +
                "            </project>\n" +
                "            <project title=\"Проект 2\">\n" +
                "                <role>Менеджер</role>\n" +
                "                <manager></manager>\n" +
                "            </project>\n" +
                "        </projects>\n" +
                "    </person>\n" +
                "    <person>\n" +
                "        <name>Человек 5</name>\n" +
                "        <projects>\n" +
                "            <project title=\"Проект 1\">\n" +
                "                <role>Специалист</role>\n" +
                "                <manager>Человек 2</manager>\n" +
                "            </project>\n" +
                "            <project title=\"Проект 2\">\n" +
                "                <role>Специалист</role>\n" +
                "                <manager>Человек 3</manager>\n" +
                "            </project>\n" +
                "        </projects>\n" +
                "    </person>\n" +
                "    <person>\n" +
                "        <name>Человек 6</name>\n" +
                "        <projects>\n" +
                "            <project title=\"Проект 1\">\n" +
                "                <role>Специалист</role>\n" +
                "                <manager>Человек 2</manager>\n" +
                "            </project>\n" +
                "            <project title=\"Проект 2\">\n" +
                "                <role>Специалист</role>\n" +
                "                <manager>Человек 3</manager>\n" +
                "            </project>\n" +
                "        </projects>\n" +
                "    </person>\n" +
                "    <person>\n" +
                "        <name>Человек 7</name>\n" +
                "        <projects>\n" +
                "            <project title=\"Проект 2\">\n" +
                "                <role>Специалист</role>\n" +
                "                <manager>Человек 4</manager>\n" +
                "            </project>\n" +
                "        </projects>\n" +
                "    </person>\n" +
                "</people>";
    }

    @Test
    public void getProjectsTest() throws JAXBException {
        var deser = new XmlConverter(inputXML);
        var projects = deser.getProjects();
    }
}
