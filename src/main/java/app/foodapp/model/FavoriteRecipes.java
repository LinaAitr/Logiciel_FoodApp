package app.foodapp.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FavoriteRecipes {


    public static void FillFile(String FavoriteRecipe) throws IOException {
       String fileName ="Favorites.txt";
        FileWriter fw = new FileWriter(fileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(FavoriteRecipe);
        bw.newLine();
        bw.close();


    }
}




