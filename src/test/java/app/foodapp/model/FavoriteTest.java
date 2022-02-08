package app.foodapp.model;

import org.junit.jupiter.api.Test;

import static org.testfx.assertions.api.Assertions.*;

public class FavoriteTest {

    @Test
    void addRecipeTest(){
        int id = 4;
        Favorite favTest = new Favorite();
        Recipe r =  new Recipe(id);
        favTest.addRecipe(r);
        assertThat(favTest.getFavorites().get(0)).isEqualTo(r);
    }

    @Test
    void removeRecipeTest(){
        int id = 4;
        Favorite favTest = new Favorite();
        Recipe r =  new Recipe(id);
        favTest.addRecipe(r);
        favTest.removeRecipe(r);
        assertThat(favTest).isNull();
    }

    @Test
    void cleanTest(){
        int id = 4;
        Favorite favTest = new Favorite();
        Recipe r =  new Recipe(id);
        favTest.addRecipe(r);
        favTest.clean();
        assertThat(favTest).isNull();
    }
}
