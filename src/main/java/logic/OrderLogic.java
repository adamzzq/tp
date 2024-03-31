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
import ui.CommandType;
import ui.Parser;

import java.util.Optional;
import java.util.Scanner;

public class OrderLogic {
    public static Optional<Order> createNewOrder(Scanner input, Menu menu) {
        Order newOrder = new Order();
        boolean isComplete = false;
        System.out.println("Order " + newOrder.getID() + " creating...");
        OrderHelpCommand.execute();
        while (!isComplete) {
            String inputText = input.nextLine();
            CommandType commandType;
            try {
                commandType = Parser.analyzeInput(inputText);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid command");
                OrderHelpCommand.execute();
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
                isComplete = OrderCompleteCommand.execute(newOrder);
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
}
