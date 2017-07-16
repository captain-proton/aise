
package de.paluno.calculator.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für randomResponse complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="randomResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="random" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "randomResponse", propOrder = {
    "random"
})
public class RandomResponse {

    protected int random;

    /**
     * Ruft den Wert der random-Eigenschaft ab.
     * 
     */
    public int getRandom() {
        return random;
    }

    /**
     * Legt den Wert der random-Eigenschaft fest.
     * 
     */
    public void setRandom(int value) {
        this.random = value;
    }

}
