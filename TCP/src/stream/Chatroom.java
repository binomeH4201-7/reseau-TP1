import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.io.*;

public class Chatroom {

  private List<ClientThread> connectedClientsList;
  private String name;
  private BufferedWriter outFile;

  public Chatroom(String name){
    this.name = name;
    this.connectedClientsList = new LinkedList<ClientThread>();
    try
    {
      this.outFile = new BufferedWriter(new FileWriter("./history/"+name+".save"));
    }
    catch(IOException exc)
    {
      System.out.println("Could not find the history file for chatroom "+this.name );
    }
  }

  public void join(ClientThread ct){
    this.connectedClientsList.add(ct);
  }

  public void leave(ClientThread ct){
    this.connectedClientsList.remove(ct);
  }

  public void addMessageToHistory(String message){
    try{
      this.outFile.write(message,0,message.length());
    }
    catch(IOException e)
    {
      System.out.println("Error while writing the history");
    }
  }

  public List<String> getHistory(){
    BufferedReader inFile = null;
    String line;
    List<String> history = new ArrayList<String>();

    try
    {
      inFile = new BufferedReader(new FileReader("./history/"+name+".save"));
    }
    catch(FileNotFoundException exc)
    {
      System.out.println("Could not find the history file for chatroom "+this.name );
    }

    try{

      while ((line = inFile.readLine()) != null)
        history.add(line);

      inFile.close();
    }
    catch(IOException e)
    {
      System.out.println("Error while parsing the history");
    }
    return history;
  }

  public List<ClientThread> getConnectedClients(){
    return this.connectedClientsList;
  }

  public String getName(){
    return this.name;
  }

  public void closeHistory(){
    try{
      this.outFile.close();
    }
    catch(IOException e)
    {
      System.out.println("Error while writing the history");
    }
  }

}

