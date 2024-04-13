package command.menu;

import model.Menu;

public class MenuExitCommand implements MenuCommand {

    /**
     * Executes the command to cancel the menu
     * @param menu the menu to be cancelled
     */
    public static void execute (Menu menu) {
        System.out.println("Menu " + menu.getId() + " cancelled");
    }
}
