package de.hindenbug.dox.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by nils on 30.05.17.
 */
public interface Calculator extends Remote
{
    int add(int a, int b) throws RemoteException;

    int sqr(int a) throws RemoteException;
}
