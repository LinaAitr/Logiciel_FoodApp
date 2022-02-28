package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class FavoriteRecipes {

    static ArrayList<String> nameList= new ArrayList<>();

    public static void FillFile(String FavoriteRecipe,String code) throws IOException, ParseException {
        String fileName = "Favorites.json";
        File f = new File(fileName);
        f.createNewFile();
       if (f.isFile()) {
            JSONParser jsonP = new JSONParser();
           JSONArray jsonArray = new JSONArray();
           try {
                if (f.length()!=0){
                    jsonArray = (JSONArray) jsonP.parse(new FileReader(fileName));
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put(code, FavoriteRecipe);
                    jsonArray.add(jsonObject1);
                }
                else
                {
                    JSONObject jsonObject1 = new JSONObject();
                    JSONArray jsonArray2 = new JSONArray();
                    jsonObject1.put(code, FavoriteRecipe);
                    jsonArray.add(jsonObject1);

                }

                FileWriter fw = new FileWriter(fileName);
                BufferedWriter bw = new BufferedWriter(fw);
                fw.write(String.valueOf(jsonArray));
                bw.newLine();
                fw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
       }
    }

    public static Object ShowFavorites(String code) throws IOException, ParseException {
    String fileName = "Favorites.json";
    boolean containsValue= false;
    int j=-1;
    File f = new File(fileName);
    f.createNewFile();
    if (f.isFile()) {
        if (f.length()!=0){
            JSONParser jsonP = new JSONParser();
            JSONArray arrayOfRecipes = (JSONArray) jsonP.parse(new FileReader(fileName));
                for (int i=0; i<arrayOfRecipes.size();i++) {
                    JSONObject favoriteRecipe = (JSONObject) arrayOfRecipes.get(i);
                    if (favoriteRecipe.get(code)!=null){
                        containsValue=true;
                        nameList.add((String) favoriteRecipe.get(code));
                        j++;
                        System.out.print(j+" )");
                        System.out.println(favoriteRecipe.get(code));
                    }
                }
          if (!containsValue)  System.out.println("Nothing to show");

    }
        else System.out.println("Nothing to show");
    }

    Object infos[] = new Object[]{j,containsValue};
        return infos;
    }


    public static void DeleteFavorite(String code, int number) throws IOException, ParseException {
        String fileName = "Favorites.json";
        File f = new File(fileName);
        f.createNewFile();
        if (f.isFile()) {
            if (f.length()!=0){
                JSONParser jsonP = new JSONParser();
                JSONArray arrayOfRecipes = (JSONArray) jsonP.parse(new FileReader(fileName));

                for (int i=0; i<arrayOfRecipes.size();i++) {
                    JSONObject favoriteRecipe = (JSONObject) arrayOfRecipes.get(i);
                    if (favoriteRecipe.containsKey(code) && favoriteRecipe.containsValue(nameList.get(number))){
                        arrayOfRecipes.remove(favoriteRecipe);
                    }
                }
                FileWriter fw = new FileWriter(fileName);
                BufferedWriter bw = new BufferedWriter(fw);
                fw.write(String.valueOf(arrayOfRecipes));
                bw.newLine();
                fw.close();
            }
        }
    }

}











