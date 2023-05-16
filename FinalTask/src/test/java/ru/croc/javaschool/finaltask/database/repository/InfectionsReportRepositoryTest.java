package ru.croc.javaschool.finaltask.database.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finaltask.database.provider.DataSourceProvider;
import ru.croc.javaschool.finaltask.model.output.InfectionsReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The class for testing hospitalization repository.
 */
public class InfectionsReportRepositoryTest {
    private final DataSourceProvider dataSourceProvider = new DataSourceProvider();
    private final InfectionsReportRepository repository =
            new InfectionsReportRepository(dataSourceProvider.getDataSource());

    public InfectionsReportRepositoryTest() throws SQLException {
    }

    //region independent_repository_functions
    public void delete(LocalDate date) throws SQLException {
        // checking if there is deleting report in the table
        if (!Objects.isNull(repository.find(date))) {
            String deleteQuery = String.format("DELETE FROM "
                    + repository.TABLE_NAME
                    + " WHERE "
                    + "date = '%s'", date);
            try (Connection connection = dataSourceProvider.getDataSource().getConnection();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(deleteQuery);
            } catch (SQLException e) {
                throw e;
            }
        }
    }

    public InfectionsReport find(LocalDate date) throws SQLException {
        InfectionsReport report = null;
        var selectQuery = String.format(
                "SELECT * FROM %s WHERE date = '%s'",
                repository.TABLE_NAME,
                date.toString());

        try (var connection = dataSourceProvider.getDataSource().getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(selectQuery)) {
            if (resultSet.next()) {
                report = new InfectionsReport(
                        LocalDate.parse(resultSet.getString(1)), // date
                        resultSet.getInt(2), // hospitalized count
                        resultSet.getInt(3) // discharged count
                );
            }
        } catch (SQLException e) {
            throw e;
        }

        return report;
    }

    public boolean create(InfectionsReport report) throws SQLException {
        // checking if record already exists in table
        if (Objects.isNull(
                repository.find(report.getDate()))) {

            String insertQuery = "INSERT INTO "
                    + repository.TABLE_NAME
                    + " VALUES (?, ?, ?)";

            try (Connection connection = dataSourceProvider.getDataSource().getConnection();
                 PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(
                        1,
                        report.getDate().toString());
                statement.setInt(
                        2,
                        report.getInfectionCasesCount());
                statement.setInt(
                        3,
                        report.getRecoveryCasesCount());
                statement.executeUpdate();
                return true;
            } catch (SQLException ex) {
                throw ex;
            }
        }
        return false;
    }
    //endregion

    @Test
    public void initTableTest() throws SQLException {
        repository.initTable();
    }

    @Test
    public void findTest() throws SQLException {
        InfectionsReport rep = new InfectionsReport(
                LocalDate.now(),
                123,
                321
        );
        this.create(rep);
        var actualrep = repository.find(rep.getDate());
        this.delete(rep.getDate());

        Assertions.assertEquals(rep, actualrep);
    }

    @Test
    public void createTest() throws SQLException {
        InfectionsReport rep = new InfectionsReport(
                LocalDate.now(),
                123,
                321
        );
        repository.create(rep);
        var actualrep = this.find(rep.getDate());
        this.delete(rep.getDate());

        Assertions.assertEquals(rep, actualrep);
    }

    @Test
    public void deleteTest() throws SQLException {
        InfectionsReport rep = new InfectionsReport(
                LocalDate.now(),
                123,
                321
        );
        this.create(rep);
        repository.delete(rep.getDate());
        var actualrep = this.find(rep.getDate());


        Assertions.assertEquals(null, actualrep);
    }
}
