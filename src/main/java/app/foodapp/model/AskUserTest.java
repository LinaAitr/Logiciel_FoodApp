package app.foodapp.model;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AskUserTest {
    public static void AskUser() throws IOException, ParseException {
        Scanner inputUser = new Scanner(System.in);
        List<String> idList;
        System.out.println("Put 1 for a query search / put 2 for an ingredient search / Put 3 to show your favorites");
        int choice = inputUser.nextInt();

        while (choice != 1 && choice!=2 && choice!=3){
            System.out.println("Put 1 for a query search / put 2 for an ingredient search / Put 3 to show your favorites");
            choice = inputUser.nextInt();
        }
        if (choice == 1){
            System.out.println("Please, give us your key word !");
            String keyWord = inputUser.next();
            if (displaySearchByKey(RequestAPI.jsonSearchByKey(keyWord))){
                idList = RequestAPI.searchByKey(keyWord);
                recipeInfoByID(inputUser, idList);

           }
        }

        else if (choice == 2){
            System.out.println("Please, give us your ingredient !");
            String ingredient = inputUser.next();
            if (displaySearchByIngredient(RequestAPI.jsonSearchByIngredient(ingredient))) {
                idList = RequestAPI.searchByIngredient(ingredient);
                recipeInfoByID(inputUser, idList);

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

    private static void recipeInfoByID(Scanner inputUser, List<String> idList) throws IOException, ParseException {
        System.out.println("Do you want to know more about one this recipes? just give us the number !");
        System.out.print("Number : ");
        int index = inputUser.nextInt();
        RecipeInformations recipe = new  RecipeInformations(idList.get(index));
        System.out.print(RecipeInformations.SearchById(recipe));
        System.out.print("Do you want to add to favorite ?  1 for yes / 2 for no");
        int answ = inputUser.nextInt();
        while (answ != 1 && answ!=2){
            System.out.print("Do you want to add to favorite ?  1 for yes / 2 for no " + "\n");
            answ = inputUser.nextInt();
        }
        if (answ==1){
           System.out.print("Give your Code ! : ");
           String code = inputUser.next();
           FavoriteRecipes.FillFile(recipe.getTitle(),code);
        }
    }

    public static boolean displaySearchByIngredient(JSONArray dataObject){
        if (dataObject.isEmpty()){
            System.out.println("No recipe found !");
            return false;
        }
        else{
            for (int i=0; i<dataObject.size();i++){
                JSONObject recipeData = (JSONObject) dataObject.get(i);

                System.out.print(i+"-");
                System.out.println(recipeData.get("title"));
            }
        }
        return true;
    }

    public static boolean displaySearchByKey(JSONArray resultRecipes){
        if (resultRecipes.isEmpty()){
            System.out.println("No recipe found !");
            return false;
        }
        else{
            int l=-1;
            for (Object recipe : resultRecipes) {
                JSONObject person = (JSONObject) recipe;
                l++;
                System.out.print(l+"-");
                String name = (String) person.get("title");
                System.out.println(name);
            }
        }
        return true;
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
