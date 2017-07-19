package de.paluno.calculator.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "CalculationServices")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Calculator
{

    @WebMethod(operationName = "add")
    @WebResult(name = "addition")
    int add(@WebParam(name = "a") int a, @WebParam(name = "b") int b);

    @WebMethod(operationName = "substract")
    @WebResult(name = "substraction")
    int sub(@WebParam(name = "a") int a, @WebParam(name = "b") int b);

    @WebMethod(operationName = "multiply")
    @WebResult(name = "arithmetic-product")
    int mul(@WebParam(name = "a") int a, @WebParam(name = "b") int b);

    @WebMethod(operationName = "divide")
    @WebResult(name = "division")
    int div(@WebParam(name = "a") int a, @WebParam(name = "b") int b) throws ArithmeticException;

    @WebMethod(operationName = "random")
    @WebResult(name = "random")
    int random();
}
