import Models.Task;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");

        // накидываем задачи
        Task task1 = new Task("Глажка", "Погладить кота, а лучше вещи");
        Task task2 = new Task("Мусор", "Вынести мусор");
        Task task3 = new Task("Собака", "Её тоже надо гладить");
    }
}
