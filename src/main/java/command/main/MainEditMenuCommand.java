package command.main;

import logic.MenuLogic;
import model.Menu;
import model.MenuItem;
import storage.Storage;
import ui.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class MainEditMenuCommand implements MainCommand{

    public static Optional<Menu> execute(Scanner input, String inputText, ArrayList<Menu> menusList) {
        String[] parsedString = Parser.splitInput(Parser.analyzeInput(inputText), inputText);
        String menuID = parsedString[0];
        Optional<Menu> menuToEdit = menusList.stream().filter(menu -> menu.getId().equals(menuID)).findAny();
        if (menuToEdit.isPresent()) {
            Menu copymenu = new Menu(menuToEdit.get().getId());
            for (MenuItem menuItem : menuToEdit.get().getMenuItemList()) {
                copymenu.add(menuItem);
            }
            MenuLogic.modifyMenu(input, copymenu, menusList.toArray().length).ifPresent(menu -> {
                int ogIndex = menusList.indexOf(menuToEdit.get());
                menusList.remove(ogIndex);
                menusList.add(ogIndex, copymenu);
                try {
                    Storage.updateMenus(menusList);
                } catch (IOException e) {
                    System.out.println("Error updating menus save file.");
                }
            });
            return Optional.of(copymenu);
        }
        return Optional.empty();
    }
}
