package command.stats;

import stats.OrderStatistics;
import ui.Parser;

public class StatsProfitCommand implements StatsCommand {
    /**
     * Prints the profit in the order statistics
     *
     * @param stats OrderStatistics object containing the statistics
     * @param inputText the input text that was entered by the user
     */
    public static void execute(OrderStatistics stats, String inputText) {
        String[] indexString = Parser.splitInput(Parser.analyzeInput(inputText), inputText);
        double cost = Double.parseDouble(indexString[0]);
        System.out.printf("Profit: $%.2f\n", stats.getProfit(cost));
    }
}
