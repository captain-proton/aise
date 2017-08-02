package de.hindenbug.dox.calculator;

import java.io.IOException;

/**
 * Created by Nils on 30.07.2017.
 */
public class CalculatorApplication
{
    public static void main(String[] args) throws IOException
    {
        CalculatorClient client = new CalculatorClient();
        System.out.println(client.add(2,3));
        System.out.println(client.sqr(5));
    }
}
