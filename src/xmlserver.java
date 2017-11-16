import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
//import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.w3c.*;
import org.w3c.dom.NodeList;
import org.xml.*;


//found most of this code online, adapted for project https://codereview.stackexchange.com/questions/41445/client-sends-xml-server-parses-xml-queues-up-and-executes-commands
// just adapted it for jdom



public class xmlserver {

    /**
     * Server Socket 
    */
    private ServerSocket _serverSocket = null;

    /**
     * Client Socket
     */
    private Socket _clientSocket = null;

    /**
     * List of client commands 
     */
    private ArrayList<Runnable> _workQueue = null; 

    /**
     * Date Time formatter
     */
    private final SimpleDateFormat _dateTimeFormatter = 
            new SimpleDateFormat("YYYY/MM/DD HH:mm:ss");

    /**
     * Singleton Implementation: In the context of this app, only one instance of
     * SocketServerMonitor makes sense
     */
    private static xmlserver _singletonInstance = null;

    /**
     * Hidden constructor prevents instantiation of this type
     */
    private xmlserver() {
    }

    /**
     * Creates, if the singleton instance hasn't been created before and returns 
     * the single instance of SocketServerMonitor 
     * 
     * @return Singleton Instance of SocketServerMonitor
    */
    public static xmlserver getSingleSocketServerMonitor() {
        if (_singletonInstance == null) {
            _singletonInstance = new xmlserver();
        }
        return _singletonInstance;
    }

    /**
     * Starts monitoring the server socket. Blocks until a client connects on
     * this socket
     * @param serverEndPoint Address to which the server socket is bound
     */
    public void start(SocketAddress serverEndPoint) {
        if (_serverSocket != null) {
            stop();
        } else {
            try {
                _serverSocket = new ServerSocket();
                _serverSocket.bind(serverEndPoint);
                System.out.println("Listening for connections at " + serverEndPoint.toString());
                _clientSocket = _serverSocket.accept();
            } catch (IOException ex) {
                System.out.println("Error: Unable to Start Server Socket!\n\t" + ex);
            }
        }
    }

    /**
     * Stop monitoring. Closes server and client sockets
     */
    public void stop() {
        if (_serverSocket != null) {
            try {
                System.out.println("Stopping Server Socket Monitor");
                _serverSocket.close();
                _clientSocket.close();
                _serverSocket = null;
            } catch (IOException ex) {
                System.out.println("Error: Unable to stop server socket monitor! " + ex);
            }
        }
    }

    /**
     * Adds a client command to work queue
     * @param commandNode Node corresponding to XML element Command
     */

    private void addToWorkQueue(final Node commandNode) {
        if (_workQueue == null)
            _workQueue = new ArrayList<>();
        _workQueue.add(new Runnable() {

            @Override
            public void run() {
                System.out.println(((Element) commandNode).getTextContent() + " " + _dateTimeFormatter.format(new Date()));
            }
        });           
    }

    /**
     * Executes queued client commands. The execution is started in a new thread
     * so the callee thread isn't blocked until commands are executed. Each command
     * is executed in a separate thread and the commands are guaranteed to be executed
     * in the order in which they were received by the server
     */
    private void processCommandQueue() {
        if (_workQueue != null) {

            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        for (Runnable work : _workQueue) {
                            Thread workerThread = new Thread(work);
                            workerThread.start();
                            workerThread.join();
                        }
                    } catch (InterruptedException ex){
                        System.out.println("Error: Unable to process commands!\n\t" + ex);
                    } finally {
                        _workQueue.clear();
                    }
                }

            }).start();            
        }
    }   

    /**
     * Read a string from the client that contains a XML document hierarchy
     * @return Document Parsed XML document
     * @throws JDOMException 
     */

    private Document acceptXMLFromClient() throws JDOMException {
        Document xmlDoc = null;
        try {
            BufferedReader clientReader = new BufferedReader(
                    new InputStreamReader(_clientSocket.getInputStream()));
            // block until client sends us a message
            String clientMessage = clientReader.readLine();
            // read xml message
            InputSource is = new InputSource(new StringReader(clientMessage));
           // xmlDoc = (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
            SAXBuilder builder = new SAXBuilder();
            xmlDoc = builder.build(is);
        } catch (IOException ex) {
            // FIXME: put exceptions in log files?
            System.out.println("Error: Unable to read XML from client!\n\t" + ex);
        }
        return xmlDoc;
    }        

    /**
     * Waits on client messages. Attempts to read client messages as a XML document
     * and sends a receipt notice to the client when a message is received. Attempts
     * to read the Command tag from the client and queues up the client command 
     * for execution
     * @throws JDOMException 
     * @throws IOException 
     * @throws InvocationTargetException 
     * @throws InstantiationException 
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     */
    private void listenToClient() throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException, IOException, JDOMException {
        assert _clientSocket != null : "Invalid Client Socket!";
        // open a writer to client socket
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(_clientSocket.getOutputStream(), true);
        } catch (IOException ex) {
            System.out.println("Error: Unable to write to client socket!\n\t" + ex);
        }
        while (true) {
            // Read xml from client
            Document xmlDoc = acceptXMLFromClient();
            if (xmlDoc != null) {
                // Send receipt notice to client  
            	
            	Deserializer deserialize = new Deserializer();
                deserialize.deserialize(xmlDoc, null);
                     }

            
            
           
            processCommandQueue();
        }
    }

    /**
     * @param args the command line arguments
     * @throws JDOMException 
     * @throws IOException 
     * @throws InvocationTargetException 
     * @throws InstantiationException 
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     */
    public static void main(final String[] args) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException, IOException, JDOMException {
        // Make sure command line arguments are valid
       
System.out.println("Starting xml server");
        try {
            getSingleSocketServerMonitor().start(
                    new InetSocketAddress("localhost",3333));
            getSingleSocketServerMonitor().listenToClient();
        } catch (NumberFormatException ex) {
            System.out.println(ex);
        }
    }        
}