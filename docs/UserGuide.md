# User Guide

## Introduction

DinEz is an integrated system solution which aims to address certain aspects of restaurant management encountered during
the daily operation of a restaurant in a digitalised way, including order taking, menu management and order statistics.

If you are well-versed with CLI and can type fast, DinEz can help to coordinate the various tasks and allow you to
access all the necessary information in one application. The CLI is also reliable and simpler to operate than a
traditional GUI.

* [Quick start](#quick-start)
* [Features](#features)
  * [User help guide: `help`](#user-help-guide-help)
  * [Create new menu: `create menu`](#create-new-menu-create-menu)
  * [Edit menu: `edit menu`](#edit-menu-edit-menu)
  * [Create new order: `create order`](#create-new-order-create-order)
  * [Check orders: `view order`](#check-orders-view-order)
  * [Get order receipt: `receipt order`](#get-order-receipt-receipt-order)
  * [Check menus: `view menu`](#check-menu-items-view-menu)
  * [Check restaurant info: `view restaurant`](#check-restaurant-info-view-restaurant)
  * [Edit restaurant info: `edit restaurant`](#edit-restaurant-info-edit-restaurant)
  * [Check order statistics: `view performance`](#check-order-statistics-view-performance)
  * [Quit the program: `bye`](#quit-the-program-bye)
  * [Save data](#save-data)
  * [Editing the data file](#editing-the-data-file)
* [Features in Menu Interface](#features-in-menu-interface)
  * [Menu help guide: `help`](#menu-help-guide-help)
  * [Add items: `add`](#add-items-add)
  * [Delete items: `delete`](#delete-items-delete)
  * [Check menu items: `view item`](#check-menu-items-view-item)
  * [Complete current menu: `complete`](#complete-current-menu-complete)
  * [Abort current menu: `cancel`](#abort-current-menu-cancel)
* [Features in Order Interface](#features-in-order-interface)
  * [Order help guide](#order-help-guide-help)
  * [Add items: `add`](#add-items-add-1)
  * [Delete items: `delete`](#delete-items-delete-1)
  * [Check menu items: `view menu`](#check-menu-items-view-menu)
  * [Check order items: `view item`](#check-order-items-view-item)
  * [Complete current order: `complete`](#complete-current-order-complete)
  * [Abort current order: `cancel`](#abort-current-order-cancel)
* [Features in Statistics interface](#features-in-statistics-interface)
  * [Bestselling item: `bestselling`](#find-bestselling-items-bestselling)
  * [Total orders: `total orders`](#get-total-orders-count-total-orders)
  * [Gross revenue: `revenue gross`](#calculate-gross-revenue-revenue--gross)
  * [Net revenue: `revenue net`](#calculate-net-revenue-revenue--net)
  * [View profit: `view profit cost <cost>`](#calculate-profit-view-profit-cost-cost)
  * [Return to main interface: `quit`](#return-to-main-interface-quit)
* [FAQ](#faq)
* [Command summary](#command-summary)

## Quick Start

1. Ensure you have Java `11` installed on your computer.
2. Download the latest `DinEz.jar` from [here](https://github.com/AY2324S2-CS2113-F14-2/tp/releases)
3. Copy the file to the folder you want to use as the _home folder_ for the restaurant management system
4. Open a command terminal, `cd` into the folder in which you placed the `DinEz.jar` then use the command
`java -jar DinEz.jar` to run the program.
5. You should be prompted to enter a restaurant name, address, and a username. After entering the required information,
   you should see a greeting message. The following is an example of what you should see:
   ```
    Hello from DinEz
    Enter restaurant name: 
    Fine Food
    Enter address of restaurant:
    Avenue 0
    Enter user name:
    Tom
    Here are the list of available commands:
    help: Shows all the commands that can be used.
    create order -menu <menu_id>: Creates a new order using the specified menu.
    view -order -all: Shows a brief summary of all the created orders.
    view -order <order_id>: Shows all the contents of a specified order.
    receipt -order <order_id>: shows the receipt of the specified order.
    create menu: Creates a new menu.
    edit -menu <menu_id>: Modify the specified menu's items in the menu interface.
    view -menu <menu_id>: Shows all the contents of a specified menu.
    view -menu -all: Shows a brief summary of all the created menus.
    view performance: Enters the order statistics interface.
    bye: Quits the program.
   ```
6. **IMPORTANT!!!** Our application consists of **three interfaces**(i.e. main,order and menu) which consists of
   different commands. `create order` enters order interface, `create menu` enters menu interface. Type `help` to check
   available commands at current interface.
7. **IMPORTANT!!!** We include a default menu with ID 0 for user to test our application.
8. Type the command in the CLI and press Enter to execute it e.g. typing `help` then pressing
   Enter will display the help menu.
   Here are some example commands you can try:
- `help`: Displays all the commands that can be used
- `create order -menu 01`: Creates a new order which uses the menu of ID `01`
  and navigates to the order interface to perform sub-commands
- `view -menu -all`: Shows a brief summary of all the created menus
- `bye`: Exits from the program

9. Refer to the [Features](#features) below for details of each command


> [!NOTE]
> * All the spaces in the commands are for clarity and are **optional** to include in the actual command.<br>
> * Commands are **case-insensitive**.<br>
> * The `<angle-brackets>` are used to denote the parameters that should be replaced with the **actual user input**.


Accepted formats examples:<br>
`create order -menu 01`
`create order-menu01`
`createorder-menu01`
`CREATE ORDER -MENU 01`
`Createorder -menu 01`(Non-exhaustive list)


## Features

### User help guide: `help`
Shows all the commands that can be used.

Format: `help`

### Create new menu: `create menu`
Creates a new menu and goes to the menu interface to perform sub-commands. (Refer to
[Features for Menu interface](#features-in-menu-interface) for the sub-commands)

Format: `create menu`

### Edit menu: `edit menu`
Edits a menu by going to the menu interface to perform sub-commands. (Refer to
[Features for Menu interface](#features-in-menu-interface) for the sub-commands)

Format: `edit -menu <menu_id>`

* The `<menu_id>` is a number representing the menu ID.
* The `<menu_id>` can be obtained from the `view -menu -all` command.

Example of usage:

`edit -menu 01`

### Create new order: `create order`
Creates a new order with a specified menu and goes to the order interface to perform sub-commands. (Refer to
[Features for Order interface](#features-in-order-interface) for the sub-commands)

Format: `create order -menu <menu_id>`

* The `<menu_id>` is a number representing the menu ID. You can obtain this ID with the help of the `view -menu -all`
  command.

Example of usage:

`create order -menu 01`

### Check orders: `view order`
Shows all the contents of a specified order, or shows the brief summary of all orders.

Format: `view -order <order_id>` and `view -order -all`

* The `<order_id>` is a unique number representing the order. This ID can be obtained from the
  `view -order -all` command.

Example of usage:

`view -order -all`

`view -order 20240403022723`

### Get order receipt: `receipt order`
Shows the receipt of a specified order.

Format: `receipt -order <order_id>`

* The `<order_id>` is a unique number representing the order. This ID can be obtained from the
  `view -order -all` command.

Example of usage:

`receipt -order 20240403022723`

### Check menus: `view menu`
Shows all the contents of a specified menu, or shows the brief summary of all menus.

Format: `view -menu <menu_id>` and `view -menu -all`

* The `<menu_id>` is a unique number representing the menu. This ID can be obtained from the
  `view -menu -all` command.

Example of usage:

`view -menu -all`

`view -menu 02`

### Check restaurant info: `view restaurant`
Shows the restaurant name and address currently in use.

Format: `view restaurant`

### Edit restaurant info: `edit restaurant`
Changes the restaurant name and address.

Format: `edit restaurant`

### Check order statistics: `view performance`
Enters the order statistics interface where various statistics based on all completed orders can be viewed.(Refer to
[Features in statistics interface](#features-in-statistics-interface) for the sub-commands)

Format: `view performance`

### Quit the program: `bye`
Exits out of the program.

Format: `bye`

### Save data
Data for restaurant information, orders, and menus are saved in the hard disk automatically after any command that
changes the data. There is no need to save manually.

### Editing the data file
Data for restaurant information, orders, and menus are saved automatically in 3 separate text files `restaurant.txt`,
`orders.txt`, and `menus.txt`. These 3 files are located in `[JAR file location]/data/*.txt` (where `*.txt` represents
the name of the data file).
> [!CAUTION]
> * If your changes to the data files makes its format invalid, our application will discard the files that have been
    corrupted and start with the respective empty data files at the next run. Therefore, it is recommended to make a
    backup of your data files before editing it.
> * Additionally, some edits can cause DinEz to behave in unexpected ways (e.g., if order ID is changed to a string).
    Hence, only edit the data files if you are confident that you can update it correctly.

## Features in Menu interface

### Menu help guide: `help`
Shows all the commands that can be used in the menu interface.

Format: `help`

### Add items: `add`
Adds an item with specified ID, name, and price to the current menu.

Format: `add -item <item_name> -price <price_of_item>`

* The `<item_id>` is unique a number to identify the item. You cannot add two items with the same ID.
* The `<item_name>` should contain English alphabets and spaces only.
* The `<price_of_item>` should be the price of the item without the `$` symbol.

Example of usage:

`add -item Chicken Rice -price 3.50`

`add -item Seafood Fried Rice -price 5`

### Delete items: `delete`
Deletes an item of a specified ID from the current menu.

Format: `delete -item <item_id>`

* The `<item_id>` is a number representing the menu item. You can obtain this ID with the help of the `view item`
  command.

Example of usage:

`delete -item 1`

### Check menu items: `view item`
Shows all the items in the current menu.

Format: `view item`

### Complete current menu: `complete`
Marks the current menu as completed and returns to the main interface.

Format: `complete`

* The `complete` command cannot be used on an empty menu. You will be prompted to add items to the menu.

### Abort current menu: `cancel`
Cancels the current menu's creation and returns to the main interface.

Format: `cancel`

* The cancelled menu will be discarded and cannot be retrieved.

## Features in Order interface

### Order help guide: `help`
Shows all the commands that can be used in the order interface.

Format: `help`

### Add items: `add`
Adds a specified quantity of a specific menu item into the order.

Format: ` add -item <item_id> -quantity <quantity_of_item>`

* The `<item_id>` is unique a number to identify the item. You can obtain this ID using the `view menu` command.
* The `<quantity_of_item>` is a number representing the amount of the specified item to add

Example of usage:

`add -item 1 -quantity 2`

### Delete items: `delete`
Deletes a specified quantity of a specified item in the order.

Format: `delete -item <item_id> -quantity <quantity_of_item>`

* The `<item_id>` is a number representing the menu item. You can obtain this ID with the help of the `view menu`
  command.

Example of usage:

`delete -item 1 -quantity 1`

### Check menu items: `view menu`
Shows the menu used by the current order.

Format: `view menu`

### Check order items: `view item`
Shows all the items in the current order.

Format: `view item`

### Complete current order: `complete`
Marks the current order as completed and returns to the main interface.

Format: `complete` or `complete -discount <discount>`

* The `complete` command cannot be used on an empty order. You will be prompted to add items to the order.
* The `<discount>` is a number representing the discount percentage. It should be an integer without the `%`
  symbol, ranging from 0 to 99.

### Abort current order: `cancel`
Cancels the current order's creation and returns to the main interface.

Format: `cancel`

* The cancelled order will be discarded and cannot be retrieved.

<!-- for reference in stats feature
        System.out.println("Available commands:");
        System.out.println("\tbestselling - View the best selling item(s)");
        System.out.println("\ttotal orders - View the total number of orders");
        System.out.println("\trevenue -gross - View the gross revenue");
        System.out.println("\trevenue -net - View the net revenue");
        System.out.println("\tview profit -cost <cost> - View the profit based on the cost");
        System.out.println("\tquit - return to main interface");
-->
## Features in Statistics interface

### Find bestselling items: `bestselling`
Shows the best-selling item(s) in the restaurant of all time.
The best-selling item is defined as the item with the highest quantity sold. If there are multiple items with the same
highest quantity sold, the top 3 items will be displayed.

Format: `bestselling`

### Get total orders count: `total orders`
Shows the total number of orders that have been created.

Format: `total orders`

### Calculate Gross revenue: `revenue -gross`
Shows the gross revenue of the restaurant. (i.e. total revenue before deducting costs and taxes)

Format: `revenue -gross`

### Calculate Net revenue: `revenue -net`
Shows the net revenue of the restaurant. (i.e. total revenue after deducting taxes)

Format: `revenue -net`

### Calculate profit: `view profit cost <cost>`
Shows the profit based on the cost of the items in the restaurant. (i.e. total revenue after deducting costs and taxes)

Format: `view profit -cost <cost>`

* The `<cost>` is the money spent on preparing the items.
* It should be a non-negative number without the `$` symbol.

Example of usage:
`view profit -cost 150`

### Return to main interface: `quit`
Returns to the main interface from the statistics interface.

Format: `quit`


## FAQ

**Q**: How do I save restaurant information, as well as the menus and orders I have created? 

**A**: Data for restaurant information, orders, and menus are saved in the hard disk automatically after any command
that changes the data. There is no need to save manually. The data files are stored in the `data` directory, which is
located in the same directory where you put the `DinEz.jar` file.

**Q**: How many menus can I have?

**A**: Just like the orders, there is no limit to the number of menus that can be created. The goal of this system is
to be able to allow the user to create multiple menus from which items for the order can be chosen from.

**Q** What happens if I try to add similar items to the menu?

**A**: This is not allowed. The item name and ID are unique in each menu.

**Q**: Can I delete an order that I have entered?

**A**: No, once an order has been created, it cannot be deleted. This is to ensure that the order history is preserved 
as they are important for the restaurant's records.

**Q**: Can I edit an order that I have entered?

**A**: To preserve the integrity of the generated orders, we do not offer a feature to edit orders that have been 
entered. A confirmation message is hence present for the user to verify that their inputs are correct.

**Q**: What is the difference between net revenue and profit?

**A**: As mentioned in the user guide for those features, the difference between net revenue and profit is that the 
former includes costs of production, but the latter excludes it.


## Command Summary

### Main interface commands
* User help guide `help`
* Check menus `view -menu <menu_id>` and `view -menu -all`
* Create new menu `create menu` <<[Menu Interface](#menu-interface-sub-commands)>>
* Edit menu `edit -menu <menu_id>` <<[Menu Interface](#menu-interface-sub-commands)>>
* Check orders `view -order <order_id>` and `view -order -all`
* Create new order `create order -menu <menu_id>`<<[Order Interface](#order-interface-sub-commands)>>
* Get order receipt `receipt -order <order_id>`
* Check restaurant info `view restaurant`
* Edit restaurant info `edit restaurant`
* Check order statistics `view performance`<<[Statistics interface](#statistics-interface-sub-commands)>>
* Quit the program `bye` 

### Menu interface sub-commands
  * Add items `add -item <item_name> -price <price_of_item>`
  * Delete items `delete -item <item_id>`
  * Check menu items `view item`
  * Complete current menu `complete`
  * Abort current menu `cancel` <br>

### Order interface sub-commands
  * Add items `add -item <item_id> -quantity <quantity_of_item>`
  * Delete items `delete -item <item_id> -quantity <quantity_of_item>`
  * Check menu items `view menu`
  * Check order items `view item`
  * Complete current order `complete` or `complete -discount <discount>`
  * Abort current order `cancel`

### Statistics interface sub-commands
* Find bestselling items: `bestselling`
* Get total orders count: `total orders`
* Calculate Gross revenue: `revenue -gross`
* Calculate Net revenue: `revenue -net`
* Calculate profit: `view profit -cost <cost>`
* Return to main interface: `quit`
