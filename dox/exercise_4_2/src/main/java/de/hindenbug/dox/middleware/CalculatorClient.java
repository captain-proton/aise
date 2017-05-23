package de.hindenbug.dox.middleware;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by nils on 22.05.17.
 */
public class CalculatorClient implements Runnable
{
    private static final Logger LOG = LoggerFactory.getLogger(CalculatorClient.class);

    private final CalculatorServer server;
    private Socket serverSocket;
    private Queue<String> lines;
    private boolean stop;

    public CalculatorClient(CalculatorServer server)
    {
        this.server = server;
        this.lines = new LinkedBlockingQueue<>();
    }

    public void processInput(String value)
    {
        this.lines.offer(value);
    }

    public void shutdown()
    {
        LOG.info("shut down command received");
        this.stop = true;
    }

    @Override
    public void run()
    {
        LOG.info("client started, waiting to connect");
        try (Socket serverSocket = new Socket(server.getHost(), server.getPort());
             PrintWriter output = new PrintWriter(serverSocket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(serverSocket.getInputStream())))
        {
            LOG.info("connected to server " + server);
            this.serverSocket = serverSocket;
            while (!stop
                    && !Thread.currentThread().isInterrupted()
                    && !serverSocket.isClosed())
            {
                String line;
                while ((line = lines.poll()) != null
                        && !stop)
                {
                    // write to server
                    output.println(line);
                    // read response
                    String response = input.readLine();
                    // output response
                    LOG.info(response);

                    if (StringUtils.equalsIgnoreCase(line, "stop"))
                    {
                        stop = true;
                        LOG.info("stop received");
                    }
                }
                try
                {
                    Thread.sleep(100);
                } catch (InterruptedException e)
                {
                    stop = true;
                    LOG.warn("calculator client interrupted -> shutting down");
                }
            }
            LOG.info("stopped/interrupted -> shutting down");
        } catch (IOException e)
        {
            LOG.error("could not read line from input", e);
        }
        LOG.info("stopped");
    }
}
