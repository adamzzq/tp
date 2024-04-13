package command.main;

import model.Restaurant;

public class MainViewRestaurantInfoCommand {
    /**
     * Executes the command to view the restaurant information.
     *
     * @param restaurant The Restaurant object whose information needs to be displayed
     */
    public static void execute(Restaurant restaurant) {
        System.out.println("Restaurant name: " + restaurant.getRestaurantName());
        System.out.println("Restaurant address: " + restaurant.getRestaurantAddress());
    }
}
