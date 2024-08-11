package models;

import emuns.TaskState;

public class Subtask extends Task {
    private Integer epicID;

    public Subtask(Integer epicID, String name, String description, TaskState state) {
        super(name, description, state);
        this.epicID = epicID;
    }

    public Integer getEpicID() {
        return epicID;
    }
}