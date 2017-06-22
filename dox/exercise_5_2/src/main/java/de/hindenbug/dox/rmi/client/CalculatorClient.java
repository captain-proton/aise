package de.hindenbug.dox.rmi.client;

import de.hindenbug.dox.rmi.server.Calculator;
import de.hindenbug.dox.rmi.server.CalculatorServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by nils on 30.05.17.
 */
public class CalculatorClient
{
    private static final Logger LOG = LoggerFactory.getLogger(CalculatorClient.class);

    private final Registry registry;
    private final Calculator server;

    public CalculatorClient() throws RemoteException, NotBoundException
    {
        this.registry = LocateRegistry.getRegistry();
        this.server = (Calculator) registry.lookup(CalculatorServer.NAME);
        LOG.debug("calculator client started");
    }

    public int add(int a, int b) throws RemoteException, NotBoundException
    {
        LOG.info(String.format("add of %d, %d", a, b));
        int result = server.add(a, b);
        LOG.info(String.format("result: %d", result));
        return result;
    }

    public int sqr(int a) throws RemoteException
    {
        LOG.info(String.format("sqr of %d", a));
        int result = server.sqr(a);
        LOG.info(String.format("result: %d", result));
        return result;

    }
}
