package command.order;

import model.Order;

public class OrderViewItemsCommand implements OrderCommand {
    /**
     * Executes the command to list all the items in an order.
     *
     * @param order     the order to be listed
     */
    public static void execute(Order order) {
        if (order.getSize() == 0) {
            System.out.println("Order is empty.");
        } else {
            System.out.println(order.viewItems());
        }
    }
}
