package stats;

import model.Item;
import model.MenuItem;
import model.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderStatisticsTest {

    public static final double SERVICE_CHARGE = 1.10;
    public static final double GST = 1.09;

    // make some dummy orders
    private ArrayList<Order> createOrderForTest() {
        ArrayList<Order> orders = new ArrayList<>();
        Order order1 = new Order("restaurant1", "address1", "user1", "orderType1");

        order1.add(new MenuItem("1", "item1", 1.0));
        order1.add(new MenuItem("1", "item1", 1.0));

        order1.add(new MenuItem("2", "item2", 2.0));
        order1.add(new MenuItem("2", "item2", 2.0));
        order1.add(new MenuItem("2", "item2", 2.0));
        order1.add(new MenuItem("2", "item2", 2.0));

        order1.add(new MenuItem("3", "item3", 3.0));
        order1.add(new MenuItem("4", "item4", 4.0));
        order1.add(new MenuItem("5", "item5", 5.0));

        Order order2 = new Order("restaurant2", "address2", "user2", "orderType2");
        order2.add(new MenuItem("1", "item1", 1.0));
        order2.add(new MenuItem("1", "item1", 1.0));
        order2.add(new MenuItem("1", "item1", 1.0));
        order2.add(new MenuItem("1", "item1", 1.0));
        order2.add(new MenuItem("1", "item1", 1.0));

        order2.add(new MenuItem("2", "item2", 2.0));
        order1.add(new MenuItem("2", "item2", 2.0));
        order1.add(new MenuItem("2", "item2", 2.0));

        order2.add(new MenuItem("3", "item3", 3.0));

        orders.add(order1);
        orders.add(order2);

        return orders;
    }


    @Test
    void testGetBestSellingItemsWithOrders() {
        OrderStatistics orderStatistics = new OrderStatistics(createOrderForTest());
        List<Item> bestSellingItems = orderStatistics.getBestSellingItems();
        // expected best selling items are item1 and item2
        assertEquals(2, bestSellingItems.size());
        assertEquals("item1", bestSellingItems.get(0).getName());
        assertEquals("item2", bestSellingItems.get(1).getName());
    }

    // test best selling items with empty orders
    @Test
    void testGetBestSellingItemsWithEmptyOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        OrderStatistics orderStatistics = new OrderStatistics(orders);
        List<Item> bestSellingItems = orderStatistics.getBestSellingItems();
        // expected best selling items are empty
        assertEquals(0, bestSellingItems.size());
    }

    // test for getGrossSales
    @Test
    void testGetGrossSales() {
        ArrayList<Order> orders = createOrderForTest();
        OrderStatistics orderStatistics = new OrderStatistics(createOrderForTest());
        assertEquals(String.format("%.2f", 36 * SERVICE_CHARGE * GST),
                String.format("%.2f", orderStatistics.getGrossRevenue()));
        Order newOrder = new Order("restaurant3", "address3", "user3", "orderType3");
        newOrder.add(new MenuItem("1", "item1", 1.0));
        newOrder.add(new MenuItem("1", "item1", 1.0));
        newOrder.add(new MenuItem("1", "item1", 1.0));
        newOrder.add(new MenuItem("5", "item5", 999.0));
        orders.add(newOrder);
        orderStatistics = new OrderStatistics(orders);
        assertEquals(String.format("%.2f", 1038 * SERVICE_CHARGE * GST),
                String.format("%.2f", orderStatistics.getGrossRevenue()));
    }

    // test for getGrossSales with empty orders
    @Test
    void testGetGrossSalesWithEmptyOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        OrderStatistics orderStatistics = new OrderStatistics(orders);
        assertEquals(0.0, orderStatistics.getGrossRevenue());
    }

    // test for getNetSales
    @Test
    void testGetNetSales() {
        ArrayList<Order> orders = createOrderForTest();
        OrderStatistics orderStatistics = new OrderStatistics(createOrderForTest());
        assertEquals(String.format("%.2f", 36 * SERVICE_CHARGE),
                String.format("%.2f", orderStatistics.getNetRevenue()));
        Order newOrder = new Order("restaurant3", "address3", "user3", "orderType3");
        newOrder.add(new MenuItem("1", "item1", 1.0));
        newOrder.add(new MenuItem("1", "item1", 1.0));
        newOrder.add(new MenuItem("1", "item1", 1.0));
        newOrder.add(new MenuItem("5", "item5", 999.0));
        orders.add(newOrder);
        orderStatistics = new OrderStatistics(orders);
        assertEquals(String.format("%.2f", 1038 * SERVICE_CHARGE),
                String.format("%.2f", orderStatistics.getNetRevenue()));
    }

    // test for getNetSales with empty orders
    @Test
    void testGetNetSalesWithEmptyOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        OrderStatistics orderStatistics = new OrderStatistics(orders);
        assertEquals(0.0, orderStatistics.getNetRevenue());
    }

    // test for totalOrders
    @Test
    void testTotalOrders() {
        ArrayList<Order> orders = createOrderForTest();
        OrderStatistics orderStatistics = new OrderStatistics(createOrderForTest());
        assertEquals(2, orderStatistics.getOrderCount());
        Order newOrder = new Order("restaurant3", "address3", "user3", "orderType3");
        newOrder.add(new MenuItem("1", "item1", 1.0));
        newOrder.add(new MenuItem("1", "item1", 1.0));
        newOrder.add(new MenuItem("1", "item1", 1.0));
        newOrder.add(new MenuItem("5", "item5", 999.0));
        orders.add(newOrder);
        orderStatistics = new OrderStatistics(orders);
        assertEquals(3, orderStatistics.getOrderCount());
    }

    // test for totalOrders with empty orders
    @Test
    void testTotalOrdersWithEmptyOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        OrderStatistics orderStatistics = new OrderStatistics(orders);
        assertEquals(0, orderStatistics.getOrderCount());
    }
}
