package ru.croc.javaschool.finaltask.database.provider;

import org.apache.derby.jdbc.EmbeddedDataSource;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * The class responsible for providing datasource.
 */
public class DataSourceProvider {

    private EmbeddedDataSource dataSource;

    /**
     * Creates datasource if it doesn't exist and gives it back.
     *
     * @return Data source of covidStatistics database.
     */
    public DataSource getDataSource() {
        if (Objects.isNull(dataSource)) {
            dataSource = new EmbeddedDataSource();
            dataSource.setDatabaseName("covidStatistics");
            dataSource.setUser("Root");
            dataSource.setUser("tooR");
            dataSource.setCreateDatabase("create");
        }
        return dataSource;
    }
}
