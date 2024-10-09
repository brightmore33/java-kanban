package manager;

import emuns.TaskState;
import models.Epic;
import models.Subtask;
import models.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Task expectedTask = new Task(0, "Первая задача", "Пурум пум",TaskState.NEW);

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
}