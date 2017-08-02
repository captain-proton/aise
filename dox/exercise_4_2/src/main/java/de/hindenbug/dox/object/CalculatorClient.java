package de.hindenbug.dox.object;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Nils on 30.07.2017.
 */
public class CalculatorClient
{
    public Point move(Point point, int x, int y) throws IOException, ClassNotFoundException
    {
        String host = "localhost";
        int port = CalculatorServer.PORT;
        try (Socket server = new Socket(host, port);
             ObjectOutputStream output = new ObjectOutputStream(server.getOutputStream());
             ObjectInputStream input = new ObjectInputStream(server.getInputStream())
        )
        {
            output.writeObject(point);
            output.writeObject(x);
            output.writeObject(y);
            output.flush();

            Point moved = (Point) input.readObject();
            return moved;
        }
    }
}
