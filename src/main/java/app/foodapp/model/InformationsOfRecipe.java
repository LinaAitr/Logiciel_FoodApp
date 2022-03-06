package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;

public class InformationsOfRecipe {
        JSONObject recipeObj;
        //public static String APIKey = "ab7c3f5a18a04dd8903bc5fdb0be40e9";
        public static String APIKey = "30ca87269ac8432c8130d7bef6ae2e49";
        //public static String APIKey = "4e944d67e59d4271b4181168f3535444";



        public  InformationsOfRecipe (String id ) throws IOException, ParseException {
            URL URL = new URL("https://api.spoonacular.com/recipes/"+id+"/information?apiKey="+APIKey);
            JSONParser parse = new JSONParser();
            recipeObj = (JSONObject) parse.parse(String.valueOf(RequestAPI.ConnectAPI(URL)));
        }

        public String getTitle(){
            System.out.println("title: "+recipeObj.get("title"));
            return (String) recipeObj.get("title");
        }
        public String getSummary(){
            return "Summary: "+recipeObj.get("summary");
        }

        public String getReadyInMinutes(){

            return "readyInMinutes: "+recipeObj.get("readyInMinutes")+" mn";
        }
        public String getServings(){

           return  "servings: "+recipeObj.get("servings");
        }
        public String getImage(){

           return "image: "+recipeObj.get("image");
        }
        public String getInstructions(){

            JSONArray dataObject = (JSONArray) recipeObj.get("analyzedInstructions") ;
            JSONObject recipeData = (JSONObject) dataObject.get(0);
            JSONArray dataObject2 = (JSONArray) recipeData.get("steps") ;
            String instructions = "instructions : ";
            for (int i=0; i<dataObject2.size();i++){
                JSONObject recipeData2 = (JSONObject) dataObject2.get(i);
                instructions = instructions+ "Step "+i+" : "+recipeData2.get("step");
            }
            return instructions;
        }

        public String getId(){
            return "id: "+recipeObj.get("id");

        }


        public String extendedIngredients(){

            JSONArray missedIngredients = (JSONArray) recipeObj.get("extendedIngredients");
            String ingredients = "Ingredients : ";
            for (Object Ingredients : missedIngredients){
                JSONObject missedIngredientsObj = (JSONObject) Ingredients;
                UsedIngredients newMissedIngredients= new UsedIngredients(missedIngredientsObj);
                ingredients = ingredients + "-"+newMissedIngredients.getName()+" : "+newMissedIngredients.getAmount()+" "+newMissedIngredients.getUnit();
            }
            return ingredients;
        }


        public static String SearchById(app.foodapp.model.InformationsOfRecipe recipe) throws IOException, ParseException {
            return "" + recipe.getTitle()+" "+ recipe.getSummary()+recipe.getReadyInMinutes()+ recipe.getServings()+
                    recipe.getImage() +recipe.extendedIngredients() +recipe.getInstructions();


        }






}
