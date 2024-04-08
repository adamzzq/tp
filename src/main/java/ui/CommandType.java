package ui;

public enum CommandType {
    //main commands
    EXIT("(?i)bye"),
    HELP("(?i)help"),
    CREATE_ORDER("(?i)create\\s*order\\s*-menu\\s*(\\d+)"), // case-insensitive, space safe
    VIEW_ORDER("(?i)view\\s*-order\\s*(\\d+)"),
    VIEW_ALL_ORDERS("(?i)view\\s*-order\\s*-all"),
    VIEW_MENU("(?i)view\\s*-menu\\s*(\\d+)"),
    VIEW_ALL_MENUS("(?i)view\\s*-menu\\s*-all"),
    CREATE_MENU("(?i)create\\s*menu"),
    EDIT_MENU("(?i)edit\\s*-menu\\s*(\\d+)"),
    ORDER_RECEIPT("(?i)receipt\\s*-order\\s*(\\d+)"),


    //order commands
    ADD_ITEM("(?i)add\\s*-item\\s*(\\d+)\\s*-quantity\\s*(\\d+)"),
    DELETE_ITEM("(?i)delete\\s*-item\\s*(\\d+)\\s*-quantity\\s*(\\d+)"),
    VIEW_ITEM("view item"),
    COMPLETE("(?i)complete\\s*"),
    VIEW_MENU_ORDERLOGIC("(?i)view menu"),

    // edit menu commands
    ADD_MENU_ITEM("(?i)add\\s*-item\\s*([A-Z\\s]+)\\s*-price\\s*(\\d+(\\.\\d+)?)"),
    DELETE_MENU_ITEM("(?i)delete\\s*-item\\s*(\\d+)");



    private final String commandRegex;
    CommandType(String commandRegex) {
        this.commandRegex = commandRegex;
    }

    String getCommandRegex() {
        return commandRegex;
    }
}
