package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static java.lang.String.valueOf;

public class RecipeInformations {
    //declaration of variables
    private String id;
    private String title;
    private String image;
    private String usedIngredientCount;
    private String missedIngredientCount;
    private String likes;
    JSONObject recipeObj;

    public  RecipeInformations (JSONObject recipeObj ){
        this.recipeObj=recipeObj;
    }

    public String getTitle(){
        this.title = (String) recipeObj.get("title");
     return title;
    }

    public String getImage(){
        this.image = (String) recipeObj.get("image");
        return image;
    }

    public String getId(){
        this.id = valueOf(recipeObj.get("id"));
        return id;
    }

    public String getUsedIngredientCount(){
        this.usedIngredientCount = valueOf(recipeObj.get("usedIngredientCount"));
        return usedIngredientCount;
    }

    public String getMissedIngredientCount(){
        this.missedIngredientCount = valueOf(recipeObj.get("missedIngredientCount"));
        return missedIngredientCount;
    }

    public String getLikes(){
        this.likes = valueOf(recipeObj.get("likes"));
        return likes;
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
