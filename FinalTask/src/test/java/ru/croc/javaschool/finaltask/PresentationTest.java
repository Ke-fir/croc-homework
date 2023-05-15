package ru.croc.javaschool.finaltask;

import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The class responsible for presenting demo scenery.
 */
public class PresentationTest {

    @BeforeAll
    public void init() {
        // creating directory with reports if not created
        File directory = new File("Reports");
        directory.mkdir();
        // creating 7 files in directory (like we work with xml ones per week)
        for (int day = 8; day < 15; day++) {
            String sDate = "2023-05-" + day;
            String filename = sDate + ".xml";
            var file = new File(directory, filename);
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            String xml = "<report date=\"" + sDate + "\">\n" +
                    "    <infectionCasesNumber>" + day * 100 + "</infectionCasesNumber>\n" +
                    "    <recoveryCasesNumber>" + (day * 100 + 20) + "</recoveryCasesNumber>\n" +
                    "    <hospitalizationsNumber>" + day * 90 + "</hospitalizationsNumber>\n" +
                    "    <dischargedNumber>" + (day * 90 + 10) + "</dischargedNumber>\n" +
                    "</report>";
            try (var pw = new PrintWriter(file)) {
                pw.write(xml);
            } catch (FileNotFoundException ex) {
                System.err.println("Произошла ошибка при записи в файл: " + ex.getLocalizedMessage());
            }

        }
    }
}
