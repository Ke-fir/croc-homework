package ru.croc.javaschool.finaltask.model.input;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import ru.croc.javaschool.finaltask.model.output.HospitalizationsReport;
import ru.croc.javaschool.finaltask.model.output.InfectionsReport;
import ru.croc.javaschool.finaltask.service.objectadapter.LocalDateAdapter;

import java.time.LocalDate;

/**
 * The class responsible for keeping info from daily report xml.
 * Contains properties date, hospitalizationCasesCount, dischargingCasesCount, infectionCasesCount, recoveryCasesCount.
 */
@XmlRootElement(name = "report")
@XmlAccessorType(XmlAccessType.FIELD)
public class DailyReport {
    @XmlAttribute(name = "date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
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

    @Override
    public boolean equals(Object obj) {
        DailyReport rep = (DailyReport) obj;
        return this.date.equals(rep.date) &&
                this.dischargingCasesCount == rep.dischargingCasesCount &&
                this.hospitalizationCasesCount == rep.hospitalizationCasesCount &&
                this.recoveryCasesCount == rep.recoveryCasesCount &&
                this.infectionCasesCount == rep.infectionCasesCount;
    }

    //region GetSet
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
    //endregion
}
