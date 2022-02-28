package app.foodapp.controller;

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
    public static String APIKey = "4e944d67e59d4271b4181168f3535444";
    static ArrayList<String> idList= new ArrayList<>();


    public static String showRecipe(String keyWord) throws IOException, ParseException {
        Scanner word = new Scanner(keyWord);
        return SearchByIngredient(keyWord);

    }
    public static String SearchByIngredient(String ingredient) throws IOException {
        String result = "";
        try {
            idList.clear();
            int numberRecipe=20;
            URL URL = new URL("https://api.spoonacular.com/recipes/findByIngredients?apiKey="+APIKey+"&ingredients="+ingredient+"&number="+numberRecipe);
            JSONParser parse = new JSONParser();
            JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(RequestAPI.ConnectAPI(URL)));

            if (dataObject.isEmpty()){
                result = result +"No recipe found !";
                return result;
            }
            else{
                for (int i=0; i<dataObject.size();i++){
                    JSONObject recipeData = (JSONObject) dataObject.get(i);
                    String id = valueOf(recipeData.get("id"));
                     result = result + i+"-" +
                    recipeData.get("title")+
                    id;
                    idList.add(id);
                }
                return result ;
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
     return result;
    }


}
