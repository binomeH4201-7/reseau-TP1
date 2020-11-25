import java.io.IOException;
/**
 * Start the graphical interface and create a client
 *
 * @author BUONOMO Phanie, BATEL Arthur
 */
public class ClientLauncher {

    public static void main(String[] args) throws IOException {
        EchoClient echoClient = new EchoClient();
        GraphicalInterface g = new GraphicalInterface("TCP chat", echoClient);
        echoClient.setInterface(g);
    }
}
