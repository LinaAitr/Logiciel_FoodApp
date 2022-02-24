package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;

public class RecipeInformations {
    JSONObject recipeObj;
    public static String APIKey = "ab7c3f5a18a04dd8903bc5fdb0be40e9";
    //String APIKey = "30ca87269ac8432c8130d7bef6ae2e49";


    public  RecipeInformations (JSONObject recipeObj ){
        this.recipeObj=recipeObj;
    }

    public void getTitle(){

        System.out.println("title: "+recipeObj.get("title"));
    }
    public void getSummary(){
        System.out.println("Summary: "+recipeObj.get("summary"));
    }

    public void getReadyInMinutes(){

        System.out.println("readyInMinutes: "+recipeObj.get("readyInMinutes")+" mn");
    }
    public void getServings(){

        System.out.println("servings: "+recipeObj.get("servings"));
    }
    public void getImage(){

        System.out.println("image: "+recipeObj.get("image"));
    }
    public void getInstructions(){

        JSONArray dataObject = (JSONArray) recipeObj.get("analyzedInstructions") ;
        JSONObject recipeData = (JSONObject) dataObject.get(0);
        JSONArray dataObject2 = (JSONArray) recipeData.get("steps") ;
        System.out.println("instructions : ");
        for (int i=0; i<dataObject2.size();i++){
            JSONObject recipeData2 = (JSONObject) dataObject2.get(i);
            System.out.println("Step "+i+" : "+recipeData2.get("step"));
        }

        }

    public void getId(){
        System.out.println("id: "+recipeObj.get("id"));

    }


    public void extendedIngredients(){

        JSONArray missedIngredients = (JSONArray) recipeObj.get("extendedIngredients");
        System.out.println("Ingredients : ");
        for (Object Ingredients : missedIngredients){
            JSONObject missedIngredientsObj = (JSONObject) Ingredients;
            UsedIngredients newMissedIngredients= new UsedIngredients(missedIngredientsObj);
            System.out.println("-"+newMissedIngredients.getName()+" : "+newMissedIngredients.getAmount()+" "+newMissedIngredients.getUnit());


        }

    }


    public static void SearchById(String id){
        try {
            URL URL = new URL("https://api.spoonacular.com/recipes/"+id+"/information?apiKey="+APIKey);
            JSONParser parse = new JSONParser();
            JSONObject dataObject = (JSONObject) parse.parse(String.valueOf(RequestAPI.ConnectAPI(URL)));
            RecipeInformations recipe = new  RecipeInformations(dataObject);
            System.out.println();
            recipe.getTitle();
            System.out.println();
            recipe.getSummary();
            System.out.println();
            recipe.getReadyInMinutes();
            System.out.println();
            recipe.getServings();
            System.out.println();
            recipe.getImage();
            System.out.println();
            recipe.extendedIngredients();
            System.out.println();
            recipe.getInstructions();
            System.out.println();




        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }



}

