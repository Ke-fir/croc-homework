package ru.croc.javaschool.finaltask.service;

import ru.croc.javaschool.finaltask.database.repository.impl.HospitalizationsReportRepository;
import ru.croc.javaschool.finaltask.database.repository.impl.InfectionsReportRepository;
import ru.croc.javaschool.finaltask.model.dto.DailyReport;

import java.util.List;
import java.util.Objects;

/**
 * The class responsible for daily report service.
 */
public class DailyReportService {

    private final HospitalizationsReportRepository hospitalRepository;

    private final InfectionsReportRepository infectionRepository;

    public DailyReportService(HospitalizationsReportRepository hospitalRepository, InfectionsReportRepository infectionRepository) {
        this.hospitalRepository = hospitalRepository;
        this.infectionRepository = infectionRepository;
    }

    /**
     * Creates new records to both of tables from daily report.
     *
     * @param report Daily report instance.
     * @return True if done successfully. False if sth went wrong.
     */
    public boolean createNewReport(DailyReport report) {
        var hospitalReport = report.toHospitalizationsReport();
        var infectionReport = report.toInfectionsReport();

        System.out.println("Запись отчёта о госпитализации в таблицу " + hospitalRepository.TABLE_NAME);
        if (!Objects.isNull(hospitalRepository.create(hospitalReport))) {
            System.out.println("Запись отчёта о заболеваниях в таблицу " + infectionRepository.TABLE_NAME);
            if (!Objects.isNull(infectionRepository.create(infectionReport))) {
                System.out.println("Записи проведены успешно");
                return true;
            }
        }
        System.err.println("Произошла ошиба при записи ежедневного отчета в базу данных");
        return false;
    }

    /**
     * Creates new records of reports from list in both tables.
     *
     * @param reports List of daily reports
     * @return True if all work was done. False if even 1 of reports from list wasn't added to database.
     */
    public boolean createAllReports(List<DailyReport> reports) {
        boolean result = true;
        for (var rep : reports) {
            result &= createNewReport(rep);
        }
        return result;
    }
}
