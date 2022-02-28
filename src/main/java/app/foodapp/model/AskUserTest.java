package app.foodapp.model;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class AskUserTest {
    public static void AskUser() throws IOException, ParseException {
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Put 1 for a query search / put 2 for an ingredient search / Put 3 to show your favorites");
        int choice = inputUser.nextInt();

        while (choice != 1 && choice!=2 && choice!=3){
            System.out.println("Put 1 for a query search / put 2 for an ingredient search / Put 3 to show your favorites");
            choice = inputUser.nextInt();
        }
        if (choice == 1){
            System.out.println("Please, give us your key word !");
            String keyWord = inputUser.next();
            if (RequestAPI.SearchByKey(keyWord)){
               recipeInfoByID(inputUser);
           }
        }

        else if (choice == 2){
            System.out.println("Please, give us your ingredient !");
            String ingredient = inputUser.next();
            if (RequestAPI.SearchByIngredient(ingredient)) {
                recipeInfoByID(inputUser);
            }

        }
         else{
            System.out.println("What is you code ?");
            String code = inputUser.next();
           Object favoritesDetails[]= (Object[]) FavoriteRecipes.ShowFavorites(code);
           Boolean favoritesIsNotEmpty= (Boolean) favoritesDetails[1];
           int numberOfFavorites= (int) favoritesDetails[0];
            if (favoritesIsNotEmpty){
               System.out.println("Do you want yo delete something ? 1 for no / 2 for no");
               int number = inputUser.nextInt();
               while (number != 1 && number!=2 ){
                   System.out.println("Do you want yo delete something ? 1 for no / 2 for no");
                   number = inputUser.nextInt();
               }
               if (number==2) AskUser();
               else
               {
                   System.out.println("Choose a number from your favorites recipes ");
                   int numberFav = inputUser.nextInt();
                   System.out.println(numberOfFavorites);
                   while (0>numberFav || numberFav>numberOfFavorites){
                       System.out.println("Choose a number from your favorites recipes ");
                       numberFav = inputUser.nextInt();
                   }
                   FavoriteRecipes.DeleteFavorite(code,numberFav);
               }


           }

        }
    }

    private static void recipeInfoByID(Scanner inputUser) throws IOException, ParseException {
        System.out.println("Do you want to know more about one this recipes? just give us the number !");
        System.out.print("Number : ");
        int index = inputUser.nextInt();
        RecipeInformations recipe = new  RecipeInformations(RequestAPI.idList.get(index));
        System.out.print(RecipeInformations.SearchById(recipe));
        System.out.print("Do you want to add to favorite ?  1 for yes / 2 for no");
        int answ = inputUser.nextInt();
        while (answ != 1 && answ!=2){
            System.out.print("Do you want to add to favorite ?  1 for yes / 2 for no " + "\n");
            answ = inputUser.nextInt();
        }
        if (answ==1){
           System.out.print("Donez votre Code ! : ");
           String code = inputUser.next();
            FavoriteRecipes.FillFile(recipe.getTitle(),code);
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
