package command.main;

import model.Menu;

import java.util.ArrayList;

public class MainViewMenusSummaryCommand {

    /**
     * Executes the command to view the summary of all menus
     * @param menuList the list of menus to choose from
     */
    public static void execute(ArrayList<Menu> menuList) {
        if (menuList.isEmpty()){
            System.out.println("No orders available");
            return;
        }
        menuList.forEach(x -> System.out.println(x.getMenuSummary()));
    }
}
