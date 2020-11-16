/***
 * EchoServer
 * Example of a TCP server
 * Date: 10/01/04
 * Authors:
 */


import java.io.*;
import java.net.*;
import java.util.List;
import java.util.ArrayList;

public class EchoServerMultiThreaded  {
  
 	/**
  	* main method
	* @param EchoServer port
  	* 
  	**/

    private static List<ClientThread> connectedThreadList = new ArrayList<ClientThread>();

    public static void main(String args[]){ 
      ServerSocket listenSocket;
            
    	if (args.length != 1) {
            System.out.println("Usage: java EchoServer <EchoServer port>");
            System.exit(1);
    	}
    	try {
    		listenSocket = new ServerSocket(Integer.parseInt(args[0])); //port
    		System.out.println("Server ready..."); 
    		while (true) {
    			Socket clientSocket = listenSocket.accept();
    			System.out.println("Connexion from:" + clientSocket.getInetAddress());
    			ClientThread ct = new ClientThread(clientSocket);
          addConnectedThread(ct);
    			ct.start();
    		}
      } catch (Exception e) {
          System.err.println("Error in EchoServer:" + e);
      }
    }

    private static void addConnectedThread(ClientThread ct){
      connectedThreadList.add(ct);
    }

    public static void removeConnectedThread(ClientThread ct){
      connectedThreadList.remove(ct);
    }

    public static void publishMessage(String msg){
      for(ClientThread ct : connectedThreadList){
        ct.sendMessageToClient(msg);
        System.out.println("Publish Message : "+msg);
      }
    }
}

  
