package app.foodapp.model;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AskUserTest {
    public static void AskUser(ArrayList user) throws IOException, ParseException {
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Put 1 for a query search / put 2 for an ingredient search / Put 3 to show your favorites/ put 4 to Stop");
        int choice = inputUser.nextInt();

        while (choice != 1 && choice!=2 && choice!=3 && choice!=4){
            System.out.println("Put 1 for a query search / put 2 for an ingredient search / Put 3 to show your favorites");
            choice = inputUser.nextInt();
        }
        if (choice == 1){
            System.out.println("Please, give us your key word !");
            String keyWord = inputUser.next();
            if (RequestAPI.SearchByKey(keyWord)){
               recipeInfoByID(user,inputUser);
           }
            AskUser(user);
        }

        else if (choice == 2){
            System.out.println("Please, give us your ingredient !");
            String ingredient = inputUser.next();
            if (RequestAPI.SearchByIngredient(ingredient)) {
                recipeInfoByID(user,inputUser);
            }
            AskUser(user);


        }
         else if (choice == 3) {
           Object favoritesDetails[]= (Object[]) FavoriteRecipes.ShowFavorites(user);
           Boolean favoritesIsNotEmpty= (Boolean) favoritesDetails[1];
           int numberOfFavorites= (int) favoritesDetails[0];
            if (favoritesIsNotEmpty){
               System.out.println("Do you want yo delete something ? 1 for no / 2 for no");
               int number = inputUser.nextInt();
               while (number != 1 && number!=2 ){
                   System.out.println("Do you want yo delete something ? 1 for no / 2 for no");
                   number = inputUser.nextInt();
               }
               if (number==2) AskUser(user);
               else
               {
                   System.out.println("Choose a number from your favorites recipes ");
                   int numberFav = inputUser.nextInt();
                   System.out.println(numberOfFavorites);
                   while (0>numberFav || numberFav>numberOfFavorites){
                       System.out.println("Choose a number from your favorites recipes ");
                       numberFav = inputUser.nextInt();
                   }
                   FavoriteRecipes.DeleteFavorite(user,numberFav);
               }


           }
            AskUser(user);

        }
    }

    private static void recipeInfoByID(ArrayList<String> user ,Scanner inputUser) throws IOException, ParseException {
        System.out.println("Do you want to know more about one this recipes? just give us the number !");
        System.out.print("Number : ");
        int index = inputUser.nextInt();
        RecipeInformations recipe = new  RecipeInformations(RequestAPI.idList.get(index));
        RecipeInformations.SearchById(recipe);
        System.out.print("Do you want to add to favorite ?  1 for yes / 2 for no");
        int answ = inputUser.nextInt();
        while (answ != 1 && answ!=2){
            System.out.print("Do you want to add to favorite ?  1 for yes / 2 for no \n");
            answ = inputUser.nextInt();
        }
        if (answ==1){
            FavoriteRecipes.FillFile(recipe.getTitle(),user);
        }


    }

    private static ArrayList<String> LogAndSign( ) throws IOException, ParseException {
        System.out.println("Choose an option :");
        System.out.println("1-Log in");
        System.out.println("2-Sign in");
        Scanner inputUser = new Scanner(System.in);
        int logAndSign = inputUser.nextInt();
        System.out.print("Username : ");
        String userName = inputUser.next();
        System.out.print("Password : ");
        String password = inputUser.next();
        ArrayList<String> user = new ArrayList<>();
        user.add(userName);
        user.add(password);
        user.add("yes");
        if (logAndSign==2){
            FavoriteRecipes.SignIn(user,logAndSign);
        }
        else if (logAndSign==1)
        {
            ArrayList userUpdate =FavoriteRecipes.SignIn(user,logAndSign);
            if (userUpdate.get(2)=="no"){
                System.out.println("Account doesnt exist ! PLease verify spelling or create a new one !");
                LogAndSign();
            }
        }
        return user;





    }


        public static void main(String[] args) throws IOException, ParseException {
        ArrayList user = LogAndSign();
        AskUser(user);
    }

}
