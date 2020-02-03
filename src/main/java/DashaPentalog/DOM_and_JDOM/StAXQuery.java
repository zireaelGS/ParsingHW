package DashaPentalog.DOM_and_JDOM;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

public class StAXQuery {
    public static void main(String[] args) {
        boolean bFirstname = false;
        boolean bAge = false;
        boolean bTitles = false;
        boolean bTitle = false;
        boolean bAliases = false;
        boolean bAlias = false;
        boolean bRuler = false;
        boolean isRequestCharacterID = false;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(new FileReader("westerosFamilyJDOM.xml"));

            String requestedCharacterID = "001";

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();

                        if (qName.equalsIgnoreCase("character")) {
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            String charID = attributes.next().getValue();

                            if (charID.equalsIgnoreCase(requestedCharacterID)) {
                                System.out.println("Start Element : character");
                                System.out.println("CharacterID : " + charID);
                                isRequestCharacterID = true;
                            }
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

                        if (bFirstname && isRequestCharacterID) {
                            System.out.println("Firstname:" + characters.getData());
                            bFirstname = false;
                        }
                        if (bAge && isRequestCharacterID) {
                            System.out.println("Age:" + characters.getData());
                            bAge = false;
                        }
                        if (bTitles && isRequestCharacterID) {
                            System.out.println("Titles:" + characters.getData());
                            bTitles = false;
                        }
                        if (bTitle && isRequestCharacterID) {
                            System.out.println("Title:" + characters.getData());
                            bTitle = false;
                        }
                        if (bAliases && isRequestCharacterID) {
                            System.out.println("Aliases:" + characters.getData());
                            bAliases = false;
                        }
                        if (bAlias && isRequestCharacterID) {
                            System.out.println("Alias:" + characters.getData());
                            bAlias = false;
                        }
                        if (bRuler && isRequestCharacterID) {
                            System.out.println("Ruler:" + characters.getData());
                            bRuler = false;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();

                        if (endElement.getName().getLocalPart().equalsIgnoreCase(
                                "character") && isRequestCharacterID) {
                            System.out.println("End Element : character");
                            System.out.println();
                            isRequestCharacterID = false;
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
