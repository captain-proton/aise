package de.paluno.dox.xml.sax;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import de.paluno.dox.xml.xsd.XsdMain;

public class DoxSaxParser {

	static final String JAXP_SCHEMA_LANGUAGE =
	        "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	    
	    static final String W3C_XML_SCHEMA =
	        "http://www.w3.org/2001/XMLSchema";

	    static final String JAXP_SCHEMA_SOURCE =
	        "http://java.sun.com/xml/jaxp/properties/schemaSource";
	
	
	public void parse(String filename, SchemaType type) throws SAXException,
			IOException, ParserConfigurationException {

		SAXParserFactory factory = SAXParserFactory.newInstance();

		factory.setValidating(true);

		SAXParser saxParser = factory.newSAXParser();
		XMLReader xmlReader;
		
		// Validation for DTD
		if (type.equals(SchemaType.DTD)) {
			xmlReader = saxParser.getXMLReader();
			xmlReader.setContentHandler(new DoxSaxHandler());
			xmlReader.setErrorHandler(new DoxErrorHandler());
			xmlReader.parse(convertToFileURL(filename));
		}

		// Validation for XSD
		
		if(type.equals(SchemaType.XSD)) {
			saxParser.setProperty(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
			saxParser.setProperty(JAXP_SCHEMA_SOURCE, new File(XsdMain.SHEMA_SOURCE));
			
			xmlReader = saxParser.getXMLReader();
			xmlReader.setContentHandler(new DoxSaxHandler());
			xmlReader.setErrorHandler(new DoxErrorHandler());
			xmlReader.parse(convertToFileURL(filename));
		}
	}

	private static String convertToFileURL(String filename) {
		String path = new File(filename).getAbsolutePath();
		if (File.separatorChar != '/') {
			path = path.replace(File.separatorChar, '/');
		}

		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		return "file:" + path;
	}

	public static enum SchemaType {
		XSD, DTD
	}
}
