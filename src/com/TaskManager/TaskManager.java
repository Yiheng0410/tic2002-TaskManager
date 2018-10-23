package com.TaskManager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TaskManager {
    private Storage storage;
    private UI ui;
    private TaskList tasks;


    public TaskManager(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.getTasksFromFile());
    }

    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        ui.printTasks(tasks);
        while (!isExit) {
            try {
                String fullCommand = ui.readUserCommand();
                String commandWord = Parser.getUserCommandWrold(fullCommand);
                switch (commandWord) {
                    case "exit":
                        break;
                    case " ": // exit if user input is empty
                        isExit = true;
                        break;
                    case "add":
                        tasks.addTask(Parser.createTask(fullCommand.substring(4)));
                        break;
                    case "print":
                        ui.printTasks(tasks);
                        break;
                    case "delete":
                        tasks.removeTask(Integer.parseInt(fullCommand.substring(7))-1);
                        break;
                    case "update":
                        try {
                            storage.update_file(tasks);
                        } catch (IOException e) {
                            ui.printError("Problem encountered while updating file" + e.getMessage());
                        }
                        break;
                    default:
                        ui.printError();
                }
            } catch (TaskManagerException e) {
                ui.printError(e.getMessage());
            }
        }
        ui.exit();
    }
}
