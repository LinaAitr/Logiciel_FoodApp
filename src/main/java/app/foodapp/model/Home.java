package app.foodapp.model;

import java.util.ArrayList;
import java.util.List;

public class Home {

    private Favorite favorite;
    private List<Recipe> recipeList;
    //private searchBar;

    public Home() {
        this.favorite = new Favorite();
        this.recipeList = new ArrayList<>();
    }


    public Favorite getFavorite() {
        return favorite;
    }

    public void setFavorite(Favorite favorite) {
        this.favorite = favorite;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
}
