package command.main;

import model.Menu;

import java.util.ArrayList;

/**
 * Displays a list of all the menu items
 */
public class MainViewMenusSummaryCommand {

    /**
     * Executes the command to view the summary of all menus
     * @param menuList the list of menus to choose from
     */
    public static void execute(ArrayList<Menu> menuList) {
        if (menuList.isEmpty()){
            System.out.println("No menus available");
            return;
        }
        menuList.forEach(x -> System.out.println(x.getMenuSummary()));
    }
}
