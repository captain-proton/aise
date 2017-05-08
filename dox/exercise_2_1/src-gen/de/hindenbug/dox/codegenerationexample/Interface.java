package de.hindenbug.dox.codegenerationexample;

import javax.persistence.Entity;

/**
 * A representation of the model object '<em><b>Interface</b></em>'. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
@Entity(name = "codeGenerationExample_Interface")
public class Interface extends LanguageConstruct {

	/**
	 * A toString method which prints the values of all EAttributes of this
	 * instance. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		return "Interface " + "{extends: " + super.toString() + "} ";
	}
}
