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
    private static final String TITLE = "RECEIPT";
    private static final String HEADERS = "| Item ID  | Name            | Unit Price  | Quantity |\n";
    private static final int NAME_MAX_LENGTH = 15; // Default length for Name column header
    private static final String SHORT_ROW_START = "| %-8s | %-15s | $%-10.2";
    private static final String SHORT_ROW_END = " | %-9d|\n";
    private static final String LONG_ROW_FORMAT = "|          | %-15s |"
            + " ".repeat(13) + "|" + " ".repeat(10) + "|\n";
    private static final char SCIENTIFIC_NOTATION = 'e';
    private static final char FLOAT_NOTATION = 'f';

    private static final int SUBTITLE_OFFSET = 2;
    private static final int ORDER_TYPE_OFFSET = 16;
    private static final int CASHIER_NAME_OFFSET = 13;
    private static final int ORDER_ID_OFFSET = 14;
    private static final int MAX_CHARGE_LENGTH = 6;
    private static final int CHARGE_VALUE_OFFSET = 3;
    private String orderID;
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
    public boolean add(MenuItem item) throws IllegalArgumentException {
        if (getItemCount(item.getID()) >= 9999) {
            throw new IllegalArgumentException("The quantity of the item has reached the maximum of 9999");
        }
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
     * @param headerBuilder the StringBuilder to append the header to
     * @return the formatted receipt header
     */
    private StringBuilder getReceiptHeader(StringBuilder headerBuilder) {
        return headerBuilder.append(ROW_DELIMITER)
                .append("|")
                .append(centerAlign(TITLE))
                .append("|\n")
                .append(ROW_DELIMITER)

                .append("|")
                .append(centerAlign(restaurantName))
                .append("|\n")

                .append("|")
                .append(centerAlign(restaurantAddress))
                .append("|\n")
                .append("|")
                .append(centerAlign(""))
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

    private String centerAlign(String word) {
        int totalSpaces = ROW_DELIMITER.length() - word.length() - SUBTITLE_OFFSET;
        int leftSpaces = totalSpaces / 2;
        int rightSpaces = leftSpaces;

        if (totalSpaces % 2 == 0) { // to offset the difference in left and right for odd length
            rightSpaces -= 1;
        }
        return " ".repeat(leftSpaces) + word + " ".repeat(rightSpaces);
    }

    /**
     * Returns a formatted receipt with summary (bottom part of the receipt) appended
     *
     * @param receiptBuilder the StringBuilder to append the summary to
     * @param discount the discount to be applied to the order
     * @return the formatted receipt with summary
     */
    private StringBuilder getReceiptSummary(StringBuilder receiptBuilder, double discount) {
        double netTotal = getTotalPrice() / (1 + GST) / (1 + SERVICE_CHARGE) * (1 - discount);
        double discountAmount = -discount * netTotal / (1 - discount);
        double serviceCharge = netTotal * SERVICE_CHARGE;
        double gst = netTotal * (1 + SERVICE_CHARGE) * GST;
        double grandTotal = netTotal + serviceCharge + gst;
        int maxLength = 0;

        // find which number has the most number of digits
        for (double num : new double[]{discountAmount, netTotal, serviceCharge, gst, grandTotal}) {
            int numLength = String.valueOf((int) Math.abs(num)).length() + CHARGE_VALUE_OFFSET;

            if (numLength > 9) {
                String scientificNotation = String.format("%.2e", Math.abs(num));
                numLength = scientificNotation.length();
            }
            if (numLength >= maxLength) {
                maxLength = numLength;
            }
        }
        receiptBuilder.append(ROW_DELIMITER);

        if (discount != 0) {
            receiptBuilder
                    .append(setChargeFormat((int) (discount * 100) + "% Off Discount:", discountAmount, maxLength));
        }

        receiptBuilder
                .append(setChargeFormat("Subtotal:", netTotal, maxLength))
                .append(ROW_DELIMITER)
                .append(setChargeFormat(setChargeDescription("Service Charge", SERVICE_CHARGE)
                        , serviceCharge, maxLength))
                .append(setChargeFormat(setChargeDescription("GST", GST), gst, maxLength))
                .append(setChargeFormat("Grand Total:", grandTotal, maxLength))
                .append(ROW_DELIMITER);

        return receiptBuilder.append("| Cashier: ")
                .append(userName)
                .append(" ".repeat(ROW_DELIMITER.length() - userName.length()- CASHIER_NAME_OFFSET))
                .append("|\n")
                .append(ROW_DELIMITER);
    }

    /**
     * Returns a formatted charge description with the percentage in brackets
     *
     * @param description the description of the charge
     * @param percentage the value of the charge in decimal form e.g. 0.35 for 35% discount
     * @return the formatted charge description
     */
    private String setChargeDescription(String description, double percentage) {
        return String.format("%s (%.1f%%): ", description, percentage * 100);
    }

    /**
     * Returns a formatted charge line aligned to the right by the dollar sign
     *
     * @param description the description of the charge
     * @param charge the charge to be displayed
     * @param maxDigits the maximum number of digits in the charge
     * @return the formatted charge line
     */
    private String setChargeFormat(String description, double charge, int maxDigits) {
        int descriptionLength = String.valueOf(description).length();
        int middleSpace = ROW_DELIMITER.length() - descriptionLength - maxDigits - 2 * CHARGE_VALUE_OFFSET - 1;
        StringBuilder chargeLine = new StringBuilder().append("| ").append(description);
        int i = 0;

        while (i < middleSpace) {
            chargeLine.append(" ");
            i++;
        }

        if (charge < 0) {
            chargeLine.append("-");
            charge = -charge;
        } else {
            chargeLine.append(" ");
        }

        chargeLine.append(String.format("$%.2" + chooseFormat(charge), charge));
        i = chargeLine.length();

        while (i < ROW_DELIMITER.length() - 2) {
            chargeLine.append(" ");
            i++;
        }

        chargeLine.append("|\n");
        return chargeLine.toString();
    }

    /**
     * Returns a formatted and complete receipt
     *
     * @param discount the discount to be applied to the order in decimal form ranging from 0.1 to 0.99
     *     in 2 decimal places, with 0 being an exception for no discount (e.g. 0.15 for 15% discount)
     * @return the formatted receipt
     * @throws IllegalArgumentException if the discount is not between 1% and 99%
     */
    public String getReceipt(double discount) throws IllegalArgumentException {
        if (discount != 0 && !(discount >= 0.01 && discount <= 0.99)) {
            throw new IllegalArgumentException("Discount must be between 1% and 99% (inclusive)");
        }

        StringBuilder receiptBuilder = new StringBuilder();
        Set<String> processedItems = new HashSet<>();

        receiptBuilder = getReceiptHeader(receiptBuilder);

        for (MenuItem item : orderItemList) {
            String itemID = item.getID();

            if (!processedItems.contains(itemID)) {
                int quantity = getItemCount(itemID);
                String shortName = item.getName().length() > NAME_MAX_LENGTH
                        ? item.getName().substring(0, NAME_MAX_LENGTH) : item.getName();
                String shortNameFormat = SHORT_ROW_START + chooseFormat(item.getPrice()) + SHORT_ROW_END;

                receiptBuilder.append(String.format(shortNameFormat, itemID, shortName, item.getPrice(), quantity));

                // iterate through the rest part of the name, keep the max length of 15
                for (int i = NAME_MAX_LENGTH; i < item.getName().length(); i += NAME_MAX_LENGTH) {
                    String partialName = item.getName()
                            .substring(i, Math.min(i + NAME_MAX_LENGTH, item.getName().length()));

                    receiptBuilder.append(String.format(LONG_ROW_FORMAT, partialName));
                }
                processedItems.add(itemID);
            }
        }

        receiptBuilder = getReceiptSummary(receiptBuilder, discount);
        return receiptBuilder.toString();
    }

    /**
     * Returns a formatted receipt without any discount
     *
     * @return the formatted receipt
     */
    public String getReceipt() {
        return getReceipt(0);
    }

    /**
     * Chooses the format of the number based on the length of the integer part
     *
     * @param value the value to be formatted
     * @return 'e' if the value has more than 7 digits, 'f' otherwise
     */
    private char chooseFormat(double value) {
        // If the value is too large, use scientific notation
        return (String.valueOf((int) (value)).length()) > MAX_CHARGE_LENGTH ? SCIENTIFIC_NOTATION : FLOAT_NOTATION;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public String getUserName() {
        return userName;
    }

    public String getOrderType() {
        return orderType;
    }

    public ArrayList<MenuItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
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
