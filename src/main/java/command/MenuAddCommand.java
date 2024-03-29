package command;

import model.Menu;
import model.MenuItem;
import ui.Parser;


public class MenuAddCommand implements MenuCommand {
    public static Menu execute(Menu newMenu, String inputText) {
        String[] indexString = Parser.splitInput(Parser.analyzeInput(inputText), inputText);
        String itemID = indexString[0];
        String itemName = indexString[1];
        String itemPrice = indexString[2];
        //Optional<Menu> item = Menu.getMenuList().get(Integer.parseInt(itemID));

        newMenu.add(new MenuItem(itemID,itemName,Double.parseDouble(itemPrice)));
        return newMenu;
    }
}
