package manager;

import emuns.TaskState;
import models.Epic;
import models.Subtask;
import models.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {

    private int idCounter = 0;
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private HistoryManager historyManager;

    public InMemoryTaskManager() {
        this.historyManager = Managers.getDefaultHistory();
    }

    @Override
    public int getNewId() {
        return idCounter++;
    }

    // ---=== Таски и их Методы===---

    // новая задача
    @Override
    public Task addNewTask(Task newTask) {
        int newTaskId = getNewId();
        newTask.setId(newTaskId);
        tasks.put(newTask.getId(), newTask);
        System.out.println("Добавлена новая задача");
        return newTask;
    }

    // обновление задачи
    @Override
    public Task updateTask(Task updatedTask) {
        int updatedTaskId = updatedTask.getId();
        if (tasks.containsKey(updatedTaskId)) {
            tasks.put(updatedTaskId, updatedTask);
            System.out.println("Задача обновлена");
            return updatedTask;
        } else {
            System.out.println("Такой задачи нет");
            return null;
        }
    }

    // получение задачи по идетификатору
    @Override
    public Task getTaskById(int taskId) {
        if (tasks.containsKey(taskId)) {
            historyManager.add(tasks.get(taskId));
            return tasks.get(taskId);
        }
        System.out.println("Такой задачи нет");
        return null;
    }

    // получить список всех задач
    @Override
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    // удаление Таски по идентификатору
    @Override
    public void removeTaskById(int taskId) {
        if (tasks.containsKey(taskId)) {
            tasks.remove(taskId);
            historyManager.remove(taskId);
        } else {
            System.out.println("Такой задачи нет");
        }
    }


    @Override
    public void removeAllTasks() {
        tasks.clear();
    }

    // ---=== Эпики и их Методы===---

    @Override
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

    @Override
    public Epic getEpicById(int epicId) {
        if (epics.containsKey(epicId)) {
            historyManager.add(epics.get(epicId));
            return epics.get(epicId);
        }
        System.out.println("Такого Эпика нет.");
        return null;
    }

    @Override
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
        if (counterNew == updatedEpic.getSubTasksIDs().size()) {
            updatedEpic.setState(TaskState.NEW);
        } else if (counterDone == updatedEpic.getSubTasksIDs().size()) {
            updatedEpic.setState(TaskState.DONE);
        } else {
            updatedEpic.setState(TaskState.IN_PROGRESS);
        }
    }

    @Override
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public void removeAllEpics() {
        subtasks.clear();
        epics.clear();
    }

    @Override
    public void removeEpicByID(int epicID) {
        if (epics.containsKey(epicID)) {
            Epic removedEpic = epics.get(epicID);
            for (Integer subtaskID : removedEpic.getSubTasksIDs()) {
                subtasks.remove(subtaskID);
            }
            epics.remove(epicID);
        } else {
            System.out.println("Такого Эпика нет. Удлить нельзя.");
        }
    }

    // ---=== Подзадачи и Методы===---

    @Override
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

    @Override
    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public Subtask getSubtaskByID(int subtaskID) {
        if (subtasks.containsKey(subtaskID)) {
            historyManager.add(subtasks.get(subtaskID));
            return subtasks.get(subtaskID);
        }
        System.out.println("Такой подзадачи нет.");
        return null;
    }

    @Override
    public void removeSubtaskByID(int subtaskID) {
        Subtask subtask = subtasks.remove(subtaskID);
        if (subtask == null) {
            return;
        } else {
            Epic epic = epics.get(subtask.getEpicID());
            epic.deleteSubTaskID(subtaskID);
            updateEpicState(epic.getId());
        }
    }

    @Override
    public void removeAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.clearSubTasksIDs();
            updateEpicState(epic.getId());
        }
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        Subtask updatedSubtask = subtasks.get(subtask.getId());
        if (updatedSubtask == null) {
            System.out.println("Подзадачи нет");
            return;
        }

        Epic epic = epics.get(subtask.getEpicID());
        if (epic == null) {
            System.out.println("Задачи Эпик нет");
            return;
        }

        if (!updatedSubtask.getEpicID().equals(subtask.getEpicID())) {
            System.out.println("Нет возможности обновить подзадачу");
            return;
        }

        subtasks.put(subtask.getId(), subtask);
        updateEpicState(epic.getId());
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }
}