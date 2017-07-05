package de.paluno.dox.parser.sax;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import de.paluno.dox.parser.CompactDisc;

public class DoxSaxHandler extends DefaultHandler {

	// Verwenden Sie Bitte diese Liste zum Speichern der CompactDisc-Objeckte
	private List<CompactDisc> catalog = new ArrayList<CompactDisc>();

	// Parsen Sie die XML hier

	// Parsen Sie die XML hier - ENDE

	public List<CompactDisc> getCatalog() {
		return catalog;
	}

	public void setCatalog(List<CompactDisc> catalog) {
		this.catalog = catalog;
	}
}
