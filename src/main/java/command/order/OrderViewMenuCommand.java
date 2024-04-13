package command.order;

import command.main.MainCommand;
import model.Menu;

public class OrderViewMenuCommand implements MainCommand {


    /**
     * Executes the command to view the menu.
     *
     * @param menu the menu to be viewed
     */
    public static void execute(Menu menu) {
        System.out.println(menu);
    }
}
