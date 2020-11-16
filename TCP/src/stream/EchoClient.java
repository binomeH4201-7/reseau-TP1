/***
 * EchoClient
 * Example of a TCP client 
 * Date: 10/01/04
 * Authors:
 */

import java.io.*;
import java.net.*;


public class EchoClient {

 
  /**
  *  main method
  *  accepts a connection, receives a message from client then sends an echo to the client
  **/
    public static void main(String[] args) throws IOException {

        Socket echoSocket = null;
        PrintStream socOut = null;
        BufferedReader stdIn = null;
        EchoClientDisplay clientDisplay = null;

        if (args.length != 2) {
          System.out.println("Usage: java EchoClient <EchoServer host> <EchoServer port>");
          System.exit(1);
        }

        try {
      	    // creation socket ==> connexion
      	    echoSocket = new Socket(args[0],new Integer(args[1]).intValue()); 

            //thread d'affichage des retours
      	    EchoClientDisplay echoClientDisplay = new EchoClientDisplay(echoSocket);
      	    echoClientDisplay.start();

            //Canaux de récupréation des messages et d'envoi
    		    socOut= new PrintStream(echoSocket.getOutputStream());
    		    stdIn = new BufferedReader(new InputStreamReader(System.in));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host:" + args[0]);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to:"+ args[0]);
            System.exit(1);
        }
                             
        String line;
        while (true) {
        	line = stdIn.readLine();
        	if (line.equals(".")) break;
        	socOut.println(line);
        }
      socOut.close();
      stdIn.close();
      echoSocket.close();
    }
}

