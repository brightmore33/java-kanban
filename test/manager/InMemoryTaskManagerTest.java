package manager;

import emuns.TaskState;
import models.Epic;
import models.Subtask;
import models.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    private Task task;
    private Epic epic;
    private Subtask subtask;
    private TaskManager taskManager;
    private HistoryManager historyManager;

    @BeforeEach
    void prepare() {
        task = new Task("Первая задача", "Пурум пум", TaskState.NEW);
        epic = new Epic("Первый Эпик", "Рум пум пам");
        taskManager = Managers.getDefault();
        historyManager = Managers.getDefaultHistory();
    }

    @Test
    void shouldAddNewTask() {
        Task expectedTask = new Task(0, "Первая задача", "Пурум пум", TaskState.NEW);

        Task currentTask = taskManager.addNewTask(task);

        Assertions.assertEquals(expectedTask, currentTask);
        Assertions.assertNotNull(currentTask);
        Assertions.assertNotNull(currentTask.getId());
    }

    @Test
    public void taskStabilityWhenAddToManager() {
        taskManager.addNewTask(task);

        Task currentTask = taskManager.getTaskById(0);

        Assertions.assertEquals(task.getId(), currentTask.getId());
        Assertions.assertEquals(task.getName(), currentTask.getName());
        Assertions.assertEquals(task.getState(), currentTask.getState());
    }

    @Test
    void shouldUpdateTask() {
        Task expectedTask = new Task(0, "Первая задача", "Пурум пум", TaskState.DONE);

        Task addedTask = taskManager.addNewTask(task);
        Task updatedTask = new Task(addedTask.getId(), "Первая задача", "Пурум пум", TaskState.DONE);
        taskManager.updateTask(updatedTask);

        Task currentTask = taskManager.getTaskById(addedTask.getId());

        Assertions.assertEquals(expectedTask, currentTask);
        Assertions.assertEquals(updatedTask.getState(), currentTask.getState());
    }

    @Test
    void shouldGetTaskById() {
        Task exepectedTask = new Task(0, "Первая задача", "Пурум пум", TaskState.NEW);

        Task addedTask = taskManager.addNewTask(task);
        Task currentTask = taskManager.getTaskById(addedTask.getId());

        Assertions.assertEquals(exepectedTask, currentTask);
    }

    @Test
    void shouldGetAllTasks() {
        Task task1 = new Task("Первая задача", "Пурум пум", TaskState.NEW);
        Task task2 = new Task("Вторая задача", "Что-то там", TaskState.IN_PROGRESS);
        Task task3 = new Task("Третья задача", "Вот это пвоворот!!!", TaskState.DONE);

        taskManager.addNewTask(task1);
        taskManager.addNewTask(task2);
        taskManager.addNewTask(task3);
        final List<Task> tasks = taskManager.getAllTasks();

        Assertions.assertNotNull(tasks, "Список должен быть не пуст.");
        Assertions.assertEquals(3, tasks.size());
    }

    @Test
    void shouldDeleteTaskById() {
        Task toRemoveTask = taskManager.addNewTask(task);

        taskManager.removeTaskById(toRemoveTask.getId());
        Task removedTask = taskManager.getTaskById(toRemoveTask.getId());

        Assertions.assertNull(removedTask);
    }

    @Test
    void shouldRemoveAllTasks() {
        Task task1 = new Task("Первая задача", "Пурум пум", TaskState.NEW);
        Task task2 = new Task("Вторая задача", "Что-то там", TaskState.IN_PROGRESS);
        Task task3 = new Task("Третья задача", "Вот это пвоворот!!!", TaskState.DONE);

        taskManager.addNewTask(task1);
        taskManager.addNewTask(task2);
        taskManager.addNewTask(task3);

        taskManager.removeAllTasks();
        final List<Task> tasks = taskManager.getAllTasks();

        Assertions.assertNotNull(tasks, "Список должен быть не пустой.");
        Assertions.assertTrue(tasks.isEmpty(), "Дожен быть пустой список.");
    }

    @Test
    void shouldAddEpic() {
        Epic expectedEpic = new Epic(0, "Первый Эпик", "Рум пум пам", TaskState.NEW);

        Epic currentEpic = taskManager.addNewEpic(epic);

        Assertions.assertNotNull(currentEpic);
        Assertions.assertNotNull(currentEpic.getId());
        Assertions.assertEquals(expectedEpic, currentEpic);
    }

    @Test
    void shouldEpicsIdsDoNotConflict() {
        Epic epic1 = new Epic(0, "Первый Эпик", "Рум пум пам", TaskState.NEW);
        Epic epic2 = new Epic("Второй Эпик", "Рум пум пам Second");

        taskManager.addNewEpic(epic1);
        taskManager.addNewEpic(epic2);

        Assertions.assertNotEquals(epic1.getId(), epic2.getId(), "Идентификаторы не должны совпадать.");
    }


}