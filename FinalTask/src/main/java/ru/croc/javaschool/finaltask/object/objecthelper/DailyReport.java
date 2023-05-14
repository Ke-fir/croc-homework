package ru.croc.javaschool.finaltask.object.objecthelper;

import jakarta.xml.bind.annotation.*;
import ru.croc.javaschool.finaltask.object.HospitalizationsReport;
import ru.croc.javaschool.finaltask.object.InfectionsReport;

import java.time.LocalDate;

/**
 * The class responsible for keeping info from daily report xml.
 * Contains properties date, hospitalizationCasesCount, dischargingCasesCount, infectionCasesCount, recoveryCasesCount.
 */
@XmlRootElement(name = "report")
@XmlAccessorType(XmlAccessType.FIELD)
public class DailyReport {
    @XmlAttribute(name = "date")
    private LocalDate date;
    @XmlElement(name = "infectionCasesCount")
    private int infectionCasesCount;
    @XmlElement(name = "recoveryCasesCount")
    private int recoveryCasesCount;
    @XmlElement(name = "hospitalizationCasesCount")
    private int hospitalizationCasesCount;
    @XmlElement(name = "dischargingCasesCount")
    private int dischargingCasesCount;

    /**
     * Creates a DailyReport.
     *
     * @param date                      The date for which the report is created
     * @param infectionCasesCount       Number of new infections on that date
     * @param recoveryCasesCount        Number of recoveries on that date
     * @param hospitalizationCasesCount Number of hospitalizations on that date
     * @param dischargingCasesCount     Number of patients discharged from hospitals on that date
     */
    public DailyReport(LocalDate date, int infectionCasesCount, int recoveryCasesCount, int hospitalizationCasesCount,
                       int dischargingCasesCount) {
        this.date = date;
        this.infectionCasesCount = infectionCasesCount;
        this.recoveryCasesCount = recoveryCasesCount;
        this.hospitalizationCasesCount = hospitalizationCasesCount;
        this.dischargingCasesCount = dischargingCasesCount;
    }

    /**
     * Default constructor
     */
    public DailyReport() {
    }

    public HospitalizationsReport toHospitalizationsReport() {
        return new HospitalizationsReport(date, hospitalizationCasesCount, dischargingCasesCount);
    }

    public InfectionsReport toInfectionsReport() {
        return new InfectionsReport(date, infectionCasesCount, recoveryCasesCount);
    }

    public LocalDate getDate() {
        return date;
    }

    public int getInfectionCasesCount() {
        return infectionCasesCount;
    }

    public int getRecoveryCasesCount() {
        return recoveryCasesCount;
    }

    public int getHospitalizationCasesCount() {
        return hospitalizationCasesCount;
    }

    public int getDischargingCasesCount() {
        return dischargingCasesCount;
    }
}
