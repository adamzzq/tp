package command.menu;

import model.Menu;
import model.MenuItem;
import ui.Parser;

import java.util.Optional;


public class MenuAddCommand implements MenuCommand {
    /**
     * Executes the adding of items to a new menu
     * @param newMenu the menu to which the item is added to
     * @param inputText the user input in the form of String from the command line
     * @return the menu which may have new items added to it
     */
    public static Menu execute(Menu newMenu, String inputText) {
        String[] indexString = Parser.splitInput(Parser.analyzeInput(inputText), inputText);
        String itemID = indexString[0];
        String itemName = indexString[1];
        String itemPrice = indexString[2];
        Optional<MenuItem> itemByName = newMenu.getItemByName(itemName);
        Optional<MenuItem> itemByID = newMenu.getItemById(itemID);

        if (itemByID.isPresent()) {
            System.out.println("Item already in menu. It has ID: " + itemID + "and Name: " + itemByID.get().getName());
        } else if (itemByName.isPresent()) {
            System.out.println("Item already in menu. It has ID: " + itemByName.get().getID() +
                    " and Name: " + itemName);
        } else {
            newMenu.add(new MenuItem(itemID,itemName,Double.parseDouble(itemPrice)));
            System.out.println("Item successfully added to menu!");
            System.out.println(newMenu);
        }


        return newMenu;
    }
}
