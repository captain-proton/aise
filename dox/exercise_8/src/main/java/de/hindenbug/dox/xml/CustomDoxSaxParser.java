package de.hindenbug.dox.xml;

import de.paluno.dox.parser.CompactDisc;
import de.paluno.dox.parser.sax.DoxSaxHandler;
import de.paluno.dox.parser.sax.DoxSaxParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Nils on 27.06.2017.
 */
public class CustomDoxSaxParser extends DoxSaxParser
{
    @Override
    public List<CompactDisc> parse(String filename) throws SAXException, IOException, ParserConfigurationException
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        InputStream xmlInput = new FileInputStream(filename);

        SAXParser saxParser = factory.newSAXParser();
        DoxSaxHandler handler = new CustomDoxSaxHandler();
        saxParser.parse(xmlInput, handler);

        return handler.getCatalog();
    }
}
