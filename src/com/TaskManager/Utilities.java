package com.TaskManager;

public class Utilities {

    protected static void printError(String msg) {
        System.out.println(msg);
    }

    protected static void printError() {
        System.out.println("Unknown command! please try again");
    }

    private static void exit() {
        System.out.println("Bye!");
    }

}
