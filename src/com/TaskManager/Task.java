package com.TaskManager;

import java.util.List;

public abstract class Task {

    protected String description;
    //protected boolean isDone;


    public Task(String taskName) {
        this.description = taskName;
    }

    public void setDescription(String taskName) {
        this.description = taskName;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return "task";
    }

    public abstract void setStatus(boolean status);

    public abstract boolean getStatus();

    public abstract void setDeadline(String ddl);

    public abstract String getDeadline();


    @Override
    public String toString() {
        return "description: " + description;
    }


}
