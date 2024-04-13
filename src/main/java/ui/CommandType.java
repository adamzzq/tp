package ui;

public enum CommandType {
    //main commands
    EXIT_MAIN("(?i)bye"),
    HELP("(?i)help"),
    CREATE_ORDER("(?i)create\\s*order\\s*-menu\\s*(\\d+)"), // case-insensitive, space safe
    VIEW_ORDER("(?i)view\\s*-order\\s*(\\d+)"),
    VIEW_ALL_ORDERS("(?i)view\\s*-order\\s*-all"),
    VIEW_MENU("(?i)view\\s*-menu\\s*(\\d+)"),
    VIEW_ALL_MENUS("(?i)view\\s*-menu\\s*-all"),
    CREATE_MENU("(?i)create\\s*menu"),
    EDIT_MENU("(?i)edit\\s*-menu\\s*(\\d+)"),
    ORDER_RECEIPT("(?i)receipt\\s*-order\\s*(\\d+)"),
    VIEW_RESTAURANT_INFO("(?i)view\\s*restaurant"),
    EDIT_RESTAURANT_INFO("(?i)edit\\s*restaurant"),
    VIEW_PERFORMANCE_INFO("(?i)view\\s*performance"),


    //order commands
    ADD_ITEM("(?i)add\\s*-item\\s*(\\d+)\\s*-quantity\\s*(\\d+)"),
    DELETE_ITEM("(?i)delete\\s*-item\\s*(\\d+)\\s*-quantity\\s*(\\d+)"),
    VIEW_ITEM("(?i)view item"),
    COMPLETE("(?i)complete\\s*"),
    VIEW_MENU_ORDERLOGIC("(?i)view menu"),

    EXIT("(?i)cancel"),

    // edit menu commands
    ADD_MENU_ITEM("(?i)add\\s*-item\\s*([A-Z\\s]+)\\s*-price\\s*(\\d+(\\.\\d+)?)"),
    DELETE_MENU_ITEM("(?i)delete\\s*-item\\s*(\\d+)"),

    // stats commands
    VIEW_BEST_SELLING_ITEM("(?i)bestselling"),
    VIEW_TOTAL_ORDERS("(?i)total\\s*orders"),
    VIEW_GROSS_REVENUE("(?i)revenue\\s*-gross"),
    VIEW_NET_REVENUE("(?i)revenue\\s*-net"),
    VIEW_PROFIT("(?i)view\\s*profit\\s*-cost\\s*(\\d+(\\.\\d+)?)"),
    QUIT("(?i)quit");

    private final String commandRegex;
    CommandType(String commandRegex) {
        this.commandRegex = commandRegex;
    }

    String getCommandRegex() {
        return commandRegex;
    }
}
