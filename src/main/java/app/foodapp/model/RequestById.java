package app.foodapp.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;

public class RequestById {
    public static String key = "ab7c3f5a18a04dd8903bc5fdb0be40e9";
    //String key = "30ca87269ac8432c8130d7bef6ae2e49";

    public static void SearchById(String id){
        try {
            URL URL = new URL("https://api.spoonacular.com/recipes/"+id+"/information?apiKey="+key);
            JSONParser parse = new JSONParser();
            JSONObject dataObject = (JSONObject) parse.parse(String.valueOf(RequestAPI.ConnectAPI(URL)));
            RecipeInformations recipe = new  RecipeInformations(dataObject);
            recipe.getTitle();
            recipe.getSummary();
            recipe.getReadyInMinutes();
            recipe.getServings();
            recipe.getImage();
            recipe.extendedIngredients();



        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }



    }
