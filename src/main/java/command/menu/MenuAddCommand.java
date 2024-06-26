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
     */
    public static void execute(Menu newMenu, String inputText) {
        String[] indexString = Parser.splitInput(Parser.analyzeInput(inputText), inputText);
        String itemID = String.valueOf(newMenu.getSize() + 1);
        String itemName = indexString[0];
        String itemPrice = indexString[1];
        Optional<MenuItem> itemByName = newMenu.getItemByName(itemName);

        // limit price range to 0.01 - 9999.99
        if (Double.parseDouble(itemPrice) < 0.01 || Double.parseDouble(itemPrice) > 9999.99) {
            System.out.println("Price must be between 0 and 10000 (both exclusive)");
            return;
        }

        if (itemName.isEmpty()) {
            System.out.println("Item name cannot be empty");
            return;
        }

        if (itemByName.isPresent()) {
            System.out.println("Item already in menu. It has ID: "
                    + itemByName.get().getID() + " and Name: " + itemByName.get().getName());
        } else {
            newMenu.add(new MenuItem(itemID, itemName, Double.parseDouble(itemPrice)));
            System.out.println("Item successfully added to menu!");
            System.out.println(newMenu);
        }
    }
}
