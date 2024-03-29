package command.menu;

import model.Menu;

public class MenuCompleteCommand implements MenuCommand {
    public static boolean execute(Menu menu) {
        if (menu.getSize() == 0) {
            System.out.println("Menu" + menu.getID() + " is empty. Please add items to the menu.");
            return false;
        }
        return true;
    }
}
