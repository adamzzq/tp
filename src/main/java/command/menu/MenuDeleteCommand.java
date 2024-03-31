package command.menu;

import model.Menu;
import ui.Parser;

public class MenuDeleteCommand implements MenuCommand{
    /**
     * Executes the command to remove an item from the current menu
     * @param menu the menu containing the item to be removed
     * @param inputText the user input in the form of String from the command line
     * @return the menu which may or may not have been updated
     */
    public static Menu execute(Menu menu, String inputText) {
        String[] indexString = Parser.splitInput(Parser.analyzeInput(inputText),inputText);
        String itemID = indexString[0];
        if (menu.getItemById(itemID).isEmpty()) {
            System.out.println("Item not found in menu");
            System.out.println(menu);
            return menu;
        } else {
            System.out.println(menu.getItemById(itemID).get().getName() + " is removed from menu");
            menu.remove(itemID);
            System.out.println(menu);
            return menu;
        }

    }
}
