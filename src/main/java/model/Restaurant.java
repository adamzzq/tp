package model;

import java.util.Scanner;

public class Restaurant {
    private String restaurantName;
    private String restaurantAddress;

    //@@author Zhengwinter
    /**
     * Initializes the restaurant object by prompting the user for the restaurant name and address.
     */
    public void initRestaurant() {
        boolean isValidRestaurantName = false;
        boolean isValidAddress = false;
        while (!isValidRestaurantName) {
            isValidRestaurantName = getInput("Restaurant");
        }
        while (!isValidAddress) {
            isValidAddress = getInput("Address");
        }
    }

    /**
     * Obtains a specific input from the user based on the provided token.
     *
     * @param token A String representing the value to be initialized (e.g., "Restaurant", "Address")
     * @return true if the user input is valid, false otherwise
     */
    private boolean getInput (String token) {
        switch(token) {
        case "Restaurant":
            System.out.println("Enter restaurant name: ");
            break;
        case "Address":
            System.out.println("Enter address of restaurant: ");
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
    /**
     * Returns the name of the restaurant.
     *
     * @return The name of the restaurant
     */
    public String getRestaurantName() {
        return restaurantName;
    }

    /**
     * Sets the name of the restaurant.
     *
     * @param name The new name of the restaurant
     */
    public void setRestaurantName(String name) {
        restaurantName = name;
    }

    /**
     * Returns the address of the restaurant.
     *
     * @return The address of the restaurant
     */
    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    /**
     * Sets the address of the restaurant.
     *
     * @param address The new address of the restaurant
     */
    public void setRestaurantAddress(String address) {
        restaurantAddress = address;
    }
}
