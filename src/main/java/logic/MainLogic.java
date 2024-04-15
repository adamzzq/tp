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
import command.main.MainViewRestaurantInfoCommand;
import command.main.MainEditRestaurantInfoCommand;


import model.Menu;
import model.Order;
import model.Restaurant;
import storage.Storage;
import ui.CommandErrorMessage;
import ui.CommandType;
import ui.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

public class MainLogic {
    private static final int MAX_NAME_LENGTH = 50;
    private static String userName;

    public static void main(String[] args) {
        // Set default locale to US to ensure consistent formatting
        Locale.setDefault(Locale.US);

        //Initialise all required models
        System.out.println("Hello from DinEz");
        Scanner input = new Scanner(System.in);
        Restaurant restaurant = new Restaurant();
        ArrayList<Order> ordersList = new ArrayList<>();
        ArrayList<Menu> menusList = new ArrayList<>();

        boolean isRestaurantLoaded = false;
        try {
            isRestaurantLoaded = Storage.checkRestaurantData(restaurant);
        } catch (IOException | SecurityException e) {
            System.out.println("Error creating save files, data might not be saved.");
        }

        if (!isRestaurantLoaded) {
            restaurant.initRestaurant();
            Storage.saveRestaurant(restaurant);
        }
        Storage.loadData(ordersList, menusList);

        boolean isValidUser = false;

        while (!isValidUser) {
            isValidUser = initializeUser();
        }

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
                menuSelected.flatMap(menu -> OrderLogic.createNewOrder(input, menu,
                        restaurant.getRestaurantName(), restaurant.getRestaurantAddress(), userName))
                        .ifPresent(order -> {
                            ordersList.add(order);
                            Storage.saveOrder(order);
                        });
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
                        .ifPresentOrElse(menu -> {
                            menusList.add(menu);
                            Storage.saveMenu(menu);
                        }, () -> System.out.println("Menu not created"));
                break;
            case EDIT_MENU:
                MainEditMenuCommand.execute(input, inputText, menusList);
                break;
            case VIEW_MENU:
                MainViewMenuCommand.execute(menusList, inputText);
                break;
            case VIEW_ALL_MENUS:
                MainViewMenusSummaryCommand.execute(menusList);
                break;
            case EDIT_RESTAURANT_INFO:
                MainEditRestaurantInfoCommand.execute(restaurant);
                break;
            case VIEW_RESTAURANT_INFO:
                MainViewRestaurantInfoCommand.execute(restaurant);
                break;
            case VIEW_PERFORMANCE_INFO:
                StatsLogic.showStats(input, ordersList);
                break;
            default:
                CommandErrorMessage.printMainError(inputText);
            }
        }
    }

    /**
     * Upon entering the system, the user is prompted to enter details about the restaurant including
     * details like restaurant name, restaurant address as well as username. This function handles the
     * prompting for input and receiving the input from the user
     * @return true if the input was valid, false otherwise
     */
    private static boolean initializeUser() {
        System.out.println("Enter user name: ");

        Scanner input = new Scanner(System.in);
        String inputString= input.nextLine();
        int length = inputString.length();
        if (length > MAX_NAME_LENGTH) {
            System.out.println("Name is too long! Please enter a name with less than 50 characters.");
            return false;
        }

        if (inputString.isBlank() || inputString.isEmpty()) {
            System.out.println("Input cannot be empty!");
            return false;
        } else {
            userName = inputString;
            return true;
        }
    }
}
