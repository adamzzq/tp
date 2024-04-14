package command;

import command.menu.MenuAddCommand;
import command.menu.MenuCompleteCommand;
import command.menu.MenuDeleteCommand;
import command.menu.MenuExitCommand;
import command.menu.MenuHelpCommand;
import model.Menu;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuCommandTest {
    @Test
    public void testMenuHelp() {
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        MenuHelpCommand.execute();
        String expectedOutput = "Here are the list of available commands:\n" +
                "\thelp: Shows all the commands that can be used.\n" +
                "\tadd -item <item_name> -price <price_of_item> : " +
                "Adds a new item to the menu.\n" +
                "\tdelete -item <item_id> : Deletes an item from the menu.\n" +
                "\tview items: Shows all the items in the current menu.\n" +
                "\tcomplete: Completes the menu and returns to the main menu.\n" +
                "\tcancel: Aborts the current menu and returns to the main menu.\n";

        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    @Test
    public void testMenuAddNewItem() {
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        Menu newMenu = new Menu("001");
        MenuAddCommand.execute(newMenu,"add -item Fried Chicken -price 5.00");
        String expectedOutput = "Item successfully added to menu!\n" +
                "+------------------------------------------+\n" +
                "|                   MENU                   |\n" +
                "+------+-----------------------------------+\n" +
                "| ID   |         Name          | Price     |\n" +
                "+------+-----------------------------------+\n" +
                "| 1    | Fried Chicken         | $5.00     |\n" +
                "+------+-----------------------------------+\n\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    @Test
    public void testMenuAddExistingItem() {
        Menu newMenu = new Menu("001");
        MenuAddCommand.execute(newMenu,"add -item Fried Chicken -price 5.00");
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        MenuAddCommand.execute(newMenu,"add -item Fried Chicken -price 5.00");
        String expectedOutput = "Item already in menu. It has ID: " +
                "1" + " and Name: " + "Fried Chicken\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    @Test
    public void testMenuDeleteNonexistentItem() {
        Menu newMenu = new Menu("001");
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        MenuDeleteCommand.execute(newMenu,"delete -item 7");
        String expectedOutput = "Item not found in menu\n" +
                "+------------------------------------------+\n" +
                "|                   MENU                   |\n" +
                "+------+-----------------------------------+\n" +
                "| ID   |         Name          | Price     |\n" +
                "+------+-----------------------------------+\n" +
                "+------+-----------------------------------+\n\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    @Test
    public void testMenuDeleteOneExistingItem() {
        Menu newMenu = new Menu("001");
        MenuAddCommand.execute(newMenu,"add -item Ice Cream -price 2.00");
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        MenuDeleteCommand.execute(newMenu,"delete -item 1");
        String expectedOutput = "Ice Cream is removed from menu\n" +
                "+------------------------------------------+\n" +
                "|                   MENU                   |\n" +
                "+------+-----------------------------------+\n" +
                "| ID   |         Name          | Price     |\n" +
                "+------+-----------------------------------+\n" +
                "+------+-----------------------------------+\n\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    /*@Test
    public void testMenuDeleteMiddleExistingItem() {
        Menu newMenu = new Menu("001");
        MenuAddCommand.execute(newMenu,"add -item Ice Cream -price 2.00");
        MenuAddCommand.execute(newMenu,"add -item Sundae -price 3.00");
        MenuAddCommand.execute(newMenu,"add -item Frappe -price 4.00");
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        MenuDeleteCommand.execute(newMenu,"delete -item 2");
        String expectedOutput = "Sundae is removed from menu\n" +
                "+------------------------------------------+\n" +
                "|                   MENU                   |\n" +
                "+------+-----------------------------------+\n" +
                "| ID   |         Name          |   Price   |\n" +
                "+------+-----------------------------------+\n" +
                "| 1    | Ice Cream             |$2.00      |\n" +
                "| 2    | Frappe                |$4.00      |\n" +
                "+------+-----------------------------------+\n\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }*/
    @Test
    public void testMenuCompleteEmptyMenu() {
        Menu newMenu = new Menu("001");
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        MenuCompleteCommand.execute(newMenu);
        String expectedOutput = "Menu 001 is empty. Please add items to the menu.\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }
    @Test
    public void testMenuCompleteNonEmptyMenu() {
        Menu newMenu = new Menu("001");
        MenuAddCommand.execute(newMenu,"add -item Ice Cream -price 2.00");
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        MenuCompleteCommand.execute(newMenu);
        String expectedOutput = "Menu 001 has been saved!\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    @Test
    public void menuExitEmptyMenu() {
        Menu newMenu = new Menu("001");
        MenuAddCommand.execute(newMenu,"add -item Ice Cream -price 2.00");
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        MenuExitCommand.execute(newMenu);
        String expectedOutput = "Menu 001 cancelled\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }

    @Test
    public void menuExitNonEmptyMenu() {
        Menu newMenu = new Menu("001");
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        MenuExitCommand.execute(newMenu);
        String expectedOutput = "Menu 001 cancelled\n";
        assertEquals(expectedOutput,outputContent.toString().replace("\r",""));
    }
}
