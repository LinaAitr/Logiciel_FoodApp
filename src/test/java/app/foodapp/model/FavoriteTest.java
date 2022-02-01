package app.foodapp.model;

import org.junit.jupiter.api.Test;

public class FavoriteTest {

    @Test
    void getFavoriteTest(){
        int id = 4;
        Favorite favTest = new Favorite();
        favTest.addRecipe(new Recipe(id));

    }

    @Test
    void addRecipeTest(){
        //todo
    }

    @Test
    void removeRecipeTest(){
        //todo
    }

    @Test
    void cleanTest(){
        //todo
    }
}
