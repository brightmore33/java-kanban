import Enums.TaskState;
import Manager.TaskManager;
import Models.Task;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");

        TaskManager taskManager = new TaskManager();

        // накидываем Таски
        Task task1 = taskManager.addNewTask(new Task("Задача 1-я", "Погладить кота, а лучше вещи"));
        Task task2 = taskManager.addNewTask(new Task("Задача 2-я", "Вынести мусор"));
        Task task3 = taskManager.addNewTask(new Task("Задача 3-я", "Замочить таракана"));

        // обновляем Таску
        Task newTask = new Task(task1.getId(), "Задача 1-я", "Погладить кота, а лучше вещи", TaskState.IN_PROGRESS);
        System.out.println(taskManager.updateTask(newTask));

        // вытаскиваем Таску по идентификатору
        System.out.println("Получаем задачу по id:\n" + taskManager.getTaskById(2));

        // вывод всех Тасков
        System.out.println("Список всех задач:\n" + taskManager.getAllTasks());
    }
}