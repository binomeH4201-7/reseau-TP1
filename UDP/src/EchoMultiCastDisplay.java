/***
 * EchoClient
 * Example of a TCP client 
 * Date: 10/01/04
 * Authors:
 */

import java.io.*;
import java.net.*;

/**
 * Class representing a ClientMulticast Thread
 * Allows to write and receive message at the same time
 *
 * @author BUONOMO Phanie, BATEL Arthur
 */
public class EchoMultiCastDisplay extends Thread {

 
  /**
  *  main method
  *  accepts a connection, receives a message from client then sends an echo to the client
  **/

    MulticastSocket multiCastSocket = null;

    EchoMultiCastDisplay(MulticastSocket multicastSocket) {

        this.multiCastSocket = multicastSocket;   
    }

    public void run(){

      // Build a datagram packet for response
      byte[] buf = new byte[1000];  
      DatagramPacket recv = new  DatagramPacket(buf, buf.length);  

      while(true){     
        try{ 
          multiCastSocket.receive(recv);
          String msg = new String(recv.getData(), 0, recv.getLength()); 
          System.out.println("Response: " + msg);
        } catch (Exception e) {break;}
      }
    }
}


