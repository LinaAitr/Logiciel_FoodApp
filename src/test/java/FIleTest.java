import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class FIleTest {
    public static void main(String[] args) {
         JSONParser jsonP = new JSONParser();
                try {
                    JSONObject jsonO = (JSONObject)jsonP.parse(new FileReader("/amuhome/k21232433/IdeaProjects/kebci-sofiane-tp-note/foodapp/fichiers json/recipeInformation.json"));

                    String title = (String )jsonO.get("title");
                    String summary = (String) jsonO.get("summary");
                    String sourceUrl = (String) jsonO.get("sourceUrl");
                    System.out.println("title :"+ title);
                    System.out.println("summary: "+ summary);
                    System.out.println("sourceUrl: "+ sourceUrl);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

    }
        }


