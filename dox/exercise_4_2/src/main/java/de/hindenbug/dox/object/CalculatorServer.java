package de.hindenbug.dox.object;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Nils on 30.07.2017.
 */
public class CalculatorServer
{
    public static final int PORT = 4711;

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        try (ServerSocket server = new ServerSocket(PORT))
        {
            while (true)
            {
                System.out.println("server started");
                Socket socket = server.accept();
                System.out.println("server bound");

                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

                Point point = (Point) input.readObject();
                Integer x = (Integer) input.readObject();
                Integer y = (Integer) input.readObject();

                point.setLocation(point.getX() + x, point.getY() + y);

                output.writeObject(point);
            }
        }
    }
}
