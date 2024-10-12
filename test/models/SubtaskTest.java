package models;

import emun.TaskState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {
    @Test
    public void shouldReturnTrueWhenSubTasksHaveSameId() {
        Subtask subTask1 = new Subtask(4, "Первая подзадача", "румбаааа", TaskState.NEW);
        Subtask subTask2 = new Subtask(4, "Первая подзадача", "румбаааа", TaskState.NEW);

        subTask1.setId(5);
        subTask2.setId(5);

        assertEquals(subTask1, subTask2);
    }
}