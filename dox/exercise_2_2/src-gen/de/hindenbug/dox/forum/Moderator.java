package de.hindenbug.dox.forum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A representation of the model object '<em><b>Moderator</b></em>'. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class Moderator extends User {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private List<Forum> moderates = new ArrayList<Forum>();

	/**
	 * Returns the value of '<em><b>moderates</b></em>' feature. Note: the
	 * returned collection is Unmodifiable use the
	 * {#addToModerates(de.hindenbug.dox.forum.Forum value)} and
	 * {@link #removeFromModerates(Forum value)} methods to modify this feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>moderates</b></em>' feature
	 * @generated
	 */
	public List<Forum> getModerates() {
		return Collections.unmodifiableList(moderates);
	}

	/**
	 * Adds to the <em>moderates</em> feature.
	 *
	 * @param moderatesValue
	 *            the value to add
	 * @return true if the value is added to the collection (it was not yet
	 *         present in the collection), false otherwise
	 * @generated
	 */
	public boolean addToModerates(Forum moderatesValue) {
		if (!moderates.contains(moderatesValue)) {
			boolean result = moderates.add(moderatesValue);
			moderatesValue.setModerator(this);
			return result;
		}
		return false;
	}

	/**
	 * Removes from the <em>moderates</em> feature.
	 *
	 * @param moderatesValue
	 *            the value to remove
	 * @return true if the value is removed from the collection (it existed in
	 *         the collection before removing), false otherwise
	 *
	 * @generated
	 */
	public boolean removeFromModerates(Forum moderatesValue) {
		if (moderates.contains(moderatesValue)) {
			boolean result = moderates.remove(moderatesValue);
			moderatesValue.setModerator(null);
			return result;
		}
		return false;
	}

	/**
	 * Clears the <em>moderates</em> feature.
	 * 
	 * @generated
	 */
	public void clearModerates() {
		while (!moderates.isEmpty()) {
			removeFromModerates(moderates.iterator().next());
		}
	}

	/**
	 * Sets the '{@link Moderator#getModerates() <em>moderates</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newModerates
	 *            the new value of the '{@link Moderator#getModerates()
	 *            moderates}' feature.
	 * @generated
	 */
	public void setModerates(List<Forum> newModerates) {
		clearModerates();
		for (Forum value : newModerates) {
			addToModerates(value);
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
		return "Moderator " + "{extends: " + super.toString() + "} ";
	}
}
