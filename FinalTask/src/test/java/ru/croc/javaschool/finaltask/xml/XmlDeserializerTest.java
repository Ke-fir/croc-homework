package ru.croc.javaschool.finaltask.xml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finaltask.object.DailyReport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

/**
 * The class responsible for testing deserialization from XML.
 */
public class XmlDeserializerTest {
    @Test
    public void deserializeTest() {
        //region Init
        var xml =
                "<report date=\"2023-05-14\">\n" +
                        "    <infectionCasesCount>20000</infectionCasesCount>\n" +
                        "    <recoveryCasesCount>21000</recoveryCasesCount>\n" +
                        "    <hospitalizationCasesCount>5000</hospitalizationCasesCount>\n" +
                        "    <dischargingCasesCount>5500</dischargingCasesCount>\n" +
                        "</report>";
        //endregion

        var expectedReport = new DailyReport(LocalDate.of(2023, Month.MAY, 14), 20000,
                21000, 5000, 5500);
        var actualReport = new XmlDeserializer().deserialize(xml);
        Assertions.assertEquals(expectedReport, actualReport);
    }

    @Test
    public void deserializeFileTest() {
        //region INIT
        var xml =
                "<report date=\"2023-05-14\">\n" +
                        "    <infectionCasesCount>20000</infectionCasesCount>\n" +
                        "    <recoveryCasesCount>21000</recoveryCasesCount>\n" +
                        "    <hospitalizationCasesCount>5000</hospitalizationCasesCount>\n" +
                        "    <dischargingCasesCount>5500</dischargingCasesCount>\n" +
                        "</report>";
        var expectedReport = new DailyReport(LocalDate.of(2023, Month.MAY, 14), 20000,
                21000, 5000, 5500);
        var tempFile = new File("deserializationTest.xml");
        try (var pw = new PrintWriter(tempFile)) {
            pw.write(xml);
        } catch (FileNotFoundException ex) {
            System.err.println("Произошла ошибка при записи в файл: " + ex.getLocalizedMessage());
        }
        // endregion

        var deserializer = new XmlDeserializer();
        var actualReport = deserializer.deserialize(tempFile);
        tempFile.delete();
        Assertions.assertEquals(expectedReport, actualReport);
    }

    @Test
    public void deserializeFromDirectoryTest() {
        //region INIT
        String directoryName = "reports";
        var expectedDailyReports = new ArrayList<DailyReport>();
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
            expectedDailyReports.add(new DailyReport(LocalDate.parse(sDate), infectionCasesNumber, recoveryCasesNumber,
                    hospitalizationsNumber, dischargedNumber));
            //writing xml to file
            try (var pw = new PrintWriter(file)) {
                pw.write(xml);
            } catch (FileNotFoundException ex) {
                System.err.println("Произошла ошибка при записи в файл: " + ex.getLocalizedMessage());
            }
        }
        //endregion

        var deserializer = new XmlDeserializer();
        var actualReports = deserializer.deserializeFromDirectory(directoryName, LocalDate.of(2023, Month.MAY, 15),
                LocalDate.of(2023, Month.MAY, 21));
        Assertions.assertEquals(expectedDailyReports, actualReports);
    }
}
