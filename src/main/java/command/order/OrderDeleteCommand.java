package command.order;

import model.Menu;
import model.Order;
import ui.Parser;

public class OrderDeleteCommand implements OrderCommand {
    /**
     * Executes the command to remove an item from an order.
     *
     * @param order     the order containing the item to be removed
     * @param inputText the input containing the details of the item to be removed
     * @param menu      the menu that the item to be removed is from
     */
    public static void execute(Order order, String inputText, Menu menu) {
        String[] indexString = Parser.splitInput(Parser.analyzeInput(inputText), inputText);
        String itemID = indexString[0];
        String itemQuantity = indexString[1];
        if (order.getItemCount(itemID) == 0) {
            System.out.println("Item not found in order");
        } else if (order.getItemCount(itemID) - Integer.parseInt(itemQuantity) <= 0) {
            order.remove(itemID);
            assert order.getItemCount(itemID) == 0 : "all items of itemID should be removed from the order";
            System.out.println("All " + " " + menu.getItemById(itemID).get().getName() + " is removed from order");
        } else {
            for (int i = 0; i < Integer.parseInt(itemQuantity); i++) {
                order.remove(menu.getItemById(itemID).get());
            }
            System.out.println(itemQuantity + " " + menu.getItemById(itemID).get().getName()
                    + " is removed from order");
        }
    }
}
