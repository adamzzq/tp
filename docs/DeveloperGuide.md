# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design

### Architecture
![Architecture](images\Architecture.png)

The **Architecture diagram** given above shows the high-level design of the application.

Given below is a quick overview of main components and how they interact with each other.

**`Main`** is in charge of the app launch, shut down, and taking user input.

* At the app launch, it loads the data from the file system, and initializes other components.
* At the app shut down, it saves the data to the file system, and shuts down other components.

The app's work is done by the following components:

* [**`MainLogic`**](#sublogic-component): The main logic command executor.
* [**`SubLogic`**](#sublogic-component): The sub logics(i.e. **`MenuLogic`**, **`OrderLogic`**) command executor.
* [**`Parser`**](#parser-component): The parser that parses user input to command.
* [**`Model`**](#model-component): The data model that stores the data.
* [**`Command`**](#command-component): Represents a command that the user can execute.
* [**`Storage`**](#storage-component): Reads data from, and writes data.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues 
the command `Create order -menu 01`, `add -item 001` and `complete`.

![Sequence Diagram](images\ArchitectureSequenceDiagram.png)

### UI Component
The UI component is responsible for parsing user input into commands that can be executed by the logic component.
There is a `analyzeInput` method in the `Parser` class that interprets the user input and classifies 
it into a `CommandType` enum.<br>
The `splitInput` method is used to split the user input into an array of strings, according to the command type.
It returns an array containing any arguments that are needed to execute the command.

### Logic Component
The logic component consists of classes that handle the logic of the application. The logic component is divided into
`MainLogic` and `SubLogic` which consists of `OrderLogic` and `MenuLogic`.

* [**`MainLogic`**](#model-component): A class to handle the first level commands, and pass the user input to corresponding
classes for analysis and execution.
* [**`OrderLogic`**](#model-component) and [**`MenuLogic`**](#model-component): A class to handle the second level commands,
and pass the user input to corresponding classes for analysis and execution.

### Command Component
The command component consists of two different command interfaces: `MainCommand` and `OrderCommand`. The `MainCommand`
interface is for the various command classes that are used in the `MainLogic`, while the `OrderCommand` interface is for
command classes used in `OrderLogic`.  

A `Command` object will be created in either the `MainLogic` or `OrderLogic`
based on what command the user has inputted(e.g., `MainHelpCommand` object is created when the user inputs the `help`
command). The `execute()` method of the `Command` object is then called to execute the command, which may require
certain arguments based on the type of command.

### Model Component
The model consists of classes describing the objects used in this application.
The general structure is that menu and order are separate, but they both work with `menuItem(s)`, which 
represent food items on the menu.

* [**`ItemManager`**](#model-component): An interface containing methods representing operations common to **`Menu`** 
  and **`Order`**. <br><br />
* [**`Item`**](#model-component): An abstract class representing a food item. It should be implemented by **`MenuItem`**.
  <br><br />
* [**`Menu`**](#model-component): A class representing the menu(s) of the restaurant, where each contains menuItem(s)
 that can be ordered. Multiple menus can exist and each has a unique ID. <br><br />
* [**`MenuItem`**](#model-component): A class inheriting item, and represents a food item on the menu. <br><br />
* [**`Order`**](#model-component): A class representing an order to be entered into the system to be kept track of. Each 
  order has a unique ID generated from the time of order.<br><br />
* [**`SetMenu`**](#model-component): An enumeration representing the different types of set menus available, examples of
  which includes *breakfast*, *lunch*, *dinner*.

The *Class Diagram* below shows how the model components interact with each other, including interactions such as 
dependencies, associations and inheritance.

![Class Diagram](images/modelcomponent.png)

## Implementation

### `MainLogic`
Generally, the main logic works as follows:
1. User enters an input which is received in the *ui* and parsed by the `Parser`.
2. The `Parser` classifies the command based on `CommandType`.
3. If it is a first level command, `execute` is called on the corresponding class.
4. If it is not a first level command, the command will be pass to `SubLogic` to handle.

**Create Order** <br>
`Mainlogic` takes user input and crate an `Order` class and pass it to `OrderLogic` to execute the command.

**View Order by ID** <br>
`Mainlogic` takes in the command and the order ID, execute the `view order` command by calling a static method<br>
in `ViewOrderCommand` class.

**View all orders** <br>
`Mainlogic` takes in the command and calls the `ViewOrdersSummaryCommand` class to execute the command<br>    
by querying the orderList.

**View Receipt** <br>
`Mainlogic` takes in the command and calls the `ViewReceiptCommand` class to execute the command

### `OrderLogic`
Generally, the order logic works as follows: 
1. User enters an input which is received in the *ui* and parsed by the `Parser`. 
2. The `Parser` classifies the command based on `CommandType`
3. Within `OrderLogic`, `execute` is called on the corresponding class
4. Control is passed to other sections of the code

**View Menu** <br>

Within the construct of the order logic, the menu can be accessed for viewing in order to select items from 
available menus. This is carried out with the `view menu` command.

**View Item**  

Within `OrderLogic`, a list containing all the items that have been added to the current active order can be viewed by executing
the `view item` command.

**Add**  

Inside `OrderLogic`, items from the menu can be added into the current active order.
This is carried out using the `add -item <item_id> -quantity <quantity_of_item>` command,
where `<item_id>` is an integer corresponding to the item's id in the menu,
and `<quantity_of_item>` is an integer of the amount of that item to be added.

**Delete**  

In `OrderLogic`, items from the current order can be removed via the
`delete -item <item_id> -quantity <quantity_of_item>` command. `<item_id>`
and `<quantity_of_item>` are the same type of parameters as the ones specified
in the `Add` command class.

**Complete**  

In `OrderLogic`, once the order is finished, it can be completed and closed
by executing the `complete` command. This marks the current order as completed
and the program returns back to `MainLogic` for subsequent command executions.

**Cancel**

In "OrderLogic", the user can cancel the current order by executing the `cancel` command.
This will abort the current order created and return to the main menu.

### `MenuLogic`
![MenuLogic Diagram](images/MenuLogicSequenceDiagram.png)
Generally, the menu logic works similar to order logic:
1. User enters an input which is received in the *ui* and parsed by the `Parser`.
2. The `Parser` classifies the command based on `CommandType`
3. Within `MenuLogic`, `execute` is called on the corresponding class
4. Control is passed to other sections of the code

**View Item**  

Within `MenuLogic`, a list containing all the items that have been added to the current active order can be viewed by executing
the `view item` command.

**Add**

Inside `MenuLogic`, items from the menu can be added into the current active order.
This is carried out using the `add -item <item_name> -price <price_of_item>` command,
where `<item_name>` is a string represent the name of the MenuItem,
and `<price_of_item>` is a double of the price of that item to be added.

**Delete**

In `MenuLogic`, items from the current order can be removed via the
`delete -item <item_id>` command. `<item_id>` is the index of the item in the menu.

**Complete**

Inside `MenuLogic`, once the order is finished, it can be completed and closed
by executing the `complete` command. This marks the current Menu as completed
and the program returns back to `MainLogic` for subsequent command executions.

**Cancel**
In `MenuLogic`, the user can cancel the current menu by executing the `cancel` command.
This will abort the current menu created and return to the main menu.

## Product scope
### Target user profile

* has a need to manage orders in a restaurant
* has a need to manage menus in a restaurant
* has a need to manage cashiering duty in a restaurant
* prefer CLI apps than GUI apps
* can type fast


### Value proposition

Manage orders and menus faster and more efficiently than traditional GUI applications for faster typer.

## User Stories

| Priority | As a ...         | I want to ...                             | So that I can ...                                               |
|----------|------------------|-------------------------------------------|-----------------------------------------------------------------|
| `* * *`  | restaurant owner | Add dishes to order/menu                  | easily refer and calculate the total price                      |
| `* * *`  | restaurant owner | Delete dishes to order/menu               | remove the dishes that the customers do not want                |
| `*   *`  | restaurant owner | view the order receipt                    | check the order details and the total price                     |
| `* * *`  | restaurant owner | manage cashiering duties in my restaurant | keep track of the money that comes in and out of the restaurant |
| `*   *`  | restaurant owner | manage menus in my restaurant             | keep track of the dishes that are available in the restaurant   |
| `*    `  | restaurant owner | view the order/menu                       | check the dishes that are available in the restaurant           |


## Non-Functional Requirements

* Should Work on any mainstream OS as long as it has Java 11 or above installed.
* Should be able to handle and save a large number of orders and menus for restaurant's daily operations.
* Faster type can use the CLI app more efficiently than GUI app

## Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS.


## Instructions for manual testing

### Launch and shutdown
* Initial launch
  1. Download the jar file and copy into an empty folder
  2. Open a terminal and navigate to the folder containing the jar file
  3. Run the command `java -jar DinEz.jar` to launch the application
* Shutdown
  1. Type `exit` and press enter to shutdown the application

### Create order and add/delete/view item
* To create an order
  1. Prerequisite: Type `create order -menu 01` in Main interface to enter the Order interface, 01 is the default menu
  2. Test Case: `Add -item 001 -quantity 2`
     Expected: Item 001 is added to the order with quantity 2
  3. Test Case: `View -item`
     Expected: Item 001 is displayed with quantity 2
  3. Test Case: `Delete -item 001 -quantity 1`
     Expected: Item 001 is removed from the order with quantity 1
