package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.valueOf;

public class RequestAPI {

    //public static String APIKey = "ab7c3f5a18a04dd8903bc5fdb0be40e9";
    //public static String APIKey = "30ca87269ac8432c8130d7bef6ae2e49";
    //public static String APIKey = "4e944d67e59d4271b4181168f3535444";
    public static String APIKey = "79f2327aad3240e68f49b7de252cd5fe";


    public static StringBuilder connectAPI(URL url) throws IOException {
        HttpURLConnection conn =(HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();
        StringBuilder informationString = new StringBuilder();
        if(responseCode !=200){
            throw new RuntimeException("erreur");
        }
        else {
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                informationString.append(scanner.nextLine());
            }
            scanner.close();
        }
        return informationString;
    }

    public static List<String> searchByIngredient(String ingredient) throws IOException {
        try {
            JSONArray dataObject = jsonSearchByIngredient(ingredient);
            List<String> idList = new ArrayList<>();
            for (int i=0; i<dataObject.size();i++){
                JSONObject recipeData = (JSONObject) dataObject.get(i);
                String id = valueOf(recipeData.get("id"));
                idList.add(id);
            }
            return idList;
            }
        catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static JSONArray jsonSearchByIngredient(String ingredient) throws IOException, ParseException {
        int numberRecipe=20;
        URL URL = new URL("https://api.spoonacular.com/recipes/findByIngredients?apiKey="+APIKey+"&ingredients="+ingredient+"&number="+numberRecipe);
        JSONParser  parse = new JSONParser();

        return (JSONArray) parse.parse(String.valueOf(connectAPI(URL)));
    }


    public static List<String> searchByKey(String key){
        try {
            JSONArray resultRecipes = jsonSearchByKey(key);
            List<String> idList = new ArrayList<>();
            for (Object recipe : resultRecipes) {
                JSONObject person = (JSONObject) recipe;
                String id = valueOf(person.get("id"));
                idList.add(id);
            }
            return idList;
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static JSONArray jsonSearchByKey(String key) throws IOException, ParseException {
        int numberRecipe=20;
        URL URL = new URL("https://api.spoonacular.com/recipes/complexSearch?apiKey="+APIKey+"&query="+key+"&number="+numberRecipe);
        JSONParser parse = new JSONParser();
        JSONObject jsonO = (JSONObject) parse.parse(String.valueOf(connectAPI(URL)));
        return (JSONArray) jsonO.get("results");
    }

}
