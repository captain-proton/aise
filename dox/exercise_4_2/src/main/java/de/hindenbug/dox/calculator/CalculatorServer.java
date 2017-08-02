package de.hindenbug.dox.calculator;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Nils on 30.07.2017.
 */
public class CalculatorServer
{
    public static final int PORT = 4711;

    public static void main(String[] args) throws IOException, InterruptedException
    {
        try (ServerSocket server = new ServerSocket(PORT))
        {
            while (true)
            {
                System.out.println("server started");
                Socket socket = server.accept();
                System.out.println("server bound");

                Scanner scanner = new Scanner(socket.getInputStream());
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                String line = scanner.nextLine().trim();
                System.out.println("received line: " + line);
                if (line.matches("add,\\d+,\\d+"))
                {
                    String[] requestArgs = line.split(",");
                    int a = Integer.valueOf(requestArgs[1]);
                    int b = Integer.valueOf(requestArgs[2]);
                    writer.println(a + b);
                } else if (line.matches("sqr,\\d+"))
                {
                    String[] requestArgs = line.split(",");
                    int a = Integer.valueOf(requestArgs[1]);
                    writer.println(a * a);
                } else
                {
                    writer.println("invalid request");
                }
            }
        }
    }
}
