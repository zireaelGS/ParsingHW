package DashaPentalog.DOM_and_JDOM;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class JDOMadd {

    //задание-добавить новую семью и одного персонажа
    //для этого считываю файл, что уже имелся и создаю новую семью и одного нового члена семьи. присоединяю
    //созданные элементы к рут элементу сущетсвующего файла и записываю новый файл с добавлениями
    public static void main(String[] args) {
        try {
//        Создаю документ из файла или потока
            File inputFile = new File("westerosFamilyJDOM.xml");
//         Создаю экземпляр класса org.jdom.input.SAXBuilder, который умеет строить JDOM-дерево из файлов, потоков, URL и т.д.
            SAXBuilder saxBuilder = new SAXBuilder();
            //Вызоваю метод build() экземпляра SAXBuilder с указанием файла
            Document document = saxBuilder.build(inputFile);
//            Извлекаю рут элемент
            Element rootElement = document.getRootElement();
            System.out.println("Root element : " + rootElement.getName());
            System.out.println("----------------------------");
            // Создаем элемент новой family и добавляем ему атрибут
            Element family = new Element("family");
            family.setAttribute("idOfTheHouse", "3");
            family.setAttribute("nameOfTheHouse", "Lannister");
            //создаем первого персонажа со всеми характеристиками  и сразу добавляем в семью 1
            family.addContent(new Element("character").setAttribute("characterID", "005")
                    .addContent(new Element("firstname").addContent("Tyrion"))
                    .addContent(new Element("age").addContent("39 y.o."))
                    .addContent(new Element("titles")
                            .addContent(new Element("title").addContent("Hand of the King"))
                            .addContent(new Element("title").addContent("Master of Coin"))
                            .addContent(new Element("title").addContent("Lord of Casterly Rock"))
                            .addContent(new Element("title").addContent("Hand of the Queen")))
                    .addContent(new Element("aliases")
                            .addContent(new Element("alias").addContent("The Imp"))
                            .addContent(new Element("alias").addContent("The Halfman"))
                            .addContent(new Element("alias").addContent("Yollo"))
                            .addContent(new Element("alias").addContent("Hugor Hill")))
                    .addContent(new Element("ruler").addContent("Hah, he is on his own mind, so he is his own ruler")));
            rootElement.addContent(family);
            // Делаем "красивый" формат вывода XML
            Format fmt = Format.getPrettyFormat();
            // Выводим созданный XML как поток байт на стандартный вывод и в файл, используя подготовленный формат
            XMLOutputter serializer = new XMLOutputter(fmt);
            serializer.output(document, System.out);
            serializer.output(document, new FileOutputStream(new File("westerosFamilyJDOMadded.xml")));
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
    }
}
