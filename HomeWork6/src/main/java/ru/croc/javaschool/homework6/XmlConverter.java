package ru.croc.javaschool.homework6;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import ru.croc.javaschool.homework6.inputxmlobjects.Project;
import ru.croc.javaschool.homework6.inputxmlobjects.Projects;

import java.io.StringReader;
import java.util.List;

/**
 * Class that response to convert XML to objects and from objects to XML of another format.
 */
public class XmlConverter {
    private final String xml;

    public XmlConverter(String xml){
        this.xml = xml;
    }
    public List<Project> getProjects() throws JAXBException {
        //var projectsarr = new ArrayList<Project>();
        var stringReader = new StringReader(xml);
        var context = JAXBContext.newInstance(Projects.class);
        var unmarshaller = context.createUnmarshaller();
        var projects = (Projects) unmarshaller.unmarshal(stringReader);
        return projects.getProjects();
    }
}
