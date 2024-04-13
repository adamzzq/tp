package command.menu;

import model.Menu;

public class MenuCompleteCommand implements MenuCommand {

    /**
     * Executes the command to save the menu
     * @param menu the menu to be saved
     * @return a boolean value to indicate if the menu has been saved
     */
    public static boolean execute(Menu menu) {
        if (menu.getSize() == 0) {
            System.out.println("Menu " + menu.getId() + " is empty. Please add items to the menu.");
            return false;
        }
        System.out.println("Menu " + menu.getId() + " has been saved!");
        return true;
    }
}
