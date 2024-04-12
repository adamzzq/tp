package model;

import java.util.Scanner;

public class Restaurant {
    private static String restaurantName;
    private static String restaurantAddress;

    //@@author Zhengwinter
    public static void initRestaurant(Scanner input) {
        boolean isValidRestaurantName = false;
        boolean isValidAddress = false;
        while (!isValidRestaurantName) {
            isValidRestaurantName = initializeSystem("Restaurant");
        }
        while (!isValidAddress) {
            isValidAddress = initializeSystem("Address");
        }
    }

    private static boolean initializeSystem (String token) {
        switch(token) {
        case "Restaurant":
            System.out.println("Enter restaurant name: ");
            break;
        case "Address":
            System.out.println("Enter address of restaurant: ");
            break;
        case "User":
            System.out.println("Enter user name: ");
            break;
        default:
            System.out.println("Error in received initialization token");
        }
        Scanner input = new Scanner(System.in);
        String inputString= input.nextLine();
        switch(token) {
        case "Restaurant":
            restaurantName = inputString;
            break;
        case "Address":
            restaurantAddress = inputString;
            break;
        default:
            System.out.println("Error in received initialization token");
        }
        if (inputString.isBlank() || inputString.isEmpty()) {
            System.out.println("Input cannot be empty!");
            return false;
        } else {
            return true;
        }
    }

    //@@author webtjs
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String name) {
        restaurantName = name;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String address) {
        restaurantAddress = address;
    }
}
