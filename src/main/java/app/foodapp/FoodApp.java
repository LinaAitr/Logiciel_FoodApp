package app.foodapp;

import app.foodapp.model.FavoriteRecipes;
import app.foodapp.model.RecipeInformations;
import app.foodapp.model.RequestAPI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;
import java.util.Timer;


public class FoodApp extends Application {
    VBox vbox = new VBox();
    VBox vbox2 = new VBox();
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
        primaryStage.setTitle("FoodApp");
        //primaryStage.setFullScreen(true);
        primaryStage.setHeight(700);
        primaryStage.setWidth(1500);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


        Group group = new Group();

        final Label password = new Label("Password :");
        final Label userName = new Label("Username :");
        final Button signButton = new Button("Sign in");
        final Button logButton = new Button("Log in");
        signButton.setLayoutX(1222);
        signButton.paddingProperty();

        TextField passwordTextField = new TextField("");
        TextField userNameTextField = new TextField("");

        //Label notExist=new Label("");

        logButton.setOnAction(actionEvent -> {
            try {
                ArrayList<String> usersInfos = new ArrayList<>();
                usersInfos.add(passwordTextField.getText());
                usersInfos.add(userNameTextField.getText());
               //Label notExist=ConexionWindow.sign(usersInfos);
                //notExist.setText(ConexionWindow.sign(usersInfos).getText());
                ArrayList<String> f = FavoriteRecipes.SignIn(usersInfos,1);
                if (f.get(2)=="notExist"){
                    Label notExist = new Label("You account doesn't exist !");
                    vbox.getChildren().add(notExist);
                    Timer t =new Timer();
                    t.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            // vbox.getChildren().remove(notExist);
                            notExist.setVisible(false);
                        }
                    },1000);
                }
                else if (f.get(2)=="alreadyCreated2"){
                    vbox.getChildren().remove(0,vbox.getChildren().size());
                    Label alreadyCreated2 = new Label(" Welcome again "+usersInfos.get(0));
                    vbox.getChildren().add(alreadyCreated2);
                    Timer t =new Timer();
                    t.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            // vbox.getChildren().remove(notExist);
                            alreadyCreated2.setVisible(false);
                        }
                    },3000);
                    //vbox.getChildren().addAll(mainPage());
                    mainPage();
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });



        //signButton.setStyle("-fx-background-color: pink");
        signButton.setLayoutX(1200);
        HBox hbox =new HBox();
        hbox.getChildren().add(signButton);
        hbox.getChildren().add(logButton);
        vbox.getChildren().add(hbox);
       // vbox.getChildren().add(signButton);
       // vbox.getChildren().add(logButton);
        vbox.getChildren().add(userName);
        vbox.getChildren().add(passwordTextField);
        vbox.getChildren().add(password);
        vbox.getChildren().add(userNameTextField);




        hbox.setStyle("-fx-alignment:TOP_CENTER");


        group.getChildren().add(vbox);
        group.getChildren().add(vbox2);
        group.setStyle("-fx-alignment:TOP_CENTER");
        Scene sc =new Scene(group);
        //primaryStage.setResizable(false);
        double windowHeight =primaryStage.getHeight();
        double windowWidth =primaryStage.getMaxWidth();
        vbox.setLayoutX((windowHeight/2)-100);
        vbox.setLayoutY(windowHeight/2-100);


        primaryStage.setScene(sc);
    }

    public void showRecipes(TextField textField) throws IOException, ParseException {
      ArrayList<String> tr = RequestAPI.SearchByKey(textField.getText());
      vbox2.getChildren().addAll(new Text("Recipes with "+ textField.getText() +" :"));

        //VBox vbox = new VBox();
        int n=5;
        for (int j=0; j<(n/4);j++){
        HBox hbox = new HBox();
        hbox.setId(String.valueOf(j));
        for (int i=j*4; i<(j+1)*(n);i++){
            VBox vbox3 = new VBox();
            //RecipeInformations rec = new RecipeInformations(tr.get(i));
            //final Image image = new Image(rec.getImage());
            //Hyperlink tg =new Hyperlink(""+rec.getTitle());
            Hyperlink tg =new Hyperlink("tg"+i);
            showSingelRecipe(tg,tr.get(i) );
            tg.setMaxHeight(400);
           // final ImageView imageView = new ImageView(image);
            //imageView.setFitHeight(100);
            //imageView.setFitWidth(200);
            vbox3.getChildren().add(tg);
            //vbox3.getChildren().add(imageView);
            hbox.getChildren().add(vbox3);

            }
        hbox.setSpacing(50);
        vbox2.getChildren().add(hbox);
        vbox2.setLayoutX(100);
        vbox2.setLayoutY(150);

        }
    }

    public void showSingelRecipe(Hyperlink tg, String id){
        tg.setOnAction(event->{
            try {
                RecipeInformations rec = new RecipeInformations(id);
                vbox2.getChildren().remove(0,vbox2.getChildren().size());
                Label title = new Label(tg.getText());
                Label time = new Label("Time : "+ rec.getReadyInMinutes());
                Label servings = new Label(rec.getServings());
                ArrayList<String> ingredients =rec.extendedIngredients();
                vbox2.getChildren().add(title);
                vbox2.getChildren().add(time);
                vbox2.getChildren().add(servings);

                Label ingredientLabel = new Label("Ingredients : ");
                vbox2.getChildren().add(ingredientLabel);

                for (int i=0;i<ingredients.size();i++){
                    Label ingredient = new Label("     -"+ingredients.get(i));
                    vbox2.getChildren().add(ingredient);

                }
                ArrayList<String> steps =rec.getInstructions();

                Label stepsLabel = new Label("steps : ");
                vbox2.getChildren().add(stepsLabel);
                for (int i=0;i<steps.size();i++){
                    Label step = new Label("     -"+steps.get(i));
                    vbox2.getChildren().add(step);

                }






            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

    }
    public void mainPage( ){
        vbox.setLayoutX(0);
        vbox.setLayoutY(0);

        final Button button = new Button("Search");
        Label ingredient = new Label("Give us an ingredient");
        TextField textField = new TextField("");
        HBox hBoxMain = new HBox();
        hBoxMain.getChildren().add(button);
        hBoxMain.getChildren().add(textField);
       // vbox.getChildren().add(button);
        vbox.getChildren().add(ingredient);
        vbox.getChildren().add(hBoxMain);
        vbox.setStyle("-fx-alignment:TOP_CENTER");
        vbox.setLayoutX(600);

        button.setOnAction(actionEvent -> {
            try {
                showRecipes(textField);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }
    public static void main(String[] args) { launch(args); }
}
