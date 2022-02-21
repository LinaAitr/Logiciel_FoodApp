package app.foodapp.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class TryAPI_RecipeInfo {



    public static void main(String[] args) throws IOException {

        try {

            /* apiKey: ab7c3f5a18a04dd8903bc5fdb0be40e9
            pour ingredient
            url : url donné +"?apiKey=ab7c3f5a18a04dd8903bc5fdb0be40e9&" et "ingredients=" et ingredient
            pour plusieurs ",+" entre les ingredients
            */

            URL url = new URL("https://api.spoonacular.com/recipes/632577/information?apiKey=ab7c3f5a18a04dd8903bc5fdb0be40e9&");

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
                String title =  (String) dataObject.get("title");
                System.out.println("summary: "+ title);





            }


        } catch (MalformedURLException | ParseException e) {
            e.printStackTrace();
        }
    }
}


