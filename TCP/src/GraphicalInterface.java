import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Graphical Interface.
 * Constructs the graphical interface for the client side and prints the messages received from the Server.
 *
 * @author BUONOMO Phanie, BATEL Arthur
 */
public class GraphicalInterface extends JFrame implements ListSelectionListener {

    EchoClient echoClient;

    /*
    Variables disposition
     */
    private final int INTERACTION_PANEL_WIDTH = 400;
    private final int INTERACTION_PANEL_HEIGTH = 40;
    private final int INTERACTION_PANEL_CONTENT_WIDTH = 300;
    private final int SERVER_PANEL_HEIGTH = 190;
    private final int CHAT_ROOM_LIST_HEIGTH = 100;
    private final int CHAT_ROOM_PANEL_HEIGHT = 160;
    private final int MESSAGE_TEXT_AREA_HEIGTH = 100;
    private final int MESSAGE_TYPING_PANEL_HEIGTH = 100;
    private final int MESSAGE_PANEL_HEIGTH = 260;
    private final int MESSAGE_SCROLL_PANEL_HEIGTH = 620;
    private final int VERTICAL_STRUT_SIZE = 10;
    private final int FIELD_LABEL_BTN_HEIGHT = 20;
    private final int BTN_WIDTH = 20;
    private final int LABEL_WIDTH = 120;
    private final int CHAT_ROOM_TEXT_FIELD_WIDTH = 360;
    private final int RIGHT_FIELD_BORDER = 10;
    private final int PANEL_CHAT_BORDER = 10;

    private final String DEFAULT_IP = "0.0.0.0";
    private final String DEFAULT_PORT = "5100";
    private final String DEFAULT_PSEUDONYM = "INSA User";
    private final String DEFAULT_CHAT_ROOM_NAME = "hexanome";
    private final String DEFAULT_MESSAGE= "Salut tout le monde !";
    private final String DEFAULT_CHAT_MESSAGE= "Loading messages...";

    private final String CONNECT_BTN_LABEL = "Connect";
    private final String DISCONNECT_BTN_LABEL = "Disconnect";
    private final String NEW_BTN_LABEL = "New";
    private final String JOIN_BTN_LABEL = "Join";
    private final String LEAVE_BTN_LABEL = "Leave";
    private final String SEND_BTN_LABEL = "Send";

    private final String SERVER_IP_LABEL = "Server Ip";
    private final String PORT_LABEL = "Port";
    private final String PSEUDONYM_LABEL = "Pseudonym";
    private final String CONNEXION_STATE_LABEL = "Connexion state";
    private final String CONEXION_STATE_MSG_LABEL = "Starting ...";
    private final String CHAT_ROOM_LABEL = "Chat rooms";
    private final String MESSAGE_LABEL = "Message";

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
        serverConnectBtn = new JButton(CONNECT_BTN_LABEL);
        serverDisconnectBtn = new JButton(DISCONNECT_BTN_LABEL);
        chatRoomCreateBtn = new JButton(NEW_BTN_LABEL);
        chatRoomJoinBtn = new JButton(JOIN_BTN_LABEL);
        chatRoomLeaveBtn = new JButton(LEAVE_BTN_LABEL);
        msgSendBtn = new JButton(SEND_BTN_LABEL);

        setButtons(true,false,false,false, false, false);

            //Add listeners to buttons
            serverConnectBtn.addActionListener(new ConnectToServer());
            serverDisconnectBtn.addActionListener(new DisconnectFromServer());
            chatRoomCreateBtn.addActionListener(new CreateChatRoom());
            chatRoomJoinBtn.addActionListener(new JoinChatRoom());
            chatRoomLeaveBtn.addActionListener(new LeaveChatRoom());
            msgSendBtn.addActionListener(new SendMessage());

        //Labels initialisation

