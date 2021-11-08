# Tan Li Xin - Project Portfolio Page

## Overview
CLIvershelf is a Java command line desktop application that helps small bookstore owners to keep track of item 
inventory, store finances and present it in a user-friendly manner, which enables better efficient management. 

### Summary of Contributions
Code contributed: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=t-l-xin&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25)

Contributions 

1. **Parser Component** (Pull Requests: [#22](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/22), 
[#45](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/45), [#101](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/101), 
[#233](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/233))
   * What it does: `Parser` parses any user input that has been entered, and returns the respective `Command` 
     objects according to the `commandWord` found, or returns an exception if `commandWord` not found or if the 
     command does not follow the respective command formats
   * Highlights: Pattern matching is used extensively to simplify the parsing process and allow better code readability. 
     The pattern regex customised to each `Command` and is checked after every edit to ensure that the regex is able to
     capture the correct matching groups. 
   * Credits: Adapted and reused Parser code from [AddressBook-2](https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java)
           

2. **Features**
   
   MarkUpCommand [#112](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/112)
      * What it does: Allows users to view current item markup and calculate the markup pricing estimates.
      * Justification: This feature allows better decision-making for the user, it allows the user to check current 
      and estimated markup prices easily without the need to use an additional device such as a calculator.
         

3. **Enhancement to existing features**

   * Improvement to `ReportCommand` : [#112](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/112) 
     * Added calculation methods to display sales statistics (profit, gross profit)
     * Improved function to view the statistics and items by between 2 time periods
   * IO-redirection input [#112](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/112)
   * Added JUnit tests for `Parser`, `MarkUpCommand` & `SalesMarkUp` class [#43](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/43), [#197](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/197/files) 
   * Added customised error messages for `add`, `edit`, `report`, `markup` commands [#213](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/213)     
   * Fixed bugs found ([#197](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/197), [#233](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/233))
       

4. **Documentation**
   
   User Guide
      * Updated user command input formats ([#95](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/95), [#118](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/118/files))
   Developer Guide
      * Design: Subcomponent Sales diagram & description [#232](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/232)
      * Implementation: 
        * Description & diagram for `markup` feature [#232](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/232)
        * Description for `report` & `sell` feature [#265](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/265) 
      * Target User Profile [#77](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/77/files)
      * Non-functional requirements [#88](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/88)
      * Manual Testing: `create`, `remove`, `report`, `sell`, `markup` [#106](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/106), [#213](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/213/files)
         

5. **Review contribution**
   * Reviewed teammate's PR [here](https://github.com/AY2122S1-CS2113T-F11-4/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me)
   

6. **Community**
   * Reviewed another group's Developer's Guide [Team T09-1](https://github.com/nus-cs2113-AY2122S1/tp/pull/24)
   * Bugs in other team projects [PED link](https://github.com/t-l-xin/ped/issues)
