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
        System.out.println("Initializing menu " + activeMenu.getId() + "...");
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
            if (commandType == CommandType.ADD_MENU_ITEM || commandType == CommandType.DELETE_MENU_ITEM) {
                activeMenu = editMenu(commandType,activeMenu,inputText);
            } else if (commandType == CommandType.COMPLETE) {
                isComplete = MenuCompleteCommand.execute(activeMenu);
            } else if (commandType == CommandType.HELP) {
                MenuHelpCommand.execute();
            } else if (commandType == CommandType.EXIT) {
                MenuExitCommand.execute(activeMenu);
                return Optional.empty();
            } else {
                System.out.println("Invalid command");
                MenuHelpCommand.execute();
            }
        }
        return Optional.of(activeMenu);
    }
    private static Menu editMenu(CommandType commandType, Menu activeMenu, String inputText) {
        if (commandType == CommandType.ADD_MENU_ITEM) {
            return MenuAddCommand.execute(activeMenu, inputText);
        } else {
            return MenuDeleteCommand.execute(activeMenu, inputText);
        }
    }
}
