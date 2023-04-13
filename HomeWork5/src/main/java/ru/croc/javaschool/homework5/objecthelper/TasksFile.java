package ru.croc.javaschool.homework5.objecthelper;


import ru.croc.javaschool.homework5.object.Task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for writing tasks to file and reading data from it.
 */
public class TasksFile {
    private final static String path = "Tasks.txt";

    /**
     * Writes tasks to file. Always rewrites file.
     *
     * @param tasks list of tasks
     */
    public static void writeToFile(ArrayList<Task> tasks) {
        var text = "";
        for (var task : tasks) {
            text += task.toString() + "\n";
        }

        try (FileWriter writer = new FileWriter(path, false)) {
            writer.write(text);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    /**
     * Reads file and transform lines to tasks.
     *
     * @return list of tasks
     */
    public static ArrayList<Task> getTasksFromFile() {
        var tasks = new ArrayList<Task>();
        var text = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = reader.readLine(); //firstly read 1 line
            while (line != null) {
                text.add(line);
                line = reader.readLine();
            }
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }

        for (var line : text){
            String[] ln = line.split(" \"");
            tasks.add(new Task(Integer.parseInt(ln[0]), ln[1].replace("\"", ""),
                    ln[2].replace("\"", ""), ln[3].replace("\"", ""),
                    ln[4].replace("\"", "")));
        }
        return tasks;
    }
}
