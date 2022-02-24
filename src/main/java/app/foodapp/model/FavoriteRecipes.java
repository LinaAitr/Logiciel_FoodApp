package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class FavoriteRecipes {


    public static void FillFile(String FavoriteRecipe) throws IOException, ParseException {
        String fileName = "Favorites.json";
        File f = new File(fileName);
        if (f.isFile()) {

            JSONParser jsonP = new JSONParser();
            try {
                JSONArray arrayOfRecipes = (JSONArray) jsonP.parse(new FileReader(fileName));
                JSONArray jsonArray = new JSONArray();
                for (int i=0; i<arrayOfRecipes.size();i++) {
                    JSONObject recipeData = (JSONObject) arrayOfRecipes.get(i);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("Name", recipeData.get("Name"));
                    jsonArray.add(jsonObject);

                }
                JSONObject jsonObject1 = new JSONObject();

                jsonObject1.put("Name", FavoriteRecipe);
                //jsonObject2.put("id", "Madame");
                jsonArray.add(jsonObject1);
                // jsonArray.add(jsonObject2);

                FileWriter fw = new FileWriter(fileName, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(String.valueOf(jsonArray));
                bw.newLine();
                fw.close();


            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}






