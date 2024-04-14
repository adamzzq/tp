package command;

import command.main.MainHelpCommand;
import command.main.MainExitCommand;
import command.main.MainViewOrderCommand;
import command.main.MainViewOrdersSummaryCommand;
import command.main.MainCreateOrderCommand;
import command.main.MainEditMenuCommand;
import command.main.MainViewMenuCommand;
import command.main.MainViewRestaurantInfoCommand;
import command.main.MainViewMenusSummaryCommand;
import command.main.MainReceiptOrderCommand;
import model.Menu;
import model.MenuItem;
import model.Order;
import model.Restaurant;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainCommandTest {
    private Menu generateMenu() {
        MenuItem dish1 = new MenuItem("1", "Burger", 6.00);
        MenuItem dish2 = new MenuItem("2", "Fries", 3.00);
        MenuItem dish3 = new MenuItem("3", "Sandwich", 4.00);

        Menu testMenu = new Menu("1");
        testMenu.add(dish1);
        testMenu.add(dish2);
        testMenu.add(dish3);
        return testMenu;
    }

    private Restaurant generateRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName("Pho King");
        restaurant.setRestaurantAddress("NUS");
        return restaurant;
    }

    private Order generateOrder() {
        MenuItem dish1 = new MenuItem("1", "Burger", 6.00);
        MenuItem dish2 = new MenuItem("2", "Fries", 3.00);
        MenuItem dish3 = new MenuItem("3", "Sandwich", 4.00);
        Restaurant testRestaurant = generateRestaurant();
        Order testOrder = new Order(testRestaurant.getRestaurantName(), testRestaurant.getRestaurantAddress(),
                "John", "Takeaway");
        testOrder.setOrderID("12345");
        testOrder.add(dish1);
        testOrder.add(dish2);
        testOrder.add(dish3);
        return testOrder;
    }

    @Test
    public void testMainHelpCommand() {
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));

        MainHelpCommand.execute();
        String expectedOutput = "Here are the list of available commands:\n" +
                "\thelp: Shows all the commands that can be used.\n" +
                "\tcreate order -menu <menu_id>: Creates a new order using the specified menu.\n" +
                "\tview -order -all: Shows a brief summary of all the created orders.\n" +
                "\tview -order <order_id>: Shows all the contents of a specified order.\n" +
                "\treceipt -order <order_id>: shows the receipt of the specified order.\n" +
                "\tcreate menu: Creates a new menu.\n" +
                "\tedit -menu <menu_id>: Modify the specified menu's items in the menu interface.\n" +
                "\tview -menu <menu_id>: Shows all the contents of a specified menu.\n" +
                "\tview -menu -all: Shows a brief summary of all the created menus.\n" +
                "\tedit restaurant: Modify restaurant name and address.\n" +
                "\tview restaurant: Shows the restaurant name and address currently in use.\n" +
                "\tview performance: Enters the order statistics interface.\n" +
                "\tbye: Quits the program.\n";

        assertEquals(expectedOutput, outputContent.toString().replace("\r", ""));
    }

    @Test
    public void testMainExitCommand() {
        assertTrue(MainExitCommand.execute(false));
    }


    @Test
    public void testMainViewOrdersSummaryCommand_emptyList() {
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));

        ArrayList<Order> emptyList = new ArrayList<>();
        MainViewOrdersSummaryCommand.execute(emptyList);
        String expectedOutput = "No orders available\n";

        assertEquals(expectedOutput, outputContent.toString().replace("\r", ""));
    }

    @Test
    public void testMainViewOrderCommand_invalidOrder() {
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));

        ArrayList<Order> emptyList = new ArrayList<>();
        MainViewOrderCommand.execute(emptyList, "view -order 12345");
        String expectedOutput = "Order not found\n";

        assertEquals(expectedOutput, outputContent.toString().replace("\r", ""));
    }

    @Test
    public void testMainCreateOrderCommand_invalidMenu() {
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));

        Menu testMenu = new Menu("01");
        ArrayList<Menu> testMenuList = new ArrayList<>();
        testMenuList.add(testMenu);
        MainCreateOrderCommand.execute("create order -menu 02", testMenuList);
        String expectedOutput = "Menu does not exist\n" + "Order not created\n";

        assertEquals(expectedOutput, outputContent.toString().replace("\r", ""));
    }

    @Test
    public void testMainEditMenuCommand_invalidMenu() {
        ByteArrayInputStream inputContent = new ByteArrayInputStream("test".getBytes());
        Scanner input = new Scanner(inputContent);
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));

        Menu testMenu = new Menu("1");
        ArrayList<Menu> testMenuList = new ArrayList<>();
        testMenuList.add(testMenu);
        MainEditMenuCommand.execute(input, "edit -menu 2", testMenuList);
        String expectedOutput = "Menu ID not found\n";

        assertEquals(expectedOutput, outputContent.toString().replace("\r", ""));
    }

    @Test
    public void testMainEditMenuCommand_validMenu() {
        String commands = "add -item Pizza -price 6\n" + "complete\n";
        ByteArrayInputStream inputContent = new ByteArrayInputStream(commands.getBytes());
        Scanner input = new Scanner(inputContent);
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));

        ArrayList<Menu> testMenuList = new ArrayList<>();
        testMenuList.add(generateMenu());
        MainEditMenuCommand.execute(input, "edit -menu 1", testMenuList);
        String expectedOutput = "Initializing menu 1...\n" +
                "Here are the list of available commands:\n" +
                "\thelp: Shows all the commands that can be used.\n" +
                "\tadd -item <item_name> -price <price_of_item> : Adds a new item to the menu.\n" +
                "\tdelete -item <item_id> : Deletes an item from the menu.\n" +
                "\tview items: Shows all the items in the current menu.\n" +
                "\tcomplete: Completes the menu and returns to the main menu.\n" +
                "\tcancel: Aborts the current menu and returns to the main menu.\n" +
                "[Menu: 1] >>> Item successfully added to menu!\n" +
                "+------------------------------------------+\n" +
                "|                   MENU                   |\n" +
                "+------+-----------------------------------+\n" +
                "| ID   |         Name          | Price     |\n" +
                "+------+-----------------------------------+\n" +
                "| 1    | Burger                | $6.00     |\n" +
                "| 2    | Fries                 | $3.00     |\n" +
                "| 3    | Sandwich              | $4.00     |\n" +
                "| 4    | Pizza                 | $6.00     |\n" +
                "+------+-----------------------------------+\n" +
                "\n" +
                "[Menu: 1] >>> Menu 1 has been saved!\n";

        assertEquals(expectedOutput, outputContent.toString().replace("\r", ""));
    }

    @Test
    public void testMainViewMenuCommand_validMenu() {
        ArrayList<Menu> testMenuList = new ArrayList<>();
        testMenuList.add(generateMenu());

        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));

        MainViewMenuCommand.execute(testMenuList, "view -menu 1");
        String expectedOutput = "+------------------------------------------+\n" +
                                "|                   MENU                   |\n" +
                                "+------+-----------------------------------+\n" +
                                "| ID   |         Name          | Price     |\n" +
                                "+------+-----------------------------------+\n" +
                                "| 1    | Burger                | $6.00     |\n" +
                                "| 2    | Fries                 | $3.00     |\n" +
                                "| 3    | Sandwich              | $4.00     |\n" +
                                "+------+-----------------------------------+\n\n";
        assertEquals(expectedOutput, outputContent.toString().replace("\r", ""));
    }

    @Test
    public void testMainViewCommand_invalidMenu() {
        ArrayList<Menu> testMenuList = new ArrayList<>();
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));

        MainViewMenuCommand.execute(testMenuList, "view -menu 1");
        String expectedOutput = "Menu not found\n";
        assertEquals(expectedOutput, outputContent.toString().replace("\r", ""));
    }

    @Test
    public void testMainViewMenusSummaryCommand_emptyMenusList() {
        ArrayList<Menu> testMenuList = new ArrayList<>();
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));

        MainViewMenusSummaryCommand.execute(testMenuList);
        String expectedOutput = "No menus available\n";
        assertEquals(expectedOutput, outputContent.toString().replace("\r", ""));
    }

    @Test
    public void testMainViewRestaurantInfoCommand() {
        Restaurant testRestaurant = generateRestaurant();

        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        MainViewRestaurantInfoCommand.execute(testRestaurant);
        String expectedOutput = "Restaurant name: Pho King\n" + "Restaurant address: NUS\n";
        assertEquals(expectedOutput, outputContent.toString().replace("\r", ""));
    }

    @Test
    public void testMainReceiptOrderCommand_invalidOrder() {
        ArrayList<Order> testOrdersList = new ArrayList<>();
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));

        MainReceiptOrderCommand.execute(testOrdersList, "receipt -order 2024123123");
        String expectedOutput = "Order not found\n";
        assertEquals(expectedOutput, outputContent.toString().replace("\r", ""));
    }

    @Test
    public void testMainReciptOrderCommand_validOrder() {
        ArrayList<Order> testOrdersList = new ArrayList<>();
        testOrdersList.add(generateOrder());
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));

        MainReceiptOrderCommand.execute(testOrdersList, "receipt -order 12345");
        String expectedOutput = "+-----------------------------------------------------+\n" +
                "|                       RECEIPT                       |\n" +
                "+-----------------------------------------------------+\n" +
                "|                       Pho King                      |\n" +
                "|                         NUS                         |\n" +
                "|                                                     |\n" +
                "| Order Type: Takeaway                                |\n" +
                "| Order ID: 12345                                     |\n" +
                "+-----------------------------------------------------+\n" +
                "| Item ID  | Name            | Unit Price  | Quantity |\n" +
                "+-----------------------------------------------------+\n" +
                "| 1        | Burger          | $6.00       | 1        |\n" +
                "| 2        | Fries           | $3.00       | 1        |\n" +
                "| 3        | Sandwich        | $4.00       | 1        |\n" +
                "+-----------------------------------------------------+\n" +
                "| Subtotal:                                    $13.00 |\n" +
                "+-----------------------------------------------------+\n" +
                "| Service Charge (10.0%):                      $1.30  |\n" +
                "| GST (9.0%):                                  $1.29  |\n" +
                "| Grand Total:                                 $15.59 |\n" +
                "+-----------------------------------------------------+\n" +
                "| Cashier: John                                       |\n" +
                "+-----------------------------------------------------+\n" +
                "\n";
        assertEquals(expectedOutput, outputContent.toString().replace("\r", ""));
    }
}
