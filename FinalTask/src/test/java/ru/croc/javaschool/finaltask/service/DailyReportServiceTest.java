package ru.croc.javaschool.finaltask.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finaltask.database.provider.DataSourceProvider;
import ru.croc.javaschool.finaltask.database.repository.HospitalizationsReportRepository;
import ru.croc.javaschool.finaltask.database.repository.InfectionsReportRepository;
import ru.croc.javaschool.finaltask.model.input.DailyReport;
import ru.croc.javaschool.finaltask.model.output.HospitalizationsReport;
import ru.croc.javaschool.finaltask.model.output.InfectionsReport;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Test class for daily report service.
 */
public class DailyReportServiceTest {
    private final DataSourceProvider dataSourceProvider = new DataSourceProvider();
    private final HospitalizationsReportRepository hRepository =
            new HospitalizationsReportRepository(dataSourceProvider.getDataSource());
    private final InfectionsReportRepository iRepository =
            new InfectionsReportRepository(dataSourceProvider.getDataSource());
    private final DailyReportService ds = new DailyReportService(hRepository, iRepository);


    public DailyReportServiceTest() throws SQLException {
    }

    @Test
    public void createNewReport() {
         var report = new DailyReport(
                 LocalDate.now(),
                 1776,
                 6771,
                 355,
                 553
         );
         var expectedHospitalizationReport = new HospitalizationsReport(
                 report.getDate(),
                 report.getHospitalizationCasesCount(),
                 report.getDischargingCasesCount()
         );
         var expectedInfectionReport = new InfectionsReport(
                 report.getDate(),
                 report.getInfectionCasesCount(),
                 report.getRecoveryCasesCount()
         );

         ds.createNewReport(report);
         var actualHospitalizationReport = hRepository.find(report.getDate());
        hRepository.delete(report.getDate());
         var actualInfectionReport = iRepository.find(report.getDate());
         iRepository.delete(report.getDate());
        Assertions.assertEquals(expectedHospitalizationReport, actualHospitalizationReport);
        Assertions.assertEquals(expectedInfectionReport, actualInfectionReport);
     }

     @Test
    public void createAllReportsTest() {
         var report1 = new DailyReport(
                 LocalDate.of(2023, Month.MAY, 17),
                 1776,
                 6771,
                 355,
                 553
         );
         var report2 = new DailyReport(
                 LocalDate.of(2023, Month.MAY, 18),
                 177,
                 677,
                 35,
                 55
         );
         var report3 = new DailyReport(
                 LocalDate.of(2023, Month.MAY, 19),
                 17,
                 67,
                 3,
                 5
         );
         var repList = new ArrayList<DailyReport>(Arrays.asList(report1, report2, report3));
         ds.createAllReports(repList);
         for (var rep : repList) {
             Assertions.assertNotNull(iRepository.find(rep.getDate()));
             iRepository.delete(rep.getDate());
             Assertions.assertNotNull(hRepository.find(rep.getDate()));
             hRepository.delete(rep.getDate());
         }
     }
}
