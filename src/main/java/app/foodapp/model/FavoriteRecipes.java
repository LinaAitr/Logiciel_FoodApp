package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class FavoriteRecipes {


    public static void FillFile(String FavoriteRecipe,ArrayList<String> listOfInfos) throws IOException, ParseException {
        ArrayList<String> userAndPassword = new ArrayList<>();
        userAndPassword.add(0,listOfInfos.get(0));
        userAndPassword.add(1,listOfInfos.get(1));
        String code = String.valueOf(userAndPassword);
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
                    jsonObject1.put(code, FavoriteRecipe);
                    jsonArray.add(jsonObject1);

                }

                FileWriter fileWriter = new FileWriter(fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                fileWriter.write(String.valueOf(jsonArray));
                bufferedWriter.newLine();
                fileWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
       }
    }

    static ArrayList<String> nameList= new ArrayList<>();
    static ArrayList<String> idList= new ArrayList<>();


    public static ArrayList<String> ShowFavorites(ArrayList<String> listOfInfos) throws IOException, ParseException {
    ArrayList<String> userAndPassword = new ArrayList<>();
    userAndPassword.add(0,listOfInfos.get(0));
    userAndPassword.add(1,listOfInfos.get(1));
    String code = String.valueOf(userAndPassword);
    String fileName = "Favorites.json";
    idList.clear();
    //boolean containsValue= false;
   // int j=-1;
    File file = new File(fileName);
    file.createNewFile();
    if (file.isFile()) {
        if (file.length()!=0){
            JSONParser jsonP = new JSONParser();
            JSONArray arrayOfRecipes = (JSONArray) jsonP.parse(new FileReader(fileName));
                for (int i=0; i<arrayOfRecipes.size();i++) {
                    JSONObject favoriteRecipe = (JSONObject) arrayOfRecipes.get(i);
                    if (favoriteRecipe.get(code)!=null && favoriteRecipe.get(code)!=""){
                        idList.add((String) favoriteRecipe.get(code));
                        //j++;
                        // System.out.print(j+") ");
                        //System.out.println(favoriteRecipe.get(code));
                    }
                }
          //if (!containsValue)  System.out.println("Nothing to show");

    }
        //else System.out.println("Nothing to show");

    }

        return idList;
    }





    public static ArrayList<String> SignIn(ArrayList<String> listOfInfos, int logAndSign) throws IOException, ParseException {
        String fileName = "Favorites.json";

        ArrayList<String> userAndPassword = new ArrayList<>();
        userAndPassword.add(0,listOfInfos.get(0));
        userAndPassword.add(1,listOfInfos.get(1));
        String code = String.valueOf(userAndPassword);
        File f = new File(fileName);
        f.createNewFile();
        if (f.isFile()) {
            JSONParser jsonP = new JSONParser();
            JSONArray jsonArray = new JSONArray();
            try {
                if (f.length()!=0){
                    jsonArray = (JSONArray) jsonP.parse(new FileReader(fileName));
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put(code, "");
                    if(jsonArray.contains(jsonObject1)){
                        if (logAndSign == 2){
                            listOfInfos.add(2, "alreadyCreated");

                        }
                        else
                        {
                            listOfInfos.add(2, "alreadyCreated2");
                        }
                        return listOfInfos;
                    }

                   jsonArray.add(jsonObject1);
                }
                else
                {
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put(code, "");
                    jsonArray.add(jsonObject1);
                }
                if (logAndSign==1)
                {
                    listOfInfos.add(2,"notExist");
                    return listOfInfos;
                }
                FileWriter fw = new FileWriter(fileName);
                BufferedWriter bw = new BufferedWriter(fw);
                fw.write(String.valueOf(jsonArray));
                bw.newLine();
                fw.close();
                listOfInfos.add(2, "AccountCreated");


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listOfInfos;
    }






        public static void DeleteFavorite(ArrayList<String> listOfInfos, int number) throws IOException, ParseException {
        String fileName = "Favorites.json";
        ArrayList<String> userAndPassword = new ArrayList<>();
        userAndPassword.add(0,listOfInfos.get(0));
        userAndPassword.add(1,listOfInfos.get(1));
        String code = String.valueOf(userAndPassword);
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











