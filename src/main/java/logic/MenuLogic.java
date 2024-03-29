package logic;

import command.MenuAddCommand;
import command.MenuHelpCommand;
import command.OrderHelpCommand;
import model.Menu;
import ui.CommandType;
import ui.Parser;

import java.util.Optional;
import java.util.Scanner;


//import static ui.CommandType.HELP;

public class MenuLogic {
    public static Optional<Menu> createNewMenu(Scanner input) {
        Menu newMenu = new Menu(String.valueOf(Menu.getMenuNum()));
        boolean isComplete = false;
        System.out.println("Initializing menu" + newMenu.getID() + "processing...");
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
            case ADD_ITEM:
                newMenu = MenuAddCommand.execute(newMenu, inputText);
                break;
            case DELETE_ITEM:
                //newMenu = MenuDeleteCommand.execute(newMenu, inputText);
                break;
            case VIEW_MENU_LIST:
                //MenuViewMenuListCommand.execute();
                break;
            case COMPLETE_MENU:
                //isComplete = MenuCompleteCommand.execute(newMenu);
                break;
            //case HELP:
                //MenuHelpCommand.execute();
            case EXIT:
                //MenuExitcommand.execute(newMenu);
                return Optional.empty();
            default:
                System.out.println("Invalid command");
                OrderHelpCommand.execute();
            }
        }
        return Optional.of(newMenu);
    }
}
