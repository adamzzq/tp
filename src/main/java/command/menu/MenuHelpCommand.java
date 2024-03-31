package command.menu;

public class MenuHelpCommand implements MenuCommand {
    /**
     * Provides a guide to the user on the available commands for navigating the Menu sub-interface
     */
    public static void execute() {
        System.out.println("Here are the list of available commands:");
        System.out.println("\thelp: Shows all the commands that can be used.");
        System.out.println("\tadd -item <item_id> -name <item_name> -price <price_of_item> : " +
                "Adds a new item to the menu.");
        System.out.println("\tdelete -item <item_id> : Deletes an item from the menu.");
        System.out.println("\tview item: Shows all the items in the current menu.");
        System.out.println("\tcomplete: Completes the menu and returns to the main menu.");
        System.out.println("\tbye: Aborts the current menu and returns to the main menu.");
    }
}
