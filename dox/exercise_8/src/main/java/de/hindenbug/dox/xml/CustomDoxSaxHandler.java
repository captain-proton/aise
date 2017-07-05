package de.hindenbug.dox.xml;

import de.paluno.dox.parser.CompactDisc;
import de.paluno.dox.parser.sax.DoxSaxHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nils on 27.06.2017.
 */
public class CustomDoxSaxHandler extends DoxSaxHandler
{
    private List<CompactDisc> catalog;

    private CompactDisc currentDisc;

    private StringBuilder currentText;

    @Override
    public void startDocument() throws SAXException
    {
        catalog = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException
    {
        setCatalog(catalog);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        currentText = new StringBuilder();
        switch (qName)
        {
            case "CD":
                currentDisc = new CompactDisc();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {

        CompactDiscHandler.handleEndTag(qName, currentText.toString(), currentDisc, catalog);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        currentText.append(new String(ch, start, length));
    }
}
