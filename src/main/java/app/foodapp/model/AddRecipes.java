package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AddRecipes {

    public static <e> void AddRecipe(String title , String time, HashMap ingredients) throws IOException, ParseException {
        String fileName = "MyRecipes.json";
        File f = new File(fileName);
        f.createNewFile();
        if (f.isFile()) {
            JSONParser jsonP = new JSONParser();
            JSONArray NewJsonArrayHomeRecipes = new JSONArray();
            try {
                if (f.length()!=0){
                    JSONArray jsonArrayHomeRecipes = (JSONArray) jsonP.parse(new FileReader(fileName));
                    for (int i=0; i<jsonArrayHomeRecipes.size();i++) {
                        JSONObject madeHomeRecipe = (JSONObject) jsonArrayHomeRecipes.get(i);
                        NewJsonArrayHomeRecipes.add(madeHomeRecipe);
                }
                }
                Iterator iterator = ingredients.entrySet().iterator();
                JSONObject ingredientsObject = new JSONObject();
                int ingredientsNumber=0;
                while (iterator.hasNext()) {
                    ingredientsNumber++;
                    Map.Entry ingredient = (Map.Entry) iterator.next();
                    JSONObject ingredientObject = new JSONObject();
                    ingredientObject.put("Name",ingredient.getKey());
                    ingredientObject.put("Quantity",ingredient.getValue());
                    ingredientsObject.put(ingredientsNumber,ingredientObject);
                }

                JSONObject jsonObjectNewRecipe = new JSONObject();
                jsonObjectNewRecipe.put("Time",  time);
                jsonObjectNewRecipe.put("Name",  title);
                jsonObjectNewRecipe.put("Ingredients",  ingredientsObject);
                NewJsonArrayHomeRecipes.add(jsonObjectNewRecipe);

                FileWriter fw = new FileWriter(fileName);
                BufferedWriter bw = new BufferedWriter(fw);
                fw.write(String.valueOf(NewJsonArrayHomeRecipes));
                bw.newLine();
                fw.close();

            } catch (IOException e  ) {
                e.printStackTrace();
            }
        }
    }




    }


