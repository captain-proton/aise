package de.hindenbug.dox.calculator;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Nils on 30.07.2017.
 */
public class CalculatorClient
{
    public int add(int a, int b) throws IOException
    {
        return sendRequest(String.format("add,%d,%d", a, b));
    }

    public int sqr(int a) throws IOException
    {
        return sendRequest(String.format("sqr,%d", a));
    }

    private int sendRequest(String line) throws IOException
    {
        String host = "localhost";
        int port = CalculatorServer.PORT;
        try (Socket server = new Socket(host, port);
             PrintWriter writer = new PrintWriter(server.getOutputStream(), true);
             Scanner reader = new Scanner(server.getInputStream()))
        {
            writer.println(line);
            return reader.nextInt();
        }
    }
}
