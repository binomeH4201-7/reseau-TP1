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

public class EchoMultiCast  {
  
 	/**
  	* main method
	* @param EchoServer port
  	* 
  	**/

    public static void main(String args[]){ 

      if (args.length != 2) {
        System.out.println("Usage: java EchoMultiCast <MultiCast adress> <MultiCast port>");
        System.exit(1);
      }

      InetAddress groupAddress = null;
      int groupPort = 0;
      MulticastSocket multiCastSocket = null;

      try{
        //Group id address
        groupAddress = InetAddress.getByName(args[0]);
        groupPort = Integer.parseInt(args[1]);
      }catch (Exception e){
        System.err.println("Error in EchoMultiCast:" + e);    
      }

      try{
        //Create a multicast socket
        multiCastSocket = new MulticastSocket(groupPort);
      }catch (IOException e){
        System.err.println("Error in EchoMultiCast, multicast instanciation:" + e);   
      }

      //Join the group
      try{
        multiCastSocket.joinGroup(groupAddress);
      }catch(IOException e){
        System.err.println("Error in EchoMultiCast, multicast join:" + e);   
      }

      //New thread to display messages
      EchoMultiCastDisplay md = new EchoMultiCastDisplay(multiCastSocket);
      md.start();

      //Canaux de récupération des messages
      BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

      //Récupération des messages et envoi
      String line;
      while (true) {
        try{
          line = stdIn.readLine();
          if (line.equals(".")) break;
          if(!line.isEmpty()){
            DatagramPacket msg = new DatagramPacket(line.getBytes(), line.length(), groupAddress, groupPort);
            multiCastSocket.send(msg);
          }
        }catch (IOException e){
          System.err.println("Error in EchoMultiCast, unable to acquire or sent the message:" + e);
        }
      }
            
      try{
        multiCastSocket.leaveGroup(groupAddress);
      }catch (IOException e){
        System.err.println("Error in EchoMultiCast, unable to leave the group:" + e);
      }
    }
}

  
