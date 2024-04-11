# Zheng Wentao - Project Portfolio Page

## Overview

### Project: DinEZ

DinEz is a CLI application intended to help someone manage the menus and orders of a restaurant.
It is written in Java and has about 2kLoC.

### Summary of Contributions

* **New Feature**: <<*Menu Commands*>> Added the different Menu commands, including MenuHelp, MenuComplete,MenuExit, 
  MenuDelete
   * What it does: Allows the user to navigate around the Menu sub-interface, excluding Menu adding feature
   * Justification: An independent set of commands for Menu and Order sub-interfaces allows different
     logic to be implemented for the 2 sub-systems
   * Highlights: This enhancement paved the way for the implementation of the Menu sub-interface
   * Credits: All the code was manually written but modifications were made by other members of the team.
* **New Feature**: <<*Additional order details and receipt formatting*>> Implement additional information that would be 
  prompted for when building an order
   * What it does: Prompts the user for additional details such as restaurant name, restaurant address, order
     type and name of user.
   * Justification: This feature improves the ability of the CLI application to model a real cashiering system. 
     These features also make the CLI application more customizable to a variety of potential users.
   * Highlights: This enhancement introduced new variables to be kept track of within the implementation of order and
     order-related components, and is ultimately reflected in the order receipt.
   * Credits: Implementation was mostly done manually, but the proper formatting was done by Zeng Zheqi.
 * **Code contributed**: 
    [RepoSense link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-02-23&tabOpen=true&tabType=authorship&tabAuthor=Zhengwinter&tabRepo=AY2324S2-CS2113-F14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
 * **Project Management**: Assisted in managing `v1.0` and `v2.1`
 * **Enhancement to existing feature**: Modified Menu class and MenuLogic (Pull requests [#74](https://github.com/AY2324S2-CS2113-F14-2/tp/pull/74))
 * **Documentation**: 
     * **Developer Guide**: 
       * Added documentation for all the model components including `ItemManager`, `Menuitem`, `Menu`, `Order`
     * **Model Diagram**: Included a class diagram for the model components
     * **User guide**: Added FAQ to justify why edit order is not implemented
 
* **Community**: 
     * PR reviewed (with no trivial review comments): [#143](https://github.com/AY2324S2-CS2113-F14-2/tp/pull/143),
                                                    [#144](https://github.com/AY2324S2-CS2113-F14-2/tp/pull/144)
     * Provided detailed explanation and justification for the majority of PRs (a few examples shown below):
        [#72](https://github.com/AY2324S2-CS2113-F14-2/tp/pull/72), 
        [#85](https://github.com/AY2324S2-CS2113-F14-2/tp/pull/85), 
        [#141](https://github.com/AY2324S2-CS2113-F14-2/tp/pull/141)