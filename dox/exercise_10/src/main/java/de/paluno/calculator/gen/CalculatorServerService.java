
package de.paluno.calculator.gen;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "CalculatorServerService", targetNamespace = "http://server.calculator.paluno.de/", wsdlLocation = "http://localhost:9876/Calculator?wsdl")
public class CalculatorServerService
    extends Service
{

    private final static URL CALCULATORSERVERSERVICE_WSDL_LOCATION;
    private final static WebServiceException CALCULATORSERVERSERVICE_EXCEPTION;
    private final static QName CALCULATORSERVERSERVICE_QNAME = new QName("http://server.calculator.paluno.de/", "CalculatorServerService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:9876/Calculator?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CALCULATORSERVERSERVICE_WSDL_LOCATION = url;
        CALCULATORSERVERSERVICE_EXCEPTION = e;
    }

    public CalculatorServerService() {
        super(__getWsdlLocation(), CALCULATORSERVERSERVICE_QNAME);
    }

    public CalculatorServerService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CALCULATORSERVERSERVICE_QNAME, features);
    }

    public CalculatorServerService(URL wsdlLocation) {
        super(wsdlLocation, CALCULATORSERVERSERVICE_QNAME);
    }

    public CalculatorServerService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CALCULATORSERVERSERVICE_QNAME, features);
    }

    public CalculatorServerService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CalculatorServerService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns CalculationServices
     */
    @WebEndpoint(name = "CalculatorServerPort")
    public CalculationServices getCalculatorServerPort() {
        return super.getPort(new QName("http://server.calculator.paluno.de/", "CalculatorServerPort"), CalculationServices.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CalculationServices
     */
    @WebEndpoint(name = "CalculatorServerPort")
    public CalculationServices getCalculatorServerPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server.calculator.paluno.de/", "CalculatorServerPort"), CalculationServices.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CALCULATORSERVERSERVICE_EXCEPTION!= null) {
            throw CALCULATORSERVERSERVICE_EXCEPTION;
        }
        return CALCULATORSERVERSERVICE_WSDL_LOCATION;
    }

}