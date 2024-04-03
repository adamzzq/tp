# User Guide

## Introduction

DinEz is an integrated system solution which aims to address certain aspects of restaurant management encountered during
the daily operation of a restaurant in a digitalised way, including order taking, menu management, inventory monitoring
and order statistics etc.  
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
  * [Quit the program: `bye`](#quit-the-program-bye)
  * [Order statistics `[coming in v2.1]`](#order-statistics-coming-in-v21)
  * [Save data `[coming in v2.1]`](#save-data-coming-in-v21)
* [Features for Menu Interface](#features-for-menu-interface)
  * [Add items: `add`](#add-items-add)
  * [Delete items: `delete`](#delete-items-delete)
  * [Check menu items: `view item`](#check-menu-items-view-item)
  * [Complete current menu: `complete`](#complete-current-menu-complete)
  * [Abort current menu: `bye`](#abort-current-menu-bye)
* [Features for Order Interface](#features-for-order-interface)
  * [Add items: `add`](#add-items-add-1)
  * [Delete items: `delete`](#delete-items-delete-1)
  * [Check menu items: `view menu`](#check-menu-items-view-menu)
  * [Check order items: `view item`](#check-order-items-view-item)
  * [Complete current order: `complete`](#complete-current-order-complete)
  * [Abort current order: `bye`](#abort-current-order-bye)
* [FAQ](#faq)
* [Command summary](#command-summary)

## Quick Start

1. Ensure you have Java `11` installed on your computer.
2. Download the latest `DinEz.jar` from [here](https://github.com/AY2324S2-CS2113-F14-2/tp/releases)
3. Copy the file to the folder you want to use as the _home folder_ for the restaurant management system
4. Open a command terminal, `cd` into the folder in which you placed the `DinEz.jar` then use the command
`java -jar DinEz.jar` to run the program.
5. You should see a greeting message like the following one:
   ```
    Hello from DinEz
    current menu ID: 01
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
    bye: Quits the program.
   ```
6. Type the command in the CLI and press Enter to execute it e.g. typing `help` then pressing
   Enter will display the help menu.
   Here are some example commands you can try:
- `help`: Displays all the commands that can be used
- `create order -menu 01`: Creates a new order which uses the menu of ID `01`
  and navigates to the order interface to perform sub-commands
- `view -menu -all`: Shows a brief summary of all the created menus
- `bye`: Exits from the program

7. Refer to the [Features](#features) below for details of each command

## Features

### User help guide: `help`
Shows all the commands that can be used.

Format: `help`

### Create new menu: `create menu`
Creates a new menu and goes to the menu interface to perform sub-commands. (Refer to
[Features for Menu interface](#features-for-menu-interface) for the sub-commands)

Format: `create menu`

### Edit menu: `edit menu`
Edits a menu by going to the menu interface to perform sub-commands. (Refer to
[Features for Menu interface](#features-for-menu-interface) for the sub-commands)

Format: `edit -menu <menu_id>`

* The `<menu_id>` is a number representing the menu ID.
* The `<menu_id>` can be obtained from the `view -menu -all` command.

Example of usage:

`edit -menu 01`

### Create new order: `create order`
Creates a new order with a specified menu and goes to the order interface to perform sub-commands. (Refer to
[Features for Order interface](#features-for-order-interface) for the sub-commands)

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

### Quit the program: `bye`
Exits out of the program.

Format: `bye`

### Order statistics `[coming in v2.1]`
_Details coming soon..._

### Save data `[coming in v2.1]`
_Details coming soon..._

## Features for Menu interface

### Add items: `add`
Adds an item with specified ID, name, and price to the current menu.

Format: `add -item <item_id> -name <item_name> -price <price_of_item>`

* The `<item_id>` is unique a number to identify the item. You cannot add two items with the same ID.
* The `<item_name>` cannot contain symbols (e.g. `~!@#$%^&*()`)
* The `<price_of_item>` should be the price of the item without the `$` symbol.

Example of usage:

`add -item 001 -name Chicken Rice -price 3.50`

`add -item 007 -name Seafood Fried Rice -price 5`

### Delete items: `delete`
Deletes an item of a specified ID from the current menu.

Format: `delete -item <item_id>`

* The `<item_id>` is a number representing the menu item. You can obtain this ID with the help of the `view item`
  command.

Example of usage:

`delete -item 001`

### Check menu items: `view item`
Shows all the items in the current menu.

Format: `view item`

### Complete current menu: `complete`
Marks the current menu as completed and returns to the main interface.

Format: `complete`

* The `complete` command cannot be used on an empty menu. You will be prompted to add items to the menu.

### Abort current menu: `bye`
Cancels the current menu's creation and returns to the main interface.

Format: `bye`

* The cancelled menu will be discarded and cannot be retrieved.

## Features for order interface

### Add items: `add`
Adds a specified quantity of a specific menu item into the order.

Format: ` add -item <item_id> -quantity <quantity_of_item>`

* The `<item_id>` is unique a number to identify the item. You can obtain this ID using the `view menu` command.
* The `<quantity_of_item>` is a number representing the amount of the specified item to add

Example of usage:

`add -item 001 -quantity 2`

### Delete items: `delete`
Deletes a specified quantity of a specified item in the order.

Format: `delete -item <item_id> -quantity <quantity_of_item>`

* The `<item_id>` is a number representing the menu item. You can obtain this ID with the help of the `view menu`
  command.

Example of usage:

`delete -item 001 -quantity 1`

### Check menu items: `view menu`
Shows the menu used by the current order.

Format: `view menu`

### Check order items: `view item`
Shows all the items in the current order.

Format: `view item`

### Complete current order: `complete`
Marks the current order as completed and returns to the main interface.

Format: `complete`

* The `complete` command cannot be used on an empty order. You will be prompted to add items to the order.

### Abort current order: `bye`
Cancels the current order's creation and returns to the main interface.

Format: `bye`

* The cancelled order will be discarded and cannot be retrieved.

## FAQ

**Q**: How do I save the menus and orders I have created? 

**A**: As of `v2.0`, a save feature has not been implemented yet. Newly created orders and menus will be discarded once
the program terminates.  
The save feature will be implemented in `v2.1`, where the save data will be stored in the
same directory where you put the `DinEz.jar` file.

## Command Summary

* User help guide `help`
* Check menus `view -menu <menu_id>` and `view -menu -all`
* Create new menu `create menu`
* Edit menu `edit -menu <menu_id>`
  * **Menu interface sub-commands**
  * Add items `add -item <item_id> -name <item_name> -price <price_of_item>`
  * Delete items `delete -item <item_id>`
  * Check menu items `view item`
  * Complete current menu `complete`
  * Abort current menu `bye`
* Check orders `view -order <order_id>` and `view -order -all`
* Create new order `create order -menu <menu_id>`
  * **Order interface sub-commands**
  * Add items `add -item <item_id> -quantity <quantity_of_item>`
  * Delete items `delete -item <item_id> -quantity <quantity_of_item>`
  * Check menu items `view menu`
  * Check order items `view item`
  * Complete current order `complete`
  * Abort current order `bye`
* Get order receipt `receipt -order <order_id>`
* Quit the program `bye`
