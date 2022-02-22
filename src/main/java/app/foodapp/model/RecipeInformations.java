package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RecipeInformations {
    JSONObject recipeObj;

    public  RecipeInformations (JSONObject recipeObj ){
        this.recipeObj=recipeObj;
    }

    public void getTitle(){
        System.out.println("title: "+recipeObj.get("title"));
    }
    public void getSummary(){
        System.out.println("Summary: "+recipeObj.get("Summary"));
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

    public void getId(){
        System.out.println("id: "+recipeObj.get("id"));

    }


   /* public String getMissedIngredientCount(){

        return valueOf(recipeObj.get("missedIngredientCount"));
        System.out.println("usedIngredientCount: "+recipeObj.get("usedIngredientCount"));

    }*/

   /* //public String getLikes(){
        return valueOf(recipeObj.get("likes"));
    }*/

    public void extendedIngredients(){

        JSONArray missedIngredients = (JSONArray) recipeObj.get("extendedIngredients");
        System.out.println("Ingredients : ");
        for (Object Ingredients : missedIngredients){
            JSONObject missedIngredientsObj = (JSONObject) Ingredients;
            UsedIngredients newMissedIngredients= new UsedIngredients(missedIngredientsObj);
            System.out.println("-"+newMissedIngredients.getName()+" : "+newMissedIngredients.getAmount()+" "+newMissedIngredients.getUnit());


        }

    }













}
