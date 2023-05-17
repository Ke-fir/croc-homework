package ru.croc.javaschool.finaltask.database.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finaltask.database.provider.DataSourceProvider;
import ru.croc.javaschool.finaltask.database.repository.impl.HospitalizationsReportRepository;
import ru.croc.javaschool.finaltask.model.entity.HospitalizationsReport;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * The class for testing hospitalization repository.
 */
public class HospitalizationsReportRepositoryTest {
    private final DataSourceProvider dataSourceProvider = new DataSourceProvider();
    private final HospitalizationsReportRepository repository =
            new HospitalizationsReportRepository(dataSourceProvider.getDataSource());

    private final HospitalizationsReport expectedReport = new HospitalizationsReport(
            LocalDate.now(),
            123,
            321
    );

    public HospitalizationsReportRepositoryTest() throws SQLException {
    }

    @Test
    public void initTableTest() throws SQLException {
        repository.initTable();
    }

    @Test
    public void findTest() throws SQLException {
        repository.create(expectedReport);
        var actualrep = repository.findByDate(expectedReport.getDate());
        Assertions.assertEquals(expectedReport, actualrep);
        /*
         * I don't use @AfterEach to delete created report because deleting prints some logs, and it is strange
         * when test passes but in console you can see red error log because deleting test already deleted report
         * but @AfterEach tries to delete it ones more time.
         */
        repository.deleteByDate(expectedReport.getDate());
    }

    @Test
    public void createTest() throws SQLException {
        var actualrep = repository.create(expectedReport);
        Assertions.assertEquals(expectedReport, actualrep);
        repository.deleteByDate(expectedReport.getDate());
    }

    @Test
    public void deleteTest() throws SQLException {
        repository.create(expectedReport);
        boolean result = repository.deleteByDate(expectedReport.getDate());
        Assertions.assertTrue(result);
    }
}
