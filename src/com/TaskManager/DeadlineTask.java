package com.TaskManager;

public class DeadlineTask extends TodoTask{
    protected String by;

    public DeadlineTask(){
        super();
        by = " ";
    }


    public DeadlineTask(String description, boolean status, String by) {
        super(description,status);
        this.by=by;
    }

    public String getBy(){
        return by;
    }

    public void setBy(String dt){
        by=dt;
    }


    @Override
    public String toString(){
        return super.toString() + System.lineSeparator() + "do by: " + by;
    }
}
