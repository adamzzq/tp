package command.stats;

public class StatsHelpCommand implements StatsCommand {
    /**
     * Prints the help message for the stats commands
     */
    public static void execute() {
        System.out.println("Available commands:");
        System.out.println("\tbestselling - View the best selling item(s)");
        System.out.println("\ttotal orders - View the total number of orders");
        System.out.println("\trevenue -gross - View the gross revenue");
        System.out.println("\trevenue -net - View the net revenue");
        System.out.println("\tview profit -cost <cost> - View the profit based on the cost");
        System.out.println("\tquit - return to main interface");

    }
}
