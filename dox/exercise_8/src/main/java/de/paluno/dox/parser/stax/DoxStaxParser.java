package de.paluno.dox.parser.stax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import de.paluno.dox.parser.CompactDisc;

public class DoxStaxParser {

	public List<CompactDisc> parse(String filename)
			throws FileNotFoundException, XMLStreamException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLEventReader r = factory.createXMLEventReader(filename,
				new FileInputStream(filename));

		// Verwenden Sie Bitte diese Liste zum Speichern der
		// CompactDisc-Objeckte
		List<CompactDisc> catalog = new ArrayList<CompactDisc>();

		// Parsen Sie die XML hier

		// Parsen Sie die XML hier - ENDE

		return catalog;
	}
}
