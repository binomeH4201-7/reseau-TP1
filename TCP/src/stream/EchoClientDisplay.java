/***
 * EchoClient
 * Example of a TCP client 
 * Date: 10/01/04
 * Authors:
 */

import java.io.*;
import java.net.*;


public class EchoClientDisplay extends Thread {

  BufferedReader socIn = null;
 
  /**
  *  main method
  *  accepts a connection, receives a message from client then sends an echo to the client
  **/
    EchoClientDisplay(Socket echoSocket) {

        try {
	           socIn = new BufferedReader(
	    		           new InputStreamReader(echoSocket.getInputStream()));    
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection");
            System.exit(1);
        }
        
    }

    public void run(){

      while(true){     
        try{ 
              System.out.println("Message from chat : " + socIn.readLine());
        } catch (Exception e) {break;}
      }

      try{
        socIn.close();
      } catch (Exception e){}
    }
}


