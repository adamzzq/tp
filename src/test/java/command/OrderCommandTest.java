package command;

import command.menu.MenuAddCommand;
import command.order.OrderAddCommand;
import command.order.OrderViewItemsCommand;
import command.order.OrderExitCommand;
import command.order.OrderDeleteCommand;
import command.order.OrderCompleteCommand;
import command.order.OrderHelpCommand;
import command.order.OrderViewMenuCommand;
import model.Menu;
import model.Order;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderCommandTest {
    @Test
    public void testOrderHelp() {
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        OrderHelpCommand.execute();
        String expectedOutput = "Here are the list of available commands:\n" +
                "\thelp: Shows all the commands that can be used.\n" +
                "\tadd -item <item_id> -quantity <quantity_of_item>: " +
                "Adds the specified quantity \n\t\tof a particular menu item into the order.\n" +
                "\tdelete -item <item_id> -quantity <quantity_of_item>: "
                + "Deletes the specified quantity \n\t\tof a particular menu item from the order.\n"+
                "\tview menu: Shows the menu of the current order.\n" +
                "\tview item: Shows the items in the current order.\n" +
                "\tcomplete (-discount <percentage>): Completes the order and returns to the main menu.\n"+
                "\tcancel: Aborts the current order and returns to the main menu.\n";

        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    @Test
    public void testOrderAddOneExistingItem() {
        Menu newMenu = new Menu("1");
        MenuAddCommand.execute(newMenu,"add -item Ice Cream -price 2.00");
        MenuAddCommand.execute(newMenu,"add -item Sundae -price 3.00");
        MenuAddCommand.execute(newMenu,"add -item Frappe -price 4.00");
        MenuAddCommand.execute(newMenu,"add -item Fried Chicken -price 5.00");
        Order newOrder = new Order("abc restaurant", "abc street",
                "mr abc", "Takeaway");
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        OrderAddCommand.execute(newOrder, "add -item 1 -quantity 1",newMenu);
        String expectedOutput = "1 Ice Cream is added to order\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    @Test
    public void testOrderAddMaxExistingItem() {
        Menu newMenu = new Menu("1");
        MenuAddCommand.execute(newMenu,"add -item Ice Cream -price 2.00");
        MenuAddCommand.execute(newMenu,"add -item Sundae -price 3.00");
        MenuAddCommand.execute(newMenu,"add -item Frappe -price 4.00");
        MenuAddCommand.execute(newMenu,"add -item Fried Chicken -price 5.00");
        Order newOrder = new Order("abc restaurant", "abc street",
                "mr abc", "Takeaway");
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        OrderAddCommand.execute(newOrder, "add -item 2 -quantity 9999",newMenu);
        String expectedOutput = "9999 Sundae is added to order\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    @Test
    public void testOrderAddTenThousandExistingItem() {
        Menu newMenu = new Menu("1");
        MenuAddCommand.execute(newMenu,"add -item Ice Cream -price 2.00");
        MenuAddCommand.execute(newMenu,"add -item Sundae -price 3.00");
        MenuAddCommand.execute(newMenu,"add -item Frappe -price 4.00");
        MenuAddCommand.execute(newMenu,"add -item Fried Chicken -price 5.00");
        Order newOrder = new Order("abc restaurant", "abc street",
                "mr abc", "Takeaway");
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        OrderAddCommand.execute(newOrder, "add -item 2 -quantity 10000",newMenu);
        String expectedOutput = "Quantity must be between 0 and 10000 (both exclusive)\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    @Test
    public void testOrderAddZeroExistingItem() {
        Menu newMenu = new Menu("1");
        MenuAddCommand.execute(newMenu,"add -item Ice Cream -price 2.00");
        MenuAddCommand.execute(newMenu,"add -item Sundae -price 3.00");
        MenuAddCommand.execute(newMenu,"add -item Frappe -price 4.00");
        MenuAddCommand.execute(newMenu,"add -item Fried Chicken -price 5.00");
        Order newOrder = new Order("abc restaurant", "abc street",
                "mr abc", "Takeaway");
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        OrderAddCommand.execute(newOrder, "add -item 4 -quantity 0",newMenu);
        String expectedOutput = "Quantity must be between 0 and 10000 (both exclusive)\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    @Test
    public void testOrderAddNonExistingItem() {
        Menu newMenu = new Menu("1");
        MenuAddCommand.execute(newMenu,"add -item Ice Cream -price 2.00");
        MenuAddCommand.execute(newMenu,"add -item Sundae -price 3.00");
        MenuAddCommand.execute(newMenu,"add -item Frappe -price 4.00");
        MenuAddCommand.execute(newMenu,"add -item Fried Chicken -price 5.00");
        Order newOrder = new Order("abc restaurant", "abc street",
                "mr abc", "Takeaway");
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        OrderAddCommand.execute(newOrder, "add -item 7 -quantity 2",newMenu);
        String expectedOutput = "Item not found in menu\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    @Test
    public void testOrderEmptyComplete() {
        String response = "y";
        Scanner newScanner = new Scanner(response);
        Menu newMenu = new Menu("1");
        MenuAddCommand.execute(newMenu,"add -item Ice Cream -price 2.00");
        MenuAddCommand.execute(newMenu,"add -item Sundae -price 3.00");
        MenuAddCommand.execute(newMenu,"add -item Frappe -price 4.00");
        MenuAddCommand.execute(newMenu,"add -item Fried Chicken -price 5.00");
        Order newOrder = new Order("abc restaurant", "abc street",
                "mr abc", "Takeaway");
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        OrderCompleteCommand.execute(newOrder, newScanner,"complete order");
        String expectedOutput = "Order " + newOrder.getId() + " is empty. Please add items to the order.\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    @Test
    public void testOrderValidComplete() {
        String response = "y";
        Scanner newScanner = new Scanner(response);
        Menu newMenu = new Menu("1");
        MenuAddCommand.execute(newMenu,"add -item Ice Cream -price 2.00");
        MenuAddCommand.execute(newMenu,"add -item Sundae -price 3.00");
        MenuAddCommand.execute(newMenu,"add -item Frappe -price 4.00");
        MenuAddCommand.execute(newMenu,"add -item Fried Chicken -price 5.00");
        Order newOrder = new Order("abc restaurant", "abc street",
                "mr abc", "Takeaway");
        OrderAddCommand.execute(newOrder, "add -item 2 -quantity 2",newMenu);
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        OrderCompleteCommand.execute(newOrder, newScanner,"complete");
        String expectedOutput = "+-----------------------------------------------------+\n" +
                "|                       RECEIPT                       |\n" +
                "+-----------------------------------------------------+\n" +
                "|                    abc restaurant                   |\n" +
                "|                      abc street                     |\n" +
                "|                                                     |\n" +
                "| Order Type: Takeaway                                |\n" +
                "| Order ID: " + newOrder.getId() + "                            |\n" +
                "+-----------------------------------------------------+\n" +
                "| Item ID  | Name            | Unit Price  | Quantity |\n" +
                "+-----------------------------------------------------+\n" +
                "| 2        | Sundae          | $3.00       | 2        |\n" +
                "+-----------------------------------------------------+\n" +
                "| Subtotal:                                     $6.00 |\n" +
                "+-----------------------------------------------------+\n" +
                "| Service Charge (10.0%):                       $0.60 |\n" +
                "| GST (9.0%):                                   $0.59 |\n" +
                "| Grand Total:                                  $7.19 |\n" +
                "+-----------------------------------------------------+\n" +
                "| Cashier: mr abc                                     |\n" +
                "+-----------------------------------------------------+\n\n" +

                "WARNING: Once an order is completed, you are NOT ALLOWED to edit or delete it.\n" +
                "Do you want to complete the order? (type 'y' to complete, anything else to cancel)\n"+

                "Order " + newOrder.getId() + " is completed!\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    @Test
    public void testOrderValidRejectComplete() {
        String response = "k";
        Scanner newScanner = new Scanner(response);
        Menu newMenu = new Menu("1");
        MenuAddCommand.execute(newMenu,"add -item Ice Cream -price 2.00");
        MenuAddCommand.execute(newMenu,"add -item Sundae -price 3.00");
        MenuAddCommand.execute(newMenu,"add -item Frappe -price 4.00");
        MenuAddCommand.execute(newMenu,"add -item Fried Chicken -price 5.00");
        Order newOrder = new Order("abc restaurant", "abc street",
                "mr abc", "Takeaway");
        OrderAddCommand.execute(newOrder, "add -item 2 -quantity 2",newMenu);
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        OrderCompleteCommand.execute(newOrder, newScanner,"complete");
        String expectedOutput = "+-----------------------------------------------------+\n" +
                "|                       RECEIPT                       |\n" +
                "+-----------------------------------------------------+\n" +
                "|                    abc restaurant                   |\n" +
                "|                      abc street                     |\n" +
                "|                                                     |\n" +
                "| Order Type: Takeaway                                |\n" +
                "| Order ID: " + newOrder.getId() + "                            |\n" +
                "+-----------------------------------------------------+\n" +
                "| Item ID  | Name            | Unit Price  | Quantity |\n" +
                "+-----------------------------------------------------+\n" +
                "| 2        | Sundae          | $3.00       | 2        |\n" +
                "+-----------------------------------------------------+\n" +
                "| Subtotal:                                     $6.00 |\n" +
                "+-----------------------------------------------------+\n" +
                "| Service Charge (10.0%):                       $0.60 |\n" +
                "| GST (9.0%):                                   $0.59 |\n" +
                "| Grand Total:                                  $7.19 |\n" +
                "+-----------------------------------------------------+\n" +
                "| Cashier: mr abc                                     |\n" +
                "+-----------------------------------------------------+\n\n" +
                "WARNING: Once an order is completed, you are NOT ALLOWED to edit or delete it.\n" +
                "Do you want to complete the order? (type 'y' to complete, anything else to cancel)\n"+

                "Order " + newOrder.getId() + " is not completed.\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    @Test
    public void testOrderDeleteNonExistentItem() {
        Menu newMenu = new Menu("1");
        MenuAddCommand.execute(newMenu,"add -item Ice Cream -price 2.00");
        MenuAddCommand.execute(newMenu,"add -item Sundae -price 3.00");
        MenuAddCommand.execute(newMenu,"add -item Frappe -price 4.00");
        MenuAddCommand.execute(newMenu,"add -item Fried Chicken -price 5.00");
        Order newOrder = new Order("abc restaurant", "abc street",
                "mr abc", "Takeaway");
        OrderAddCommand.execute(newOrder, "add -item 2 -quantity 2",newMenu);
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        OrderDeleteCommand.execute(newOrder, "delete -item 5 -quantity 2",newMenu);
        String expectedOutput = "Item not found in order\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    @Test
    public void testOrderDeleteExcessExistentItem() {
        Menu newMenu = new Menu("1");
        MenuAddCommand.execute(newMenu,"add -item Ice Cream -price 2.00");
        MenuAddCommand.execute(newMenu,"add -item Sundae -price 3.00");
        MenuAddCommand.execute(newMenu,"add -item Frappe -price 4.00");
        MenuAddCommand.execute(newMenu,"add -item Fried Chicken -price 5.00");
        Order newOrder = new Order("abc restaurant", "abc street",
                "mr abc", "Takeaway");
        OrderAddCommand.execute(newOrder, "add -item 2 -quantity 2",newMenu);
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        OrderDeleteCommand.execute(newOrder, "delete -item 2 -quantity 7",newMenu);
        String expectedOutput = "All Sundae is removed from order\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    @Test
    public void testOrderDeletePartialExistentItem() {
        Menu newMenu = new Menu("1");
        MenuAddCommand.execute(newMenu,"add -item Ice Cream -price 2.00");
        MenuAddCommand.execute(newMenu,"add -item Sundae -price 3.00");
        MenuAddCommand.execute(newMenu,"add -item Frappe -price 4.00");
        MenuAddCommand.execute(newMenu,"add -item Fried Chicken -price 5.00");
        Order newOrder = new Order("abc restaurant", "abc street",
                "mr abc", "Takeaway");
        OrderAddCommand.execute(newOrder, "add -item 2 -quantity 2",newMenu);
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        OrderDeleteCommand.execute(newOrder, "delete -item 2 -quantity 1",newMenu);
        String expectedOutput = "1 Sundae is removed from order\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    @Test
    public void testOrderExitNonEmptyOrder() {
        Menu newMenu = new Menu("1");
        MenuAddCommand.execute(newMenu,"add -item Ice Cream -price 2.00");
        MenuAddCommand.execute(newMenu,"add -item Sundae -price 3.00");
        MenuAddCommand.execute(newMenu,"add -item Frappe -price 4.00");
        MenuAddCommand.execute(newMenu,"add -item Fried Chicken -price 5.00");
        Order newOrder = new Order("abc restaurant", "abc street",
                "mr abc", "Takeaway");
        OrderAddCommand.execute(newOrder, "add -item 2 -quantity 2",newMenu);
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        OrderExitCommand.execute(newOrder);
        String expectedOutput = "Order " + newOrder.getId() + " cancelled\n" + "Order not created\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    @Test
    public void testOrderExitEmptyOrder() {
        Menu newMenu = new Menu("1");
        MenuAddCommand.execute(newMenu,"add -item Ice Cream -price 2.00");
        MenuAddCommand.execute(newMenu,"add -item Sundae -price 3.00");
        MenuAddCommand.execute(newMenu,"add -item Frappe -price 4.00");
        MenuAddCommand.execute(newMenu,"add -item Fried Chicken -price 5.00");
        Order newOrder = new Order("abc restaurant", "abc street",
                "mr abc", "Takeaway");
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        OrderExitCommand.execute(newOrder);
        String expectedOutput = "Order " + newOrder.getId() + " cancelled\n" + "Order not created\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }
    @Test
    public void testOrderViewEmptyOrder() {
        Menu newMenu = new Menu("1");
        MenuAddCommand.execute(newMenu,"add -item Ice Cream -price 2.00");
        MenuAddCommand.execute(newMenu,"add -item Sundae -price 3.00");
        MenuAddCommand.execute(newMenu,"add -item Frappe -price 4.00");
        MenuAddCommand.execute(newMenu,"add -item Fried Chicken -price 5.00");
        Order newOrder = new Order("abc restaurant", "abc street",
                "mr abc", "Takeaway");
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        OrderViewItemsCommand.execute(newOrder);
        String expectedOutput = "Order is empty.\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    @Test
    public void testOrderViewNonEmptyOrder() {
        Menu newMenu = new Menu("1");
        MenuAddCommand.execute(newMenu,"add -item Ice Cream -price 2.00");
        MenuAddCommand.execute(newMenu,"add -item Sundae -price 3.00");
        MenuAddCommand.execute(newMenu,"add -item Frappe -price 4.00");
        MenuAddCommand.execute(newMenu,"add -item Fried Chicken -price 5.00");
        Order newOrder = new Order("abc restaurant", "abc street",
                "mr abc", "Takeaway");
        OrderAddCommand.execute(newOrder, "add -item 2 -quantity 2",newMenu);
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        OrderViewItemsCommand.execute(newOrder);
        String expectedOutput = "+-----------------------------------------------------+\n" +
                "|                    Current Order                    |\n" +
                "+-----------------------------------------------------+\n" +
                "| 2        | Sundae          | $3.00       | 2        |\n" +
                "+-----------------------------------------------------+\n" +
                "| Subtotal:                                     $6.00 |\n" +
                "+-----------------------------------------------------+\n" +
                "| Service Charge (10.0%):                       $0.60 |\n" +
                "| GST (9.0%):                                   $0.59 |\n" +
                "| Grand Total:                                  $7.19 |\n" +
                "+-----------------------------------------------------+\n" +
                "| Cashier: mr abc                                     |\n" +
                "+-----------------------------------------------------+\n\n" ;
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    @Test
    public void testOrderViewMenu() {
        Menu newMenu = new Menu("1");
        MenuAddCommand.execute(newMenu,"add -item Ice Cream -price 2.00");
        MenuAddCommand.execute(newMenu,"add -item Sundae -price 3.00");
        MenuAddCommand.execute(newMenu,"add -item Frappe -price 4.00");
        MenuAddCommand.execute(newMenu,"add -item Fried Chicken -price 5.00");

        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));

        String expectedOutput = "+------------------------------------------+\n" +
                                "|                   MENU                   |\n" +
                                "+------+-----------------------------------+\n" +
                                "| ID   |         Name          | Price     |\n" +
                                "+------+-----------------------------------+\n" +
                                "| 1    | Ice Cream             | $2.00     |\n" +
                                "| 2    | Sundae                | $3.00     |\n" +
                                "| 3    | Frappe                | $4.00     |\n" +
                                "| 4    | Fried Chicken         | $5.00     |\n" +
                                "+------+-----------------------------------+\n\n";

        OrderViewMenuCommand.execute(newMenu);
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

}
