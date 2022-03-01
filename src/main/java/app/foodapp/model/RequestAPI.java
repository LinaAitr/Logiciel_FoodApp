package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.String.valueOf;

public class RequestAPI {

    //public static String APIKey = "ab7c3f5a18a04dd8903bc5fdb0be40e9";
    public static String APIKey = "30ca87269ac8432c8130d7bef6ae2e49";
   // public static String APIKey = "4e944d67e59d4271b4181168f3535444";


    public static StringBuilder ConnectAPI(URL url) throws IOException {
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

    public static boolean SearchByIngredient(String ingredient) throws IOException {
        try {
            boolean recipeFound = false;
            idList.clear();
            int numberRecipe=20;
            URL URL = new URL("https://api.spoonacular.com/recipes/findByIngredients?apiKey="+APIKey+"&ingredients="+ingredient+"&number="+numberRecipe);
            JSONParser  parse = new JSONParser();
            JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(ConnectAPI(URL)));

            if (dataObject.isEmpty()){
                System.out.println("No recipe found !");
                return recipeFound;
            }
            else{
                for (int i=0; i<dataObject.size();i++){
                    JSONObject recipeData = (JSONObject) dataObject.get(i);

                    System.out.print(i+"-");
                    System.out.println(recipeData.get("title"));
                    String id = valueOf(recipeData.get("id"));
                    idList.add(id);
                }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

     return true;
    }

    static ArrayList<String> idList= new ArrayList<>();

    public static boolean SearchByKey(String key){
        try {
            int l=-1;
            idList.clear();
            int numberRecipe=20;
            URL URL = new URL("https://api.spoonacular.com/recipes/complexSearch?apiKey="+APIKey+"&query="+key+"&number="+numberRecipe);
            JSONParser parse = new JSONParser();
            JSONObject jsonO = (JSONObject)parse.parse(String.valueOf(ConnectAPI(URL)));

            JSONArray resultRecipes = (JSONArray) jsonO.get("results");

            if (resultRecipes.isEmpty()) {
                System.out.println("No recipe found !");
                return false;
            }
            else{
                for (Object recipe : resultRecipes) {
                    JSONObject person = (JSONObject) recipe;
                    l++;
                    System.out.print(l+"-");
                    String name = (String) person.get("title");
                    String id = valueOf(person.get("id"));
                    idList.add(id);
                    System.out.println(name);
                }
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        if (idList.size() !=0 ){
            return true;
        }
        return false;
    }

}
