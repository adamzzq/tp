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
    private static final String ROW_DELIMITER = "+------+-----------------------------------+\n";
    private static final String MENU_HEADER = "+------------------------------------------+\n" +
            "|                   MENU                   |\n" +
            ROW_DELIMITER +
            "| ID   |         Name          | Price     |\n" +
            ROW_DELIMITER;
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
        return menuItemList.stream().filter(x -> x.getName().compareToIgnoreCase(itemName) == 0).findAny();
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
        sortId(itemID);
        return true;
    }

    /**
     * Sorts the id of the items in the menu after an item is removed
     * @param startId The id of the item that is removed
     */
    private void sortId(String startId) {
        for (int id = Integer.parseInt(startId); id <= menuItemList.size(); id++) {
            Item item = menuItemList.get(id - 1);
            item.setID(id + "");
        }
    }

    public int getSize() {
        return menuItemList.size();
    }

    public String getMenuSummary() {
        return String.format("Menu ID: %s, Number of items: %d", this.menuID, this.menuItemList.size());
    }

    @Override
    public String toString() {
        StringBuilder menuString = new StringBuilder();
        menuString.append(MENU_HEADER);
        if (menuItemList.isEmpty()) {
            menuString.append(ROW_DELIMITER);
        } else {
            for (MenuItem item : menuItemList) {
                String shortName = item.getName().length() > NAME_MAX_LENGTH
                        ? item.getName().substring(0, NAME_MAX_LENGTH) : item.getName();

                String priceFormat = (String.valueOf(item.getPrice()).length() > 7) ?
                        "| %-5s| %-22s|$%-10.2e|\n" : "| %-5s| %-22s| $%-9.2f|\n";

                menuString.append(String.format(priceFormat,
                            item.getID(), shortName, item.getPrice()));

                // wrap the item name to the next line if it is too long
                for (int i = NAME_MAX_LENGTH; i < item.getName().length(); i += NAME_MAX_LENGTH) {
                    String name = item.getName().substring(i, Math.min(i + NAME_MAX_LENGTH, item.getName().length()));
                    menuString.append(String.format("| %-5s| %-22s| %-10s|\n",
                            "", name, ""));
                }

            }
            menuString.append(ROW_DELIMITER);
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
            logr.log(Level.SEVERE, "File logger not working.", e);
        }
    }
}
