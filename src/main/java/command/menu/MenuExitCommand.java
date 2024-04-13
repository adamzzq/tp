package command.menu;

import model.Menu;

public class MenuExitCommand implements MenuCommand {
    /**
     * Outputs confirmation message of cancelling a menu
     * @param menu The menu that is being cancelled
     */
    public static void execute (Menu menu) {
        System.out.println("Menu " + menu.getId() + " cancelled");
    }
}
