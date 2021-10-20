# User Guide

## Contents of User Guide
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Usage](#usage)
- [Features](#features)
  1. [`add` - Add new items](#add-new-item)  
  2. [`delete` - Delete item from list](#delete-an-item)
  3. [`list` - List all items](#listing-all-items)
  4. [`get` - Retrieve information of an item](#get-information-about-an-item)
  5. [`edit` - Update an item](#edit-an-item)
  6. [`report` - Generate sales report](#generate-sales-report)
  7. [`bye` - Exit command](#exit-program)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Introduction

CLIvershelf allows bookstore owners to create a management system of inventories and finances to better manage their
business, where they can view these information in a user-friendly manner.

## Quick Start

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Usage

Notes about the command format:

* Words in UPPER_CASE are the parameters to be supplied by the user. E.g. in delete `n/NAME`, `NAME` is the parameter
  which can be used as add n/Pilot Pen.
* Items in square brackets are optional. E.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.
* Extraneous parameters for commands that do not take in parameters (such as list) will be ignored.

{give detailed instructions on how to use the command here}

## Features

### Add new item

Adds a new item to the inventory, specifying its name, category, purchase cost, selling price, quantity (and remarks if any).

Format: `add n/NAME c/CATEGORY p/PURCHASE_PRICE s/SELLING_PRICE q/QUANTITY [r/REMARKS]`

Examples of usage:
```
add n/Harry Potter c/book p/27 s/37 q/5 

add n/Pilot P100 c/stationary p/1 s/1.5 q/100 r/Not many people bought this. Can consider a 50% discount.
```

Expected outcome
```
add n/Harry Potter c/book p/27 s/37 q/5
This item has been added to the list.
list
1. Harry Potter (purchase cost: 27, selling price: 37)

add n/Pilot P100 c/stationary p/1 s/1.5 q/100 r/Not many people bought this. Can consider a 50% discount.
This item has been added to the list.
list
1. Harry Potter (purchase cost: 27, selling price: 37)
2. Pilot P100 (purchase cost: 1, selling price: 1.5)

```

### Delete an item

Deletes item from the inventory with the matching name

Format: `delete n/NAME`

Example of usage:
```
delete n/Alice in wonderland
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

Shows a list of all items in the inventory list

Format: `list [c/CATEGORY]`

Examples of usage:
```
list 

list c/stationary
```
Expected outcome:
```

```


### Get information about an item

Retrieves information of an item 

Format: `get n/NAME [p/PROPERTY]`

Examples of usage:
```
get n/Lord of the Rings

get n/Apples Never Fall p/quantity
```
Expected outcome:
```

```


### Edit an item

Update the properties of an item

Format: `edit n/NAME p/PROPERTY v/VALUE [s/SHOWRESULT]`

Examples of usage:
```
edit n/Lord of the Rings p/sellingPrice v/30

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

Generates the sales report for given month

Format: `report t/PERIOD`

Example of usage:
```
report Jun 2020
```

Expected outcome:
```

```

### Exit program

Exits from CLIvershelf

Format: `bye`


## FAQ

**Q**: How do I transfer my data to another computer?

**A**: {your answer here}

## Command Summary

|Commands    |Format, Examples                                                                                                                 |
| ----       | ----                                                                                                                            |
|**Add**     | `add n/NAME c/CATEGORY p/PURCHASE_PRICE s/SELLING_PRICE q/QUANTITY [r/REMARKS]` <br> eg: add n/Harry Potter c/book p/27 s/37 q/5|
|**Delete**  | `delete n/NAME` <br> eg: `delete n/Alice in wonderland`                                                                         |
|**List**    | `list [c/CATEGORY]` <br> eg: `list c/stationary`                                                                                |
|**Get**     | `get n/NAME [p/PROPERTY]` <br> eg: `get n/Apples Never Fall p/quantity`                                                         |
|**Edit**    | `edit n/NAME p/PROPERTY v/VALUE [s/SHOWRESULT]` <br> eg: edit n/Apples Never Fall p/quantity v/100 s/false                      |
|**Report**  | `report t/PERIOD` <br> eg: `report Jun 2020`                                                                                    |
|**Bye**     | `bye`                                                                                                                           |


