package de.hindenbug.dox.xml;

import de.paluno.dox.parser.CompactDisc;
import de.paluno.dox.parser.stax.DoxStaxParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nils on 29.06.17.
 */
public class CustomDoxStaxParser extends DoxStaxParser
{
    private static final Logger LOG = LoggerFactory.getLogger(CustomDoxStaxParser.class);
    private List<CompactDisc> catalog;
    private StringBuilder currentText;
    private CompactDisc currentDisc;

    @Override
    public List<CompactDisc> parse(String filename) throws FileNotFoundException, XMLStreamException
    {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader r = factory.createXMLEventReader(filename,
                new FileInputStream(filename));

        catalog = new ArrayList<>();

        while (r.hasNext())
        {
            handleXmlEvent(r.nextEvent());
        }
        return catalog;
    }

    private void handleXmlEvent(XMLEvent xmlEvent)
    {
        final int eventType = xmlEvent.getEventType();
        switch (eventType) {
            case XMLEvent.START_ELEMENT:
                currentText = new StringBuilder();
                handleStartElement(xmlEvent.asStartElement());
                break;
            case XMLEvent.END_ELEMENT:
                handleEndElement(xmlEvent.asEndElement());
                break;
            case XMLEvent.CHARACTERS:
                currentText.append(xmlEvent.asCharacters().getData());
                break;
        }
    }

    private void handleStartElement(StartElement startElement)
    {
        if (startElement.getName().getLocalPart().equals("CD"))
        {
            currentDisc = new CompactDisc();
        }
    }

    private void handleEndElement(EndElement endElement)
    {
        final String name = endElement.getName().getLocalPart();
        CompactDiscHandler.handleEndTag(name, currentText.toString(), currentDisc, catalog);
    }

}
