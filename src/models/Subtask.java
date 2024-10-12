package models;

import emun.TaskState;

public class Subtask extends Task {
    private Integer epicID;

    public Subtask(Integer epicID, String name, String description) {
        super(name, description);
        this.epicID = epicID;
    }

    public Subtask(Integer epicID, String name, String description, TaskState state) {
        super(name, description, state);
        this.epicID = epicID;
    }

    public Subtask(Integer epicID, Integer id, String name, String description, TaskState state) {
        super(id, name, description, TaskState.NEW);
        this.epicID = epicID;
    }

    public Integer getEpicID() {
        return epicID;
    }
}