package command.stats;

import stats.OrderStatistics;

public class StatsGrossRevenueCommand implements StatsCommand{
    /**
     * Prints the gross revenue in the order statistics
     *
     * @param stats OrderStatistics object containing the statistics
     */
    public static void execute(OrderStatistics stats) {
        System.out.printf("Gross revenue: $%.2f\n", stats.getGrossRevenue());
    }
}
