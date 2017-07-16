
package de.paluno.calculator.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für substractResponse complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="substractResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="substraction" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "substractResponse", propOrder = {
    "substraction"
})
public class SubstractResponse {

    protected int substraction;

    /**
     * Ruft den Wert der substraction-Eigenschaft ab.
     * 
     */
    public int getSubstraction() {
        return substraction;
    }

    /**
     * Legt den Wert der substraction-Eigenschaft fest.
     * 
     */
    public void setSubstraction(int value) {
        this.substraction = value;
    }

}
