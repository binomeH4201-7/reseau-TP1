import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicalInterface extends JFrame {
    protected final static int WIDTH = 1420;
    protected final static int HEIGHT = 850;

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

    //Chat rom panels
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

    //Text Area
    private final JTextArea chatTextArea;
    private final JTextArea messageTextField;

    public GraphicalInterface(String title) {
        super(title);

        //Window layout
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));

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

            //Add Panels in the frames
            this.getContentPane().add(chatPanel);
            this.getContentPane().add(interactionPanel);

            interactionPanel.add(serverPanel);
            interactionPanel.add(chatRoomPanel);
            interactionPanel.add(messagePanel);

            serverPanel.add(serverIpPanel);
            serverPanel.add(serverPortPanel);
            serverPanel.add(serverPseudonymPanel);
            serverPanel.add(serverStatePanel);
            serverPanel.add(serverButtonPanel);

            chatRoomPanel.add(chatRoomNamePanel);
            chatRoomPanel.add(chatRoomButtonPanel);

            messagePanel.add(messageTypingPanel);
            messagePanel.add(messageButtonPanel);

        //Button initialisation
        serverConnectBtn = new JButton("Connect");
        serverDisconnectBtn = new JButton("Disconnect");
        chatRoomCreateBtn = new JButton("New");
        chatRoomJoinBtn = new JButton("Join");
        chatRoomLeaveBtn = new JButton("Leave");
        msgSendBtn = new JButton("Send");

            //Prefered Size
            this.serverConnectBtn.setPreferredSize(new Dimension(40,20));
            this.serverDisconnectBtn.setPreferredSize(new Dimension(40,20));

            this.chatRoomCreateBtn.setPreferredSize(new Dimension(40,20));
            this.chatRoomJoinBtn.setPreferredSize(new Dimension(40,20));
            this.chatRoomLeaveBtn.setPreferredSize(new Dimension(40,20));

            this.msgSendBtn.setPreferredSize(new Dimension(600,260));

            //Add Button to container
            serverButtonPanel.add(serverConnectBtn);
            serverButtonPanel.add(serverDisconnectBtn);

            chatRoomButtonPanel.add(chatRoomCreateBtn);
            chatRoomButtonPanel.add(chatRoomJoinBtn);
            chatRoomButtonPanel.add(chatRoomLeaveBtn);

            messageButtonPanel.add(msgSendBtn);

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

            //Add Label in containers
            serverIpPanel.add(serverIpLabel);
            serverPortPanel.add(serverPortLabel);
            serverPseudonymPanel.add(pseudonymLabel);
            serverStatePanel.add(connexionStateLabel);
            serverStatePanel.add(connexionStateMsgLabel);

            chatRoomNamePanel.add(chatRoomLabel);

            messageTypingPanel.add(messageLabel);

            //Text Area initialisation
            chatTextArea = new JTextArea("Loading messages...");
            messageTextField = new JTextArea("Salut tout le monde !");

            chatTextArea.setLineWrap(true);
            chatTextArea.setEditable(false);
            chatTextArea.setBorder(new EmptyBorder(0,10,0,10));
            messageTextField.setLineWrap(true);

            chatTextArea.setText("How to Use BoxLayout\n" +
                    "Note: This lesson covers writing layout code by hand, which can be challenging. If you are not interested in learning all the details of layout management, you might prefer to use the GroupLayout layout manager combined with a builder tool to lay out your GUI. One such builder tool is the NetBeans IDE. Otherwise, if you want to code by hand and do not want to use GroupLayout, then GridBagLayout is recommended as the next most flexible and powerful layout manager.\n" +
                    "\n" +
                    "If you are interested in using JavaFX to create your GUI, see Working With Layouts in JavaFX.\n" +
                    "\n" +
                    "The Swing packages include a general purpose layout manager named BoxLayout. BoxLayout either stacks its components on top of each other or places them in a row — your choice. You might think of it as a version of FlowLayout, but with greater functionality. Here is a picture of an application that demonstrates using BoxLayout to display a centered column of components:\n" +
                    "A snapshot of BoxLayoutDemo\n" +
                    "\n" +
                    "Click the Launch button to run BoxLayoutDemo using Java™ Web Start (download JDK 7 or later). Alternatively, to compile and run the example yourself, consult the example index.\n" +
                    "Launches the BoxLayoutDemo example\n" +
                    "\n" +
                    "You can see the code in BoxLayoutDemo.java.\n" +
                    "\n" +
                    "The following figure shows a GUI that uses two instances of BoxLayout. In the top part of the GUI, a top-to-bottom box layout places a label above a scroll pane. In the bottom part of the GUI, a left-to-right box layout places two buttons next to each other. A BorderLayout combines the two parts of the GUI and ensures that any excess space is given to the scroll pane.\n" +
                    "Uses both left-to-right and top-to-bottom box layouts\n" +
                    "\n" +
                    "You can find links for running ListDialog and for its source files in the example index for Using Swing Components.\n" +
                    "\n" +
                    "The following code, taken from ListDialog.java, lays out the GUI. This code is in the constructor for the dialog, which is implemented as a JDialog subclass. The bold lines of code set up the box layouts and add components to them.\n" +
                    "\n" +
                    "JScrollPane listScroller = new JScrollPane(list);\n" +
                    "listScroller.setPreferredSize(new Dimension(250, 80));\n" +
                    "listScroller.setAlignmentX(LEFT_ALIGNMENT);\n" +
                    "...\n" +
                    "//Lay out the label and scroll pane from top to bottom.\n" +
                    "JPanel listPane = new JPanel();\n" +
                    "listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));\n" +
                    "JLabel label = new JLabel(labelText);\n" +
                    "...\n" +
                    "listPane.add(label);\n" +
                    "listPane.add(Box.createRigidArea(new Dimension(0,5)));\n" +
                    "listPane.add(listScroller);\n" +
                    "listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));\n" +
                    "\n" +
                    "//Lay out the buttons from left to right.\n" +
                    "JPanel buttonPane = new JPanel();\n" +
                    "buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));\n" +
                    "buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));\n" +
                    "buttonPane.add(Box.createHorizontalGlue());\n" +
                    "buttonPane.add(cancelButton);\n" +
                    "buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));\n" +
                    "buttonPane.add(setButton);\n" +
                    "\n" +
                    "//Put everything together, using the content pane's BorderLayout.\n" +
                    "Container contentPane = getContentPane();\n" +
                    "contentPane.add(listPane, BorderLayout.CENTER);\n" +
                    "contentPane.add(buttonPane, BorderLayout.PAGE_END);\n" +
                    "\n" +
                    "The first bold line creates a top-to-bottom box layout and sets it up as the layout manager for listPane. The two arguments to the BoxLayout constructor are the container that it manages and the axis along which the components will be laid out. The PAGE_AXIS constant specifies that components should be laid out in the direction that lines flow across a page as determined by the target container's ComponentOrientation property. The LINE_AXIS constant specifies that components should be laid out in the direction of a line of text as determined by the target container's ComponentOrientation property. These constants allow for internationalization, by laying out components in their container with the correct left-to-right, right-to-left or top-to-bottom orientation for the language being used.\n" +
                    "\n" +
                    "The next three bold lines add the label and scroll pane to the container, separating them with a rigid area — an invisible component used to add space between components. In this case, the rigid area has no width and puts exactly 5 pixels between the label and scroll pane. Rigid areas are discussed later, in Using Invisible Components as Filler.\n" +
                    "\n" +
                    "The next chunk of bold code creates a left-to-right box layout and sets it up for the buttonPane container. Then the code adds two buttons to the container, using a rigid area to put 10 pixels between the buttons. To place the buttons at the right side of their container, the first component added to the container is glue. This glue is an invisible component that grows as necessary to absorb any extra space in its container. Glue is discussed in Using Invisible Components as Filler.\n" +
                    "\n" +
                    "As an alternative to using invisible components, you can sometimes use empty borders to create space around components, most particularly panels. For example, the preceding code snippet uses empty borders to put 10 pixels between all sides of the dialog and its contents, and between the two parts of the contents. Borders are completely independent of layout managers. They are simply how Swing components draw their edges and provide padding between the content of the component and the edge. See How to Use Borders for more information.\n" +
                    "\n" +
                    "The following sections discuss BoxLayout in more detail:\n" +
                    "\n" +
                    "    Box layout features\n" +
                    "    Using invisible components as filler\n" +
                    "    Fixing alignment problems\n" +
                    "    Specifying component sizes\n" +
                    "    The box layout API\n" +
                    "    Examples that use box layouts\n" +
                    "\n" +
                    "Do not let the length of the BoxLayout discussion scare you! You can probably use BoxLayout with the information you already have. If you run into trouble or you want to take advantage of BoxLayout's power, read on.\n" +
                    "Box Layout Features\n" +
                    "\n" +
                    "As said before, BoxLayout arranges components either on top of each other or in a row. As the box layout arranges components, it takes the components' alignments and minimum, preferred, and maximum sizes into account. In this section, we will talk about top-to-bottom layout. The same concepts apply to left-to-right or right-to-left layout. You simply substitute X for Y, height for width, and so on.\n" +
                    "Version note: Before JDK version 1.4, no constants existed for specifying the box layout's axis in a localizable way. Instead, you specified X_AXIS (left to right) or Y_AXIS (top to bottom) when creating the BoxLayout. Our examples now use the constants LINE_AXIS and PAGE_AXIS, which are preferred because they enable programs to adjust to languages that have different orientations. In the default, left-to-right orientation, LINE_AXIS specifies left-to-right layout and PAGE_AXIS specifies top-to-bottom layout.\n" +
                    "\n" +
                    "When a BoxLayout lays out components from top to bottom, it tries to size each component at the component's preferred height. If the vertical space of the layout does not match the sum of the preferred heights, then BoxLayout tries to resize the components to fill the space. The components either grow or shrink to fill the space, with BoxLayout honoring the minimum and maximum sizes of each of the components. Any extra space appears at the bottom of the container.\n" +
                    "\n" +
                    "For a top-to-bottom box layout, the preferred width of the container is that of the maximum preferred width of the children. If the container is forced to be wider than that, BoxLayout attempts to size the width of each component to that of the container's width (minus insets). If the maximum size of a component is smaller than the width of the container, then X alignment comes into play.\n" +
                    "\n" +
                    "The X alignments affect not only the components' positions relative to each other, but also the location of the components (as a group) within their container. The following figures illustrate alignment of components that have restricted maximum widths.\n" +
                    "Three left-aligned components  Three center-aligned components  Three right-aligned components\n" +
                    "\n" +
                    "In the first figure, all three components have an X alignment of 0.0 (Component.LEFT_ALIGNMENT). This means that the components' left sides should be aligned. Furthermore, it means that all three components are positioned as far left in their container as possible.\n" +
                    "\n" +
                    "In the second figure, all three components have an X alignment of 0.5 (Component.CENTER_ALIGNMENT). This means that the components' centers should be aligned, and that the components should be positioned in the horizontal center of their container.\n" +
                    "\n" +
                    "In the third figure, the components have an X alignment of 1.0 (Component.RIGHT_ALIGNMENT). You can guess what that means for the components' alignment and position relative to their container.\n" +
                    "\n" +
                    "You might be wondering what happens when the components have both restricted maximum sizes and different X alignments. The next figure shows an example of this:\n" +
                    "Three components with mixed X alignments\n" +
                    "\n" +
                    "As you can see, the left side of the component with an X alignment of 0.0 (Component.LEFT_ALIGNMENT) is aligned with the center of the component that has the 0.5 X alignment (Component.CENTER_ALIGNMENT), which is aligned with the right side of the component that has an X alignment of 1.0 (Component.RIGHT_ALIGNMENT). Mixed alignments like this are further discussed in Fixing Alignment Problems.\n" +
                    "\n" +
                    "What if none of the components has a maximum width? In this case, if all the components have identical X alignment, then all components are made as wide as their container. If the X alignments are different, then any component with an X alignment of 0.0 (left) or 1.0 (right) will be smaller. All components with an intermediate X alignment (such as center) will be as wide as their container. Here are two examples:\n" +
                    "Three components with mixed X alignments and no maximum size  Three components with mixed X alignments and no maximum size\n" +
                    "\n" +
                    "To get to know BoxLayout better, you can run your own experiments with BoxLayoutDemo2.\n" +
                    "Try this: \n" +
                    "\n" +
                    "    Click the Launch button to run BoxLayoutDemo2 using Java™ Web Start (download JDK 7 or later). Alternatively, to compile and run the example yourself, consult the example index.Launches the BoxLayoutDemo2 example\n" +
                    "\n" +
                    "    You can see the code in BoxLayoutDemo2.java.\n" +
                    "    You will see a window like the one above that contains three rectangles. Each rectangle is an instance of BLDComponent, which is a JComponent subclass.\n" +
                    "    Click inside one of the rectangles.\n" +
                    "    This is how you change the rectangle's X alignment.\n" +
                    "    Click the check box at the bottom of the window.\n" +
                    "    This turns off restricted sizing for all the rectangles.\n" +
                    "    Make the window taller.\n" +
                    "    This makes the rectangles' container larger than the sum of the rectangles' preferred sizes. The container is a JPanel that has a red outline, so that you can tell where the container's edges are.\n" +
                    "\n" +
                    "Using Invisible Components as Filler\n" +
                    "\n" +
                    "Each component controlled by a box layout butts up against its neighboring components. If you want to have space between components, you can either add an empty border to one or both components, or insert invisible components to provide the space. You can create invisible components with the help of the Box class.\n" +
                    "\n" +
                    "The Box class defines a nested class, Box.Filler, that is a transparent component that paints nothing, and is used to provide space between other components. However, Filler is not actually invisible, because setVisible(false) is not invoked. The Box class provides convenience methods to help you create common kinds of filler. The following table gives details about creating invisible components with Box and Box.Filler.\n" +
                    "Type \tSize Constraints \tHow to Create\n" +
                    "rigid area \tThree components with mixed X alignments and no maximum size\n" +
                    "\tBox.createRigidArea(size)\n" +
                    "glue, horizontal \tThree components with mixed X alignments and no maximum size\n" +
                    "\tBox.createHorizontalGlue()\n" +
                    "glue, vertical \tThree components with mixed X alignments and no maximum size\n" +
                    "\tBox.createVerticalGlue()\n" +
                    "custom Box.Filler \t(as specified) \tnew Box.Filler(minSize, prefSize, maxSize)\n" +
                    "\n" +
                    "Here is how you generally use each type of filler:\n" +
                    "\n" +
                    "Rigid area\n" +
                    "    Use this when you want a fixed-size space between two components. For example, to put 5 pixels between two components in a left-to-right box, you can use this code:\n" +
                    "\n" +
                    "    container.add(firstComponent);\n" +
                    "    container.add(Box.createRigidArea(new Dimension(5,0)));\n" +
                    "    container.add(secondComponent);\n" +
                    "\n" +
                    "    Without rigid areaWith rigid area\n" +
                    "\n" +
                    "    Note: The Box class provides another kind of filler for putting fixed space between components: a vertical or horizontal strut. Unfortunately, struts have unlimited maximum heights or widths (for horizontal and vertical struts, respectively). This means that if you use a horizontal box within a vertical box, for example, the horizontal box can sometimes become too tall. For this reason, we recommend that you use rigid areas instead of struts.\n" +
                    "Glue\n" +
                    "    Use this to specify where excess space in a layout should go. Think of it as a kind of elastic glue — stretchy and expandable, yet taking up no space unless you pull apart the components that it is sticking to. For example, by putting horizontal glue between two components in a left-to-right box, you make any extra space go between those components, instead of to the right of all the components. Here is an example of making the space in a left-to-right box go between two components, instead of to the right of the components:\n" +
                    "\n" +
                    "    container.add(firstComponent);\n" +
                    "    container.add(Box.createHorizontalGlue());\n" +
                    "    container.add(secondComponent);\n" +
                    "\n" +
                    "    Without horizontal glueWith glue\n" +
                    "Custom Box.Filler\n" +
                    "    Use this to specify a component with whatever minimum, preferred, and maximum sizes you want. For example, to create some filler in a left-to-right layout that puts at least 5 pixels between two components and ensures that the container has a minimum height of 100 pixels, you could use this code:\n" +
                    "\n" +
                    "    container.add(firstComponent);\n" +
                    "    Dimension minSize = new Dimension(5, 100);\n" +
                    "    Dimension prefSize = new Dimension(5, 100);\n" +
                    "    Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);\n" +
                    "    container.add(new Box.Filler(minSize, prefSize, maxSize));\n" +
                    "    container.add(secondComponent);\n" +
                    "\n" +
                    "    Without custom filler With custom filler\n" +
                    "\n" +
                    "Fixing Alignment Problems\n" +
                    "\n" +
                    "Two types of alignment problems sometimes occur with BoxLayout:\n" +
                    "\n" +
                    "    A group of components all have the same alignment, but you want to change their alignment to make them look better. For example, instead of having the centers of a group of left-to-right buttons all in a line, you might want the bottoms of the buttons to be aligned. Here is an example:\n" +
                    "\n" +
                    "    Customizing alignment\n" +
                    "    Two or more components controlled by a BoxLayout have different default alignments, which causes them to be mis-aligned. For example, as the following shows, if a label and a panel are in a top-to-bottom box layout, the label's left edge is, by default, aligned with the center of the panel.\n" +
                    "\n" +
                    "    X alignment mismatch\n" +
                    "\n" +
                    "In general, all the components controlled by a top-to-bottom BoxLayout object should have the same X alignment. Similarly, all the components controlled by a left-to-right Boxlayout should generally have the same Y alignment. You can set a JComponent's X alignment by invoking its setAlignmentX method. An alternative available to all components is to override the getAlignmentX method in a custom subclass of the component class. Similarly, you set the Y alignment of a component by invoking the setAlignmentY method or by overriding getAlignmentY.\n" +
                    "\n" +
                    "Here is an example, taken from an application called BoxAlignmentDemo, of changing the Y alignments of two buttons so that the bottoms of the buttons are aligned:\n" +
                    "\n" +
                    "button1.setAlignmentY(Component.BOTTOM_ALIGNMENT);\n" +
                    "button2.setAlignmentY(Component.BOTTOM_ALIGNMENT);\n" +
                    "\n" +
                    "Click the Launch button to run BoxAlignmentDemo using Java™ Web Start (download JDK 7 or later). Alternatively, to compile and run the example yourself, consult the example index.\n" +
                    "Launches the BoxAlignmentDemo example"+
        "\n" +
                "    Customizing alignment\n" +
                "    Two or more components controlled by a BoxLayout have different default alignments, which causes them to be mis-aligned. For example, as the following shows, if a label and a panel are in a top-to-bottom box layout, the label's left edge is, by default, aligned with the center of the panel.\n" +
                "\n" +
                "    X alignment mismatch\n" +
                "\n" +
                "In general, all the components controlled by a top-to-bottom BoxLayout object should have the same X alignment. Similarly, all the components controlled by a left-to-right Boxlayout should generally have the same Y alignment. You can set a JComponent's X alignment by invoking its setAlignmentX method. An alternative available to all components is to override the getAlignmentX method in a custom subclass of the component class. Similarly, you set the Y alignment of a component by invoking the setAlignmentY method or by overriding getAlignmentY.\n" +
                "\n" +
                "Here is an example, taken from an application called BoxAlignmentDemo, of changing the Y alignments of two buttons so that the bottoms of the buttons are aligned:\n" +
                "\n" +
                "button1.setAlignmentY(Component.BOTTOM_ALIGNMENT);\n" +
                "button2.setAlignmentY(Component.BOTTOM_ALIGNMENT);\n" +
                "\n" +
                "Click the Launch button to run BoxAlignmentDemo using Java™ Web Start (download JDK 7 or later). Alternatively, to compile and run the example yourself, consult the example index.\n" +
                "Launches the BoxAlignmentDemo example"+
        "\n" +
                "    Customizing alignment\n" +
                "    Two or more components controlled by a BoxLayout have different default alignments, which causes them to be mis-aligned. For example, as the following shows, if a label and a panel are in a top-to-bottom box layout, the label's left edge is, by default, aligned with the center of the panel.\n" +
                "\n" +
                "    X alignment mismatch\n" +
                "\n" +
                "In general, all the components controlled by a top-to-bottom BoxLayout object should have the same X alignment. Similarly, all the components controlled by a left-to-right Boxlayout should generally have the same Y alignment. You can set a JComponent's X alignment by invoking its setAlignmentX method. An alternative available to all components is to override the getAlignmentX method in a custom subclass of the component class. Similarly, you set the Y alignment of a component by invoking the setAlignmentY method or by overriding getAlignmentY.\n" +
                "\n" +
                "Here is an example, taken from an application called BoxAlignmentDemo, of changing the Y alignments of two buttons so that the bottoms of the buttons are aligned:\n" +
                "\n" +
                "button1.setAlignmentY(Component.BOTTOM_ALIGNMENT);\n" +
                "button2.setAlignmentY(Component.BOTTOM_ALIGNMENT);\n" +
                "\n" +
                "Click the Launch button to run BoxAlignmentDemo using Java™ Web Start (download JDK 7 or later). Alternatively, to compile and run the example yourself, consult the example index.\n" +
                "Launches the BoxAlignmentDemo example");

        //ScrollPane initialisation
        messageScrollPane =  new JScrollPane(chatTextArea);
        messageScrollPane.createHorizontalScrollBar();

            //Prefered Size
            this.messageScrollPane.setPreferredSize(new Dimension(400,620));

            //AddScrollBar to container
            chatPanel.add(messageScrollPane);

        //Text Fields initialisation
        serverIpTextField = new JTextField("0.0.0.0");
        serverPortTextField = new JTextField("5100");
        pseudonymTextField = new JTextField("INSA User");

        chatRoomNameTextField = new JTextField("hexanôme");

            //Prefered size
            serverIpTextField.setPreferredSize(new Dimension(400,20));
            serverPortTextField.setPreferredSize(new Dimension(400,20));
            pseudonymTextField.setPreferredSize(new Dimension(400,20));

            chatRoomNameTextField.setPreferredSize(new Dimension(400,20));

            messageTextField.setPreferredSize(new Dimension(400,100));

            //Add Text Fields to cntainers
            serverIpPanel.add(serverIpTextField);
            serverPortPanel.add(serverPortTextField);
            serverPseudonymPanel.add(pseudonymTextField);

            chatRoomNamePanel.add(chatRoomNameTextField);

            messageTypingPanel.add(messageTextField);

        //Window initialisation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public class LoadRequestListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {

        }

    }

    public void publishMessage(String msg){
        messageLabel.setText(msg);
        this.setTitle(msg);
    }
}
