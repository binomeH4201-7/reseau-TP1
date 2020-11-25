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
   **/
  public void run() {
    try {
      BufferedReader socIn;
      socIn = new BufferedReader(
          new InputStreamReader(clientSocket.getInputStream()));    
      socOut = new PrintStream(clientSocket.getOutputStream());
      EchoServerMultiThreaded.chatRoomListInform(this);
      while (true) {
        String line = socIn.readLine();
        String[] sublines = line.split("\\$",4);
        String tag = sublines[0];
        String date = sublines[1];
        String pseudo = sublines[2];
        String chatroom_or_post = "";
        if(sublines.length== 4) chatroom_or_post = sublines[3];
        String msg = date+" : <"+pseudo+"> ";

        System.out.println("tag: "+tag+"\n"
                          +"date: "+date+"\n"
                          +"pseudo: "+pseudo+"\n"
                          +"chatroom_or_post: "+chatroom_or_post); 

        if(currentChat != null){
          switch(tag){
            case "LEAVE":
              this.leave(msg);
              break;
            case "CREATE":
              this.leave(msg);
              this.create(msg,chatroom_or_post);
              break;
            case "POST":
              this.post(msg,chatroom_or_post);
              break;
            case "JOIN":
              this.leave(msg);
              this.join(msg,chatroom_or_post);
              break;
          }
        }
        else
        {
          switch(tag){
            case "CREATE":
              this.create(msg,chatroom_or_post);
              break;
            case "JOIN":
              this.join(msg,chatroom_or_post);
              break;
            default:
              socOut.println("You must join a chatroom first");
          }
        }
      }
    } catch (Exception e) {
      System.err.println("Error in ClientThread:");
      e.printStackTrace();
      EchoServerMultiThreaded.removeConnectedThread(this);
    }
  }

  public void sendMessageToClient(String msg){
    socOut.println("PUBLISH$"+msg);
  }

  public void sendInformationToClient(String info){
    socOut.println("INFORM$"+info);
  }

  private void create(String msg, String chatroom){
              if(EchoServerMultiThreaded.createChatroom(chatroom)){
              	currentChat = EchoServerMultiThreaded.joinChatroom(this,chatroom);
              	EchoServerMultiThreaded.publishMessage(msg+ "has created the chatroom "+chatroom,currentChat);
              	} else {
              		this.join(msg,chatroom);
          		}
  }

  private void join(String msg, String chatroom){
              currentChat = EchoServerMultiThreaded.joinChatroom(this,chatroom);
              if(currentChat == null)
              	this.create(msg,chatroom);
              else
              	EchoServerMultiThreaded.publishMessage(msg+"has joined the room.",currentChat);
  }
  
  private void leave(String msg){
              EchoServerMultiThreaded.leaveChatroom(this,currentChat);
              EchoServerMultiThreaded.publishMessage(msg+"has left.",currentChat);
              currentChat = null;
  }

  private void post(String msg, String post){
              EchoServerMultiThreaded.publishMessage(msg+post,currentChat);
  }

}
