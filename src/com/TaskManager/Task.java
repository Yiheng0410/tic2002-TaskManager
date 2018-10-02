package com.TaskManager;
import java.util.List;

public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String name,boolean status){
       setTask(name);
       setDone(status);
    }
    public Task(){
        this(" ",false);
    }

    public void setTask(String name){
        this.description=name;
    }

    public void setDone(boolean status){
        isDone=status;
    }

    public boolean isDone(){
        return isDone;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public String toString() {
        String status = null;
        if (isDone){
            status = "Yes";
        } else {
            status = "No";
        }
        return "description: "+ description + System.lineSeparator() + "is done? " + status;
    }

}
