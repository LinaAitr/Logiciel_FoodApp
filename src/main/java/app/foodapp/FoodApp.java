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
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import javax.print.DocFlavor;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.Timer;


public class FoodApp extends Application {
    VBox vbox = new VBox();
    VBox vbox2 = new VBox();
    VBox vBox4 =new VBox();
    Stage primaryStage1;
    ArrayList<String> loginInfos        = new ArrayList<>();
    //ArrayList<String> loginInfos = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
        primaryStage.setTitle("FoodApp");
        primaryStage1=primaryStage;
        //primaryStage.setFullScreen(true);
        primaryStage.setHeight(700);
        primaryStage.setWidth(1500);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        LogPage(primaryStage);
    }

    private void LogPage(Stage primaryStage) {
        vbox.getChildren().remove(0,vbox.getChildren().size());
        Group group = new Group();

        final Label password = new Label("Password :");
        final Label userName = new Label("Username :");
        final Button signButton = new Button("Sign in");
        final Button logButton = new Button("Log in");
        signButton.setLayoutX(1222);
        signButton.paddingProperty();

        TextField passwordTextField = new TextField("sofiane");
        TextField userNameTextField = new TextField("8dd4");

        //Label notExist=new Label("");

        logButton.setOnAction(actionEvent -> {
            try {
                ArrayList<String> usersInfos = new ArrayList<>();
                usersInfos.add(passwordTextField.getText());
                usersInfos.add(userNameTextField.getText());
                //Label notExist=ConexionWindow.sign(usersInfos);
                //notExist.setText(ConexionWindow.sign(usersInfos).getText());
                loginInfos = FavoriteRecipes.SignIn(usersInfos,1);
                if (loginInfos.get(2)=="notExist"){
                    Label notExist = new Label("Account doesnt exist ! PLease verify spelling or create a new one !");
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
                else if (loginInfos.get(2)=="alreadyCreated2"){
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




        signButton.setOnAction(sign -> {
            try {
                ArrayList<String> usersInfos = new ArrayList<>();
                usersInfos.add(passwordTextField.getText());
                usersInfos.add(userNameTextField.getText());
                loginInfos = FavoriteRecipes.SignIn(usersInfos,2);
                if (loginInfos.get(2)=="AccountCreated"){
                    vbox.getChildren().remove(0,vbox.getChildren().size());
                    Label notExist = new Label("Account created !");
                    Label welcomeUser = new Label(" Welcome "+usersInfos.get(0));
                    vbox.getChildren().add(notExist);
                    vbox.getChildren().add(welcomeUser);
                    Timer t =new Timer();
                    t.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            // vbox.getChildren().remove(notExist);
                            notExist.setVisible(false);
                        }
                    },1000);
                    mainPage();
                }
                else if (loginInfos.get(2)=="alreadyCreated"){
                    vbox.getChildren().remove(0,vbox.getChildren().size());
                    Label accountExists = new Label("Account Already created !");
                    Label welcomeUser = new Label(" Welcome again "+usersInfos.get(0));
                    vbox.getChildren().add(accountExists);
                    vbox.getChildren().add(welcomeUser);
                    Timer t =new Timer();
                    t.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            accountExists.setVisible(false);
                        }
                    },3000);
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
        double windowHeight = primaryStage.getHeight();
        double windowWidth = primaryStage.getMaxWidth();
        vbox.setLayoutX((windowHeight/2)-100);
        vbox.setLayoutY(windowHeight/2-100);


        primaryStage.setScene(sc);
    }
    public  void getListOfIdForSearch(TextField textField) throws IOException, ParseException {
        ArrayList<String> lisOfId = RequestAPI.SearchByKey(textField.getText());
        showRecipes(textField,lisOfId,4);

    }
    public  void getListOfIdForFavorites(TextField textField) throws IOException, ParseException {
        ArrayList<String> lisOfId = FavoriteRecipes.ShowFavorites(loginInfos);
        showRecipes(textField,lisOfId,lisOfId.size()-1);

    }

    public void showRecipes(TextField textField,ArrayList<String> lisOfId, int n) throws IOException, ParseException {
        vbox2.getChildren().addAll(new Text("Recipes with "+ textField.getText() +" :"));

        //VBox vbox = new VBox();
        vbox2.getChildren().remove(0,vbox2.getChildren().size());
        for (int j=0; j<=(n/5);j++){
            HBox hbox = new HBox();
            hbox.setId(String.valueOf(j));
            for (int i=j*5; i<(j+1)*(n);i++){
                VBox vbox3 = new VBox();
                RecipeInformations rec = new RecipeInformations(lisOfId.get(i));
                final Image imageRecipe = new Image(rec.getImage());

                File file2 = new File("/amuhome/k21232433/Bureau/Genie logiciel/foodapp/coeurRempli.png");
                String localUrlFullHeart = file2.toURI().toURL().toString();
                final Image iconFull = new Image(localUrlFullHeart);

                File file = new File("/amuhome/k21232433/Bureau/Genie logiciel/foodapp/coeurVide.png");
                String localUrl = file.toURI().toURL().toString();
                final Image icon = new Image(localUrl);

                ArrayList<String> idLIstFavorite = FavoriteRecipes.ShowFavorites(loginInfos);


                Hyperlink title =new Hyperlink(""+rec.getTitle());
                Label time = new Label("Time : "+rec.getReadyInMinutes());
                //Hyperlink title =new Hyperlink("title"+i);
                showSingelRecipe(title,lisOfId.get(i),textField );
                title.setMaxHeight(400);
                String recipeId = rec.getId();
                final ImageView recipeImage = new ImageView(imageRecipe);
                final ImageView favoriteImage;

                if (idLIstFavorite.contains(recipeId)){
                    favoriteImage =new ImageView(iconFull);
                }
                else favoriteImage =new ImageView(icon);


                Button addFavoriteButton = new Button();
                addFavoriteButton.setOnAction(addToFavorite ->{
                    try {
                        if ( !idLIstFavorite.contains(rec.getId())){

                            favoriteImage.setImage(iconFull);
                            FavoriteRecipes.FillFile(rec.getId(),loginInfos);
                        }
                        else
                        {

                            favoriteImage.setImage(icon);
                            FavoriteRecipes.DeleteFavorite(loginInfos,idLIstFavorite.indexOf(rec.getId()));

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });
                addFavoriteButton.setStyle("-fx-background-color: transparent;");
                addFavoriteButton.setGraphic(favoriteImage);
                recipeImage.setFitHeight(100);
                recipeImage.setFitWidth(200);
                favoriteImage.setFitHeight(20);
                favoriteImage.setFitWidth(20);
                vbox3.getChildren().add(title);
                HBox hboxFav = new HBox();
                hboxFav.getChildren().add(time);
                hboxFav.getChildren().add(addFavoriteButton);
                hboxFav.setSpacing(30);
                hboxFav.setStyle("-fx-alignment:TOP_CENTER");
                vbox3.setStyle("-fx-alignment:TOP_CENTER");
                vbox3.getChildren().add(hboxFav);
                vbox3.setStyle("-fx-background-color: pink;");

                vbox3.getChildren().add(recipeImage);

                hbox.getChildren().add(vbox3);

            }
            hbox.setSpacing(50);
            vbox2.getChildren().add(hbox);
            vbox2.setLayoutX(100);
            vbox2.setLayoutY(150);

        }
    }



    public void showSingelRecipe(Hyperlink tg, String id, TextField textField){
        tg.setOnAction(event->{
            try {
                RecipeInformations rec = new RecipeInformations(id);
                vbox2.getChildren().remove(0,vbox2.getChildren().size());
                Button previous = new Button("<--Previous");
                previous.setOnAction(prev ->{
                    try {
                        getListOfIdForSearch(textField);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });
                vbox2.getChildren().add(previous);
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
        final Button favorites = new Button("Favorites");
        final Button logOff = new Button("Log off");
        logOff.setOnAction(logoff->{
            LogPage(primaryStage1);
        });



        Label ingredient = new Label("Give us an ingredient");
        TextField textField = new TextField("");
        HBox hBoxMain = new HBox();
        hBoxMain.getChildren().add(button);
        hBoxMain.getChildren().add(textField);
        hBoxMain.getChildren().add(favorites);
        hBoxMain.getChildren().add(logOff);

        // vbox.getChildren().add(button);
        vbox.getChildren().add(ingredient);
        vbox.getChildren().add(hBoxMain);
        vbox.setStyle("-fx-alignment:TOP_CENTER");
        vbox.setLayoutX(600);
        favorites.setOnAction(fav->{
            try {
                showMyFavorites(loginInfos,textField);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        button.setOnAction(actionEvent -> {
            try {
                vbox2.getChildren().remove(0,vbox2.getChildren().size());
                getListOfIdForSearch(textField);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    private void showMyFavorites(ArrayList<String> loginInfos,TextField textField) throws IOException, ParseException {
        getListOfIdForFavorites(textField);

    }

    public static void main(String[] args) { launch(args); }
}
