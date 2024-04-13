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

    public static void loadData(ArrayList<Order> ordersList, ArrayList<Menu> menusList) {
        try {
            loadOrders(ordersList);
            loadMenus(menusList);
        } catch (IOException e) {
            System.out.println("Error loading order data.");
        }
    }

    private static void loadRestaurant(Restaurant restaurant) throws FileNotFoundException {
        File restaurantFile = new File(RESTAURANT_FILEPATH);
        Scanner restaurantScanner = new Scanner(restaurantFile);
        String[] restaurantDetails = restaurantScanner.nextLine().split(" \\| ");
        restaurant.setRestaurantName(restaurantDetails[0]);
        restaurant.setRestaurantAddress(restaurantDetails[1]);
    }

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
                Order order = new Order(restaurantDetails[0], restaurantDetails[1], orderDetails[1], orderDetails[2]);
                order.setOrderID(orderDetails[0]);
                while (ordersScanner.hasNext()) {
                    String line = ordersScanner.nextLine();
                    if (line.trim().equals("-")) {
                        break;
                    }
                    String[] itemDetails = line.split(" \\| ");
                    MenuItem item = new MenuItem(itemDetails[0], itemDetails[1], Double.parseDouble(itemDetails[2]));
                    for (int i = 0; i < Integer.parseInt(itemDetails[3]); i++) {
                        order.add(item);
                    }
                }
                ordersList.add(order);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e) {
                ordersScanner.close();
                System.out.println("Order data corrupted, erasing data...");
                ordersFile.delete();
                return;
            }

        }
    }

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
                    MenuItem item = new MenuItem(itemDetails[0], itemDetails[1], Double.parseDouble(itemDetails[2]));
                    menu.add(item);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e) {
                    menusScanner.close();
                    System.out.println("Menu data corrupted, erasing data...");
                    menusFile.delete();
                    return;
                }
            }
            menusList.add(menu);
        }
    }

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

    public static void updateMenus(ArrayList<Menu> menusList) throws IOException{
        FileWriter fw = new FileWriter(MENUS_FILEPATH);
        for (Menu menu : menusList) {
            saveMenu(menu);
        }
        fw.close();
    }
}
