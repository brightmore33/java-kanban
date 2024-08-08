package Manager;

import Enums.TaskState;
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

    public Epic updateEpic(Epic updatedEpic) {
        int updatedEpicId = updatedEpic.getId();
        if (epics.containsKey(updatedEpicId)) {
            Epic updatingEpic = epics.get(updatedEpicId);
            updatingEpic.setName(updatedEpic.getName());
            updatingEpic.setDescription(updatedEpic.getDescription());
            epics.put(updatedEpicId, updatedEpic);
            System.out.println("Задача Эпик обновлена");
            return updatedEpic;
        } else {
            System.out.println("Такой Эпик задачи нет");
            return null;
        }
    }

    private void updateEpicState(int epicId) {
        Epic updatedEpic = epics.get(epicId);

        int counterNew = 0;
        int counterDone = 0;

        if (updatedEpic.getSubTasksIDs().isEmpty()) {
            updatedEpic.setState(TaskState.NEW);
            return;
        }
        for (int id : updatedEpic.getSubTasksIDs()) {
            if (subtasks.get(id).getState() == TaskState.NEW) {
                counterNew++;
            } else if (subtasks.get(id).getState() == TaskState.DONE) {
                counterDone++;
            }
        }
        if ((counterNew == updatedEpic.getSubTasksIDs().size())) {
            updatedEpic.setState(TaskState.NEW);
        } else if (counterDone == updatedEpic.getSubTasksIDs().size()) {
            updatedEpic.setState(TaskState.DONE);
        } else {
            updatedEpic.setState(TaskState.IN_PROGRESS);
        }
    }

    public ArrayList<Epic> getAllEpics() {
        if (!epics.isEmpty()) {
            return new ArrayList<>(epics.values());
        } else {
            System.out.println("Эпиков нет");
            return null;
        }
    }

    // ---=== Подзадачи и Методы===---

    public Subtask addNewSubtask(Subtask newSubtask) {
        int epicID = newSubtask.getEpicID();
        if (!epics.containsKey(epicID)) {
            System.out.println("Такой Эпик задачи нет");
            return null;
        } else {
            int newSubtaskID = getNewId();

            newSubtask.setId(newSubtaskID);
            subtasks.put(newSubtaskID, newSubtask);
            Epic baseEpic = epics.get(epicID);
            baseEpic.addSubTaskID(newSubtaskID);
            updateEpicState(epicID);

            System.out.println("Добавлена подзадача");
            return newSubtask;
        }
    }

}