package de.hindenbug.dox.codegenerationexample;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * A representation of the model object '<em><b>LanguageConstruct</b></em>'.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
@Entity(name = "codeGenerationExample_LanguageConstruct")
public abstract class LanguageConstruct {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Basic(optional = false)
	private String name = null;

	/**
	 * Returns the value of '<em><b>name</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>name</b></em>' feature
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the '{@link LanguageConstruct#getName() <em>name</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newName
	 *            the new value of the '{@link LanguageConstruct#getName()
	 *            name}' feature.
	 * @generated
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * A toString method which prints the values of all EAttributes of this
	 * instance. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		return "LanguageConstruct " + " [name: " + getName() + "]";
	}
}
