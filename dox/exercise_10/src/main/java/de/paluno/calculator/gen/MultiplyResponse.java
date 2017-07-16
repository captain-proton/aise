
package de.paluno.calculator.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für multiplyResponse complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="multiplyResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arithmetic-product" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "multiplyResponse", propOrder = {
    "arithmeticProduct"
})
public class MultiplyResponse {

    @XmlElement(name = "arithmetic-product")
    protected int arithmeticProduct;

    /**
     * Ruft den Wert der arithmeticProduct-Eigenschaft ab.
     * 
     */
    public int getArithmeticProduct() {
        return arithmeticProduct;
    }

    /**
     * Legt den Wert der arithmeticProduct-Eigenschaft fest.
     * 
     */
    public void setArithmeticProduct(int value) {
        this.arithmeticProduct = value;
    }

}
