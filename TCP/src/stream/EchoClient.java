/*
   EchoClient
   Example of a TCP client
Date: 10/01/04
Authors: BUONOMO Phanie - BATEL Arthur
 */

import java.io.*;
import java.net.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;


public class EchoClient {

  private Socket echoSocket;
  private PrintStream socOut;
  private BufferedReader socIn;
  private String pseudo;
  private GraphicalInterface ihm;
  private ThreadDisplay td;

  /**
   *  main method
   *  accepts a connection, receives a message from client then sends an echo to the client
   **/

  public void connectToServer(String ip, int port, String pseudo){
    echoSocket = null;
    socOut = null;
    socIn = null;
    this.pseudo = pseudo;
    try{
      echoSocket = new Socket(ip,port);
      socOut = new PrintStream(echoSocket.getOutputStream());
      socIn = new BufferedReader( new InputStreamReader(echoSocket.getInputStream()));
    } catch (UnknownHostException e) {
      System.err.println("Don't know about host:" + ip);
      System.exit(1);
    } catch (IOException e) {
      System.err.println("Couldn't get I/O for "
          + "the connection to:"+ ip);
      System.exit(1);
    }

    td = new ThreadDisplay(ihm);
    td.start();
  }

  public void disconnectFromServer(){
    try{
      echoSocket.close();
      socIn.close();
      socOut.close();
      td.arret();
    } catch(Exception e){
      System.err.println("Error while closing the streams : "+e);
    }
  }

  public void join(String chatroom){
    String request = "JOIN$";
    LocalTime date = LocalTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("H:m");
    String dateString = date.format(format);
    request +=dateString+"$"+this.pseudo+"$"+chatroom;
    socOut.println(request);
  }

  public void create(String chatroom){
    String request = "CREATE$";
    LocalTime date = LocalTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("H:m");
    String dateString = date.format(format);
    request +=dateString+"$"+this.pseudo+"$"+chatroom;
    socOut.println(request);
  }

  public void leave(){
    String request = "LEAVE$";
    LocalTime date = LocalTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("H:m");
    String dateString = date.format(format);
    request +=dateString+"$"+this.pseudo;
    socOut.println(request);
  }

  public void post(String msg){
    String request = "POST$";
    LocalTime date = LocalTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("H:m");
    String dateString = date.format(format);
    request +=dateString+"$"+this.pseudo+"$"+msg;
    socOut.println(request);
  }

  public void setInterface(GraphicalInterface g){
    ihm = g;
  }

  public class ThreadDisplay extends Thread{
    GraphicalInterface ihm;
    boolean running;

    public ThreadDisplay(GraphicalInterface g){
      this.ihm = g;
      this.running = true;
    }

    public void arret(){
      running = false;
    }

    public void run(){
      while(running) {
        try {
          String line = socIn.readLine();
          ihm.publishMessage(line);
        } catch (Exception e) {
          System.err.println("Error while trying to print message : " + e);
        }
      }
    }
  }
}
