package app.foodapp.model;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class AskUserTest {
    public static void AskUser() throws IOException, ParseException {
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Put 1 for a query search / put 2 for an ingredient search");
        int choice = inputUser.nextInt();

        while (choice != 1 && choice!=2){
            System.out.println("Put 1 for a query search / put 2 for an ingredient search");
            choice = inputUser.nextInt();
        }

        if (choice == 1){
            System.out.println("Please, give us your key word !");
            String keyWord = inputUser.next();
           if (RequestAPI.SearchByKey(keyWord)){
               recipeInfoByID(inputUser);
           }

        }
        else{
            System.out.println("Please, give us your ingredient !");
            String ingredient = inputUser.next();
            if (RequestAPI.SearchByIngredient(ingredient) ) {
                recipeInfoByID(inputUser);
            }

        }
    }

    private static void recipeInfoByID(Scanner inputUser) throws IOException, ParseException {
        System.out.println("Do you want to know more about one this recipes? just give us the number !");
        System.out.print("Number : ");
        int index = inputUser.nextInt();
        RecipeInformations recipe = new  RecipeInformations(RequestAPI.idList.get(index));
        RecipeInformations.SearchById(recipe);
        System.out.print("Do you want to add to favorite ?  1 for yes / 2 for no");
        int answ = inputUser.nextInt();
        while (answ != 1 && answ!=2){
            System.out.print("Do you want to add to favorite ?  1 for yes / 2 for no");
            answ = inputUser.nextInt();
        }
        if (answ==1){
            FavoriteRecipes.FillFile(recipe.getTitle());
        }


    }

    public static void main(String[] args) throws IOException, ParseException {
        AskUser();
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Do you want to search again ? 1 for yes / 2 for no");
        int answer = inputUser.nextInt();
        while (answer == 1){
            AskUser();
            System.out.println("Do you want to search again ? 1 for yes / 2 for no");
            answer = inputUser.nextInt();
        }
    }

}
