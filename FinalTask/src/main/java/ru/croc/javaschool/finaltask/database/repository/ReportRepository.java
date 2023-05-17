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
     * @return Report if it was created successfully. Null if sth went wrong.
     */
    T create(T report);

    /**
     * Finds record in table due to date value.
     *
     * @param date Date of report.
     * @return Report.
     */
    T findByDate(LocalDate date);

    /**
     * Finds list of records that was done in some date range.
     *
     * @param startDate Start date of range
     * @param endDate End date of range
     * @return List of records.
     */
    //List<T> findByDateRange(LocalDate startDate, LocalDate endDate);

    /**
     * Deletes report with suitable date from table.
     *
     * @param date Date of report.
     * @return True if work was done. False if sth went wrong.
     */
    boolean deleteByDate(LocalDate date);
}
