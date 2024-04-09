package logic;

import command.order.OrderViewItemsCommand;
import command.order.OrderAddCommand;
import command.order.OrderCompleteCommand;
import command.order.OrderDeleteCommand;
import command.order.OrderExitCommand;
import command.order.OrderHelpCommand;
import command.order.OrderViewMenuCommand;

import model.Menu;
import model.Order;
import ui.CommandErrorMessage;
import ui.CommandType;
import ui.Parser;

import java.util.Optional;
import java.util.Scanner;

public class OrderLogic {
    private static String orderType;
    public static Optional<Order> createNewOrder(Scanner input, Menu menu, String restaurantName,
                                                 String restaurantAddress, String userName) {
        try {
            initializeOrderType();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input to order type."); //can handle this with a command
        }

        Order newOrder = new Order(restaurantName,restaurantAddress,userName,orderType);
        boolean isComplete = false;
        String orderID = newOrder.getId();

        System.out.println("Order " + orderID + " creating...");
        OrderHelpCommand.execute();
        while (!isComplete) {
            System.out.print("[Order: " + orderID + "] [Menu: " + menu.getId() + "] >>> ");
            String inputText = input.nextLine();
            CommandType commandType;
            try {
                commandType = Parser.analyzeInput(inputText);
            } catch (IllegalArgumentException e) {
                CommandErrorMessage.printOrderError(inputText);
                continue;
            }
            switch (commandType) {
            case ADD_ITEM:
                newOrder = OrderAddCommand.execute(newOrder, inputText, menu);
                break;
            case DELETE_ITEM:
                newOrder = OrderDeleteCommand.execute(newOrder, inputText, menu);
                break;
            case VIEW_ITEM:
                OrderViewItemsCommand.execute(newOrder);
                break;
            case VIEW_MENU_ORDERLOGIC:
                OrderViewMenuCommand.execute(menu);
                break;
            case HELP:
                OrderHelpCommand.execute();
                break;
            case COMPLETE:
                isComplete = OrderCompleteCommand.execute(newOrder, input);
                break;
            case EXIT:
                OrderExitCommand.execute(newOrder);
                return Optional.empty();
            default:
                System.out.println("Invalid command");
                OrderHelpCommand.execute();
            }
        }
        return Optional.of(newOrder);
    }

    private static void initializeOrderType() {
        System.out.println("Would you like your order to be\n " +
                "   1) dine in\n" +
                "    2) takeaway\n" +
                "Please enter 1 or 2: ");
        Scanner orderTypeInput= new Scanner(System.in);
        String orderTypeUserInput = orderTypeInput.nextLine();

        if (orderTypeUserInput.trim().equals("1")) {
            orderType = "Dine in";
        } else if (orderTypeUserInput.trim().equals("2")) {
            orderType = "Takeaway";
        } else {
            throw new IllegalArgumentException("Input not 1 or 2!");
        }
    }
}
