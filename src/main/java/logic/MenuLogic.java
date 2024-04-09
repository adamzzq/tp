package logic;

import command.menu.MenuAddCommand;
import command.menu.MenuCompleteCommand;
import command.menu.MenuDeleteCommand;
import command.menu.MenuExitCommand;
import command.menu.MenuHelpCommand;
import model.Menu;
import ui.CommandErrorMessage;
import ui.CommandType;
import ui.Parser;

import java.util.Optional;
import java.util.Scanner;


public class MenuLogic {
    public static Optional<Menu> modifyMenu(Scanner input, Menu menu, int menuLen) {
        Menu activeMenu = (menu == null) ? new Menu("0" + (menuLen + 1)) : menu;
        String menuID = activeMenu.getId();
        boolean isComplete = false;
        System.out.println("Initializing menu " + menuID + "...");
        MenuHelpCommand.execute();
        while (!isComplete) {
            System.out.print("[Menu: " + menuID + "] >>> ");
            String inputText = input.nextLine();
            CommandType commandType;
            try {
                commandType = Parser.analyzeInput(inputText);
            } catch (IllegalArgumentException e) {
                CommandErrorMessage.printMenuError(inputText);
                continue;
            }
            switch(commandType) {
            case ADD_MENU_ITEM:
                MenuAddCommand.execute(activeMenu, inputText);
                break;
            case DELETE_MENU_ITEM:
                MenuDeleteCommand.execute(activeMenu, inputText);
                break;
            case VIEW_ITEM:
                System.out.println(activeMenu);
                break;
            case COMPLETE:
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
