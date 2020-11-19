import java.io.IOException;

public class ClientLauncher {

    public static void main(String[] args) throws IOException {
        EchoClient echoClient = new EchoClient();
        GraphicalInterface g = new GraphicalInterface("TCP chat", echoClient);
        echoClient.setGraphicalInterface(g);
    }
}
