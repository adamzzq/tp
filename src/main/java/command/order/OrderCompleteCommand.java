package command.order;

import model.Order;
import ui.Parser;

import java.util.Scanner;

public class OrderCompleteCommand implements OrderCommand {
    /**
     * Executes the command to complete an order.
     *
     * @param order     the order to be completed
     * @return true if the order is completed, false otherwise
     */
    public static boolean execute(Order order, Scanner input, String inputText) {
        if (order.getSize() == 0) {
            System.out.println("Order " + order.getId() + " is empty. Please add items to the order.");
            return false;
        }

        String[] indexString = Parser.splitInput(Parser.analyzeInput(inputText), inputText);
        double discount;
        try {
            discount = Integer.parseInt(indexString[1]) / 100.0;
        } catch (ArrayIndexOutOfBoundsException e) {
            discount = 0.0;
        }

        try {
            System.out.println(order.getReceipt(discount));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
        System.out.println("WARNING: Once an order is completed, you are NOT ALLOWED to edit or delete it.");
        System.out.println("Do you want to complete the order? (type 'y' to complete, anything else to cancel)");
        String userInput = input.nextLine();
        if (userInput.equals("y")) {
            System.out.println("Order " + order.getId() + " is completed!");
            return true;
        } else {
            System.out.println("Order " + order.getId() + " is not completed.");
            return false;
        }
    }
}
