package command.menu;

import model.Menu;

public class MenuExitCommand implements MenuCommand {
    public static void execute (Menu menu) {
        System.out.println("Menu " + menu.getID() + " cancelled");
    }
}
