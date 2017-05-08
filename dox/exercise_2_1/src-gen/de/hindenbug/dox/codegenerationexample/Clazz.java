package de.hindenbug.dox.codegenerationexample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * A representation of the model object '<em><b>Clazz</b></em>'. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
@Entity(name = "codeGenerationExample_Clazz")
public class Clazz extends LanguageConstruct {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, optional = true)
	private Interface interfaces = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<Clazz> superClazz = new ArrayList<Clazz>();

	/**
	 * Returns the value of '<em><b>interfaces</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>interfaces</b></em>' feature
	 * @generated
	 */
	public Interface getInterfaces() {
		return interfaces;
	}

	/**
	 * Sets the '{@link Clazz#getInterfaces() <em>interfaces</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newInterfaces
	 *            the new value of the '{@link Clazz#getInterfaces()
	 *            interfaces}' feature.
	 * @generated
	 */
	public void setInterfaces(Interface newInterfaces) {
		interfaces = newInterfaces;
	}

	/**
	 * Returns the value of '<em><b>superClazz</b></em>' feature. Note: the
	 * returned collection is Unmodifiable use the
	 * {#addToSuperClazz(de.hindenbug.dox.codegenerationexample.Clazz value)}
	 * and {@link #removeFromSuperClazz(Clazz value)} methods to modify this
	 * feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>superClazz</b></em>' feature
	 * @generated
	 */
	public List<Clazz> getSuperClazz() {
		return Collections.unmodifiableList(superClazz);
	}

	/**
	 * Adds to the <em>superClazz</em> feature.
	 *
	 * @param superClazzValue
	 *            the value to add
	 * @return true if the value is added to the collection (it was not yet
	 *         present in the collection), false otherwise
	 * @generated
	 */
	public boolean addToSuperClazz(Clazz superClazzValue) {
		if (!superClazz.contains(superClazzValue)) {
			boolean result = superClazz.add(superClazzValue);
			return result;
		}
		return false;
	}

	/**
	 * Removes from the <em>superClazz</em> feature.
	 *
	 * @param superClazzValue
	 *            the value to remove
	 * @return true if the value is removed from the collection (it existed in
	 *         the collection before removing), false otherwise
	 *
	 * @generated
	 */
	public boolean removeFromSuperClazz(Clazz superClazzValue) {
		if (superClazz.contains(superClazzValue)) {
			boolean result = superClazz.remove(superClazzValue);
			return result;
		}
		return false;
	}

	/**
	 * Clears the <em>superClazz</em> feature.
	 * 
	 * @generated
	 */
	public void clearSuperClazz() {
		while (!superClazz.isEmpty()) {
			removeFromSuperClazz(superClazz.iterator().next());
		}
	}

	/**
	 * Sets the '{@link Clazz#getSuperClazz() <em>superClazz</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newSuperClazz
	 *            the new value of the '{@link Clazz#getSuperClazz()
	 *            superClazz}' feature.
	 * @generated
	 */
	public void setSuperClazz(List<Clazz> newSuperClazz) {
		clearSuperClazz();
		for (Clazz value : newSuperClazz) {
			addToSuperClazz(value);
		}
	}

	/**
	 * A toString method which prints the values of all EAttributes of this
	 * instance. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		return "Clazz " + "{extends: " + super.toString() + "} ";
	}
}
