package command.main;

import model.Order;
import ui.Parser;

import java.util.ArrayList;

public class MainViewOrderCommand implements MainCommand {

    /**
     * Executes the command to view an order
     * @param ordersList the list of orders to choose from
     * @param inputText the user input in the form of String from the command line
     */
    public static void execute(ArrayList<Order> ordersList, String inputText) {
        String[] indexString = Parser.splitInput(Parser.analyzeInput(inputText), inputText);
        String orderID = indexString[0];
        ordersList.stream()
                .filter(order -> order.getId().equals(orderID))
                .findAny()
                .map(Order::getReceipt)
                .ifPresentOrElse(System.out::println, () -> System.out.println("Order not found"));
    }
}
