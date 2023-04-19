package ru.croc.javaschool.homework6.converter;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import ru.croc.javaschool.homework6.inputxmlobject.Projects;
import ru.croc.javaschool.homework6.outxmlobject.People;

import java.io.StringReader;
import java.io.StringWriter;

/**
 * Class that response to convert XML to objects and from objects to XML of another format.
 */
public class XmlConverter {
    /**
     * Deserialize input XML to list of projects
     *
     * @param xml input XML string
     * @return projects
     * @throws JAXBException
     */
    public Projects deserializeProjects(String xml) throws JAXBException {
        var stringReader = new StringReader(xml);
        var context = JAXBContext.newInstance(Projects.class);
        var unmarshaller = context.createUnmarshaller();
        var projects = (Projects) unmarshaller.unmarshal(stringReader);
        return projects;
    }

    /**
     * Serialize list of person to XML
     *
     * @param people list of person
     * @return XML string
     * @throws JAXBException
     */
    public String serializePeople(People people) throws JAXBException {
        var stringWriter = new StringWriter();
        var context = JAXBContext.newInstance(People.class);
        var marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        marshaller.marshal(people, stringWriter);
        return stringWriter.toString();
    }

    /**
     * Converts XML with root element named "projects" to XML with root element "people"
     *
     * @param projectsXml XML string of projects
     * @return XML strin of people
     * @throws JAXBException
     */
    public String convertProjectsXmlToPeopleXml(String projectsXml) throws JAXBException {
        var projects = deserializeProjects(projectsXml);
        var people = ObjectConverter.convertProjectsToPeople(projects);
        return serializePeople(people);
    }
}
