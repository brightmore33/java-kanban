package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    private Epic epic1;
    private Epic epic2;

    @Test
    public void shouldReturnTrueWhenEpicsHaveSameId() {
        epic1 = new Epic("Первый Эпик", "Парам пам пам");
        //epic2 = new Epic("Второй Эпик", "Рам пам пам");
        epic2 = new Epic("Первый Эпик", "Парам пам пам");

        epic1.setId(1);
        epic2.setId(1);

        assertTrue(epic1.equals(epic2), "Эпики с одинаковыми идентификаторами должны быть так же одинаковы.");
    }
}