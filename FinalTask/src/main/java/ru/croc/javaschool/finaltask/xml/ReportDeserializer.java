package ru.croc.javaschool.finaltask.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import ru.croc.javaschool.finaltask.model.dto.DailyReport;

import java.io.File;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class responsible for deserialization input xml to objects.
 */
public class ReportDeserializer {
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
     * Deserialize all files in named directory to reports.
     *
     * @param directoryPath Path of directory
     * @return List of reports. Empty list if no of files in directory can be deserialized.
     */
    public List<DailyReport> deserializeFromDirectory(String directoryPath) {
        ArrayList<DailyReport> reports = new ArrayList<>();
        File[] fList = new File(directoryPath).listFiles();

        if (fList != null) {
            for (File file : fList) {
                try {
                    var report = deserialize(file);
                    reports.add(report);
                } catch (Exception ex) {
                    System.err.println("Произошла ошибка при десериализации файлов директории: "
                            + ex.getLocalizedMessage());
                }
            }
        }
        return reports;
    }

    /**
     * Deserialize every file in directory which date is in the range.
     *
     * @param directoryPath Path of directory
     * @param startDate     Start date of time period
     * @param endDate       End date of time period
     * @return List of reports. Empty list if no of files in directory can be deserialized.
     */
    public List<DailyReport> deserializeFromDirectoryWithDateRange(String directoryPath,
                                                                   LocalDate startDate,
                                                                   LocalDate endDate) {
        var reports = new ArrayList<>(deserializeFromDirectory(directoryPath));
        return reports.stream()
                .filter(
                        rep -> rep.getDate().isAfter(startDate.minusDays(1))
                                && rep.getDate().isBefore(endDate.plusDays(1))
                )
                .collect(Collectors.toList());
    }
}
