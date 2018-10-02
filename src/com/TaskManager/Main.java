package com.TaskManager;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;



public class Main {
    static Scanner in = new Scanner(System.in);
    static List<Task> tasks = new ArrayList<>();
    static String path="task.rtf";

    public static void main(String[] args) {
        String line;
        int welcome=2;
        printWelcome();
        //welcome=in.nextInt();

        if(welcome==1){
            boolean isExit = false;

            while (!isExit){
                System.out.print("Your task? ");
                line = in.nextLine();
                String command = line.split(" ")[0];
                if(command=="exit"||command==" "){
                    isExit=true;
                    printError();
                }
                else{
                    createTaskFromUserInput(command,line);
                }
            }
            exit();
        }
        else if(welcome==2){
            getTasksFromFile();
            printTasks();
        }
        else
        {
            printError();
        }
    }
    private static void printWelcome() {
        System.out.println("Welcome to TaskManager! Manual Task input, press 1. Task file import, press 2");
    }

    private static void  addTask(String input) throws TaskManagerException  {
        Task task=new Task();

        task.setTask(input.substring(3).trim());

        if(!task.description.isEmpty()){
            tasks.add(task);
        }
        else{
            throw new TaskManagerException("Empty description for Task");
        }
    }

    private static void  addTodoTask(String input) throws TaskManagerException {
        Task task=new TodoTask();
        task.setTask(input.substring(4).trim());
        ((TodoTask) task).isDone=false;
        if(task.description.isEmpty()){
            throw new TaskManagerException("Empty description for TodoTask");
        }
        else{
            tasks.add(task);
        }
    }

    private static void  addDeadlineTask(String input) throws TaskManagerException {
        Task task=new DeadlineTask();
        String description=input.split("/")[0].trim().substring(9);
        String by=input.substring(input.indexOf("by")+3,input.length());

        if(!input.contains("/")||!input.contains("by")){
            throw new TaskManagerException("Invalid deadline format");
        }
        else if(description.isEmpty()){
            throw new TaskManagerException("Empty description for DeadlineTask");
        }
        else if(by.isEmpty()){
            throw new TaskManagerException("Empty deadline for DeadlineTask");
        }
        else{
            task.setTask(description);
            ((DeadlineTask) task).setBy(by);
            tasks.add(task);
        }
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

    private static void getTasksFromFile() {
        //List<Task> loadedTasks = new ArrayList<>();
        try {
            List<String> lines = loadFile(path);
            for (String line : lines) {
                if (line.trim().isEmpty()) { //ignore empty lines
                    continue;
                }
                tasks.add(createTaskFromFile(line)); //convert the line to a task and add to the list
            }
        } catch (FileNotFoundException e) {
            printError("problem encountered while loading data: " + e.getMessage());
        }
        //return loadedTasks;
    }

    public static List<String> loadFile(String path) throws FileNotFoundException{
        List<String> lines= new ArrayList<>();
        File f = new File(path);
        Scanner s = new Scanner(f);

        while (s.hasNext()){
            System.out.println(s.next());
            lines.add(s.next());
        }

        s.close();

        return lines;
    }

    public static void createTaskFromUserInput(String command,String line){
            try{
                switch (command) {
                    case "add":
                        addTask(line);
                        System.out.println("Tasks in the list: "+tasks.size());
                        break;
                    case "todo":
                        addTodoTask(line);
                        System.out.println("Tasks in the list: "+tasks.size());
                        break;
                    case "deadline":
                        addDeadlineTask(line);
                        System.out.println("Tasks in the list: "+tasks.size());
                        break;
                    case "print":
                        printTasks();
                        break;
                    case "done":
                        markAsDone(line);
                        break;
                    default:
                        printError();
                }
            }
            catch (TaskManagerException e)
            {
                printError(e.getMessage());
            }
    }

    private static Task createTaskFromFile(String input){
        boolean status = false;

        String description = input.split("\\|")[2];
        Task task = new Task(description,status);

        if(Integer.parseInt(input.split("\\|")[1].trim()) == 1){
            status = true;
            task.setDone(status);
        }

        if(input.startsWith("D")){
            String deadline = input.split("\\|")[3];
            task = new DeadlineTask(description, status,deadline);
        }

        return task;
    }

    public void updateTaskFile(List<Task> taskList){
        try {
            PrintWriter output = new PrintWriter(path);
            for (int i = 0; i < taskList.size(); i++) {
                int status = 0;
                String description = taskList.get(i).getDescription();
                if(taskList.get(i).isDone()) status = 1;
                if(description.split("\\n").length > 2){
                    output.println("Deadline | "+ Integer.toString(status) + " | " + taskList.get(i).getDescription() + " | " + (description.split("\\n")[2]));
                } else{
                    output.println("Todo | "+ Integer.toString(status) + " | " + taskList.get(i).getDescription());
                }
            }
            output.close();
        }
        catch (IOException e){
            printError("problem encountered while writing data to file: " + e.getMessage());
        }
    }
}