        serverIpLabel = new JLabel(SERVER_IP_LABEL, SwingConstants.RIGHT);
        serverPortLabel = new JLabel(PORT_LABEL,SwingConstants.RIGHT);
        pseudonymLabel = new JLabel(PSEUDONYM_LABEL,SwingConstants.RIGHT);
        connexionStateLabel = new JLabel(CONNEXION_STATE_LABEL,SwingConstants.RIGHT);
        connexionStateMsgLabel = new JLabel(CONEXION_STATE_MSG_LABEL,SwingConstants.LEFT);
        chatRoomLabel = new JLabel(CHAT_ROOM_LABEL,SwingConstants.LEFT);
        messageLabel = new JLabel(MESSAGE_LABEL,SwingConstants.RIGHT);

        //Text Area initialisation
        chatTextArea = new JTextArea(DEFAULT_CHAT_MESSAGE);
        chatTextArea.setLineWrap(true);
        chatTextArea.setEditable(false);
        DefaultCaret caret = (DefaultCaret)chatTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        messageTextArea = new JTextArea(DEFAULT_MESSAGE);
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
        serverIpTextField = new JTextField(DEFAULT_IP);
        serverPortTextField = new JTextField(DEFAULT_PORT);
        pseudonymTextField = new JTextField(DEFAULT_PSEUDONYM);
        chatRoomNameTextField = new JTextField(DEFAULT_CHAT_ROOM_NAME);

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
        serverPanel.add(Box.createVerticalStrut(VERTICAL_STRUT_SIZE));
        serverPanel.add(serverPortPanel);
        serverPanel.add(Box.createVerticalStrut(VERTICAL_STRUT_SIZE));
        serverPanel.add(serverPseudonymPanel);
        serverPanel.add(Box.createVerticalStrut(VERTICAL_STRUT_SIZE));
        serverPanel.add(serverStatePanel);
        serverPanel.add(Box.createVerticalStrut(VERTICAL_STRUT_SIZE));
        serverPanel.add(serverButtonPanel);

        chatRoomNamePanel.add(chatRoomLabel);
        chatRoomNamePanel.add(listScrollPane);

        chatRoomButtonPanel.add(chatRoomJoinBtn);
        chatRoomButtonPanel.add(chatRoomLeaveBtn);
        chatRoomButtonPanel.add(chatRoomNameTextField);
        chatRoomButtonPanel.add(chatRoomCreateBtn);

        chatRoomPanel.add(chatRoomNamePanel);
        serverPanel.add(Box.createVerticalStrut(VERTICAL_STRUT_SIZE));
        chatRoomPanel.add(chatRoomButtonPanel);

        messageTypingPanel.add(messageLabel);
        messageTypingPanel.add(messageTextArea);

        messageButtonPanel.add(msgSendBtn);

        messagePanel.add(messageTypingPanel);
        serverPanel.add(Box.createVerticalStrut(VERTICAL_STRUT_SIZE));
        messagePanel.add(messageButtonPanel);

        interactionPanel.add(serverPanel);
        serverPanel.add(Box.createVerticalStrut(VERTICAL_STRUT_SIZE));
        interactionPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        serverPanel.add(Box.createVerticalStrut(VERTICAL_STRUT_SIZE));
        interactionPanel.add(chatRoomPanel);
        serverPanel.add(Box.createVerticalStrut(VERTICAL_STRUT_SIZE));
        interactionPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        serverPanel.add(Box.createVerticalStrut(VERTICAL_STRUT_SIZE));
        interactionPanel.add(messagePanel);

        chatPanel.add(messageScrollPane);

        //Disposition
        serverIpLabel.setPreferredSize(new Dimension(LABEL_WIDTH,FIELD_LABEL_BTN_HEIGHT));
        serverIpLabel.setBorder(new EmptyBorder(0,0,0,RIGHT_FIELD_BORDER));

        serverIpTextField.setMaximumSize(new Dimension(INTERACTION_PANEL_CONTENT_WIDTH,FIELD_LABEL_BTN_HEIGHT));

