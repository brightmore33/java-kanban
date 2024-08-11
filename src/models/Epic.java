package models;

import emuns.TaskState;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subTasksIDs = new ArrayList<>();

    // конструктов Эпика
    public Epic(String name, String description) {
        super(name, description, TaskState.NEW);
    }

    public Epic(Integer id, String name, String description, TaskState state) {
        super(id, name, description, state);
    }

    // --== Методы Эпиков ==--

    // получаем идентификаторы подзадач
    public ArrayList<Integer> getSubTasksIDs() {
        return new ArrayList<>(subTasksIDs);
    }

    // добавление идентификатора подзадачи
    public void addSubTaskID(Integer idSubTask) {
        subTasksIDs.add(idSubTask);
    }

    // удаление подзадачи по идентификатору
    public void deleteSubTaskID(Integer idSubTask) {
        subTasksIDs.remove(idSubTask);
    }

    // очистка
    public void clearSubTasksIDs() {
        subTasksIDs.clear();
    }
}
