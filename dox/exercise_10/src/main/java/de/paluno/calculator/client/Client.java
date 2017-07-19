package de.paluno.calculator.client;

import de.paluno.calculator.gen.CalculationServices;
import de.paluno.calculator.gen.CalculatorServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

public class Client
{

    private static final Logger LOG = LoggerFactory.getLogger(Client.class);

    /**
     * @param args
     * @throws MalformedURLException
     */
    public static void main(String[] args) throws MalformedURLException
    {
        CalculationServices calculator = new CalculatorServerService().getCalculatorServerPort();

        int add = calculator.add(4, 5);
        LOG.info(String.format("%12s: %d, %d = %d", "add", 4, 5, add));

        int product = calculator.multiply(4, 5);
        LOG.info(String.format("%12s: %d, %d = %d", "multiply", 4, 5, product));

        double division = calculator.divide(8, 2);
        LOG.info(String.format("%12s: %d, %d = %f", "division", 8, 2, division));

        int substraction = calculator.substract(0, 1);
        LOG.info(String.format("%12s: %d, %d = %d", "substraction", 0, 1, substraction));

        LOG.info(String.format("%12s: %d", "random", calculator.random()));
    }

}
