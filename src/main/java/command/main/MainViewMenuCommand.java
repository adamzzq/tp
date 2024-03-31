package command.main;

import model.Menu;
import java.util.ArrayList;
import ui.Parser;

public class MainViewMenuCommand {
    public static void execute(ArrayList<Menu> menuList, String inputText) {
        String[] indexString = Parser.splitInput(Parser.analyzeInput(inputText), inputText);
        String menuID = indexString[0];
        menuList.stream()
                .filter(menu -> menu.getID().equals(menuID))
                .findAny()
                .ifPresentOrElse(System.out::println, () -> System.out.println("Menu not found"));
    }

}
