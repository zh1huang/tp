# User Guide

## Contents of User Guide
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Usage](#usage)
- [Features](#features)
  1. [`help` - Show help](#display-help-message)
  2. [`add` - Add new items](#add-new-item)  
  3. [`delete` - Delete item from list](#delete-an-item)
  4. [`list` - List all items](#listing-all-items)
  5. [`get` - Retrieve information of an item](#get-information-about-an-item)
  6. [`edit` - Update an item](#edit-an-item)
  7. [`report` - Generate sales report](#generate-sales-report)
  8. [`bye` - Exit command](#exit-program)
  9. [`[coming in v3.0]` - Add customer rating & review for each item](#add-customer-rating--review-for-each-item-coming-in-v30)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Introduction

CLIvershelf allows bookstore owners to create a management system of inventories and finances to better manage their
business, where they can view these information in a user-friendly manner.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download &#11015; the latest version of `Duke` from [here](https://github.com/AY2122S1-CS2113T-F11-4/tp/releases).
3. Run `cd (path to the folder containing duke.jar)` and `duke -jar duke.jar` to start using CLIvershelf.
4. {placeholder: show a screenshot when the app first start up}
5. Type `help` to see the basic commands available, you may try to using the example commands below:
   * `add  n/Geronimo l/book1 p/15.90 s/23.99 q/10` - Adds a Book "Geronimo" to the shelf name "book1" 
   * `delete n/Narnia` - Deletes the Book "Narnia" from the shelf
   * `list l/book1` - list the items from shelf name book1
   * `get n/Pilot pen` - get information about an item "Pilot pen"
   * `report c/stats` - Show a report summary of the sales statistics
   * `bye` - exit the app
6. Refer to the [Features](#features) below for details of each command.
## Usage

Notes about the command format:

* Words in UPPER_CASE are the parameters to be supplied by the user. E.g. in delete `n/NAME`, `NAME` is the parameter
  which can be used as add n/Pilot Pen.
* Items in square brackets are optional and can only be specified **once** for that command. E.g. `get n/NAME [p/PROPERTY]` can be used as `get n/Pencil p/cost` or as `get n/Pencil`.
* &#9888; Commands that do not take in extra unspecified parameters from user guide will be flagged as **invalid** command formats.

{give detailed instructions on how to use the command here}

## Features

### Display help message

Displays help message with list of performable actions.

Format: `help`

Expected outcome:
```
help
_______________________________________________________
Here are the performable actions:
Words in UPPER_CASE are the parameters to be supplied by the user.
Items in square brackets [ ] are optional.
____________________________________________________________________________________________
Description: Command format
____________________________________________________________________________________________
1. Get help : help
2. Add item: add n/NAME l/SHELF_NAME p/PURCHASE_PRICE s/SELLING_PRICE q/QUANTITY [r/REMARKS]
3. Delete item: delete n/NAME
4. List items: list [l/SHELF_NAME] [c/CATEGORY]
5. Get information of item : get n/NAME [p/PROPERTY]
6. Edit an item: edit n/NAME p/PROPERTY v/VALUE [s/SHOWRESULT]
7. Generate sales report: report t/PERIOD
8. Exit program: bye
____________________________________________________________________________________________
```


### Add new item

Adds a new item to the inventory, specifying its name, category, purchase cost, selling price, quantity (and remarks if any).

Format: `add n/NAME l/SHELF_NAME p/PURCHASE_PRICE s/SELLING_PRICE q/QUANTITY [r/REMARKS]`

Example: **Add 5 books titled "Harry Potter" with a purchase cost of $27 and selling price of $37**
```
add n/Harry Potter c/book p/27 s/37 q/5 
```

Expected outcome
```
This item has been added to the list.
list
1. Harry Potter (purchase cost: 27, selling price: 37)
```

Example: **Add 100 Pilot P100 stationary with a purchase cost of $1 and selling price of $1.50. Also added additional remarks**
```
add n/Pilot P100 c/stationary p/1 s/1.5 q/100 r/Not many people bought this. Can consider a 50% discount.
```

Expected outcome:
```
This item has been added to the list.
list
1. Harry Potter (purchase cost: 27, selling price: 37)
2. Pilot P100 (purchase cost: 1, selling price: 1.5)
```

### Delete an item

Deletes item from the inventory with the matching name.

Format: `delete n/NAME`

Example: **Delete item named "Alice in Wonderland"**
```
delete n/Alice in Wonderland
```
Expected outcome:
```
list
1. Harry Potter (purchase cost: 27, selling price: 37)
2. Pilot P100 (purchase cost: 1, selling price: 1.5)
3. Alice in wonderland (purchase cost: 27, selling price: 37)
delete n/Alice in wonderland
This item has been removed from the list.
list
1. Harry Potter (purchase cost: 27, selling price: 37)
2. Pilot P100 (purchase cost: 1, selling price: 1.5)
```

### Listing all items 

Shows a list of all items in the inventory list.

Format: `list [l/SHELF_NAME] [c/CATEGORY]`

Example: **Listing all items in all categories**
```
list 
```
Expected outcome:
```

```

Example: **Listing all items under stationary category**
```
list c/stationary
```
Expected outcome:
```
```



### Get information about an item

Retrieves information of an item. 

Format: `get n/NAME [p/PROPERTY]`

Example: **Retrieves all the information of "Lord of the Rings"**
```
get n/Lord of the Rings
```
Expected outcome:
```

```

Example: **Retrieves the price of "Apples Never Fall"**
```
get n/Apples Never Fall p/quantity
```
Expected outcome:
```
```
### Edit an item

Updates the properties of an item.

Format: `edit n/NAME p/PROPERTY v/VALUE [s/SHOWRESULT]`

Example: **Update "Lord of the Rings"'s selling price as $30**
```
edit n/Lord of the Rings p/sellingPrice v/30
```

Expected outcome:

```

```
Example:
```
edit n/Apples Never Fall p/quantity v/100 s/false
```

Expected outcome:
```
list
1. Harry Potter (purchase cost: 27, selling price: 37)
2. Pilot P100 (purchase cost: 1, selling price: 1.5)
3. Lord of the Rings (purchase cost: 27, selling price: 37)
edit n/Lord of the Rings p/sellingPrice v/30
This item has been updated.
list
1. Harry Potter (purchase cost: 27, selling price: 37)
2. Pilot P100 (purchase cost: 1, selling price: 1.5)
3. Lord of the Rings (purchase cost: 27, selling price: 30)
```

### Generate sales report

Generates the sales report for given month.

Format: `report c/CONTENT_TYPE [ym/YEAR-MONTH]`

&#128456; Only 2 `CONTENT_TYPE` can be specified either `c/stats` to view statistics of sold items or `c/items` to view the list of all items

&#128456; `YEAR-MONTH` need to follow the format `YYYY-MM`

Example: **Generate sales report for the month of June 2020**
```
report c/stats
```

Expected outcome:
```

```

### Exit program

Exits from CLIvershelf.

Format: `bye`

Expected outcome:
```
Bye! Hope to see you again!
```

### Add customer rating & review for each item `[coming in v3.0]`

Users would be able to record the customer ratings and review of each item sold in the bookstore. 

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Copy the save file `data/output.txt` on your current computer into the other computer that you wish to use. Launch the program again and the saved data should load.

**Q**: What happens if my app crashes half-way?

**A**: Do not worry as your data will always be automatically saved in the text file whenever there are any changes made throughout the program. 
You can simply restart the program and your last updated data will be loaded.

**Q**: I have some suggestions/found some bugs for CLIver Shelf, what should I do?

**A**: Please do not hesitate to contact the [team](https://github.com/AY2122S1-CS2113T-F11-4/tp/blob/master/docs/AboutUs.md).

## Command Summary

|Commands    |Format, Examples                                                                                                                 |
| ----       | ----                                                                                                                            |
|**Help**    | `help`                                                                                                                          |
|**Add**     | `add n/NAME c/CATEGORY p/PURCHASE_PRICE s/SELLING_PRICE q/QUANTITY [r/REMARKS]` <br> eg: `add n/Harry Potter c/book p/27 s/37 q/5`|
|**Delete**  | `delete n/NAME` <br> eg: `delete n/Alice in wonderland`                                                                         |
|**List**    | `list [c/CATEGORY]` <br> eg: `list c/stationary`                                                                                |
|**Get**     | `get n/NAME [p/PROPERTY]` <br> eg: `get n/Apples Never Fall p/quantity`                                                         |
|**Edit**    | `edit n/NAME p/PROPERTY v/VALUE [s/SHOWRESULT]` <br> eg: `edit n/Apples Never Fall p/quantity v/100 s/false`                      |
|**Report**  | `report c/CONTENT_TYPE [ym/YEAR-MONTH]` <br> eg: `report c/stats`                                                                                    |
|**Bye**     | `bye`                                                                                                                           |


