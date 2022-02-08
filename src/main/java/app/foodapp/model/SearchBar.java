package app.foodapp.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Scanner;

public class SearchBar {

    public static String search(Scanner scanner){ // renvoi peut etre une liste ou un object
        String result = "";
        JSONParser jsonP = new JSONParser();
        String scannerMessage = scanner.nextLine(); // modifié pour mettre nextLine() et donc boucle while hasNext ..

        if(scannerMessage.equals("apple") ){
            JSONObject jsonO = (JSONObject)jsonP.parse(new FileReader("fichiers json/findByIngredients.json"));
            RecipeInformations recipeInformations = new RecipeInformations(jsonO);
            result = recipeInformations.getTitle();
        }

        else if(scannerMessage.equals("vegan")){
            JSONObject jsonO = (JSONObject)jsonP.parse(new FileReader("fichiers json/recipeInformation.json"));
            RecipeInformations recipeInformations = new RecipeInformations(jsonO);
            result = recipeInformations.getTitle();
        }

        if(scannerMessage.equals("favorite") ){
            JSONObject jsonO = (JSONObject)jsonP.parse(new FileReader("fichiers json/recipeInformation.json"));
            RecipeInformations recipeInformations = new RecipeInformations(jsonO);
            result = recipeInformations.getTitle();
        }

        else {
            result ="Sorry, I can't do anything yet ! (Read: /*+ scanner.nextLine() +*/)";
        }
        return result;

    public static void selectRecipe(Scanner scanner){
        String scMessage = scanner.nextLine();
        // faire appel methode de team2;
    }
}
