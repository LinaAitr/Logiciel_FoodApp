package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class RecipeInformations {
    JSONObject recipeObj;
   // public static String APIKey = "ab7c3f5a18a04dd8903bc5fdb0be40e9";
   //public static String APIKey = "30ca87269ac8432c8130d7bef6ae2e49";
    public static String APIKey = "4e944d67e59d4271b4181168f3535444";
   //public static String APIKey   = "79f2327aad3240e68f49b7de252cd5fe";
    //public static String APIKey = "a2c302f11e894e71962240cde6bd7c5e";
    //public static String APIKey =  "46058900bcc748ed8daaf0ba6ec0deea";




    public RecipeInformations (String id ) throws IOException, ParseException {
        URL URL = new URL("https://api.spoonacular.com/recipes/"+id+"/information?apiKey="+APIKey);
        JSONParser parse = new JSONParser();
        recipeObj = (JSONObject) parse.parse(String.valueOf(RequestAPI.ConnectAPI(URL)));
    }

    public String getTitle(){
        System.out.println("title: "+recipeObj.get("title"));
        return (String) recipeObj.get("title");
    }
    public String getSummary(){

        //System.out.println("Summary: "+recipeObj.get("summary"));
        return (String) recipeObj.get("summary");
    }

    public String getReadyInMinutes(){

       // System.out.println("readyInMinutes: "+recipeObj.get("readyInMinutes")+" mn");
        return recipeObj.get("readyInMinutes")+" mn";
    }
    public String getServings(){

        //System.out.println("servings: "+recipeObj.get("servings"));
        return "servings: "+recipeObj.get("servings");
    }
    public String getImage(){
        System.out.println("image: "+recipeObj.get("image"));
        return (String) recipeObj.get("image");
    }
    public ArrayList<String> getInstructions(){

        JSONArray dataObject = (JSONArray) recipeObj.get("analyzedInstructions") ;
        JSONObject recipeData = (JSONObject) dataObject.get(0);
        JSONArray dataObject2 = (JSONArray) recipeData.get("steps") ;
        ArrayList<String> steps =new ArrayList<>();

        System.out.println("instructions : ");
        for (int i=0; i<dataObject2.size();i++){
            JSONObject recipeData2 = (JSONObject) dataObject2.get(i);
            //System.out.println("Step "+i+" : "+recipeData2.get("step"));
            steps.add("Step "+i+" : "+recipeData2.get("step"));
        }
return steps;
        }

    public void getId(){
        System.out.println("id: "+recipeObj.get("id"));

    }


    public ArrayList<String> extendedIngredients(){

        JSONArray missedIngredients = (JSONArray) recipeObj.get("extendedIngredients");
        System.out.println("Ingredients : ");
        ArrayList<String> ingredients =new ArrayList<>();
        for (Object Ingredients : missedIngredients){
            JSONObject missedIngredientsObj = (JSONObject) Ingredients;
            UsedIngredients newMissedIngredients= new UsedIngredients(missedIngredientsObj);
           // System.out.println("-"+newMissedIngredients.getName()+" : "+newMissedIngredients.getAmount()+" "+newMissedIngredients.getUnit());
        ingredients.add("-"+newMissedIngredients.getName()+" : "+newMissedIngredients.getAmount()+" "+newMissedIngredients.getUnit());
        }
 return ingredients;
    }


    public static void SearchById(RecipeInformations recipe) throws IOException, ParseException {
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



    }



}

