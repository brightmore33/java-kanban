package Models;

import Enums.TaskState;

public class Task {

    private final int id;
    private final String name;
    private final String description;
    private final TaskState state;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.state = TaskState.NEW;
    }
}
