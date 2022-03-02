package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class FavoriteRecipes {


    public static void FillFile(String FavoriteRecipe,ArrayList<String> listOfInfos) throws IOException, ParseException {
        String code = listOfInfos.get(0)+listOfInfos.get(1);
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

    static ArrayList<String> nameList= new ArrayList<>();

    public static Object ShowFavorites(ArrayList<String> listOfInfos) throws IOException, ParseException {
      String code = listOfInfos.get(0)+listOfInfos.get(1);
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
                    if (favoriteRecipe.get(code)!=null && favoriteRecipe.get(code)!=""){
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





    public static ArrayList<String> SignIn(ArrayList<String> user, int logAndSign) throws IOException, ParseException {
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
                    jsonObject1.put(user.get(0)+user.get(1), "");
                    if(jsonArray.contains(jsonObject1)){
                        if (logAndSign == 2){
                            System.out.println("Account Already created !");
                            System.out.println("Welcome Again "+ user.get(0));
                        }
                        else
                        {
                            System.out.println("Welcome Again "+ user.get(0));
                        }
                        return user;
                    }
                    else
                    if (logAndSign==1)
                    {
                        user.add(2,"no");
                        return user;
                    }
                   // jsonArray.add(jsonObject1);
                }
                else
                {
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put(user, "");
                    jsonArray.add(jsonObject1);
                }
                FileWriter fw = new FileWriter(fileName);
                BufferedWriter bw = new BufferedWriter(fw);
                fw.write(String.valueOf(jsonArray));
                bw.newLine();
                fw.close();
                System.out.println("Account created !");
                System.out.println("Welcome  "+ user.get(0));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return user;
    }






        public static void DeleteFavorite(ArrayList<String> listOfInfos, int number) throws IOException, ParseException {
        String fileName = "Favorites.json";
        String code = listOfInfos.get(0)+listOfInfos.get(1);
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











