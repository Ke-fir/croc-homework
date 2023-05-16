package ru.croc.javaschool.finaltask.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finaltask.model.input.DailyReport;
import ru.croc.javaschool.finaltask.model.output.HospitalizationsReport;
import ru.croc.javaschool.finaltask.model.output.InfectionsReport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

/**
 * Test class for database handler.
 */
public class DatabaseHandlerTest {
    DatabaseHandler handler = new DatabaseHandler();

    @Test
    public void checkTablesExistTest() {
        handler.checkTablesExist();
    }

    @Test
    public void createTablesTest() {
        handler.createTables();
    }

    @Test
    public void addRecordToInfectionStatsTest() throws SQLException {
        var expectedReport = new InfectionsReport(LocalDate.now(), 15, 20);
        var actualLastReport = new InfectionsReport();

        // adding new record
        if (handler.addRecordToInfectionStats(expectedReport)) {
            // reading last record in table
            Connection connection = DriverManager.getConnection(handler.url);
            Statement statement = connection.createStatement();
            var resultSet = statement.executeQuery("SELECT date, infection_cases_count, recovery_cases_count " +
                    "FROM " + handler.infectionTableName);
            while (resultSet.next()) {
                actualLastReport = new InfectionsReport(
                        LocalDate.parse(resultSet.getString(1)),
                        resultSet.getInt(2),
                        resultSet.getInt(3));
            }
            // deleting added record
            statement.executeUpdate("DELETE FROM " + handler.infectionTableName + " WHERE date = '" + expectedReport.getDate().toString() + "'");
            connection.close();
            statement.close();
        }

        Assertions.assertEquals(expectedReport, actualLastReport);
    }

    @Test
    public void addRecordToHospitalizationStatsTest() throws SQLException {
        var expectedReport = new HospitalizationsReport(LocalDate.now(), 15, 20);
        var actualLastReport = new HospitalizationsReport();

        // adding new record
        if (handler.addRecordToHospitalizationStats(expectedReport)) {
            // reading last record in table
            Connection connection = DriverManager.getConnection(handler.url);
            Statement statement = connection.createStatement();
            var resultSet = statement.executeQuery("SELECT * FROM " + handler.hospitalizationTableName);
            while (resultSet.next()) {
                actualLastReport = new HospitalizationsReport(
                        LocalDate.parse(resultSet.getString(1)),
                        resultSet.getInt(2),
                        resultSet.getInt(3));
            }
            // deleting added record
            statement.executeUpdate("DELETE FROM " + handler.hospitalizationTableName +
                    " WHERE date = '" + expectedReport.getDate().toString() + "'");
            connection.close();
            statement.close();
        }

        Assertions.assertEquals(expectedReport, actualLastReport);
    }

    /**
     * Tests writing list of daily reports to DB.
     * @throws SQLException
     */
    @Test
    public void writeReportsToDatabaseTest() throws SQLException {
        //region INIT
        ArrayList<DailyReport> dailyReports = new ArrayList<>();
        dailyReports.add(new DailyReport(LocalDate.of(2023, Month.MAY, 15),
                15, 20, 10, 12));
        dailyReports.add(new DailyReport(LocalDate.of(2023, Month.MAY, 14),
                155, 520, 110, 120));
        dailyReports.add(new DailyReport(LocalDate.of(2023, Month.MAY, 13),
                315, 260, 90, 182));

        var expectedInfectionReports = new ArrayList<InfectionsReport>();
        var expectedHospitalizationReports = new ArrayList<HospitalizationsReport>();

        dailyReports.stream().forEach(x -> {
            expectedInfectionReports.add(x.toInfectionsReport());
            expectedHospitalizationReports.add(x.toHospitalizationsReport());
        });
        //endregion

        var actualInfectionReports = new ArrayList<InfectionsReport>();
        var actualHospitalizationReports = new ArrayList<HospitalizationsReport>();
        if (handler.writeReportsToDatabase(dailyReports)) {
            Connection connection = DriverManager.getConnection(handler.url);
            Statement statement = connection.createStatement();
            var resultSet = statement.executeQuery("SELECT * FROM " + handler.hospitalizationTableName);
            while (resultSet.next()) {
                actualHospitalizationReports.add(new HospitalizationsReport(
                        LocalDate.parse(resultSet.getString(1)),
                        resultSet.getInt(2),
                        resultSet.getInt(3)));
            }
            resultSet = statement.executeQuery("SELECT * FROM " + handler.infectionTableName);
            while (resultSet.next()) {
                actualInfectionReports.add(new InfectionsReport(
                        LocalDate.parse(resultSet.getString(1)),
                        resultSet.getInt(2),
                        resultSet.getInt(3)));
            }
            for (var rep : expectedInfectionReports) {
                statement.executeUpdate("DELETE FROM " + handler.infectionTableName +
                        " WHERE date = '" + rep.getDate().toString() + "'");
            }
            for (var rep : expectedHospitalizationReports) {
                statement.executeUpdate("DELETE FROM " + handler.hospitalizationTableName +
                        " WHERE date = '" + rep.getDate().toString() + "'");
            }
            connection.close();
            statement.close();
        }
        Assertions.assertEquals(expectedHospitalizationReports, actualHospitalizationReports);
        Assertions.assertEquals(expectedInfectionReports, actualInfectionReports);
    }
}
