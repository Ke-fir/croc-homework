package ru.croc.javaschool.finaltask.database.repository;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Interface of database repository for reports.
 */
public interface ReportRepository<T> {
    /**
     * Initialize table.
     *
     * @return True if work was done. False if sth went wrong.
     */
    boolean initTable() throws SQLException;

    /**
     * Creates record in table.
     *
     * @return True if work was done. False if sth went wrong.
     */
    boolean create(T report);

    /**
     * Finds record in table due to date value.
     *
     * @param date Date of report.
     * @return Report.
     */
    T find(LocalDate date);

    /**
     * Deletes report with suitable date from table.
     *
     * @param date Date of report.
     * @return True if work was done. False if sth went wrong.
     */
    boolean delete(LocalDate date);
}
