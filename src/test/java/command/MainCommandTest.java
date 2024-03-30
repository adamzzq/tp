package command;

import command.main.MainExitCommand;
import command.main.MainHelpCommand;
import command.main.MainViewOrderCommand;
import command.main.MainViewOrdersSummaryCommand;
import command.main.MainCreateOrderCommand;
import command.main.MainEditMenuCommand;
import model.Menu;
import model.Order;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainCommandTest {

    @Test
    public void testMainExitCommand() {
        assertTrue(MainExitCommand.execute(false));
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
                "\tcreate menu: Creates a new menu.\n" +
                "\tedit -menu <menu_id>: Modify the specified menu's items in the menu interface.\n" +
                "\tbye: Quits the program.\n";

        assertEquals(expectedOutput, outputContent.toString().replace("\r", ""));
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
        Menu testMenu = new Menu("01");
        ArrayList<Menu> testMenuList = new ArrayList<>();
        testMenuList.add(testMenu);
        Optional<Menu> menuToEdit = MainEditMenuCommand.execute("edit -menu 00", testMenuList);

        assertTrue(menuToEdit.isEmpty());
    }
}
