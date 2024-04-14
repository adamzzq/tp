package command.main;

import model.Restaurant;
import storage.Storage;

public class MainEditRestaurantInfoCommand {
    /**
     * Executes the command to edit the restaurant information.
     *
     * @param restaurant The Restaurant object whose information needs to be edited
     */
    public static void execute(Restaurant restaurant) {
        restaurant.initRestaurant();
        System.out.println("Restaurant info has been updated.");
        Storage.saveRestaurant(restaurant);
    }
}
