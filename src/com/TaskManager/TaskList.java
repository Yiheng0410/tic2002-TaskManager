package com.TaskManager;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public  TaskList(ArrayList<Task> tasks){
        this.tasks=tasks;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public Task getTasksAtIndex(int n) {
        return tasks.get(n);
    }

    public void removeTask(int n) {
         tasks.remove(n);
    }

    public void removeAllTask() {
        tasks.clear();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int getSize() {

        return tasks.size();
    }

    public void editTaskDescription(int n, String description) {
        getTasksAtIndex(n).setDescription(description);
    }
}
