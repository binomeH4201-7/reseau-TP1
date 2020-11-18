/***
 * ClientThread
 * Example of a TCP server
 * Date: 14/12/08
 * Authors:
 */


import java.io.*;
import java.net.*;
import java.io.InputStream;

public class ClientThread
extends Thread {

  private Socket clientSocket;
  private PrintStream socOut;
  private Chatroom currentChat;

  ClientThread(Socket s) {
    this.clientSocket = s;
    this.currentChat = null;
  }

  /**
   * receives a request from client then sends an echo to the client
   * @param clientSocket the client socket
   **/
  public void run() {
    try {
      BufferedReader socIn = null;
      socIn = new BufferedReader(
          new InputStreamReader(clientSocket.getInputStream()));    
      socOut = new PrintStream(clientSocket.getOutputStream());
      while (true) {
        String line = socIn.readLine();
        //récuperer le tag du message : JOIN LEAVE CREATE POST
        //token delimiteur : $
        String[] sublines = line.split("$",2);
        String tag = sublines[0];
        String post= sublines[1];
        String[] subpost = post.split("$",3);
        String date = subpost[0];
        String pseudo = subpost[1];
        String chatroom = "";
        if(subpost.length == 3) chatroom = subpost[2];
        if(currentChat != null){
          switch(tag){
            case "LEAVE":
              EchoServerMultiThreaded.leaveChatroom(this,currentChat);
              EchoServerMultiThreaded.publishMessage(date+" : "+pseudo+" has left.",currentChat);
              currentChat = null;
              break;
            case "CREATE":
              EchoServerMultiThreaded.createChatroom(post);
              currentChat = EchoServerMultiThreaded.joinChatroom(this,chatroom);
              EchoServerMultiThreaded.publishMessage(date+" : "+pseudo+ "has created the chatroom "+chatroom,currentChat);
              break;
            case "POST":
              EchoServerMultiThreaded.publishMessage(date+" : <"+pseudo+">  "+chatroom,currentChat);
              break;
            case "JOIN":
              EchoServerMultiThreaded.leaveChatroom(this,currentChat);
              currentChat = EchoServerMultiThreaded.joinChatroom(this,post);
              EchoServerMultiThreaded.publishMessage(date+" : "+pseudo+" has joined the room.",currentChat);
              break;
          }
        }
        else
        {
          switch(tag){
            case "CREATE":
              EchoServerMultiThreaded.createChatroom(post);
              currentChat = EchoServerMultiThreaded.joinChatroom(this,post);
              EchoServerMultiThreaded.publishMessage(date+" : "+pseudo+ "has created the chatroom "+chatroom,currentChat);
              break;
            case "JOIN":
              EchoServerMultiThreaded.leaveChatroom(this,currentChat);
              currentChat = EchoServerMultiThreaded.joinChatroom(this,post);
              EchoServerMultiThreaded.publishMessage(date+" : "+pseudo+" has joined the room.",currentChat);
              break;
            default:
              socOut.println("You must join a chatroom first");
          }
        }
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
