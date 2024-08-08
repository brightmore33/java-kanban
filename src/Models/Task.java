package Models;

import Enums.TaskState;

import java.util.Objects;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "(id = '" + id + "'" +
                ", name = '" + name + "'" +
                ", state = '" + state + "'" +
                ", description = '" + description + "')";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, state);
    }
}
