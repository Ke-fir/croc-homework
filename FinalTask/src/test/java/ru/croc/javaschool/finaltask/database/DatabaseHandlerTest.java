package ru.croc.javaschool.finaltask.database;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finaltask.object.InfectionsReport;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Handler;

/**
 * Test class for database handler.
 */
public class DatabaseHandlerTest {
    DatabaseHandler handler = new DatabaseHandler();

    @Test
    public void checkTablesExistTest() {
        handler.checkTablesExist();
    }
    @Test
    public void createTablesTest() {
        handler.createTables();
    }


}
