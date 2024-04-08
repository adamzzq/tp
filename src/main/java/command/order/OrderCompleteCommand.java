package command.order;

import model.Order;

import java.util.Scanner;

public class OrderCompleteCommand implements OrderCommand {
    /**
     * Executes the command to complete an order.
     *
     * @param order     the order to be completed
     * @return true if the order is completed, false otherwise
     */
    public static boolean execute(Order order, Scanner input) {
        if (order.getSize() == 0) {
            System.out.println("Order " + order.getId() + " is empty. Please add items to the order.");
            return false;
        }
        System.out.println(order.getReceipt());
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
