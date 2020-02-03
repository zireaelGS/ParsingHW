package DashaPentalog.DOM_and_JDOM;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

//Any approach (preferable XPATH) - create methods: check tag presence, check that tag
// contains children (not simple text node, but nested tags), return list of values for specified tag
//Любой подход (предпочтительно XPATH) - создавать методы: проверять наличие тега, проверять, что тег содержит дочерние
//        элементы (не простой текстовый узел, а вложенные теги), возвращать список значений для указанного тега
public class XPathMethods {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
//        //check tag presence
//        existTag("westerosFamilyJDOM.xml", "character");
//        existTag("westerosFamilyJDOM.xml", "title");
//        existTag("westerosFamilyJDOM.xml", "fuydsgf");
//        System.out.println();
//        //check that tag contains children (not simple text node, but nested tags)
//        tagContainsChildren("westerosFamilyJDOM.xml", "firstname");
//        tagContainsChildren("westerosFamilyJDOM.xml", "aliases");
//        tagContainsChildren("westerosFamilyJDOM.xml", "asdas");
//        System.out.println();
//        //return a list of values of childs for specific tag
        returnsChildNodesOfTag("westerosFamilyJDOM.xml", "family");
        returnsChildNodesOfTag("westerosFamilyJDOM.xml", "titles");
//        returnsChildNodesOfTag("westerosFamilyJDOM.xml", "title");
//        returnsChildNodesOfTag("westerosFamilyJDOM.xml", "famfdsfdsily");


    }

    //    method for checking the tag presence
    public static void existTag(String nameOfTheFile, String nameOfTagForCheck) {
        try {
            File inputFile = new File(nameOfTheFile);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;

            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = "//" + nameOfTagForCheck;
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
                    doc, XPathConstants.NODESET);
            if (nodeList.getLength() != 0) {
                System.out.println("The tag \"" + nameOfTagForCheck + "\" is exists ");
            } else {
                System.err.println("The tag \"" + nameOfTagForCheck + "\" doesn't exists ");
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    //    method for checking  that tag contains children (not simple text node, but nested tags)
    public static void tagContainsChildren(String nameOfTheFile, String nameOfTagForCheck) {
        try {
            File inputFile = new File(nameOfTheFile);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;

            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            //икс паф,куда будем подставлять указанный нами тег
            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = "//" + nameOfTagForCheck;
            //список нодов для указанного тега
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
                    doc, XPathConstants.NODESET);
            //проверка на наличие подобного тега в документе
            if (nodeList.getLength() != 0) {
                //проверка есть ли у данного нода дочерние элементы
                if (nodeList.item(0).hasChildNodes() & nodeList.item(0).getNodeType() != Node.TEXT_NODE) {
                    System.out.println("Tag: " + nodeList.item(0).getNodeName() + " does have child nodes.");
                } else {
                    System.out.println("Tag: " + nodeList.item(0).getNodeName() + " doesn't have child nodes.");
                }
            } else {
                System.err.println("Tag: " + nameOfTagForCheck + " doesn't exists ");
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    //method for returning a list of values of childs for specific tag
    public static void returnsChildNodesOfTag(String nameOfTheFile, String nameOfTagForCheck) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        File inputFile = new File(nameOfTheFile);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        dBuilder = dbFactory.newDocumentBuilder();

        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        //икс паф,куда будем подставлять указанный нами тег
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "//" + nameOfTagForCheck;
        System.out.println("tag: " + nameOfTagForCheck);
        while (true) {
            //создаем коллекцию, в котороую будут добавляться дочерние элементы указанного тега
            List list = new LinkedList();
            //список нодов для указанного тега
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
                    doc, XPathConstants.NODESET);
            Element eElement = (Element) nodeList.item(0);
            //проверяем содержит ли данный тег дочерние теги
            if (eElement.hasChildNodes()) {
                //получаем список дочерних тегов
                NodeList children = eElement.getChildNodes();
                for (int j = 0; j < children.getLength(); j++) {
                    Node child = children.item(j);
                    if (child.getNodeType() != Node.TEXT_NODE) {
                        list.add(child.getNodeName());
                    }
                }
                for (Object element : list) {
                    System.out.println(element);
                }
                if (!list.isEmpty()) {
                    expression = "//" + list.get(0);

                    System.out.println("dsadsadasd" + expression);
                }
            } else {
                System.out.println("This tag: " + eElement.getNodeName() + " doesn't contains child elements");
            }
            if (list.contains("ruler")) {
                break;
            }
        }
    }

}







