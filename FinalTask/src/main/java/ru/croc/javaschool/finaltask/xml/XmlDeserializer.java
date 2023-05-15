package ru.croc.javaschool.finaltask.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import ru.croc.javaschool.finaltask.object.DailyReport;

import java.io.File;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Deserialize daily report from file.
     *
     * @param file
     * @return Daily report. Null if file can't be deserialized.
     */
    public DailyReport deserialize(File file) {
        try {
            var context = JAXBContext.newInstance(DailyReport.class);
            var unmarshaller = context.createUnmarshaller();
            return (DailyReport) unmarshaller.unmarshal(file);
        } catch (JAXBException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Deserialize every file in directory that's date is between start & end dates.
     * Can be used for deserializing files for some time period.
     *
     * @param directoryPath Path of directory
     * @param startDate Start date of time period
     * @param endDate End date of time period
     * @return List of reports. Empty list if no of files in directory can be deserialized.
     */
    public List<DailyReport> deserializeFromDirectory(String directoryPath, LocalDate startDate, LocalDate endDate) {
        ArrayList<DailyReport> reports = new ArrayList<>();
        File[] fList = new File(directoryPath).listFiles();
        
        if (fList != null) {
            for (File file : fList) {
                try {
                    var report = deserialize(file);
                    if (startDate.compareTo(report.getDate()) < 1 && endDate.compareTo(report.getDate()) > -1) {
                        reports.add(report);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return reports;
    }
}
