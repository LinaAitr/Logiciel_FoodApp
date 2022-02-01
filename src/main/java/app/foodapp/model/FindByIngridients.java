package app.foodapp.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class FindByIngridients {

        public static void main(String[] args) {
            JSONParser jsonP = new JSONParser();
            try {
                JSONObject jsonO = (JSONObject)jsonP.parse(new FileReader("/amuhome/k21232433/IdeaProjects/kebci-sofiane-tp-note/foodapp/fichiers json/recipeInformation.json"));

                String servings =  String.valueOf(jsonO.get("servings"));
                String summary = (String) jsonO.get("summary");
                String sourceUrl = (String) jsonO.get("sourceUrl");
                System.out.println("servings :"+ servings);
                System.out.println("summary: "+ summary);
                System.out.println("sourceUrl: "+ sourceUrl);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }

        }
    }




