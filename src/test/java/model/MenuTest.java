package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuTest {
    @Test
    public void testEmptyMenu() {
        Menu menu = new Menu("001");

        assertEquals(
            "+------------------------------------------+\n" +
                    "|                   MENU                   |\n" +
                    "+------+-----------------------------------+\n" +
                    "| ID   |         Name          | Price     |\n" +
                    "+------+-----------------------------------+\n" +
                    "+------+-----------------------------------+\n"
                    , menu.toString());

    }
    @Test public void testMenuWithOneItem() {
        Menu menu = new Menu("02");
        menu.add(new MenuItem("1","Nasi Lemak",3.00));
        assertEquals(
            "+------------------------------------------+\n" +
                    "|                   MENU                   |\n" +
                    "+------+-----------------------------------+\n" +
                    "| ID   |         Name          | Price     |\n" +
                    "+------+-----------------------------------+\n" +
                    "| 1    | Nasi Lemak            | $3.00     |\n" +
                    "+------+-----------------------------------+\n",
                    menu.toString());

    }
    @Test
    public void testMenuAddAndRemove() {

        MenuItem dish001 = new MenuItem("001", "Chicken Rice", 3.50);
        MenuItem dish002 = new MenuItem("002", "Nasi Lemak", 3.00);
        MenuItem dish004 = new MenuItem("004", "Mee Siam", 3.50);
        Menu menu = new Menu("001");

        menu.add(dish001);
        assertEquals("+------------------------------------------+\n" +
                    "|                   MENU                   |\n" +
                    "+------+-----------------------------------+\n" +
                    "| ID   |         Name          |   Price   |\n" +
                    "+------+-----------------------------------+\n" +
                    "| 001  | Chicken Rice          |$3.50      |\n" +
                    "+------+-----------------------------------+\n", menu.toString());
        menu.add(dish002);
        assertEquals("+------------------------------------------+\n" +
                    "|                   MENU                   |\n" +
                    "+------+-----------------------------------+\n" +
                    "| ID   |         Name          |   Price   |\n" +
                    "+------+-----------------------------------+\n" +
                    "| 001  | Chicken Rice          |$3.50      |\n" +
                    "| 002  | Nasi Lemak            |$3.00      |\n" +
                    "+------+-----------------------------------+\n", menu.toString());
        menu.add(dish004);
        assertEquals("+------------------------------------------+\n" +
                    "|                   MENU                   |\n" +
                    "+------+-----------------------------------+\n" +
                    "| ID   |         Name          |   Price   |\n" +
                    "+------+-----------------------------------+\n" +
                    "| 001  | Chicken Rice          |$3.50      |\n" +
                    "| 002  | Nasi Lemak            |$3.00      |\n" +
                    "| 004  | Mee Siam              |$3.50      |\n" +
                    "+------+-----------------------------------+\n", menu.toString());
        menu.remove("002");
        assertEquals("+------------------------------------------+\n" +
                    "|                   MENU                   |\n" +
                    "+------+-----------------------------------+\n" +
                    "| ID   |         Name          |   Price   |\n" +
                    "+------+-----------------------------------+\n" +
                    "| 001  | Chicken Rice          |$3.50      |\n" +
                    "| 004  | Mee Siam              |$3.50      |\n" +
                    "+------+-----------------------------------+\n", menu.toString());
        menu.remove("004");
        assertEquals("+------------------------------------------+\n" +
                    "|                   MENU                   |\n" +
                    "+------+-----------------------------------+\n" +
                    "| ID   |         Name          |   Price   |\n" +
                    "+------+-----------------------------------+\n" +
                    "| 001  | Chicken Rice          |$3.50      |\n" +
                    "+------+-----------------------------------+\n", menu.toString());
    }



}
