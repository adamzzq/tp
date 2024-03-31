package command.main;

import model.Menu;

import java.util.ArrayList;

public class MainViewMenusSummaryCommand {
    public static void execute(ArrayList<Menu> menuList) {
        if (menuList.isEmpty()){
            System.out.println("No orders available");
            return;
        }
        menuList.forEach(x -> System.out.println(x.getMenuSummary()));
    }
}
