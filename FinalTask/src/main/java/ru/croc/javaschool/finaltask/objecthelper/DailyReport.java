package ru.croc.javaschool.finaltask.objecthelper;

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

    public HospitalizationsReport toHospitalizationsReport() {
        return new HospitalizationsReport(date, hospitalizationCasesCount, dischargingCasesCount);
    }

    public InfectionsReport toInfectionsReport() {
        return new InfectionsReport(date, infectionCasesCount, recoveryCasesCount);
    }
}
