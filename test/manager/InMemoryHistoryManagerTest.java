package manager;

import emuns.TaskState;
import models.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class InMemoryHistoryManagerTest {
    private HistoryManager historyManager;
    private TaskManager taskManager;

    @BeforeEach
    void prepare() {
        historyManager = Managers.getDefaultHistory();
        taskManager = Managers.getDefault();
    }

    @Test
    void shouldAddTaskToHistoryList() {
        Task task = new Task(0, "Первая задача", "Пурум пум", TaskState.NEW);

        historyManager.add(task);
        final List<Task> history = historyManager.getHistory();

        Assertions.assertNotNull(history, "История не должна быть пустой.");
        Assertions.assertEquals(1, history.size(), "История не должна быть пустой.");
    }

    @Test
    void shouldAddNewTaskToTheEndOfHistory() {
        Task task1 = new Task( "Первая задача", "Пурум пум", TaskState.NEW);
        Task task2 = new Task( "Вторая задача", "Врум рум рум", TaskState.IN_PROGRESS);

        historyManager.add(task1);
        historyManager.add(task2);
        final List<Task> history = historyManager.getHistory();

        Assertions.assertEquals(task2, history.get(history.size() - 1), "Задача должна быть в конце списка.");
    }

    @Test
    void shouldAddSameTaskOnlyOnceAtHistory() {
        Task task1 = new Task( "Первая задача", "Пурум пум", TaskState.NEW);

        historyManager.add(task1);
        historyManager.add(task1);
        historyManager.add(task1);
        final List<Task> history = historyManager.getHistory();

        Assertions.assertEquals(1, history.size(), "Не должно быть дублткатов в истории.");
    }

    @Test
    void shouldRemoveFromHistoryWhenTaskRemoved() {
        Task task1 = new Task( "Первая задача", "Пурум пум", TaskState.NEW);
        taskManager.addNewTask(task1);
        taskManager.getTaskById(task1.getId());

        taskManager.removeTaskById(task1.getId());
        final List<Task> history = historyManager.getHistory();

        Assertions.assertEquals(0, history.size(), "В истории осталась запись.");
    }
}
