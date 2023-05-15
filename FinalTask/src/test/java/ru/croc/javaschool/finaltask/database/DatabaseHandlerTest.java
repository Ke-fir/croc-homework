package ru.croc.javaschool.finaltask.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finaltask.object.HospitalizationsReport;
import ru.croc.javaschool.finaltask.object.InfectionsReport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

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
                    "FROM Infection_stats");
            while (resultSet.next()) {
                actualLastReport = new InfectionsReport(
                        LocalDate.parse(resultSet.getString(1)),
                        resultSet.getInt(2),
                        resultSet.getInt(3));
            }
            // deleting added record
            statement.executeUpdate("DELETE FROM Infection_stats WHERE date = '" + expectedReport.getDate().toString() + "'");
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
            var resultSet = statement.executeQuery("SELECT * FROM Hospitalization_stats");
            while (resultSet.next()) {
                actualLastReport = new HospitalizationsReport(
                        LocalDate.parse(resultSet.getString(1)),
                        resultSet.getInt(2),
                        resultSet.getInt(3));
            }
            // deleting added record
            statement.executeUpdate("DELETE FROM Hospitalization_stats WHERE date = '" + expectedReport.getDate().toString() + "'");
            connection.close();
            statement.close();
        }

        Assertions.assertEquals(expectedReport, actualLastReport);
    }
}
