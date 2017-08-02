package de.hindenbug.dox.object;

import java.awt.*;
import java.io.IOException;

/**
 * Created by Nils on 30.07.2017.
 */
public class CalculatorApplication
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        CalculatorClient client = new CalculatorClient();
        System.out.println(client.move(new Point(4, 5), 2, -1));
    }
}
