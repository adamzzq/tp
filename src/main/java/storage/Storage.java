package storage;

import model.Menu;
import model.MenuItem;
import model.Order;
import model.Restaurant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.NoSuchElementException;

public class Storage {
    private static final String RESTAURANT_FILEPATH = "data/restaurant.txt";
    private static final String ORDERS_FILEPATH = "data/orders.txt";
    private static final String MENUS_FILEPATH = "data/menus.txt";
    private static final String SEPARATOR = " | ";

    /**
     * Checks if a new restaurant needs to be created based on the provided restaurant data.
     *
     * @param restaurant The Restaurant object to check
     * @return true if a new restaurant needs to be created, false otherwise
     * @throws IOException If an I/O error occurs while creating or reading the restaurant file
     */
    public static boolean checkNewRestaurant(Restaurant restaurant) throws IOException{
        File restaurantFile = new File(RESTAURANT_FILEPATH);
        restaurantFile.getParentFile().mkdirs();
        restaurantFile.createNewFile();
        try {
            loadRestaurant(restaurant);
            return false;
        } catch (ArrayIndexOutOfBoundsException | FileNotFoundException | NoSuchElementException e) {
            return true;
        }
    }

    /**
     * Loads the order and menu data into their respective ArrayLists.
     *
     * @param ordersList The ArrayList to store the loaded orders
     * @param menusList  The ArrayList to store the loaded menus
     */
    public static void loadData(ArrayList<Order> ordersList, ArrayList<Menu> menusList) {
        try {
            loadOrders(ordersList);
            loadMenus(menusList);
        } catch (IOException e) {
            System.out.println("Error loading order data.");
        }
    }

    /**
     * Loads the restaurant data from the restaurant save file and sets the name and address
     * in the provided Restaurant object.
     *
     * @param restaurant The Restaurant object to populate with data
     * @throws FileNotFoundException If the restaurant save file is not found
     */
    private static void loadRestaurant(Restaurant restaurant) throws FileNotFoundException {
        File restaurantFile = new File(RESTAURANT_FILEPATH);
        Scanner restaurantScanner = new Scanner(restaurantFile);
        String[] restaurantDetails = restaurantScanner.nextLine().split(" \\| ");
        restaurant.setRestaurantName(restaurantDetails[0]);
        restaurant.setRestaurantAddress(restaurantDetails[1]);
    }

    /**
     * Loads the order data from the order file and populates the provided ArrayList
     * with the loaded orders.
     *
     * @param ordersList The ArrayList to store the loaded orders
     * @throws IOException If an I/O error occurs while reading the order file
     */
    private static void loadOrders(ArrayList<Order> ordersList) throws IOException{
        File ordersFile = new File(ORDERS_FILEPATH);
        if (ordersFile.createNewFile()) {
            return;
        }
        Scanner ordersScanner = new Scanner(ordersFile);
        while (ordersScanner.hasNext()) {
            try {
                String[] restaurantDetails = ordersScanner.nextLine().split(" \\| ");
                String[] orderDetails = ordersScanner.nextLine().split(" \\| ");
                String orderType = orderDetails[2];
                if (!orderType.equals("Takeaway") && !orderType.equals("Dine in")) {
                    throw new IllegalArgumentException("Invalid order type");
                }
                Order order = new Order(restaurantDetails[0], restaurantDetails[1], orderDetails[1], orderType);
                order.setOrderID(orderDetails[0]);
                while (ordersScanner.hasNext()) {
                    String line = ordersScanner.nextLine();
                    if (line.trim().equals("-")) {
                        break;
                    }
                    String[] itemDetails = line.split(" \\| ");
                    int itemID = Integer.parseInt(itemDetails[0]);
                    double itemPrice = Double.parseDouble(itemDetails[2]);
                    if (itemID <= 0 || itemPrice <= 0) {
                        throw new IllegalArgumentException("Negative number received");
                    }
                    MenuItem item = new MenuItem(itemDetails[0], itemDetails[1], itemPrice);
                    for (int i = 0; i < Integer.parseInt(itemDetails[3]); i++) {
                        order.add(item);
                    }
                }
                ordersList.add(order);
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | NullPointerException e) {
                ordersScanner.close();
                System.out.println("Order data corrupted, erasing data...");
                ordersFile.delete();
                return;
            }

        }
    }

