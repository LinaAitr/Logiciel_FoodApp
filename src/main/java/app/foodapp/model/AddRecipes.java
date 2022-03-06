//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package app.foodapp.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AddRecipes {

    public static void AddRecipe(ArrayList<String> user, String title, String time, HashMap<String, ArrayList<String>> ingredients, ArrayList<String> steps) throws IOException, ParseException {
        String fileName = "MyRecipes.json";
        File f = new File(fileName);
        ArrayList<String> userAndPassword = new ArrayList();
        userAndPassword.add(0, (String)user.get(0));
        userAndPassword.add(1, (String)user.get(1));
        String code = String.valueOf(userAndPassword);
        f.createNewFile();
        if (f.isFile()) {
            JSONParser jsonP = new JSONParser();
            JSONArray NewJsonArrayHomeRecipes = new JSONArray();
            try {
                if (f.length() != 0L) {
                    JSONArray jsonArrayHomeRecipes = (JSONArray)jsonP.parse(new FileReader(fileName));

                    for(int i = 0; i < jsonArrayHomeRecipes.size(); ++i) {
                        JSONObject madeHomeRecipe = (JSONObject)jsonArrayHomeRecipes.get(i);
                        NewJsonArrayHomeRecipes.add(madeHomeRecipe);
                    }
                }

                Iterator<Entry<String, ArrayList<String>>> iterator = ingredients.entrySet().iterator();
                JSONArray IgredientsNewJsonArray = new JSONArray();

                JSONObject jsonObjectNewRecipe;
                while(iterator.hasNext()) {
                    Entry<String, ArrayList<String>> ingredient = (Entry)iterator.next();
                    jsonObjectNewRecipe = new JSONObject();
                    jsonObjectNewRecipe.put("Name", ingredient.getKey());
                    ArrayList<String> ingredientQuantityAndUnity = (ArrayList)ingredient.getValue();
                    jsonObjectNewRecipe.put("Quantity", ingredientQuantityAndUnity.get(0));
                    jsonObjectNewRecipe.put("Unity", ingredientQuantityAndUnity.get(1));
                    IgredientsNewJsonArray.add(jsonObjectNewRecipe);
                }
                JSONArray stepsNewJsonArray = new JSONArray();

                JSONObject jsonObjectNewRecipeUser;
                for(int i = 0; i < steps.size(); ++i) {
                    jsonObjectNewRecipeUser = new JSONObject();
                    jsonObjectNewRecipeUser.put("Step", steps.get(i));
                    stepsNewJsonArray.add(jsonObjectNewRecipeUser);
                }

                jsonObjectNewRecipe = new JSONObject();
                jsonObjectNewRecipeUser = new JSONObject();
                jsonObjectNewRecipe.put("Time", time);
                jsonObjectNewRecipe.put("Name", title);
                jsonObjectNewRecipe.put("Ingredients", IgredientsNewJsonArray);
                jsonObjectNewRecipe.put("Steps", stepsNewJsonArray);
                jsonObjectNewRecipeUser.put(code, jsonObjectNewRecipe);
                NewJsonArrayHomeRecipes.add(jsonObjectNewRecipeUser);
                FileWriter fw = new FileWriter(fileName);
                BufferedWriter bw = new BufferedWriter(fw);
                fw.write(String.valueOf(NewJsonArrayHomeRecipes));
                bw.newLine();
                fw.close();
            } catch (IOException var18) {
                var18.printStackTrace();
            }
        }
    }

    public static void ShowRecipes(ArrayList<String> user) throws IOException, ParseException {
        String fileName = "MyRecipes.json";
        File f = new File(fileName);
        ArrayList<String> userAndPassword = new ArrayList();
        userAndPassword.add(0, (String)user.get(0));
        userAndPassword.add(1, (String)user.get(1));
        String code = String.valueOf(userAndPassword);
        f.createNewFile();

        if (f.isFile()) {
            if (f.length() != 0L) {
                JSONParser jsonP = new JSONParser();
                JSONArray arrayOfRecipes = (JSONArray)jsonP.parse(new FileReader(fileName));

                for(int i = 0; i < arrayOfRecipes.size(); ++i) {
                    System.out.println();
                    JSONObject myRecipes = (JSONObject)arrayOfRecipes.get(i);
                    if (myRecipes.get(code) != null) {
                        System.out.println("Recipe number " + (i + 1) + " : ");
                        JSONObject recipe = (JSONObject)myRecipes.get(code);
                        JSONArray arrayOfIngredients = (JSONArray)recipe.get("Ingredients");
                        System.out.println("Name :" + recipe.get("Name"));
                        System.out.println("Time :" + recipe.get("Time"));
                        System.out.println("Ingredients :");

                        for(int j = 0; j < arrayOfIngredients.size(); ++j) {
                            JSONObject ingredient = (JSONObject)arrayOfIngredients.get(j);
                            System.out.println("-Name : " + ingredient.get("Name"));
                            PrintStream var10000 = System.out;
                            Object var10001 = ingredient.get("Quantity");
                            var10000.println("-Quantity : " + var10001 + " " + ingredient.get("Unity"));
                            System.out.println();
                        }

                        JSONArray arrayOfSteps = (JSONArray)recipe.get("Steps");

                        for(int j = 0; j < arrayOfSteps.size(); ++j) {
                            JSONObject step = (JSONObject)arrayOfSteps.get(j);
                            System.out.println("-step " + (j + 1) + " : " + step.get("Step"));
                            System.out.println();
                        }


                    }
                    System.out.println();
                    System.out.println("No recipe found");
                    System.out.println();

                }
            } else {
                System.out.println();
                System.out.println("No recipe found");
                System.out.println();

            }

        }



    }
}








