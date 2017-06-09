package de.hindenbug.dox.calculator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Nils Verheyen
 * @since 23.05.17 08:44
 */
public class ClientHandler implements Runnable
{
    private static final Logger LOG = LoggerFactory.getLogger(ClientHandler.class);
    private final PrintWriter output;
    private final BufferedReader input;
    private boolean stop;

    ClientHandler(Socket clientSocket) throws IOException
    {
        this.output = new PrintWriter(clientSocket.getOutputStream(), true);
        this.input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void shutdown()
    {
        LOG.info("shut down command received");
        this.stop = true;
    }

    @Override
    public void run()
    {
        LOG.info("ready, waiting for input");
        String inputLine;
        String outputLine;
        CalculatorProtocol protocol = new CalculatorProtocol();

        while (!stop && !Thread.currentThread().isInterrupted())
        {
            try
            {
                inputLine = input.readLine();
                if (StringUtils.equalsIgnoreCase(inputLine, "stop"))
                {
                    stop = true;
                    LOG.info("stop read -> shutting down");
                    output.println("bye");
                } else
                {
                    LOG.info(inputLine);
                    outputLine = protocol.processInput(inputLine);
                    output.println(outputLine);
                }
            } catch (IOException e)
            {
                LOG.error("could not read from client", e);
            }
            try
            {
                Thread.sleep(200);
            } catch (InterruptedException e)
            {
                stop = true;
                LOG.warn("calculator handler interrupted -> shutting down");
            }
        }
        LOG.info("stopped");
    }
}
