# Zeng Zheqi - Project Portfolio Page
## Project: DinEZ
### Overview
DinEz is a CLI application designed for restaurant managers to manage menus, orders, and statistics.
It is written in Java and has about 4kLoC.

### Summary of contribution
#### **Code contributed**: [RepoSense link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-02-23&tabOpen=true&tabType=authorship&tabAuthor=adamzzq&tabRepo=AY2324S2-CS2113-F14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

#### **New Features**:
- Design and implement `Parser` and related command type classes with regular expressions
  * What it does: parse user inputs into commands and arguments which can be executed by the respective logic components
  * Justification: The parsing capability streamlined the interaction between users and the system,
  improving usability and efficiency. Leveraging on regular expressions, it allows more flexible user inputs with minimal change in
  code while keeping its robustness to invalid inputs. For example, some common careless mistakes made in typing with
  extra or missing spaces between command words, or incorrect capitalisation, are taken care of with small tweaks in the
  regular expression, while still able to detect incorrect commands or arguments.
  * Highlights:
    * Making use of Java's Stream API and functional programming concepts, significantly shortens the code length in parsing
    inputs as compared to explicitly checking inputs in non-declarative programming ways, thereby also increases the readability of the code.
    * It laid the foundation for future development of command-based functionalities in the project by increasing
    the scalability.

- Implement the `Statistics` feature
  * What it does: Provides users with statistical insights such as total orders, revenue, bestsellers, etc.
  * Justification: Restaurant managers often need to track the performance of their restaurants, and this feature
  provides them with the necessary data to make informed decisions.
  * Highlights:
    * Add `StatsLogic`, `StatsCommand`, and `Statatistics` related classes in packages to handle the statistics feature,
    which separates the logic of statistics from other features, keeping the codebase modular and maintainable.
    * Utilised Java's generics to implement a flexible pair class to store the statistics data, which is extensible to
    handle more statistics in the future.

#### **Enhancements to existing features**:
- Improve the display format of receipt and menu in the `Order` and `Menu` features
  * Allow long item names and restaurant information to be displayed in multiple lines to prevent the receipt and menu
  from being too wide.
  * Sort the item IDs after deletion in the menu to ensure the item IDs are continuous and consistent.
  * Add subtotal, tax and gst to the receipt to provide a more detailed breakdown of the order.

- Implement limit-checking mechanism for the `Order` and `Menu` features
  * Prevent users from adding more items than the limit set by the system, thereby preventing digits overflow in the
  receipt and menu.
  * Prevent users from adding unreasonably expensive items to the menu.
  * Provide users with feedback on the limit reached when adding items.

#### **Project management**:
- Assisted in the release of `v1.0` and `v2.1` on GitHub.
- Created labels and categorised issues on GitHub
- Created and managed milestones v1.0 and assigned issues to the respective milestones

#### **Documentation**:
- User Guide:
  * Added documentation for the `Statistics` feature
  * Added documentation for the `Menu` and `Order` features
  * Added documentation for the `Help` command
- Developer Guide:
  * Added `UI` component description in the architecture section
  * Added descriptions and sequence diagram for the `StatsLogic` component
  * Added some user stories about the `Statistics`, `Menu`, and `Order` features

#### **Community**:
- Reviewed other team's DG and UG to give constructive feedback and suggestions





