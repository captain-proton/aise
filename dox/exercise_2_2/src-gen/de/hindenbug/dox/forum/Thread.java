package de.hindenbug.dox.forum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A representation of the model object '<em><b>Thread</b></em>'. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class Thread {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private Forum forum = null;

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
	private boolean isSticky = false;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private List<Post> post = new ArrayList<Post>();

	/**
	 * Returns the value of '<em><b>forum</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>forum</b></em>' feature
	 * @generated
	 */
	public Forum getForum() {
		return forum;
	}

	/**
	 * Sets the '{@link Thread#getForum() <em>forum</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newForum
	 *            the new value of the '{@link Thread#getForum() forum}'
	 *            feature.
	 * @generated
	 */
	public void setForum(Forum newForum) {
		if (forum != newForum) {
			if (forum != null) {
				forum.removeFromThreads(this);
			}
			forum = newForum;
			if (forum != null) {
				forum.addToThreads(this);
			}
		}
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
	 * Sets the '{@link Thread#getName() <em>name</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newName
	 *            the new value of the '{@link Thread#getName() name}' feature.
	 * @generated
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Returns the value of '<em><b>isSticky</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>isSticky</b></em>' feature
	 * @generated
	 */
	public boolean isIsSticky() {
		return isSticky;
	}

	/**
	 * Sets the '{@link Thread#isIsSticky() <em>isSticky</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newIsSticky
	 *            the new value of the '{@link Thread#isIsSticky() isSticky}'
	 *            feature.
	 * @generated
	 */
	public void setIsSticky(boolean newIsSticky) {
		isSticky = newIsSticky;
	}

	/**
	 * Returns the value of '<em><b>post</b></em>' feature. Note: the returned
	 * collection is Unmodifiable use the
	 * {#addToPost(de.hindenbug.dox.forum.Post value)} and
	 * {@link #removeFromPost(Post value)} methods to modify this feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>post</b></em>' feature
	 * @generated
	 */
	public List<Post> getPost() {
		return Collections.unmodifiableList(post);
	}

	/**
	 * Adds to the <em>post</em> feature.
	 *
	 * @param postValue
	 *            the value to add
	 * @return true if the value is added to the collection (it was not yet
	 *         present in the collection), false otherwise
	 * @generated
	 */
	public boolean addToPost(Post postValue) {
		if (!post.contains(postValue)) {
			boolean result = post.add(postValue);
			return result;
		}
		return false;
	}

	/**
	 * Removes from the <em>post</em> feature.
	 *
	 * @param postValue
	 *            the value to remove
	 * @return true if the value is removed from the collection (it existed in
	 *         the collection before removing), false otherwise
	 *
	 * @generated
	 */
	public boolean removeFromPost(Post postValue) {
		if (post.contains(postValue)) {
			boolean result = post.remove(postValue);
			return result;
		}
		return false;
	}

	/**
	 * Clears the <em>post</em> feature.
	 * 
	 * @generated
	 */
	public void clearPost() {
		while (!post.isEmpty()) {
			removeFromPost(post.iterator().next());
		}
	}

	/**
	 * Sets the '{@link Thread#getPost() <em>post</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newPost
	 *            the new value of the '{@link Thread#getPost() post}' feature.
	 * @generated
	 */
	public void setPost(List<Post> newPost) {
		clearPost();
		for (Post value : newPost) {
			addToPost(value);
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
		return "Thread " + " [name: " + getName() + "]" + " [isSticky: " + isIsSticky() + "]";
	}
}
