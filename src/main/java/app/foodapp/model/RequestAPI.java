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
    //public static String APIKey = "4e944d67e59d4271b4181168f3535444";
    //public static String APIKey   = "79f2327aad3240e68f49b7de252cd5fe";
    //public static String APIKey = "a2c302f11e894e71962240cde6bd7c5e";
   // public static String APIKey =  "46058900bcc748ed8daaf0ba6ec0deea";
   //public static String APIKey =  "62347f3022614a4ea4288fb87c696515";




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
    static ArrayList<String> idList= new ArrayList<>();
    static ArrayList<String> nameList= new ArrayList<>();



    public static ArrayList<ArrayList<String>> SearchByIngredient(String ingredient) throws IOException {
        ArrayList<ArrayList<String>> content = new ArrayList<>();
        try {
            idList.clear();
            nameList.clear();
            int numberRecipe=5;
            URL URL = new URL("https://api.spoonacular.com/recipes/findByIngredients?apiKey="+APIKey+"&ingredients="+ingredient+"&number="+numberRecipe);
            JSONParser  parse = new JSONParser();
            JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(ConnectAPI(URL)));
                for (int i=0; i<dataObject.size();i++){
                    JSONObject recipeData = (JSONObject) dataObject.get(i);
                    String name = (String) recipeData.get("title");
                    String id = valueOf(recipeData.get("id"));
                    idList.add(id);
                    nameList.add(name);

            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        //content.clear();
        content.add(idList);
        content.add(nameList);

        return content;
    }



    public static ArrayList<ArrayList<String>> SearchByKey(String key){
        try {
            idList.clear();
            nameList.clear();
            int numberRecipe=5;
            URL URL = new URL("https://api.spoonacular.com/recipes/complexSearch?apiKey="+APIKey+"&query="+key+"&number="+numberRecipe);
            JSONParser parse = new JSONParser();
            JSONObject jsonO = (JSONObject)parse.parse(String.valueOf(ConnectAPI(URL)));

            JSONArray resultRecipes = (JSONArray) jsonO.get("results");
/*
            if (resultRecipes.isEmpty()) {
                System.out.println("No recipe found !");
                return false;
            }*/
            //else{
                for (Object recipe : resultRecipes) {
                    JSONObject person = (JSONObject) recipe;
                    String name = (String) person.get("title");
                    String id = valueOf(person.get("id"));
                    idList.add(id);
                    nameList.add(name);
                }
         //   }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        ArrayList<ArrayList<String>> content = new ArrayList<>();
        content.add(idList);
        content.add(nameList);
        return content;
    }

}
