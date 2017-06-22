package de.paluno.dox.rmiobjects;

import de.hindenbug.dox.rmi.PersonInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {
	void receivePerson(Person p) throws RemoteException;
	void updatePerson(PersonInterface p) throws RemoteException;

	public static final String BIND_NAME = "Server";
}
