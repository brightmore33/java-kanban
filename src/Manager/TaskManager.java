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

    // обновление задачи
    public Task updateTask(Task updatedTask) {
        int updatedTaskId = updatedTask.getId();
        if (tasks.containsKey(updatedTaskId)) {
            tasks.put(updatedTaskId,updatedTask);
            System.out.println("Задача обновлена");
            return updatedTask;
        } else {
            System.out.println("Такой задачи нет");
            return null;
        }
    }

    // получение задачи по идетификатору
    public Task getTaskById(int taskId) {
        if (tasks.containsKey(taskId)) {
            return tasks.get(taskId);
        }
        System.out.println("Такой задачи нет");
        return null;
    }

    // получить список всех задач
    public ArrayList<Task> getAllTasks() {
        if (!tasks.isEmpty()) {
            return new ArrayList<>(tasks.values());
        }
        System.out.println("Список задас пуст");
        return null;
    }

    // удаление Таски по идентификатору
    public void removeTaskById(int taskId) {
        if (tasks.containsKey(taskId)) {
            tasks.remove(taskId);
        } else {
            System.out.println("Такой задачи нет");
        }
    }

    // ---=== Эпики и их Методы===---

    public Epic addNewEpic(Epic newEpic) {
        if (newEpic == null) {
            System.out.println("Прверка на пустоту не пройдена");
            return null;
        } else {
            int newEpicID = getNewId();
            newEpic.setId(newEpicID);
            epics.put(newEpicID, newEpic);
            System.out.println("Задача Эпик добавлена");
            return newEpic;
        }
    }
}
