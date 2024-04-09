package command;

import command.main.MainExitCommand;
import command.main.MainViewOrderCommand;
import command.main.MainViewOrdersSummaryCommand;
import command.main.MainCreateOrderCommand;
import command.main.MainEditMenuCommand;
import model.Menu;
import model.Order;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainCommandTest {

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
        Menu testMenu = new Menu("01");
        ArrayList<Menu> testMenuList = new ArrayList<>();
        testMenuList.add(testMenu);
        Optional<Menu> menuToEdit = MainEditMenuCommand.execute(input, "edit -menu 02", testMenuList);

        assertTrue(menuToEdit.isEmpty());
    }
}
