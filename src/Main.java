import emuns.TaskState;
import manager.InMemoryTaskManager;
import manager.Managers;
import manager.TaskManager;
import models.Epic;
import models.Subtask;
import models.Task;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");

        TaskManager taskManager = Managers.getDefault();

        // накидываем Таски
        Task task1 = taskManager.addNewTask(new Task("Задача 1-я", "Погладить кота, а лучше вещи"));
        Task task2 = taskManager.addNewTask(new Task("Задача 2-я", "Вынести мусор"));
        Task task3 = taskManager.addNewTask(new Task("Задача 3-я", "Замочить таракана"));

        // Добавляем Эпики
        Epic epic1 = taskManager.addNewEpic(new Epic("Задача 1-я - Эпик", "Уборка"));
        Epic epic2 = taskManager.addNewEpic(new Epic("Задача 2-я - Эпик", "Собакен"));

        // --== BEGIN: ВВОДИМ ПОДЗАДАЧИ ==-

        // Для 1-го Эпика
        Subtask subtask1ForEpic1 = taskManager.addNewSubtask(new Subtask(epic1.getId(), "1-я подзадача к 1-му Эпику",
                "Помыть полы", TaskState.IN_PROGRESS));
        Subtask subtask2ForEpic1 = taskManager.addNewSubtask(new Subtask(epic1.getId(), "2-я подзадача к 1-му Эпику",
                "Протереть пыль", TaskState.NEW));
        Subtask subtask3ForEpic1 = taskManager.addNewSubtask(new Subtask(epic1.getId(), "3-я подзадача к 1-му Эпику",
                "Вытряхнуть половик", TaskState.NEW));

        // Для 2-го Эпика
        Subtask subtask1ForEpic2 = taskManager.addNewSubtask(new Subtask(epic1.getId(), "1-я подзадача к 2-му Эпику",
                "Погулять с Умой", TaskState.DONE));
        Subtask subtask2ForEpic2 = taskManager.addNewSubtask(new Subtask(epic1.getId(), "2-я подзадача к 2-му Эпику",
                "Помыть собаню после прогулки", TaskState.IN_PROGRESS));
        Subtask subtask3ForEpic2 = taskManager.addNewSubtask(new Subtask(epic1.getId(), "3-я подзадача к 2-му Эпику",
                "Накормить собакена", TaskState.NEW));

        // --== END: ВВЕЛИ ПОДЗАДАЧИ ==-

        taskManager.getTaskById(task1.getId());

        printHistory(taskManager);
    }

    public static void printHistory(TaskManager manager) {
        System.out.println("История");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}