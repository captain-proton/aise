package de.paluno.dox.rmiobjects;

import de.hindenbug.dox.rmi.PersonInterface;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client
{

    public static void main(String[] args) throws RemoteException, NotBoundException
    {
        final Registry registry = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
        final Server server = (Server) registry.lookup(Server.BIND_NAME);

        final Person p = new Person("Hans Wurst", 23);

        simpleReceivePerson(server, p);
        interfaceReceivePerson(server, p);
    }

    private static void simpleReceivePerson(Server server, Person p) throws RemoteException
    {
        System.out.println("Before: " + p);
        server.receivePerson(p);
        System.out.println("After: " + p);
    }

    private static void interfaceReceivePerson(Server server, Person p) throws RemoteException
    {
        PersonInterface person = (PersonInterface) UnicastRemoteObject.exportObject(p, 0);
        System.out.println("Before: " + person.getName() + " - " + person.getAge());
        server.updatePerson(person);
        System.out.println("After: " + person.getName() + " - " + person.getAge());
    }

}
