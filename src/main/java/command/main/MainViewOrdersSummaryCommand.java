package command.main;

import model.Order;

import java.util.ArrayList;

public class MainViewOrdersSummaryCommand implements MainCommand {

    /**
     * Executes the command to view the summary of all orders
     * @param ordersList the list of orders to choose from
     */
    public static void execute(ArrayList<Order> ordersList) {
        if (ordersList.isEmpty()){
            System.out.println("No orders available");
            return;
        }
        ordersList.forEach(x -> System.out.println(x.getOrderSummary()));
    }
}
