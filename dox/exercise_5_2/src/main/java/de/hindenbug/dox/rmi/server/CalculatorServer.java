package de.hindenbug.dox.rmi.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by nils on 30.05.17.
 */
public class CalculatorServer implements Calculator
{
    public static final String NAME = "dox.rmi.calculator";

    private static final Logger LOG = LoggerFactory.getLogger(CalculatorServer.class);

    private Registry registry;

    public void start() throws RemoteException
    {
        registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        CalculatorServer server = new CalculatorServer();
        Calculator stub = (Calculator) UnicastRemoteObject.exportObject(server, 4711);
        registry.rebind(NAME, stub);
        LOG.info("CalculatorServer bound");
    }

    public void stopServer() throws RemoteException, NotBoundException
    {
        try {
            registry.lookup(NAME);
            registry.unbind(NAME);
        } catch (NotBoundException e)
        {
            LOG.error(NAME + " not bound in rmi registry");
        }
    }

    public static void main(String[] args) throws RemoteException
    {
        if (System.getSecurityManager() == null)
        {
            System.setSecurityManager(new SecurityManager());
        }
        CalculatorServer server = new CalculatorServer();
        server.start();
    }

    @Override
    public int add(int a, int b)
    {
        return a + b;
    }

    @Override
    public int sqr(int a)
    {
        return a * a;
    }
}
