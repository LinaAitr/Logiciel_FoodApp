package app.foodapp.model;

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


public class TryAPI_RecipeInfo {

        public static void SearchById(int id){

        try {

            /* apiKey: ab7c3f5a18a04dd8903bc5fdb0be40e9
            pour ingredient
            url : url donné +"?apiKey=ab7c3f5a18a04dd8903bc5fdb0be40e9&" et "ingredients=" et ingredient
            pour plusieurs ",+" entre les ingredients
            */

            URL url = new URL("https://api.spoonacular.com/recipes/"+id+"/information?apiKey=30ca87269ac8432c8130d7bef6ae2e49");

            HttpURLConnection conn =(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if(responseCode !=200){
                throw new RuntimeException("erreur");
            }
            else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();

                //System.out.println(informationString);

                JSONParser parse = new JSONParser();
                JSONObject dataObject = (JSONObject) parse.parse(String.valueOf(informationString));
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

            }


        } catch (MalformedURLException | ParseException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
}


