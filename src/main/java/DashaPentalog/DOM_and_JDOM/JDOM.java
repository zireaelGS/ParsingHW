package DashaPentalog.DOM_and_JDOM;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class JDOM {
    public static void main(String[] args) {

        // Создаем документ
        Document xmlDoc = new Document();
        // Создаем корневой элемент
        Element root = new Element("westerosFamily");
        // Добавляем корневой элемент в документ
        xmlDoc.setRootElement(root);

        // Создаем элемент первой family и добавляем ему атрибут
        Element family1 = new Element("family");
        family1.setAttribute("idOfTheHouse", "1");
        family1.setAttribute("nameOfTheHouse", "Targaryen");
        //создаем первого персонажа со всеми характеристиками  и сразу добавляем в семью 1
        family1.addContent(new Element("character").setAttribute("characterID", "001")
                .addContent(new Element("firstname").addContent("Daenerys"))
                .addContent(new Element("age").addContent("22 y.o."))
                .addContent(new Element("titles")
                        .addContent(new Element("title").addContent("Queen of Meereen"))
                        .addContent(new Element("title").addContent("Mother of Dragons"))
                        .addContent(new Element("title").addContent("Khaleesi of the Great Grass Sea"))
                        .addContent(new Element("title").addContent("The Unburnt"))
                        .addContent(new Element("title").addContent("Breaker of Chains")))
                .addContent(new Element("aliases")
                        .addContent(new Element("alias").addContent("Dragonmother"))
                        .addContent(new Element("alias").addContent("Dani")))
                .addContent(new Element("ruler").addContent("This is the head of the kingdom")));
        //создаем второго персонажа со всеми характеристиками и добавляем в семью 1
        family1.addContent(new Element("character").setAttribute("characterID", "002")
                .addContent(new Element("firstname").addContent("Rhaegar"))
                .addContent(new Element("age").addContent("38 y.o."))
                .addContent(new Element("titles")
                        .addContent(new Element("title").addContent("Last Dragon"))
                        .addContent(new Element("title").addContent("Prince dragon")))
                .addContent(new Element("aliases")
                        .addContent(new Element("alias").addContent("Silver prince")))
                .addContent(new Element("ruler")
                        .addContent(family1.getChild("character").getAttributeValue("characterID", "001"))));

        // Создаем элемент второй family и добавляем ему атрибут
        Element family2 = new Element("family");
        family2.setAttribute("idOfTheHouse", "2");
        family2.setAttribute("nameOfTheHouse", "Stark");
        //создаем первого персонажа со всеми характеристиками  и сразу добавляем в семью 2
        family2.addContent(new Element("character").setAttribute("characterID", "003")
                .addContent(new Element("firstname").addContent("John"))
                .addContent(new Element("age").addContent("22 y.o."))
                .addContent(new Element("titles")
                        .addContent(new Element("title").addContent("King in the North"))
                        .addContent(new Element("title").addContent("Warden of the North")))
                .addContent(new Element("aliases")
                        .addContent(new Element("alias").addContent("Lord Snow"))
                        .addContent(new Element("alias").addContent("The Bastard of Winterfell")))
                .addContent(new Element("ruler").addContent("It is a head of the north")));
        //создаем второго персонажа со всеми характеристиками и добавляем в семью 2
        family2.addContent(new Element("character").setAttribute("characterID", "004")
                .addContent(new Element("firstname").addContent("Arya"))
                .addContent(new Element("age").addContent("17 y.o."))
                .addContent(new Element("titles")
                        .addContent(new Element("title").addContent("Princess")))
                .addContent(new Element("aliases")
                        .addContent(new Element("alias").addContent("Faceless"))
                        .addContent(new Element("alias").addContent("Arry"))
                        .addContent(new Element("alias").addContent("Stickboy")))
                .addContent(new Element("ruler")
                        .addContent(family2.getChild("character").getAttributeValue("characterID", "003"))));
        //добавляем элементы family1 и family2 в root элемент westerosFamily
        root.addContent(family1);
        root.addContent(family2);
        try {
            // Делаем "красивый" формат вывода XML
            Format fmt = Format.getPrettyFormat();
            // Выводим созданный XML как поток байт на стандартный вывод и в файл, используя подготовленный формат
            XMLOutputter serializer = new XMLOutputter(fmt);
            serializer.output(xmlDoc, System.out);
            serializer.output(xmlDoc, new FileOutputStream(new File("westerosFamilyJDOMr.xml")));
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}
