package app.foodapp.model;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AskUserTest {
    public static void AskUser(ArrayList user) throws IOException, ParseException {
        Scanner inputUser = new Scanner(System.in);
        int choice = -1;
        do {
            System.out.println();
            System.out.println("Choose an option :\n");
            System.out.println("1-Key research");
            System.out.println("2-Ingredient research");
            System.out.println("3-Show favorites");
            System.out.println("4-Quit");
            System.out.print("Your choice : ");
            choice = inputUser.nextInt();
        } while (choice != 1 && choice!=2 && choice!=3 && choice!=4);


        if (choice == 1){
            System.out.println();
            System.out.print("Give us your key word : ");
            String keyWord = inputUser.next();
            if (RequestAPI.SearchByKey(keyWord)){
               recipeInfoByID(user,inputUser);
           }
            AskUser(user);
        }

        else if (choice == 2){
            System.out.println();
            System.out.print("Give us your ingredient : ");
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
                int number = -1;
                do {
                    System.out.println();
                    System.out.println("Do you want to delete something ? ");
                    System.out.println("1-Yes");
                    System.out.println("2-No");
                    number = inputUser.nextInt();
                } while (number != 1 && number!=2 );


               if (number==2) AskUser(user);
               else
               {
                   int numberFav =-1;
                   do {
                       System.out.println();
                       System.out.println("Choose a number from your favorite recipes ! ");
                       numberFav = inputUser.nextInt();
                   } while (0>numberFav || numberFav>numberOfFavorites);

                   FavoriteRecipes.DeleteFavorite(user,numberFav);
                   System.out.println("Recipe deleted !");

               }


           }
            AskUser(user);

        }
    }

    private static void recipeInfoByID(ArrayList<String> user ,Scanner inputUser) throws IOException, ParseException {
        System.out.println();
        System.out.println("Do you want to know more about one this recipes ?");
        System.out.print("Recipe number : ");
        int index = inputUser.nextInt();
        RecipeInformations recipe = new  RecipeInformations(RequestAPI.idList.get(index));
        RecipeInformations.SearchById(recipe);

        int answ = -1;
        do {
            System.out.println();
            System.out.println("Do you want to add to favorite ?");
            System.out.println("1-Yes");
            System.out.println("2-No");
            answ = inputUser.nextInt();
        }while (answ != 1 && answ!=2);
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
        ArrayList userUpdate =FavoriteRecipes.SignIn(user,logAndSign);
        if (logAndSign==2){
            if (userUpdate.get(2) == "alreadyCreated"){
                System.out.println();
                System.out.println("Account Already created !");
                System.out.println("Welcome Again "+ userUpdate.get(0));
            }
            else if (userUpdate.get(2)=="AccountCreated"){
                System.out.println();
                System.out.println("Account created !");
                System.out.println("Welcome  "+ userUpdate.get(0)+" !");
            }
        }
        else if (logAndSign==1)
        {
            if (userUpdate.get(2)=="notExist"){
                System.out.println();
                System.out.println("Account doesnt exist ! PLease verify spelling or create a new one !");
                LogAndSign();
            }
            else if (userUpdate.get(2)=="alreadyCreated2") {
                System.out.println();
                System.out.println("Welcome Again "+ userUpdate.get(0)+" !");
            }

        }
        return user;
    }






        public static void main(String[] args) throws IOException, ParseException {
        ArrayList user = LogAndSign();
        AskUser(user);
    }

}
