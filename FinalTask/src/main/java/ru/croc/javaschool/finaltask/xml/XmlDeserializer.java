package ru.croc.javaschool.finaltask.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import ru.croc.javaschool.finaltask.object.DailyReport;

import java.io.StringReader;

/**
 * The class responsible for deserialization input xml to objects.
 */
public class XmlDeserializer {
    /**
     * Unmarshal XML string to daily report object.
     *
     * @param xml The xml string with daily report
     * @return The daily report for special date
     */
    public DailyReport deserialize(String xml) {
        try {
            var stringReader = new StringReader(xml);
            var context = JAXBContext.newInstance(DailyReport.class);
            var unmarshaller = context.createUnmarshaller();
            return (DailyReport) unmarshaller.unmarshal(stringReader);
        } catch (JAXBException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
