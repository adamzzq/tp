package command.main;

import model.Menu;
import ui.Parser;

import java.util.ArrayList;
import java.util.Optional;

public class MainCreateOrderCommand implements MainCommand {

    /**
     * Executes the command to create an order
     * @param inputText the user input in the form of String from the command line
     * @param menusList the list of menus to choose from
     * @return an Optional of Menu
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
