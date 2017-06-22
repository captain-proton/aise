package de.hindenbug.dox.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Nils on 17.06.2017.
 */
public interface PersonInterface extends Remote
{
    void setName(String name) throws RemoteException;

    String getName() throws RemoteException;

    void setAge(int age) throws RemoteException;

    int getAge() throws RemoteException;
}