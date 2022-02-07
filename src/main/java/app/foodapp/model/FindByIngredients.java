package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;


public class FindByIngredients {
    public static void main(String[] args){

        JSONParser parser = new JSONParser();
        //"C:\\Personal\\workspace\\Java\\Kalyani\\untitled\\src\\Jsonwrite.json")
        try(Reader reader = new FileReader("fichiers json/recipeInformation.json")) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            System.out.println(jsonObject);

            String name = (String) jsonObject.get("name");
            System.out.println("name :"+name);

           /* String Collage = (String) jsonObject.get("Collage");
            System.out.println("Collage :"+Collage);


            String Deparment = (String) jsonObject.get("Deparment");
            System.out.println("Deparment :"+Deparment);

            String Branch = (String) jsonObject.get("Branch");
            System.out.println("Branch :"+Branch);

            String year = (String) jsonObject.get("year");
            System.out.println("Year :"+year);*/


            JSONArray Sub = (JSONArray) jsonObject.get("Subjects");

            Iterator<Object> iterator =  Sub.iterator();


            while (iterator.hasNext()) {
                System.out.println("subjects :"+iterator.next());

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}


    /*
        public static void main(String[] args) {
            JSONParser jsonP = new JSONParser();
            try {
                JSONObject jsonO = (JSONObject) jsonP.parse(new FileReader("fichiers json/recipeInformation.json"));
                String name = (String) jsonO.get("name");
                System.out.println("name: "+name);

             /*   String servings =  String.valueOf(jsonO.get("servings"));
                String summary = (String) jsonO.get("summary");
                String sourceUrl = (String) jsonO.get("sourceUrl");
                System.out.println("servings :"+ servings);
                System.out.println("summary: "+ summary);
                System.out.println("sourceUrl: "+ sourceUrl);*/
             /*   JSONArray Sub = (JSONArray) jsonO.get("name");


                for (Object o : Sub) {
                    System.out.println("name :" + o);

                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }

        }
    }*/
    //    implementation 'com.fasterxml.jackson.core:jackson-databind:2.8.9'
//    implementation 'com.google.code.gson:gson:2.8.9'
//    implementation group: 'org.json', name: 'json', version: '20180813'




