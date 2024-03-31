package model;

import java.util.ArrayList;

import java.util.Optional;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.FileHandler;

import static model.SetMenu.Breakfast;
import static model.SetMenu.Lunch;
import static model.SetMenu.Dinner;

public class Menu implements ItemManager {
    private static final Logger logr = Logger.getLogger("MenuLogger");
    private final ArrayList<MenuItem> menuItemList = new ArrayList<>();
    private final String menuId;

    public Menu(String menuId) {
        Menu.setupLogger();
        this.menuId = menuId;
    }

    public Menu(SetMenu menuType) {
        Menu.setupLogger();
        switch (menuType) {
        case Breakfast:
            this.menuId = String.valueOf(Breakfast);
            break;
        case Lunch:
            this.menuId = String.valueOf(Lunch);
            break;
        case Dinner:
            this.menuId = String.valueOf(Dinner);
            break;
        default:
            this.menuId = "No Menu type";
        }
    }

    public Optional<MenuItem> getItemById(String itemID) {
        return menuItemList.stream().filter(x -> x.getID().equals(itemID)).findAny();
    }

    public Optional<MenuItem> getItemByName(String itemName) {
        return menuItemList.stream().filter(x -> x.getName().equals(itemName)).findAny();
    }

    @Override
    public String getId() {
        return menuId;
    }
    @Override
    public boolean add(MenuItem item) {
        this.menuItemList.add(item);
        return true;
    }


    /**
     * Removes item from the menu by its name
     * @param itemID The name of the item
     */
    @Override
    public boolean remove(String itemID) {
        assert itemID != null: "itemID of item to be removed should not be null";
        this.menuItemList.removeIf(x -> x.getID().equals(itemID));
        return true;
    }

    /*@Override
    public String toString() {
        return this.menuID + "\n" +
                IntStream.range(0,this.menuItemList.size())
                        .mapToObj(x -> (x + 1) + ". " + this.menuItemList.get(x))
                        .collect(Collectors.joining("\n"));
    }*/

    @Override
    public String toString() {
        StringBuilder menuString = new StringBuilder();
        menuString.append("+--------------------------------------+\n");
        menuString.append("|              MENU                    |\n");
        menuString.append("+------+-------------------------------+\n");
        menuString.append("| ID   | Name                  | Price |\n");
        menuString.append("+------+-------------------------------+\n");
        if (menuItemList.isEmpty()) {
            menuString.append("+------+-------------------------------+\n");
        } else {
            for (MenuItem item : menuItemList) {
                menuString.append(String.format("| %-5s| %-22s| $%-5.2f|\n",
                        item.getID(), item.getName(), item.getPrice()));
            }
            menuString.append("+------+-------------------------------+\n");
        }

        return menuString.toString();
    }

    /**
     * Set up logger for this class. It has two handlers, one FileHandler and one ConsoleHandler
     * FileHandler records log messages from FINE and above
     * ConsoleHandler only records SEVERE messages
     */
    private static void setupLogger() {
        LogManager.getLogManager().reset();
        logr.setLevel(Level.ALL);

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        logr.addHandler(ch);

        try {
            FileHandler fh = new FileHandler("MenuLogger.log");
            fh.setLevel(Level.FINE);
            logr.addHandler(fh);
        } catch (java.io.IOException e) {
            logr.log(Level.SEVERE, "File logger not working.",e);
        }
    }


    public int getSize() {
        return menuItemList.size();
    }

}
