package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order implements ItemManager {
    private static final double SERVICE_CHARGE = 0.1;
    private static final double GST = 0.09;
    private static final String ROW_DELIMITER = "+-----------------------------------------------------+\n";
    private static final String TITLE = "|                       RECEIPT                       |\n";
    private static final String HEADERS = "| ID       | Name            | Price       | Quantity |\n";
    private static final int NAME_MAX_LENGTH = 15; // Default length for Name column header
    private static final String SHORT_ROW_START = "| %-8s | %-15s | $%-10.2";
    private static final String SHORT_ROW_END = " | %-9d|\n";
    private static final String LONG_ROW_FORMAT = "|          | %-15s |"
            + " ".repeat(13) + "|" + " ".repeat(10) + "|\n";
    private static final String CHARGE_FORMAT = "| %-37s $%-12.2";
    private static final String DISCOUNT_AMOUNT_FORMAT = "| %.0f%% %-32s -$%-12.2";
    private static final String SINGLE_DIGIT_DISCOUNT_FORMAT = "| %.0f%% %-33s -$%-12.2";
    private static final int SUBTITLE_OFFSET = 4;
    private static final int ORDER_TYPE_OFFSET = 16;
    private static final int CASHIER_NAME_OFFSET = 13;
    private static final int ORDER_ID_OFFSET = 14;
    private final String orderID;

    private final String restaurantName;
    private final String restaurantAddress;
    private final String orderType;
    private final String userName;
    private final ArrayList<MenuItem> orderItemList = new ArrayList<>();

    public Order(String restaurantName, String restaurantAddress, String userName, String orderType) {
        this.orderID = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.userName = userName;
        this.orderType = orderType;
    }

    @Override
    public boolean add(MenuItem item) {
        this.orderItemList.add(item);
        return true;
    }

    /**
     * Removes all items from the order list by its id
     *
     * @param itemID the id of the item to be removed
     */
    @Override
    public boolean remove(String itemID) {
        return this.orderItemList.removeIf(x -> x.getID().equals(itemID));
    }

    public boolean remove(MenuItem item) {
        return this.orderItemList.remove(item);
    }

    public int getItemCount(String itemID) {
        return (int) this.orderItemList.stream().filter(x -> x.getID().equals(itemID)).count();
    }

    @Override
    public String getId() {
        return orderID;
    }

    public int getSize() {
        return this.orderItemList.size();
    }

    public double getTotalPrice() {
        return Double.parseDouble(String.format("%.2f",
                this.orderItemList.stream().mapToDouble(Item::getPrice).sum() * (1 + SERVICE_CHARGE) * (1 + GST)));
    }

    /**
     * Returns a formatted receipt header (top part of the receipt)
     *
     * @return the formatted receipt header
     */
    private StringBuilder getReceiptHeader(StringBuilder headerBuilder) {
        return headerBuilder.append(ROW_DELIMITER)
                .append(TITLE)
                .append(ROW_DELIMITER)

                .append("| ")
                .append(restaurantName)
                .append(" ".repeat(ROW_DELIMITER.length() - restaurantName.length() - SUBTITLE_OFFSET))
                .append("|\n")

                .append("| ")
                .append(restaurantAddress)
                .append(" ".repeat(ROW_DELIMITER.length() - restaurantAddress.length() - SUBTITLE_OFFSET))
                .append("|\n")

                .append("| Order Type: ")
                .append(orderType)
                .append(" ".repeat(ROW_DELIMITER.length() - orderType.length() - ORDER_TYPE_OFFSET))
                .append("|\n")

                .append("| Order ID: ")
                .append(orderID)
                .append(" ".repeat(ROW_DELIMITER.length() - orderID.length() - ORDER_ID_OFFSET))
                .append("|\n")

                .append(ROW_DELIMITER)
                .append(HEADERS)
                .append(ROW_DELIMITER);
    }

    /**
     * Returns a formatted receipt with summary (bottom part of the receipt) appended
     *
     * @param discount the discount to be applied to the order
     * @return the formatted receipt with summary
     */
    private StringBuilder getReceiptSummary(StringBuilder receiptBuilder, double discount) {
        double netTotal = getTotalPrice() / (1 + GST) / (1 + SERVICE_CHARGE) * (1 - discount);
        double discountAmount = (discount) * netTotal / (1 - discount);
        double serviceCharge = netTotal * SERVICE_CHARGE;
        double gst = netTotal * (1 + SERVICE_CHARGE) * GST;
        double grandTotal = netTotal + serviceCharge + gst;

        receiptBuilder.append(ROW_DELIMITER)
                .append(String.format((discount< 0.095 ? SINGLE_DIGIT_DISCOUNT_FORMAT : DISCOUNT_AMOUNT_FORMAT)
                                + chooseFormat(discount) + " |\n", discount * 100,
                        "Off Discount:", discountAmount))
                .append(String.format(CHARGE_FORMAT + chooseFormat(netTotal) + " |\n",
                        "Subtotal:", netTotal))
                .append(ROW_DELIMITER)
                .append(String.format(CHARGE_FORMAT + chooseFormat(serviceCharge) + " |\n",
                        "Service Charge (" + (SERVICE_CHARGE * 100) + "%):", serviceCharge))
                .append(String.format(CHARGE_FORMAT + chooseFormat(gst) + " |\n",
                        "GST (" + (GST * 100) + "%):", gst))
                .append(String.format(CHARGE_FORMAT + chooseFormat(getTotalPrice()) + " |\n",
                        "Grand Total:", grandTotal))
                .append(ROW_DELIMITER);

        return receiptBuilder.append("| Cashier: ")
                .append(userName)
                .append(" ".repeat(ROW_DELIMITER.length() - userName.length()- CASHIER_NAME_OFFSET))
                .append("|\n")
                .append(ROW_DELIMITER);
    }

    /**
     * Returns a formatted and complete receipt
     *
     * @param discount the discount to be applied to the order
     * @return the formatted receipt
     */
    public String getReceipt(double discount)  {
        StringBuilder receiptBuilder = new StringBuilder();
        Set<String> processedItems = new HashSet<>();

        receiptBuilder = getReceiptHeader(receiptBuilder);

        for (MenuItem item : orderItemList) {
            String itemID = item.getID();

            if (!processedItems.contains(itemID)) {
                int quantity = getItemCount(itemID);
                String shortName = item.getName().length() > NAME_MAX_LENGTH
                        ? item.getName().substring(0, NAME_MAX_LENGTH) : item.getName();
                String format = SHORT_ROW_START + chooseFormat(item.getPrice()) + SHORT_ROW_END;
                String formattedString = String.format(format, itemID, shortName, item.getPrice(), quantity);

                receiptBuilder.append(formattedString);
                // iterate through the rest part of the name, keep the max length of 15
                for (int i = NAME_MAX_LENGTH; i < item.getName().length(); i += NAME_MAX_LENGTH) {
                    String name = item.getName().substring(i, Math.min(i + NAME_MAX_LENGTH, item.getName().length()));
                    receiptBuilder.append(String.format(LONG_ROW_FORMAT, name));
                }

                processedItems.add(itemID);
            }
        }

        receiptBuilder = getReceiptSummary(receiptBuilder, discount);

        return receiptBuilder.toString();
    }

    public String getReceipt() {
        return getReceipt(0);
    }

    private char chooseFormat(double value) {
        // If the value is too large, use scientific notation
        return (String.valueOf((int) (value)).length()) > 7 ? 'e' : 'f';
    }

    /**
     * Returns a brief summary of the order
     *
     * @return the orderID and the total price of the order
     */
    public String getOrderSummary() {
        return this.orderID + " {Total Price: " + this.getTotalPrice() + "}";
    }

    @Override
    public String toString() {
        return this.orderID + "\n" +
                IntStream.range(0, this.orderItemList.size())
                        .mapToObj(x -> (x + 1) + ". " + this.orderItemList.get(x))
                        .collect(Collectors.joining("\n"));
    }
}
