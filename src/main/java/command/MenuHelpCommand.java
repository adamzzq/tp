package command;

public class MenuHelpCommand implements MenuCommand {
    public static void execute() {
        System.out.println("Here are the list of available commands:");
        System.out.println("\thelp: Shows all the commands that can be used.");
        System.out.println("\tadd -item <item_id> -name <item_name> -price <price_of_item> : Shows the current menu.");
        System.out.println("\tdelete -item <item_id> : Shows the current menu.");
        System.out.println("\tview -menu -all: Shows a list of all the menus of the restaurant");
        System.out.println("\tview -menu <menu_id>: Shows the items on the specified menu");
        System.out.println("\tcomplete: Completes the menu and returns to the main menu.");
        System.out.println("\tbye: Aborts the current menu and returns to the main menu.");
    }
}
