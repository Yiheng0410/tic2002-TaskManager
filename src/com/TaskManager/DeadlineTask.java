package com.TaskManager;

public class DeadlineTask extends TodoTask{
    protected String ddl;

    public DeadlineTask(String description, boolean status, String ddl) {
        super(description,status);
        this.ddl=ddl;
    }

    public void setDeadline(String ddl){
        this.ddl=ddl;

    }

    public String getDeadline(){
        return  this.ddl;
    }

    public String getType(){
        return "deadline";
    }

    public static Task addDeadlineTask(String description, String done, String ddl) {
        Boolean status;

        if (done == "T") {
            status = true;
        } else {
            status= false;
        }

        Task task = new DeadlineTask (description,status,ddl);

        return task;
    }

    @Override
    public String toString(){
        return super.toString() + System.lineSeparator() + "do by: " + ddl;
    }
}
