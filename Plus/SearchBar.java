package app.foodapp.model;

/*
public class SearchBar {

    public static String search(Scanner scanner) throws IOException, ParseException { // renvoi peut etre une liste ou un object
        String result = "";
        JSONParser jsonP = new JSONParser();
        String scannerMessage = scanner.nextLine(); // modifié pour mettre nextLine() et donc boucle while hasNext ..

        if (scannerMessage.equals("apple") || scannerMessage.equals("vegan") || scannerMessage.equals("favorite")) {
            if (scannerMessage.equals("apple")) {
                JSONArray arrayOfRecipes = (JSONArray) jsonP.parse(new FileReader("fichiers json/findByIngredients.json"));

                for (Object recipe : arrayOfRecipes) {
                    JSONObject recipeObj = (JSONObject) recipe;
                    RecipeInformations newRecipe = new RecipeInformations(recipeObj);

                    result += newRecipe.getTitle() + "\n";
                }
            }


            if (scannerMessage.equals("vegan")) {
                JSONObject jsonO = (JSONObject) jsonP.parse(new FileReader("fichiers json/recipeInformation.json"));
                RecipeInformations recipeInformations = new RecipeInformations(jsonO);
                result = recipeInformations.getTitle();
            }
            if (scannerMessage.equals("favorite")) {
                JSONObject jsonO = (JSONObject) jsonP.parse(new FileReader("fichiers json/recipeInformation.json"));
                RecipeInformations recipeInformations = new RecipeInformations(jsonO);
                result = recipeInformations.getTitle();
            }
        }
        else {
            //System.out.println(scannerMessage);
            result = "Sorry, I can't do anything yet ! ";(Read:  scanner.nextLine() +")";
        }
        return result;
    }

    public static void selectRecipe(Scanner scanner){
        String scMessage = scanner.nextLine();
        // faire appel methode de team2;
    }
}
*/