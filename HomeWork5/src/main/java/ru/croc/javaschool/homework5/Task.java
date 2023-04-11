package ru.croc.javaschool.homework5;

/**
 * Work task.
 */
public class Task {
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
}
