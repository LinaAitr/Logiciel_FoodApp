package app.foodapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class FoodAppController implements Initializable {

    @FXML private Button helloWorldButton;
    @FXML private Button goodByeWorldButton;

    @FXML private Button logInButton;
    @FXML private Button searchButton;
    @FXML private Button dropMenuButton;
    @FXML private Button homeButton;

    @FXML private Label label;

    //    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {}

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
            helloWorldButton.setVisible(true);
    }

    @FXML
    private void logIn(){
        label.setText("your log in is done ");
    }
}
