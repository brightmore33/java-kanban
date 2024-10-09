package manager;

import models.Epic;
import models.Subtask;
import models.Task;

import java.util.ArrayList;
import java.util.List;

public interface        TaskManager {
    int getNewId();

    // новая задача
    Task addNewTask(Task newTask);

    // обновление задачи
    Task updateTask(Task updatedTask);

    // получение задачи по идетификатору
    Task getTaskById(int taskId);

    // получить список всех задач
    ArrayList<Task> getAllTasks();

    // удаление Таски по идентификатору
    void removeTaskById(int taskId);

    void removeAllTasks();

    Epic addNewEpic(Epic newEpic);

    Epic updateEpic(Epic updatedEpic);

    Epic getEpicById(int id);

    ArrayList<Epic> getAllEpics();

    void removeAllEpics();

    void removeEpicByID(int epicID);

    Subtask addNewSubtask(Subtask newSubtask);

    ArrayList<Subtask> getAllSubtasks();

    Subtask getSubtaskByID(int subtaskID);

    void removeSubtaskByID(int subtaskID);

    void removeAllSubtasks();

    void updateSubtask(Subtask subtask);

    List<Task> getHistory();
}
