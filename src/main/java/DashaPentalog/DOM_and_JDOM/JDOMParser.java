package DashaPentalog.DOM_and_JDOM;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JDOMParser {
    public static void main(String[] args) {

        try {
//           Создаю документ из файла или потока
            File inputFile = new File("westerosFamilyJDOM.xml");
//         Создаю экземпляр класса org.jdom.input.SAXBuilder, который умеет строить JDOM-дерево из файлов, потоков, URL и т.д.
            SAXBuilder saxBuilder = new SAXBuilder();
//           Вызоваю метод build() экземпляра SAXBuilder с указанием файла
            Document document = saxBuilder.build(inputFile);
//            Извлекаю рут элемент
            Element rootElement = document.getRootElement();
            System.out.println("Root element : " + rootElement.getName());
//           Получаю  список всех дочерних элементов рут элемента
            List<Element> elementList = rootElement.getChildren();
            System.out.println("----------------------------");
//          извлекаю семьи с их атрибутами
            for (int index = 0; index < elementList.size(); index++) {
                Element family = elementList.get(index);
                System.out.println("\nCurrent Element :"
                        + family.getName());
                Attribute attribute = family.getAttribute("idOfTheHouse");
                System.out.println("ID Of The House : "
                        + attribute.getValue());
                Attribute attribute2 = family.getAttribute("nameOfTheHouse");
                System.out.println("Name Of The House : "
                        + attribute2.getValue() + "\n");
                //извлекаю  персонажей с их атрибутом(ID) и со всей информацией о персонаже
                List<Element> characterList = family.getChildren();
                for (int index2 = 0; index2 < characterList.size(); index2++) {
                    Element character = characterList.get(index2);
                    Attribute attributeCharacter = character.getAttribute("characterID");
                    System.out.println("Character ID : "
                            + attributeCharacter.getValue());
                    //вывод всей информации по конкретному персонажу
                    System.out.println("Firstname : " + character.getChild("firstname").getText());
                    System.out.println("Age : " + character.getChild("age").getText());
                    System.out.println("Titles : ");
                    List<Element> titlesList = character.getChild("titles").getChildren();
                    for (Element title : titlesList) {
                        System.out.println("  Title : " + title.getText());
                    }
                    System.out.println("Aliases : ");
                    List<Element> aliasesList = character.getChild("aliases").getChildren();
                    for (Element alias : aliasesList) {
                        System.out.println("  Alias : " + alias.getText());
                    }
                    System.out.println("Ruler : " + character.getChild("ruler").getText() + "\n");
                }
            }
        } catch (JDOMException |
                IOException e) {
            e.printStackTrace();
        }
    }
}

