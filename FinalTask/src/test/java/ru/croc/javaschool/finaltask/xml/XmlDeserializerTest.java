package ru.croc.javaschool.finaltask.xml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finaltask.object.DailyReport;

import java.time.LocalDate;
import java.time.Month;

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
}
