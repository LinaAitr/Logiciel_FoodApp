package app.foodapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class  FoodAppController implements Initializable {


    @FXML Label image;
    @FXML private TextField searchBar;
    @FXML private Button searchButton;
    @FXML private Button favoritesButton;

    @FXML private Button helloWorldButton;
    @FXML private Button goodByeWorldButton;

    @FXML private Button logInButton;
    @FXML private Button dropMenuButton;
    @FXML private Button homeButton;


    @FXML private Label label;
    @FXML private Hyperlink recipe;




    //    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        searchBar.setAlignment(Pos.TOP_CENTER);

    }

    @FXML
    private void search(){
        searchBar.setVisible(true);
        label.setText(searchBar.getText());
        searchBar.setText("");
        //logInButton.setVisible(false);
        goodByeWorldButton.setVisible(false);
    }

    @FXML
    private void openFavorites(){

    }


    @FXML
    private void displayHelloWorld() {
        label.setText("Hello World");
        helloWorldButton.setVisible(false);
        if (!goodByeWorldButton.isVisible())
            goodByeWorldButton.setVisible(true);
    }

    @FXML
    private void goodByeWorld() {
        label.setText("");
        goodByeWorldButton.setVisible(false);
        if (!helloWorldButton.isVisible())
            helloWorldButton.setVisible(false);
    }

    @FXML
    private void logIn(){
        label.setText("your log in is done ");
        recipe.setVisible(false);
        if (!recipe.isVisible())
            recipe.setVisible(false);
    }

    @FXML
    private void welcomeToApp() {
        label.setText("Welcome to foodApp");
        helloWorldButton.setVisible(false);
        if (!goodByeWorldButton.isVisible())
            goodByeWorldButton.setVisible(true);
    }

//    @FXML
//    private void showRecipes() throws IOException, ParseException {
//        recipes.setText(String.valueOf(ShowRecipes.showRecipe(searchBar.getText())));
//    }

    @FXML
    private void showRecipes() throws IOException, ParseException {

        ArrayList<String> charOfRecipe= ShowRecipes.showRecipe(searchBar.getText());
        //System.out.println(charOfRecipe);

        for (String s : charOfRecipe) {

            recipe.setText( s+ "\r");
            recipe.setVisible(true);
        }

    }

    @FXML
    private void toPrint(){
        System.out.println("ça marche!!");
    }
}
