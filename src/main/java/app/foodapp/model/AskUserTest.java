package app.foodapp.model;

import java.util.Scanner;

public class AskUserTest {
    public static void AskUser(){
        Scanner saisieUtilisateur = new Scanner(System.in);
        System.out.println("Put 1 for a query search / put 2 for an ingredient search");
        int str = saisieUtilisateur.nextInt();

        while (str != 1 && str!=2){
            System.out.println("Put 1 for a query search / put 2 for an ingredient search");
            str = saisieUtilisateur.nextInt();
        }

        if (str == 1){
            System.out.println("Please, give us your key !");
            String key = saisieUtilisateur.next();
            TryAPI_KeyWord.SearchByKey(key);
            System.out.println("Do you want to know more about one this recipes? juste give us the id !");
            System.out.print("id :");
            int id = saisieUtilisateur.nextInt();
            TryAPI_RecipeInfo.SearchById(id);

        }
        else{
            System.out.println("Please, give us your ingredient !");
            String ingredient = saisieUtilisateur.next();
            TryAPI.SearchByIngredient(ingredient);
        }
    }

        public static void main(String[] args) {
            AskUser();
            Scanner saisieUtilisateur = new Scanner(System.in);
            System.out.println("Do you want to search again ? 1 for yes / 2 for no");
            int answer = saisieUtilisateur.nextInt();
            while (answer == 1){
                AskUser();
                System.out.println("Do you want to search again ? y/n");
                answer = saisieUtilisateur.nextInt();

            }





        }

}
