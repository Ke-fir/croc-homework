package ru.croc.javaschool.homework5.console;

import ru.croc.javaschool.homework5.Task;

import java.util.ArrayList;

/**
 * Class for console graphics
 */
public class Graphics {
    /**
     * List of tasks.
     */
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Prints header of tasks table.
     */
    private void printHead() {
        var head = "--------------------------------------------------------\n" +
                "TASKS\n" +
                "--------------------------------------------------------\n" +
                " №  |  code  |   name  |   description   |  executor  |  status\n" +
                "--------------------------------------------------------";
        System.out.println(head);
    }

    /**
     * Prints part with tasks formatted to ugly table.
     */
    private void printTaskTable() {
        int n = 0;
        for (var task : tasks) {
            n++;
            System.out.println(n + "  |  " + task.toString().replace(" \"", "  |  \""));
        }
    }

    /**
     * Prints lower part with info about menu commands.
     */
    private void printMenuInfo() {
        var menuInfo = "\n--------------------------------------------------------\n" +
                "To use any command write its number without dot.\n" +
                "--------------------------------------------------------\n" +
                "1. Add new task;\n" +
                "2. Remove task;\n" +
                "3. Edit task; \n" +
                "4. Save to file.\n" +
                "--------------------------------------------------------";
        System.out.println(menuInfo);
    }

    /**
     * "Clears" console.
     */
    public void clear() {
        var emptyLines = "";
        for (int i = 0; i < 50; i++) {
            emptyLines += "\n";
        }
        System.out.println(emptyLines);
    }

    /**
     * Prints main info.
     */
    public void printInfo(){
        printHead();
        printTaskTable();
        printMenuInfo();
    }

    public Graphics(ArrayList<Task> tasks){
        this.tasks.addAll(tasks);
    }
}
