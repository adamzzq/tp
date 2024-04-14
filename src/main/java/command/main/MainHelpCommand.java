package command.main;


public class MainHelpCommand implements MainCommand {

    /**
     * Displays all the available commands that can be used.
     */
    public static void execute() {
        String helpMessage = "Here are the list of available commands:\n" +
                "\thelp: Shows all the commands that can be used.\n" +
                "\tcreate order -menu <menu_id>: Creates a new order using the specified menu.\n" +
                "\tview -order -all: Shows a brief summary of all the created orders.\n" +
                "\tview -order <order_id>: Shows all the contents of a specified order.\n" +
                "\treceipt -order <order_id>: shows the receipt of the specified order.\n" +
                "\tcreate menu: Creates a new menu.\n" +
                "\tedit -menu <menu_id>: Modify the specified menu's items in the menu interface.\n" +
                "\tview -menu <menu_id>: Shows all the contents of a specified menu.\n" +
                "\tview -menu -all: Shows a brief summary of all the created menus.\n" +
                "\tedit restaurant: Modify restaurant name and address.\n" +
                "\tview restaurant: Shows the restaurant name and address currently in use.\n" +
                "\tview performance: Enters the order statistics interface.\n" +
                "\tbye: Quits the program.";
        System.out.println(helpMessage);
    }
}
