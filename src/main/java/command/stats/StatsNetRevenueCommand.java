package command.stats;

import stats.OrderStatistics;

public class StatsNetRevenueCommand implements StatsCommand {
    /**
     * Prints the net revenue in the order statistics
     *
     * @param stats OrderStatistics object containing the statistics
     */
    public static void execute(OrderStatistics stats) {
        System.out.printf("Net revenue: $%.2f\n", stats.getNetRevenue());
    }
}
