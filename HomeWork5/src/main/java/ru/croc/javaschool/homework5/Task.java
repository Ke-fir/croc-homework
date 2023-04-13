package ru.croc.javaschool.homework5;

import java.text.MessageFormat;

/**
 * Work task.
 */
public class Task implements Comparable<Task> {
    /**
     * Task code.
     */
    private int code;

    /**
     * Task name.
     */
    private String name;

    /**
     * Task description.
     */
    private String description;

    /**
     * Task executor.
     */
    private String executor;

    /**
     * Task status.
     */
    private String status;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getExecutor() {
        return executor;
    }

    public String getStatus() {
        return status;
    }

    public Task(int code, String name, String description, String executor, String status) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.executor = executor;
        this.status = status;
    }

    @Override
    public int compareTo(Task o) {
        if (this.code > o.code) {
            return 1;
        } else {
            if (this.code < o.code) {
                return -1;
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        var task = (Task) obj;
        var equality = true;
        equality &= this.code == task.code &&
                this.name.equals(task.name) &&
                this.description.equals(task.description) &&
                this.executor.equals(task.executor) &&
                this.status.equals(task.status);
        return equality;
    }

    /**
     * Makes from this task the clone of another task.
     *
     * @param task task that should be cloned
     */
    public void cloneOf(Task task) {
        this.code = task.code;
        this.name = task.name;
        this.description = task.description;
        this.executor = task.executor;
        this.status = task.status;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0} \"{1}\" \"{2}\" \"{3}\" \"{4}\"", code, name, description, executor, status);
    }
}
