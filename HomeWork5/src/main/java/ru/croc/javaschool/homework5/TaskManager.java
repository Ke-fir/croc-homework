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

    public void editTask(Task task){
            tasks.stream()
                    .filter(x -> x.getCode() == task.getCode())
                    .map(x -> task);
    }
}
