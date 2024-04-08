package ui;

public class CommandErrorMessage {
    private static final String INVALID_PARAMS =
            "Please ensure that you have entered the necessary parameters correctly.";
    private static final String ORDER_VIEW = "Invalid format for view command. "
            + "Please enter either \"view menu\" or \"view item\".";
    private static final String MENU_VIEW = "Invalid format for view command. Please enter \"view item\".";
    private static final String INVALID_COMMAND =
            "Command not recognised. Type \"help\" to see the list of available commands.";

    public static void printMainError(String inputText) {
        String errorMessage = "";
        if (inputText.startsWith("view") || inputText.startsWith("create")
                || inputText.startsWith("receipt") || inputText.startsWith("edit")) {
            errorMessage = INVALID_PARAMS;
        } else {
            errorMessage = INVALID_COMMAND;
        }
        System.out.println(errorMessage);
    }

    public static void printOrderError(String inputText) {
        String errorMessage = "";
        if (inputText.startsWith("add") || inputText.startsWith("delete")) {
            errorMessage = INVALID_PARAMS;
        } else if (inputText.startsWith("view")) {
            errorMessage = ORDER_VIEW;
        } else {
            errorMessage = INVALID_COMMAND;
        }
        System.out.println(errorMessage);
    }

    public static void printMenuError(String inputText) {
        String errorMessage = "";
        if (inputText.startsWith("add") || inputText.startsWith("delete")) {
            errorMessage = INVALID_PARAMS;
        } else if (inputText.startsWith("view")) {
            errorMessage = MENU_VIEW;
        } else {
            errorMessage = INVALID_COMMAND;
        }
        System.out.println(errorMessage);
    }
}
