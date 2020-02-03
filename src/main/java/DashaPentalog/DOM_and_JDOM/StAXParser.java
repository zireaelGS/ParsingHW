package DashaPentalog.DOM_and_JDOM;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;


public class StAXParser {
    public static void main(String[] args) {
        boolean bFirstname = false;
        boolean bAge = false;
        boolean bTitles = false;
        boolean bTitle = false;
        boolean bAliases = false;
        boolean bAlias = false;
        boolean bRuler = false;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(new FileReader("westerosFamilyJDOM.xml"));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {

                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();

                        if (qName.equalsIgnoreCase("family")) {
                            System.out.println("Start Element : family");
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            String idOfTheHouse = attributes.next().getValue();
                            System.out.println("ID Of The House: " + idOfTheHouse);
                            String nameOfTheHouse = attributes.next().getValue();
                            System.out.println("Name Of The House : " + nameOfTheHouse);
                        } else if (qName.equalsIgnoreCase("character")) {
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            String idOfTheHouse = attributes.next().getValue();
                            System.out.println("ID of the character: " + idOfTheHouse);
                        } else if (qName.equalsIgnoreCase("Firstname")) {
                            bFirstname = true;
                        } else if (qName.equalsIgnoreCase("Age")) {
                            bAge = true;
                        } else if (qName.equalsIgnoreCase("Titles")) {
                            bTitles = true;
                        } else if (qName.equalsIgnoreCase("Title")) {
                            bTitle = true;
                        } else if (qName.equalsIgnoreCase("Aliases")) {
                            bAliases = true;
                        } else if (qName.equalsIgnoreCase("Alias")) {
                            bAlias = true;
                        } else if (qName.equalsIgnoreCase("Ruler")) {
                            bRuler = true;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if (bFirstname) {
                            System.out.println("First Name: " + characters.getData());
                            bFirstname = false;
                        }
                        if (bAge) {
                            System.out.println("Age: " + characters.getData());
                            bAge = false;
                        }
                        if (bTitles) {
                            System.out.println("Titles: " + characters.getData());
                            bTitles = false;
                        }
                        if (bTitle) {
                            System.out.println("Title: " + characters.getData());
                            bTitle = false;
                        }
                        if (bAliases) {
                            System.out.println("Aliases: " + characters.getData());
                            bAliases = false;
                        }
                        if (bAlias) {
                            System.out.println("Alias: " + characters.getData());
                            bAlias = false;
                        }
                        if (bRuler) {
                            System.out.println("Ruler: " + characters.getData());
                            bRuler = false;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();

                        if (endElement.getName().getLocalPart().equalsIgnoreCase("family")) {
                            System.out.println("End Element : family");
                            System.out.println();
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
