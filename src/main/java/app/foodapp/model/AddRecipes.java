package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AddRecipes {

    public static void AddRecipe(ArrayList<String> user , String title , String time, HashMap<String, ArrayList<String>> ingredients, ArrayList<String> steps) throws IOException, ParseException {
        String fileName = "MyRecipes.json";
        File f = new File(fileName);
        ArrayList<String> userAndPassword = new ArrayList<>();
        userAndPassword.add(0,user.get(0));
        userAndPassword.add(1,user.get(1));
        String code = String.valueOf(userAndPassword);
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
                Iterator<Map.Entry<String, ArrayList<String>>> iterator = ingredients.entrySet().iterator();
                JSONArray IgredientsNewJsonArray = new JSONArray();
                while (iterator.hasNext()) {
                    Map.Entry<String, ArrayList<String>> ingredient = iterator.next();
                    JSONObject ingredientObject = new JSONObject();
                    ingredientObject.put("Name",ingredient.getKey());
                    ArrayList<String> ingredientQuantityAndUnity = ingredient.getValue();
                    ingredientObject.put("Quantity", ingredientQuantityAndUnity.get(0));
                    ingredientObject.put("Unity", ingredientQuantityAndUnity.get(1));
                    IgredientsNewJsonArray.add(ingredientObject);
                }
                JSONArray stepsNewJsonArray = new JSONArray();

                for (int i=0 ; i<steps.size();i++) {
                    JSONObject stepObject = new JSONObject();
                    stepObject.put("Step",steps.get(i));
                    stepsNewJsonArray.add(stepObject);
                }

                JSONObject jsonObjectNewRecipe = new JSONObject();
                JSONObject jsonObjectNewRecipeUser = new JSONObject();
                jsonObjectNewRecipe.put("Time",  time);
                jsonObjectNewRecipe.put("Name",  title);
                jsonObjectNewRecipe.put("Ingredients",  IgredientsNewJsonArray);
                jsonObjectNewRecipe.put("Steps",  stepsNewJsonArray);

                jsonObjectNewRecipeUser.put(code,jsonObjectNewRecipe);
                NewJsonArrayHomeRecipes.add(jsonObjectNewRecipeUser);

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

    public static ArrayList<String> ShowRecipes(ArrayList<String> user) throws IOException, ParseException {
        String fileName = "MyRecipes.json";
        File f = new File(fileName);
        ArrayList<String> userAndPassword = new ArrayList<>();
        userAndPassword.add(0,user.get(0));
        userAndPassword.add(1,user.get(1));
        String code = String.valueOf(userAndPassword);
        f.createNewFile();
        ArrayList<String> names = new ArrayList<>();

        if (f.isFile()) {
            if (f.length()!=0){
                String result = "";
                JSONParser jsonP = new JSONParser();
                JSONArray arrayOfRecipes = (JSONArray) jsonP.parse(new FileReader(fileName));
                for (int i=0; i<arrayOfRecipes.size();i++) {
                    System.out.println();
                    JSONObject myRecipes = (JSONObject) arrayOfRecipes.get(i);
                    if (myRecipes.get(code)!=null){
                        System.out.println("Recipe number "+ (i+1) +" : ");
                        result += "Recipe number "+ (i+1) + " : " ;
                        JSONObject recipe = (JSONObject) myRecipes.get(code);
                        JSONArray arrayOfIngredients = (JSONArray) recipe.get("Ingredients");
                        System.out.println("Name :" +recipe.get("Name"));
                        names.add((String) recipe.get("Name"));
                        System.out.println("Time :" +recipe.get("Time"));
                        System.out.println("Ingredients :");
                        for (int j=0; j<arrayOfIngredients.size();j++) {
                            JSONObject ingredient = (JSONObject) arrayOfIngredients.get(j);
                            System.out.println("-Name : "+ ingredient.get("Name"));
                            System.out.println("-Quantity : "+ingredient.get("Quantity")+" "+ingredient.get("Unity"));
                            System.out.println();
                        }
                        JSONArray arrayOfSteps = (JSONArray) recipe.get("Steps");

                        for (int j=0; j<arrayOfSteps.size();j++) {
                            JSONObject step = (JSONObject) arrayOfSteps.get(j);
                            System.out.println("-step "+(j+1)+" : "+ step.get("Step"));
                            System.out.println();
                        }


                    }
                    System.out.println();
                    System.out.println("No recipe found");
                    System.out.println();

                }
            }
            else{
                System.out.println();
                System.out.println("No recipe found");
                System.out.println();

            }

        }


 return names;
        }
    }








