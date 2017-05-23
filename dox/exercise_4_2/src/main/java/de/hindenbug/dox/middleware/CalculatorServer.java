package de.hindenbug.dox.middleware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by nils on 22.05.17.
 */
public class CalculatorServer implements Runnable
{
    private static final Logger LOG = LoggerFactory.getLogger(CalculatorServer.class);

    private final ExecutorService handler;
    private final List<ClientHandler> handlerSubmissions;

    private final String host;
    private final int port;

    private boolean started;
    private boolean stop;
    private ServerSocket serverSocket;

    public CalculatorServer(String host, int port)
    {
        this.host = host;
        this.port = port;
        this.handler = Executors.newCachedThreadPool();
        this.handlerSubmissions = new ArrayList<>();
    }

    public void shutdown()
    {
        LOG.info("shut down command received");
        this.stop = true;
        try
        {
            this.serverSocket.close();
        } catch (IOException e)
        {
            LOG.error("could not close server socket", e);
        }
    }

    @Override
    public void run()
    {
        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            LOG.info("server started on port " + port);
            this.serverSocket = serverSocket;
            this.started = true;

            while (!stop
                    && !Thread.currentThread().isInterrupted()
                    && serverSocket.isBound())
            {
                // accept a connection
                LOG.info("accepting client");
                try
                {
                    // wait until a new client connects
                    Socket clientSocket = serverSocket.accept();
                    ClientHandler handler = new ClientHandler(clientSocket);

                    LOG.info("client connected");
                    // start handler thread
                    this.handler.submit(handler);
                    this.handlerSubmissions.add(handler);
                } catch (IOException e)
                {
                    LOG.error("an i/o error occured when waiting for a connection. " + e.getMessage());
                }
            }
        } catch (IOException e)
        {
            LOG.error("server could not be started", e);
        } finally
        {
            this.handlerSubmissions.forEach(ClientHandler::shutdown);
            this.handler.shutdown();
            this.started = false;
        }
        LOG.info("stopped");
    }

    public String getHost()
    {
        return host;
    }

    public int getPort()
    {
        return port;
    }

    public boolean isStarted()
    {
        return started;
    }

    @Override
    public String toString()
    {
        return "CalculatorServer{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}
