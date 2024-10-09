package models;

import emuns.TaskState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Task task1;
    private Task task2;

    @Test
    public void shouldReturnTrueWhenTasksHaveSameId() {
        task1 = new Task("Первая задача", "Труля ля", TaskState.NEW);
        // task2 = new Task("Вторая задача", "Труля ля лямба", TaskState.NEW);
        task2 = new Task("Первая задача", "Труля ля", TaskState.NEW);

        task1.setId(1);
        task2.setId(1);

        //assertTrue(task1.equals(task2), "Задачи с одинаковыми идентификаторами должны быть так же одинаковы.");
        assertEquals(task1, task2);
    }
}