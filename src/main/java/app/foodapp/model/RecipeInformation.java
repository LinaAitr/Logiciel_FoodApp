package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class RecipeInformation {

    public static void main(String[] args) {

        JSONParser jsonP = new JSONParser();
        try {
            JSONObject jsonO = (JSONObject)jsonP.parse(new FileReader("fichiers json/recipeInformation.json"));
            String title =  (String) jsonO.get("title");
            String summary = (String) jsonO.get("summary");
            String sourceUrl = (String) jsonO.get("sourceUrl");
            System.out.println("title: "+ title);
            System.out.println("sourceUrl: "+ sourceUrl);
            System.out.println("summary: "+ summary);


            JSONArray listOfStates = (JSONArray) jsonO.get("extendedIngredients");

            Iterator iterator = listOfStates.iterator();
            while (iterator.hasNext()) {
                Iterator<Map.Entry> itr1 = ((Map) iterator.next()).entrySet().iterator();
                while (itr1.hasNext()) {
                    Map.Entry pair = itr1.next();
                    System.out.println(pair.getKey() + " : " + pair.getValue());
                }
            }

            // while (iterator.hasNext()) {
            //  System.out.println(iterator.next());
            //   }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }
}

