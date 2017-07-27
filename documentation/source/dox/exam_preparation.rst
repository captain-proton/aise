********************
Prüfungsvorbereitung
********************

Beispielcode
============

Sockets
-------

Server
^^^^^^

.. code-block:: java

    try (ServerSocket serverSocket = new ServerSocket(port))
    {
        while (serverSocket.isBound())
        {
            // hier wird geblockt
            Socket clientSocket = serverSocket.accept();
            ClientHandler handler = new ClientHandler(clientSocket);
        }
    }

Der ``ClientHandler`` verwendet intern noch ein Protokoll, was den Input parst und ein Ergebnis berechnet.

Client
^^^^^^

.. code-block:: java

    try (Socket serverSocket = new Socket(server.getHost(), server.getPort());
         PrintWriter output = new PrintWriter(serverSocket.getOutputStream(), true);
         BufferedReader input = new BufferedReader(new InputStreamReader(serverSocket.getInputStream())))
    {
        // einlesen der resourcen
        // ...
        // uebertragen der resourcen an den server
        output.println(line);
        // ergebnis auslesen
        String response = input.readLine();
    }

RMI
---

Interface
^^^^^^^^^
.. code-block:: java

    public interface Calculator extends Remote
    {
        // die exception muss von jeder Methode geworfen werden
        int add(int a, int b) throws RemoteException;
    }

Server
^^^^^^

.. code-block:: java

    public class CalculatorServer implements Calculator
    {
        private static final String NAME = "Calculator";
        public void start() throws RemoteException
        {
            // 1099
            registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            // server objekt erstellen
            CalculatorServer server = new CalculatorServer();
            // exportieren, damit es bereit gemacht wird um entfernte aufrufe entgegen zu nehmen
            Calculator stub = (Calculator) UnicastRemoteObject.exportObject(server, 4711);
            // server an die registry binden
            registry.rebind(NAME, stub);
        }
    }

Client
^^^^^^

.. code-block:: java

    try {
        Registry registry = LocateRegistry.getRegistry();
        Calculator server = (Calculator) registry.lookup(CalculatorServer.NAME);
        // aufruf der methoden
        System.out.println(server.add(1, 2));
    } catch (RemoteException) {}

Wenn Objekte übertragen werden und ggfs. geändert werden muss die Klasse dieser Objekte das ``Remote``-Interface und ``Serializable`` implementieren. Wenn das Objekt im Client und Server geändert werden soll muss dieses über ``UnicastRemoteObject.exportObject`` exportiert werden, da ansonsten Änderungen die im Server vorgenommen werden nicht an den Client übertragen werden.

IDL
---

.. literalinclude:: exam_preparation/sample.idl
    :language: idl

SAX
---

Parser
^^^^^^

.. code-block:: java

    SAXParserFactory factory = SAXParserFactory.newInstance();

    InputStream xmlInput = new FileInputStream(filename);

    SAXParser saxParser = factory.newSAXParser();
    DoxSaxHandler handler = new DoxSaxHandler();
    saxParser.parse(xmlInput, handler);

Handler
^^^^^^^

.. code-block:: java

    public class DoxSaxHandler extends DefaultHandler
    {
        public void startDocument() throws SAXException {}
        public void endDocument() throws SAXException {}
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {}
        public void endElement(String uri, String localName, String qName) throws SAXException {}
        public void characters(char[] ch, int start, int length) throws SAXException {}
    }

Die Methoden müssen entsprechend mit Logik gefüllt werden.

StaX
----

.. code-block:: java

    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLEventReader r = factory.createXMLEventReader(filename, new FileInputStream(filename));

    List<?> arr = new ArrayList<>();

    while (r.hasNext())
    {
        final int eventType = xmlEvent.getEventType();
        switch (eventType) {
            case XMLEvent.START_ELEMENT:
                currentText = new StringBuilder();
                handleStartElement(xmlEvent.asStartElement());
                break;
            case XMLEvent.END_ELEMENT:
                handleEndElement(xmlEvent.asEndElement());
                break;
            case XMLEvent.CHARACTERS:
                currentText.append(xmlEvent.asCharacters().getData());
                break;
        }
    }

DTD
---

.. literalinclude:: ../nsdbms/exam_preparation/cheatsheets/dtd_cheatsheet.dtd
    :language: dtd

XML-Schema
----------

.. literalinclude:: ../nsdbms/exam_preparation/cheatsheets/xsd_cheatsheet.xml
    :language: xml

SOAP-Webservice
---------------

Interface
^^^^^^^^^

.. code-block:: java

    /*
    Das SOAPBinding ist optional und dient nur der Stilisierung
    des Services. Bei RPC muessen die Parameter auch genau in der
    Reihenfolge angegeben sein, wie sie in der Methode definiert sind.
    */
    @WebService(name = "CalculationServices")
    @SOAPBinding(style = SOAPBinding.Style.RPC)
    public interface Calculator
    {
        /*
        die annotationen fuer die methode sind optional. der name
        der operation wird nicht! in die wsdl uebertragen. es konnte
        kurzfristig nicht ermittelt werden wozu dieser ueberhaupt dient.
        */
        @WebMethod(operationName = "add")
        @WebResult(name = "addition")
        int add(@WebParam(name = "a") int a, @WebParam(name = "b") int b);
    }

Server
^^^^^^

.. code-block:: java

    /*
    Die Annotationen muessen dieselben sein, wie im Interface bereits
    angegeben.
    */
    @WebService(endpointInterface = "de.paluno.calculator.server.Calculator")
    @SOAPBinding(style = SOAPBinding.Style.RPC)
    public class CalculatorServer implements Calculator
    {
        public CalculatorServer()
        {
            Endpoint endpoint = Endpoint.publish("http://localhost:9876/Calculator", this);
        }
    }

Generierung der Services
^^^^^^^^^^^^^^^^^^^^^^^^

.. code-block:: shell

    wsimport -s src/main/java -p de.paluno.calculator.gen http://localhost:9876/Calculator?wsdl

Es werden zwei Klassen generiert:

- ``CalculatorServerService``
- ``CalculatorServices``

Die ServerService Klasse verfügt über Methoden den WebService zu adressieren. Die Services Klasse enthält den eigentlichen WebService, über den auch die definierten Methoden aufgerufen werden können.

Client
^^^^^^

.. code-block:: java

    CalculationServices calculator = new CalculatorServerService().getCalculatorServerPort();
    calculator.add(2, 3);
    // usw.
