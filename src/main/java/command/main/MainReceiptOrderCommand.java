package command.main;

import model.Order;
import ui.Parser;

import java.util.ArrayList;

public class MainReceiptOrderCommand {

    /**
     * Executes the command to print the receipt of an order
     * @param ordersList the list of orders to choose from
     * @param inputText the user input in the form of String from the command line
     */
    public static void execute(ArrayList<Order> ordersList, String inputText) {
        String[] indexString = Parser.splitInput(Parser.analyzeInput(inputText), inputText);
        String orderID = indexString[0];
        ordersList.stream()
                .filter(order -> order.getId().equals(orderID))
                .findAny()
                .ifPresentOrElse(x -> {
                    System.out.println(x.getReceipt()); },
                        () -> System.out.println("Order not found"));
    }
}
