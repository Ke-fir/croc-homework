package ru.croc.javaschool.finaltask.model.entity;

import java.time.LocalDate;

/**
 * The class responsible for daily statistics about count of hospitalized and discharged people.
 * Contains properties date, hospitalizationsCount, dischargedCount.
 */
public class HospitalizationsReport {
    /**
     * The date of report.
     */
    private LocalDate date;

    /**
     * The number of hospitalizations on that date.
     */
    private int hospitalizationsCount;

    /**
     * The number of discharged patients on that date.
     */
    private int dischargedCount;

    /**
     * Creates a HospitalizationsReport instance.
     *
     * @param date                  The date of report
     * @param hospitalizationsCount The number of hospitalizations on that date
     * @param dischargedCount       The number of discharged patients on that date
     */
    public HospitalizationsReport(LocalDate date, int hospitalizationsCount, int dischargedCount) {
        this.date = date;
        this.hospitalizationsCount = hospitalizationsCount;
        this.dischargedCount = dischargedCount;
    }

    /**
     * Default constructor.
     */
    public HospitalizationsReport() {
    }

    // Is override for right work of Assertions.areEqual()
    @Override
    public boolean equals(Object obj) {
        var rep = (HospitalizationsReport)obj;
        return this.date.equals(rep.date) &&
                this.hospitalizationsCount == rep.hospitalizationsCount &&
                this.dischargedCount == rep.dischargedCount;
    }

    //region GetSet
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHospitalizationsCount() {
        return hospitalizationsCount;
    }

    public void setHospitalizationsCount(int hospitalizationsCount) {
        this.hospitalizationsCount = hospitalizationsCount;
    }

    public int getDischargedCount() {
        return dischargedCount;
    }

    public void setDischargedCount(int dischargedCount) {
        this.dischargedCount = dischargedCount;
    }
    //endregion
}
