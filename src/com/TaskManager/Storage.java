package com.TaskManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    protected String path;

    public Storage (String path){
       setPath(path);

    }
    public Storage(){
        this("");
    }

    public void setPath(String path){
        this.path=path;
    }

    public String getPath(){
        return  path;
    }

    private static List<Task> getTasksFromFile(String filename) {
        List<Task> loadedTasks = new ArrayList<>();

        try {
            List<String> lines = loadFile(filename);
            for (String line : lines) {
                if (line.trim().isEmpty()) { //ignore empty lines
                    continue;
                }
                loadedTasks.add(createTask(line)); //convert the line to a task and add to the list
            }

        } catch (FileNotFoundException e) {
            Utilities.printError("problem encountered while loading data: " + e.getMessage());
        }

        return loadedTasks;
    }

    public static List<String> loadFile(String path) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();

        File f = new File(path);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            lines.add(s.nextLine());
        }
        s.close();
        return lines;
    }
}
