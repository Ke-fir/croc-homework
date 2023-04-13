package ru.croc.javaschool.homework5.console;

import ru.croc.javaschool.homework5.Graphics;
import ru.croc.javaschool.homework5.Task;
import ru.croc.javaschool.homework5.TaskManager;
import ru.croc.javaschool.homework5.TasksFile;

import java.util.Scanner;

/**
 * Console api for OOO"Roga & Copyta"
 */
public class ConsoleApi {
    private ru.croc.javaschool.homework5.Graphics graphics;
    private TaskManager taskManager;
    private Scanner scanner;

    /**
     * Runs application.
     */
    public void run() {
        taskManager = new TaskManager(TasksFile.getTasksFromFile());
        while (true) {
            graphics = new Graphics(taskManager.getTasks());
            scanner = new Scanner(System.in);
            switch (scanner.nextInt()) {
                case (1):
                    runAddMenu();
                    break;
                case (2):
                    runRemoveMenu();
                    break;
                case (3):
                    runEditMenu();
                    break;
                case (4):
                    TasksFile.writeToFile(taskManager.getTasks());
            }
        }
    }

    private void runAddMenu() {
        scanner = new Scanner(System.in);
        System.out.println("~~~NEW TASK~~~");
        System.out.print("Code: ");
        var code = Integer.parseInt(scanner.nextLine());
        System.out.print("Name: ");
        var name = scanner.nextLine();
        System.out.print("Description: ");
        var description = scanner.nextLine();
        System.out.print("Executor: ");
        var executor = scanner.nextLine();
        System.out.print("Status: ");
        var status = scanner.nextLine();
        taskManager.addTask(new Task(code, name, description, executor, status));
        graphics.clear();
    }

    private void runRemoveMenu(){
        scanner = new Scanner(System.in);
        System.out.println("~~~REMOVE TASK~~~");
        System.out.print("Code of existing task that should be removed: ");
        var code = Integer.parseInt(scanner.nextLine());
        taskManager.removeTask(code);
        graphics.clear();
    }

    private void runEditMenu(){
        scanner = new Scanner(System.in);
        System.out.println("~~~EDIT TASK~~~");
        System.out.print("Code of existing task: ");
        var code = Integer.parseInt(scanner.nextLine());
        System.out.print("New name: ");
        var name = scanner.nextLine();
        System.out.print("New description: ");
        var description = scanner.nextLine();
        System.out.print("New executor: ");
        var executor = scanner.nextLine();
        System.out.print("New status: ");
        var status = scanner.nextLine();
        taskManager.editTask(new Task(code, name, description, executor, status));
        graphics.clear();
    }
}
