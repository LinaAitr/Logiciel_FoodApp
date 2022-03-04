package app.foodapp;

import app.foodapp.model.RecipeInformations;
import app.foodapp.model.RequestAPI;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;


public class FoodApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
        primaryStage.setTitle("FoodApp");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();




        Group group = new Group();
        VBox vbox = new VBox();
        vbox.getChildren().addAll(new Text("Les recettes sont :"));
        Hyperlink link = new Hyperlink();



        ArrayList<String> tr = RequestAPI.SearchByKey("apple");

        for (int i=0; i<20;i++){

            RecipeInformations rec = new RecipeInformations(tr.get(i));
            final Image image = new Image(rec.getImage());
            Hyperlink tg =new Hyperlink(""+rec.getTitle());
            tg.setMaxHeight(400);
            final ImageView imageView = new ImageView(image);
            imageView.setFitHeight(200);
            imageView.setFitWidth(400);
            vbox.getChildren().addAll(tg);
            vbox.getChildren().addAll(imageView);
        }


        group.getChildren().add(vbox);
        primaryStage.setScene(new Scene(group));
    }

    public static void main(String[] args) { launch(args); }
}
