package de.hindenbug.dox.xml;

import de.paluno.dox.parser.CompactDisc;

import java.util.Collection;

/**
 * Created by nils on 29.06.17.
 */
public class CompactDiscHandler
{
    public static void handleEndTag(final String tagName, String currentText, CompactDisc disc, Collection<CompactDisc> catalog)
    {
        switch (tagName)
        {
            case "CD":
                catalog.add(disc);
                break;
            case "TITLE":
                disc.setTitle(currentText);
                break;
            case "ARTIST":
                disc.setArtist(currentText);
                break;
            case "COUNTRY":
                disc.setCountry(currentText);
                break;
            case "COMPANY":
                disc.setCompany(currentText);
                break;
            case "PRICE":
                disc.setPrice(Double.parseDouble(currentText));
                break;
            case "YEAR":
                disc.setYear(Integer.parseInt(currentText));
                break;
        }
    }
}
