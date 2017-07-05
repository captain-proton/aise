package de.hindenbug.dox.xml.dtd;

import de.hindenbug.dox.xml.CustomDoxSaxParser;
import de.paluno.dox.parser.CompactDisc;
import de.paluno.dox.parser.sax.DoxSaxParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by Nils on 27.06.2017.
 */
public class TestCustomDoxSaxParser extends Assert
{
    protected List<CompactDisc> loadCatalog() throws Exception
    {
        DoxSaxParser parser = new CustomDoxSaxParser();
        String path = "./src/main/java/de/paluno/dox/parser/cd_catalog.xml";
        List<CompactDisc> catalog = parser.parse(path);
        return catalog;
    }

    @Test
    public void testXmlParsing() throws Exception
    {
        List<CompactDisc> catalog = loadCatalog();
        Assert.assertNotEquals(catalog.size(), 0);
    }

    @Test(dependsOnMethods = "testXmlParsing")
    public void testString() throws Exception
    {
        List<CompactDisc> catalog = loadCatalog();
        Assert.assertTrue(getDisc(catalog, "Big Willie style", "Will Smith").isPresent());
        Assert.assertFalse(getDisc(catalog, "Big Willie style ", "Will Smith").isPresent());
    }

    @Test(dependsOnMethods = "testXmlParsing")
    public void testDouble() throws Exception
    {
        List<CompactDisc> catalog = loadCatalog();
        Assert.assertEquals(getDisc(catalog, "The very best of", "Cat Stevens").get().getPrice(), 8.9);
    }

    @Test(dependsOnMethods = "testXmlParsing")
    public void testInteger() throws Exception
    {
        List<CompactDisc> catalog = loadCatalog();
        Assert.assertEquals(getDisc(catalog, "Stop", "Sam Brown").get().getYear(), 1988);
    }

    private Optional<CompactDisc> getDisc(Collection<CompactDisc> catalog, String title, String artist)
    {
        return catalog.stream()
                .filter(cd -> cd.getTitle().equals(title) && cd.getArtist().equals(artist))
                .findAny();
    }
}
