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

public class TryAPI_KeyWord {
    public static void SearchByKey(String key){
        try {

            /* apiKey: ab7c3f5a18a04dd8903bc5fdb0be40e9
            pour ingredient
            url : url donné +"?apiKey=ab7c3f5a18a04dd8903bc5fdb0be40e9&" et "ingredients=" et ingredient
            pour plusieurs ",+" entre les ingredients
            */

            URL url = new URL("https://api.spoonacular.com/recipes/complexSearch?apiKey=30ca87269ac8432c8130d7bef6ae2e49&query="+key);

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



                JSONParser parse = new JSONParser();
                JSONObject jsonO = (JSONObject)parse.parse(String.valueOf(informationString));


                JSONArray listOfStates = (JSONArray) jsonO.get("results");
                System.out.println(jsonO.get("results"));
                JSONArray a = (JSONArray) listOfStates;
                for (Object o : a)
                {
                    JSONObject person = (JSONObject) o;
                    String name = (String) person.get("title");
                    String id = valueOf(person.get("id"));

                    System.out.println(name);
                    System.out.println(id);




                }


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

