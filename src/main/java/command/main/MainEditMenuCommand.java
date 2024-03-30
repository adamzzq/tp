package command.main;

import model.Menu;
import ui.CommandType;
import ui.Parser;

import java.util.ArrayList;
import java.util.Optional;

public class MainEditMenuCommand implements MainCommand{
    public static Optional<Menu> execute(CommandType commandType, String inputText, ArrayList<Menu> menusList) {
        String[] parsedString = Parser.splitInput(commandType, inputText);
        String menuID = parsedString[0];
        return menusList.stream().filter(menu -> menu.getID().equals(menuID)).findAny();
    }
}