        serverPortLabel.setPreferredSize(new Dimension(LABEL_WIDTH,FIELD_LABEL_BTN_HEIGHT));
        serverPortLabel.setBorder(new EmptyBorder(0,0,0,RIGHT_FIELD_BORDER));

        serverPortTextField.setMaximumSize(new Dimension(INTERACTION_PANEL_CONTENT_WIDTH,FIELD_LABEL_BTN_HEIGHT));

        pseudonymLabel.setPreferredSize(new Dimension(LABEL_WIDTH,FIELD_LABEL_BTN_HEIGHT));
        pseudonymLabel.setBorder(new EmptyBorder(0,0,0,RIGHT_FIELD_BORDER));

        pseudonymTextField.setMaximumSize(new Dimension(INTERACTION_PANEL_CONTENT_WIDTH,FIELD_LABEL_BTN_HEIGHT));

        connexionStateLabel.setPreferredSize(new Dimension(LABEL_WIDTH,FIELD_LABEL_BTN_HEIGHT));
        connexionStateLabel.setBorder(new EmptyBorder(0,0,0,RIGHT_FIELD_BORDER));

        connexionStateMsgLabel.setMinimumSize(new Dimension(INTERACTION_PANEL_CONTENT_WIDTH,FIELD_LABEL_BTN_HEIGHT));

        serverConnectBtn.setPreferredSize(new Dimension(BTN_WIDTH,FIELD_LABEL_BTN_HEIGHT));
        serverDisconnectBtn.setPreferredSize(new Dimension(BTN_WIDTH,FIELD_LABEL_BTN_HEIGHT));

        serverIpPanel.setPreferredSize(new Dimension(INTERACTION_PANEL_WIDTH,INTERACTION_PANEL_HEIGTH));
        serverIpPanel.setLayout(new BoxLayout(serverIpPanel, BoxLayout.X_AXIS));

        serverPortPanel.setPreferredSize(new Dimension(INTERACTION_PANEL_WIDTH,INTERACTION_PANEL_HEIGTH));
        serverPortPanel.setLayout(new BoxLayout(serverPortPanel, BoxLayout.X_AXIS));

        serverPseudonymPanel.setPreferredSize(new Dimension(INTERACTION_PANEL_WIDTH,INTERACTION_PANEL_HEIGTH));
        serverPseudonymPanel.setLayout(new BoxLayout(serverPseudonymPanel, BoxLayout.X_AXIS));

        serverStatePanel.setPreferredSize(new Dimension(INTERACTION_PANEL_WIDTH,INTERACTION_PANEL_HEIGTH));
        serverStatePanel.setLayout(new BoxLayout(serverStatePanel, BoxLayout.X_AXIS));

        serverButtonPanel.setPreferredSize(new Dimension(INTERACTION_PANEL_WIDTH,INTERACTION_PANEL_HEIGTH));
        serverButtonPanel.setLayout(new BoxLayout(serverButtonPanel, BoxLayout.X_AXIS));

        serverPanel.setPreferredSize(new Dimension(INTERACTION_PANEL_WIDTH, SERVER_PANEL_HEIGTH));
        serverPortLabel.setBorder(new EmptyBorder(PANEL_CHAT_BORDER,PANEL_CHAT_BORDER,PANEL_CHAT_BORDER,PANEL_CHAT_BORDER));
        serverPanel.setLayout(new BoxLayout(serverPanel, BoxLayout.Y_AXIS));

        chatRoomLabel.setBorder(new EmptyBorder(0,0,0,RIGHT_FIELD_BORDER));
        chatRoomLabel.setPreferredSize(new Dimension(LABEL_WIDTH,FIELD_LABEL_BTN_HEIGHT));

        listScrollPane.setMinimumSize(new Dimension(INTERACTION_PANEL_CONTENT_WIDTH,CHAT_ROOM_LIST_HEIGTH));
        listScrollPane.setBorder(new EmptyBorder(PANEL_CHAT_BORDER,PANEL_CHAT_BORDER,PANEL_CHAT_BORDER,PANEL_CHAT_BORDER));

