/*
   EchoServerMultiThreaded
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
   * @param EchoServerMultiThreaded port
   * 
   **/

  private static List<ClientThread> connectedThreadList = new ArrayList<>();
  private static List<Chatroom> chatroomList = new ArrayList<>();

  public static void main(String[] args){
    ServerSocket listenSocket;

    if (args.length != 1) {
      System.out.println("Usage: java EchoServerMultiThreaded <EchoServerMultiThreaded port>");
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
      System.err.println("Error in EchoServerMultiThreaded:" + e);
    }
  }

  public static void createChatroom(String chatName){
    Chatroom chat = new Chatroom(chatName);
    chatroomList.add(chat);
  }

  private static void addConnectedThread(ClientThread ct){
    connectedThreadList.add(ct);
  }

  public static Chatroom joinChatroom(ClientThread ct, String nameChat){
    for(Chatroom chat : chatroomList){
      if(chat.getName().equals(nameChat)){
        chat.join(ct);
        return chat;
      }
    }
    return null;
  }

  public static void removeConnectedThread(ClientThread ct){
    connectedThreadList.remove(ct);
  }

  public static void leaveChatroom(ClientThread ct, Chatroom chat){
    chat.leave(ct);
  }

  public static void publishMessage(String msg){
    for(ClientThread ct : connectedThreadList){
      ct.sendMessageToClient(msg);
    }
  }

  public static void publishMessage(String msg, Chatroom chat){
    for(ClientThread ct : chat.getConnectedClients()){
      ct.sendMessageToClient(msg);
    }
    saveMessage(msg,chat);
  }

  private static void saveMessage(String msg, Chatroom chat){
    chat.addMessageToHistory(msg);
  }
}
