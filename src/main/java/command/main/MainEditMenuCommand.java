package command.main;

import model.Menu;
import ui.Parser;

import java.util.ArrayList;
import java.util.Optional;

public class MainEditMenuCommand implements MainCommand{
    public static Optional<Menu> execute(String inputText, ArrayList<Menu> menusList) {
        String[] parsedString = Parser.splitInput(Parser.analyzeInput(inputText), inputText);
        String menuID = parsedString[0];
        return menusList.stream().filter(menu -> menu.getID().equals(menuID)).findAny();
    }
}
