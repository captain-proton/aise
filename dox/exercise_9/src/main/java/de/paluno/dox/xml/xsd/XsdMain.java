package de.paluno.dox.xml.xsd;

import de.paluno.dox.xml.sax.DoxSaxParser;
import de.paluno.dox.xml.sax.DoxSaxParser.SchemaType;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XsdMain
{

    public static final String FILE_NAME = "./src/main/java/de/paluno/dox/xml/xsd/example.xml";
    public static final String SHEMA_SOURCE = "./src/main/java/de/paluno/dox/xml/xsd/schema.xsd";

    public static void main(String[] args)
    {
        System.out.println("Parsing gestartet (XSD)\n\n");
        DoxSaxParser saxParser = new DoxSaxParser();
        try
        {
            saxParser.parse(FILE_NAME, SchemaType.XSD);
            System.out.println("\n\nParsing beendet (XSD)");
        } catch (SAXException | IOException | ParserConfigurationException e)
        {
            e.printStackTrace();
        }

    }

}
