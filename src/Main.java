import emuns.TaskState;
import manager.InMemoryTaskManager;
import models.Epic;
import models.Subtask;
import models.Task;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");

        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

        // накидываем Таски
        Task task1 = inMemoryTaskManager.addNewTask(new Task("Задача 1-я", "Погладить кота, а лучше вещи"));
        Task task2 = inMemoryTaskManager.addNewTask(new Task("Задача 2-я", "Вынести мусор"));
        Task task3 = inMemoryTaskManager.addNewTask(new Task("Задача 3-я", "Замочить таракана"));

        // Добавляем Эпики
        Epic epic1 = inMemoryTaskManager.addNewEpic(new Epic("Задача 1-я - Эпик", "Уборка"));
        Epic epic2 = inMemoryTaskManager.addNewEpic(new Epic("Задача 2-я - Эпик", "Собакен"));

        // обновляем Таску
        Task newTask = new Task(task1.getId(), "Задача 1-я", "Погладить кота, а лучше вещи", TaskState.IN_PROGRESS);
        System.out.println(inMemoryTaskManager.updateTask(newTask));

        // вытаскиваем Таску по идентификатору
        System.out.println("Получаем задачу по id:\n" + inMemoryTaskManager.getTaskById(2));

        // удаляем задачу
        System.out.println("Удалим задачу:");
        inMemoryTaskManager.removeTaskById(1);

        // вывод всех Тасков
        System.out.println("Список всех задач:\n" + inMemoryTaskManager.getAllTasks());

        // обновляем Эпик задачу
        Epic newEpic = new Epic(epic1.getId(), "Задача 1-я - Эпик", "Уборка", TaskState.DONE);
        System.out.println(inMemoryTaskManager.updateEpic(newEpic));

        // вывод всех Эпиков
        System.out.println("Список всех задач Эпик:\n" + inMemoryTaskManager.getAllEpics());

        // --== BEGIN: ВВОДИМ ПОДЗАДАЧИ ==-

        // Для 1-го Эпика
        Subtask subtask1ForEpic1 = inMemoryTaskManager.addNewSubtask(new Subtask(epic1.getId(), "1-я подзадача к 1-му Эпику",
                "Помыть полы", TaskState.IN_PROGRESS));
        Subtask subtask2ForEpic1 = inMemoryTaskManager.addNewSubtask(new Subtask(epic1.getId(), "2-я подзадача к 1-му Эпику",
                "Протереть пыль", TaskState.NEW));
        Subtask subtask3ForEpic1 = inMemoryTaskManager.addNewSubtask(new Subtask(epic1.getId(), "3-я подзадача к 1-му Эпику",
                "Вытряхнуть половик", TaskState.NEW));

        // Для 2-го Эпика
        Subtask subtask1ForEpic2 = inMemoryTaskManager.addNewSubtask(new Subtask(epic1.getId(), "1-я подзадача к 2-му Эпику",
                "Погулять с Умой", TaskState.DONE));
        Subtask subtask2ForEpic2 = inMemoryTaskManager.addNewSubtask(new Subtask(epic1.getId(), "2-я подзадача к 2-му Эпику",
                "Помыть собаню после прогулки", TaskState.IN_PROGRESS));
        Subtask subtask3ForEpic2 = inMemoryTaskManager.addNewSubtask(new Subtask(epic1.getId(), "3-я подзадача к 2-му Эпику",
                "Накормить собакена", TaskState.NEW));

        // --== END: ВВЕЛИ ПОДЗАДАЧИ ==-

        // удалим Эпик
        inMemoryTaskManager.removeEpicByID(epic1.getId());
        System.out.println("Список всех задач Эпик:\n" + inMemoryTaskManager.getAllEpics());

        // вывод все подзадачи
        System.out.println("Список всех подзадач:\n" + "    " + inMemoryTaskManager.getAllSubtasks());

        // вывод подзадачи по идентификатору
        System.out.println("Подзадача 6:\n" + "    " + inMemoryTaskManager.getSubtaskByID(6));

        // удалим подзадачу по идентификатору
        System.out.println("Удаление позадачи 5...");
        inMemoryTaskManager.removeSubtaskByID(5);
        System.out.println("Список всех подзадач:\n" + "    " + inMemoryTaskManager.getAllSubtasks());

        // обновляем подзадачу
        Subtask updatedSubtask3ForEpic2 = new Subtask(epic2.getId(), "3-я подзадача к 2-му Эпику", "Накормить собакена", TaskState.IN_PROGRESS);
        updatedSubtask3ForEpic2.setId(subtask3ForEpic2.getId());
        inMemoryTaskManager.updateSubtask(updatedSubtask3ForEpic2);
        System.out.println("Список всех подзадач:\n" + "    " + inMemoryTaskManager.getAllSubtasks());

        // удаляем все подзадачи
        System.out.println("Удаление всех подзадач...");
        inMemoryTaskManager.removeAllSubtasks();
        System.out.println("Список всех подзадач:\n" + "    " + inMemoryTaskManager.getAllSubtasks());
        System.out.println("Список всех задач Эпик:\n" + inMemoryTaskManager.getAllEpics());

        // удалим все Эпики
        inMemoryTaskManager.removeAllEpics();
        System.out.println("Список всех задач Эпик - после их удаления:\n" + inMemoryTaskManager.getAllEpics());
    }
}