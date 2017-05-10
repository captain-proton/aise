package de.hindenbug.dox.forum;

import java.util.Date;

/**
 * A representation of the model object '<em><b>Post</b></em>'. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class Post {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private String text = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private Date send = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private User author = null;

	/**
	 * Returns the value of '<em><b>text</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>text</b></em>' feature
	 * @generated
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the '{@link Post#getText() <em>text</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newText
	 *            the new value of the '{@link Post#getText() text}' feature.
	 * @generated
	 */
	public void setText(String newText) {
		text = newText;
	}

	/**
	 * Returns the value of '<em><b>send</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>send</b></em>' feature
	 * @generated
	 */
	public Date getSend() {
		return send;
	}

	/**
	 * Sets the '{@link Post#getSend() <em>send</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newSend
	 *            the new value of the '{@link Post#getSend() send}' feature.
	 * @generated
	 */
	public void setSend(Date newSend) {
		send = newSend;
	}

	/**
	 * Returns the value of '<em><b>author</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>author</b></em>' feature
	 * @generated
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * Sets the '{@link Post#getAuthor() <em>author</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newAuthor
	 *            the new value of the '{@link Post#getAuthor() author}'
	 *            feature.
	 * @generated
	 */
	public void setAuthor(User newAuthor) {
		if (author != newAuthor) {
			if (author != null) {
				author.removeFromPosts(this);
			}
			author = newAuthor;
			if (author != null) {
				author.addToPosts(this);
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
		return "Post " + " [text: " + getText() + "]" + " [send: " + getSend() + "]";
	}
}
