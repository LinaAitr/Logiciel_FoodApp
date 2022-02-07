package app.foodapp;

import app.foodapp.model.SearchBar;

import java.util.Scanner;

public class FoodAppCLI {
    public static void main(String[] args) {
        System.out.println("Welcome to the food app");
        System.out.println("You requested command '" + args[0] + "' with parameter '" + args[1] + "'");

        //System.out.println("Input your command: ");
        System.out.println("Which recipe : \n - apple, \n - vegan. ");
        Scanner scanner = new Scanner(System.in);
        System.out.println(SearchBar.search(scanner));
        SearchBar.selectRecipe(scanner);
        scanner.close();
    }
}
