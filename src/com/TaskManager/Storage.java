package com.TaskManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private  String filePath;
    protected UI ui= new UI();

    public Storage(String path) {
        this.filePath = path;

    }

    public void setPath(String path) {
        this.filePath = path;
    }

    public String getPath() {
        return this.filePath;
    }

    public ArrayList<Task> getTasksFromFile() {
        //TaskList tasks = new TaskList();
        ArrayList<Task> tasks =new ArrayList();
        try {
            List<String> lines = loadFile();
            for (String line : lines) {
                if (line.trim().isEmpty()) { //ignore empty lines
                    continue;
                }
                tasks.add(Parser.createTask(line)); //convert the line to a task and add to the list
            }

        } catch (FileNotFoundException e) {
            ui.printError("problem encountered while loading data: " + e.getMessage());
        } catch (TaskManagerException e) {
            ui.printError("TaskManager Exception"+e);
        }
        return tasks;
    }

    public List<String> loadFile() throws FileNotFoundException {
        List<String> lines = new ArrayList<>();

        File f = new File(filePath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            lines.add(s.nextLine());
        }
        s.close();
        return lines;
    }

    public void update_file(TaskList tasks) throws IOException {

        FileWriter fw = new FileWriter(filePath);

        for (int i = 0; i < tasks.getSize(); i++) {
            String s = convertTaskToString(tasks.getTasksAtIndex(i));
            fw.write(s);
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    private String convertTaskToString(Task task) {
        String type;
        String isDone;
        String content;
        String doBy = " ";

        if (task.getStatus()) {
            isDone = "1";
        } else {
            isDone = "0";
        }
        content = task.getDescription();

        String s = null;

        if (task.getType().equals("todo")) {
            type = "T";
            s = type + " | " + isDone + " | " + content;
        } else if (task.getType().equals("deadline")) {
            type = "D";
            doBy = task.getDeadline();
            s = type + " | " + isDone + " | " + content + " | " + doBy;
        }
        return s;
    }


}
