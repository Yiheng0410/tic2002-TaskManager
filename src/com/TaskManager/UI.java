package com.TaskManager;

import java.util.Scanner;

public class UI {
    private Scanner in;

    public UI() {
        in = new Scanner(System.in);
    }

    public String readUserCommand() {
        System.out.print("Your task? ");
        return in.nextLine().trim();
    }

    public void showMessage(String s){
        System.out.println(s);
    }

    public  void printError(String msg) {
        System.out.println(msg);
    }

    public  void printError() {
        System.out.println("Unknown command! please try again");
    }

    public  void exit() {
        System.out.println("Bye!");
    }

    public  void printWelcome() {
        System.out.println("Welcome to TaskManager!");
    }

    public void printTasks(TaskList tasks) {
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println("[" + (i + 1) + "] " + tasks.getTasksAtIndex(i));
        }
    }

}
