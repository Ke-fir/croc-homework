package ru.croc.javaschool.finaltask.database.repository;

import ru.croc.javaschool.finaltask.model.output.InfectionsReport;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The class responsible for infection report repository.
 */
public class InfectionsReportRepository implements ReportRepository<InfectionsReport> {

    private final DataSource dataSource;
    public final String TABLE_NAME = "Infection_stats";

    public InfectionsReportRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        initTable();
    }

    @Override
    public boolean initTable() {
        System.out.println("Инициализация таблицы: " + TABLE_NAME);
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            var databaseMetadata = connection.getMetaData();
            var resultSet = databaseMetadata.getTables(
                    null,
                    null,
                    TABLE_NAME.toUpperCase(),
                    null
            );
            if (resultSet.next()) {
                System.out.println("Таблица уже существует");
            } else {
                statement.executeUpdate(
                        "CREATE TABLE "
                                + TABLE_NAME
                                + "date VARCHAR(10) NOT NULL, "
                                + "infection_cases_count INT, "
                                + "recovery_cases_count INT, "
                                + "PRIMARY KEY (date)"
                                + ")");
                System.out.println(String.format("Таблица %s успешно создана", TABLE_NAME));
            }
            return true;
        } catch (SQLException e) {
            System.out.println(String.format("Возникла ошибка при создании таблицы %s: ", TABLE_NAME) + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean create(InfectionsReport report) {
        // checking if record already exists in table
        if (Objects.isNull(
                find(report.getDate())
        )) {
            System.out.println("Добавление записи в таблицу " + TABLE_NAME);

            String insertQuery = "INSERT INTO "
                    + TABLE_NAME
                    + " VALUES (?, ?, ?)";

            try (Connection connection = dataSource.getConnection();
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

                System.out.printf("Запись в %s добавлена успешно", TABLE_NAME);
                return true;
            } catch (SQLException ex) {
                System.err.printf("При добавлении записи в %s произошла ошибка: %s", TABLE_NAME, ex.getLocalizedMessage());

            }
        } else {
            System.err.printf("Произошла ошибка при добавлении записи в %s: запись с датой %s уже существует",
                    TABLE_NAME,
                    report.getDate()
            );
        }
        return false;
    }

    @Override
    public InfectionsReport find(LocalDate date) {
        InfectionsReport report = null;
        var selectQuery = String.format(
                "SELECT * FROM %s WHERE date = '%s'",
                TABLE_NAME,
                date.toString());

        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(selectQuery)) {
            if (resultSet.next()) {
                report = new InfectionsReport(
                        LocalDate.parse(resultSet.getString(1)), // date
                        resultSet.getInt(2), // hospitalized count
                        resultSet.getInt(3)// discharged count
                );
            }
        } catch (SQLException e) {
            System.err.printf("Возникла ошибка выполнения запроса (поиск) в таблице %s: %s ", TABLE_NAME,
                    e.getLocalizedMessage());
        }
        return report;
    }

    @Override
    public boolean delete(LocalDate date) {
        // checking if there is deleting report in the table
        if (!Objects.isNull(find(date))) {
            System.out.printf("Удаление записи %s из таблицы $s", date, TABLE_NAME);

            String deleteQuery = String.format("DELETE FROM "
                    + TABLE_NAME
                    + " WHERE "
                    + "date = '%s'", date);
            try (Connection connection = dataSource.getConnection();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(deleteQuery);
                System.out.printf("Запись %s удалена успешно", date);
                return true;
            } catch (SQLException e) {
                System.err.printf("При удалении %s из %s произошла ошибка: %s",
                        date,
                        TABLE_NAME,
                        e.getLocalizedMessage()
                );
            }
        } else {
            System.err.printf("Произошла ошибка при удалении записи %s: ее не существует в %s",
                    date,
                    TABLE_NAME);
        }
        return false;
    }
}