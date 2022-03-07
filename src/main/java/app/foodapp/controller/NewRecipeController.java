package app.foodapp.controller;

import app.foodapp.model.AddRecipes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class NewRecipeController {

    @FXML private TextField titleNewRecipe;
    @FXML private TextField timeProductionNewRecipe;

    @FXML private TextField ingredientName1;
    @FXML private TextField ingredientQuantity1;
    @FXML private TextField ingredientUnity1;
    @FXML private TextField ingredientName2;
    @FXML private TextField ingredientQuantity2;
    @FXML private TextField ingredientUnity2;
    @FXML private TextField ingredientName3;
    @FXML private TextField ingredientQuantity3;
    @FXML private TextField ingredientUnity3;
    @FXML private TextField ingredientName4;
    @FXML private TextField ingredientQuantity4;
    @FXML private TextField ingredientUnity4;
    @FXML private TextField ingredientName5;
    @FXML private TextField ingredientQuantity5;
    @FXML private TextField ingredientUnity5;
    @FXML private TextField ingredientName6;
    @FXML private TextField ingredientQuantity6;
    @FXML private TextField ingredientUnity6;

    @FXML private TextField step1;
    @FXML private TextField step2;
    @FXML private TextField step3;
    @FXML private TextField step4;
    @FXML private TextField step5;
    @FXML private TextField step6;

    private void addNewRecipe() {
        String titleRecipe = titleNewRecipe.getText();
        String timeProduction = timeProductionNewRecipe.getText();
        HashMap<String, ArrayList<String>> ingredients = makeIngredientList();
        ArrayList<String> steps = makeStepsList();


        //AddRecipes.AddRecipe(user, titleRecipe, timeProduction, ingredients, steps);todo

    }


        private HashMap<String, ArrayList<String>> makeIngredientList(){
            HashMap<String, ArrayList<String>> ingredients = new HashMap<>();

            if (ingredientName1.getText() != ""){
                ArrayList<String> ingredient = new ArrayList<>();
                ingredient.add(ingredientUnity1.getText());
                ingredient.add(ingredientQuantity1.getText());
                ingredients.put(ingredientName1.getText() , ingredient);
            }

            if (ingredientName3.getText() != ""){
                ArrayList<String> ingredient = new ArrayList<>();
                ingredient.add(ingredientUnity3.getText());
                ingredient.add(ingredientQuantity3.getText());
                ingredients.put(ingredientName3.getText(), ingredient);
            }

            if (ingredientName4.getText() != ""){
                ArrayList<String> ingredient = new ArrayList<>();
                ingredient.add(ingredientQuantity4.getText());
                ingredients.put(ingredientName4.getText(), ingredient);
            }

            if (ingredientName5.getText() != ""){
                ArrayList<String> ingredient = new ArrayList<>();
                ingredient.add(ingredientUnity5.getText());
                ingredient.add(ingredientQuantity5.getText());
                ingredients.put(ingredientName5.getText(), ingredient);
            }

            if (ingredientName2.getText() != ""){
                ArrayList<String> ingredient = new ArrayList<>();
                ingredient.add(ingredientUnity2.getText());
                ingredient.add(ingredientQuantity2.getText());
                ingredient.add(ingredientName2.getText());
                ingredients.put("2", ingredient);
            }

            if (ingredientName6.getText() != ""){
                ArrayList<String> ingredient = new ArrayList<>();
                ingredient.add(ingredientUnity6.getText());
                ingredient.add(ingredientQuantity6.getText());
                ingredients.put(ingredientName6.getText(), ingredient);
            }
            return ingredients;
        }

        private ArrayList<String> makeStepsList(){
            ArrayList<String> steps = new ArrayList<>();
            if (step1.getText() != "") {
                steps.add(step1.getText());
            }
            if (step2.getText() != "") {
                steps.add(step2.getText());
            }
            if (step3.getText() != "") {
                steps.add(step3.getText());
            }
            if (step4.getText() != "") {
                steps.add(step4.getText());
            }
            if (step5.getText() != "") {
                steps.add(step5.getText());
            }
            if (step6.getText() != "") {
                steps.add(step6.getText());
            }

            return steps;

        }





}
