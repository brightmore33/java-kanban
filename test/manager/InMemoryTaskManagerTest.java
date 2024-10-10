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

    @Test
    void shouldGetEpicById() {
        Epic expectedEpic = new Epic(0, "Первый Эпик", "Рум пум пам", TaskState.NEW);

        Epic savedEpic = taskManager.addNewEpic(epic);
        Epic currentEpic = taskManager.getEpicById(savedEpic.getId());

        Assertions.assertEquals(expectedEpic, currentEpic);
    }

    @Test
    void shouldGetAllEpics() {
        Epic epic1 = new Epic("Первый Эпик", "Рум пум пам");
        Epic epic2 = new Epic("Второй Эпик", "Рум пум пам Second");
        Epic epic3 = new Epic("Третий Эпик", "Рум пум пам Third");

        taskManager.addNewEpic(epic1);
        taskManager.addNewEpic(epic2);
        taskManager.addNewEpic(epic3);

        final List<Epic> epics = taskManager.getAllEpics();

        Assertions.assertNotNull(epics, "Список Эпиков не должен быть пустым.");
        Assertions.assertEquals(3, epics.size());
    }

    @Test
    void shouldUpdateEpic() {
        Epic expectedEpic = new Epic(0, "Первый Эпик", "Рум пум пам", TaskState.NEW);

        Epic addedEpic = taskManager.addNewEpic(epic);
        Epic updatedEpic = new Epic(0, "Первый Эпик", "Рум пум пам", TaskState.NEW);
        taskManager.updateEpic(updatedEpic);
        Epic currentEpic = taskManager.getEpicById(addedEpic.getId());

        Assertions.assertEquals(expectedEpic, currentEpic);
    }

    @Test
    void shouldRemoveEpicById() {
        Epic epicToRemove = taskManager.addNewEpic(epic);

        taskManager.removeEpicByID(epicToRemove.getId());
        Epic removedEpic = taskManager.getEpicById(epicToRemove.getId());

        Assertions.assertNull(removedEpic);
    }

    @Test
    void shouldRemoveAllEpics() {
        Epic epic1 = new Epic("Первый Эпик", "Рум пум пам");
        Epic epic2 = new Epic("Второй Эпик", "Рум пум пам Second");
        Epic epic3 = new Epic("Третий Эпик", "Рум пум пам Third");

        taskManager.addNewEpic(epic1);
        taskManager.addNewEpic(epic2);
        taskManager.addNewEpic(epic3);

        taskManager.removeAllEpics();
        final List<Epic> epics = taskManager.getAllEpics();

        Assertions.assertNotNull(epics, "Список Эпиков не должен быть пустым.");
        Assertions.assertTrue(epics.isEmpty(), "Дожен быть пустой список.");
    }

    @Test
    void shouldAddNewSubTask() {
        taskManager.addNewEpic(epic);
        subtask = new Subtask(epic.getId(), "Первая подзадача", "Пошобуршим.", TaskState.IN_PROGRESS);
        Subtask expectedSubtask = new Subtask(epic.getId(), 2, "Первая подзадача", "Пошобуршим."
                , TaskState.IN_PROGRESS);

        Subtask currentSubtask = taskManager.addNewSubtask(subtask);

        Assertions.assertNotNull(currentSubtask);
        Assertions.assertNotNull(currentSubtask.getId());
        Assertions.assertEquals(expectedSubtask, currentSubtask);
    }

    @Test
    void shouldSubtasksIdsDoNotConflict() {
        taskManager.addNewEpic(epic);
        Subtask subtask1 = new Subtask(epic.getId(), 2, "Первая подзадача", "Пошобуршим",
                TaskState.NEW);
        Subtask subtask2 = new Subtask(epic.getId(), "Вторая подзадача", "Пошобуршим второй раз");

        taskManager.addNewSubtask(subtask1);
        taskManager.addNewSubtask(subtask2);

        Assertions.assertNotEquals(subtask1.getId(), subtask2.getId(), "Идентификаторы подздача должны " +
                "различаться.");
    }

    @Test
    void shouldUpdateSubtask() {
        taskManager.addNewEpic(epic);
        subtask = new Subtask(epic.getId(), "Первая подзадача", "Пошобуршим");
        Subtask expectedSubtask = new Subtask(epic.getId(), 2, "Первая подзадача", "Пошобуршим.",
                TaskState.DONE);

        Subtask updatedSubtask = new Subtask(epic.getId(), 2, "Первая подзадача", "Пошобуршим",
                TaskState.DONE);
        taskManager.updateSubtask(updatedSubtask);
        Subtask currentSubtask = taskManager.getSubtaskByID(updatedSubtask.getId());

        Assertions.assertEquals(expectedSubtask, currentSubtask);
    }
}