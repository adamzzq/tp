package logic;

import command.menu.MenuAddCommand;
import command.menu.MenuCompleteCommand;
import command.menu.MenuDeleteCommand;
import command.menu.MenuExitCommand;
import command.menu.MenuHelpCommand;
import model.Menu;
import ui.CommandType;
import ui.Parser;

import java.util.Optional;
import java.util.Scanner;


public class MenuLogic {
    public static Optional<Menu> modifyMenu(Scanner input, Menu menu, int menuLen) {
        Menu activeMenu = (menu == null) ? new Menu("0" + (menuLen + 1)) : menu;

        boolean isComplete = false;
        System.out.println("Initializing menu " + activeMenu.getID() + "...");
        MenuHelpCommand.execute();
        while (!isComplete) {
            String inputText = input.nextLine();
            CommandType commandType;
            try {
                commandType = Parser.analyzeInput(inputText);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid command");
                continue;
            }
            switch(commandType) {
            case ADD_MENU_ITEM:
                activeMenu = MenuAddCommand.execute(activeMenu, inputText);
                break;
            case DELETE_MENU_ITEM:
                activeMenu = MenuDeleteCommand.execute(activeMenu, inputText);
                break;
            case COMPLETE_MENU:
                isComplete = MenuCompleteCommand.execute(activeMenu);
                break;
            case HELP:
                MenuHelpCommand.execute();
                break;
            case EXIT:
                MenuExitCommand.execute(activeMenu);
                return Optional.empty();
            default:
                System.out.println("Invalid command");
                MenuHelpCommand.execute();
            }
        }
        return Optional.of(activeMenu);
    }
}
