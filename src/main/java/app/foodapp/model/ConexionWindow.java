package app.foodapp.model;
import javafx.scene.control.Label;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class ConnexionWindow {


    static public Label sign(ArrayList<String> usersInfos) throws IOException, ParseException {
        usersInfos = FavoriteRecipes.SignIn(usersInfos,1);
        if (usersInfos.get(2)=="notExist"){
            Label notExist = new Label("You account doesn't exist !");
           return notExist;
        }
        if (usersInfos.get(2)=="alreadyCreated2"){
            Label alreadyCreated2 = new Label(" Welcome again "+usersInfos.get(0));
            return alreadyCreated2;
        }
    return new Label("");
    }
}


