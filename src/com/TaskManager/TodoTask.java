package com.TaskManager;

public class TodoTask extends Task{

    protected boolean status;


    public TodoTask(String description,boolean done) {
        super(description);
        this.status=done;
    }

    public  void setStatus(boolean status){
        this.status=status;

    }

    public boolean getStatus(){
        return this.status;

    }
    public void setDeadline(String ddl){

    }

    public String getDeadline(){
        return  null;
    }

    @Override
    public String getType(){
        return "todo";
    }

    public static Task addTodoTask(String description, String done) {
        Boolean status;

        if (done == "T") {
            status = true;
        } else {
            status= false;
        }

        Task task = new TodoTask(description,status);

        return task;
    }
    @Override
    public String toString(){
        return super.toString() + System.lineSeparator()+ "Is done?: "+status;
    }
}
