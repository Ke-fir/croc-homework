package ru.croc.javaschool.finaltask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finaltask.database.provider.DataSourceProvider;
import ru.croc.javaschool.finaltask.database.repository.HospitalizationsReportRepository;
import ru.croc.javaschool.finaltask.database.repository.InfectionsReportRepository;
import ru.croc.javaschool.finaltask.model.output.HospitalizationsReport;
import ru.croc.javaschool.finaltask.model.output.InfectionsReport;
import ru.croc.javaschool.finaltask.service.DailyReportService;
import ru.croc.javaschool.finaltask.service.xml.XmlDeserializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

/**
 * The class responsible for presenting demo scenery.
 */
public class PresentationTest {
    private final String directoryName = "reports";
    private  ArrayList<InfectionsReport> expectedInfectionReports = new ArrayList<>();
    private ArrayList<HospitalizationsReport> expectedHospitalizationReports = new ArrayList<>();
    @BeforeEach
    public void init() {
        // creating directory with reports if not created
        File directory = new File(directoryName);
        directory.mkdir();
        // creating 7 files in directory (like we work with xml ones per week)
        for (int day = 15; day < 22; day++) {
            String sDate = "2023-05-" + day;
            String filename = sDate + ".xml";
            var file = new File(directory, filename);
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // some random numbers, no logic
            int infectionCasesNumber = day * 100;
            int recoveryCasesNumber = day * 100 + 20;
            int hospitalizationsNumber = day * 90;
            int dischargedNumber = day + 3 * 90;
            String xml = "<report date=\"" + sDate + "\">\n" +
                    "    <infectionCasesCount>" + infectionCasesNumber + "</infectionCasesCount>\n" +
                    "    <recoveryCasesCount>" + recoveryCasesNumber + "</recoveryCasesCount>\n" +
                    "    <hospitalizationCasesCount>" + hospitalizationsNumber + "</hospitalizationCasesCount>\n" +
                    "    <dischargingCasesCount>" + dischargedNumber + "</dischargingCasesCount>\n" +
                    "</report>";
            // adding info to expected reports
            expectedInfectionReports.add(new InfectionsReport(LocalDate.parse(sDate), infectionCasesNumber,
                    recoveryCasesNumber));
            expectedHospitalizationReports.add(new HospitalizationsReport(LocalDate.parse(sDate),
                    hospitalizationsNumber, dischargedNumber));
            //writing xml to file
            try (var pw = new PrintWriter(file)) {
                pw.write(xml);
            } catch (FileNotFoundException ex) {
                System.err.println("Произошла ошибка при записи в файл: " + ex.getLocalizedMessage());
            }
        }
    }

    /**
     * Presents working scenery.
     */
    @Test
    public void present() throws SQLException {
        var actualInfectionReports = new ArrayList<InfectionsReport>();
        var actualHospitalizationReports = new ArrayList<HospitalizationsReport>();
        XmlDeserializer deserializer = new XmlDeserializer();
        // getting daily reports from directory "reports"
        var dailyReports = deserializer.deserializeFromDirectory(directoryName, LocalDate.of(2023, Month.MAY, 15),
                LocalDate.of(2023, Month.MAY, 21));

        // initialization
        var dataSource = new DataSourceProvider().getDataSource();
        var infectionTableRepository = new InfectionsReportRepository(dataSource);
        var hospitalizationTableRepository = new HospitalizationsReportRepository(dataSource);
        var dailyReportsService = new DailyReportService(hospitalizationTableRepository, infectionTableRepository);

        // adding all deserialized daily reports from directory to DB
        dailyReportsService.createAllReports(dailyReports);

        for (var dailyReport : dailyReports) {
            var date = dailyReport.getDate();
            // adding report from DB to actual list
            actualInfectionReports.add(
                    infectionTableRepository.find(date)
            );
            // deleting temporary record from DB
            infectionTableRepository.delete(date);
            // adding report from DB to actual list
            actualHospitalizationReports.add(
                    hospitalizationTableRepository.find(date)
            );
            // deleting temporary record from DB
            hospitalizationTableRepository.delete(date);
        }

        Assertions.assertEquals(expectedHospitalizationReports, actualHospitalizationReports);
        Assertions.assertEquals(expectedInfectionReports, actualInfectionReports);
    }
}
