package app.foodapp.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SearchBar {

    public static String search(Scanner scanner) throws IOException, ParseException { // renvoi peut etre une liste ou un object
        String result = "";
        JSONParser jsonP = new JSONParser();
        String scannerMessage = scanner.nextLine(); // modifié pour mettre nextLine() et donc boucle while hasNext ..

        if (scannerMessage.equals("apple") || scannerMessage.equals("vegan") || scannerMessage.equals("favorite")) {
            if (scannerMessage.equals("apple")) {
                System.out.println(scannerMessage);
                JSONObject jsonO = (JSONObject) jsonP.parse(new FileReader("fichiers json/findByIngredients.json"));
                RecipeInformations recipeInformations = new RecipeInformations(jsonO);
                result = recipeInformations.getTitle();
            }

            if (scannerMessage.equals("vegan")) {
                JSONObject jsonO = (JSONObject) jsonP.parse(new FileReader("fichiers json/recipeInformation.json"));
                RecipeInformations recipeInformations = new RecipeInformations(jsonO);
                result = recipeInformations.getTitle();
            }
            if (scannerMessage.equals("favorite")) {
                JSONObject jsonO = (JSONObject) jsonP.parse(new FileReader("fichiers json/recipeInformation.json"));
                RecipeInformations recipeInformations = new RecipeInformations(jsonO);
                result = recipeInformations.getTitle();
            }
        }
        else {
            //System.out.println(scannerMessage);
            result = "Sorry, I can't do anything yet ! ";/*(Read:  scanner.nextLine() +")";*/
        }
        return result;
    }

    public static void selectRecipe(Scanner scanner){
        String scMessage = scanner.nextLine();
        // faire appel methode de team2;
    }
}
