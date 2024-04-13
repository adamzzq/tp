package command.main;

import model.Menu;
import java.util.ArrayList;
import ui.Parser;

/**
 * Displays the menu that was specified by the menu ID
 */
public class MainViewMenuCommand {

    /**
     * Executes the command to view a menu
     * @param menuList the list of menus to choose from
     * @param inputText the user input in the form of String from the command line
     */
    public static void execute(ArrayList<Menu> menuList, String inputText) {
        String[] indexString = Parser.splitInput(Parser.analyzeInput(inputText), inputText);
        String menuID = indexString[0];
        menuList.stream()
                .filter(menu -> menu.getId().equals(menuID))
                .findAny()
                .ifPresentOrElse(System.out::println, () -> System.out.println("Menu not found"));
    }

}
