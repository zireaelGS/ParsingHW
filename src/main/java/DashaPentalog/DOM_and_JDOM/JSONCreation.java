package DashaPentalog.DOM_and_JDOM;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JSONCreation {

    public static void main(String[] args) {
            //создаю первого персонажа
        JSONObject character1_0 = new JSONObject();
        JSONObject character1 = new JSONObject();
        character1.put("characterID", "001");
        character1.put("firstname", "Daenerys");
        character1.put("age", "22 y.o.");
        JSONArray titles = new JSONArray();
        titles.add("Queen of Meereen");
        titles.add("Mother of Dragon");
        titles.add("Khaleesi of the Great Grass Sea");
        titles.add("The Unburnt");
        titles.add("Breaker of Chains");
        character1.put("titles", titles);
        JSONArray aliases = new JSONArray();
        aliases.add("Dragonmother");
        aliases.add("Dani");
        character1.put("aliases", aliases);
        character1.put("ruler", "This is the head of the kingdom");
        character1_0.put("character",character1);
        //создаю второго персонажа
        JSONObject character2_0 = new JSONObject();
        JSONObject character2 = new JSONObject();
        character2.put("characterID", "002");
        character2.put("firstname", "Rhaegar");
        character2.put("age", "38 y.o.");
        JSONArray titles2 = new JSONArray();
        titles2.add("Last Dragon");
        titles2.add("Prince dragon");
        character2.put("titles", titles);
        JSONArray aliases2 = new JSONArray();
        aliases2.add("Silver prince");
        character2.put("aliases", aliases);
        character2.put("ruler", character1.get("characterID"));
        character2_0.put("character",character2);
        //создаю первую семью
        JSONObject family1 = new JSONObject();
        //массив из персонажей
        JSONArray charactersF1Array = new JSONArray();
        charactersF1Array.add(character1_0);
        charactersF1Array.add(character2_0);
        //добавляю в 1ю семью аттрибуты
        family1.put("idOfTheHouse","1");
        family1.put("nameOfTheHouse","Targaryen");
        //добавляю в 1ю семью 2х персонажей (массив выше)
        family1.put("characters",charactersF1Array);



        //создаю третьего персонажа
        JSONObject character3_0 = new JSONObject();
        JSONObject character3 = new JSONObject();
        character3.put("characterID", "003");
        character3.put("firstname", "John");
        character3.put("age", "22 y.o.");
        JSONArray titles3 = new JSONArray();
        titles3.add("King in the North");
        titles3.add("Warden of the North");
        character3.put("titles", titles);
        JSONArray aliases3 = new JSONArray();
        aliases3.add("Lord Snow");
        aliases3.add("The Bastard of Winterfell");
        character3.put("aliases", aliases);
        character3.put("ruler", "It is a head of the north");
        character3_0.put("character",character3);
        //создаю четвертого персонажа
        JSONObject character4_0 = new JSONObject();
        JSONObject character4 = new JSONObject();
        character4.put("characterID", "004");
        character4.put("firstname", "Arya");
        character4.put("age", "17 y.o.");
        JSONArray titles4 = new JSONArray();
        titles4.add("Princess");
        character4.put("titles", titles);
        JSONArray aliases4 = new JSONArray();
        aliases4.add("Faceless");
        aliases4.add("Arry");
        aliases4.add("Stickboy");
        character4.put("aliases", aliases);
        character4.put("ruler", character3.get("characterID"));
        character4_0.put("character",character4);
        //создаю вторую семью
        JSONObject family2 = new JSONObject();
        //массив из персонажей
        JSONArray charactersF2Array = new JSONArray();
        charactersF2Array.add(character3_0);
        charactersF2Array.add(character4_0);
        //добавляю во 2ю семью аттрибуты
        family2.put("idOfTheHouse","2");
        family2.put("nameOfTheHouse","Stark");
        //добавляю во 2ю семью 2х персонажей (массив выше)
        family2.put("characters",charactersF2Array);

        //для каждой семьи создаю отдельный объект
        JSONObject family1_0 = new JSONObject();
        family1_0.put("family",family1);
        JSONObject family2_0 = new JSONObject();
        family2_0.put("family",family2);

        //создаю westerosFamily
        JSONObject westerosFamily = new JSONObject();

        //массив из семей
        JSONArray westerosFamilys = new JSONArray();
        westerosFamilys.add(family1_0);
        westerosFamilys.add(family2_0);
        //добавляю в westerosFamily 2 семьи (массив выше)
        westerosFamily.put("westerosFamily", westerosFamilys);




        try (FileWriter file = new FileWriter("westerosFamilyJSONsimleCreated.json")) {
            file.write(westerosFamily.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(westerosFamily);

    }

}