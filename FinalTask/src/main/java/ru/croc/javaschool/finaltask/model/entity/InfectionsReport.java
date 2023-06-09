package ru.croc.javaschool.finaltask.model.entity;

import java.time.LocalDate;

/**
 * The class responsible for daily statistics about count of infected and recovered people.
 * Contains properties date, infectionCasesCount, recoveryCasesCount.
 */
public class InfectionsReport {

    /**
     * The date of report.
     */
    private LocalDate date;

    /**
     * The number of new infection cases.
     */
    private int infectionCasesCount;

    /**
     * The number of new recovery cases.
     */
    private int recoveryCasesCount;

    /**
     * Creates a InfectionsReport instance.
     *
     * @param date                The date of the report
     * @param infectionCasesCount The number of new infection cases
     * @param recoveryCasesCount  The number of recoveries
     */
    public InfectionsReport(LocalDate date, int infectionCasesCount, int recoveryCasesCount) {
        this.date = date;
        this.infectionCasesCount = infectionCasesCount;
        this.recoveryCasesCount = recoveryCasesCount;
    }

    /**
     * Default constructor.
     */
    /* Is used for serialization */
    public InfectionsReport() {
    }

    /* Is override for right work of Assertions.areEqual() */
    @Override
    public boolean equals(Object obj) {
        var rep = (InfectionsReport) obj;
        return this.date.equals(rep.date) &&
                this.infectionCasesCount == rep.infectionCasesCount &&
                this.recoveryCasesCount == rep.recoveryCasesCount;
    }

    //region GetSet
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getInfectionCasesCount() {
        return infectionCasesCount;
    }

    public void setInfectionCasesCount(int infectionCasesCount) {
        this.infectionCasesCount = infectionCasesCount;
    }

    public int getRecoveryCasesCount() {
        return recoveryCasesCount;
    }

    public void setRecoveryCasesCount(int recoveryCasesCount) {
        this.recoveryCasesCount = recoveryCasesCount;
    }
    //endregion
}
