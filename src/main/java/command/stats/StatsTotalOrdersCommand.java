package command.stats;

import stats.OrderStatistics;

public class StatsTotalOrdersCommand implements StatsCommand {
    /**
     * Executes the command to view the total number of orders.
     *
     * @param orderStatistics the statistics object containing the data
     */
    public static void execute(OrderStatistics orderStatistics) {
        System.out.println("Total number of orders: " + orderStatistics.getOrderCount());
    }
}
