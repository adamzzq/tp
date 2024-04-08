package ui;

public class CommandErrorMessage {
    public static void printError(String inputText) {
        String errorMessage = "";
        if (inputText.startsWith("view") || inputText.startsWith("create")
                || inputText.startsWith("receipt") || inputText.startsWith("edit")
                || inputText.startsWith("add") || inputText.startsWith("delete")) {
            errorMessage = "Please ensure that you have entered the necessary parameters correctly.";
        } else if (inputText.startsWith("help")) {
            errorMessage = "Did you mean to type \"help\"?";
        } else if (inputText.startsWith("complete")) {
            errorMessage = "Did you mean to type \"complete\"?";
        } else if (inputText.startsWith("bye")) {
            errorMessage = "Did you mean to type \"bye\"?";
        } else {
            errorMessage = "Command not recognised. Type \"help\" to see the list of available commands.";
        }
        System.out.println(errorMessage);
    }
}
