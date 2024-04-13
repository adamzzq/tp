import model.Item;
import model.MenuItem;
import model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderStatistics {
    private final ArrayList<Order> orders;
    private final ArrayList<MenuItem> itemList = new ArrayList<>();
    private final List<Pair<MenuItem, Integer>> itemFrequencies = new ArrayList<>();
    private final double grossSales;

    public OrderStatistics(ArrayList<Order> orders) {
        this.orders = orders;
        this.grossSales = getGrossSales();

        for (Order order : orders) {
            itemList.addAll(order.getOrderItemList());
        }

        itemList.sort(MenuItem::compareTo);
        setItemFrequencies();
    }

    private void setItemFrequencies() {
        for (MenuItem item : itemList) {
            Pair<MenuItem, Integer> newPair = new Pair<>(item, 1);

            for (Pair<MenuItem, Integer> pair : itemFrequencies) {
                if (pair.getFirst().compareTo(item) == 0) {
                    // If the item is already in the list, increment its frequency
                    newPair = new Pair<>(pair.getFirst(), pair.getSecond() + 1);
                    itemFrequencies.removeIf(oldPair -> oldPair.getFirst().compareTo(item) == 0);
                    break;
                }
            }

            itemFrequencies.add(newPair);
        }
    }

    private void sortFrequenciesDescending() {
        itemFrequencies.sort((item1, item2) -> item2.getSecond() - item1.getSecond());
    }

    public List<Item> getBestSellingItem() {
        sortFrequenciesDescending();
        List<Item> bestSellingItems = new ArrayList<>();

        if (itemFrequencies.isEmpty()) {
            return bestSellingItems;
        }

        // check if there are multiple best-selling items,
        for (Pair<MenuItem, Integer> itemFrequency : itemFrequencies) {
            if (Objects.equals(itemFrequency.getSecond(), itemFrequencies.get(0).getSecond())) {
                bestSellingItems.add(itemFrequency.getFirst());
            } else {
                break;
            }
        }

        return bestSellingItems;
    }

    private double getGrossSales() {
        double totalSales = 0.0;
        for (Order order : orders) {
            totalSales += order.getTotalPrice();
        }
        return totalSales;
    }

    public double getNetSales() {
        return grossSales / (1 + 0.09);
    }

    public double getProfit(double cost) {
        return getNetSales() - cost;
    }
}
