package command;

import model.Order;
import model.OrdersList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest {
    @Test
    public void testHelpCommand() {
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));

        HelpCommand helpCommand = new HelpCommand();
        OrdersList ordersList = new OrdersList();
        Order order = new Order();
        helpCommand.execute(ordersList, order);

        String expectedOutput = """
                Here are the list of available commands:
                \thelp: Shows all the commands that can be used.
                \tcreate order -menu <menu_id>: Creates a new order using the specified menu.
                \tview -order -all: Shows a brief summary of all the created orders.
                \tview -order <order_id>: Shows all the contents of a specified order.
                \tedit -order <order_id>: Navigates to the order interface to perform sub-commands for editing an order.
                \tbye: Quits the program
                """;
        assertEquals(expectedOutput, outputContent.toString());
    }
}
