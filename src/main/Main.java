package main;
import java.util.Scanner;

public class Main {
    public static final String URL = "jdbc:mysql://localhost:3306/calculator?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String USER = "admin";
    public static final String PASS = "admin";
    
    public static void main(String args[]){
        Calculator calc = new Calculator();
        SanitizeInput cleaner = new SanitizeInput();
        Scanner input = new Scanner(System.in);
        String userInput = input.next();
        String cleanedInput = cleaner.cleanInput(userInput);
        
        //Database Logic
        System.out.println(calc.evaluate(cleanedInput));
    }
}
