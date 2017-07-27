package de.hindenbug.dox.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController
{
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public Integer add(@RequestParam(value = "a", defaultValue = "0") Integer a,
                       @RequestParam(value = "b", defaultValue = "0") Integer b)
    {
        return a + b;
    }

    @RequestMapping(value = "/substract", method = {RequestMethod.GET})
    public Integer substract(@RequestParam(value = "a", defaultValue = "0") Integer a,
                             @RequestParam(value = "b", defaultValue = "0") Integer b)
    {
        return a - b;
    }

    @RequestMapping(value = "/multiply", method = {RequestMethod.GET})
    public Integer multiply(@RequestParam(value = "a", defaultValue = "0") Integer a,
                            @RequestParam(value = "b", defaultValue = "0") Integer b)
    {
        return a * b;
    }

    @RequestMapping(value = "/divide", method = {RequestMethod.GET})
    public Double divide(@RequestParam(value = "a", defaultValue = "0") Double a,
                         @RequestParam(value = "b", defaultValue = "0") Double b) throws ArithmeticException
    {
        return a / b;
    }
}
