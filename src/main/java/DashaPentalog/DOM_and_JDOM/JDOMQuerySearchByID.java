package DashaPentalog.DOM_and_JDOM;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JDOMQuerySearchByID {
    //Задание-Запрос любого персонажа

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
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
            //получаю список существующих дочерних элементов из рут элемента
            List<Element> familyList = rootElement.getChildren();
            System.out.println("----------------------------");

            //a)Запрос любого персонажа
            //список, в который в будущем будут сохраняться ID всех персонажей, для дальнейшей проверки
            List<String> IDs = new ArrayList<>();

            System.out.println("This is a list with the id of all characters:");
            //в цикле, беру каждую семью по очереди
            for (int i = 0; i < familyList.size(); i++) {
                Element family = familyList.get(i);
                //разбиваю эту семью на членов семьи
                List<Element> character = family.getChildren();
                //для каждого члена семьи выделяю ID и вывожу его на экран,чтобы пользователь знал, какие персонажи
                // и с каким ID существуют
                for (Element oneCharacter : character) {
                    System.out.println(" - " + oneCharacter.getAttribute("characterID").getValue());
                    //сохраняем ID в список для проверки
                    IDs.add(oneCharacter.getAttribute("characterID").getValue());
                }
            }
            //предлагаю польователю ввести ID персонажа, о котором он хочет узнать больше или ввести 0,чтобы выйти
            System.out.println("Please, enter the ID of the character you would like to know more about or press 0 to exit: ");
            String ID = input.nextLine();
            if (ID.equals("0")) {
                System.exit(0);
            }
            //проверяю на правильность ввода,если введен персонаж,отсутсвующий в списке выше,то выводим сообщение об ошибке
            else if (!IDs.contains(ID)) {
                do {
                    System.err.println("This character doesn't exists");
                    //предлагаю либо выйти из программы, либо попробовать ввести ID снова
                    System.out.println("You can try to enter the ID of the character again or " +
                            "exit from the program(for exit enter 0): ");
                    ID = input.nextLine();
                    if (ID.equals("0")) {
                        System.exit(0);
                    }
                    //и так пока пользватель не введет правильный вариант или не введет 0
                } while (!IDs.contains(ID) | ID.equals("0"));
            }
            //вывод всей информации по конкретному персонажу
            for (int i = 0; i < familyList.size(); i++) {
                Element family = familyList.get(i);
                List<Element> character = family.getChildren();
                for (Element oneCharacter : character) {
                    if (oneCharacter.getAttribute("characterID").getValue().equals(ID)) {
                        System.out.println("Firstname : " + oneCharacter.getChild("firstname").getText());
                        System.out.println("Age : " + oneCharacter.getChild("age").getText());
                        System.out.println("Titles : ");
                        List<Element> titlesList = oneCharacter.getChild("titles").getChildren();
                        for (Element title : titlesList) {
                            System.out.println("  Title : " + title.getText());
                        }
                        System.out.println("Aliases : ");
                        List<Element> aliasesList = oneCharacter.getChild("aliases").getChildren();
                        for (Element alias : aliasesList) {
                            System.out.println("  Alias : " + alias.getText());
                        }
                        System.out.println("Ruler : " + oneCharacter.getChild("ruler").getText() + "\n");
                    }
                }
            }
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
    }
}
