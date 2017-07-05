package de.paluno.dox.parser.sax;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import de.paluno.dox.parser.CompactDisc;

public class DoxSaxParser {

	public List<CompactDisc> parse(String filename) throws SAXException,
			IOException, ParserConfigurationException {

		SAXParserFactory factory = SAXParserFactory.newInstance();

		InputStream xmlInput = new FileInputStream(filename);

		SAXParser saxParser = factory.newSAXParser();
		DoxSaxHandler handler = new DoxSaxHandler();
		saxParser.parse(xmlInput, handler);

		return handler.getCatalog();
	}
}
