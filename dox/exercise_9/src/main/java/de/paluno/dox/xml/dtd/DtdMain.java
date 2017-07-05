package de.paluno.dox.xml.dtd;

import de.paluno.dox.xml.sax.DoxSaxParser;
import de.paluno.dox.xml.sax.DoxSaxParser.SchemaType;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DtdMain
{

    public static final String FILE_NAME = "./src/main/java/de/paluno/dox/xml/dtd/example.xml";

    public static void main(String[] args)
    {
        System.out.println("Parsing gestartet (DTD)\n\n");
        DoxSaxParser saxParser = new DoxSaxParser();
        try
        {
            saxParser.parse(FILE_NAME, SchemaType.DTD);
            System.out.println("\n\nParsing beendet (DTD)");
        } catch (SAXException | IOException | ParserConfigurationException e)
        {
            e.printStackTrace();
        }

    }
}
