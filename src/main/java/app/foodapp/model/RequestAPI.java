package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
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

    public static void SearchByIngredient(String ingredient) throws IOException {
        try {
            URL URL = new URL("https://api.spoonacular.com/recipes/findByIngredients?apiKey=30ca87269ac8432c8130d7bef6ae2e49&ingredients="+ingredient);
            JSONParser  parse = new JSONParser();
            JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(ConnectAPI(URL)));

            for (int i=0; i<dataObject.size();i++){
                JSONObject recipeData = (JSONObject) dataObject.get(i);
                System.out.print(i+"-");
                System.out.println(recipeData.get("title"));
            }
        } catch (MalformedURLException | ParseException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void SearchByKey(String key){
        try {
            URL URL = new URL("https://api.spoonacular.com/recipes/complexSearch?apiKey=30ca87269ac8432c8130d7bef6ae2e49&query="+key);
            JSONParser parse = new JSONParser();
            JSONObject jsonO = (JSONObject)parse.parse(String.valueOf(ConnectAPI(URL)));

            JSONArray listOfStates = (JSONArray) jsonO.get("results");
            System.out.println(jsonO.get("results"));
            JSONArray a = (JSONArray) listOfStates;
            for (Object o : a) {
                JSONObject person = (JSONObject) o;
                String name = (String) person.get("title");
                String id = valueOf(person.get("id"));
                System.out.println(name);
                System.out.println(id);
            }
        } catch (MalformedURLException | ParseException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        } catch (MalformedURLException | ParseException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
