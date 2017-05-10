package de.hindenbug.dox.forum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A representation of the model object '<em><b>Forum</b></em>'. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class Forum {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private Forum parentForum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private String name = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private List<Thread> threads = new ArrayList<Thread>();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private Moderator moderator = null;

	/**
	 * Returns the value of '<em><b>parentForum</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>parentForum</b></em>' feature
	 * @generated
	 */
	public Forum getParentForum() {
		return parentForum;
	}

	/**
	 * Sets the '{@link Forum#getParentForum() <em>parentForum</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newParentForum
	 *            the new value of the '{@link Forum#getParentForum()
	 *            parentForum}' feature.
	 * @generated
	 */
	public void setParentForum(Forum newParentForum) {
		parentForum = newParentForum;
	}

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
	 * Sets the '{@link Forum#getName() <em>name</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newName
	 *            the new value of the '{@link Forum#getName() name}' feature.
	 * @generated
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Returns the value of '<em><b>threads</b></em>' feature. Note: the
	 * returned collection is Unmodifiable use the
	 * {#addToThreads(de.hindenbug.dox.forum.Thread value)} and
	 * {@link #removeFromThreads(Thread value)} methods to modify this feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>threads</b></em>' feature
	 * @generated
	 */
	public List<Thread> getThreads() {
		return Collections.unmodifiableList(threads);
	}

	/**
	 * Adds to the <em>threads</em> feature.
	 *
	 * @param threadsValue
	 *            the value to add
	 * @return true if the value is added to the collection (it was not yet
	 *         present in the collection), false otherwise
	 * @generated
	 */
	public boolean addToThreads(Thread threadsValue) {
		if (!threads.contains(threadsValue)) {
			boolean result = threads.add(threadsValue);
			threadsValue.setForum(this);
			return result;
		}
		return false;
	}

	/**
	 * Removes from the <em>threads</em> feature.
	 *
	 * @param threadsValue
	 *            the value to remove
	 * @return true if the value is removed from the collection (it existed in
	 *         the collection before removing), false otherwise
	 *
	 * @generated
	 */
	public boolean removeFromThreads(Thread threadsValue) {
		if (threads.contains(threadsValue)) {
			boolean result = threads.remove(threadsValue);
			threadsValue.setForum(null);
			return result;
		}
		return false;
	}

	/**
	 * Clears the <em>threads</em> feature.
	 * 
	 * @generated
	 */
	public void clearThreads() {
		while (!threads.isEmpty()) {
			removeFromThreads(threads.iterator().next());
		}
	}

	/**
	 * Sets the '{@link Forum#getThreads() <em>threads</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newThreads
	 *            the new value of the '{@link Forum#getThreads() threads}'
	 *            feature.
	 * @generated
	 */
	public void setThreads(List<Thread> newThreads) {
		clearThreads();
		for (Thread value : newThreads) {
			addToThreads(value);
		}
	}

	/**
	 * Returns the value of '<em><b>moderator</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>moderator</b></em>' feature
	 * @generated
	 */
	public Moderator getModerator() {
		return moderator;
	}

	/**
	 * Sets the '{@link Forum#getModerator() <em>moderator</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newModerator
	 *            the new value of the '{@link Forum#getModerator() moderator}'
	 *            feature.
	 * @generated
	 */
	public void setModerator(Moderator newModerator) {
		if (moderator != newModerator) {
			if (moderator != null) {
				moderator.removeFromModerates(this);
			}
			moderator = newModerator;
			if (moderator != null) {
				moderator.addToModerates(this);
			}
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
		return "Forum " + " [name: " + getName() + "]";
	}
}
