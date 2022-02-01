package app.foodapp;

import java.util.Scanner;

public class FoodAppCLI {
    public static void main(String[] args) {
        System.out.println("Welcome to the food app");

        System.out.println("You requested command '" + args[0] + "' with parameter '" + args[1] + "'");

        //System.out.println("Input your command: ");
        System.out.println("Which recipe : 1 or 2 ? ");
        Scanner scanner = new Scanner(System.in);
        if(scanner.nextInt() == 1 ){
            System.out.println("choix 1");
            scanner.close();
        }
        else if(scanner.nextInt() == 2){
            System.out.println("choix 2");
            scanner.close();
        }
        else {System.out.println("Sorry, I can't do anything yet ! (Read: " + scanner.nextLine() +")");
            scanner.close();}

    }
}
