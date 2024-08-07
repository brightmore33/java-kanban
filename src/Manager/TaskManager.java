package Manager;

import Models.Epic;
import Models.Subtask;
import Models.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    private int idCounter = 0;
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public int getNewId() {
        return idCounter++;
    }

    // ---=== Таски и их Методы===---

    // новая задача
    public Task addNewTask(Task newTask) {
        int newTaskId = getNewId();
        newTask.setId(newTaskId);
        tasks.put(newTask.getId(), newTask);
        System.out.println("Добавлена новая задача");
        return newTask;
    }

    // получить список всех задач
    public ArrayList<Task> getAllTasks() {
        if (!tasks.isEmpty()) {
            return new ArrayList<>(tasks.values());
        }
        System.out.println("Список задас пуст");
        return null;
    }
}
