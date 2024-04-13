package command.main;

import model.Menu;
import ui.Parser;

import java.util.ArrayList;
import java.util.Optional;

public class MainCreateOrderCommand implements MainCommand {
    /**
     * Creates an order from the main interface by selecting items from an existing menu
     * @param inputText The input string entered by the user from the command line
     * @param menusList The list of all existing menus
     * @return the menu that was selected if it is present, otherwise return empty Optional
     */
    public static Optional<Menu> execute(String inputText, ArrayList<Menu> menusList) {
        try {
            String[] indexString = Parser.splitInput(Parser.analyzeInput(inputText), inputText);
            String menuID = indexString[0];
            return Optional.of(menusList.get(Integer.parseInt(menuID) - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Menu does not exist");
            System.out.println("Order not created");
        }
        return Optional.empty();
    }
}
