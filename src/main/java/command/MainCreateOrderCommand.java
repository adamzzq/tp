package command;

import logic.OrderLogic;
import model.Menu;
import model.Order;
import ui.Parser;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class MainCreateOrderCommand implements MainCommand{

    public static Optional<Menu> execute(Scanner input, String inputText, ArrayList<Menu> menusList) {
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
