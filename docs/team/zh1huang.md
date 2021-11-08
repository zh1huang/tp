# Wang Zhihuang's Project Portfolio Page

## Project: CLIverShelf

CLIverShelf is a desktop application used for inventory management and quick data analysis tool for small businesses such as small bookstore and convenient stores. The user interacts with it using a Command-Line Interface. 

Given below are my contributions to the project.
* Code contributed: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=zh1huang&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)
* Enhancements implemented:
  * `ListCommand`: Gives user the choice to either list out every item in a specified shelf, or from every shelf within the bookstore in a **clean table format**. This feature is further reused in other commands such as `ReportCommand` and `SearchCommand`.
  * `GetCommand`: The user is able to retrieve information about an item on the list, detailing Name, Cost, Price, ID, and remarks if any. The user is able to find extra information here not shown on `ListCommand`.
  * `HelpCommand`: A new user is able to type `help` to check the allowed formatting of the available commands. Other users may also use it should they forget the command formats.
  * `ExitCommand`: Allows for a way to exit the program elegantly.
  * Implementation of over-ridden `equals` method for all the `CommandTests` and also `ParserTest` for more accurate testings.
  * Integration of main class `CliverShelf` with other components `Logic`, `UI`, `Storage` and `Model`.
  * Added Junit tests for `ListCommand`, `GetCommand`, `HelpCommand` and `ExitCommand`, achieving more than 80% test coverage for all.
* Contributions to the UG:
  * Table of contents, Quick Start, FAQ and Command Summary
  * `List`, `Get`, `Help` and `Exit` commands : Format, example inputs and expected outputs.
  * Helped my teammates in ensuring expected outputs were standardised, and consistent with `Help`.
  * Improved the explanation for `Add` and `Sell` in real-life context to enhance user experience.
* Contributions to the DG:
  * Table of contents, Setting up, User Stories
  * Overall design of `Logic` component (with class diagram): Explain how the different subclasses in `Logic` work together.
  * Implementation for `Listing all items` (with sequence diagram and class diagram): Explain how the `ListCommand` work with other components to execute the command.
  * Manual testing instructions for `list` and `get`
  * Reviewed and corrected teammates' diagrams and explanations.
* Contributions to team-based task:
  * Regularly report bugs/issues and assign tasks to teammates
  * Fix preliminary bugs from PE-D
  * Updating both UG/DG that are not specific to my feature (as described above)
* Review/mentoring contributions: Actively gave feedbacks and suggestions to teammates, both during online meetings/group chats and PR reviews.
* Contributions beyond the project team: 
  * [PED link](https://github.com/zh1huang/ped/issues)
  * Gave tips and tricks to teammates on how to use plantUML more effectively