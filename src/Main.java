import Enums.TaskState;
import Manager.TaskManager;
import Models.Epic;
import Models.Subtask;
import Models.Task;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");

        TaskManager taskManager = new TaskManager();

        // накидываем Таски
        Task task1 = taskManager.addNewTask(new Task("Задача 1-я", "Погладить кота, а лучше вещи"));
        Task task2 = taskManager.addNewTask(new Task("Задача 2-я", "Вынести мусор"));
        Task task3 = taskManager.addNewTask(new Task("Задача 3-я", "Замочить таракана"));

        // Добавляем Эпики
        Epic epic1 = taskManager.addNewEpic(new Epic("Задача 1-я - Эпик", "Уборка"));

        // обновляем Таску
        Task newTask = new Task(task1.getId(), "Задача 1-я", "Погладить кота, а лучше вещи", TaskState.IN_PROGRESS);
        System.out.println(taskManager.updateTask(newTask));

        // вытаскиваем Таску по идентификатору
        System.out.println("Получаем задачу по id:\n" + taskManager.getTaskById(2));

        // удаляем задачу
        System.out.println("Удалим задачу:");
        taskManager.removeTaskById(1);

        // вывод всех Тасков
        System.out.println("Список всех задач:\n" + taskManager.getAllTasks());

        // обновляем Эпик задачу
        Epic newEpic = new Epic(epic1.getId(), "Задача 1-я - Эпик", "Уборка", TaskState.DONE);
        System.out.println(taskManager.updateEpic(newEpic));

        // вывод всех Эпиков
        System.out.println("Список всех задач Эпик:\n" + taskManager.getAllEpics());

        // --== ВВОДИМ ПОДЗАДАЧИ ==-

        // Для 1-го Эпика
        Subtask subtask1ForEpic1 = taskManager.addNewSubtask(new Subtask(epic1.getId(), "1-я подзадача к 1-му Эпику",
                "Помыть полы", TaskState.IN_PROGRESS));
        Subtask subtask2ForEpic1 = taskManager.addNewSubtask(new Subtask(epic1.getId(), "2-я подзадача к 1-му Эпику",
                "Протереть пыль", TaskState.NEW));
        Subtask subtask3ForEpic1 = taskManager.addNewSubtask(new Subtask(epic1.getId(), "3-я подзадача к 1-му Эпику",
                "Вытряхнуть половик", TaskState.NEW));
    }
}