package app.foodapp.controller;

import app.foodapp.model.FavoriteRecipes;
import app.foodapp.model.RecipeInformations;
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
import java.util.List;
import java.util.ResourceBundle;

public class FoodAppController implements Initializable {


    @FXML private TextField searchBar;
    @FXML private Button searchButton;
    @FXML private TextField codeFav;
    @FXML private Button searchFavButton;
    @FXML private Button favoritesButton;

    @FXML private Button helloWorldButton;
    @FXML private Button goodByeWorldButton;

    @FXML private Button logInButton;
    @FXML private Button dropMenuButton;
    @FXML private Button homeButton;


    @FXML private Label label;

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
    private void openFavorites() {
        codeFav.setVisible(true);
        searchFavButton.setVisible(true);

    }

    @FXML
    private void searchFav() throws IOException, ParseException {
        int i = 0;
        List<String> favList = new ArrayList<>();
        favList.add("69095");
        //FavoriteRecipes.ShowFavorites(codeFav.getText());
        for(String fav : favList){
            RecipeInformations infoFav = new RecipeInformations(fav);
            Hyperlink hyperlink = new Hyperlink(i+")"+ infoFav.getTitle());
        }
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
    }

    @FXML
    private void welcomeToApp() {
        label.setText("Welcome to foodApp");
        helloWorldButton.setVisible(false);
        if (!goodByeWorldButton.isVisible())
            goodByeWorldButton.setVisible(true);
    }
}
