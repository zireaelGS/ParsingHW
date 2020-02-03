package DashaPentalog.DOM_and_JDOM;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class JDOMRecursiveQueryRuler {
    public static void main(String[] args) {
//Задание-рекурсивный запрос всех правителей(ruler)
        try {
//           Создаю документ из файла или потока
            File inputFile = new File("westerosFamilyJDOM.xml");
//           Создаю экземпляр класса org.jdom.input.SAXBuilder, который умеет строить JDOM-дерево из файлов, потоков, URL и т.д.
            SAXBuilder saxBuilder = new SAXBuilder();
//            Вызываю метод build() экземпляра SAXBuilder с указанием файла
            Document document = saxBuilder.build(inputFile);
//            Извлекаю рут элемент
            Element rootElement = document.getRootElement();
            System.out.println("Root element : " + rootElement.getName() + "\n");
//            Применяю метод,который рекурсивно читает файл
            listChildren(rootElement);
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
    }

    //в данном методе с помощью метода getChildren(),получаю список List всех дочерних экземпляров
// корневого Element, а затем для каждого дочернего элемента так же вызваю getChildren() и т.д.
    public static void listChildren(Element element) {
        //т.к. по заданию, нам нужно не просто вывести все элементы на экран, а вывести только правителей, произвожу
        //проверку на условия,что если это элемент "character" и при этом дочерний элемент этого элемента с названием "Ruler"
        //имеет значение,которое не содержит цифр(т.к. в xml документе в элементе "Ruler" цифры содержаться только у тех,кто
        //не является правителем и ссылается на id своего правителя,оторое состоит из цифр). Если это условие выполнено, то
        // остаются только те, кто является правителем и далее мы выводим на экран значение атрибутта персонажа, который подходит
        // под условия(а атрибутом в данном случае является ID персонажа)
        if (element.getName().equals("character") && element.getChild("ruler").getValue().matches("[^0-9]*")) {
            System.out.println(element.getAttribute("characterID").getValue());
        }
        List children = element.getChildren();
        Iterator iterator = children.iterator();
        while (iterator.hasNext()) {
            Element child = (Element) iterator.next();
            listChildren(child);
        }
    }
}

//еще один вариант рекурсии(в main добавить //            ReadXmlRecursively(rootElement.getChildren().iterator());)
//    private static void ReadXmlRecursively(Iterator<Element> list) {
//        Element element = null;
//        while (list.hasNext()) {
//            element = list.next();
//            {
//                if (element.getName().equals("character") && element.getChild("Ruler").getValue().matches("[^0-9]*")) {
//                    System.out.println(element.getAttribute("characterID").getValue());
//                }
//                ReadXmlRecursively(element.getChildren().iterator());
//            }
//        }
//    }
//}

//тут лежит рабочая версия,но не рекурсивная
//    //    Создаем документ из файла или потока
//File inputFile = new File("westerosFamilyJDOM.xml");
//    //           Создаю DocumentBuilder
//    SAXBuilder saxBuilder = new SAXBuilder();
//    Document document = saxBuilder.build(inputFile);
//    //            Извлекаю рут элемент
//    Element rootElement = document.getRootElement();
//            System.out.println("Root element : " + rootElement.getName());
//
//                    //получаю список существующих семей из рут элемента
//                    List<Element> familyList = rootElement.getChildren();
//        System.out.println("Rulers IDs and names are: ");
//        for (Element family : familyList) {
//        List<Element> characters = family.getChildren();
//        for (Element character : characters) {
//        if (!character.getChild("Ruler").getText().matches("[0-9]*")) {
//        System.out.println(character.getAttribute("characterID").getValue());
//        }
//        }
//        }