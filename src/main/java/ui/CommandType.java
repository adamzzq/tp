package ui;

public enum CommandType {
    // main commands
    EXIT("(?i)bye"),
    HELP("(?i)help"),
    CREATE_ORDER("(?i)create\\s*order\\s*-menu\\s*(\\d+)"), // case-insensitive, space safe

    // order commands
    VIEW_ORDER("(?i)view\\s*-order\\s*(\\d+)"),
    VIEW_ALL_ORDERS("(?i)view\\s*-order\\s*-all"),
    EDIT_ORDER("(?i)edit\\s*-order\\s*(\\d+)"),

    // edit order commands
    ADD_ITEM("(?i)add\\s*-item\\s*(\\d+)\\s*-quantity\\s*(\\d+)"),
    DELETE_ITEM("(?i)delete\\s*-item\\s*(\\d+)\\s*-quantity\\s*(\\d+)"),
    VIEW_ITEM("view item"),
    COMPLETE("(?i)complete\\s*"),

    // edit menu commands
    ADD_MENU_ITEM("(?i)add\\s*-item\\s*(\\d+)\\s*-name\\s*([A-Z\\s]+)\\s*-price\\s*(\\d+(\\.\\d+)?)"),
    DELETE_MENU_ITEM("(?i)delete\\s*-item\\s*(\\d+)"),
    VIEW_MENU("(?i)view menu"),
    CREATE_MENU("(?i)create\\s*menu"),
    VIEW_MENU_LIST("(?i)view menu list"),
    EDIT_MENU("(?i)edit\\s*-menu\\s*(\\d+)");

    private final String commandRegex;
    CommandType(String commandRegex) {
        this.commandRegex = commandRegex;
    }

    String getCommandRegex() {
        return commandRegex;
    }
}
