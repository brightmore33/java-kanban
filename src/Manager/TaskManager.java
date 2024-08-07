package Manager;

public class TaskManager {

    private static int idCounter = 0;

    public static int getNewID() {
        return idCounter++;
    }
}
