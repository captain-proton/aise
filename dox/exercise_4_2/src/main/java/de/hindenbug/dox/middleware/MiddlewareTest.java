package de.hindenbug.dox.middleware;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by nils on 22.05.17.
 */
public class MiddlewareTest
{
    private static final Logger LOG = LoggerFactory.getLogger(MiddlewareTest.class);
    private static final int TIMEOUT = 10000;

    private final ExecutorService network = Executors.newFixedThreadPool(2);
    private List<CalculatorClient> clients = new ArrayList<>();
    private CalculatorServer server;

    public static void main(String[] args)
    {
        MiddlewareTest middleware = new MiddlewareTest();
        middleware.run();
    }

    private void run()
    {
        // start the server
        try
        {
            server = startServer();
            waitForServerSocket(server);
        } catch (IOException e)
        {
            LOG.error("could not start server", e);
        }

        if (server != null)
        {
            try
            {
                // start the client
                CalculatorClient client = startClient(server);
                communicate(server, client);
            } catch (IOException e)
            {
                LOG.error("could not start client", e);
            }
        }
    }

    private void waitForServerSocket(CalculatorServer server)
    {
        long time = System.currentTimeMillis();
        while (!server.isStarted()
                && time + TIMEOUT > System.currentTimeMillis()) {
            try
            {
                Thread.sleep(100);
            } catch (InterruptedException e)
            {
                LOG.info("interrupted while waiting for server start");
            }
        }
    }

    private void communicate(CalculatorServer server, CalculatorClient client)
    {
        boolean shutdown = false;
        Scanner scanner = new Scanner(System.in);
        while (!shutdown && scanner.hasNext())
        {
            String line = scanner.next();
            client.processInput(line);
            if (StringUtils.equalsIgnoreCase(line, "stop"))
            {
                LOG.info("received shutdown command");
                shutdown = true;

                // close each client
                clients.forEach(CalculatorClient::shutdown);

                // stop the server itself (and associated threads)
                server.shutdown();

                // shutdown the thread pool
                network.shutdown();
            }
        }
    }

    private CalculatorClient startClient(CalculatorServer server) throws IOException
    {
        CalculatorClient client = new CalculatorClient(server);
        network.submit(client);
        clients.add(client);
        return client;
    }

    private CalculatorServer startServer() throws IOException
    {
        String host = "localhost";
        int port = 4711;

        CalculatorServer server = new CalculatorServer(host, port);
        network.submit(server);
        return server;
    }
}
