package ru.croc.javaschool.homework5.objecthelper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.homework5.object.Task;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Test class for task manager.
 */
public class TaskManagerTest {
    /**
     * List of tasks for test.
     */
    private ArrayList<Task> expectedTasks;

    private TaskManager taskManager;

    /**
     * Cleans expected tasks list.
     */
    @BeforeEach
    public void clean() {
        expectedTasks = new ArrayList<>();
        taskManager = new TaskManager();
    }

    /**
     * Tests of adding in task manager.
     */
    @Test
    public void addTaskTest() {
        var t1 = new Task(17, "T1", "Task number 1", "Kirill", "In progress");
        var t2 = new Task(0, "T2", "Task number 2", "Sasha", "Done");
        var t3 = new Task(98, "T3", "Task number 3", "Alex", "Failed");

        expectedTasks.addAll(new ArrayList<>(Arrays.asList(t1, t2, t3)));

        taskManager.addTask(t1);
        taskManager.addTask(t2);
        taskManager.addTask(t3);

        Assertions.assertArrayEquals(expectedTasks.toArray(), taskManager.getTasks().toArray());
    }

    /**
     * Test of removing task from task list.
     */
    @Test
    public void removeTaskTest() {
        var t1 = new Task(17, "T1", "Task number 1", "Kirill", "In progress");
        var t2 = new Task(0, "T2", "Task number 2", "Sasha", "Done");
        var t3 = new Task(98, "T3", "Task number 3", "Alex", "Failed");

        expectedTasks.addAll(new ArrayList<>(Arrays.asList(t1, t3)));

        taskManager = new TaskManager(new ArrayList<>(Arrays.asList(t1, t2, t3)));
        taskManager.removeTask(0);

        Assertions.assertArrayEquals(expectedTasks.toArray(), taskManager.getTasks().toArray());
    }

    /**
     * Tests editing of the task in list.
     */
    @Test
    public void editTaskTest() {
        var t1 = new Task(17, "T1", "Task number 1", "Kirill", "In progress");
        var t2 = new Task(0, "T2", "Task number 2", "Sasha", "Done");
        var t3 = new Task(98, "T3", "Task number 3", "Alex", "Failed");
        var editedT2 = new Task(0, "Updated T2", "Task number 2", "Sasha", "In progress");

        expectedTasks.addAll(new ArrayList<>(Arrays.asList(t1, editedT2, t3)));

        taskManager = new TaskManager(new ArrayList<>(Arrays.asList(t1, t2, t3)));
        taskManager.editTask(editedT2);

        Assertions.assertArrayEquals(expectedTasks.toArray(), taskManager.getTasks().toArray());
    }
}
