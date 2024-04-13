package command.main;


public class MainHelpCommand implements MainCommand {

    /**
     * Executes the command to show all the available commands
     */
    public static void execute() {
        System.out.println("Here are the list of available commands:");
        System.out.println("\thelp: Shows all the commands that can be used.");
        System.out.println("\tcreate order -menu <menu_id>: Creates a new order using the specified menu.");
        System.out.println("\tview -order -all: Shows a brief summary of all the created orders.");
        System.out.println("\tview -order <order_id>: Shows all the contents of a specified order.");
        System.out.println("\treceipt -order <order_id>: shows the receipt of the specified order.");
        System.out.println("\tcreate menu: Creates a new menu.");
        System.out.println("\tedit -menu <menu_id>: Modify the specified menu's items in the menu interface.");
        System.out.println("\tview -menu <menu_id>: Shows all the contents of a specified menu.");
        System.out.println("\tview -menu -all: Shows a brief summary of all the created menus.");
        System.out.println("\tbye: Quits the program.");
    }
}
