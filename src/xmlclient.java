import java.io.*;
import java.net.*;


public class xmlclient {
//found most of this code online, https://codereview.stackexchange.com/questions/41445/client-sends-xml-server-parses-xml-queues-up-and-executes-commands
	public xmlclient(String filename, int port) throws IOException
	{
	

     // Wait for the server to accept connection before reading the xml file 
     BufferedReader reader = new BufferedReader( new FileReader ( filename));
     String line;
     StringBuilder  stringBuilder = new StringBuilder();
     while((line = reader.readLine() ) != null) {
         stringBuilder.append(line);
         System.out.println(line);
     }

     // Send xml data to server 
     Socket socket = new Socket("localhost", port);
     PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
     writer.println(stringBuilder.toString());
     
     
     
         BufferedReader serverReader = new BufferedReader(
                 new InputStreamReader(socket.getInputStream()));

         String serverResponse = null;
         while ((serverResponse = serverReader.readLine()) != null) {
             System.out.println("Server Response: " + serverResponse);
     
     
}
}
}