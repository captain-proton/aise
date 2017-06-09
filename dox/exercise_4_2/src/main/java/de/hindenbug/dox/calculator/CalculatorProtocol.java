package de.hindenbug.dox.calculator;

import java.util.Scanner;

/**
 * Created by nils on 22.05.17.
 */
public class CalculatorProtocol
{
    public String processInput(String inputLine)
    {
        Scanner scanner = new Scanner(inputLine).useDelimiter(",");
        String result = "invalid request";
        if (scanner.hasNext())
        {
            String method = scanner.next().toLowerCase();
            switch (method)
            {
                case "sqr":
                    result = evalSquare(scanner);
                    break;
                case "add":
                    result = evalAdd(scanner);
                    break;
            }
        }
        scanner.close();
        return result;
    }

    private String evalSquare(Scanner scanner)
    {
        int p = scanner.hasNextInt()
                ? scanner.nextInt()
                : 0;
        return Integer.toString(p * p);
    }

    private String evalAdd(Scanner scanner)
    {
        int p = scanner.hasNextInt()
                ? scanner.nextInt()
                : 0;
        int q = scanner.hasNextInt()
                ? scanner.nextInt()
                : 0;
        return Long.toString(p + q);
    }
}