        chatRoomJoinBtn.setMinimumSize(new Dimension(BTN_WIDTH,FIELD_LABEL_BTN_HEIGHT));

        chatRoomLeaveBtn.setMinimumSize(new Dimension(BTN_WIDTH,FIELD_LABEL_BTN_HEIGHT));

        chatRoomNameTextField.setMaximumSize(new Dimension(CHAT_ROOM_TEXT_FIELD_WIDTH,FIELD_LABEL_BTN_HEIGHT));

        chatRoomCreateBtn.setMinimumSize(new Dimension(BTN_WIDTH,FIELD_LABEL_BTN_HEIGHT));

        chatRoomNamePanel.setLayout(new BoxLayout(chatRoomNamePanel, BoxLayout.Y_AXIS));

        chatRoomButtonPanel.setPreferredSize(new Dimension(INTERACTION_PANEL_WIDTH,INTERACTION_PANEL_HEIGTH));
        chatRoomButtonPanel.setBorder(new EmptyBorder(PANEL_CHAT_BORDER,PANEL_CHAT_BORDER,PANEL_CHAT_BORDER,PANEL_CHAT_BORDER));
        chatRoomButtonPanel.setLayout(new BoxLayout(chatRoomButtonPanel, BoxLayout.X_AXIS));

        chatRoomPanel.setPreferredSize(new Dimension(INTERACTION_PANEL_WIDTH,CHAT_ROOM_PANEL_HEIGHT));
        chatRoomPanel.setLayout(new BoxLayout(chatRoomPanel, BoxLayout.Y_AXIS));

        messageLabel.setPreferredSize(new Dimension(LABEL_WIDTH,FIELD_LABEL_BTN_HEIGHT));
        messageLabel.setBorder(new EmptyBorder(0,0,0,RIGHT_FIELD_BORDER));

        messageTextArea.setMinimumSize(new Dimension(INTERACTION_PANEL_CONTENT_WIDTH,MESSAGE_TEXT_AREA_HEIGTH));

        msgSendBtn.setPreferredSize(new Dimension(BTN_WIDTH,FIELD_LABEL_BTN_HEIGHT));

        messageTypingPanel.setPreferredSize(new Dimension(INTERACTION_PANEL_WIDTH,MESSAGE_TYPING_PANEL_HEIGTH));
        messageTypingPanel.setBorder(new EmptyBorder(PANEL_CHAT_BORDER,PANEL_CHAT_BORDER,PANEL_CHAT_BORDER,PANEL_CHAT_BORDER));
        messageTypingPanel.setLayout(new BoxLayout(messageTypingPanel, BoxLayout.Y_AXIS));

        messageButtonPanel.setPreferredSize(new Dimension(INTERACTION_PANEL_WIDTH,INTERACTION_PANEL_HEIGTH));
        messageButtonPanel.setLayout(new BoxLayout(messageButtonPanel, BoxLayout.X_AXIS));

        messagePanel.setPreferredSize(new Dimension(INTERACTION_PANEL_WIDTH,MESSAGE_PANEL_HEIGTH));
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));

        messageScrollPane.setPreferredSize(new Dimension(INTERACTION_PANEL_CONTENT_WIDTH,MESSAGE_SCROLL_PANEL_HEIGTH));

        chatTextArea.setBorder(new EmptyBorder(0,PANEL_CHAT_BORDER,0,PANEL_CHAT_BORDER));

        chatPanel.setPreferredSize(new Dimension(INTERACTION_PANEL_CONTENT_WIDTH,MESSAGE_SCROLL_PANEL_HEIGTH));
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));

        interactionPanel.setPreferredSize(new Dimension(INTERACTION_PANEL_WIDTH,MESSAGE_SCROLL_PANEL_HEIGTH));
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
            messageTextArea.setText("");
        }
    }

}
