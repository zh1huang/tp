# User Guide

## Introduction

CLIvershelf allows bookstore owners to create a management system of inventories and finances to better manage their
business, where they can view these information in a user-friendly manner.

## Quick Start

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features

### Add new items: `add`

Add a new item to the inventory, specifying its name, category, price and quantity.

Format: `add n/NAME c/CATEGORY p/PRICE q/QUANTITY [r/REMARKS]`

Examples:

* `add n/Harry Potter 1 c/books p/$37 q/1` Add 1 book titled ‘Harry Potter’ with a price of $37
* `add n/Pilot P100 c/stationary p/$1 q/1  r/Not many people bought this. Can consider a 50% discount.` Add a Pilot P100
  pen with a price of $1 with remarks.

### Delete items: `delete`

Delete item from the inventory with the matching name

Format: `delete n/NAME`

Examples:

* list followed by `delete n/Alice in wonderland`
* list followed by `delete n/Stabilo colour pencil`

### Listing all items : `list`

Shows a list of all items in the inventory list

Format: `list [c/CATEGORY]`

Examples:

* `list c/all` Lists every item regardless of their categories
* `list c/stationary` Lists every Stationary
* `list c/books` Lists every Book

### Get information about an item: `get`

Show the property(s) of one item (Description, price, quantity etc)

Format: `get n/NAME [p/PROPERTY]`

Examples:

* `get n/Lord of the Rings`
* `get n/Apples Never Fall p/quantity`

### Update the items: `edit`

Change properties of the items (Description, price, quantity etc)

Format: `edit n/NAME p/PROPERTY v/VALUE [s/SHOWRESULT]`

Examples:

* `edit n/Lord of the Rings p/price v/30`
* `edit n/Apples Never Fall p/quantity v/100 s/false`

## Usage

Notes about the command format:

* Words in UPPER_CASE are the parameters to be supplied by the user. E.g. in delete `n/NAME`, `NAME` is the parameter
  which can be used as add n/Pilot Pen.
* Items in square brackets are optional. E.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.
* Extraneous parameters for commands that do not take in parameters (such as list) will be ignored.

{give detailed instructions on how to use the command here}

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
