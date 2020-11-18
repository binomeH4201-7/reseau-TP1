import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.io.*;

public class Chatroom {

  private List<ClientThread> connectedClientsList;
  private String name;

  public Chatroom(String name){
    this.name = name;
    this.connectedClientsList = new LinkedList<ClientThread>();
  }

  public void join(ClientThread ct){
    this.connectedClientsList.add(ct);
  }

  public void leave(ClientThread ct){
    this.connectedClientsList.remove(ct);
  }

  public void addMessageToHistory(String message){
    BufferedWriter outFile = null;
    try
    {
      outFile = new BufferedWriter(new FileWriter("./history/"+name+".save",true));
    }
    catch(IOException exc)
    {
      System.out.println("Could not find the history file for chatroom "+this.name );
    }
    try{
      outFile.append(message+"\n");
      outFile.close();

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
      System.out.println("Could not find the history file for chatroom "+this.name);
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

}

