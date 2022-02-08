package app.foodapp.model;

import java.util.Scanner;

public class SearchBar {

    public static String search(Scanner scanner){ // renvoi peut etre une liste ou un object
        String result = "";
        String scannerMessage = scanner.next(); // modifié pour mettre nextLine() et donc boucle while hasNext ..

        if(scannerMessage.equals("apple") ){
            result = "apple"; // appeler methode qui renvoi les noms des plats, (list1)
        }

        else if(scannerMessage.equals("vegan")){
            result ="vegan"; // appeler methode qui renvoi les noms des plats, (list2)
        }
        else {result ="Sorry, I can't do anything yet ! (Read: /*+ scanner.nextLine() +*/)";
        }
        return result;

    }

    public static void selectRecipe(Scanner scanner){
        String scMessage = scanner.nextLine();
        // faire appel methode de team2;
    }
}
