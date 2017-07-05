package de.paluno.dox.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DoxSaxHandler extends DefaultHandler {

	private String currentInd = "";
	private final String indInc = "   ";

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		String fullTag = "<" + qName;

		int attrLength = attributes.getLength();
		for (int i = 0; i < attrLength; i++) {
			fullTag = fullTag + " " + attributes.getQName(i) + "=\""
					+ attributes.getValue(i) + "\"";
		}
		System.out.println(currentInd + fullTag + ">");
		currentInd = currentInd + indInc;
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		currentInd = currentInd.replaceFirst(indInc, "");
		System.out.println(currentInd + "</" + qName + ">");
	}

	public void characters(char ch[], int start, int length)
			throws SAXException {
		String value = new String(ch, start, length).trim();
		if (value.length() == 0)
			return; // ignore white space

		System.out.println(currentInd + value);
	}
}
