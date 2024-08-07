package Models;

import Enums.TaskState;

public class Task {

    protected int id;
    protected String name;
    protected String description;
    protected TaskState state;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.state = TaskState.NEW;
    }

    public Task(String name, String description, TaskState state) {
        this.name = name;
        this.description = description;
        this.state = state;
    }


    public Task(int id, String name, String description, TaskState state) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
    }
}
