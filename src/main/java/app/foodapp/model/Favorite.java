package app.foodapp.model;
import java.util.ArrayList;
import java.util.List;

public class Favorite {

    private List<Dish> favorites;

    public Favorite(){
        favorites = new ArrayList<>();
    }

    public List<Dish> getFavorites(){
        return favorites;
    }

    public void addDish(Dish dish){
        favorites.add(dish);
    }

    public void removeDish(Dish dish){
        favorites.remove(dish);
    }

    public void clean(){
        favorites = new ArrayList<>();
    }


}
