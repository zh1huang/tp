# Zhang Haoyu - Project Portfolio Page

## Overview

CLIverShelf is a desktop application used for inventory management and quick data analysis tool for small businesses
such as small bookstore and convenient stores. The user interacts with it using a Command-Line Interface.

### Summary of Contributions

Given below are my contributions to the project.

* Code
  contributed: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=haoyusimon&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other)
* Enhancements implemented:
    * **New Feature**: Create a new shelf
    * What it does: Allows the user to create a new shelf before adding new items.
    * Justification: This feature is the most basic function of the application. The user needs to create a shelf before
      adding items.
    * Highlights: Additional checks have been added to prevent invalid name inputs.

    * **New Feature**: Delete a shelf
    * What it does: Allow the user to delete a shelf.
    * Justification: This feature enables the user to manipulate his shelves freely.
    * Highlights: Additional checks have been added to prevent removal of non-empty shelves, removal of non-existent
      shelves and removal of soldItem shelf.

    * **New Feature**: Add a new item/multiple items with ID automatically generated
    * What it does: Allow the user to add one item or multiple identical items to a shelf. After adding the items, the
      user can get item IDs and can use the ID to sell the item later on.
    * Justification: Adding multiple items to a shelf at once is very convenient for users who want to add lots of
      items. The IDs generated after adding items also helps the user to label the items easily and also enables them to
      sell the items quickly later on without having to spend time to identify items.
    * Highlights: Automatically generate ID for each item; Additional checks have been added to prevent illegal
      operations such as exceeding shelf size limit, adding to non-existent shelves and adding items with invalid
      properties.

    * **New Feature**: Delete a specific item from a specific shelf
    * What it does: Allow the user to delete an item from a shelf.
    * Justification: Deleting an item is a basic function for the user to manipulate the items.
    * Highlights: Additional checks have been added to prevent deletion of non-existent item or deletion of items from
      non-existent shelf.

    * **New Feature**: Edit the properties of an item
    * What it does: Allow the user to edit the properties of an item in a shelf.
    * Justification: Editing an item is a basic function for the user to manipulate the items.
    * Highlights: Additional checks have been added to prevent the user from editing non-existent items or editing
      invalid properties.

    * **New Feature**: Sell an item
    * What it does: Allow the user to use the item ID to sell the item.
    * Justification: Sell an item is a basic and important function for the user to manage the items.
    * Highlights: Selling an item using its ID is very convenient and intuitive. Moreover, additional checks have been
      added to prevent the user from selling non-existent items.

    * **New Feature**: Get the report of SoldItems (uses the helper functions written by Lixin)
    * What it does: Allow the user view reports of soldItems (both details and overall statistics).
    * Justification: It helps the user to analyse the sales trend and help to adjust his business strategies.
    * Highlights: The format of the report has been specifically designed: the details of soldItems are shown in a table
      for clarity.

* Quality Assurance:
    * Added JUnit tests for `AddCommand`, `CreateShelfCommand`, `DeleteCommand`, `EditCommand`, `RemoveShelfCommand`
      , `SellCommand`, `SalesManager`, and `SalesReport`. The test coverage (methods, lines and branch) for most of these
      classes are above 80%.
    * Tested the code and reported bugs in GitHub issues:

* Contributions to the UG:
    * Adding instructions and expected outputs to Add new items section.
    * Adding expected outputs to Edit an item section.
    * Adding expected outputs to Delete an item section.
    * Add new instructions to the Usage section.
    * Reviewed the parts written by other teammates.

* Contributions to the DG:
    * Wrote the `Commands` sub-component design and created corresponding sequence diagram
    * Wrote the adding new items feature implementation and created corresponding sequence diagram
    * Wrote the editing items feature implementation and created corresponding sequence diagram
    * Created the corresponding sequence diagram for selling items and generating sales report.
    * Improved on the Introduction section, adding Aim and Target Audience sub-section.
    * Adding the test cases, command and expected result of Creating a shelf test, Removing a shelf test, Adding an item test, Deleting an item test and Editing an item test.
    * Reviewed the parts written by other teammates.
    
* Contributions to team-based task:
    * Fix bugs from PE-D

* Review/mentoring contributions:
    *

* Contributions beyond the project team:
    * [PED link](https://github.com/haoyusimon/ped/issues)