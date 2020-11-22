import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicalInterface extends JFrame implements ListSelectionListener {

    EchoClient echoClient;

    /*
     * Déclaration des objets graphiques
     */
    //Main Panels
    private final JPanel chatPanel;
    private final JPanel interactionPanel;

    //Server Panels
    private final JPanel serverPanel;
    private final JPanel serverIpPanel;
    private final JPanel serverPortPanel;
    private final JPanel serverPseudonymPanel;
    private final JPanel serverStatePanel;
    private final JPanel serverButtonPanel;

    //Chat room panels
    private final JPanel chatRoomPanel;
    private final JPanel chatRoomNamePanel;
    private final JPanel chatRoomButtonPanel;

    //Message panels
    private final JPanel messagePanel;
    private final JPanel messageTypingPanel;
    private final JPanel messageButtonPanel;

    //Buttons
    private final JButton serverConnectBtn;
    private final JButton serverDisconnectBtn;
    private final JButton chatRoomCreateBtn;
    private final JButton chatRoomJoinBtn;
    private final JButton chatRoomLeaveBtn;
    private final JButton msgSendBtn;

    //Labels
    private final JLabel serverIpLabel;
    private final JLabel serverPortLabel;
    private final JLabel pseudonymLabel;
    private final JLabel connexionStateLabel;
    private final JLabel connexionStateMsgLabel;
    private final JLabel chatRoomLabel;
    private final JLabel messageLabel;

    //Text field
    private final JTextField serverIpTextField;
    private final JTextField serverPortTextField;
    private final JTextField pseudonymTextField;
    private final JTextField chatRoomNameTextField;

    //ScrollPane
    private final JScrollPane messageScrollPane;
    private final JScrollPane listScrollPane;

    //Text Area
    private final JTextArea chatTextArea;
    private final JTextArea messageTextArea;

    //List
    private final DefaultListModel chatRoomListModel;
    private final JList chatRoomList;

    public GraphicalInterface(String title, EchoClient echoClient) {
        super(title);

        this.echoClient = echoClient;

        //Button initialisation
        serverConnectBtn = new JButton("Connect");
        serverDisconnectBtn = new JButton("Disconnect");
        chatRoomCreateBtn = new JButton("New");
        chatRoomJoinBtn = new JButton("Join");
        chatRoomLeaveBtn = new JButton("Leave");
        msgSendBtn = new JButton("Send");

        setButtons(true,false,false,false, false, false);

            //Add listeners to buttons
            serverConnectBtn.addActionListener(new ConnectToServer());
            serverDisconnectBtn.addActionListener(new DisconnectFromServer());
            chatRoomCreateBtn.addActionListener(new CreateChatRoom());
            chatRoomJoinBtn.addActionListener(new JoinChatRoom());
            chatRoomLeaveBtn.addActionListener(new LeaveChatRoom());
            msgSendBtn.addActionListener(new SendMessage());

        //Labels initialisation

        serverIpLabel = new JLabel("Server Ip", SwingConstants.RIGHT);
        serverPortLabel = new JLabel("Port",SwingConstants.RIGHT);
        pseudonymLabel = new JLabel("Pseudonym",SwingConstants.RIGHT);
        connexionStateLabel = new JLabel("Connexion state",SwingConstants.RIGHT);
        connexionStateMsgLabel = new JLabel("Starting ...",SwingConstants.LEFT);
        chatRoomLabel = new JLabel("Chat rooms",SwingConstants.LEFT);
        messageLabel = new JLabel("Message",SwingConstants.RIGHT);

        //Text Area initialisation
        chatTextArea = new JTextArea("Loading messages...");
        chatTextArea.setLineWrap(true);
        chatTextArea.setEditable(false);
        DefaultCaret caret = (DefaultCaret)chatTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        messageTextArea = new JTextArea("Salut tout le monde !");
        messageTextArea.setLineWrap(true);

        //List initialisation
        chatRoomListModel = new DefaultListModel();
        chatRoomList = new JList(chatRoomListModel);
        chatRoomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        chatRoomList.setSelectedIndex(0);
        chatRoomList.addListSelectionListener(this);
        chatRoomList.setVisibleRowCount(5);

        //ScrollPane initialisation
        messageScrollPane =  new JScrollPane(chatTextArea);
        messageScrollPane.createHorizontalScrollBar();
        listScrollPane =  new JScrollPane(chatRoomList);
        listScrollPane.createHorizontalScrollBar();

        //Text Fields initialisation
        serverIpTextField = new JTextField("0.0.0.0");
        serverPortTextField = new JTextField("5100");
        pseudonymTextField = new JTextField("INSA User");
        chatRoomNameTextField = new JTextField("hexanome");

        //Containers initialisation
        this.chatPanel = new JPanel();
        this.interactionPanel = new JPanel();

        this.serverPanel = new JPanel();
        this.serverIpPanel = new JPanel();
        this.serverPortPanel = new JPanel();
        this.serverPseudonymPanel = new JPanel();
        this.serverStatePanel = new JPanel();
        this.serverButtonPanel = new JPanel();

        this.chatRoomPanel = new JPanel();
        this.chatRoomNamePanel = new JPanel();
        this.chatRoomButtonPanel = new JPanel();

        this.messagePanel = new JPanel();
        this.messageTypingPanel = new JPanel();
        this.messageButtonPanel = new JPanel();

        //Add objects to panels
        serverIpPanel.add(serverIpLabel);
        serverIpPanel.add(serverIpTextField);

        serverPortPanel.add(serverPortLabel);
        serverPortPanel.add(serverPortTextField);

        serverPseudonymPanel.add(pseudonymLabel);
        serverPseudonymPanel.add(pseudonymTextField);

        serverStatePanel.add(connexionStateLabel);
        serverStatePanel.add(connexionStateMsgLabel);

        serverButtonPanel.add(serverConnectBtn);
        serverButtonPanel.add(serverDisconnectBtn);

        serverPanel.add(serverIpPanel);
        serverPanel.add(Box.createVerticalStrut(10));
        serverPanel.add(serverPortPanel);
        serverPanel.add(Box.createVerticalStrut(10));
        serverPanel.add(serverPseudonymPanel);
        serverPanel.add(Box.createVerticalStrut(10));
        serverPanel.add(serverStatePanel);
        serverPanel.add(Box.createVerticalStrut(10));
        serverPanel.add(serverButtonPanel);

        chatRoomNamePanel.add(chatRoomLabel);
        chatRoomNamePanel.add(listScrollPane);

        chatRoomButtonPanel.add(chatRoomJoinBtn);
        chatRoomButtonPanel.add(chatRoomLeaveBtn);
        chatRoomButtonPanel.add(chatRoomNameTextField);
        chatRoomButtonPanel.add(chatRoomCreateBtn);

        chatRoomPanel.add(chatRoomNamePanel);
        serverPanel.add(Box.createVerticalStrut(5));
        chatRoomPanel.add(chatRoomButtonPanel);

        messageTypingPanel.add(messageLabel);
        messageTypingPanel.add(messageTextArea);

        messageButtonPanel.add(msgSendBtn);

        messagePanel.add(messageTypingPanel);
        serverPanel.add(Box.createVerticalStrut(5));
        messagePanel.add(messageButtonPanel);

        interactionPanel.add(serverPanel);
        serverPanel.add(Box.createVerticalStrut(5));
        interactionPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        serverPanel.add(Box.createVerticalStrut(5));
        interactionPanel.add(chatRoomPanel);
        serverPanel.add(Box.createVerticalStrut(5));
        interactionPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        serverPanel.add(Box.createVerticalStrut(5));
        interactionPanel.add(messagePanel);

        chatPanel.add(messageScrollPane);

        //Disposition
        serverIpLabel.setPreferredSize(new Dimension(120,20));
        serverIpLabel.setBorder(new EmptyBorder(0,0,0,10));

        serverIpTextField.setMaximumSize(new Dimension(400,20));

        serverPortLabel.setPreferredSize(new Dimension(120,20));
        serverPortLabel.setBorder(new EmptyBorder(0,0,0,10));

        serverPortTextField.setMaximumSize(new Dimension(400,20));

        pseudonymLabel.setPreferredSize(new Dimension(120,20));
        pseudonymLabel.setBorder(new EmptyBorder(0,0,0,10));

        pseudonymTextField.setMaximumSize(new Dimension(400,20));

        connexionStateLabel.setPreferredSize(new Dimension(120,20));
        connexionStateLabel.setBorder(new EmptyBorder(0,0,0,10));

        connexionStateMsgLabel.setMinimumSize(new Dimension(400,20));

        serverConnectBtn.setPreferredSize(new Dimension(40,20));
        serverDisconnectBtn.setPreferredSize(new Dimension(40,20));

        serverIpPanel.setPreferredSize(new Dimension(600,40));
        serverIpPanel.setLayout(new BoxLayout(serverIpPanel, BoxLayout.X_AXIS));

        serverPortPanel.setPreferredSize(new Dimension(600,40));
        serverPortPanel.setLayout(new BoxLayout(serverPortPanel, BoxLayout.X_AXIS));

        serverPseudonymPanel.setPreferredSize(new Dimension(600,40));
        serverPseudonymPanel.setLayout(new BoxLayout(serverPseudonymPanel, BoxLayout.X_AXIS));

        serverStatePanel.setPreferredSize(new Dimension(600,40));
        serverStatePanel.setLayout(new BoxLayout(serverStatePanel, BoxLayout.X_AXIS));

        serverButtonPanel.setPreferredSize(new Dimension(600,40));
        serverButtonPanel.setLayout(new BoxLayout(serverButtonPanel, BoxLayout.X_AXIS));

        serverPanel.setPreferredSize(new Dimension(600,190));
        serverPortLabel.setBorder(new EmptyBorder(10,10,10,10));
        serverPanel.setLayout(new BoxLayout(serverPanel, BoxLayout.Y_AXIS));

        chatRoomLabel.setBorder(new EmptyBorder(0,0,0,10));
        chatRoomLabel.setPreferredSize(new Dimension(120,20));

        listScrollPane.setMinimumSize(new Dimension(400,100));
        listScrollPane.setBorder(new EmptyBorder(10,10,10,10));

        chatRoomJoinBtn.setMinimumSize(new Dimension(40,20));

        chatRoomLeaveBtn.setMinimumSize(new Dimension(40,20));

        chatRoomNameTextField.setMaximumSize(new Dimension(360,20));

        chatRoomCreateBtn.setMinimumSize(new Dimension(40,20));

        chatRoomNamePanel.setLayout(new BoxLayout(chatRoomNamePanel, BoxLayout.Y_AXIS));

        chatRoomButtonPanel.setPreferredSize(new Dimension(600,40));
        chatRoomButtonPanel.setBorder(new EmptyBorder(10,10,10,10));
        chatRoomButtonPanel.setLayout(new BoxLayout(chatRoomButtonPanel, BoxLayout.X_AXIS));

        chatRoomPanel.setPreferredSize(new Dimension(600,160));
        chatRoomPanel.setLayout(new BoxLayout(chatRoomPanel, BoxLayout.Y_AXIS));

        messageLabel.setPreferredSize(new Dimension(120,20));
        messageLabel.setBorder(new EmptyBorder(0,0,0,10));

        messageTextArea.setMinimumSize(new Dimension(400,100));

        msgSendBtn.setPreferredSize(new Dimension(600,260));

        messageTypingPanel.setPreferredSize(new Dimension(600,100));
        messageTypingPanel.setBorder(new EmptyBorder(10,10,10,10));
        messageTypingPanel.setLayout(new BoxLayout(messageTypingPanel, BoxLayout.Y_AXIS));

        messageButtonPanel.setPreferredSize(new Dimension(600,40));
        messageButtonPanel.setLayout(new BoxLayout(messageButtonPanel, BoxLayout.X_AXIS));

        messagePanel.setPreferredSize(new Dimension(600,260));
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));

        messageScrollPane.setPreferredSize(new Dimension(400,620));

        chatTextArea.setBorder(new EmptyBorder(0,10,0,10));

        chatPanel.setPreferredSize(new Dimension(400,620));
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));

        interactionPanel.setPreferredSize(new Dimension(600,620));
        interactionPanel.setLayout(new BoxLayout(interactionPanel, BoxLayout.Y_AXIS));

        //Add Panels in the window
        getContentPane().add(chatPanel);
        getContentPane().add(interactionPanel);

        //Window initialisation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
        getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
        pack();
        setLocationRelativeTo(null);

    }

    public void addChatRoom(String chatRoom){
        chatRoomListModel.addElement(chatRoom);
        chatRoomList.setSelectedIndex(chatRoomListModel.getSize()-1);
        chatRoomList.ensureIndexIsVisible(chatRoomListModel.getSize()-1);
    }

    public void publishConnexionState(String msg) {
        connexionStateMsgLabel.setText(msg);
    }

    public void publishMessage(String msg) {
        chatTextArea.setText(chatTextArea.getText()+msg+"\n");
    }

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
    }

    public void setButtons(boolean serverConnect, boolean serverDisconnect, boolean chatRoomCreate, boolean chatRoomJoin, boolean chatRoomLeave, boolean sendMsg){
        serverConnectBtn.setEnabled(serverConnect);
        serverDisconnectBtn.setEnabled(serverDisconnect);
        chatRoomCreateBtn.setEnabled(chatRoomCreate);
        chatRoomJoinBtn.setEnabled(chatRoomJoin);
        chatRoomLeaveBtn.setEnabled(chatRoomLeave);
        msgSendBtn.setEnabled(sendMsg);
    }

    public class ConnectToServer implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            echoClient.connectToServer(serverIpTextField.getText(),Integer.parseInt(serverPortTextField.getText()),pseudonymTextField.getText());
            setButtons(true,true,true,true, true, true);
        }
    }

    public class DisconnectFromServer implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            chatTextArea.setText("");
            echoClient.disconnectFromServer();
            chatRoomListModel.removeAllElements();
            setButtons(true,false,false,false, false, false);
        }
    }

    public class CreateChatRoom implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            chatTextArea.setText("");
            echoClient.create(chatRoomNameTextField.getText());
        }
    }

    public class JoinChatRoom implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            chatTextArea.setText("");
            echoClient.join(chatRoomListModel.getElementAt(chatRoomList.getSelectedIndex()).toString());
        }
    }

    public class LeaveChatRoom implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            chatTextArea.setText("");
            echoClient.leave();
        }
    }

    public class SendMessage implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            echoClient.post(messageTextArea.getText());
        }
    }

}
