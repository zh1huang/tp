# Yue Junfeng's Project Portfolio Page

## Project: CLIverShelf

CLIverShelf is a desktop application used for inventory management and quick data analysis tool for small businesses such as small bookstore and convenient stores. The user interacts with it using a Command-Line Interface. It is written in Java, and has about 6kLoC.

Given below are my contributions to the project.

* **Data structure**: Constructed the data structure model that was used in all features.
  * What it does: The model classes provides all the necessary methods that are required to implement all functionalities in this application.
* **New Feature**: Local data storage
  * What it does: Automatically saves all changes to a local JSON file after the execution of any command. It also manages the loading of data from the same JSON file to the application when the application is opened.
  * Justification: This feature improves the product significantly because the user no longer need to keep the application on forever to keep the application remember all the information. The user can also easily keep a snapshot of the data as backup at any time and no longer worry about any significant data loss.
  * Highlights: This enhancement does not affect any existing functionalities.
  * Credits: [org.json](https://mvnrepository.com/artifact/org.json/json) APIs were used to create JSON file and parse JSON files.
* **New Feature**: UI formatting
  * What it does: Aids the formatting of output from the application to make the application more user-friendly.
  * components included:
    * Messaging app like interface
    * Support for soft wrapping
* **New Feature**: Added a sales manager class that allows the user to record sales of inventory.
  * What it does: allow the user to mark an item as sold and record it into the system for future analysis.
* **New Feature**: Search function
  * What it does: Display all items that are related to the search keyword.
  * Justification: This feature made it easier for the users to manage items. The user may want to know if an item is still in stock, they will be able to know how much inventory they have left by searching for the item name. The user can also easily find out all the items that have the same remark. For example, the user may want to know all the items that contains the same remarks such as "damaged" or "limited edition" and use the information to make better business decisions.
* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=yuejunfeng0909&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=yuejunfeng0909&tabRepo=AY2122S1-CS2113T-F11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
* **Quality Assurance**:
  * PRs reviewed (with non-trivial review comments / contributions): [#27](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/27), [#28](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/28), [#34](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/34), [#63](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/63), [#71](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/71), [#197](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/197), [#199](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/199), [#201](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/201) and [#209](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/209)
  * Tested and reported bugs on GitHub issues. (Issues [#49](https://github.com/AY2122S1-CS2113T-F11-4/tp/issues/49), [#102](https://github.com/AY2122S1-CS2113T-F11-4/tp/issues/102) and [#243](https://github.com/AY2122S1-CS2113T-F11-4/tp/issues/243))
* **Project Management**:
  * Managed releases `v1.0`-`v2.1` (3 releases) on GitHub
* **Enhancement to existing features**:
  * Improved the model classes to automatically sort the items and shelves by alphabetical order. (Pull requests [#103](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/103) and [#203](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/203))
  * Improved UI by automatically soft-wrap long sentences to make the output more user-friendly. (Pull request [#188](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/188))
  * Fixed many bugs. (Pull requests [#113](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/113), [#114](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/114), [#229](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/229), [#242](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/242) and many more)
* **Documentation**:
  * User Guide
    * Added documentation for [search command](https://ay2122s1-cs2113t-f11-4.github.io/tp/UserGuide.html#search-for-item)
    * Reviewed the sections written by the other developers
  * Developer Guide
    * Added documentation for [architecture](https://ay2122s1-cs2113t-f11-4.github.io/tp/DeveloperGuide.html#architecture), [UI](https://ay2122s1-cs2113t-f11-4.github.io/tp/DeveloperGuide.html#ui-component), [model](https://ay2122s1-cs2113t-f11-4.github.io/tp/DeveloperGuide.html#model-component) and [storage](https://ay2122s1-cs2113t-f11-4.github.io/tp/DeveloperGuide.html#storage-component)
    * Contributed to [user stories](https://ay2122s1-cs2113t-f11-4.github.io/tp/DeveloperGuide.html#user-stories)