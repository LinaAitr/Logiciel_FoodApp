package app.foodapp.model;
import java.util.ArrayList;
import java.util.List;

public class Favorite {

    private List<Recipe> favorites;

    public Favorite(){
        favorites = new ArrayList<>();
    }

    public List<Recipe> getFavorites(){
        return favorites;
    }

    public void addRecipe(Recipe recipe){
        favorites.add(recipe);
    }

    public void removeRecipe(Recipe recipe){
        favorites.remove(recipe);
    }

    public void clean(){
        favorites = new ArrayList<>();
    }


}