    /**
     * Loads the menu data from the menu file and populates the provided ArrayList
     * with the loaded menus.
     *
     * @param menusList The ArrayList to store the loaded menus
     * @throws IOException If an I/O error occurs while reading the menu file
     */
    private static void loadMenus(ArrayList<Menu> menusList) throws IOException{
        File menusFile = new File(MENUS_FILEPATH);
        if (menusFile.createNewFile()) {
            return;
        }
        Scanner menusScanner = new Scanner(menusFile);
        while (menusScanner.hasNext()) {
            String menuID = menusScanner.nextLine().trim();
            Menu menu = new Menu(menuID);
            while (menusScanner.hasNext()) {
                String line = menusScanner.nextLine();
                if (line.trim().equals("-")) {
                    break;
                }
                String[] itemDetails = line.split(" \\| ");
                try {
                    int itemID = Integer.parseInt(itemDetails[0]);
                    double itemPrice = Double.parseDouble(itemDetails[2]);
                    if (itemID <= 0 || itemPrice <= 0) {
                        throw new IllegalArgumentException("Negative number received");
                    }
                    MenuItem item = new MenuItem(itemDetails[0], itemDetails[1], itemPrice);
                    menu.add(item);
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | NullPointerException e) {
                    menusScanner.close();
                    System.out.println("Menu data corrupted, erasing data...");
                    menusFile.delete();
                    return;
                }
            }
            menusList.add(menu);
        }
    }

    /**
     * Saves restaurant information to the restaurant save file.
     *
     * @param restaurant The Restaurant object with information to save
     */
    public static void saveRestaurant(Restaurant restaurant) {
        try {
            FileWriter fw = new FileWriter(RESTAURANT_FILEPATH);
            String restaurantDetails = restaurant.getRestaurantName() + SEPARATOR + restaurant.getRestaurantAddress();
            fw.write(restaurantDetails);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving restaurant details.");
        }
    }

    /**
     * Saves order information to the order save file.
     *
     * @param order The Order object with information to save
     */
    public static void saveOrder(Order order){
        try {
            FileWriter fw = new FileWriter(ORDERS_FILEPATH, true);
            String restaurantDetails = order.getRestaurantName() + SEPARATOR + order.getRestaurantAddress() + "\n";
            String orderDetails = order.getId() + SEPARATOR + order.getUserName() +
                    SEPARATOR + order.getOrderType() + "\n";
            fw.write(restaurantDetails);
            fw.write(orderDetails);

            Set<String> orderItems = new HashSet<>();
            for (MenuItem item : order.getOrderItemList()) {
                String itemID = item.getID();
                if (orderItems.contains(itemID)) {
                    continue;
                }
                int quantity = order.getItemCount(itemID);
                String itemData = itemID + SEPARATOR + item.getName() + SEPARATOR +
                        item.getPrice() + SEPARATOR + quantity + "\n";
                fw.write(itemData);
                orderItems.add(itemID);
            }
            fw.write("-\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error.");
        }
    }

    /**
     * Saves menu information to the menu save file.
     *
     * @param menu The Menu object with information to save
     */
    public static void saveMenu(Menu menu) {
        try {
            FileWriter fw = new FileWriter(MENUS_FILEPATH, true);
            fw.append(menu.getId()).append("\n");

            for (MenuItem item : menu.getMenuItemList()) {
                String itemData = item.getID() + SEPARATOR + item.getName() + SEPARATOR + item.getPrice() + "\n";
                fw.write(itemData);
            }
            fw.write("-\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error.");
        }
    }

    /**
     * Updates the menu save file with the data from the provided list of menus.
     *
     * @param menusList The ArrayList of Menu objects to update in the menu file
     * @throws IOException If an I/O error occurs while writing to the menu file
     */
    public static void updateMenus(ArrayList<Menu> menusList) throws IOException{
        FileWriter fw = new FileWriter(MENUS_FILEPATH);
        for (Menu menu : menusList) {
            saveMenu(menu);
        }
        fw.close();
    }
}
