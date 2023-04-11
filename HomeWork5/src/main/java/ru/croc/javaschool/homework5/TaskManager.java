package ru.croc.javaschool.homework5;

import java.util.ArrayList;

/**
 * Task manager class.
 */
public class TaskManager {
    /**
     * List of tasks.
     */
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds new task in task list.
     *
     * @param task some task that wasn't in list
     */
    public void addTask(Task task) {
        if (task != null && tasks.stream().noneMatch(x -> x.getCode() == task.getCode())) {
            tasks.add(task);
        }
    }

    /**
     * Removes task from task list with its code.
     *
     * @param code code of deleting task
     */
    public void removeTask(int code) {
        var deletingTask = tasks.stream()
                .filter(x -> x.getCode() == code)
                .findFirst()
                .get();
        tasks.remove(deletingTask);
    }

    /**
     * Edits task in task list.
     *
     * @param editedTask updated task
     */
    public void editTask(Task editedTask) {
        for (var task : tasks) {
            if (task.getCode() == editedTask.getCode()) {
                task.cloneOf(editedTask);
                return;
            }
        }

    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Constructor of task manager with list of tasks.
     *
     * @param tasks list of tasks
     */
    public TaskManager(ArrayList<Task> tasks) {
        for (var task : tasks) {
            addTask(task);
        }

    }

    /**
     * Empty constructor of task manager.
     */
    public TaskManager() {
    }
}
