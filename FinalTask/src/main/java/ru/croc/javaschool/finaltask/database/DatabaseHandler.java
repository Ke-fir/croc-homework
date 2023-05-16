package ru.croc.javaschool.finaltask.database;

import ru.croc.javaschool.finaltask.model.input.DailyReport;
import ru.croc.javaschool.finaltask.model.output.HospitalizationsReport;
import ru.croc.javaschool.finaltask.model.output.InfectionsReport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class responsible for work with database.
 */
public class DatabaseHandler {
    public final String url = "jdbc:derby:covidStatistics;create=true";
    public final String hospitalizationTableName = "Hospitalization_stats";
    public final String infectionTableName = "Infection_stats";

    /**
     * Default constructor.
     */
    public DatabaseHandler() {
    }

    /**
     * Creates tables in database
     *
     * @return True if creating was done. False if exception was thrown.
     */
    public boolean createTables() {
        if (!checkTablesExist()) {
            try (Connection connection = DriverManager.getConnection(url);
                 Statement statement = connection.createStatement()) {
                String createHospitalStats = "CREATE TABLE " + hospitalizationTableName + " (" +
                        "date VARCHAR(10) NOT NULL, " +
                        "hospitalization_cases_count INT, " +
                        "discharging_cases_count INT, " +
                        "PRIMARY KEY (date))";

                String createInfectionStats = "CREATE TABLE " + infectionTableName + " (" +
                        "date VARCHAR(10) NOT NULL, " +
                        "infection_cases_count INT, " +
                        "recovery_cases_count INT, " +
                        "PRIMARY KEY (date))";

                statement.executeUpdate(createInfectionStats);
                statement.executeUpdate(createHospitalStats);
                return true;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Checks if DB has tables.
     *
     * @return True if tables exists. False if don't.
     */
    public boolean checkTablesExist() {
        try (Connection connection = DriverManager.getConnection(url)) {
            var meta = connection.getMetaData();
            boolean hospitalizationExists = meta.getTables(null, null, hospitalizationTableName.toUpperCase(),
                    null).next();
            boolean infectionExists = meta.getTables(null, null, infectionTableName.toUpperCase(),
                    null).next();
            return hospitalizationExists && infectionExists;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Writes new record to infections table.
     *
     * @param report Infections daily report
     * @return True if was done. False if sth went wrong
     */
    public boolean addRecordToInfectionStats(InfectionsReport report) {
        if (checkTablesExist()) {
            try (Connection connection = DriverManager.getConnection(url);
                 PreparedStatement statement = connection.prepareStatement(
                         "INSERT INTO " + infectionTableName + " VALUES (?, ?, ?)")) {
                statement.setString(1, report.getDate().toString());
                statement.setInt(2, report.getInfectionCasesCount());
                statement.setInt(3, report.getRecoveryCasesCount());
                statement.executeUpdate();
                return true;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        System.err.println("Not all tables exist");
        return false;
    }

    /**
     * Writes new record to hospitalizations table.
     *
     * @param report Hospitalizations daily report
     * @return True if was done. False if sth went wrong
     */
    public boolean addRecordToHospitalizationStats(HospitalizationsReport report) {
        if (checkTablesExist()) {
            try (Connection connection = DriverManager.getConnection(url);
                 PreparedStatement statement = connection.prepareStatement(
                         "INSERT INTO " + hospitalizationTableName + " VALUES (?, ?, ?)")) {
                statement.setString(1, report.getDate().toString());
                statement.setInt(2, report.getHospitalizationsCount());
                statement.setInt(3, report.getDischargedCount());
                statement.executeUpdate();
                return true;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        System.err.println("Not all tables exist");
        return false;
    }

    /**
     * Writes every daily report to database tables.
     *
     * @param reports List of daily reports.
     * @return True if was done. False if sth went wrong.
     */
    public boolean writeReportsToDatabase(List<DailyReport> reports) {
        // filling list of dates to check if the report was already added to DB
        var infectionReportDates = new ArrayList<String>();
        var hospitalizationReportDates = new ArrayList<String>();
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT date FROM " + infectionTableName);
            while (rs.next()) {
                infectionReportDates.add(rs.getString(1));
            }
            rs = statement.executeQuery("SELECT date FROM " + hospitalizationTableName);
            while (rs.next()) {
                hospitalizationReportDates.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.err.println("Произошла ошибка при чтении записей из БД: " + ex.getLocalizedMessage());
            return false;
        }

        // adding reports to DB
        for (var report : reports) {
            var infectionReport = report.toInfectionsReport();
            var hospitalizationReport = report.toHospitalizationsReport();
            if (!infectionReportDates.contains(infectionReport.getDate().toString())) {
                addRecordToInfectionStats(infectionReport);
            }
            if (!hospitalizationReportDates.contains(hospitalizationReport.getDate().toString())) {
                addRecordToHospitalizationStats(hospitalizationReport);
            }
        }
        return true;
    }
}
