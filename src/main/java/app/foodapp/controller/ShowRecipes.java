package app.foodapp.controller;

import app.foodapp.model.RecipeInformations;
import app.foodapp.model.RequestAPI;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.String.valueOf;

public class ShowRecipes {
   //public static String APIKey = "ab7c3f5a18a04dd8903bc5fdb0be40e9";
    //public static String APIKey = "30ca87269ac8432c8130d7bef6ae2e49";
    public static String APIKey = "4e944d67e59d4271b4181168f3535444";
    //public static String APIKey ="79f2327aad3240e68f49b7de252cd5fe";
    static ArrayList<String> idList= new ArrayList<>();


    public static ArrayList<String> showRecipe(String keyWord) throws IOException, ParseException {
        Scanner word = new Scanner(keyWord);
        return SearchByIngredient(keyWord);

    }
    public static ArrayList<String> SearchByIngredient(String ingredient) throws IOException {
        ArrayList<String> searchResult = new ArrayList<>();

        try {
            idList.clear();
            int numberRecipe=20;
            URL URL = new URL("https://api.spoonacular.com/recipes/findByIngredients?apiKey="+APIKey+"&ingredients="+ingredient+"&number="+numberRecipe);
            JSONParser parse = new JSONParser();
            JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(RequestAPI.ConnectAPI(URL)));

            if (dataObject.isEmpty()){

                searchResult.add("No recipe found !");
                return searchResult;
            }
            else{
                for (int i=0; i<dataObject.size();i++){
                    JSONObject recipeData = (JSONObject) dataObject.get(i);
                    String id = valueOf(recipeData.get("id"));
                    searchResult.add(""+ i+1 +"-"+    recipeData.get("title")+", id: "+  id);
                    idList.add(id);
                }

                return searchResult ;

            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
     return  searchResult;
    }

//    public static void getRecipeiInformations(){
//        RecipeInformations recipe = new  RecipeInformations(RequestAPI.idList.get());
//        RecipeInformations.SearchById(recipe);
//    }

}
