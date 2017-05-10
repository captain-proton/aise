package de.hindenbug.dox.forum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A representation of the model object '<em><b>User</b></em>'. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class User {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private List<Forum> uses = new ArrayList<Forum>();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private String username = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private String password = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private List<Post> posts = new ArrayList<Post>();

	/**
	 * Returns the value of '<em><b>uses</b></em>' feature. Note: the returned
	 * collection is Unmodifiable use the
	 * {#addToUses(de.hindenbug.dox.forum.Forum value)} and
	 * {@link #removeFromUses(Forum value)} methods to modify this feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>uses</b></em>' feature
	 * @generated
	 */
	public List<Forum> getUses() {
		return Collections.unmodifiableList(uses);
	}

	/**
	 * Adds to the <em>uses</em> feature.
	 *
	 * @param usesValue
	 *            the value to add
	 * @return true if the value is added to the collection (it was not yet
	 *         present in the collection), false otherwise
	 * @generated
	 */
	public boolean addToUses(Forum usesValue) {
		if (!uses.contains(usesValue)) {
			boolean result = uses.add(usesValue);
			return result;
		}
		return false;
	}

	/**
	 * Removes from the <em>uses</em> feature.
	 *
	 * @param usesValue
	 *            the value to remove
	 * @return true if the value is removed from the collection (it existed in
	 *         the collection before removing), false otherwise
	 *
	 * @generated
	 */
	public boolean removeFromUses(Forum usesValue) {
		if (uses.contains(usesValue)) {
			boolean result = uses.remove(usesValue);
			return result;
		}
		return false;
	}

	/**
	 * Clears the <em>uses</em> feature.
	 * 
	 * @generated
	 */
	public void clearUses() {
		while (!uses.isEmpty()) {
			removeFromUses(uses.iterator().next());
		}
	}

	/**
	 * Sets the '{@link User#getUses() <em>uses</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newUses
	 *            the new value of the '{@link User#getUses() uses}' feature.
	 * @generated
	 */
	public void setUses(List<Forum> newUses) {
		clearUses();
		for (Forum value : newUses) {
			addToUses(value);
		}
	}

	/**
	 * Returns the value of '<em><b>username</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>username</b></em>' feature
	 * @generated
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the '{@link User#getUsername() <em>username</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newUsername
	 *            the new value of the '{@link User#getUsername() username}'
	 *            feature.
	 * @generated
	 */
	public void setUsername(String newUsername) {
		username = newUsername;
	}

	/**
	 * Returns the value of '<em><b>password</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>password</b></em>' feature
	 * @generated
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the '{@link User#getPassword() <em>password</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newPassword
	 *            the new value of the '{@link User#getPassword() password}'
	 *            feature.
	 * @generated
	 */
	public void setPassword(String newPassword) {
		password = newPassword;
	}

	/**
	 * Returns the value of '<em><b>posts</b></em>' feature. Note: the returned
	 * collection is Unmodifiable use the
	 * {#addToPosts(de.hindenbug.dox.forum.Post value)} and
	 * {@link #removeFromPosts(Post value)} methods to modify this feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>posts</b></em>' feature
	 * @generated
	 */
	public List<Post> getPosts() {
		return Collections.unmodifiableList(posts);
	}

	/**
	 * Adds to the <em>posts</em> feature.
	 *
	 * @param postsValue
	 *            the value to add
	 * @return true if the value is added to the collection (it was not yet
	 *         present in the collection), false otherwise
	 * @generated
	 */
	public boolean addToPosts(Post postsValue) {
		if (!posts.contains(postsValue)) {
			boolean result = posts.add(postsValue);
			postsValue.setAuthor(this);
			return result;
		}
		return false;
	}

	/**
	 * Removes from the <em>posts</em> feature.
	 *
	 * @param postsValue
	 *            the value to remove
	 * @return true if the value is removed from the collection (it existed in
	 *         the collection before removing), false otherwise
	 *
	 * @generated
	 */
	public boolean removeFromPosts(Post postsValue) {
		if (posts.contains(postsValue)) {
			boolean result = posts.remove(postsValue);
			postsValue.setAuthor(null);
			return result;
		}
		return false;
	}

	/**
	 * Clears the <em>posts</em> feature.
	 * 
	 * @generated
	 */
	public void clearPosts() {
		while (!posts.isEmpty()) {
			removeFromPosts(posts.iterator().next());
		}
	}

	/**
	 * Sets the '{@link User#getPosts() <em>posts</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newPosts
	 *            the new value of the '{@link User#getPosts() posts}' feature.
	 * @generated
	 */
	public void setPosts(List<Post> newPosts) {
		clearPosts();
		for (Post value : newPosts) {
			addToPosts(value);
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
		return "User " + " [username: " + getUsername() + "]" + " [password: " + getPassword() + "]";
	}
}
