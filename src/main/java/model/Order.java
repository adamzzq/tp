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
    private final String orderID;
    private final ArrayList<MenuItem> orderItemList = new ArrayList<>();

    public Order() {
        this.orderID = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
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

    public String getID() {
        return orderID;
    }

    public int getSize() {
        return this.orderItemList.size();
    }

    public double getTotalPrice() {
        return Double.parseDouble(String.format("%.2f",
                this.orderItemList.stream().mapToDouble(Item::getPrice).sum() * (1 + SERVICE_CHARGE + GST)));
    }

    // TODO: correct the GST and Service Charge calculation
    public String getReceipt() {
        StringBuilder itemsBuilder = new StringBuilder();
        StringBuilder headersBuilder = new StringBuilder();

        Set<String> processedItems = new HashSet<>();
        int idMaxLength = 6; // Default length for ID column header
        int nameMaxLength = 11; // Default length for Name column header

        for (MenuItem item : orderItemList) {
            idMaxLength = Math.max(idMaxLength, item.getID().length());
            nameMaxLength = Math.max(nameMaxLength, item.getName().length());
        }

        int totalLength = idMaxLength + nameMaxLength + 19; // 19 is the length of the rest of the headers
        int spaceLength = (totalLength) / 2;

        String idHeader = "| ID" + " ".repeat(idMaxLength - 1);
        String nameHeader = "| Name" + " ".repeat(nameMaxLength - 3);
        String priceHeader = "| Price   ";
        String quantityHeader = "| Quantity |\n";

        String headerLine = "+-" + "-".repeat(idMaxLength)
                + "-+-" + "-".repeat(nameMaxLength) + "-+---------+----------+\n";
        headersBuilder.append("+-").append("-".repeat(idMaxLength + nameMaxLength + 24)).append("-+\n");

        headersBuilder.append("|")
                .append(" ".repeat(spaceLength))
                .append("RECEIPT")
                .append(" ".repeat(totalLength % 2 != 0 ? spaceLength + 1 : spaceLength))
                .append("|\n")
                .append(headerLine)
                .append(idHeader)
                .append(nameHeader)
                .append(priceHeader)
                .append(quantityHeader)
                .append(headerLine);

        for (MenuItem item : orderItemList) {
            String itemID = item.getID();
            if (!processedItems.contains(itemID)) {
                int quantity = getItemCount(itemID);
                itemsBuilder.append(String.format("| %-" + idMaxLength
                                + "s | %-" + nameMaxLength
                                + "s | $%-6.2f | %-9d|\n",
                        itemID, item.getName(), item.getPrice(), quantity));
                processedItems.add(itemID);
            }
        }

        double serviceCharge = getTotalPrice() * SERVICE_CHARGE;
        double gst = getTotalPrice() * GST;

        itemsBuilder.append(headerLine)
                .append(String.format("| %-" + (idMaxLength + nameMaxLength + 10) + "s $%-12.2f |\n",
                        "Service Charge (" + (SERVICE_CHARGE * 100) + "%):", serviceCharge))
                .append(String.format("| %-" + (idMaxLength + nameMaxLength + 10) + "s $%-12.2f |\n",
                        "GST (" + (GST * 100) + "%):", gst))
                .append(String.format("| %-" + (idMaxLength + nameMaxLength + 10) + "s $%-12.2f |\n",
                        "Grand Total:", getTotalPrice()));

        return headersBuilder + itemsBuilder.toString();
    }

    //test getReceipt
    //    public static void main(String[] args) {
    //        Order order = new Order();
    //        MenuItem item1 = new MenuItem("1", "item1", 1000.0);
    //        MenuItem item2 = new MenuItem("2", "item2555555", 20.0);
    //        MenuItem item3 = new MenuItem("3", "item3", 30000.0);
    //        order.add(item1);
    //        order.add(item2);
    //        order.add(item3);
    //        order.add(item1);
    //        order.add(item2);
    //        order.add(item3);
    //        order.add(item1);
    //        order.add(item2);
    //        order.add(item3);
    //        System.out.println(order.getReceipt());
    //    }

    //TODO: Implement getReceipt method with discount
    public String getReceipt(double discount) {
        return null;
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
