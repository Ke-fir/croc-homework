package ru.croc.javaschool.finaltask.database;

import ru.croc.javaschool.finaltask.object.HospitalizationsReport;
import ru.croc.javaschool.finaltask.object.InfectionsReport;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The class responsible for work with database.
 */
public class DatabaseHandler {
    public final String url = "jdbc:derby:covidStatistics;create=true";

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
                String createHospitalStats = "CREATE TABLE Hospitalization_stats (" +
                        "date VARCHAR(10) NOT NULL, " +
                        "hospitalization_cases_count INT, " +
                        "discharging_cases_count INT, " +
                        "PRIMARY KEY (date))";

                String createInfectionStats = "CREATE TABLE Infection_stats (" +
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

    public boolean checkTablesExist() {
        try (Connection connection = DriverManager.getConnection(url)) {
            var meta = connection.getMetaData();
            boolean hospitalizationExists = meta.getTables(null, null, "Hospitalization_stats".toUpperCase(),
                    null).next();
            boolean infectionExists = meta.getTables(null, null, "Infection_stats".toUpperCase(),
                    null).next();
            return hospitalizationExists && infectionExists;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean addRecordToInfectionStats(InfectionsReport report) {
        if (checkTablesExist()) {
            try (Connection connection = DriverManager.getConnection(url);
                 PreparedStatement statement = connection.prepareStatement(
                         "INSERT INTO Infection_stats VALUES (?, ?, ?)")) {
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

    public boolean addRecordToHospitalizationStats(HospitalizationsReport report) {
        if (checkTablesExist()) {
            try (Connection connection = DriverManager.getConnection(url);
                 PreparedStatement statement = connection.prepareStatement(
                         "INSERT INTO Hospitalization_stats VALUES (?, ?, ?)")) {
                statement.setString(1, report.getDate().toString());
                statement.setInt(2, report.getHospitalizationsCount());
                statement.setInt(3, report.getDischargedCount());
                return true;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        System.err.println("Not all tables exist");
        return false;
    }

    public List<HospitalizationsReport> readHospitalizations() {
        var result = new ArrayList<HospitalizationsReport>();
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            var resultSet = statement.executeQuery("SELECT * FROM Hospitalization_stats");
            while (resultSet.next()) {
                result.add( new HospitalizationsReport(
                        LocalDate.parse(resultSet.getString(1)),
                        resultSet.getInt(2),
                        resultSet.getInt(3)));
            }
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<InfectionsReport> readInfections() {
        var result = new ArrayList<InfectionsReport>();
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            var resultSet = statement.executeQuery("SELECT * FROM Infection_stats");
            while (resultSet.next()) {
                result.add( new InfectionsReport(
                        LocalDate.parse(resultSet.getString(1)),
                        resultSet.getInt(2),
                        resultSet.getInt(3)));
            }
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
