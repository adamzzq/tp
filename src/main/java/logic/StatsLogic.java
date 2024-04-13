package logic;

import command.stats.StatsBestSellingItemCommand;
import command.stats.StatsGrossRevenueCommand;
import command.stats.StatsNetRevenueCommand;
import command.stats.StatsTotalOrdersCommand;
import command.stats.StatsProfitCommand;
import command.stats.StatsHelpCommand;
import model.Order;
import stats.OrderStatistics;
import ui.CommandType;
import ui.Parser;

import java.util.ArrayList;
import java.util.Scanner;

public class StatsLogic {
    public static void showStats(Scanner input, ArrayList<Order> orders) {
        System.out.println("Gathering statistics...");
        StatsHelpCommand.execute();

        OrderStatistics orderStatistics = new OrderStatistics(orders);
        boolean hasQuit = false;

        while (!hasQuit) {
            System.out.print("[Stats] >>> ");
            String inputText = input.nextLine();
            CommandType commandType;

            try {
                commandType = Parser.analyzeInput(inputText);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid command. Please try again.");
                continue;
            }

            switch (commandType) {
            case VIEW_BEST_SELLING_ITEM:
                StatsBestSellingItemCommand.execute(orderStatistics);
                break;
            case VIEW_GROSS_REVENUE:
                StatsGrossRevenueCommand.execute(orderStatistics);
                break;
            case VIEW_NET_REVENUE:
                StatsNetRevenueCommand.execute(orderStatistics);
                break;
            case VIEW_TOTAL_ORDERS:
                StatsTotalOrdersCommand.execute(orderStatistics);
                break;
            case VIEW_PROFIT:
                StatsProfitCommand.execute(orderStatistics, inputText);
                break;
            case QUIT:
                hasQuit = true;
                break;
            case HELP:
                StatsHelpCommand.execute();
                break;
            default:
                System.out.println("Invalid command. Please try again.");
                break;
            }
        }
    }
}
