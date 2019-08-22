package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

public class Main {

    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\n\n1.Calculate\n"
                    + "2.Show History\n"
                    + "3.Delete History\n"
                    + "0.Exit\n\n");

            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    inputAndCalculate();
                    break;
                case 2:
                    showHistory();
                    break;
                case 3:
                    deleteHistory();
                    break;
                case 0:
                    return;
            }
        }
    }

    static void inputAndCalculate() {
        //This is used to calculate the input
        Calculator calc = new Calculator();

        //This is used to sanitize user input
        SanitizeInput cleaner = new SanitizeInput();

        Scanner inputEquation = new Scanner(System.in);

        //Data Access Object
        HistoryAccessObject databaseAccess = new HistoryAccessObject();

        String userInput = inputEquation.next();
        String cleanedInput = cleaner.cleanInput(userInput);
        String result = cleanedInput + " = " + calc.evaluate(cleanedInput);

        //Importing the model
        History resultModel = new History(result);
        if (databaseAccess.insertHistory(resultModel)) {
            System.out.println(result);
        }
    }

    static void showHistory() {
        HistoryAccessObject databaseAccess = new HistoryAccessObject();
        //Importing the DataAccessObject
        Set<History> result = databaseAccess.getHistory();
        if (result.size() == 0) {
            System.out.println("Empty");
        }
        result.forEach(e -> System.out.println(e.getResult()));
    }

    static void deleteHistory() {
        HistoryAccessObject databaseAccess = new HistoryAccessObject();

        if (databaseAccess.clearAll()) {
            System.out.println("History Deleted");
        } else {
            System.out.println("Already Empty");

        }
    }
}
