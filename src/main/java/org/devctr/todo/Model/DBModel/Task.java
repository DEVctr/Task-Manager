package org.devctr.todo.Model.DBModel;

import java.sql.Timestamp;

public class Task {
    private int id;
    private String task_name;
    private String description;
    private Timestamp deadline;
    private Timestamp created_at;
    private String priority;
    private String status;

    public Task(int id, String task_name, String description, Timestamp deadline, Timestamp created_at, String priority, String status) {
        this.id = id;
        this.task_name = task_name;
        this.description = description;
        this.deadline = deadline;
        this.created_at = created_at;
        this.priority = priority;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
