# Wang Zhihuang's Project Portfolio Page

## Project: CLIverShelf

CLIverShelf is a desktop application used for inventory management and quick data analysis tool for small businesses such as small bookstore and convenient stores. The user interacts with it using a Command-Line Interface. 

Given below are my contributions to the project.
* Code contributed: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=zh1huang)
* Enhancements implemented:
  * `ListCommand`: Gives user the choice either list out every item in a specified shelf, in every shelf within the bookstore. This feature is further reused in `ReportCommand` and `FindCommand`.
  * `GetCommand`: The user is able to retrieve information about an item on the list, detailing Name, Cost, Price, ID, and remarks if any.
  * `HelpCommand`: A new user is able to type `help` to check the allowed formatting of the instructions. Other users may also use it should they forget the commands.
  * `ExitCommand`: Allows for a way to exit the program elegantly.
  * Implementation of over-ridden `equals` method for `test`, reused for all the `Commands`
  * Integration of main class `CliverShelf` with other components `Logic`, `UI`, `Storage` and `Model`.
* Contributions to the UG:
  * Table of contents, Quick Start, FAQ and Command Summary
  * Features (Format, example inputs and expected outputs)
* Contributions to the DG:
  * Table of contents, Setting up
  * Overall design of `Logic` component (with class diagram)
  * Implementation for `Listing all items` (with sequence diagram and object diagram)
  * Manual testing instructions for `list` and `get`
* Contributions to team-based task:
  * Regularly report bugs/issues and assign tasks to teammates
  * Fix preliminary bugs from PE-D
  * Updating both UG/DG that are not specific to my feature (as described above)
* Review/mentoring contributions: Actively gave feedbacks and suggestions to teammates, both during online meetings/group chats and PR reviews.
* Contributions beyond the project team: 
  * [PED link](https://github.com/zh1huang/ped/issues)
  * Gave tips and tricks to teammates on how to use plantUML more effectively