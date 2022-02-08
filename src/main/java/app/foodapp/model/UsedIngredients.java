package app.foodapp.model;

import org.json.simple.JSONObject;

import static java.lang.String.valueOf;

public class UsedIngredients {

        private String id ;
        private String amount;
        private String unit;
        private String unitLong;
        private String unitShort;
        private String aisle;
        private String name;
        private String original;
        private String originalString;
        private String originalName;
        private String extendedName;
        private String image;
        JSONObject missedIngredientsObj;
        public UsedIngredients(JSONObject missedIngredientsObj){
             this.missedIngredientsObj=missedIngredientsObj;
        }

        public String getID(){
                this.id = valueOf(missedIngredientsObj.get("id"));
                return id;
        }
        public String getName(){
                this.name = valueOf(missedIngredientsObj.get("name"));
                return name;
        }
}
