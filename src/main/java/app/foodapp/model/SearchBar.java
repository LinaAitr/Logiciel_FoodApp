package app.foodapp.model;

import java.util.Scanner;

public class SearchBar {

    public static String search(Scanner scanner){
        String result = "";
        String scannerMessage = scanner.next();

        if(scannerMessage.equals("apple") ){
            result = "apple";
        }

        else if(scannerMessage.equals("vegan")){
            result ="vegan";
        }
        else {result ="Sorry, I can't do anything yet ! (Read: /*+ scanner.nextLine() +*/)";
        }
        return result;

    }
}
