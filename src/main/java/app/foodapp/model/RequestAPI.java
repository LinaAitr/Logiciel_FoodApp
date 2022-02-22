package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import static java.lang.String.valueOf;

public class RequestAPI {

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
            URL URL = new URL("https://api.spoonacular.com/recipes/findByIngredients?apiKey=30ca87269ac8432c8130d7bef6ae2e49&ingredients="+ingredient+"&number=20");
            JSONParser  parse = new JSONParser();
            JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(ConnectAPI(URL)));

            if (dataObject.isEmpty()){
                System.out.println("No recipe found !");
                return false;
            }
            else{
                for (int i=0; i<dataObject.size();i++){
                    JSONObject recipeData = (JSONObject) dataObject.get(i);
                    System.out.print(i+"-");
                    System.out.println(recipeData.get("title"));
                    System.out.println(recipeData.get("id"));
                }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return true;
    }



    public static boolean SearchByKey(String key){
        try {
            URL URL = new URL("https://api.spoonacular.com/recipes/complexSearch?apiKey=30ca87269ac8432c8130d7bef6ae2e49&query="+key+"&number=20");
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
                    String name = (String) person.get("title");
                    String id = valueOf(person.get("id"));
                    System.out.println(name);
                    System.out.println(id);
                }
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void SearchById(int id){
        try {
            URL URL = new URL("https://api.spoonacular.com/recipes/"+id+"/information?apiKey=30ca87269ac8432c8130d7bef6ae2e49");
            JSONParser parse = new JSONParser();
            JSONObject dataObject = (JSONObject) parse.parse(String.valueOf(ConnectAPI(URL)));
            String readyInMinutes =  valueOf(dataObject.get("readyInMinutes"));
            String servings =  valueOf(dataObject.get("servings"));
            String title =  (String) dataObject.get("title");
            String sourceUrl =  (String) dataObject.get("sourceUrl");
            String summary =  (String) dataObject.get("summary");
            System.out.println("Title: "+ title);
            System.out.print("This will be ready in: "+ readyInMinutes+" mn");
            System.out.println(" for : "+ servings +" people");
            System.out.println("sourceUrl: "+ sourceUrl);
            System.out.println("summary : "+ summary);

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

}
