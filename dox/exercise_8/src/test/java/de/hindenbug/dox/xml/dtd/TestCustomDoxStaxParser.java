package de.hindenbug.dox.xml.dtd;

import de.hindenbug.dox.xml.CustomDoxStaxParser;
import de.paluno.dox.parser.CompactDisc;
import de.paluno.dox.parser.stax.DoxStaxParser;

import java.util.List;

/**
 * Created by nils on 29.06.17.
 */
public class TestCustomDoxStaxParser extends TestCustomDoxSaxParser
{
    protected List<CompactDisc> loadCatalog() throws Exception
    {
        DoxStaxParser parser = new CustomDoxStaxParser();
        String path = "./src/main/java/de/paluno/dox/parser/cd_catalog.xml";
        List<CompactDisc> catalog = parser.parse(path);
        return catalog;
    }
}
