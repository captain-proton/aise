package de.paluno.calculator.server;


import org.apache.commons.lang3.RandomUtils;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import javax.xml.ws.Endpoint;

@WebService(endpointInterface = "de.paluno.calculator.server.Calculator")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CalculatorServer implements Calculator
{
    private Endpoint endpoint;

    public CalculatorServer()
    {
        endpoint = Endpoint.publish("http://localhost:9876/Calculator", this);
        System.out.println("Server started.");
    }

    public static void main(String[] args)
    {
        CalculatorServer server = new CalculatorServer();
        JOptionPane.showMessageDialog(null, "Server beenden");
        server.endpoint.stop();
        System.out.println("Server stopped.");
    }

    @Override
    public int add(@WebParam(name = "a") int a, @WebParam(name = "b") int b)
    {
        return a + b;
    }

    @Override
    public int sub(@WebParam(name = "a") int a, @WebParam(name = "b") int b)
    {
        return a - b;
    }

    @Override
    public int mul(@WebParam(name = "a") int a, @WebParam(name = "b") int b)
    {
        return a * b;
    }

    @Override
    public int div(@WebParam(name = "a") int a, @WebParam(name = "b") int b)
    {
        return a / b;
    }

    @Override
    public int random()
    {
        return RandomUtils.nextInt();
    }
}
