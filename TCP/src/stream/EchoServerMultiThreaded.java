/*
 EchoServer
 Example of a TCP server
 Date: 10/01/04
 Authors: BUONOMO Phanie - BATEL Arthur
 */


import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EchoServerMultiThreaded  {
  
 	/**
  	* main method
	* @param EchoServer port
  	* 
  	**/

    private static List<ClientThread> connectedThreadList = new ArrayList<>();

    public static void main(String[] args){
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
      }
    }
}

  
