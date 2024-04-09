package logic;

import command.main.MainExitCommand;
import command.main.MainHelpCommand;
import command.main.MainCreateOrderCommand;
import command.main.MainViewOrderCommand;
import command.main.MainViewOrdersSummaryCommand;
import command.main.MainReceiptOrderCommand;
import command.main.MainEditMenuCommand;
import command.main.MainViewMenuCommand;
import command.main.MainViewMenusSummaryCommand;


import model.Menu;
import model.MenuItem;
import model.Order;
import ui.CommandErrorMessage;
import ui.CommandType;
import ui.Parser;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class MainLogic {
    public static void main(String[] args) {
        //Initialise all required models
        System.out.println("Hello from DinEz");
        Scanner input = new Scanner(System.in);
        ArrayList<Order> ordersList = new ArrayList<>();
        ArrayList<Menu> menusList = new ArrayList<>();

        //testing
        initMenu(menusList);
        System.out.println("current menu ID: " + menusList.get(0).getId());
        testOrderAddAndRemove(ordersList);
        testOrderAddAndRemove(ordersList);

        MainHelpCommand.execute();
        boolean isExit = false;
        while (!isExit) {
            System.out.print("[Main interface] >>> ");
            String inputText = input.nextLine();
            CommandType commandType;
            try {
                commandType = Parser.analyzeInput(inputText);
            } catch (IllegalArgumentException e) {
                CommandErrorMessage.printMainError(inputText);
                continue;
            }
            switch (commandType) {
            case EXIT_MAIN:
                isExit = MainExitCommand.execute(isExit);
                assert isExit : "isExit should be true";
                break;
            case HELP:
                MainHelpCommand.execute();
                break;
            case CREATE_ORDER:
                //GOTO sub-menu to add/remove menuItems, inputText is passed to detect menu selected
                Optional<Menu> menuSelected = MainCreateOrderCommand.execute(inputText, menusList);
                menuSelected.flatMap(menu -> OrderLogic.createNewOrder(input, menu)).ifPresent(ordersList::add);
                break;
            case VIEW_ORDER:
                MainViewOrderCommand.execute(ordersList, inputText);
                break;
            case VIEW_ALL_ORDERS:
                MainViewOrdersSummaryCommand.execute(ordersList);
                break;
            case ORDER_RECEIPT:
                MainReceiptOrderCommand.execute(ordersList, inputText);
                break;
            case CREATE_MENU:
                MenuLogic.modifyMenu(input, null, menusList.toArray().length)
                        .ifPresentOrElse(menusList::add, () -> System.out.println("Menu not created"));
                break;
            case EDIT_MENU:
                Optional<Menu> menuToEdit = MainEditMenuCommand.execute(inputText, menusList);
                menuToEdit.ifPresentOrElse(menu -> MenuLogic.modifyMenu(input, menu, menusList.toArray().length),
                        () -> System.out.println("Menu ID not found"));
                break;
            case VIEW_MENU:
                MainViewMenuCommand.execute(menusList, inputText);
                break;
            case VIEW_ALL_MENUS:
                MainViewMenusSummaryCommand.execute(menusList);
                break;
            default:
                CommandErrorMessage.printMainError(inputText);
            }
        }
    }

    private static void initMenu(ArrayList<Menu> menusList) {
        MenuItem dish01 = new MenuItem("001", "Chicken Rice", 3.50);
        MenuItem dish02 = new MenuItem("002", "Nasi Lemak", 3.00);
        MenuItem dish03 = new MenuItem("003", "Hokkien Mee", 4.00);
        MenuItem dish04 = new MenuItem("004", "Mee Siam", 3.50);
        MenuItem dish05 = new MenuItem("005", "Fishball Noodles", 3.00);
        MenuItem dish06 = new MenuItem("006", "Chicken Curry Rice", 5.00);
        MenuItem dish07 = new MenuItem("007", "Seafood Fried Rice", 5.50);
        MenuItem dish08 = new MenuItem("008", "Roasted delight set", 6.50);
        MenuItem dish09 = new MenuItem("009", "Hotplate beef set", 7.00);
        MenuItem dish10 = new MenuItem("010", "Kimchi noodles", 4.00);

        Menu menuV1 = new Menu("01");
        menuV1.add(dish01);
        menuV1.add(dish02);
        menuV1.add(dish03);
        menuV1.add(dish04);
        menuV1.add(dish05);
        menuV1.add(dish06);
        menuV1.add(dish07);
        menuV1.add(dish08);
        menuV1.add(dish09);
        menuV1.add(dish10);
        menusList.add(menuV1);

    }

    public static void testOrderAddAndRemove(ArrayList<Order> ordersList) {
        MenuItem dish01 = new MenuItem("D01", "Chicken Rice", 3.50);
        MenuItem dish02 = new MenuItem("D02", "Nasi Lemak", 3.00);
        MenuItem dish04 = new MenuItem("D04", "Mee Siam", 3.50);
        MenuItem dish05 = new MenuItem("D04", "Mee Siam", 3.50);
        Order order = new Order();
        order.add(dish01);
        order.add(dish02);
        order.add(dish04);
        order.add(dish05);
        ordersList.add(order);
    }
}
