# Webster Tan's Project Portfolio Page

## Overview

### Project DinEz

DinEz is a Command Line Interface (CLI) application used for managing various aspects of restaurant management such as
order taking, menu management, and order statistics. This app is designed for users who are familiar with CLI and can
type quickly. It is written in Java and has about 3 kLoC.  

### Summary of contributions

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-02-23&tabOpen=true&tabType=authorship&tabAuthor=webtjs&tabRepo=AY2324S2-CS2113-F14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **New Feature**: <<*Menu Commands*>> Added create menu command
  * What it does: Allows users to create a new menu where they can add new items into the menu.
  * Justification: Restaurants have different menus depending on the circumstance (Breakfast Menu, Lunch Menu,
  Dinner Menu etc.). Hence, this feature enables restaurant managers to create different menus according to their needs.
  * Highlights: Utilised Java's `Optional` class to indicate an absence of return value and to chain method calls,
  thereby shortening the code.

* **New Feature**: <<*Menu Commands*>> Added edit menu command
  * What it does: Allows users to make changes to a menu they have created such as adding new items or deleting existing
    items.
  * Justification: Restaurants occasionally revamp their menus due to various reasons such as experimenting new items,
    price adjustments, and cutting out items that are not selling well.
  * Highlights: 
  * Credits: Modifying the menu (e.g. adding items, deleting items etc.) reuses the code from `MenuLogic`, which was
    mostly written by Zhengwinter and modified by all other team members.

* **New Feature**: Added storage feature for restaurant details, menus, and orders
  * What it does: Allows users to store their restaurant details (restaurant name & address), menus they create, and
  orders created into a local save file. This save file can be read and the data will be loaded into the program when
  the user launches the application again.
  * Justification: Restaurant managers need to be able to be able to keep track of past orders for reasons such as
  accounting and data analysis, which can then in turn boost sales.
  * Highlights: This enhancement required careful error handling due to the need to read and write to files.

* **Enhancements to existing features**:
  * Added more descriptive error messages for invalid commands (Pull requests
    [#132](https://github.com/AY2324S2-CS2113-F14-2/tp/pull/132), [#135](https://github.com/AY2324S2-CS2113-F14-2/tp/pull/135))
  * Feature to revert changes made to menu when editing a menu via a `cancel` command
    (Pull request [#137](https://github.com/AY2324S2-CS2113-F14-2/tp/pull/137))

* **Documentation**:
  * User Guide:
    * Added documentation for Introduction, Quick Start, Features, and Command Summary
  * Developer Guide:
    * Fix issues with Developer Guide raised by TA
    * Added implementation details and sequence diagram for `OrderLogic`.
    * Added manual testing instructions for viewing created menus and orders.
    * Added manual testing instructions for saving data, loading data, and corrupting data.

* **Community**:
  * Assisted in resolving forum issues(e.g. [forum issue #38](https://github.com/nus-cs2113-AY2324S2/forum/issues/38#issuecomment-2049258461))
  * PRs reviewed (with non-trivial review comments): [#164](https://github.com/AY2324S2-CS2113-F14-2/tp/pull/164),
    [#168](https://github.com/AY2324S2-CS2113-F14-2/tp/pull/168)

* **Project Management**: 
  * Managed release `v2.0` on GitHub
