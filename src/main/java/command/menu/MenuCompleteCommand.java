package command.menu;

import model.Menu;

public class MenuCompleteCommand implements MenuCommand {
    public static boolean execute(Menu menu) {
        if (menu.getSize() == 0) {
            System.out.println("Menu " + menu.getId() + " is empty. Please add items to the menu.");
            return false;
        }
        System.out.println("Menu " + menu.getId() + " has been saved!");
        return true;
    }
}
