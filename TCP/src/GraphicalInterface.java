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

            //Prefered Size
            this.serverConnectBtn.setPreferredSize(new Dimension(40,20));
            this.serverDisconnectBtn.setPreferredSize(new Dimension(40,20));

            this.chatRoomCreateBtn.setPreferredSize(new Dimension(40,20));
            this.chatRoomJoinBtn.setPreferredSize(new Dimension(40,20));
            this.chatRoomLeaveBtn.setPreferredSize(new Dimension(40,20));

            this.msgSendBtn.setPreferredSize(new Dimension(600,260));

        //Labels initialisation, addition to container

        serverIpLabel = new JLabel("Server Ip", SwingConstants.RIGHT);
        serverPortLabel = new JLabel("Port",SwingConstants.RIGHT);
        pseudonymLabel = new JLabel("Pseudonym",SwingConstants.RIGHT);
        connexionStateLabel = new JLabel("Connexion state",SwingConstants.RIGHT);
        connexionStateMsgLabel = new JLabel("Starting ...",SwingConstants.LEFT);

        chatRoomLabel = new JLabel("Chat room",SwingConstants.RIGHT);

        messageLabel = new JLabel("Message",SwingConstants.RIGHT);

            //Prefered Size
            this.serverIpLabel.setPreferredSize(new Dimension(120,20));
            this.serverPortLabel.setPreferredSize(new Dimension(120,20));
            this.pseudonymLabel.setPreferredSize(new Dimension(120,20));
            this.connexionStateLabel.setPreferredSize(new Dimension(120,20));
            this.connexionStateMsgLabel.setMinimumSize(new Dimension(400,20));

            this.chatRoomLabel.setPreferredSize(new Dimension(120,20));

            this.messageLabel.setPreferredSize(new Dimension(120,20));

            //Borders
            this.serverIpLabel.setBorder(new EmptyBorder(0,0,0,10));
            this.serverPortLabel.setBorder(new EmptyBorder(0,0,0,10));
            this.pseudonymLabel.setBorder(new EmptyBorder(0,0,0,10));
            this.connexionStateLabel.setBorder(new EmptyBorder(0,0,0,10));
            this.connexionStateMsgLabel.setBorder(new EmptyBorder(0,20,0,80));

            this.chatRoomLabel.setBorder(new EmptyBorder(0,0,0,10));

            this.messageLabel.setBorder(new EmptyBorder(0,0,0,10));

            //Text Area initialisation
            chatTextArea = new JTextArea("Loading messages...");
            messageTextArea = new JTextArea("Salut tout le monde !");

            chatTextArea.setLineWrap(true);
            chatTextArea.setEditable(false);
            chatTextArea.setBorder(new EmptyBorder(0,10,0,10));
            DefaultCaret caret = (DefaultCaret)chatTextArea.getCaret();
            caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

            messageTextArea.setLineWrap(true);
            messageTextArea.setMaximumSize(new Dimension(400,100));
            messageTextArea.setBorder(new EmptyBorder(0,0,0,10));

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

            //Prefered Size
            this.messageScrollPane.setPreferredSize(new Dimension(400,620));
            this.listScrollPane.setPreferredSize(new Dimension(400,60));

        //Text Fields initialisation
        serverIpTextField = new JTextField("0.0.0.0");
        serverPortTextField = new JTextField("5100");
        pseudonymTextField = new JTextField("INSA User");

        chatRoomNameTextField = new JTextField("hexanome");

            //Text Field Border
            serverIpTextField.setMaximumSize(new Dimension(400,20));
            serverPortTextField.setMaximumSize(new Dimension(400,20));
            pseudonymTextField.setMaximumSize(new Dimension(400,20));

            chatRoomNameTextField.setMaximumSize(new Dimension(400,20));

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

            //Prefered Size
            this.chatPanel.setPreferredSize(new Dimension(400,620));
            this.interactionPanel.setPreferredSize(new Dimension(600,620));

            this.serverPanel.setPreferredSize(new Dimension(600,240));
            this.serverIpPanel.setPreferredSize(new Dimension(600,40));
            this.serverPortPanel.setPreferredSize(new Dimension(600,40));
            this.serverPseudonymPanel.setPreferredSize(new Dimension(600,40));
            this.serverStatePanel.setPreferredSize(new Dimension(600,40));
            this.serverButtonPanel.setPreferredSize(new Dimension(600,40));

            this.chatRoomPanel.setPreferredSize(new Dimension(600,120));
            this.chatRoomNamePanel.setPreferredSize(new Dimension(600,40));
            this.chatRoomButtonPanel.setPreferredSize(new Dimension(600,40));

            this.messagePanel.setPreferredSize(new Dimension(600,260));
            this.messageTypingPanel.setPreferredSize(new Dimension(600,100));
            this.messageButtonPanel.setPreferredSize(new Dimension(600,40));


            //Add Layout
            this.chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
            this.interactionPanel.setLayout(new BoxLayout(interactionPanel, BoxLayout.Y_AXIS));

            this.serverPanel.setLayout(new BoxLayout(serverPanel, BoxLayout.Y_AXIS));
            this.serverIpPanel.setLayout(new BoxLayout(serverIpPanel, BoxLayout.X_AXIS));
            this.serverPortPanel.setLayout(new BoxLayout(serverPortPanel, BoxLayout.X_AXIS));
            this.serverPseudonymPanel.setLayout(new BoxLayout(serverPseudonymPanel, BoxLayout.X_AXIS));
            this.serverStatePanel.setLayout(new BoxLayout(serverStatePanel, BoxLayout.X_AXIS));
            this.serverButtonPanel.setLayout(new BoxLayout(serverButtonPanel, BoxLayout.X_AXIS));

            this.chatRoomPanel.setLayout(new BoxLayout(chatRoomPanel, BoxLayout.Y_AXIS));
            this.chatRoomNamePanel.setLayout(new BoxLayout(chatRoomNamePanel, BoxLayout.X_AXIS));
            this.chatRoomButtonPanel.setLayout(new BoxLayout(chatRoomButtonPanel, BoxLayout.X_AXIS));

            this.messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
            this.messageTypingPanel.setLayout(new BoxLayout(messageTypingPanel, BoxLayout.X_AXIS));
            this.messageButtonPanel.setLayout(new BoxLayout(messageButtonPanel, BoxLayout.X_AXIS));

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
        serverPanel.add(serverPortPanel);
        serverPanel.add(serverPseudonymPanel);
        serverPanel.add(serverStatePanel);
        serverPanel.add(serverButtonPanel);

        chatRoomNamePanel.add(chatRoomLabel);
        chatRoomNamePanel.add(listScrollPane);

        chatRoomButtonPanel.add(chatRoomJoinBtn);
        chatRoomButtonPanel.add(chatRoomLeaveBtn);
        chatRoomButtonPanel.add(chatRoomNameTextField);
        chatRoomButtonPanel.add(chatRoomCreateBtn);

        chatRoomPanel.add(chatRoomNamePanel);
        chatRoomPanel.add(chatRoomButtonPanel);

        messageTypingPanel.add(messageLabel);
        messageTypingPanel.add(messageTextArea);

        messageButtonPanel.add(msgSendBtn);

        messagePanel.add(messageTypingPanel);
        messagePanel.add(messageButtonPanel);

        interactionPanel.add(serverPanel);
        interactionPanel.add(chatRoomPanel);
        interactionPanel.add(messagePanel);
        //TODO mettre des séparations interactionPanel.add(Box.createHorizontalStrut(5));
        //TODO mettre des séparations interactionPanel.add(new JSeparator(SwingConstants.VERTICAL));
        //TODO interactionPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        chatPanel.add(messageScrollPane);

        //Add Panels in the window
        this.getContentPane().add(chatPanel);
        this.getContentPane().add(interactionPanel);

        //Window initialisation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
        this.pack();

    }

    public void addChatRoom(String chatRoom){
        chatRoomListModel.addElement(chatRoom);
    }

    public void publishConnexionState(String msg) {
        connexionStateMsgLabel.setText(msg);
    }

    public void publishMessage(String msg) {
        chatTextArea.setText(chatTextArea.getText()+msg+"\n");
    }

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

            if (chatRoomList.getSelectedIndex() == -1) {
                //No selection, disable fire button.

            } else {
                //Selection, enable the fire button.
                //fireButton.setEnabled(true);
            }
        }
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
