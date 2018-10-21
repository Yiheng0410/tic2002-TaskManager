package com.TaskManager;

public class TodoTask extends Task{

    public TodoTask(){
        super();
    }

    public TodoTask(String description ,boolean status){
        super(description,status);
    }

    public String getType(){
        return "todo";
    }

}
