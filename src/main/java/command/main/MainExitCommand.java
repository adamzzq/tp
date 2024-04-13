package command.main;
public class MainExitCommand implements MainCommand {

    /**
     * Executes the command to exit the program
     * @param isExit the boolean value to exit the program
     * @return a boolean value to exit the program
     */
    public static boolean execute(boolean isExit) {
        System.out.println("Bye. Hope to see you again soon!");
        return true;
    }
}
