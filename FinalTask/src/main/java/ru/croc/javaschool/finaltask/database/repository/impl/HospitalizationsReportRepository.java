package ru.croc.javaschool.finaltask.database.repository.impl;

import ru.croc.javaschool.finaltask.database.repository.ReportRepository;
import ru.croc.javaschool.finaltask.model.entity.HospitalizationsReport;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The class responsible for hospitalization report repository.
 */
public class HospitalizationsReportRepository implements ReportRepository<HospitalizationsReport> {

    private final DataSource dataSource;
    public final String TABLE_NAME = "Hospitalization_stats";

    public HospitalizationsReportRepository(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        initTable();
    }

    @Override
    public boolean initTable() throws SQLException {
        System.out.println("Инициализация таблицы: " + TABLE_NAME);
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            var databaseMetadata = connection.getMetaData();
            var resultSet = databaseMetadata.getTables(
                    null,
                    null,
                    TABLE_NAME.toUpperCase(),
                    new String[]{"TABLE"});
            if (resultSet.next()) {
                System.err.println("Таблица уже существует");
            } else {
                statement.executeUpdate(
                        "CREATE TABLE "
                                + TABLE_NAME
                                + " ("
                                + "date VARCHAR(10) NOT NULL, "
                                + "hospitalization_cases_count INT, "
                                + "discharging_cases_count INT, "
                                + "PRIMARY KEY (date)"
                                + ")");
                System.out.println(String.format("Таблица %s успешно создана", TABLE_NAME));
            }
            return true;
        } catch (SQLException e) {
            System.err.println(String.format("Возникла ошибка при создании таблицы %s: ", TABLE_NAME) + e.getMessage());
            throw e;
        }
    }

    @Override
    public HospitalizationsReport create(HospitalizationsReport report) {
        // checking if record already exists in table
        if (Objects.isNull(
                findByDate(report.getDate()))) {
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
                        report.getHospitalizationsCount());
                statement.setInt(
                        3,
                        report.getDischargedCount());
                statement.executeUpdate();

                System.out.printf("Запись в %s добавлена успешно\n", TABLE_NAME);
            } catch (SQLException ex) {
                System.err.printf("При добавлении записи в %s произошла ошибка: %s", TABLE_NAME, ex.getLocalizedMessage());
            }
        } else {
            System.err.printf("Произошла ошибка при добавлении записи в %s: запись с датой %s уже существует\n",
                    TABLE_NAME,
                    report.getDate()
            );
            return null;
        }
        return findByDate(report.getDate());
    }

    /**
     * Finds hospitalization report in database due to its date.
     *
     * @param date Date of report.
     * @return Hospitalization report with suitable date. Or null if none was found.
     */
    @Override
    public HospitalizationsReport findByDate(LocalDate date) {
        HospitalizationsReport report = null;
        var selectQuery = String.format(
                "SELECT * FROM %s WHERE date = '%s'",
                TABLE_NAME,
                date.toString());

        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(selectQuery)) {
            if (resultSet.next()) {
                report = new HospitalizationsReport(
                        LocalDate.parse(resultSet.getString(1)), // date
                        resultSet.getInt(2), // hospitalized count
                        resultSet.getInt(3) // discharged count
                );
            }
        } catch (SQLException e) {
            System.err.printf("Возникла ошибка выполнения запроса (поиск) в таблице %s: %s\n", TABLE_NAME,
                    e.getLocalizedMessage());
        }

        return report;
    }

    /*@Override
    public List<HospitalizationsReport> findByDateRange(LocalDate startDate, LocalDate endDate) {
        var reportList = new ArrayList<HospitalizationsReport>();
        for (var date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            var report = findByDate(date);
            if (report != null) {
                reportList.add(report);
            }
        }
        return reportList;
    }*/

    @Override
    public boolean deleteByDate(LocalDate date) {
        // checking if there is deleting report in the table
        if (!Objects.isNull(findByDate(date))) {
            System.out.printf("Удаление записи %s из таблицы %s\n", date, TABLE_NAME);

            String deleteQuery = String.format("DELETE FROM "
                    + TABLE_NAME
                    + " WHERE "
                    + "date = '%s'", date);
            try (Connection connection = dataSource.getConnection();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(deleteQuery);
                System.out.printf("Запись %s удалена успешно\n", date);
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
        // checks if there is any record with suitable date after deleting
        return Objects.isNull(findByDate(date));
    }
}
