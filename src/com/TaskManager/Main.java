package com.TaskManager;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;


public class Main {
    static Scanner in = new Scanner(System.in);
    static List<Task> tasks = new ArrayList<>();
    static String path = "tasks.txt";

    public static void main(String[] args) {
        String line;
        printWelcome();
        boolean isExit = false;
        tasks = getTasksFromFile();
        printTasks();

        while (!isExit) {
            System.out.print("Your task? ");

            line = in.nextLine();

            String command = line.split(" ")[0];

            switch (command) {
                case "exit":
                    break;
                case " ": // exit if user input is empty
                    isExit = true;
                    break;
                case "add":
                    tasks.add(createTask(line.substring(4)));
                    break;
                case "print":
                    printTasks();
                    break;
                case "update":
                    try {
                        update_file(path);
                    } catch (IOException e) {
                        printError("Problem encountered while updating file" + e.getMessage());
                    }
                    break;
                default:
                    printError();
            }
        }
        exit();
    }

    private static void printWelcome() {
        System.out.println("Welcome to TaskManager!");
    }

    private static void addTask(String input) throws TaskManagerException {
        Task task = new Task();

        task.setTask(input.substring(3).trim());

        if (!task.description.isEmpty()) {
            tasks.add(task);
        } else {
            throw new TaskManagerException("Empty description for Task");
        }
    }

    private static void addTodoTask(String input) throws TaskManagerException {
        Task task = new TodoTask();
        task.setTask(input.substring(4).trim());
        ((TodoTask) task).isDone = false;
        if (task.description.isEmpty()) {
            throw new TaskManagerException("Empty description for TodoTask");
        } else {
            tasks.add(task);
        }
    }

    private static Task addTodoTask(String des, String status) {
        Task task = new TodoTask();

        task.setTask(des);

        if (status == "T") {
            ((TodoTask) task).isDone = true;
        } else {
            ((TodoTask) task).isDone = false;
        }

        return task;
    }

    private static void addDeadlineTask(String input) throws TaskManagerException {
        Task task = new DeadlineTask();
        String description = input.split("/")[0].trim().substring(9);
        String by = input.substring(input.indexOf("by") + 3, input.length());

        if (!input.contains("/") || !input.contains("by")) {
            throw new TaskManagerException("Invalid deadline format");
        } else if (description.isEmpty()) {
            throw new TaskManagerException("Empty description for DeadlineTask");
        } else if (by.isEmpty()) {
            throw new TaskManagerException("Empty deadline for DeadlineTask");
        } else {
            task.setTask(description);
            ((DeadlineTask) task).setBy(by);
            tasks.add(task);
        }
    }

    private static Task addDeadlineTask(String des, String status, String ddl) {
        Task task = new DeadlineTask();
        task.setTask(des);
        ((DeadlineTask) task).setBy(ddl);
        return task;
    }

    private static void markAsDone(String line) {
        int index = Integer.parseInt(line.substring("done".length()).trim());
        tasks.get(index - 1).setDone(true);
        System.out.println("Tasks in the list: " + tasks.size());
    }

    private static void printError(String msg) {
        System.out.println(msg);
    }

    private static void printError() {
        System.out.println("Unknown command! please try again");
    }

    private static void exit() {
        System.out.println("Bye!");
    }

    private static void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + tasks.get(i));
        }
    }

    private static List<Task> getTasksFromFile() {
        List<Task> loadedTasks = new ArrayList<>();

        try {
            List<String> lines = loadFile(path);
            //System.out.println(lines);
            for (String line : lines) {
                if (line.trim().isEmpty()) { //ignore empty lines
                    continue;
                }
                loadedTasks.add(createTask(line)); //convert the line to a task and add to the list
            }

        } catch (FileNotFoundException e) {
            printError("problem encountered while loading data: " + e.getMessage());
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

    private static Task createTask(String input) {
        String[] inputs = input.split("\\|");

        Task task = new Task();

        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = inputs[i].trim();
        }

        switch (inputs[0]) {
            case "T":

                task = addTodoTask(inputs[2], inputs[1]);
                break;
            case "D":
                task = addDeadlineTask(inputs[2], inputs[1], inputs[3]);
                break;
        }

        return task;
    }

    private static void update_file(String path) throws IOException {

        FileWriter fw = new FileWriter(path);

        for (Task task : tasks) {
            String s = convertTaskToString(task);
            fw.write(s);
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    private static String convertTaskToString(Task task) {
        String type;
        String isDone;
        String content;
        String doBy = " ";

        if (task.isDone()) {
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
            //doBy = task.;
            s = type + " | " + isDone + " | " + content + " | " + doBy;
        }
        return s;
    }
}
