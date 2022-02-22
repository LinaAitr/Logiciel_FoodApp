package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static java.lang.String.valueOf;

public class RecipeInformations {
    JSONObject recipeObj;

    public  RecipeInformations (JSONObject recipeObj ){
        this.recipeObj=recipeObj;
    }

    public String getTitle(){
        return (String) recipeObj.get("title");
    }

    public String getImage(){
        return (String) recipeObj.get("image");
    }

    public String getId(){
        return valueOf(recipeObj.get("id"));
    }

    public String getUsedIngredientCount(){
        return valueOf(recipeObj.get("usedIngredientCount"));
    }

    public String getMissedIngredientCount(){
        return valueOf(recipeObj.get("missedIngredientCount"));
    }

    public String getLikes(){
        return valueOf(recipeObj.get("likes"));
    }
    public void MissedIngredients(){

        JSONArray missedIngredients = (JSONArray) recipeObj.get("missedIngredients");
        System.out.println("Ingredients : ");
        for (Object Ingredients : missedIngredients){
            JSONObject missedIngredientsObj = (JSONObject) Ingredients;
            UsedIngredients newMissedIngredients= new UsedIngredients(missedIngredientsObj);
            //System.out.println("Ingredients id : " + newMissedIngredients.getID());
            System.out.println("-"+newMissedIngredients.getName());

        }

    }













}
