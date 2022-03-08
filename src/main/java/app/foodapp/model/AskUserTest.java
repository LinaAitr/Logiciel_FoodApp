package app.foodapp.model;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class AskUserTest {
    public static void AskUser(ArrayList<String> user) throws IOException, ParseException {
        Scanner inputUser = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Choose an option :");
            System.out.println("--------------------------");
            System.out.println("1-Key research");
            System.out.println("2-Ingredient research");
            System.out.println("3-Show favorites");
            System.out.println("4-Add a recipe");
            System.out.println("5-Quit");
            System.out.println("6-Log out");
            System.out.println("7-Show my own recipes");
            System.out.println("--------------------------");

            System.out.print("Your choice : ");

            choice = inputUser.nextInt();
        } while (choice != 1 && choice!=2 && choice!=3 && choice!=4 && choice!=5 && choice!=6 && choice!=7);


        if (choice == 1){
            System.out.println();
            System.out.print("Give us your key word : ");
            String keyWord = inputUser.next();
            System.out.println("--------------------------");
            if (RequestAPI.SearchByKey(keyWord).get(1).size()!=0){
                ArrayList<String> names =RequestAPI.SearchByKey(keyWord).get(1);
                for (int i=0; i<names.size();i++){
                    System.out.println(i+"-"+names.get(i));
                }
                System.out.println("--------------------------");
                if (names.size()!=0) {
                    recipeInfoByID(user,inputUser);
                }


            }
            AskUser(user);
        }

        else if (choice == 2){
            System.out.println();
            System.out.print("Give us your ingredient : ");
            String ingredient = inputUser.next();
            ArrayList<String> names =RequestAPI.SearchByIngredient(ingredient).get(1);
            System.out.println(names);
            for (int i=0; i<names.size();i++){
                System.out.println(i+"-"+names.get(i));
            }
            if (names.size()!=0) {
                recipeInfoByID(user,inputUser);
            }
            AskUser(user);

        }
         else if (choice == 3) {
             ArrayList<String> listOfId = FavoriteRecipes.ShowFavorites(user);
             if (listOfId.size()==0){
                 System.out.println("Nothing to show");
             }
             else{
                 for (int j=0; j<listOfId.size();j++){
                     System.out.println(listOfId.get(j));
                 }
             }

            if (listOfId.size()!=0){
                int number ;
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
                   int numberFav;
                   do {
                       System.out.println();
                       System.out.println("Choose a number from your favorite recipes ! ");
                       numberFav = inputUser.nextInt();
                   } while (0>numberFav || numberFav>listOfId.size());

                   FavoriteRecipes.DeleteFavorite(user,numberFav);
                   System.out.println("Recipe deleted !");

               }


           }
            AskUser(user);

        }
         else if (choice==6){
             StartApp();
        }
        if (choice == 7){
            System.out.println();
            AddRecipes.ShowRecipes(user);
            System.out.println();

            AskUser(user);
        }

         else if (choice==4){
            System.out.println("Give us infos ");
            System.out.println();
            System.out.print("title : ");
            Scanner sc = new Scanner(System.in);
            String title = sc.nextLine();
            System.out.print("time : ");
            String time = inputUser.next();
            System.out.println("ingredients : ");
            int i=0;
            int j=0;
            HashMap<String,ArrayList<String>> ingredients = new HashMap<>();
            int moreIngredients;
            int moreSteps;
            do {
                i++;
                System.out.println("ingredient "+i+" : ");
                System.out.print("Name :");
                sc = new Scanner(System.in);
                String ingredientName = sc.nextLine();
                System.out.print("Quantity :");
                String ingredientQtt = inputUser.next();
                System.out.print("Unity :");
                String ingredientUnity = inputUser.next();
                ArrayList<String> ingredientQuantityAndUnity =new ArrayList<>();
                ingredientQuantityAndUnity.add(ingredientQtt);
                ingredientQuantityAndUnity.add(ingredientUnity);
                ingredients.put(ingredientName, ingredientQuantityAndUnity);
                System.out.println("Do you want to add an ingredient ?");
                System.out.println("1-yes");
                System.out.println("2-No");
                System.out.print("Your choice : ");
                moreIngredients = inputUser.nextInt();
            } while (moreIngredients==1);
            System.out.println("Steps : ");
            ArrayList<String> steps =new ArrayList<>();

            do {
                j++;
                System.out.print("Step "+j+" :");
                sc = new Scanner(System.in);
                String step = sc.nextLine();
                steps.add(step);
                System.out.println("Do you want to add a step ?");
                System.out.println("1-yes");
                System.out.println("2-No");
                System.out.print("Your choice : ");
                moreSteps = inputUser.nextInt();
            }while (moreSteps==1);

            AddRecipes.AddRecipe(user,title, time,ingredients,steps);
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

        int answer;
        do {
            System.out.println();
            System.out.println("Do you want to add to favorite ?");
            System.out.println("1-Yes");
            System.out.println("2-No");
            answer = inputUser.nextInt();
        }while (answer != 1 && answer!=2);
        if (answer==1){
            FavoriteRecipes.FillFile(recipe.getId(),user);
        }


    }

    private static ArrayList<String> LogAndSign( ) throws IOException, ParseException {
        System.out.println();
        System.out.println("Choose an option :");
        System.out.println("--------------------------");
        System.out.println("1-Log in");
        System.out.println("2-Sign in");
        System.out.println("3-Quit");
        System.out.println("--------------------------");
        System.out.println("Your choice : ");
        Scanner inputUser = new Scanner(System.in);
        int logAndSign = inputUser.nextInt();
        if (logAndSign==3){
            return new ArrayList<>();
        }
        System.out.print("Username : ");
        String userName = inputUser.next();
        System.out.print("Password : ");
        String password = inputUser.next();
        ArrayList<String> user = new ArrayList<>();
        user.add(userName);
        user.add(password);
        user.add("yes");
        ArrayList<String> userUpdate =FavoriteRecipes.SignIn(user,logAndSign);
        if (logAndSign==2){
            if (Objects.equals(userUpdate.get(2), "alreadyCreated")){
                System.out.println();
                System.out.println("Account Already created !");
                System.out.println("Welcome Again "+ userUpdate.get(0));
            }
            else if (Objects.equals(userUpdate.get(2), "AccountCreated")){
                System.out.println();
                System.out.println("Account created !");
                System.out.println("Welcome  "+ userUpdate.get(0)+" !");
            }
        }
        else if (logAndSign==1)
        {
            if (Objects.equals(userUpdate.get(2), "notExist")){
                System.out.println();
                System.out.println("Account doesnt exist ! PLease verify spelling or create a new one !");
                return LogAndSign();
            }
            else if (Objects.equals(userUpdate.get(2), "alreadyCreated2")) {
                System.out.println();
                System.out.println("Welcome Again "+ userUpdate.get(0)+" !");
                System.out.println();

            }
        }
        return user;
    }

    private static void StartApp() throws IOException, ParseException {
        ArrayList<String> user = LogAndSign();
        if (user.size()!=0){
            AskUser(user);
        }
    }







        public static void main(String[] args) throws IOException, ParseException {
      StartApp();
    }

}
