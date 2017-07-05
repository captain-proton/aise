package de.paluno.dox.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.SAXException;

import de.paluno.dox.parser.sax.DoxSaxParser;
import de.paluno.dox.parser.stax.DoxStaxParser;

public class Main {

	public static void main(String[] args) {

		String filename = "./src/de/paluno/dox/parser/cd_catalog.xml";

		// StAX Parser
		System.out.println("Starte StAX.Parser: ");
		DoxStaxParser staxParser = new DoxStaxParser();
		
		try {
			List<CompactDisc> catalog = staxParser.parse(filename);
			System.out.println("StAX-Parser Ergebnis: ");
			System.out.println(catalog);
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}
		
		// SAX Parser
		System.out.println("\n\n\n----------------------------------------------------\n");
		System.out.println("Starte SAX.Parser: ");
		DoxSaxParser saxParser = new DoxSaxParser();
		try {
			List<CompactDisc> catalog = saxParser.parse(filename);
			System.out.println("SAX-Parser Ergebnis: ");
			System.out.println(catalog);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		
	}

}
