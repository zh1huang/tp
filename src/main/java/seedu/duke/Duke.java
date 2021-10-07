package seedu.duke;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static String userNullInput = null;
    public static String userEmptyInput = "";
    public static String userWrongCommandInput = "blaaaaaha";

    public static String userAddInput_1 = "add n/Harry Potter 1 c/books p/$37 q/1";
    public static String userAddInput_2 = "add n/Pilot P100 c/stationary p/$1 q/1 r/Not many people bought this. Can consider a 50% discount.";
    public static String userWrongAddInput_0 = "add ";
    public static String userWrongAddInput_1 = "add c/books p/$37 q/1";
    public static String userWrongAddInput_2 = "add n/Harry Potter 1 c/books ";

    public static String userDeleteInput_1 = "delete n/Alice in wonderland";
    public static String userDeleteInput_2 = "delete n/Stabilo colour pencil";
    public static String userWrongDeleteInput_0 = "delete ";
    public static String userWrongDeleteInput_1 = "delete p/$37";
    public static String userWrongDeleteInput_2 = "delete q/37";

    public static String userListInput_0 = "list";
    public static String userListInput_1 = "list c/all";
    public static String userListInput_2 = "list c/stationary ";
    public static String userWrongListInput_0 = "list p/223";
    public static String userWrongListInput_1 = "list p/dme";
    public static String userWrongListInput_2 = "list r/idmwk ";

    public static String userGetInput_1 = "get n/Lord of the Rings";
    public static String userGetInput_2 = "get n/Apples Never Fall p/quantity";
    public static String userWrongGetInput_1 = "get ";
    public static String userWrongGetInput_2 = "get p/quantity";

    public static String userEditInput_1 = "edit n/Lord of the Rings p/price v/30";
    public static String userEditInput_2 = "edit n/Apples Never Fall p/quantity v/100 s/false";
    public static String userEditInput_3 = "edit n/Apples Never Fall p/quantity v/100 s/true";
    public static String userWrongEditInput_1 = "edit ";
    public static String userWrongEditInput_2 = "edit n/Apples Never Fall ";
    public static String userWrongEditInput_3 = "edit v/hahaha s/true";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

//        Scanner in = new Scanner(System.in);
//        System.out.println("Hello " + in.nextLine());
        Parser.parseCommand(userEmptyInput);
    }
}
