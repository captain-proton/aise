package de.paluno.dox.rmiobjects;

import de.hindenbug.dox.rmi.PersonInterface;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MyServer implements Server
{

    @Override
    public void receivePerson(final Person p) throws RemoteException
    {
        p.setName("Paulchen Panther");
        p.setAge(52);
    }

    @Override
    public void updatePerson(PersonInterface p) throws RemoteException
    {
        p.setName("Paulchen Panther");
        p.setAge(52);
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException
    {
        final Server server = new MyServer();
        final Registry registry = LocateRegistry
                .createRegistry(Registry.REGISTRY_PORT);
        UnicastRemoteObject.exportObject(server, 0);
        registry.bind(BIND_NAME, server);
    }

}
