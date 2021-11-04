# User Guide

## Contents of User Guide
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Usage](#usage)
- [Features](#features)
  * [`help` - Show help](#display-help-message)
  * [`create` - Create a shelf](#create-a-shelf)
  * [`remove` - Remove a shelf](#remove-a-shelf)
  * [`add` - Add new items](#add-new-item)  
  * [`delete` - Delete item from list](#delete-an-item)
  * [`list` - List all items](#listing-all-items)
  * [`get` - Retrieve information of an item](#get-information-about-an-item)
  * [`edit` - Update an item](#edit-an-item)
  * [`sell` - Sell an item](#sell-an-item)
  * [`markup` - Markup price of an item](#markup-price-of-an-item)
  * [`report` - Generate sales report](#generate-sales-report)
  * [`bye` - Exit command](#exit-program)
  * [`[coming in v3.0]` - Add customer rating & review for each item](#add-customer-rating--review-for-each-item-coming-in-v30)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Introduction

CLIvershelf allows bookstore owners to create a management system of inventories and finances to better manage their
business, where they can view these information in a user-friendly manner.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download &#11015; the latest version of `CliverShelf` from [here](https://github.com/AY2122S1-CS2113T-F11-4/tp/releases).
3. Run `cd (path to the folder containing CliverShelf.jar)` and `java -jar CliverShelf.jar` to start using CLIvershelf.
4. You should see the following:
```
                .............................................................
                : Hello from                                                :
                :   _____ _      _____              _____ _          _  __  :
                :  / ____| |    |_   _|            / ____| |        | |/ _| :
                : | |    | |      | |_   _____ _ _| (___ | |__   ___| | |_  :
                : | |    | |      | \ \ / / _ \ '__\___ \| '_ \ / _ \ |  _| :
                : | |____| |____ _| |\ V /  __/ |  ____) | | | |  __/ | |   :
                :  \_____|______|_____\_/ \___|_| |_____/|_| |_|\___|_|_|   :
                : What can I do for you?                                    :
                ................................................................
                          ...................................................
                          : Enter 'help' for the list of available commands :
                          ......................................................
```
5. Type `help` to see all the commands available. You may try some example commands below:
   * `add  n/Geronimo shlv/book1 p/15.90 s/23.99 q/10` - Adds a Book "Geronimo" to the shelf name "book1" 
   * `delete shlv/book2 i/3` - Deletes item of `index 3` from the shelf `book2`
   * `list shlv/book1` - list the items from shelf name "book1"
   * `get shlv/book1 i/2` - get information about an item of `index 2` in shelf `book1`
   * `report t/stats ym/2021-10` - Show a report summary of the sales statistics in year 2021 month Oct
   * `bye` - exit the app
6. Refer to the [Features](#features) below for details of all available commands.
## Usage

Notes about the command format:

* Words in UPPER_CASE are the parameters to be supplied by the user. E.g. in delete `n/NAME`, `NAME` is the parameter
  which can be used as add n/Pilot Pen.
* Items in square brackets are optional and can only be specified **once** for that command. 
  E.g. `get n/NAME [p/PROPERTY]` can be used as `get n/Pencil p/cost` or as `get n/Pencil`.
* Commands that require date input `ym/YEAR-MONTH` for report command should follow input `ym/YYYY-MM`.
  <br>E.g. `ym/2021-11`
* &#9888; Parameters with `NAME` & `SHELF_NAME` cannot contain special character or symbols
  such as "!@#$%^&*[]{}/|\+=`~<>?,."
* &#9888; Commands that do not take in extra unspecified parameters, including additional trailing spaces from 
  user guide will be flagged as **invalid** command formats.
* &#9888; Program input is **case-sensitive** & input sequence follows what has been mentioned in this user guide, any reordering 
  of parameters will be flagged as **invalid** command format
* &#9888; Program only takes in input typed in english character set, does not accept input of other languages such as 
  Chinese characters, Japanese characters.
* &#9888; Index of items always start from `1`.

## Features

### Display help message

Displays help message with list of performable actions.

Format: `help`

Expected outcome:
```
help
        ...................................................................................................
        : Here are the performable actions:                                                               :
        : Words in UPPER_CASE are the parameters to be supplied by the user.                              :
        : Items in square brackets [ ] are optional.                                                      :
        : ____________________________________________________________________________________________    :
        : Description: Command format                                                                     :
        : ____________________________________________________________________________________________    :
        : 1. Get help : help                                                                              :
        : 2. Create new shelf: create shlv/SHELF_NAME                                                     :
        : 3. Remove existing shelf: remove shlv/SHELF_NAME                                                :
        : 4. Add item: add n/NAME shlv/SHELF_NAME p/PURCHASE_PRICE s/SELLING_PRICE q/QUANTITY [r/REMARKS] :
        : 5. Delete item: delete shlv/SHELF_NAME i/INDEX                                                  :
        : 6. List items: list [shlv/SHELF_NAME]                                                           :
        : 7. Get information of item : get shlv/SHELF_NAME i/INDEX                                        :
        : 8. Edit an item: edit shlv/SHELF_NAME i/INDEX p/PROPERTY v/VALUE                                :
        : 9. Sell an item: sell shlv/SHELF_NAME i/INDEX                                                   :
        : 10. Markup price of item: markup shlv/SHELF_NAME i/INDEX [%/PERCENT_MARKUP]                     :
        : 11. Generate sales report: report t/TYPE [ym/YEAR-MONTH]                                        :
        : 12. Exit program: bye                                                                           :
        : ____________________________________________________________________________________________    :
        ......................................................................................................
```
### Create a shelf

Creates a shelf to store items.

&#9888; **Note that shelf has to first created before [item can be added](#add-new-item).**

&#9888; **Note `SHELF_NAME` should only consist of alphabets and integers (Eg: `book1`)**

Format: `create shlv/SHELF_NAME`

Example: **Create shelf named `book2`**
```
create shlv/book2
```

Expected outcome:
```
                                          ...................................
                                          : Shelf "book2" has been created. :
                                          ......................................
```

### Remove a shelf

Removes a shelf.

&#9888; **Only can remove an empty shelf. Cannot remove a shelf with existing items.**


Format: `remove shlv/SHELF_NAME`

Example: **Remove shelf `book2`**
```
remove shlv/book2
```

Expected outcome:
```
                                       ......................................
                                       : Shelf "book2" has been deleted. :
                                       .........................................
```

### Add new item

Adds a new item to the inventory, specifying its name, shelf, purchase cost, selling price, quantity (and remarks if any).

**Important notes:**

&#9888; **[Shelf has to first created](#create-a-shelf) before item can be added.**

&#9888; **Name of item should not be too long (Should not exceed 52 characters).**

&#9888; **Maximum price and cost of any item is $9999.99 (Only allow values with 2 decimal points and below).**

&#9888; **Maximum quantity of any item is 999.**

Format: `add n/NAME shlv/SHELF_NAME p/PURCHASE_PRICE s/SELLING_PRICE q/QUANTITY [r/REMARKS]`

Example: **Add 5 books titled "Harry Potter" with a purchase cost of $27 and selling price of $37 to shelf `book1`.**
```
add n/Harry Potter shlv/book1 p/27 s/37 q/5 
```

Expected outcome
```
//todo
```

Example: **Add 99 Pilot P100 stationary with a purchase cost of $1 and selling price of $1.50 to shelf `stationary1`. 
Also added additional remarks**
```
add n/Pilot P100 shlv/stationary1 p/1 s/1.5 q/99 r/Not many people bought this. Can consider a 50% discount.
```

Expected outcome:
```
//todo
```

### Delete an item

Deletes item from the inventory with the matching name.

Format: `delete shlv/SHELF_NAME i/INDEX`

Example: **Delete item of `index 1` in shelf `book2`**
```
delete shlv/book2 i/1
```
Expected outcome:
```
//todo
```

### Listing all items 

Shows a list of all items in the inventory list.
The attributes shown are: Item name, purchase cost, selling price, quantity, and if they have remarks.

Under remarks, `x` signifies that the item does not have any remarks, while `o` signifies that it has remarks.
Users can use the [`get` function](#get-information-about-an-item) to display the remarks.

Format: `list [shlv/SHELF_NAME]`

Example: **Listing all items in all shelves**
```
list 
```
Expected outcome:
```
                         ......................................................................................................
                         : Here is the list of items:                                                                         :
                         : [book1]:                                                                                           :
                         :  No  |                        Item                         |   Cost    |   Price   | Qty | Remarks :
                         : -------------------------------------------------------------------------------------------------  :
                         :  1   | Narnia                                              | 17.40     | 25        | 1   |   x     :
                         :  2   | Three Little Pigs                                   | 10.90     | 12.99     | 97  |   o     :
                         : [book2]:                                                                                           :
                         :  No  |                        Item                         |   Cost    |   Price   | Qty | Remarks :
                         : -------------------------------------------------------------------------------------------------  :
                         :  1   | Geronimo                                            | 17.90     | 20.90     | 1   |   o     :
                         :  2   | expensive book                                      | 9999.99   | 9000.00   | 2   |   o     :
                         : [warehouse]:                                                                                       :
                         :  No  |                        Item                         |   Cost    |   Price   | Qty | Remarks :
                         : -------------------------------------------------------------------------------------------------  :
                         :  1   | sample item                                         | 12.25     | 25        | 1   |   o     :
                         .........................................................................................................
```

Example: **Listing all items under `book1` shelf**
```
list shlv/book1
```
Expected outcome:
```
                         ......................................................................................................
                         : Here is the list of items:                                                                         :
                         :  No  |                        Item                         |   Cost    |   Price   | Qty | Remarks :
                         : -------------------------------------------------------------------------------------------------  :
                         :  1   | Narnia                                              | 17.40     | 25        | 1   |   x     :
                         :  2   | Three Little Pigs                                   | 10.90     | 12.99     | 97  |   o     :
                         .........................................................................................................
```

### Get information about an item

Retrieves information of an item. 

Format: `get shlv/SHELF_NAME i/INDEX`

Example: **Retrieves all the information of item `index 1` in shelf `book1`, including remarks**
```
get shlv/book1 i/1
```
Expected outcome:
```
list shlv/book1
                         ......................................................................................................
                         : Here is the list of items:                                                                         :
                         :  No  |                        Item                         |   Cost    |   Price   | Qty | Remarks :
                         : -------------------------------------------------------------------------------------------------  :
                         :  1   | Narnia                                              | 17.40     | 25        | 1   |   o     :
                         :  2   | Three Little Pigs                                   | 10.90     | 12.99     | 97  |   x     :
                         .........................................................................................................

get shlv/book1 i/1                                                                                      
                                                                                      .........................................
                                                                                      : Here is the information of your item: :
                                                                                      : Name: Narnia                          :
                                                                                      : Cost: 17.40                           :
                                                                                      : Price: 25                             :
                                                                                      : Remarks: good book                    :
                                                                                      ............................................
```

### Edit an item

Updates the properties of an item.

Format: `edit shlv/SHELF_NAME i/INDEX p/PROPERTY v/VALUE`

&#128221; Only 2 `PROPERTY` can be specified, either `p/cost` to specify the cost of the item or `p/price` to specify the item price. 

Example: **Update selling price of item with `index 1` as $30**
```
edit shlv/book1 i/1 p/price v/30
```

Expected outcome:

```
//todo
```
Example: **Update purchase cost of item with `index 2` as $23.50**
```
edit shlv/book1 i/2 p/cost v/23.5 
```

Expected outcome:
```
//todo
```

### Sell an item

Mark an item as sold.

Format: `sell shlv/SHELF_NAME i/INDEX`

Example: **Mark an item of index 1 in shelf `book1` as sold**
```
sell shlv/book1 i/1
```

Expected outcome:
```
//todo
```


### Markup price of an item

Checks the current price markup of an item and calculates user estimated markup percent with the corresponding price change.
If no user markup percent is specified, CLIvershelf will calculate the percent markup in multiples of 10. 

Format: `markup shlv/SHELF_NAME i/INDEX [%/PERCENT_MARKUP]`

Example: **Check the markup percent estimates of the item `index 2` in shelf `book1`**
```
markup shlv/book1 i/2
```

Expected outcome:
```
                      .......................................................
                      : Item: Three Little Pigs                             :
                      : Cost: 10.90, Price: 12.99                           :
                      : Amount Difference: 2.09                             :
                      : Current Mark Up: 19%                                :
                      : markup: 0%, increase: $0.00, Final price: $10.90    :
                      : markup: 10%, increase: $1.09, Final price: $11.99   :
                      : markup: 20%, increase: $2.18, Final price: $13.08   :
                      : markup: 30%, increase: $3.27, Final price: $14.17   :
                      : markup: 40%, increase: $4.36, Final price: $15.26   :
                      : markup: 50%, increase: $5.45, Final price: $16.35   :
                      : markup: 60%, increase: $6.54, Final price: $17.44   :
                      : markup: 70%, increase: $7.63, Final price: $18.53   :
                      : markup: 80%, increase: $8.72, Final price: $19.62   :
                      : markup: 90%, increase: $9.81, Final price: $20.71   :
                      : markup: 100%, increase: $10.90, Final price: $21.80 :
                      ..........................................................
```

Example: **Check the 5% markup of the item `index 2` in shelf `book1`**
```
markup shlv/book1 i/2 %/5
```

Expected outcome:
```
//todo
```

### Generate sales report

Generates the sales report for given month.

Format: `report t/CONTENT_TYPE ym/YEAR-MONTH [ym/YEAR-MONTH]`

&#128221; Only 2 `CONTENT_TYPE` can be specified either `t/stats` to view statistics of sold items or `t/items` to view the list of all items

&#128221; `YEAR-MONTH` need to follow the format `YYYY-MM`

&#128221; If only 1 `ym/YEAR-MONTH` parameter is specified, report will be generated for that particular month in the specified year.
If 2 `ym/YEAR-MONTH` are specified, report in between the 2 date ranges will be generated

Example: **Generate sales report for the month of Oct 2021**
```
report t/stats ym/2021-10
```

Expected outcome:
```
//todo
```

### Exit program

Exits from CLIvershelf.

Format: `bye`

Expected outcome:
```
                                                        .....................
                                                        : See you next time :
                                                        ........................
```

### Add customer rating & review for each item `[coming in v3.0]`

Users would be able to record the customer ratings and review of each item sold in the bookstore. 

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Copy the save file `data/Data.txt` on your current computer into the other computer that you wish to use. 
Launch the program again and the saved data should load.

**Q**: What happens if my app crashes half-way?

**A**: Do not worry as your data will always be automatically saved in the text file whenever there are any changes made throughout the program. 
You can simply restart the program and your last updated data will be loaded.

**Q**: I have some suggestions/found some bugs for CLIver Shelf, what should I do?

**A**: Please do not hesitate to contact the [team](https://github.com/AY2122S1-CS2113T-F11-4/tp/blob/master/docs/AboutUs.md).

## Command Summary

|Commands    |Format, Examples                                                                                                                 |
| ----       | ----                                                                                                                            |
|**Help**    | `help`                                                                                                                          |
|**Create**  | `create shlv/SHELF_NAME` <br> eg: `create shlv/book1`                                                                                    |
|**Remove**  | `remove shlv/SHELF_NAME` <br> eg: `remove shlv/book4`                                                                                    |
|**Add**     | `add n/NAME shlv/SHELF_NAME p/PURCHASE_PRICE s/SELLING_PRICE q/QUANTITY [r/REMARKS]` <br> eg: `add n/Harry Potter shlv/book1 p/27 s/37 q/5`|
|**Delete**  | `delete shlv/SHELF_NAME i/INDEX` <br> eg: `delete shlv/book2 i/1`                                                                         |
|**List**    | `list [shlv/SHELF_NAME]` <br> eg: `list shlv/stationary`                                                                                |
|**Get**     | `get shlv/SHELF_NAME i/INDEX` <br> eg: `get shlv/book1 i/1`                                                         |
|**Edit**    | `edit shlv/SHELF_NAME i/INDEX p/PROPERTY v/VALUE` <br> eg: `edit shlv/book1 i/1 p/cost v/100`                      |
|**Sell**    | `sell shlv/SHELF_NAME i/INDEX` <br> eg: `sell shlv/book1 i/1`                                                                                    |
|**Markup**  | `markup shlv/SHELF_NAME i/INDEX [%/PERCENT_MARKUP]` <br> eg: `markup shlv/book1 i/1 %/5`                                                                                    |
|**Report**  | `report t/CONTENT_TYPE ym/YEAR-MONTH [ym/YEAR-MONTH]` <br> eg: `report c/stats ym/2021-10`                                                                                    |
|**Bye**     | `bye`                                                                                                                           |


