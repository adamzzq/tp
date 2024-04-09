package model;

import java.util.ArrayList;

import java.util.Optional;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.FileHandler;

public class Menu implements ItemManager {
    private static final int NAME_MAX_LENGTH = 22;
    private static final Logger logr = Logger.getLogger("MenuLogger");
    private final ArrayList<MenuItem> menuItemList = new ArrayList<>();
    private final String menuID;

    public Menu(String menuID) {
        Menu.setupLogger();
        this.menuID = menuID;
    }

    public Optional<MenuItem> getItemById(String itemID) {
        return menuItemList.stream().filter(x -> x.getID().equals(itemID)).findAny();
    }

    public Optional<MenuItem> getItemByName(String itemName) {
        return menuItemList.stream().filter(x -> x.getName().equals(itemName)).findAny();
    }

    public ArrayList<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    @Override
    public String getId() {
        return menuID;
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

    public int getSize() {
        return menuItemList.size();
    }

    public String getMenuSummary() {
        return this.menuID + " menu with " + this.menuItemList.size() + " items";
    }

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
                String shortName = item.getName().length() > NAME_MAX_LENGTH
                        ? item.getName().substring(0, NAME_MAX_LENGTH) : item.getName();

                menuString.append(String.format("| %-5s| %-22s| $%-5.2f|\n",
                            item.getID(), shortName, item.getPrice()));

                // wrap the item name to the next line if it is too long
                for (int i = NAME_MAX_LENGTH; i < item.getName().length(); i += NAME_MAX_LENGTH) {
                    String name = item.getName().substring(i, Math.min(i + NAME_MAX_LENGTH, item.getName().length()));
                    menuString.append(String.format("| %-5s| %-22s| %-6s|\n",
                            "", name, ""));
                }

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
}
