/*
 ClientThread
 Example of a TCP server
 Date: 14/12/08
 Authors:
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientThread
	extends Thread {
	
	private Socket clientSocket;
  	private PrintStream socOut;
	
	ClientThread(Socket s) {
		this.clientSocket = s;
	}

 	/**
  	* receives a request from client then sends an echo to the client
	 **/
	public void run() {
    	  try {
    		BufferedReader socIn;
    		socIn = new BufferedReader(
    			new InputStreamReader(clientSocket.getInputStream()));    
    		socOut = new PrintStream(clientSocket.getOutputStream());
      		while (true) {
      		  String line = socIn.readLine();
            EchoServerMultiThreaded.publishMessage(line);
      		}
      	} catch (Exception e) {
          	System.err.println("Error in EchoServer:" + e); 
            EchoServerMultiThreaded.removeConnectedThread(this);
        }
  	}

	public void sendMessageToClient(String msg){
    socOut.println(msg);
  }
  
}

  
