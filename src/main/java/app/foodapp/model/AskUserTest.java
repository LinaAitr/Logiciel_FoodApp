package app.foodapp.model;
import java.io.IOException;
import java.util.Scanner;

public class AskUserTest {
    public static void AskUser() throws IOException {
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

    private static void recipeInfoByID(Scanner inputUser) {
        System.out.println("Do you want to know more about one this recipes? just give us the number !");
        System.out.print("Number : ");
        int index = inputUser.nextInt();
        RequestById.SearchById(RequestAPI.idList.get(index));
    }

    public static void main(String[] args) throws IOException {
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
