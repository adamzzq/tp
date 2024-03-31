package command.main;

import model.Order;
import ui.Parser;

import java.util.ArrayList;

public class MainViewOrderCommand implements MainCommand {
    public static void execute(ArrayList<Order> ordersList, String inputText) {
        String[] indexString = Parser.splitInput(Parser.analyzeInput(inputText), inputText);
        String orderID = indexString[0];
        ordersList.stream()
                .filter(order -> order.getId().equals(orderID))
                .findAny()
                .ifPresentOrElse(System.out::println, () -> System.out.println("Order not found"));
    }
}
