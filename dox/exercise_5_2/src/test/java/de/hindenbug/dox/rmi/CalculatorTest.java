package de.hindenbug.dox.rmi;

import de.hindenbug.dox.rmi.client.CalculatorClient;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by nils on 30.05.17.
 */
public class CalculatorTest extends Assert
{
    private CalculatorClient client;

    @BeforeClass
    public void setup() throws RemoteException, NotBoundException
    {
        client = new CalculatorClient();
    }

    @Test
    public void testCalculatorServerAdd() throws RemoteException, NotBoundException
    {
        Assert.assertEquals(client.add(1, 2), 3);
        Assert.assertEquals(client.add(1, -1), 0);
    }

    @Test
    public void testCalculatorServerSqr() throws RemoteException, NotBoundException
    {
        Assert.assertEquals(client.sqr(5), 25);
        Assert.assertEquals(client.sqr(0), 0);
        Assert.assertEquals(client.sqr(-3), 9);
    }
}
