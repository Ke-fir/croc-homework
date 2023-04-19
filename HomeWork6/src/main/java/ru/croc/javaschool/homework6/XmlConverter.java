package ru.croc.javaschool.homework6;

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

    public Projects deserializeProjects(String xml) throws JAXBException {
        var stringReader = new StringReader(xml);
        var context = JAXBContext.newInstance(Projects.class);
        var unmarshaller = context.createUnmarshaller();
        var projects = (Projects) unmarshaller.unmarshal(stringReader);
        return projects;
    }

    public String serializePeople(People people) throws JAXBException {
        var stringWriter = new StringWriter();
        var context = JAXBContext.newInstance(People.class);
        var marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        marshaller.marshal(people, stringWriter);
        return stringWriter.toString();
    }
}
