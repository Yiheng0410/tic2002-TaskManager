package com.TaskManager;

public class Parser {

    public static String getUserCommandWrold(String input) {
        return input.split(" ")[0];//extract the first word of the user input
    }

    public static int getIndex(String fullCommand) throws Exception {
        try {
            return Integer.parseInt(fullCommand.split(" ")[1]);
        } catch (Exception e) {
            throw new Exception("The content format is wrong.");
        }
    }

    public static String getContent(String input) throws TaskManagerException {
        if (input.contains("with")) {
            return input.substring(input.indexOf("with") + "with".length()).trim();
        } else {
            throw new TaskManagerException("Command is without keyword with");
        }

    }

    public static Task createTask(String input) throws TaskManagerException {
        String[] inputs = input.split("\\|");

        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = inputs[i].trim();
        }

        if (inputs[2] == null) {
            throw new TaskManagerException("Invalid empty task description");
        } else {
            if (inputs[0].equals("T")) {
                return TodoTask.addTodoTask(inputs[2], inputs[1]);
            } else if (inputs[0].equals ("D")) {
                return DeadlineTask.addDeadlineTask(inputs[2], inputs[1], inputs[3]);
            }
        }

        return null;
    }
}
